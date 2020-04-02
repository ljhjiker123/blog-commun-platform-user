package com.jiker.bcp.user.dto;

import com.jiker.bcp.user.util.EncryptionByMD5;
import com.jiker.bcp.user.validator.ValidatorGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author jiker.luo
 * @date 2020/4/2
 */
@ApiModel
public class LoginUserDTO {

    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空",groups = {ValidatorGroup.Insert.class})
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空",groups = {ValidatorGroup.Insert.class})
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为空",groups = {ValidatorGroup.Insert.class})
    private Long verifiCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return EncryptionByMD5.getMD5(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(Long verifiCode) {
        this.verifiCode = verifiCode;
    }
}
