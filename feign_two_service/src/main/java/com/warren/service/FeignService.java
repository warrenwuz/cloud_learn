package com.warren.service;

/**
 * @author warren
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/data")
@FeignClient(value = "feign-one-service")
public interface FeignService {
    @RequestMapping("port")
     Integer port();
}
