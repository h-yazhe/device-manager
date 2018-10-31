package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.LocationDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.LocationVO;
import com.sicau.devicemanager.dao.LocationMapper;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.service.LocationService;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yazhe
 * Created at 10:55 2018/7/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationMapper locationMapper;
	@Autowired
	private DeviceService deviceService;

	@Override
	public void insertLocationTree(List<Location> locationList) {
		//根节点数量
		int rootCount = 0;
		for (Location location :
				locationList) {
			//查找根节点
			if ("".equals(location.getParentId())){
				rootCount++;
				//初始化根节点
				location.setLevel(1);
				location.setPath("/");
				//设置节点的属性
				setLocationInfo(location,locationList);
			}
		}
		if (rootCount == 0){
			throw new RuntimeException("根节点数量不能为0");
		}
		locationMapper.insertList(locationList);
	}

	@Override
	public void insertLocationByPId(Location location) {
		List<Location> locationList = new ArrayList<>(1);
		location.setId(KeyUtil.genUniqueKey());
		//根据父节点信息设置当前节点其他信息
		if (location.getParentId().isEmpty()){
			location.setLevel(1);
			location.setPath("/");
		}else {
			Location pLocation = locationMapper.getById(location.getParentId());
			location.setLevel(pLocation.getLevel() + 1);
			location.setPath(pLocation.getPath() + pLocation.getId() + "/");
		}
		locationList.add(location);
		locationMapper.insertList(locationList);
	}

	/**
	 * 设置当前节点极其子孙节点的主键，level和path
	 * @param location 当前地点
	 * @param locationList 地点树的节点集合
	 */
	private void setLocationInfo(Location location,List<Location> locationList){
		//查找子节点
		List<Location> childs = new ArrayList<>();
		for (Location child : locationList){
			if (location.getId().equals(child.getParentId())){
				childs.add(child);
			}
		}
		//生成主键
		location.setId(KeyUtil.genUniqueKey());
		//再继续设置子节点的属性，没有子节点时跳出递归
		for (Location child:childs){
			child.setParentId(location.getId());
			child.setLevel(location.getLevel() + 1);
			//拼接path
			child.setPath(location.getPath() + location.getId() + "/");
			setLocationInfo(child,locationList);
		}
	}

	@Override
	public void deleteLocationTree(String rootId) {
		List<String> ids = new ArrayList<>();
		locationMapper.getDescendants(rootId).forEach(location -> {
			ids.add(location.getId());
		});
		ids.add(rootId);
		//删除与地点关联的设备
		deviceService.deleteDeviceById(ids);
		//删除地点
		locationMapper.deleteByIds(ids);
	}

	@Override
	public void updateLocationTree(String rootId, List<Location> locationList) {
		deleteLocationTree(rootId);
		insertLocationTree(locationList);
	}

	@Override
	public List<LocationDTO> listLocationTree() {
		List<Location> locationList = locationMapper.selectAll();
		List<LocationDTO> locationDTOList = new ArrayList<>();
		for (Location location :
				locationList) {
			//查找根节点
			if (location.getParentId() == null){
				locationDTOList.add(createChildren(location,locationList));
			}
		}
		return locationDTOList;
	}

	@Override
	public List<Location> listLocationByPId(LocationVO locationVO) {
		QueryPage queryPage = locationVO.getQueryPage();
		PageHelper.startPage(queryPage.getPageNum(),queryPage.getPageSize(),"name");
		return locationMapper.getChildrenById(locationVO.getParentId());
	}

	/**
	 * 由当前节点生成子节点的DTO
	 * @param root 当前节点
	 * @param locationList 所有节点
	 * @return 根节点
	 */
	private LocationDTO createChildren(Location root, List<Location> locationList){
		//生成根节点DTO
		LocationDTO rootDTO = new LocationDTO();
		BeanUtils.copyProperties(root,rootDTO);
		//查找子节点
		for (Location location : locationList){
			//查找到了子节点
			if (root.getId().equals(location.getParentId())){
				//懒创建子节点的列表
				if (rootDTO.getChildren() == null){
					rootDTO.setChildren(new ArrayList<>());
				}
				//添加子节点
				rootDTO.getChildren().add(createChildren(location,locationList));
			}
		}
		//返回根节点
		return rootDTO;
	}
}
