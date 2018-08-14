package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DO.DeviceBrand;
import com.sicau.devicemanager.POJO.DO.DeviceCategory;
import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.*;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Yazhe
 * Created at 17:45 2018/8/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceMapper deviceMapper;
	@Autowired
	private DeviceBrandMapper deviceBrandMapper;
	@Autowired
	private DeviceCategoryMapper deviceCategoryMapper;
	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void addDevice(DeviceDTO deviceDTO) {
		deviceDTO.setId(KeyUtil.genUniqueKey());
		deviceMapper.insertSelective(deviceDTO);
		insertDeviceBrand(deviceDTO);
		insertDeviceCategory(deviceDTO);
	}

	private void insertDeviceBrand(DeviceDTO deviceDTO){
		DeviceBrand deviceBrand = new DeviceBrand();
		deviceBrand.setId(KeyUtil.genUniqueKey());
		deviceBrand.setBrandId(deviceDTO.getBrand().getId());
		deviceBrand.setDeviceId(deviceDTO.getId());
		deviceBrandMapper.insert(deviceBrand);
	}

	private void insertDeviceCategory(DeviceDTO deviceDTO){
		for (String categoryId :
				deviceDTO.getCategoryId()) {
			DeviceCategory deviceCategory = new DeviceCategory();
			deviceCategory.setId(KeyUtil.genUniqueKey());
			deviceCategory.setCategoryId(categoryId);
			deviceCategory.setDeviceId(deviceDTO.getId());
			deviceCategoryMapper.insert(deviceCategory);
		}
	}

	@Override
	public void updateDeviceById(DeviceDTO deviceDTO) {
		deviceMapper.updateByPrimaryKeySelective(deviceDTO);

		deviceBrandMapper.deleteByDeviceId(deviceDTO.getId());
		insertDeviceBrand(deviceDTO);

		deviceCategoryMapper.deleteByDeviceId(deviceDTO.getId());
		insertDeviceCategory(deviceDTO);
	}

	@Override
	public void deleteDeviceById(List<String> ids) {
		if (ids.isEmpty()){
			return;
		}
		deviceCategoryMapper.deleteByDeviceIds(ids);
		deviceBrandMapper.deleteByDeviceIds(ids);
		deviceMapper.deleteByIds(ids);
	}

	@Override
	public PageInfo<DeviceDTO> listDevice(DeviceDTO deviceDTO) {
		//校验时间
		Date startTime = deviceDTO.getStartTime();
		Date endTime = deviceDTO.getEndTime();
		if (startTime!=null && endTime!=null){
			int result = startTime.compareTo(endTime);
			//开始时间大于结束时间
			if (result == 1){
				throw new CommonException(ResultEnum.DATE_INCORRECT.getCode(),"请选择合适的日期");
			}
			else if (result == 0){
				//开始时间=结束时间
				deviceDTO.setStartTime(DateUtil.getStartTimeToday(startTime));
				deviceDTO.setEndTime(DateUtil.getEndTimeToday(endTime));
			}
		}
		PageHelper.startPage(deviceDTO.getQueryPage().getPageNum(),deviceDTO.getQueryPage().getPageSize());
		List<DeviceDTO> deviceDTOList = deviceMapper.getDeviceInfo(deviceDTO);
		//组装地点和分类信息
		setLocationAndCategory(deviceDTOList);
		return new PageInfo<>(deviceDTOList);
	}

	private void setLocationAndCategory(List<DeviceDTO> deviceDTOList){
		for (DeviceDTO deviceDTO : deviceDTOList){
			//地点信息
			StringBuilder locationStr = new StringBuilder();
			Location location = locationMapper.getByDeviceId(deviceDTO.getId());
			String[] locationIds = location.getPath().split("/");
			//字符串分割后的第一个元素为空，直接跳过
			for (int i = 1; i<locationIds.length; i++){
				locationStr.append("/");
				locationStr.append(locationMapper.getById(locationIds[i]).getName());
			}
			deviceDTO.setLocation(locationStr.toString());

			//分类信息
			StringBuilder categoryStr = new StringBuilder();
			Category category = categoryMapper.getByDeviceId(deviceDTO.getId());
			String[] categoryIds = category.getPath().split("/");
			for (int i = 1; i < categoryIds.length; i++){
				categoryStr.append("/");
				categoryStr.append(categoryMapper.getById(categoryIds[i]).getName());
			}
			deviceDTO.setCategory(categoryStr.toString());
		}
	}
}
