package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.DeviceModel;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.controller.DeviceController;
import com.sicau.devicemanager.dao.DeviceMapper;
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

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public boolean submitDeviceModel(DeviceModel deviceModel) {
    	//名称重复校验
		if (deviceModelMapper.getIdByName(deviceModel.getName()) != null){
			throw new BusinessException("名称不能重复");
		}
        String deviceModelName = deviceModel.getName();
        List<String> nameList = deviceModelMapper.selectAllDeviceModelName();
        for (String modelName : nameList) {
            //互换，防止空指针异常
            if (modelName.equals(deviceModelName)) {
                throw new BusinessException(BusinessExceptionEnum.DEVICE_MODEL_NAME_REPEAT);
            }
        }
        //提交设备型号表单
        deviceModelMapper.insertSelective(deviceModel);
        return true;
    }

    @Override
    public PageInfo<DeviceModel> listAllDeviceModel(QueryPage queryPage) {
        //分页查询
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), "id");
        return new PageInfo<>(deviceModelMapper.listAll());
    }

    @Override
    public void deleteDeviceModelById(int id) {
        if (!deviceMapper.selectDeviceByModelId(id).isEmpty()){
            throw new BusinessException("还存在设备正使用该商标，删除失败!");
        }
        deviceModelMapper.deleteByPrimaryKey(id);
    }
}
