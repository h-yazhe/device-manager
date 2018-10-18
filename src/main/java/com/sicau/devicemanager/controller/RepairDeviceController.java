package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.RepairOrder;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.service.RepairDeviceService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 维修设备
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class RepairDeviceController {

    @Autowired
    public RepairDeviceService repairDeviceService;


    /**
     * 报修设备
     * @param repairOrder
     * @return
     */
    @PostMapping("/submit-repair-order")
    public ResultVO repairDevice(@Validated(DeviceValidatedGroup.SubmitRepairOrder.class)
                                 @RequestBody RepairOrder repairOrder){
        repairDeviceService.submitRepairDeviceOrder(repairOrder);
        return ResultVOUtil.success();
    }
}
