package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.RoleCategory;

import java.util.List;

/**
 * Created By Chq ${Date}
 */
public interface RoleCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleCategory record);

    int insertSelective(RoleCategory record);

    RoleCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleCategory record);

    int updateByPrimaryKey(RoleCategory record);

    RoleCategory selectByRoleId(String roleId);

    List<RoleCategory> selectByRoleIds(List<String> ids);
}
