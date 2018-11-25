package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceCategory record);

    int insertSelective(DeviceCategory record);

    DeviceCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DeviceCategory record);

    int updateByPrimaryKey(DeviceCategory record);

    int deleteByDeviceId(String deviceId);

    int deleteByDeviceIds(List<String> deviceIds);

    int deleteByCategoryIds(List<String> categoryIds);

    /**
     * 更改分类id
     * @param categoryId  要修改为的分类id
     * @param categoryIds 需要更改的分类id
     * @return
     */
    int updateCategoryIdInCategoryIds(@Param("categoryId") String categoryId, @Param("categoryIds") List<String> categoryIds);
}