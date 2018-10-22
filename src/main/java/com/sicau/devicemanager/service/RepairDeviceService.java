package com.sicau.devicemanager.service;

import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.RepairOrder;
import com.sicau.devicemanager.POJO.DTO.RepairOrderDTO;

/**
 * 维修设备
 * @author
 */
public interface RepairDeviceService {


    /**
     * 提交报修设备订单
     * @param repairOrder
     */
    void submitRepairDeviceOrder(RepairOrder repairOrder);

    /**
     * 删除维修订单(申请用户可删除自己的，管理员都可以删除)
     * @param id
     */
    boolean deleteRepairDeviceOrder(Integer id);

}
