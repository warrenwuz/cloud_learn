package com.warren.constant;

import lombok.Getter;

@Getter
public enum RedisKeyEnum {

    LOGIN_SECRET_KEY("KCKHT:LOGIN:SECRET_KEY:", "登录密码加密的秘钥"),
    LOGIN_SSID_KEY("KCKHT:LOGIN:SSID:", "登录时用到的ssid"),
    LOGIN_UIDSSID_KEY("KCKHT:LOGIN:UIDSSID:", "登录时用到的UIDSSID"),

    APPLETS_LOGIN_SECRET_KEY("KCKHT:APPLETS:LOGIN:SECRET_KEY:", "小程序登录密码加密的秘钥"),
    APPLETS_LOGIN_SSID_KEY("KCKHT:APPLETS:LOGIN:SSID:", "小程序登录时用到的ssid"),
    APPLETS_LOGIN_UIDSSID_KEY("KCKHT:APPLETS:LOGIN:UIDSSID:", "小程序登录时用到的UIDSSID"),
    APPLETS_MSG_CAPTCHA_KEY("KCKHT:APPLETS:MSG:CAPTCHA:", "小程序短信验证码"),

    EXPORT_KHT_SETTLE_KEY("EXPORT_KHT_SETTLE_KEY:", "导出结算单信息"),
    EXPORT_KHT_CAPITAL_KEY("EXPORT_KHT_CAPITAL_KEY:", "导出业务资金流水信息"),
    EXPORT_KHT_LOG_KEY("EXPORT_KHT_LOG_KEY:", "导出日志信息"),
    TEST_KHT_KEY("TEST_KHT_KEY","测试缓存"),
    EXPORT_KHT_LEDGER("EXPORT_KHT_LEDGER:", "导出台账信息"),
    ;


    private String code;
    private String value;

    RedisKeyEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

}
