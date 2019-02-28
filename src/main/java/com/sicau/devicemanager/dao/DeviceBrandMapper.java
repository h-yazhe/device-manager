package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceBrand;

import java.util.List;

public interface DeviceBrandMapper {
    int deleteByPrimaryKey(String id);

    int insert(DeviceBrand record);

    int insertSelective(DeviceBrand record);

    DeviceBrand selectByPrimaryKey(String id);

    /**
     * Select from device_brand by brandId
     *
     * @param brandId the id of a brand
     * @return a list of 'devicebrand'
     * @author Xiao W
     */
    List<DeviceBrand> selectByBrandId(String brandId);

    int updateByPrimaryKeySelective(DeviceBrand record);

    int updateByPrimaryKey(DeviceBrand record);

    int deleteByDeviceId(String deviceId);

    int deleteByDeviceIds(List<String> deviceIds);
}