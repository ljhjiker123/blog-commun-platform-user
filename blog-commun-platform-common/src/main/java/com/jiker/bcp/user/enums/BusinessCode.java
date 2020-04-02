package com.jiker.bcp.user.enums;

/**
 * @author jiker.luo
 * @date 2020/4/2
 */
public enum BusinessCode {

    /**
     * 业务异常统一标识
     */
    USER_REGISTER_ERROR("601","注册用户失败"),
    USER_EXISTED("602","该用户已存在"),
    ACCOUNT_PASSWORD_ERROR("603","账号密码错误"),
    VERIFICATION_ERROR("604","验证码错误或失效");

    private String code;

    private String message;

    BusinessCode(String code,String message){
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
