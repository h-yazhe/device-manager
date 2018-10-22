package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.RepairOrder;
import com.sicau.devicemanager.POJO.DTO.DeviceStatusRecordDTO;
import com.sicau.devicemanager.POJO.DTO.RepairOrderDTO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.DeviceStatusEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.service.RepairDeviceService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;

/**
 * 维修设备
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class RepairDeviceController {

    @Autowired
    public RepairDeviceService repairDeviceService;

    /**
     * 根据用户id查询所有维修设备订单
     * @param userId
     * @return 视图对象
     * TODO
     */
    @GetMapping("/select-repair-order/{userId}")
    public ResultVO selectRepairOrderByUserId(@PathVariable("userId") String userId) {
        return null;
    }


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

    @PostMapping("/delete-repair-order")
    public ResultVO deleteRepairDeviceOrder(@RequestBody RepairOrder repairOrder){
        if (repairDeviceService.deleteRepairDeviceOrder(repairOrder.getId())){
            return ResultVOUtil.success();
        }
        return ResultVOUtil.error(ResultEnum.DELETE_FAILED);
    }
}
