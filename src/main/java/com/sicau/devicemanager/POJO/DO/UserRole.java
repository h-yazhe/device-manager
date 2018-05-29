package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

import java.util.Date;

/**
 * 用户角色关联表
 * @author BeFondOfTaro
 * Created at 14:36 2018/5/14
 */
@Data
public class UserRole {

    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 创建时间
     */
    private Date createTime;
}
