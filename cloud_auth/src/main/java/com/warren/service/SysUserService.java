package com.warren.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warren.entity.SysUser;
import com.warren.mapper.SysUserMapper;
import com.warren.vo.SysBaseUserInfoVo;
import com.warren.vo.SysUserLoginInfoVo;
import org.springframework.stereotype.Service;

/**
 * @author warren
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
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
