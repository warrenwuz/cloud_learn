package com.warren.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author kcwl
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysBaseUserInfoVo extends SysBaseUserInfoEditVo {

    @ApiModelProperty(value = "用户类型，0：管理员，1：企业用户，2：预警大屏用户")
    private Integer userType;

    @ApiModelProperty(value = "预警平台登录超时时长")
    private Integer loginOutTime;

    @ApiModelProperty(value = "用户使用标识，1-启用 2-停用")
    private Integer useFlag;

    @ApiModelProperty(value = "删除标识，0：未删除，1：已删除")
    private Integer isDel;

    @ApiModelProperty(value = "是否超级系统管理员")
    private Boolean isAdmin;

    @ApiModelProperty(value = "用户登录密码")
    private String userPassword;

    private Boolean isTester;

    @ApiModelProperty(value = "用户唯一标识")
    private String userIdentity;

    @ApiModelProperty(value = "平台名称")
    private String plateName;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "是否首次登录")
    private Integer isFirstLogin;

    @ApiModelProperty(value = "角色id")
    private List<Long> roleIds;

    @ApiModelProperty(value = "角色名称")
    private List<String> roleNames;

    @ApiModelProperty(value = "角色编号")
    private List<String> roleNos;

}
