package com.warren.entity;

import lombok.Data;

/**
 * @author warren
 */
@Data
public class SysUser implements BaseEntity {
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户类型，0管理员1美锦企业用户2货主企业3运输司机4仓库管理员
     */
    private Integer userType;
    /**
     * 用户手机号
     */
    private String userMobile;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户登录密码
     */
    private String userPassword;
    /**
     * 删除标识，0：未删除，1：已删除
     */
    private Integer isDel;
    /**
     * 预警平台登录超时时长,单位分:超过该时长未操作，预警平台将自动退出登录
     */
    private Integer loginOutTime;
    /**
     * 用户使用标识，1-启用 2-停用
     */
    private Integer useFlag;
    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 登录用户名
     */
    private String loginName;

    /**
     * 用户唯一标识
     */
    private String userIdentity;

    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 机构ID
     */
    private Long enterpriseId;

    /**
     * 是否首次登录 0否1是
     */
    private Integer isFirstLogin;

    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private java.util.Date updateTime;
}
