package com.warren.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.warren.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author warren
 */
@Configuration
public class CacheConfig {
    private static final int CACHE_INITIAL_CAPACITY = 100;
    private static final int CACHE_MAX_CAPACITY = 10000;
    @Resource
    ApplicationProperties applicationProperties;

    @Bean(name = "sessionVisitTimeCache")
    public Cache<String, Long> getSessionVisitTimeCache() {
        return Caffeine.newBuilder()
                //初始化缓冲大小
                .initialCapacity(CACHE_INITIAL_CAPACITY)
                //最大存储空间
                .maximumSize(CACHE_MAX_CAPACITY)
                //过期时间
                .expireAfterWrite(applicationProperties.getSession().getTimeout(), TimeUnit.SECONDS)
                .build();
    }
}
