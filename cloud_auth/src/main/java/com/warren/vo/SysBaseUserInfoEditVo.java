package com.warren.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author kcwl
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysBaseUserInfoEditVo extends SysBaseUserInfoNewVo {
    @NotNull
    @ApiModelProperty(value = "主键ID")
    private Long id;

}
