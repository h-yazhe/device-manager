package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.Permission;

import java.util.List;

/**
 * 权限服务
 * @author BeFondOfTaro
 * Created at 13:28 2018/5/18
 */
public interface PermissionService {

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> listPermission();
}
