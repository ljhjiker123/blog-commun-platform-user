package com.jiker.bcp.user.dto;

import com.jiker.bcp.user.validator.ValidatorGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author jiker.luo
 * @date 2020/4/3
 */
@ApiModel
public class UpdateUserDTO {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户名不能为空", groups = {ValidatorGroup.Update.class})
    private Long id;

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空", groups = {ValidatorGroup.Update.class})
    private String name;

    @ApiModelProperty(value = "联系电话")
    @NotNull(message = "联系电话不能为空", groups = {ValidatorGroup.Update.class})
    private Long contactPhone;

    @ApiModelProperty(value = "电子邮箱")
    @NotNull(message = "电子邮箱不能为空", groups = {ValidatorGroup.Update.class})
    private String mail;

    @ApiModelProperty(value = "学号/工号")
    @NotNull(message = "学号/工号不能为空", groups = {ValidatorGroup.Update.class})
    private Long workNumber;

    @ApiModelProperty(value = "用户头像")
    @NotNull(message = "用户头像不能为空", groups = {ValidatorGroup.Update.class})
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
