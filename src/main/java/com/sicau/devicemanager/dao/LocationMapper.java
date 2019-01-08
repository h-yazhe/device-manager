package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Location;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Yazhe
 * Created at 9:40 2018/7/31
 */
public interface LocationMapper {

    /**
     * 插入地点集合
     * @param locationList 地点集合
     * @return
     */
    int insertList(List<Location> locationList);

    /**
     * 根据id删除节点
     * @return
     */
    int deleteById(String id);

    int deleteByIds(List<String> ids);

    /**
     * 查询所有的地点
     * @return
     */
    List<Location> selectAll();

    /**
     * 查询该设备对应的地点
     * @param deviceId 设备id
     * @return
     */
    Location getByDeviceId(String deviceId);

    /**
     * 根据id查询其所有子节点
     * @param id locationId
     * @return
     */
    List<Location> getChildrenById(String id);

    /**
     * 根据id查询其所有子节点id
     * @param id locationId
     * @return
     */
    List<String> getChildrenIdById(String id);

    Location getById(String id);

    /**
     * 查询在id列表里的所有地点信息，list为空则查询所有
     * @param locationIds
     * @return
     */
    List<Location> getLocationsInIds(List<String> locationIds);

    List<Location> getByUserId(String userId);

    /**
     * 查询所有后代
     * @param rootId
     * @return
     */
    List<Location> getDescendants(String rootId);

    /**
     * 根据用户id查询该用户管理的地点
     * @param userId 用户id
     * @return
     */
    List<Location> getOnesLocationByUserId(String userId);

    /**
     * 查看在id列表中所有的子地点，返回自身和子地点
     * @param ids 地点的id
     * @return
     */
    List<Location> getAllChildIdByIds(List<String> ids);

	/**
	 * 根据名称获取id
	 * @param name
	 * @return
	 */
	String getIdByName(@Param("name") String name);
}