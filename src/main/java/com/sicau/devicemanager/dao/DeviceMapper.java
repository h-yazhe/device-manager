package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(String id);

	/**
	 * 根据设备id批量查询设备表信息
	 * @param ids
	 * @return
	 */
	List<Device> getByIds(List<String> ids);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

	/**
	 * 查询设备信息，用于展示
	 * @param deviceDTO
	 * @return
	 */
	List<DeviceDTO> getDeviceInfo(DeviceDTO deviceDTO);

	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	int deleteByIds(List<String> ids);

	/**
	 * 分发设备，修改设备的地点、使用时间、设备状态
	 * @param distributeDeviceDTO
	 * @return
	 */
	int distributeDevice(DistributeDeviceDTO distributeDeviceDTO);

	/**
	 * 报废设备
	 * @param deviceId
	 * @return
	 */
	int discardDevice(String deviceId);

	/**
	 * 根据地点id删除设备
	 * @param locationId
	 * @return
	 */
	int deleteByLocationIds(List<String> locationId);

	/**
	 * 根据设备id修改设备状态
	 * @param deviceId 要修改的设备
	 * @param statusId 修改的状态
	 * @return
	 */
	int updateStatusIdById(@Param("deviceId") String deviceId, @Param("statusId") Integer statusId);
}