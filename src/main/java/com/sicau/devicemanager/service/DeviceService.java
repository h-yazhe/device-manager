package com.sicau.devicemanager.service;

import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.VO.DeviceSearchSelectionVO;

import java.util.List;

/**
 * 设备服务
 * @author Yazhe
 * Created at 17:30 2018/8/7
 */
public interface DeviceService {

	/**
	 * 添加设备
	 * @param deviceDTO 设备信息
	 */
	void addDevice(DeviceDTO deviceDTO);

	/**
	 * 通过设备id更新设备信息
	 * @param deviceDTO 设备信息
	 */
	void updateDeviceById(DeviceDTO deviceDTO);

	/**
	 * 根据设备id删除设备
	 * @param ids id列表
	 */
	void deleteDeviceById(List<String> ids);

	/**
	 * 根据条件查询设备信息
	 * @param deviceDTO
	 * @return
	 */
	PageInfo<DeviceDTO> listDevice(DeviceDTO deviceDTO);

	/**
	 * 分发设备
	 * @param distributeDeviceDTO
	 */
	void distributeDevice(DistributeDeviceDTO distributeDeviceDTO);

	/**
	 * 废弃设备
	 * @param deviceId
	 */
	void discardDevice(String deviceId);

	/**
	 * 获取搜索的选项卡数据
	 * @param pageSize 每页数量
	 * @return
	 */
	DeviceSearchSelectionVO getSearchSelections(int pageSize);
}
