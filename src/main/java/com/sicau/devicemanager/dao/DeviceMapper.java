package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.VO.DeviceVO;

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
	DeviceVO getDeviceInfoById(String id);

	/**
	 * 查询设备信息，用于展示
	 * @return
	 */
	List<DeviceVO> getDeviceInfo();

	int deleteByIds(List<String> ids);
}