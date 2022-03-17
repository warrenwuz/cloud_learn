package com.warren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author warren
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignOneServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignOneServiceApplication.class,args);
    }
}
