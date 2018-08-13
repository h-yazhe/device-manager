package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DO.DeviceBrand;
import com.sicau.devicemanager.POJO.DO.DeviceCategory;
import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.dao.*;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
	public PageInfo<DeviceDTO> listDeviceByPage(QueryPage queryPage) {
		PageHelper.startPage(queryPage.getPageNum(),queryPage.getPageSize());
		List<DeviceDTO> deviceInfo = deviceMapper.getDeviceInfo();
		//组装地点和分类信息
		for (DeviceDTO deviceDTO : deviceInfo){
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
		return new PageInfo<>(deviceInfo);
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
	public List<DeviceDTO> listDevice(DeviceDTO deviceDTO) {
		return null;
	}
}
