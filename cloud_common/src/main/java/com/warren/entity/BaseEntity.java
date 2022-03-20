package com.warren.entity;

import cn.hutool.core.util.StrUtil;

import java.util.Date;

/**
 * @author warren
 */
public interface BaseEntity extends PoEntity {
    /**
     * 获取创建id
     */
    default Long getCreateId(){return null;}
    /**
     * 获取创建人姓名
     */
    default String getCreateName(){return StrUtil.EMPTY;}

    /**
     * 创建时间
     */
    default Date getCreateTime(){return null;}
    /**
     * 更新人id
     */
    default Long getUpdateId(){return null;}
    /**
     * 更新人名称
     */
    default String getUpdateName(){return StrUtil.EMPTY;}

    /**
     * 更新时间
     */
    default Date getUpdateTime(){return null;}

    /**
     * 是否删除
     */
    default Integer getIsDel() {return null;}
    /**
     * 备注
     */
    default String getRemarks(){return null;}
}
