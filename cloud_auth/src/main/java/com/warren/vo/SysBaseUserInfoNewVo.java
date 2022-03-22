package com.warren.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author kcwl
 */

@Data
public class SysBaseUserInfoNewVo {

    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "用户手机号不能为空")
    private String userMobile;

    @ApiModelProperty(value = "用户姓名")
    @NotBlank(message = "用户姓名不能为空")
    @Size(max = 8, message = "用户姓名不能超过8个字符！")
    private String userName;

    @ApiModelProperty(value = "所属部门")
    @NotNull(message = "所属部门为空")
    private Long departmentId;

    @ApiModelProperty(value = "所属机构")
    @NotNull(message = "所属机构不能为空！")
    private Long enterpriseId;

    @ApiModelProperty(value = "角色")
    private Long roleId;

    @ApiModelProperty(value = "登录用户名")
    private String loginName;

    public interface AddGroup {
    }
}
