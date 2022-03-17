# nacos config 配置说明

```yaml
# Nacos帮助文档: https://nacos.io/zh-cn/docs/concepts.html
# Nacos认证信息
spring:
  application:
    name: feign_one_service
  cloud:
    nacos:
      discovery: # nacos  注册地址
        server-addr: http://127.0.0.1:8848
      config:  # nacos config配置地址
        server-addr: ${spring.cloud.nacos.discovery.server-addr}
        context-path: /nacos
        file-extension: yaml  # 文件配置必须bootstrap.yml 否则不生效
      password: nacos
      username: nacos
  profiles:
    active: dev
```

## `dataID`格式

```
${prefix}-${spring.profiles.active}.${file-extension}
```

- `prefix` 默认为 `spring.application.name` 的值，也可以通过配置项 `spring.cloud.nacos.config.prefix`来配置。
- `spring.profiles.active` 即为当前环境对应的 profile  **注意：当 `spring.profiles.active` 为空时，对应的连接符 `-` 也将不存在，dataId 的拼接格式变成 `${prefix}.${file-extension}`**
- `file-exetension` 为配置内容的数据格式，可以通过配置项 `spring.cloud.nacos.config.file-extension` 来配置。目前只支持 `properties` 和 `yaml` 类型。文件配置必须bootstrap.yml 否则不生效
- 配置生效顺序 `${prefix}` ====>` ${prefix}.${file-extension} ` ====>` ${prefix}.${file-extension} .${file-extension}`

# Feign

- **服务名不允许有下划线**
