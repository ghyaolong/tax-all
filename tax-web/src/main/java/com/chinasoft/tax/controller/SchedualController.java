package com.chinasoft.tax.controller;

import com.chinasoft.tax.common.utils.ResponseUtil;
import com.chinasoft.tax.constant.CommonConstant;
import com.chinasoft.tax.enums.ExceptionCode;
import com.chinasoft.tax.service.SchedualService;
import com.chinasoft.tax.vo.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.quartz.*;

import java.util.List;


/**
 * @Description:
 * @Author: yaochenglong
 * @Date: 12:26 2018/11/11
 */

@Slf4j
@RestController
@RequestMapping("/schedual")
public class SchedualController {

    @Autowired
    private SchedualService schedualService;

    @Qualifier("Scheduler")
    @Autowired
    private Scheduler scheduler;

    /**
     * 获取所有定时任务
     * @param pageVo
     * @return
     */
    @PostMapping(value = "/getAllByPage")
    public Message getAll(@RequestBody PageVo pageVo){

        MyPageInfo<QuartzJobVo> all = schedualService.findAll(pageVo);
        return ResponseUtil.responseBody(all);
    }

    /**
     * 添加定时任务
     * @param quartzJobVo
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Message addJob(@RequestBody QuartzJobVo quartzJobVo){

        List<QuartzJobVo> list = schedualService.findByJobClassName(quartzJobVo.getJobClassName());
        if(list!=null&&list.size()>0){
            return ResponseUtil.responseBody(ExceptionCode.DATA_AREADY_EXIST.getCode(),"该定时任务类名已存在");
        }
        add(quartzJobVo.getJobClassName(),quartzJobVo.getCronExpression(),quartzJobVo.getParameter());
        schedualService.add(quartzJobVo);
        return ResponseUtil.responseBody("创建定时任务成功");
    }

    /**
     * 更新定时任务
     * @param quartzJobVo
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public Message editJob(@RequestBody QuartzJobVo  quartzJobVo){

        delete(quartzJobVo.getJobClassName());
        add(quartzJobVo.getJobClassName(),quartzJobVo.getCronExpression(),quartzJobVo.getParameter());
        quartzJobVo.setStatus(CommonConstant.STATUS_NORMAL);
        schedualService.edit(quartzJobVo);
        return ResponseUtil.responseBody("更新定时任务成功");
    }

    /**
     * 暂停定时任务
     * @param quartzJobVo
     * @return
     */
    @RequestMapping(value = "/pause",method = RequestMethod.POST)
    public Message pauseJob(@RequestBody QuartzJobVo quartzJobVo){

        try {
            scheduler.pauseJob(JobKey.jobKey(quartzJobVo.getJobClassName()));
        } catch (SchedulerException e) {
            throw new BizException(ExceptionCode.SCHEUDAL_ERROR.getCode(),"暂停定时任务失败");
        }
        quartzJobVo.setStatus(CommonConstant.STATUS_DISABLE);
        schedualService.edit(quartzJobVo);
        return ResponseUtil.responseBody("暂停定时任务成功");
    }

    /**
     * 恢复定时任务
     * @param quartzJobVo
     * @return
     */
    @RequestMapping(value = "/resume",method = RequestMethod.POST)
    public Message resumeJob(@RequestBody QuartzJobVo quartzJobVo){

        try {
            scheduler.resumeJob(JobKey.jobKey(quartzJobVo.getJobClassName()));
        } catch (SchedulerException e) {
            throw new BizException(ExceptionCode.SCHEUDAL_ERROR.getCode(),"恢复定时任务失败");
        }
        quartzJobVo.setStatus(CommonConstant.STATUS_NORMAL);
        schedualService.edit(quartzJobVo);
        return ResponseUtil.responseBody("恢复定时任务成功");
    }

    /**
     * 删除定时任务
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    public Message deleteJob(@PathVariable String ids){

        for(String id:ids.split(",")){
            schedualService.delByIds(id);
        }
        return ResponseUtil.responseBody("删除定时任务成功");
    }

    /**
     * 添加定时任务
     * @param jobClassName
     * @param cronExpression
     * @param parameter
     */
    public void add(String jobClassName, String cronExpression, String parameter){

        try {
            // 启动调度器
            scheduler.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
                    .withIdentity(jobClassName)
                    .usingJobData("parameter",parameter)
                    .build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error(e.toString());
            throw new BizException(ExceptionCode.SCHEUDAL_ERROR.getCode(),"创建定时任务失败");
        } catch (Exception e){
            throw new BizException(ExceptionCode.SCHEUDAL_ERROR.getCode(),"后台找不到该类名任务");
        }
    }

    /**
     * 删除定时任务
     * @param jobClassName
     */
    public void delete(String jobClassName){

        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName));
        } catch (Exception e) {
            throw new BizException(ExceptionCode.SCHEUDAL_ERROR.getCode(),"删除定时任务失败");
        }
    }

    public static Job getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (Job)class1.newInstance();
    }

}

