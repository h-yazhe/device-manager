package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.LocationDTO;
import com.sicau.devicemanager.POJO.VO.LocationVO;

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
     * 根据父id插入一个地点
     * @param location
     */
    void insertLocationByPId(Location location);

    /**
     * 删除该节点为根的树
     * @param rootId 根节点id
     */
    void deleteLocationTree(String rootId);

    /**
     * 更新地点树
     * @param rootId       根节点
     * @param locationList 节点列表
     */
    void updateLocationTree(String rootId, List<Location> locationList);

    /**
     * 查询所有地点
     * @return
     */
    List<LocationDTO> listLocationTree();

    List<Location> listLocationByPId(LocationVO locationVO);

	/**
	 * 获取用户管理的区域
	 * @param userId
	 * @return
	 */
	List<Location> getUserManagedLocations(String userId);

	/**
	 * 校验地点是否是用户管理的地点
	 * @param locationId 要校验的地点
	 * @param userId 用户id
	 * @return 返回用户管理的所有地点
	 * @throws com.sicau.devicemanager.config.exception.BusinessException 校验不通过抛出此异常
	 */
	List<Location> checkLocationInUserManagement(String locationId, String userId);
}
