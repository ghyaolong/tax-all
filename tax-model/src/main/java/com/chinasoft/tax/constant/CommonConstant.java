package com.chinasoft.tax.constant;

public interface CommonConstant {

    /**
     * 用户默认头像
     */
    String USER_DEFAULT_AVATAR = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    /**
     * 用户正常状态
     */
    Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    Integer USER_STATUS_LOCK = -1;

    /**
     * 普通用户
     */
    Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    Integer USER_TYPE_ADMIN = 1;

    /**
     * 性别男
     */
    Integer SEX_MAN = 0;

    /**
     * 性别女
     */
    Integer SEX_WOMAN = 1;

    /**
     * 性别保密
     */
    Integer SEX_SECRET = 2;

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG = 1;

    /**
     * 限流标识
     */
    String LIMIT_ALL = "XBOOT_LIMIT_ALL";

    /**
     * 页面类型权限
     */
    Integer PERMISSION_PAGE = 0;

    /**
     * 操作类型权限
     */
    Integer PERMISSION_OPERATION = 1;

    /**
     * 1级菜单
     */
    String PARENT_ID = "0";

    /**
     * 1级菜单
     */
    Integer LEVEL_ONE = 1;

    /**
     * 2级菜单
     */
    Integer LEVEL_TWO = 2;

    /**
     * 3级菜单
     */
    Integer LEVEL_THREE = 3;

    /**
     * 调用方法成功
     */
    Integer EXECUTE_SUCCESS = 0;

    /**
     * 调用方法失败,有异常
     */
    Integer EXECUTE_FAILD = 1;

    /**
     * 公司已分配
     */
    Integer COMPANY_ASSGINED = 1;

    /**
     * 公司未分配
     */
    Integer COMPANY_UNASSGINED = 0;

    /**
     * 税金申请的时候的操作类型：待提
     */
    Integer EXECUTE_TYPE_SAVE = 0;

    /**
     * 税金申请的时候的操作类型:代办
     */
    Integer EXECUTE_TYPE_COMMIT = 1;

    /**
     * 税金申请的时候的操作类型:已办
     */
    Integer EXECUTE_TYPE_DONE = 2;

    /***
     * 异常提交
     */
    Integer EXCEPTION_TYPE_COMMIT = 3;

    /**
     * 分配公司
     */
    Integer COMPANY_ASSIGNED =1;

    /**
     * 取消公司分配
     */
    Integer COMPANY_UNASSIGNED = 0;

    /**
     * 未上传
     */
    Integer FILE_UNUPLOAD = 0;
    /**
     * 已上传
     */
    Integer FILE_UPLOADED = 1;

    Integer PRE_FILE_UPLOAD= 0;

    Integer PRE_FILE_UPLOADED = 1;


    Integer UPLOAD_TAX = 0;

    Integer UPLOAD_TAX_DETAIL = 1;


    /**税务申请保存*/
    Integer TAX_SAVE = 1;

    /**税务专员*/
    Integer TAX_COMMITER = 2;

    /**税务遵从主管*/
    Integer TAX_COMPLIANCE_OFFICER = 3;

    /**税务总监*/
    Integer TAX_COMMISSIONER = 4;

    /**资金总监*/
    Integer TAX_DIRECTOR_OF_FUNDS = 5;

    /**财务总监*/
    Integer TAX_CHIEF_FINANCIAL_OFFICER = 6;

    /**补充完成*/
    Integer TAX_DONE = 7;

    /**驳回*/
    Integer TAX_REJECT = 8;

    String FILE_SIZE="fileSize";
    String FILE_TYPE="fileType";



    String USER_TAXATION="taxationIds";
    String USER_REVIEWS="reviewerIds";
    String USER_VIEWS ="viewerIds";

    String ADMIN_CODE = "ROLE_ADMINISTRATOR";

}
