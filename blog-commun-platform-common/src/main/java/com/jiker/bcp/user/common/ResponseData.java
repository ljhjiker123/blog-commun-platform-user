package com.jiker.bcp.user.common;

import com.jiker.bcp.user.enums.ResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class ResponseData implements Serializable {

    private static final long serialVersionUID = -1090327339697103946L;

    @ApiModelProperty(value = "data")
    private Object data;

    @ApiModelProperty(value = "code")
    private String code;

    @ApiModelProperty(value = "subcode")
    private String subcode;

    @ApiModelProperty(value = "message")
    private String message;

    public ResponseData(){
        super();
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public ResponseData(String code,String message){
        this.code = code;
        this.message = message;
    }

    public ResponseData(Object data){
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
