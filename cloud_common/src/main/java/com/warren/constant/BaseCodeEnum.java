package com.warren.constant;

import lombok.Getter;

@Getter
public enum BaseCodeEnum implements IResultCode{
    /** 消息 */
    SUCCESS(00000, "成功");
    private int code;
    private String msg;

    BaseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
