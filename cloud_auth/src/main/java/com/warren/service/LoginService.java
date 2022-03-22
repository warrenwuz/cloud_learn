package com.warren.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.warren.constant.AuthExceptionEnum;
import com.warren.util.Asserts;
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


    /**
     * 获取登录密钥
     */
    public String getLoginSecretKey() {
        String key = RandomUtil.randomString(SOURCES, SECRET_KEY_SIZE);
        String secretKey = RandomUtil.randomString(SOURCES, SECRET_KEY_SIZE);
        redisService.set(LOGIN_SECRET_KEY + key, secretKey, SECRET_KEY_TIMEOUT);
        return key + secretKey;
    }


    public void login(SysUserLoginInfoVo sysUserLoginInfoVo) {
        loginInfoDecrypt(sysUserLoginInfoVo);
        String userName = sysUserLoginInfoVo.getUserName();
        String password = sysUserLoginInfoVo.getPassword();
        SysBaseUserInfoVo sysBaseUserInfoVo = sysUserLoginInfoVo.getLoginType() == 1 ?
                sysUserService.getUserInfoByLoginName(userName) :
                sysUserService.getUserInfoByUserMobile(userName);


    }

    /**
     * 对用户提交的用户名和密码解密
     */
    private void loginInfoDecrypt(SysUserLoginInfoVo sysUserLoginInfoVo) {
        Object obj = redisService.get(LOGIN_SECRET_KEY + sysUserLoginInfoVo.getKey());
        //验证密钥是否超时
        Asserts.notNull(obj, AuthExceptionEnum.SUBMIT_TIMEOUT);
        String secretKey = (String) obj;
        String iv = secretKey.substring(0, 16);
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, secretKey.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeUserName = decoder.decode(sysUserLoginInfoVo.getUserName());
        String userName = aes.decryptStr(decodeUserName);
        byte[] decodePassword = decoder.decode(sysUserLoginInfoVo.getPassword());
        String password = aes.decryptStr(decodePassword);
        sysUserLoginInfoVo.setUserName(userName);
        sysUserLoginInfoVo.setPassword(password);
    }


}
