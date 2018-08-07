package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Status;

public interface StatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);
}