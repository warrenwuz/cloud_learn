package com.warren.controller;

import com.warren.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@Api(tags = "图形验证码")
@RequestMapping("/sys")
@Slf4j
public class CaptchaController {
    @Resource
    private CaptchaService captchaService;

    @GetMapping(value = "getCaptcha",produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    @Validated
    @ApiOperation( value = "获取验证码",httpMethod="GET")
    public byte[] getCaptcha(@NotBlank(message = "请求参数不不允许为空") @RequestParam("captchaId") String captchaId) {
        return  captchaService.createCaptcha(captchaId);
    }



}
