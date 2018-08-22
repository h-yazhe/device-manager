package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;

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
	 * @param deviceDTO
	 * @return
	 */
	List<DeviceDTO> getDeviceInfo(DeviceDTO deviceDTO);

	int deleteByIds(List<String> ids);

	/**
	 * 分发设备，修改设备的地点、使用时间、设备状态
	 * @param distributeDeviceDTO
	 * @return
	 */
	int distributeDevice(DistributeDeviceDTO distributeDeviceDTO);
}