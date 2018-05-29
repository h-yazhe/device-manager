package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

import java.util.Date;

/**
 * 角色权限关联表
 * @author BeFondOfTaro
 * Created at 23:33 2018/5/14
 */
@Data
public class RolePermission {

    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 创建时间
     */
    private Date createTime;
}
