package com.warren.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author warren
 * @since  2017-11-14
 */
@Getter
@Setter
public class ApiResult<T> {
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MESSAGE = "成功！";
    @NonNull
    private Integer code;
    @NonNull
    private String message;

    private T data;

    public ApiResult() {
    }

    protected ApiResult(T data) {
        this.data = data;
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    protected ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> successMsg(String msg) {
        return new ApiResult<>(SUCCESS_CODE, msg);
    }

    public static <T> ApiResult<T> successMsg(CharSequence template, Object... params) {
        return new ApiResult<>(SUCCESS_CODE, StrUtil.format(template, params));
    }

    public static <T> ApiResult<T> failMsg(String msg) {
        return new ApiResult<>(9999, msg);
    }

    public static <T> ApiResult<T> failMsg(CharSequence template, Object... params) {
        return new ApiResult<>(9999, StrUtil.format(template, params));
    }


    public static <T> ApiResult<T> reply(int code, String message, T data) {
        return new ApiResult<>(code, message, data);
    }

    public static <T> ApiResult<T> fail(Integer code, String message) {
        return new ApiResult<>(code, message);
    }

}

