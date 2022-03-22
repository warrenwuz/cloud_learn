package com.warren.exception;

import com.warren.constant.IResultCode;
import lombok.Getter;

/**
 * 自定义API异常
 * Created by macro on 2020/2/27.
 */
@Getter
public class ApiException extends RuntimeException {
    private IResultCode errorCode;
    private Integer code;

    public ApiException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
        this.code=errorCode.getCode();
    }

    public ApiException(Integer code,String message) {
        super(message);
        this.code=code;
    }

}
