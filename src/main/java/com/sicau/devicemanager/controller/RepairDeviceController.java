package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.RepairOrder;
import com.sicau.devicemanager.POJO.DTO.DeviceStatusRecordDTO;
import com.sicau.devicemanager.POJO.DTO.RepairOrderDTO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.*;
import com.sicau.devicemanager.service.RepairDeviceService;
import com.sicau.devicemanager.util.EnumUtil;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    @RequiresPermissions(ResourceConstants.ORDER+PermissionActionConstant.ADD)
    @PostMapping("/submit-repair-order")
    public ResultVO repairDevice(@Validated(DeviceValidatedGroup.SubmitRepairOrder.class)
                                 @RequestBody RepairOrder repairOrder){
        repairDeviceService.submitRepairDeviceOrder(repairOrder);
        return ResultVOUtil.success();
    }

    /**
     * 修改订单，用户调用
     * @author Xiao W
     */
    @RequiresRoles("用户")
    @PostMapping("/modify-repair-order")
    public ResultVO modifyOrder(@Validated(DeviceValidatedGroup.ModifyRepairOrder.class)
                                @RequestBody RepairOrder repairOrder) {
        repairDeviceService.modifyOrder(repairOrder);
        return ResultVOUtil.success();
    }

    /**
     * 根据设备id获取订单
     * @author Xiao W
     */
    @GetMapping("/get-orders-by-device-id")
    public ResultVO getOrders(@RequestParam String deviceId) {
        if (StringUtils.isEmpty(deviceId)) {
            throw new CommonException(ResultEnum.DEVICE_ID_CANNOT_BE_NULL);
        }
        return ResultVOUtil.success(repairDeviceService.getOrdersByDeviceId(deviceId));
    }

    /**
     * 管理员（维修人员）调用完成订单
     * @author Xiao W
     */
    @RequiresPermissions(ResourceConstants.ORDER+PermissionActionConstant.FINISH_ADMIN)
    @GetMapping("/finish-order-admin")
    public ResultVO finishAdmin(@RequestParam int orderId, @RequestParam int orderStatus) {
        if (StringUtils.isEmpty(orderId)||StringUtils.isEmpty(orderStatus)) {
            throw new CommonException(ResultEnum.ORDER_PARAMS_NOT_SATIFIED);
        }
        repairDeviceService.finishOrder(orderId, EnumUtil.getByCode(orderStatus,OrderStatusEnum.class));
        return ResultVOUtil.success();
    }


    /**
     * 用户（订单提交人员）调用完成订单
     * @author Xiao W
     */
    @RequiresPermissions(ResourceConstants.ORDER+PermissionActionConstant.FINISH_USER)
    @GetMapping("/finish-order-user")
    public ResultVO finishUser(@RequestParam int orderId, @RequestParam int deviceStatus) {
        if (StringUtils.isEmpty(orderId)||StringUtils.isEmpty(deviceStatus)) {
            throw new CommonException(ResultEnum.ORDER_PARAMS_NOT_SATIFIED);
        }
        repairDeviceService.finishOrder(orderId, EnumUtil.getByCode(deviceStatus,DeviceStatusEnum.class));
        return ResultVOUtil.success();
    }

}
