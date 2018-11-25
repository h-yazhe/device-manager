package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Category;

import java.util.List;

/**
 * @author Yazhe
 * Created at 14:59 2018/8/7
 */
public interface CategoryMapper {

    /**
     * 插入地点集合
     * @param locationList 地点集合
     * @return
     */
    int insertList(List<Category> locationList);

    /**
     * 根据id删除节点
     * @return
     */
    int deleteById(String id);

    /**
     * 查询所有的地点
     * @return
     */
    List<Category> selectAll();

    /**
     * 查询该设备对应的地点
     * @param deviceId 设备id
     * @return
     */
    Category getByDeviceId(String deviceId);

    /**
     * 根据id查询其所有子节点
     * @param id locationId
     * @return
     */
    List<Category> getChildrenById(String id);

    /**
     * 根据id查询其所有子节点id
     * @param id locationId
     * @return
     */
    List<String> getChildrenIdById(String id);

    Category getById(String id);

    List<Category> getCategoryInIds(List<String> ids);

    /**
     * 查询所有后代
     * @param rootId
     * @return
     */
    List<Category> getDescendants(String rootId);

    int deleteByIds(List<String> ids);
}
