package com.jiker.bcp.user.enums;

/**
 * @author jiker.luo
 * @date 2020/4/2
 */
public enum ResponseCode {

    /**
     * 系统统一返回码标识
     */
    SUCCESS("SYS.SUCCESS","操作成功"),
    PRAM_ERROR("SYS.PRAM_EXCEPTION","参数错误"),
    SERVER("SYS.SERVER_EXCEPTION","网络异常");

    private String code;
    private String message;

    ResponseCode(String code,String message){
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
