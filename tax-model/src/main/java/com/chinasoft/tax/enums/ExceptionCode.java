package com.chinasoft.tax.enums;

/**
 * @Author: yaochenglong
 * @Description: 异常类
 */
public enum ExceptionCode {
    /************************** 业务异常在平板展示给用户 **************************/
    REQUEST_SUCCESS("A-00000", "成功!"),
    SERVICE_EXCEPTION_ERROR("A-00001", "服务异常"),
    USER_LOGIN_TIMEOUT("A-00002", "登录超时"),
    LOGIN_FAILURE("A-000013","登录已失效，请重新登录"),
    TOKEN_ERROR("A-000014","token错误，请重新登录"),

    VAILDATE_MOBILE_ERROR("A-00003", "手机号码错误"),
    USER_VERIFACATION_CODE_DUPLICATION_ERROR("A-00004", "最后一次发送的验证码还有效，请勿频繁获取"),
    SMS_SEND_ERROR("A-00005", "短信验证码发送失败"),
    DELETE_USER_CONTACT_FAIL("A-00006","删除联系人失败"),
    RECHARGEAMOUNT_ERROR("A-00007", "请输入[1,9999]之间的充值金额"),
    CONTACT_OUT_HOSPITAL("A-00008", "联系人已出院，无法充值"),
    CONTACT_NOT_EXIST("A-00009", "联系人不存在"),
    RECHARGE_SUCCESS("A-00010", "充值成功"),
    RECHARGE_FATLURE("A-00011", "充值失败"),
    NULL_DATA("A-000012", "无数据"),
    DATA_CHANGED("A-000014", "数据已更改"),
    VAILDATE_VERIFICATIONCODE_ERROR("A-00013", "验证码不正确"),
    CHECK_IDENTITY("A-00015", "身份证号码不正确"),
    CHECK_CHINESE_NAME("A-00016", "姓名不正确"),
    CHECK_MOBILE("A-00017", "手机号码有误"),
    CHECK_PICTURE("A-00020","图片格式错误"),
    USER_REGISTER_FATLURE("A-00021","注册失败"),
    CONFIRM_PASSWORD_NOT_MATCH("A-00022","两次密码不一致"),
    PASSORD_ERROR("A-00023","密码不正确"),

    IDENTITYNUM_IS_EXIST("A-00024","姓名设置错误"),
    CHECK_FEEDBACK_CONTENT("A-00025","反馈内容长度超出限制"),
    CHECK_FEEDBACK_IMAGES("A-00026","反馈图片张数超出限制"),
    CHECK_USERNAME("A-00027","姓名设置错误"),
    USER_NAME_IS_NOT_NULL("A-00028","用户名不能为空"),
    PASSWORD_IS_NOT_NULL("A-00029","密码不能为空"),
    DEL_ROLE_HAS_MENU("A-00030","角色下有未删除的菜单，无法删除"),
    DEL_ROLE_HAS_COMPANY("A-00031","角色下有未删除的公司，无法删除"),
    ROLE_HAS_PERMISSION("A-00033","删除失败，包含正被角色使用关联的菜单或权限"),
    DEL_ROLE_HAS_PERMISSION("A-00034","角色下有未删除的菜单，无法删除"),
    PARSE_TOKEN_ERROR("A-000035","解析token失败"),
    HAS_NO_PERMISSION("A-000036","抱歉，您没有访问权限"),
    ROLE_NOT_EXIST("A-000037","角色不存在"),
    OLD_PASSWORD_INCORRECT("A-000038","原密码不正确"),
    DEPARTMENT_HAS_USER("A-000039","该部门下有关联用户，无法删除"),
    PARAM_IS_NOT_NULL("A-000040","参数不能为空"),
    FILE_IS_NOT_NULL("A-000041","上传的文件不能位空"),
    FILE_UPLOAD_ERROR("A-000042","上传文件失败"),
    SCHEUDAL_ERROR("A-000043","操作定时任务失败"),
    DEL_TAX_ERROR("A-000044","任务不存在或不是待提任务"),
    CANNOT_DELETE("A-000045","不能删除系统用户或角色"),



    /************************** 系统异常，不给用户展示，但是需要返回给前端 **************************/
    REQUEST_PARAM_ERROR("B-00001", "参数错误"),
    SQL_EXECUTE_FAILURE("B-00002", "SQL执行失败"),
    REQUEST_PARAM_MISSING("B-00003","缺少参数"),
    SYSTEM_EXCEPTION("B-00005","系统异常"),
    WXPAY_ERROR("B-00007", "调用微信接口异常"),
    ALIPAY_CONFIG_MISSING("B-00008", "未查找到该院的支付宝支付配置"),
    WX_CONFIG_MISSING("B-00009", "未查找到该院的微信支付配置"),
    ALIPAY_ERROR("B-00010", "调用支付宝接口异常"),
    CONNECT_JIGUANG_SERVER_FAIL("B-00011", "极光连接推送服务器失败"),
    SEND_JIGUANG_MESSAGE_FAIL("B-00012", "极光推送消息请求失败"),
    USERINFO_SAVE_REDIS_FAIL("B-00015", "登录信息存入缓存失败"),
    UNKNOWN_ERROR("B-00016", "未知错误!"),
    USER_INFO_IS_NOT_EXIST("B-00018","用户信息不存在"),
    LOGIN_INFO_IS_NOT_EXIST("B-00019","用户登录信息不存在"),
    REPEAT_DATA_EXCEPTION("B-00020","存在重复数据"),
    USER_NAME_AREADY_EXIST("B-00021","用户名已存在"),
    WORK_NUMBER_AREADY_EXIST("B-00022","工号已存在"),
    DATA_AREADY_EXIST("B-00023","数据已存在"),
    ROLE_AREADY_EXIST("B-00024","角色名或者角色编码已存在"),
    CONTACT_ID_EXCEPTION("B-00028","联系人ID为空"),
    ORDER_NUM_IS_NOT_EXIST("B-00029","订单号不存在"),
    SMS_EXCEPTION("B-00040","发送短信失败"),
    DATA_NOT_EXIST("B-00041","数据不存在"),
    ;
    private final String code;
    private final String msg;

    private ExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    ExceptionCode(String code){
        this.code = code;
        this.msg = ExceptionCode.getMsgByCode(code);
    }

    public String getCode() {
        return code;
    }

    public static String getMsgByCode(String code) {
        for (ExceptionCode exceptionCode : ExceptionCode.values()) {
            if (code.equals(exceptionCode.getCode())) {
                return exceptionCode.getMsg();
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ExceptionCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}