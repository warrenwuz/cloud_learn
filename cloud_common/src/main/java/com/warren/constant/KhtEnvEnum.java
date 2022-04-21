package com.warren.constant;

import lombok.Getter;

/**
 * @author pc
 * @date 2020/11/18  11:34
 */
@Getter
public enum KhtEnvEnum {
    DEV("DEV", "开发"),
    FAT("FAT", "测试"),
    PRO("PRO", "生产");


    private String code;
    private String value;

    KhtEnvEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
