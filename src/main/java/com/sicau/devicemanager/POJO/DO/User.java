package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

import java.util.Date;

/**
 * @author BeFondOfTaro
 * Created at 19:30 2018/5/13
 */
@Data
public class User {

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

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

    /**
     * 最后一次登录时间
     */
    private Date lastTime;
}
