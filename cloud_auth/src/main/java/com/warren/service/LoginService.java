package com.warren.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.warren.constant.AuthExceptionEnum;
import com.warren.entity.ApiResult;
import com.warren.util.Asserts;
import com.warren.util.PasswordUtil;
import com.warren.vo.SysBaseUserInfoVo;
import com.warren.vo.SysUserLoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.warren.constant.CacheKeyConstants.LOGIN_SECRET_KEY;

/**
 * @Author: ckwl
 * @Date: 2019/4/17 16:04
 */
@Slf4j
@Service
public class LoginService {
    private static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    //给用户名和密码加密的秘钥过期时间
    private static final int SECRET_KEY_TIMEOUT = 60 * 5;
    //秘钥长度
    private static final int SECRET_KEY_SIZE = 32;
    @Resource
    private RedisService redisService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private CaptchaService captchaService;


    /**
     * 获取登录密钥
     */
    public String getLoginSecretKey() {
        String key = RandomUtil.randomString(SOURCES, SECRET_KEY_SIZE);
        String secretKey = RandomUtil.randomString(SOURCES, SECRET_KEY_SIZE);
        redisService.set(LOGIN_SECRET_KEY + key, secretKey, SECRET_KEY_TIMEOUT);
        return key + secretKey;
    }


    public ApiResult<SysBaseUserInfoVo> login(SysUserLoginInfoVo sysUserLoginInfoVo) {
        loginInfoDecrypt(sysUserLoginInfoVo);
        String userName = sysUserLoginInfoVo.getUserName();
        String password = sysUserLoginInfoVo.getPassword();
        if (captchaService.checkCaptcha(sysUserLoginInfoVo.getCaptchaId(), sysUserLoginInfoVo.getCaptcha())) {
            return ApiResult.fail(AuthExceptionEnum.CAPTCHA_ERROR.getCode(), AuthExceptionEnum.CAPTCHA_ERROR.getMsg());
        }
        captchaService.clearCatpcha(sysUserLoginInfoVo.getCaptchaId());
        SysBaseUserInfoVo sysBaseUserInfoVo = sysUserLoginInfoVo.getLoginType() == 1 ?
                sysUserService.getUserInfoByLoginName(userName) :
                sysUserService.getUserInfoByUserMobile(userName);

        if (sysBaseUserInfoVo == null) {
            return ApiResult.fail(AuthExceptionEnum.USER_NAME_PASSWORD_ERROR.getCode(), AuthExceptionEnum.USER_NAME_PASSWORD_ERROR.getMsg());
        }
        String cipherText = PasswordUtil.encrypt(sysBaseUserInfoVo.getUserIdentity(), password, PasswordUtil.getStaticSalt());
        if (cipherText.compareTo(sysBaseUserInfoVo.getUserPassword()) != 0) {
            return ApiResult.fail(AuthExceptionEnum.USER_NAME_PASSWORD_ERROR.getCode(), AuthExceptionEnum.USER_NAME_PASSWORD_ERROR.getMsg());
        }
        return ApiResult.success(sysBaseUserInfoVo);
    }

    /**
     * 对用户提交的用户名和密码解密
     */
    private void loginInfoDecrypt(SysUserLoginInfoVo sysUserLoginInfoVo) {
        Object obj = redisService.get(LOGIN_SECRET_KEY + sysUserLoginInfoVo.getKey());
        //验证密钥是否超时
        Asserts.notNull(obj, AuthExceptionEnum.SUBMIT_TIMEOUT);
        //获取加密密钥
        String secretKey = (String) obj;
        //获取偏移量
        String iv = secretKey.substring(0, 16);
        //初始化AES
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, secretKey.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
        //BASE64转码 防止编码格式改变报错
        Base64.Decoder decoder = Base64.getDecoder();
        //获取用户名编码
        byte[] decodeUserName = decoder.decode(sysUserLoginInfoVo.getUserName());
        //解密后用户名
        String userName = aes.decryptStr(decodeUserName);
        //获取密码编码
        byte[] decodePassword = decoder.decode(sysUserLoginInfoVo.getPassword());
        //解密后的密码
        String password = aes.decryptStr(decodePassword);
        //设置用户名
        sysUserLoginInfoVo.setUserName(userName);
        //设置密码
        sysUserLoginInfoVo.setPassword(password);
    }


}
