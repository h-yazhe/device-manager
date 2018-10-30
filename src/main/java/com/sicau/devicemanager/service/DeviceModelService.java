package com.sicau.devicemanager.service;

import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.DeviceModel;

/**
 * @Author: 郭效坤
 * @Date: Create in 16:17 2018/10/30
 * @Description: 设备模型操作用服务层
 */
public interface DeviceModelService {

    /**
     * @author 郭效坤
     * @param deviceModel 设备型号表单
     * @description 新增设备型号
     * @return 成功返回true，失败抛出异常
     */
    boolean submitDeviceModel(DeviceModel deviceModel);

    /**
     * @author 郭效坤
     * @description 一次查找全部设备型号
     * @return 成功返回分页对象，内含设备型号列表，失败返回null
     */
    PageInfo<DeviceModel> listAllDeviceModel();

}
