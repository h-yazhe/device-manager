package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.RepairOrder;

public interface RepairOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepairOrder record);

    int insertSelective(RepairOrder record);

    RepairOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RepairOrder record);

    int updateByPrimaryKey(RepairOrder record);
}