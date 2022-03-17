package com.warren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author warren
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignTwoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignTwoServiceApplication.class,args);
    }
}
