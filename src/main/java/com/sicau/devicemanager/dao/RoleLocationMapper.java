package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.RoleLocation;

public interface RoleLocationMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleLocation record);

    int insertSelective(RoleLocation record);

    RoleLocation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleLocation record);

    int updateByPrimaryKey(RoleLocation record);

    RoleLocation selectByRoleId(String roleId);
}