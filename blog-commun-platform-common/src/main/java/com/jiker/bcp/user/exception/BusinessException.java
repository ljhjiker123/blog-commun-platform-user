package com.jiker.bcp.user.exception;

import com.jiker.bcp.user.enums.BusinessCode;

/**
 * @author jiker.luo
 * @date 2020/4/2
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2416956018589745766L;

    private String code;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message,Throwable arg){
        super(message,arg);
    }

    public BusinessException(BusinessCode businessCode){
        super(businessCode.getMessage());
        this.code = businessCode.getCode();
    }

    public String getCode(){
        return code;
    }

}
