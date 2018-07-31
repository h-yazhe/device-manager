package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.LocationDTO;

import java.util.List;

/**
 * 地点
 * @author Yazhe
 * Created at 10:39 2018/7/31
 */
public interface LocationService {

	/**
	 * 插入一个地点树
	 * @param locationList 节点列表
	 */
	void insertLocationTree(List<Location> locationList);

	/**
	 * 删除该节点为根的树
	 * @param rootId 根节点id
	 */
	void deleteLocationTree(String rootId);

	/**
	 * 更新地点树
	 * @param rootId 根节点
	 * @param locationList 节点列表
	 */
	void updateLocationTree(String rootId,List<Location> locationList);

	/**
	 * 查询所有地点
	 * @return
	 */
	List<LocationDTO> listLocationTree();
}
