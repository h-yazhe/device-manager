package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DO.DeviceBrand;
import com.sicau.devicemanager.POJO.DO.DeviceCategory;
import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.DeviceVO;
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
		deviceBrand.setBrandId(deviceDTO.getBrandId());
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
	public PageInfo<DeviceVO> listDeviceByPage(QueryPage queryPage) {
		PageHelper.startPage(queryPage.getPageNum(),queryPage.getPageSize());
		List<DeviceVO> deviceInfo = deviceMapper.getDeviceInfo();
		//组装地点和分类信息
		for (DeviceVO deviceVO : deviceInfo){
			//地点信息
			List<Location> locationList = new ArrayList<>();
			Location location = locationMapper.getByDeviceId(deviceVO.getId());
			String[] locationIds = location.getPath().split("/");
			//字符串分割后的第一个元素为空，直接跳过
			for (int i = 1; i<locationIds.length; i++){
				locationList.add(locationMapper.getById(locationIds[i]));
			}
			deviceVO.setLocationList(locationList);

			//分类信息
			List<Category> categoryList = new ArrayList<>();
			Category category = categoryMapper.getByDeviceId(deviceVO.getId());
			String[] categoryIds = category.getPath().split("/");
			for (int i = 1; i < locationIds.length; i++){
				categoryList.add(categoryMapper.getById(categoryIds[i]));
			}
			deviceVO.setCategoryList(categoryList);
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
}
