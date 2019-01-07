package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.DeviceModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DeviceModel record);

    int insertSelective(DeviceModel record);

    DeviceModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DeviceModel record);

    int updateByPrimaryKey(DeviceModel record);

    List<DeviceModel> listAll();

    List<String> selectAllDeviceModelName();

    String getIdByName(@Param("name") String name);
}