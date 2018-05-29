package com.sicau.devicemanager.service.impl;

import com.sicau.devicemanager.POJO.DO.Permission;
import com.sicau.devicemanager.dao.PermissionMapper;
import com.sicau.devicemanager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 13:30 2018/5/18
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listPermission() {
        return permissionMapper.listPermission();
    }
}
