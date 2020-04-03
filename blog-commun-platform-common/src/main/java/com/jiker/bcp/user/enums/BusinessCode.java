package com.jiker.bcp.user.enums;

/**
 * @author jiker.luo
 * @date 2020/4/2
 */
public enum BusinessCode {

    /**
     * 业务异常统一标识
     */
    USER_REGISTER_ERROR("601", "注册用户失败"),
    USER_EXISTED("602", "该用户已存在"),
    ACCOUNT_PASSWORD_ERROR("603", "账号密码错误"),
    VERIFICATION_ERROR("604", "验证码错误或失效"),
    USER_DOESNT_EXIST_ERROR("605", "用户不存在"),
    NO_PERMISSION_ERROR("606", "无操作权限"),
    UPDATE_USER_FAILURE("607", "更新用户失败"),
    STORE_VERIFI_CODE_ERROR("608", "储存验证码失败");

    private String code;

    private String message;

    BusinessCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
