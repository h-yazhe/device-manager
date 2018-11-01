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
     * @param deviceModel 设备型号表单
     * @return 成功返回true，失败抛出异常
     * @author 郭效坤
     * @description 新增设备型号
     */
    boolean submitDeviceModel(DeviceModel deviceModel);

    /**
     * @return 成功返回分页对象，内含设备型号列表，失败返回null
     * @author 郭效坤
     * @description 一次查找全部设备型号
     */
    PageInfo<DeviceModel> listAllDeviceModel();

    /**
     * 根据id删除设备型号
     * @param id 设备型号id
     * @return
     * @author Xiao W
     */
    void deleteDeviceModelById(int id);

}
