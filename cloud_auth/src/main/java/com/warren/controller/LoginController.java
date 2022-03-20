package com.warren.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.warren.entity.SysUser;
import com.warren.service.SysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author warren
 */
@RestController
@RequestMapping("/sys")
public class LoginController {
    @Resource
    private SysUserService sysUserService;

    @PostMapping("register")
    public String register(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return "注册成功";
    }

    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("testAuth")
    @SaCheckRole("warren")
    public String testAuth() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
