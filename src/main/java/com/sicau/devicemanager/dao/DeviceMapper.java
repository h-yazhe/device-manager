package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

	/**
	 * 查询设备信息，用于展示
	 * @param id 设备id
	 * @return
	 */
	DeviceDTO getDeviceInfoById(String id);

	/**
	 * 查询设备信息，用于展示
	 * @return
	 */
	List<DeviceDTO> getDeviceInfo();

	int deleteByIds(List<String> ids);
}