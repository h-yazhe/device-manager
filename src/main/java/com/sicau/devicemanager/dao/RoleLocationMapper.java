package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.RoleLocation;

import java.util.List;

public interface RoleLocationMapper {

    int deleteByPrimaryKey(String id);

    int insert(RoleLocation record);

    int insertSelective(RoleLocation record);

    RoleLocation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleLocation record);

    int updateByPrimaryKey(RoleLocation record);

    RoleLocation selectByRoleId(String roleId);

    /**
     * 根据RoleId的集合，查找改Role所管理层次最高的地点
     * @param ids
     * @return
     */
    List<RoleLocation> selectByRoleIds(List<String> ids);
}