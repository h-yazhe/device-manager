package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.VO.ResultVO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@ApiOperation("查询所有地点")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@GetMapping("location")
	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.GET)
	public ResultVO listLocationTree(){
		return ResultVOUtil.success(locationService.listLocationTree());
	}
}
