package com.jiker.bcp.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author jiker.luo
 * @date 2020/4/3
 */
@ApiModel
public class QueryUserDetailVO {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "联系电话")
    private Long contactPhone;

    @ApiModelProperty(value = "电子邮箱")
    private String mail;

    @ApiModelProperty(value = "学号/工号")
    private Long workNumber;

    @ApiModelProperty(value = "用户头像")
    private String picture_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(Long contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Long workNumber) {
        this.workNumber = workNumber;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }
}
