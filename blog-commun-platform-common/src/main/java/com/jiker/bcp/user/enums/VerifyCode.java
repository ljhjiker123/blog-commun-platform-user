package com.jiker.bcp.user.enums;

/**
 * @author jiker.luo
 * @date 2020/4/3
 */
public enum VerifyCode {

    /**
     * 审核状态统一标识
     */
    REJECT(-1, "审核驳回"),
    WAIT_PASS(1, "待审核"),
    PASSED(2, "审核通过");

    private Integer code;

    private String message;

    VerifyCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
