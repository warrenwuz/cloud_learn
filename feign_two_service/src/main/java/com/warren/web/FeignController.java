package com.warren.web;

import com.warren.service.FeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author warren
 */
@RestController
@RequestMapping("two")
public class FeignController {
    @Resource
    private FeignService feignService;
    @RequestMapping("port")
    public Integer port() {
        return feignService.port();
    }
}
