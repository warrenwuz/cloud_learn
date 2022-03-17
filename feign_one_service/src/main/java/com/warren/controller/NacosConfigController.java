package com.warren.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warren
 */
@RestController
@RequestMapping("/data")
@RefreshScope
public class NacosConfigController {
    @Value("${server.port}")
    private Integer port;

    @RequestMapping("port")
    public Integer port() {
        return port;
    }
}
