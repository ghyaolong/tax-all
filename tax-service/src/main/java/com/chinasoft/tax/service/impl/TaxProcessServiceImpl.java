package com.chinasoft.tax.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chinasoft.tax.common.utils.DateUtil;
import com.chinasoft.tax.common.utils.IDGeneratorUtils;
import com.chinasoft.tax.common.utils.BusinessFlowNumberUtil;
import com.chinasoft.tax.dao.TTaxApplicationDetailMapper;
import com.chinasoft.tax.dao.TTaxApplicationMapper;
import com.chinasoft.tax.dao.TUserMapper;
import com.chinasoft.tax.po.TTaxApplication;
import com.chinasoft.tax.po.TTaxApplicationDetail;
import com.chinasoft.tax.service.TaxProcessService;
import com.chinasoft.tax.service.UserService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageHelper;
import org.activiti.engine.*;
import org.activiti.engine.history.*;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TaxProcessServiceImpl implements TaxProcessService {

    private final static String TAX_PROCESS_KEY = "taxProcess";

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RuntimeService runtimeService;
    private static RuntimeService myRuntimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;
    private static TaskService myTaskService;

    @Resource
    private HistoryService historyService;

    @Autowired
    private ProcessEngine processEngine;

    @Resource
    private TTaxApplicationMapper taxApplicationMapper;

    @Resource
    private TTaxApplicationDetailMapper taxApplicationDetailMapper;

    private static TTaxApplicationMapper myTaxApplicationMapper;
    private static TTaxApplicationDetailMapper myTaxApplicationDetailMapper;

    @Value("${tax.file.upload}")
    private String uploadPath;

    public static RuntimeService getRuntimeService() {
        return myRuntimeService;
    }

    @Resource
    private UserService userService;

    public static TTaxApplicationMapper getTTaxApplicationMapper() {
        return myTaxApplicationMapper;
    }

    public static TTaxApplicationDetailMapper getTTaxApplicationDetailMapper() {
        return myTaxApplicationDetailMapper;
    }

    @Resource
    private TUserMapper userMapper;

    /**
     * 获取任务列表
     *
     * @return
     */
    @Override
    public MyPageInfo<TaxApplicationVo> listTasks(PageVo pageVo, SearchVo searchVo) {
        if (pageVo != null && searchVo != null) {
            String userId = searchVo.getUserId();
            PageHelper.startPage(pageVo.getPageNumber(), pageVo.getPageSize());
            //根据流程定义key及受理人查询待办
            List<Task> list = taskService.createTaskQuery().taskAssignee(userId).list();
            List<TaxApplicationVo> taxApplicationVoList = convertTaskVoList(list);

            taxApplicationVoList = taxApplicationVoList.stream().filter(bean -> {

                Date createTime = bean.getCreateTime();
                String startDate = searchVo.getStartDate();
                String endDate = searchVo.getEndDate();
                String companyId = searchVo.getCompanyId();
                String applicationId = searchVo.getApplicationId();
                String serialNumber = searchVo.getSerialNumber();

                if (!StringUtils.isEmpty(companyId)) {
                    boolean equals = companyId.equals(bean.getCompanyId());
                    if (!equals) {
                        return false;
                    }
                }

                if (!StringUtils.isEmpty(applicationId)) {
                    boolean equals = applicationId.equals(bean.getApplicantId());
                    if (!equals) {
                        return false;
                    }
                }

                if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate) && createTime != null) {
                    Date s = DateUtil.getDate(String.format("%s 00:00:00", startDate));
                    Date e = DateUtil.getDate(String.format("%s 23:59:59", endDate));
                    String a = DateUtil.getDate(createTime);
                    createTime = DateUtil.getDate(a);
                    boolean effectiveDate = DateUtil.isEffectiveDate(createTime, s, e);
                    if (!effectiveDate) {
                        return effectiveDate;
                    }
                }

                if (!StringUtils.isEmpty(serialNumber)) {
                    boolean equals = serialNumber.equals(bean.getSerialNumber());
                    return equals;
                }

                return true;
            }).collect(Collectors.toList());

            MyPageInfo<TaxApplicationVo> page = new MyPageInfo<>(taxApplicationVoList);
            page.setTotalElements(taxApplicationVoList.size());
            page.setPageNum(pageVo.getPageNumber());
            return page;
        } else {
            return null;
        }
    }

    public static TaskService getTaskService() {
        return myTaskService;
    }

    /**
     * 审批流程
     *
     * @param taskId         流程编号
     * @param operateApprove 审批结果
     * @param comment        批注
     * @param userId         审批人
     * @return
     */
    @Override
    public boolean audit(String taskId, Integer operateApprove, String comment, String userId, String currentHandler, JSONObject jsonObject) {

        boolean isAudit = true;
        try {

            // 由于通知监听是从xml中实例化的对象，取不到Spring bean，所以在这里设置变量
            initService();

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

            // 利用任务对象，获取流程实例id
            String processInstancesId = task.getProcessInstanceId();

            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            TaxApplicationVo bean = null;
            if (jsonObject != null) {
                bean = jsonObject.toJavaObject(TaxApplicationVo.class);
            }

            if (bean == null) {
                bean = (TaxApplicationVo) runtimeService.getVariable(pi.getId(), "bean");
            }

            String isOperateApprove;
            if (operateApprove == 0) {
                isOperateApprove = "true";
                bean.setCurrentHandler(currentHandler);
                bean.setStatus(2);
                comment = String.format("同意,%s", comment);
            } else {
                isOperateApprove = "false";
                bean.setCurrentHandler(bean.getApplicantId());
                bean.setStatus(3);
                comment = String.format("驳回,%s", comment);
            }

            // 添加批注时候的审核人，通常应该从session获取
            Authentication.setAuthenticatedUserId(userId);
            // 添加审批备注
            taskService.addComment(taskId, processInstancesId, comment);

            // 审批意见
            List<Comment> commentList = taskService.getProcessInstanceComments(task.getProcessInstanceId());

            List<AuditLogVo> auditLogVoList = new ArrayList<>();

            // 处理启动流程审批记录
            /*HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
            AuditLogVo startAuditLogVo = getStartAuditLogVo(historicProcessInstance.getStartUserId(), historicProcessInstance.getStartTime());
            auditLogVoList.add(startAuditLogVo);*/

            commentList.stream().forEach(commentBean -> {

                HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(commentBean.getTaskId()).singleResult();
                AuditLogVo auditLogVo = new AuditLogVo();
                auditLogVo.setTaskName(historicTaskInstance.getName());

                UserVo userVo = userService.getUserInfoByUserIdAndKey(commentBean.getUserId(), historicTaskInstance.getTaskDefinitionKey());
                if (userVo != null) {
                    auditLogVo.setName(userVo.getRealName());
                    List<RoleVo> roles = userVo.getRoles();
                    if (roles != null) {
                        RoleVo roleVo = roles.get(0);
                        auditLogVo.setRoleName(roleVo.getName());
                    }
                }

                String fullMessage = commentBean.getFullMessage();
                if (!StringUtils.isEmpty(fullMessage)) {
                    String[] split = fullMessage.split(",");
                    auditLogVo.setAuditResult(split[0]);
                    auditLogVo.setAdvice(split[1]);
                }
                auditLogVo.setAuditDate(commentBean.getTime());

                auditLogVoList.add(auditLogVo);
            });

            bean.setAuditLogVoList(auditLogVoList);

            String executionId = task.getExecutionId();
            runtimeService.setVariable(executionId, "bean", bean);

            Map<String, Object> map = new HashMap<>();
            map.put("operateApprove", isOperateApprove);
            taskService.complete(taskId, map);

        } catch (Exception ex) {
            ex.printStackTrace();
            isAudit = false;
        } finally {
            return isAudit;
        }
    }

    /**
     * 查询审批备注记录
     *
     * @param taskId
     * @return
     */
    @Override
    public List<Comment> searchAuditComment(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
        list.stream().forEach(bean -> {
            System.out.println(bean.getFullMessage());
        });
        return list;
    }

    /**
     * 重新提交申请
     * @param bean 更新后的实体
     * @param operateApprove 提交类型
     * @param taskId
     */
    @Override
    public boolean resubmit(TaxApplicationVo bean, Integer operateApprove, String taskId) {
        boolean isAudit = true;
        try {

            // 由于通知监听是从xml中实例化的对象，取不到Spring bean，所以在这里设置变量
            initService();

            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            String executionId = task.getExecutionId();

            String isOperateApprove = "";
            if (operateApprove == 0) {
                isOperateApprove = "true";
                bean.setStatus(2);

                String processInstanceId = task.getProcessInstanceId();
                // 添加批注时候的审核人，通常应该从session获取
                Authentication.setAuthenticatedUserId(task.getAssignee());
                // 添加审批备注
                taskService.addComment(taskId, processInstanceId, String.format("重新提交,%s", "重新提交申报流程"));

                // 审批意见
                List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);

                List<AuditLogVo> auditLogVoList = new ArrayList<>();

                // 处理启动流程审批记录
                /*HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
                AuditLogVo startAuditLogVo = getStartAuditLogVo(historicProcessInstance.getStartUserId(), historicProcessInstance.getStartTime());
                auditLogVoList.add(startAuditLogVo);*/

                commentList.stream().forEach(comment -> {

                    HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(comment.getTaskId()).singleResult();
                    AuditLogVo auditLogVo = new AuditLogVo();
                    auditLogVo.setTaskName(historicTaskInstance.getName());

                    UserVo userVo = userService.getUserInfoByUserIdAndKey(comment.getUserId(), historicTaskInstance.getTaskDefinitionKey());
                    if (userVo != null) {
                        auditLogVo.setName(userVo.getRealName());
                        List<RoleVo> roles = userVo.getRoles();
                        if (roles != null) {
                            RoleVo roleVo = roles.get(0);
                            auditLogVo.setRoleName(roleVo.getName());
                        }
                    }

                    String fullMessage = comment.getFullMessage();
                    if (!StringUtils.isEmpty(fullMessage)) {
                        String[] split = fullMessage.split(",");
                        auditLogVo.setAuditResult(split[0]);
                        auditLogVo.setAdvice(split[1]);
                    }
                    auditLogVo.setAuditDate(comment.getTime());

                    auditLogVoList.add(auditLogVo);
                });

                bean.setAuditLogVoList(auditLogVoList);
            } else {
                String processInstanceId = task.getProcessInstanceId();
                // 添加批注时候的审核人，通常应该从session获取
                Authentication.setAuthenticatedUserId(task.getAssignee());
                // 添加审批备注
                taskService.addComment(taskId, processInstanceId, String.format("放弃,%s", "放弃申报流程"));

                isOperateApprove = "false";
                bean.setStatus(4);
            }

            bean.setCurrentLink("over");
            runtimeService.setVariable(executionId, "bean", bean);

            // 更新实体对象
            updateTaxFlowProcess(bean);

            Map<String, Object> map = new HashMap<>();
            map.put("operateApprove", isOperateApprove);
            taskService.complete(taskId, map);

        } catch (Exception ex) {
            ex.printStackTrace();
            isAudit = false;
        } finally {
            return isAudit;
        }
    }

    /**
     * 查询历史纪录
     *
     * @param userId
     * @return
     */
    public List<TaxApplicationHistoryVo> searchHistory(String userId) {
        List<TaxApplicationHistoryVo> result = new ArrayList<>();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().taskAssignee(userId).finished().list();

        List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).orderByTaskId().desc().list();
        for (HistoricActivityInstance bean : list) {
            System.out.println(bean.getActivityName());
        }

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(userId).singleResult();
        if (pi != null) {
            System.out.println("流程正在执行！");
            TaxApplicationVo bean = (TaxApplicationVo) runtimeService.getVariable(pi.getId(), "bean");
        } else {
            System.out.println("流程已经执行结束！");
        }

        List<HistoricProcessInstance> hpis1 = historyService.createHistoricProcessInstanceQuery().finished().startedBy(userId).orderByProcessInstanceStartTime().desc().listPage(1, 10);


        List<HistoricProcessInstance> hpis2 = historyService.createHistoricProcessInstanceQuery().involvedUser(userId).orderByProcessInstanceStartTime().desc().listPage(1, 10);
        // historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByHistoricTaskInstanceEndTime().asc().list();
        List<HistoricTaskInstance> hpis3 = historyService.createHistoricTaskInstanceQuery().processInstanceId(userId).orderByHistoricTaskInstanceEndTime().asc().list();


        return result;
    }

    @Override
    public ProcessInstance startProcess(TaxApplicationVo taxApplicationVo) {

        if (taxApplicationVo == null) {
            return null;
        }

        // 由于通知监听是从xml中实例化的对象，取不到Spring bean，所以在这里设置变量
        initService();

        String bussinessKey = "";
        if (StringUtils.isEmpty(taxApplicationVo.getId())) {
            bussinessKey = IDGeneratorUtils.getUUID32();
        } else {
            bussinessKey = taxApplicationVo.getId();
        }

        // 生成流水号
        String businessFlowNumber = BusinessFlowNumberUtil.getBusinessFlowNumber();
        taxApplicationVo.setBusinessFlowNumber(businessFlowNumber);

        taxApplicationVo.setCreateTime(new Date());
        taxApplicationVo.setSaveTime(new Date());

        ProcessInstance pi = null;

        try {
            // 流程实例化
            ProcessDefinition pd = null;
            List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(TAX_PROCESS_KEY).list();
            if (list != null && list.size() > 0) {
                pd = list.get(0);
            } else {
                return null;
            }

            // 初始化任务参数
            Map<String, Object> vars = new HashMap<>();
            vars.put("bean", taxApplicationVo);

            String deploymentId = pd.getDeploymentId();
            String filePath = pd.getDiagramResourceName();
            // 资源编号
            taxApplicationVo.setDeploymentId(deploymentId);
            // 资源地址
            String newFilePath = String.format("%s.%s", bussinessKey, "png");
            boolean isCreateImage = createImage(deploymentId, filePath, newFilePath);
            if (isCreateImage) {
                taxApplicationVo.setFilePath(newFilePath);
            }
            // 设置为 复核申报 节点
            taxApplicationVo.setCurrentLink("reviewProcess");
            taxApplicationVo.setStatus(2);

            // 创建业务模型
            if (StringUtils.isEmpty(taxApplicationVo.getId())) {
                taxApplicationVo.setId(bussinessKey);
                saveTaxFlowPrcess(taxApplicationVo);
            }

            identityService.setAuthenticatedUserId(taxApplicationVo.getApplicantId());

            // 启动流程
            pi = runtimeService.startProcessInstanceByKey(pd.getKey(), bussinessKey, vars);
            // 流程编号
            taxApplicationVo.setSerialNumber(pi.getId());
            taxApplicationVo.setProcessInstanceId(pi.getProcessInstanceId());

            /*// 添加批注时候的审核人，通常应该从session获
            Authentication.setAuthenticatedUserId(taxApplicationVo.getApplicantId());
            // 添加审批备注
            taskService.addComment(taskId, pi.getProcessInstanceId(), "发起流程,启动税金申请流程");*/

            // 更新业务模型
            updateTaxFlowProcess(taxApplicationVo);

            return pi;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return pi;
        }
    }

    @Override
    public MyPageInfo<DoneVo> searchHistory(PageVo pageVo, SearchVo searchVo) {

        if (pageVo != null && searchVo != null) {
            String userId = searchVo.getUserId();
            boolean isAdmin = userService.isAdmin();

            Set<String> processInstanceIdSet;
            // 需要验证是否是管理员
            if (isAdmin) {
                processInstanceIdSet = queryHistoryProcessInstanceAdmin();
            } else {
                List<HistoricTaskInstance> hisTaskList = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).orderByTaskId().desc().list();
                Map<String, List<HistoricTaskInstance>> collect = hisTaskList.stream().collect(Collectors.groupingBy(HistoricTaskInstance::getProcessInstanceId));
                processInstanceIdSet = collect.keySet();
            }

            List<String> processInstanceIds = new ArrayList<>(processInstanceIdSet);

            List<DoneVo> doneVoList = new ArrayList<>();

            // 查询当前用户为启动者数据
            List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().startedBy(userId).list();

            for (HistoricProcessInstance bean : list) {
                String processInstanceId = ((HistoricProcessInstanceEntityImpl) bean).getProcessInstanceId();
                processInstanceIds.add(processInstanceId);
            }

            processInstanceIds = processInstanceIds.stream().distinct().collect(Collectors.toList());

            // 查询已办数据
            processInstanceIds.stream().forEach(processInstanceId -> {
                DoneVo doneVo = getDoneVo(processInstanceId);
                doneVoList.add(doneVo);
            });

            // 补充历史数据
            List<TTaxApplication> taxApplications;
            if (isAdmin) {
                taxApplications = taxApplicationMapper.searchHistory(null);
            } else {
                taxApplications = taxApplicationMapper.searchHistory(userId);
            }

            taxApplications.stream().forEach(taxApplication -> {

                String currentLink = "over";
                // 判断异常数据
                List<TTaxApplicationDetail> taxApplicationVoDetails = taxApplication.getDetails();
                boolean anyMatch = false;
                if (taxApplicationVoDetails != null && taxApplicationVoDetails.size() > 0) {
                    anyMatch = taxApplicationVoDetails.stream().anyMatch(bean -> !StringUtils.equals(bean.getPayableTax().toString(), bean.getTaxPaid().toString()) || !StringUtils.equals(bean.getLateFeePayable().toString(), bean.getOverduePayment().toString()));
                }
                if (anyMatch && StringUtils.isEmpty(taxApplication.getCurrentHandler())) {
                    currentLink = "overAbnormalData";
                }

                // 公司名称
                String companyId = taxApplication.getCompanyId();
                String companyName = taxApplication.getCompanyName();
                // 启动时间
                Date startTime = taxApplication.getSaveTime();

                DoneVo doneVo = new DoneVo();
                // 已办数据
                doneVo.setProcInstId(taxApplication.getBusinessFlowNumber());
                doneVo.setCurrentHandler("");
                doneVo.setCurrentLink(currentLink);
                doneVo.setFlowNum(taxApplication.getBusinessFlowNumber());
                doneVo.setCompanyId(companyId);
                doneVo.setCompanyName(companyName);
                doneVo.setCreateTime(startTime);
                doneVo.setSaveTime(startTime);
                doneVo.setSerialNumber(taxApplication.getBusinessFlowNumber());
                // 已办详情
                TaxApplicationVo taxApplicationVo = new TaxApplicationVo();
                BeanUtils.copyProperties(taxApplication, taxApplicationVo);
                doneVo.setTaxApplicationVo(taxApplicationVo);
                doneVo.setAuditLogVoList(null);
                doneVoList.add(doneVo);
            });

            List<DoneVo> collect1 = doneVoList.stream().filter(bean -> {

                Date createTime = bean.getCreateTime();
                String currentLink = bean.getCurrentLink();

                // 去除当前正在执行的节点
                /*List<Task> taskList = taskService.createTaskQuery().processInstanceId(bean.getProcInstId()).list();
                boolean anyMatch = taskList.stream().anyMatch(match -> StringUtils.equals(match.getTaskDefinitionKey(), currentLink));
                if (anyMatch) {
                    return false;
                }*/

                String startDate = searchVo.getStartDate();
                String endDate = searchVo.getEndDate();
                String companyId = searchVo.getCompanyId();
                String applicationId = searchVo.getApplicationId();
                Integer flowStatus = searchVo.getFlowStatus();
                String serialNumber = searchVo.getSerialNumber();

                if (!StringUtils.isEmpty(companyId)) {
                    boolean equals = companyId.equals(bean.getCompanyId());
                    if (!equals) {
                        return false;
                    }
                }

                if (!StringUtils.isEmpty(applicationId)) {
                    boolean equals = applicationId.equals(bean.getTaxApplicationVo().getApplicantId());
                    if (!equals) {
                        return false;
                    }
                }

                if (!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate) && createTime != null) {
                    Date s = DateUtil.getDate(String.format("%s 00:00:00", startDate));
                    Date e = DateUtil.getDate(String.format("%s 23:59:59", endDate));
                    String a = DateUtil.getDate(createTime);
                    createTime = DateUtil.getDate(a);
                    boolean effectiveDate = DateUtil.isEffectiveDate(createTime, s, e);
                    if (!effectiveDate) {
                        return effectiveDate;
                    }
                }

                if (!StringUtils.isEmpty(serialNumber)) {
                    boolean equals = serialNumber.equals(bean.getFlowNum());
                    if (!equals) {
                        return false;
                    }
                }

                if (flowStatus != null && !StringUtils.isEmpty(currentLink)) {
                    if (flowStatus == 1) {
                        if (!"over".equals(currentLink)) {
                            return false;
                        }
                    }

                    if (flowStatus == 0) {
                        return !"over".equals(currentLink);
                    }
                }

                return true;
            }).collect(Collectors.toList());

            MyPageInfo<DoneVo> page = new MyPageInfo<>(collect1);
            page.setTotalElements(collect1.size());
            page.setPageNum(pageVo.getPageNumber());
            return page;
        } else {
            return null;
        }
    }

    /**
     * 查询导出信息
     * @param processInstanceId
     */
    public DoneVo searchExportInfo(String processInstanceId) {
        DoneVo doneVo = getDoneVo(processInstanceId);
        return doneVo;
    }

    /**
     * 当用户是管理员时，则获取全部流程实例ID(processInstanceIds)
     * @return
     */
    private Set<String> queryHistoryProcessInstanceAdmin() {

        HistoryService historyService = processEngine.getHistoryService();

        //创建历史流程实例，查询对象
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();

        //设置查询条件
        //指定流程定义key，只查询某个业务流程的实例
        String processDefinitionKey = "taxProcess";
        historicProcessInstanceQuery.processDefinitionKey(processDefinitionKey);
        //设置只查询已完成的
        // historicProcessInstanceQuery.finished();

        //数据列表
        List<HistoricProcessInstance> list = historicProcessInstanceQuery.list();
        Map<String, List<HistoricProcessInstance>> collect = list.stream().collect(Collectors.groupingBy(HistoricProcessInstance::getId));
        Set<String> processInstanceIds = collect.keySet();


        /*for (HistoricProcessInstance historicProcessInstance : list) {
            System.out.println("===================");
            System.out.println("流程实例所属流程定义id：" + historicProcessInstance.getProcessDefinitionId());
            System.out.println("流程实例id：" + historicProcessInstance.getId());
            System.out.println("业务标识：" + historicProcessInstance.getBusinessKey());
            System.out.println("开始执行时间：" + historicProcessInstance.getStartTime());
            System.out.println("结束执行时间：" + historicProcessInstance.getEndTime());
            System.out.println("执行时长：" + historicProcessInstance.getDurationInMillis());
        }*/

        return processInstanceIds;
    }

    /**
     * 处理启动流程审批记录
     * @param userId 发起人
     * @param startTime 发起时间
     * @return
     */
    private AuditLogVo getStartAuditLogVo(String userId, Date startTime) {
        // 处理启动节点审批记录
        AuditLogVo startAuditLogVo = new AuditLogVo() {

            UserVo userVo = userService.getUserInfoByUserIdAndKey(userId, "approvalProcess");

            @Override
            public String getName() {
                if (userVo != null) {
                    return userVo.getRealName();
                } else {

                    return "";
                }
            }

            @Override
            public String getRoleName() {
                List<RoleVo> roles = null;
                if (userVo != null) {
                    roles = userVo.getRoles();
                }
                if (roles != null) {
                    RoleVo roleVo = roles.get(0);
                    return roleVo.getName();
                } else {
                    return "";
                }
            }

            @Override
            public String getTaskName() {
                return "启动流程";
            }

            @Override
            public String getAuditResult() {
                return "启动";
            }

            @Override
            public String getAdvice() {
                return "启动流程";
            }

            @Override
            public Date getAuditDate() {
                return startTime;
            }
        };
        return startAuditLogVo;
    }

    /**
     * 转换流程对象
     *
     * @param taskList 任务列表
     * @return 业务对象
     */
    private List<TaxApplicationVo> convertTaskVoList(List<Task> taskList) {
        List<TaxApplicationVo> result = new ArrayList<>();
        for (Task task : taskList) {
            ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            TaxApplicationVo bean = (TaxApplicationVo) runtimeService.getVariable(pi.getId(), "bean");
            if (bean != null) {
                bean.setSerialNumber(task.getId());
                bean.setCurrentLink(task.getTaskDefinitionKey());
                // 处理启动流程审批记录
                List<AuditLogVo> auditLogVoList = new ArrayList<>();
                HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
                AuditLogVo auditLogVo = getStartAuditLogVo(historicProcessInstance.getStartUserId(), historicProcessInstance.getStartTime());
                auditLogVoList.add(auditLogVo);
                List<AuditLogVo> auditLogVoListByBean = bean.getAuditLogVoList();
                if (auditLogVoListByBean != null && auditLogVoListByBean.size() > 0) {
                    auditLogVoList.addAll(bean.getAuditLogVoList());
                }
                bean.setAuditLogVoList(auditLogVoList);
                result.add(bean);
            }

        }
        return result;
    }

    /**
     * 保存税金申请业务数据
     *
     * @param taxApplicationVo
     * @return
     * @throws Exception
     */
    private void saveTaxFlowPrcess(final TaxApplicationVo taxApplicationVo) throws Exception {
        if (taxApplicationVo == null) {
            throw new Exception("税金申请信息新增失败");
        }
        TTaxApplication taxApplication = new TTaxApplication();
        BeanUtils.copyProperties(taxApplicationVo, taxApplication);
        int insertSelective = taxApplicationMapper.insertSelective(taxApplication);
        if (insertSelective <= 0) {
            throw new Exception("税金申请主信息新增失败");
        }
        List<TaxApplicationDetailVo> taxApplicationDetailVoList = taxApplicationVo.getDetails();
        List<TaxApplicationDetailVo> detailNewList = taxApplicationVo.getDetails();
        for (TaxApplicationDetailVo bean : taxApplicationDetailVoList) {
            bean.setId(IDGeneratorUtils.getUUID32());
            bean.setTaxApplicationId(taxApplication.getId());
            // 保存详细信息
            TTaxApplicationDetail taxApplicationDetail = new TTaxApplicationDetail();
            BeanUtils.copyProperties(bean, taxApplicationDetail);
            int insert = taxApplicationDetailMapper.insertSelective(taxApplicationDetail);
            if (insert <= 0) {
                detailNewList.add(bean);
                throw new Exception("税金申请子信息批量新增失败");
            }
        }
        taxApplicationVo.setDetails(detailNewList);
    }

    /**
     * 更新税金申请单
     * @param taxApplicationVo
     * @return
     * @throws Exception
     */
    private boolean updateTaxFlowProcess(TaxApplicationVo taxApplicationVo) throws Exception {
        if (taxApplicationVo == null) {
            throw new Exception("税金申请信息参数为空");
        }
        TTaxApplication taxApplication = new TTaxApplication();
        BeanUtils.copyProperties(taxApplicationVo, taxApplication);
        if (taxApplication != null && !StringUtils.isEmpty(taxApplication.getId())) {
            int i = taxApplicationMapper.updateByPrimaryKeySelective(taxApplication);
            if (i <= 0) {
                throw new Exception("税金申请信息更新失败");
            }
        }
        List<TaxApplicationDetailVo> taxApplicationDetailVoList = taxApplicationVo.getDetails();
        for (TaxApplicationDetailVo bean : taxApplicationDetailVoList) {
            if (!StringUtils.isEmpty(bean.getId())) {
                TTaxApplicationDetail taxApplicationDetail = new TTaxApplicationDetail();
                BeanUtils.copyProperties(bean, taxApplicationDetail);
                int i = taxApplicationDetailMapper.updateByPrimaryKey(taxApplicationDetail);
                if (i <= 0) {
                    throw new Exception("税金申请子信息更新失败");
                }
            }
        }
        return true;
    }

    /**
     * 创建流程图到指定目录
     * @param deploymentId
     * @param filePath
     * @param fileName
     * @return
     */
    private boolean createImage(String deploymentId, String filePath, String fileName) {
        boolean isCreateImage = true;
        try {
            InputStream in = repositoryService.getResourceAsStream(deploymentId, filePath);
            File file = new File(String.format("%s/%s", uploadPath, fileName));
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) > 0) {
                fos.write(b, 0, length);
            }
            in.close();
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            isCreateImage = false;
        } finally {
            return isCreateImage;
        }
    }

    /**
     * 获取已办详细信息
     * @param processInstanceId
     * @return
     */
    private DoneVo getDoneVo(String processInstanceId) {

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        Date startTime = historicProcessInstance.getStartTime();

        TaxApplicationVo taxApplicationVo = new TaxApplicationVo();

        // 当前处理人
        String currentHandler = "";
        // 当前节点
        String currentLink = taxApplicationVo.getCurrentLink();
        if (StringUtils.isEmpty(currentLink)) {
            currentLink = "over";
        }

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (pi != null) {
            taxApplicationVo = (TaxApplicationVo) runtimeService.getVariable(pi.getId(), "bean");
            Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
            taxApplicationVo.setCurrentLink(task.getTaskDefinitionKey());
            currentLink = task.getTaskDefinitionKey();
            if (!StringUtils.isEmpty(taxApplicationVo.getCurrentHandler())) {
                UserVo user = userService.getUserById(taxApplicationVo.getCurrentHandler());
                currentHandler = user.getUsername();
            }
        } else {
            List<HistoricVariableInstance> historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
            if (historicVariableInstanceList != null && historicVariableInstanceList.size() > 0) {
                for (HistoricVariableInstance hvi : historicVariableInstanceList) {
                    if ("bean".equals(hvi.getVariableName())) {
                        Object value = hvi.getValue();
                        BeanUtils.copyProperties(value, taxApplicationVo);
                    }
                }
            }
        }

        // 判断异常数据
        List<TaxApplicationDetailVo> taxApplicationVoDetails = taxApplicationVo.getDetails();
        boolean anyMatch = false;
        if (taxApplicationVoDetails != null && taxApplicationVoDetails.size() > 0) {
            anyMatch = taxApplicationVoDetails.stream().anyMatch(bean -> !StringUtils.equals(bean.getPayableTax().toString(), bean.getTaxPaid().toString()) || !StringUtils.equals(bean.getLateFeePayable().toString(), bean.getOverduePayment().toString()));
        }
        if (anyMatch && StringUtils.isEmpty(taxApplicationVo.getCurrentHandler())) {
            currentLink = "overAbnormalData";
        }


        // 流水号
        String serialNumber = taxApplicationVo.getSerialNumber();
        // 公司名称
        String companyId = taxApplicationVo.getCompanyId();
        String companyName = taxApplicationVo.getCompanyName();
        // 审批意见
        List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);

        List<AuditLogVo> auditLogVoList = new ArrayList<>();

        // 处理正常审批记录
        commentList.stream().forEach(bean -> {

            HistoricTaskInstance historicTaskInstance = historyService.createHistoricTaskInstanceQuery().taskId(bean.getTaskId()).singleResult();

            AuditLogVo auditLogVo = new AuditLogVo();
            auditLogVo.setTaskName(historicTaskInstance.getName());

            UserVo userVo = userService.getUserInfoByUserIdAndKey(bean.getUserId(), historicTaskInstance.getTaskDefinitionKey());
            if (userVo != null) {
                auditLogVo.setName(userVo.getRealName());
                List<RoleVo> roles = userVo.getRoles();
                if (roles != null) {
                    RoleVo roleVo = roles.get(0);
                    auditLogVo.setRoleName(roleVo.getName());
                }
            }

            String fullMessage = bean.getFullMessage();
            if (!StringUtils.isEmpty(fullMessage)) {
                String[] split = fullMessage.split(",");
                if (split != null && split.length > 1) {
                    auditLogVo.setAuditResult(split[0]);
                    if (split.length > 1) {
                        auditLogVo.setAdvice(split[1]);
                    }
                }
            }
            auditLogVo.setAuditDate(bean.getTime());

            auditLogVoList.add(auditLogVo);
        });

        // 处理启动流程审批记录
        AuditLogVo startAuditLogVo = getStartAuditLogVo(historicProcessInstance.getStartUserId(), historicProcessInstance.getStartTime());
        auditLogVoList.add(startAuditLogVo);

        DoneVo doneVo = new DoneVo();

        // 已办数据
        doneVo.setProcInstId(processInstanceId);
        doneVo.setCurrentHandler(currentHandler);
        doneVo.setCurrentLink(currentLink);
        doneVo.setFlowNum(processInstanceId);
        doneVo.setCompanyId(companyId);
        doneVo.setCompanyName(companyName);
        doneVo.setCreateTime(startTime);
        doneVo.setSaveTime(startTime);
        doneVo.setSerialNumber(serialNumber);

        // 已办详情
        doneVo.setTaxApplicationVo(taxApplicationVo);

        // 已办评论
        doneVo.setAuditLogVoList(auditLogVoList);

        return doneVo;

    }

    /**
     * 初始化服务，为了在监听器中使用
     */
    private void initService() {
        myRuntimeService = runtimeService;
        myTaskService = taskService;
        myTaxApplicationMapper = taxApplicationMapper;
        myTaxApplicationDetailMapper = taxApplicationDetailMapper;
    }

}
