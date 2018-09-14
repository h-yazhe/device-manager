package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceStatusRecord;

import java.util.List;

public interface DeviceStatusRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceStatusRecord record);

	/**
	 * 批量插入
	 * @param recordList
	 * @return
	 */
	int insertBatch(List<DeviceStatusRecord> recordList);

    int insertSelective(DeviceStatusRecord record);

    DeviceStatusRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceStatusRecord record);

    int updateByPrimaryKey(DeviceStatusRecord record);

    int deleteByDeviceIds(List<String> deviceIds);
}