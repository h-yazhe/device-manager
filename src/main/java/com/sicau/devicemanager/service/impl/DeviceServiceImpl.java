package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.*;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.DeviceSearchSelectionVO;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.constants.DeviceStatusEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.*;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.KeyUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
	@Autowired
	private DeviceStatusRecordMapper deviceStatusRecordMapper;
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private CustodianMapper custodianMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private DeviceModelMapper deviceModelMapper;
	@Autowired
	private WorkNatureMapper workNatureMapper;

	@Override
	public void addDevice(DeviceDTO deviceDTO) {
		deviceDTO.setId(KeyUtil.genUniqueKey());
		deviceDTO.setStatusId(DeviceStatusEnum.IN_STORAGE.getCode());
		deviceMapper.insertSelective(deviceDTO);
		deviceStatusRecordMapper.insert(
				new DeviceStatusRecord(KeyUtil.genUniqueKey(),deviceDTO.getId(),
						DeviceStatusEnum.UNCONNECTED.getCode(),DeviceStatusEnum.IN_STORAGE.getCode(),
						(String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal())
		);
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
				deviceDTO.getCategoryIds()) {
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

		//获取查询条件的分类id及所有子分类id
		if (!StringUtils.isEmpty(deviceDTO.getCategoryId())){
			List<Category> categoryList = categoryMapper.getDescendants(deviceDTO.getCategoryId());
			List<String> categoryIds = new ArrayList<>();
			categoryIds.add(deviceDTO.getCategoryId());
			categoryList.forEach((category) -> categoryIds.add(category.getId()));
			deviceDTO.setCategoryIds(categoryIds);
		}

		//获取查询条件的地点id及所有子地点id，若查询的地点id不属于角色管理区域下的id，则抛出异常
		String userId = deviceDTO.getUserId();
		if (userId == null){
			throw new RuntimeException("用户id不能为空");
		}
		//获取用户管理的地点（根地点）
		List<Location> userLocationList = locationMapper.getByUserId(userId);
		List<Location> locationList = new ArrayList<>();
		userLocationList.forEach((userLocation)->{
			locationList.add(userLocation);
			//获取所有子地点
			locationList.addAll(locationMapper.getDescendants(userLocation.getId()));
		});
		//开始校验地点
		if (!StringUtils.isEmpty(deviceDTO.getLocationId())){
			if (!checkLocationId(deviceDTO.getLocationId(),locationList)){
				throw new CommonException(ResultEnum.LOCATION_UNAUTHORIZED);
			}
		}
		//设置地点id作为查询条件
		List<String> locationIds = new ArrayList<>();
		for (Location item : locationList){
			locationIds.add(item.getId());
		}
		deviceDTO.setLocationIds(locationIds);

		//分页查询
		QueryPage queryPage = deviceDTO.getQueryPage();
		//默认id升序
		if (queryPage.getOrderBy() == null){
			queryPage.setOrderBy("id");
			queryPage.setOrderDirection("asc");
		}
		PageHelper.startPage(queryPage.getPageNum(),queryPage.getPageSize(),
				queryPage.getOrderBy() + " " +queryPage.getOrderDirection());
		List<DeviceDTO> deviceDTOList = deviceMapper.getDeviceInfo(deviceDTO);
		//组装地点和分类信息
		setLocationAndCategory(deviceDTOList);
		return new PageInfo<>(deviceDTOList);
	}

	@Override
	public void distributeDevice(DistributeDeviceDTO distributeDeviceDTO) {
		distributeDeviceDTO.setUseTime(new Date());
		deviceMapper.distributeDevice(distributeDeviceDTO);
	}

	@Override
	public void discardDevice(String deviceId) {
		deviceMapper.discardDevice(deviceId);
	}

	@Override
	public DeviceSearchSelectionVO getSearchSelections(int pageSize) {
		DeviceSearchSelectionVO deviceSearchSelectionVO = new DeviceSearchSelectionVO();
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setCategoryList(categoryMapper.getChildrenById(""));
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setLocationList(locationMapper.getChildrenById(""));
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setBrandList(brandMapper.listBrand());
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setCustodianList(custodianMapper.listAll());
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setDepartmentList(departmentMapper.listAll());
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setDeviceModelList(deviceModelMapper.listAll());
		PageHelper.startPage(1,pageSize);
		deviceSearchSelectionVO.setWorkNatureList(workNatureMapper.listAll());
		return deviceSearchSelectionVO;
	}

	/**
	 * 校验locationId是否在目标list中
	 * @param locationId
	 * @param locationList
	 * @return 存在返回true，否则返回false
	 */
	private boolean checkLocationId(String locationId, List<Location> locationList){
		for (Location location : locationList){
			if (locationId.equals(location.getId())){
				return true;
			}
		}
		return false;
	}

	private void setLocationAndCategory(List<DeviceDTO> deviceDTOList){
		for (DeviceDTO deviceDTO : deviceDTOList){
			//地点信息
			StringBuilder locationStr = new StringBuilder();
			Location location = deviceDTO.getLocation();
			//不给用户返回location数据
			deviceDTO.setLocation(null);
			List<String> locationIds = new ArrayList<>(Arrays.asList(location.getPath().split("/")));
			//字符串分割后第一个元素为空，去掉
			if (locationIds.size()>0){
				locationIds.remove(0);
				List<Location> locationList = locationMapper.getLocationsInIds(locationIds);
				for (Location item : locationList){
					locationStr.append(item.getName());
					locationStr.append("/");
				}
				deviceDTO.setLocationStr(locationStr.append(location.getName()).toString());
			}else {
				//否则为顶级区域
				deviceDTO.setLocationStr(location.getName());
			}

			//分类信息
			StringBuilder categoryStr = new StringBuilder();
			Category category = deviceDTO.getCategory();
			//TODO 上线删除，测试用
			if (category == null){
				deviceDTO.setCategoryStr("未分类");
				continue;
			}
			deviceDTO.setCategory(null);
			List<String> categoryIds = new ArrayList<>(Arrays.asList(category.getPath().split("/")));
			if (categoryIds.size()>0){
				categoryIds.remove(0);
				List<Category> categoryList = categoryMapper.getCategoryInIds(categoryIds);
				for (Category item : categoryList){
					categoryStr.append(item.getName());
					categoryStr.append("/");
				}
				deviceDTO.setCategoryStr(categoryStr.append(category.getName()).toString());
			}else {
				deviceDTO.setCategoryStr(category.getName());
			}
		}
	}
}
