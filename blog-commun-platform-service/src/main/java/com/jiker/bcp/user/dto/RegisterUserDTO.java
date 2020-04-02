package com.jiker.bcp.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.jiker.bcp.user.util.EncryptionByMD5;
import com.jiker.bcp.user.validator.ValidatorGroup;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author jiker.luo
 * @date 2020/4/1
 */
@ApiModel
public class RegisterUserDTO {

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空",groups = {ValidatorGroup.Insert.class})
    private String password;

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空",groups = {ValidatorGroup.Insert.class})
    private String name;

    @ApiModelProperty(value = "联系电话")
    @NotNull(message = "联系电话不能为空",groups = {ValidatorGroup.Insert.class})
    private Long contactPhone;

    @ApiModelProperty(value = "电子邮箱")
    @NotNull(message = "电子邮箱不能为空",groups = {ValidatorGroup.Insert.class})
    private String mail;

    @ApiModelProperty(value = "学号/工号")
    @NotNull(message = "学号/工号不能为空",groups = {ValidatorGroup.Insert.class})
    private Long workNumber;

    @ApiModelProperty(value = "是否为管理员 0 否；1 是")
    @NotNull(message = "是否为管理员不能为空",groups = {ValidatorGroup.Insert.class})
    private Integer roleAdmin;

    public String getPassword() {
        return EncryptionByMD5.getMD5(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getRoleAdmin() {
        return roleAdmin;
    }

    public void setRoleAdmin(Integer roleAdmin) {
        this.roleAdmin = roleAdmin;
    }
}
