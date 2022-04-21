package com.warren.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.warren.ApplicationProperties;
import com.warren.constant.CacheKeyConstants;
import com.warren.entity.KhtUserSessionData;
import com.warren.vo.SysBaseUserInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * @author warren
 */
@Service
public class SessionService {
    private static final String PREFIX_SEPARATOR = ":";

    private static final int KEY_SIZE = 32;
    private static final int UPDATE_EXPIRED_INTERNAL = 30000;
    @Resource
    private RedisService redisService;
    @Resource
    ApplicationProperties applicationProperties;
    @Resource
    Cache<String, Long> sessionVisitTimeCache;


    public KhtUserSessionData startSession(SysBaseUserInfoVo sysBaseUserVo) {

        KhtUserSessionData session = createSession(sysBaseUserVo);

        saveSession(session);

        SessionDataHolder.setSessionData(session);

        updateExpiredTime(session);

        session.setExpired(System.currentTimeMillis() + (session.getExpired()*1000));

        return session;
    }
    private void saveSession(KhtUserSessionData session) {
        String key = getSessionCacheKey(session.getSsid());
        redisService.set(key, session, applicationProperties.getSession().getTimeout());
        //保存ssid与用户的关系，冻结或者删除可以从缓存中移除登录信息
        redisService.set(CacheKeyConstants.LOGIN_UIDSSID_KEY + session.getUserIdentity(), key, applicationProperties.getSession().getTimeout());
    }

    private void updateExpiredTime(KhtUserSessionData session) {
        Long lastVisitTime = sessionVisitTimeCache.getIfPresent(session.getSsid());
        Long curVisitTime = System.currentTimeMillis();

        if ((lastVisitTime == null) || (curVisitTime - lastVisitTime) > UPDATE_EXPIRED_INTERNAL) {
            setExpiredTime(session);
        }
        sessionVisitTimeCache.put(session.getSsid(), curVisitTime);
    }


    private KhtUserSessionData createSession(SysBaseUserInfoVo sysBaseUserVo) {
        KhtUserSessionData session = new KhtUserSessionData();
        //设置公司ID
        session.setEnterpriseId(sysBaseUserVo.getEnterpriseId());
        //设置用户ID
        session.setUserId(sysBaseUserVo.getId());
        //设置用户类型
        session.setUserType(sysBaseUserVo.getUserType());
        //设置用户姓名
        session.setUserName(sysBaseUserVo.getUserName());
        //设置登陆姓名
        session.setLoginName(sysBaseUserVo.getLoginName());
        //设置用户标志
        session.setUserIdentity(sysBaseUserVo.getUserIdentity());
        //设置用户是否启用
        session.setUseFlag(sysBaseUserVo.getUseFlag());
        //设置用户手机号
        session.setUserMobile(sysBaseUserVo.getUserMobile());
        //预警平台登录超时时长
        session.setLoginOutTime(sysBaseUserVo.getLoginOutTime());
        //是否管理员
        session.setIsAdmin(sysBaseUserVo.getIsAdmin());
        //是否测试
        session.setIsTester(sysBaseUserVo.getIsTester());
        //生成ssid
        session.setSsid(createSessionId(session.getUserMobile()));
        //设置角色ID
        session.setRoleId(sysBaseUserVo.getRoleId());
        //设置过期时间
        session.setExpired((long) applicationProperties.getSession().getTimeout());
        //设置部门ID
        session.setDepartmentId(sysBaseUserVo.getDepartmentId());
        return session;
    }

    public String createSessionId(String userMobile) {
        return DigestUtils.md5DigestAsHex((userMobile + System.currentTimeMillis()).getBytes());
    }

    private void setExpiredTime(KhtUserSessionData session) {

        redisService.expire(getSessionCacheKey(session.getSsid()), applicationProperties.getSession().getTimeout());

        session.getAttributes().forEach(name -> {
            redisService.expire(getAttributeCacheKey(session.getSsid(), name), applicationProperties.getSession().getTimeout());
        });
    }

    private String getSessionCacheKey(String sessionId) {
        return CacheKeyConstants.LOGIN_SSID_KEY + StrUtil.DOT + sessionId;
    }

    private String getAttributeCacheKey(String sessionId, String name) {
        return CacheKeyConstants.LOGIN_SSID_KEY + StrUtil.DOT + sessionId + StrUtil.DOT + name;
    }

}
