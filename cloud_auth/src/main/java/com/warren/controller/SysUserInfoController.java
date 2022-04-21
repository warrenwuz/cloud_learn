package com.warren.controller;


import cn.hutool.crypto.CipherMode;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.warren.entity.ApiResult;
import com.warren.service.SysUserService;
import com.warren.util.PasswordUtil;
import com.warren.vo.SysBaseUserInfoNewVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author kcwl
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "账号管理")
public class SysUserInfoController {
    @Resource
    private SysUserService sysUserService;




    @PostMapping(value = "addSysUser")
    @ApiOperation(value = "账号管理-添加用户")
    public ApiResult<String> addSysUser(@RequestBody @Validated SysBaseUserInfoNewVo sysBaseUserInfoNewVo) {



        return ApiResult.success("成功");
    }








}
