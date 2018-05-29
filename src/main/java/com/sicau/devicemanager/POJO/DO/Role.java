package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

import java.util.Date;

/**
 * 角色
 * @author BeFondOfTaro
 * Created at 23:10 2018/5/13
 */
@Data
public class Role {

    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
