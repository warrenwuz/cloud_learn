package com.warren.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author warren
 */
@Getter
@Setter
public class SysUserLoginInfoVo {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码id")
    private String captchaId;
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @NotBlank(message = "密钥不能为空")
    @ApiModelProperty(value = "秘钥")
    private String key;

    @NotNull(message = "登陆类型不能为空")
    @ApiModelProperty(value = "登陆类型  1-用户名 2-手机号")
    private Integer loginType;

    /**
     * 是否为测试
     */
    private boolean isTest;
}
