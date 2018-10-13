package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.VO.LocationVO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.LocationService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地点
 * @author Yazhe
 * Created at 17:02 2018/7/31
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
@Api(tags = "地点")
public class LocationController {

	@Autowired
	private LocationService locationService;

//	@ApiOperation("查询所有地点")
//	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
//	@GetMapping("location")
//	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.GET)
//	public ResultVO listLocationTree(){
//		return ResultVOUtil.success(locationService.listLocationTree());
//	}

	@ApiOperation("根据父id查询所有地点")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("list-location-by-pid")
	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.GET)
	public ResultVO listLocationByPId(@Validated({ListTreeByPId.class}) @RequestBody LocationVO locationVO){
		return ResultVOUtil.success(locationService.listLocationByPId(locationVO));
	}

	@ApiOperation("删除该节点为根的树")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("delete-location-tree-by-Id/{rootId}")
	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.DELETE)
	public ResultVO deleteLocationTree(@PathVariable("rootId") String rootId){
		locationService.deleteLocationTree(rootId);
		return ResultVOUtil.success();
	}

	@ApiOperation("更新地点树")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PutMapping("location/{rootId}")
	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.UPDATE)
	public ResultVO updateLocationTree(@PathVariable String rootId,@RequestBody List<Location> locationList){
		locationService.updateLocationTree(rootId, locationList);
		return ResultVOUtil.success();
	}

//	@ApiOperation("插入一个地点树")
//	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
//	@PostMapping("location")
//	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.ADD)
//	public ResultVO insertLocationTree(@RequestBody List<Location> locationList){
//		locationService.insertLocationTree(locationList);
//		return ResultVOUtil.success();
//	}

	/**
	 * 根据父id插入一个子地点
	 * @param location
	 * @return
	 */
	@PostMapping("insert-location-by-pid")
	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.ADD)
	public ResultVO insertLocationByPId(@RequestBody Location location){
		locationService.insertLocationByPId(location);
		return ResultVOUtil.success();
	}
}
