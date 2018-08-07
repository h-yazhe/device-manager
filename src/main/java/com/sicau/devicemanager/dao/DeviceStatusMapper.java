package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceStatus;

public interface DeviceStatusMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceStatus record);

    int insertSelective(DeviceStatus record);

    DeviceStatus selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceStatus record);

    int updateByPrimaryKey(DeviceStatus record);
}