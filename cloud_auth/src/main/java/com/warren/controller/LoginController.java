package com.warren.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.warren.entity.ApiResult;
import com.warren.service.CaptchaService;
import com.warren.service.LoginService;
import com.warren.service.SysUserService;
import com.warren.vo.SysUserLoginInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author warren
 */
@RestController
@RequestMapping("/sys")
public class LoginController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private LoginService sysUserLoginService;
    @Resource
    private CaptchaService captchaService;

    @GetMapping(value = "getLoginSecretKey")
    @ApiOperation(value = "获取登录秘钥")
    public ApiResult<String> getLoginSecretKey() {
        return ApiResult.success(sysUserLoginService.getLoginSecretKey());
    }

    @RequestMapping("doLogin")
    public ApiResult<String> doLogin(@RequestBody @Validated SysUserLoginInfoVo sysUserLoginInfoVo) {
        sysUserLoginService.login(sysUserLoginInfoVo);
        return ApiResult.successMsg("登陆失败");
    }

}
