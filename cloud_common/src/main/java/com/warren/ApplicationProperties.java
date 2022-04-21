package com.warren;

import com.warren.constant.KhtEnvEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ckwl
 */
@Data
@Component
@ConfigurationProperties("kckht")
public class ApplicationProperties {


    private SysUerAuth auth = new SysUerAuth();

    private CacheProperties cache = new CacheProperties();

    private LoginSession session = new LoginSession();

    private ReplayProtect replay = new ReplayProtect();

    private String env = KhtEnvEnum.FAT.getCode();

    @Data
    public static class SysUerAuth {
        private boolean enable;
        private boolean encrypt = true;
        private boolean captcha= true;
        private boolean refreshToken=false;
        private String patterns = "/**";
        private List<String> excludePatterns = new ArrayList<>();
        private List<String> denyAdminPatterns = new ArrayList<>();
    }

    @Data
    public static class CacheProperties {
        private int expiredTime = 60 * 30;
        private int maxSize = 10000;

    }

    @Data
    public static class LoginSession {
        private int timeout = 30 * 60;
    }


    @Data
    public static class ReplayProtect {
        private boolean enable = true;
        private long protectInterval = 30000;
        private int expiredTime = 60;
    }
}
