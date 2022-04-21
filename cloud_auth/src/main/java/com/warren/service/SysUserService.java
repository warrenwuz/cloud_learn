package com.warren.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warren.entity.SysUser;
import com.warren.mapper.SysUserMapper;
import com.warren.util.PasswordUtil;
import com.warren.vo.SysBaseUserInfoNewVo;
import com.warren.vo.SysBaseUserInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author warren
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {



    public static final String Salt = "63293188";//密钥
    /**
     * 定义迭代次数为1000次iteration
     */
    private static final int ITERATION_COUNT = 1000;
    public void addSysUser(SysBaseUserInfoNewVo sysBaseUserInfoNewVo){
        //String newPasword = PasswordUtil.encrypt(userIdentity, defaultPassword, PasswordUtil.getStaticSalt());

    }


    /**
     * 根据登陆用户名获取用户信息
     *
     * @param loginName 登陆用户名
     * @return 快慧通基础用户表对象
     */
    public SysBaseUserInfoVo getUserInfoByLoginName(String loginName) {
        return BeanUtil.copyProperties(getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getLoginName,loginName)), SysBaseUserInfoVo.class);
    }
    /**
     * 根据手机号获取用户信息
     *
     * @param userMobile 登陆用户名
     * @return 快慧通基础用户表对象
     */
    public SysBaseUserInfoVo getUserInfoByUserMobile(String userMobile) {
        return BeanUtil.copyProperties(getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getLoginName,userMobile)), SysBaseUserInfoVo.class);
    }
}
