package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.DeviceModel;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.DeviceModelService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 郭效坤
 * @Date Create in 15:53 2018/10/30
 * @Description 新建DeviceModel类用作设备型号的操作
 * 设备型号的查询和新增
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class DeviceModelController {

    @Autowired
    private DeviceModelService deviceModelService;

    /**
     * @author 郭效坤
     * @param deviceModel 设备型号表单
     * @description 新增设备型号
     * @return 成功返回视图对象，失败（设备型号重名）抛出异常
     */
    @PostMapping("/device-model-submit")
    @RequiresPermissions(ResourceConstants.MODEl + PermissionActionConstant.ADD)
    public ResultVO submitDeviceModel(@RequestBody DeviceModel deviceModel) {
       return ResultVOUtil.success(deviceModelService.submitDeviceModel(deviceModel));
    }

    /**
     * @author 郭效坤
     * @description 查找所有设备型号
     * @return 成功返回PResultVO下的PageInfo分页对象，如果没有则其中的DeviceModel列表为空
     */
    @PostMapping("/device-model-listAll")
    @RequiresPermissions(ResourceConstants.MODEl + PermissionActionConstant.GET)
    public ResultVO findDeviceModel() {
        return ResultVOUtil.success(deviceModelService.listAllDeviceModel());
    }

}
