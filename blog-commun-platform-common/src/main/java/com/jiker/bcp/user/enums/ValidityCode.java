package com.jiker.bcp.user.enums;

/**
 * @author jiker.luo
 * @date 2020/4/3
 */
public enum ValidityCode {

    /**
     * 审核状态统一标识
     */
    PROHIBIT(0, "已禁用"),
    NORMAL(1, "正常");

    private Integer code;

    private String message;

    ValidityCode(int code, String message) {
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
