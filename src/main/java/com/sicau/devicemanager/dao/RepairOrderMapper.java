package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.RepairOrder;
import com.sicau.devicemanager.POJO.DTO.RepairOrderDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RepairOrder record);

    int insertSelective(RepairOrder record);

    RepairOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RepairOrder record);

    int updateByPrimaryKey(RepairOrder record);

    List<RepairOrder> getOrdersByDeviceId(String deviceId);

    List<RepairOrderDTO> selectRepairOrderByUserId(@Param("applyUserId") String applyUserId);

    List<RepairOrderDTO> selectRepairOrderByStatus(@Param("statusCode") Integer statusCode);
}