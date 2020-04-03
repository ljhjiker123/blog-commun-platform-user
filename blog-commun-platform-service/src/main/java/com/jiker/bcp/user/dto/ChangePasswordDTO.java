package com.jiker.bcp.user.dto;

import com.jiker.bcp.user.util.EncryptionByMD5;
import com.jiker.bcp.user.validator.ValidatorGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author jiker.luo
 * @date 2020/4/3
 */
@ApiModel
public class ChangePasswordDTO {

    @ApiModelProperty(value = "用户id")
    @NotNull(message = "用户id不能为空", groups = {ValidatorGroup.Update.class})
    private Long id;

    @ApiModelProperty(value = "用户密码")
    @NotNull(message = "用户密码不能为空", groups = {ValidatorGroup.Update.class})
    private String password;

    @ApiModelProperty(value = "新密码")
    @NotNull(message = "新密码不能为空", groups = {ValidatorGroup.Update.class})
    private String newPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return EncryptionByMD5.getMD5(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return EncryptionByMD5.getMD5(this.newPassword);
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
