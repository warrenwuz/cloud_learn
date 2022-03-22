package com.warren.exception;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.warren.entity.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author warren
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {
    // 全局异常拦截（拦截项目中的所有异常）
    @ResponseBody
    @ExceptionHandler
    public ApiResult<String> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        // 不同异常返回不同状态码
        if (e instanceof NotLoginException) {    // 如果是未登录异常
            NotLoginException ee = (NotLoginException) e;
            return ApiResult.failMsg(ee.getMessage());
        } else if (e instanceof NotRoleException) {        // 如果是角色异常
            NotRoleException ee = (NotRoleException) e;
            return ApiResult.failMsg("无角色:" + ee.getRole());
        } else if (e instanceof NotPermissionException) {    // 如果是权限异常
            NotPermissionException ee = (NotPermissionException) e;
            return ApiResult.failMsg("无此权限:" + ee.getCode());
        } else if (e instanceof DisableLoginException) {    // 如果是被封禁异常
            DisableLoginException ee = (DisableLoginException) e;
            return ApiResult.failMsg("账号被封禁：" + ee.getDisableTime() + "秒后解封");
        } else if (e instanceof MethodArgumentNotValidException) {    // 如果是被封禁异常
           // MethodArgumentNotValidException ee = (MethodArgumentNotValidException) e;
            return ApiResult.failMsg("请求参数异常");
        }
        else {    // 普通异常, 输出：500 + 异常信息
            log.error("请求接口未知异常",e);
            return ApiResult.failMsg(e.getMessage());
        }
    }
}
