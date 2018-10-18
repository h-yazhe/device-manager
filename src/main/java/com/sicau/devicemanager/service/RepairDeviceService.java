package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.RepairOrder;

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
}
