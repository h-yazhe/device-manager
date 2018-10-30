package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.DeviceModel;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.DeviceModelMapper;
import com.sicau.devicemanager.service.DeviceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 郭效坤
 * @Date: Create in 16:21 2018/10/30
 * @Description:
 */
@Service
public class DeviceModelServiceImpl implements DeviceModelService {

    @Autowired
    private DeviceModelMapper deviceModelMapper;

    @Override
    public boolean submitDeviceModel(DeviceModel deviceModel) {
        String deviceModelName = deviceModel.getName();
        List<String> nameList = deviceModelMapper.selectAllDeviceModelName();
        for (String modelName : nameList){
            //互换，防止空指针异常
            if (modelName.equals(deviceModelName)) {
                throw new CommonException(ResultEnum.DEVICE_MODEL_NAME_REPEAT);
            }
        }
        //提交设备型号表单
        deviceModelMapper.insertSelective(deviceModel);
        return true;
    }

    @Override
    public PageInfo<DeviceModel> listAllDeviceModel() {
        //分页查询
        QueryPage queryPage = new QueryPage();
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), "id");
        return new PageInfo<>(deviceModelMapper.listAll());
    }
}
