package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceStatusRecord;

public interface DeviceStatusRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceStatusRecord record);

    int insertSelective(DeviceStatusRecord record);

    DeviceStatusRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceStatusRecord record);

    int updateByPrimaryKey(DeviceStatusRecord record);
}