package com.warren.service;

import cn.hutool.core.util.StrUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.warren.constant.CacheKeyConstants.PREFIX_CATPCHA;

/**
 * @author warren
 */
@Service
@Slf4j
public class CaptchaService {

    //验证码长度
    private static final int CAPTCHA_LEN = 4;

    @Resource
    private RedisService redisService;

    @Resource
    private DefaultKaptcha captchaProducer;

    public byte[] createCaptcha(String captchaId) {
        String strCaptchaValue = createCaptchaValue();
        saveCaptchaValue(captchaId, strCaptchaValue);
        BufferedImage image = captchaProducer.createImage(strCaptchaValue);
        return image2bytes(image);
    }

    private byte[] image2bytes(BufferedImage image) {
        byte[] imageData = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", out);
            imageData = out.toByteArray();
        } catch (IOException e) {
            log.error("生成验证码错误", e);
        }
        return imageData;
    }

    public boolean checkCaptcha(String captchaId, String captchaTextInput) {

        String strCaptchaText = (String) redisService.get(getRedisKey(captchaId));

        return !StrUtil.isEmpty(strCaptchaText) && strCaptchaText.compareTo(captchaTextInput) == 0;
    }

    public void clearCatpcha(String captchaId) {
        redisService.del(getRedisKey(captchaId));
    }

    private void saveCaptchaValue(String captchaId, String strCaptchaValue) {
        redisService.set(getRedisKey(captchaId), strCaptchaValue, 60);
    }

    private String createCaptchaValue() {

        Random random = new Random();

        if (CaptchaService.CAPTCHA_LEN == 1) {
            return String.valueOf(random.nextInt(10));
        }

        int a = random.nextInt(9) + 1;
        int m = 1;

        for (int i = 0; i < CaptchaService.CAPTCHA_LEN - 1; i++) {
            m = m * 10;
        }
        int b = random.nextInt(m);

        return String.valueOf(a * m + b);
    }

    private String getRedisKey(String catId) {
        return PREFIX_CATPCHA + catId;
    }
}

