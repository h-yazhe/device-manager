package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceCategory;

import java.util.List;

public interface DeviceCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceCategory record);

    int insertSelective(DeviceCategory record);

    DeviceCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceCategory record);

    int updateByPrimaryKey(DeviceCategory record);

    int deleteByDeviceId(String deviceId);

	int deleteByDeviceIds(List<String> deviceIds);
}