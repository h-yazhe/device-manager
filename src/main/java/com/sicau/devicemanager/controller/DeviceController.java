package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 设备管理
 * @author Yazhe
 * Created at 12:18 2018/8/9
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX + "/device")
@Api(tags = "设备管理")
public class DeviceController {

	@Autowired
	private DeviceService deviceService;

	@ApiOperation("添加设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/add")
	public ResultVO addDevice(@Validated(DeviceValidatedGroup.AddDeviceGroup.class)
						  @RequestBody DeviceDTO deviceDTO){
		deviceService.addDevice(deviceDTO);
		return ResultVOUtil.success();
	}

	@ApiOperation("更新设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/update")
	public ResultVO updateDeviceById(@Validated(DeviceValidatedGroup.UpdateDeviceGroup.class)
										 @RequestBody DeviceDTO deviceDTO){
		deviceService.updateDeviceById(deviceDTO);
		return ResultVOUtil.success();
	}

	@ApiOperation("分页查询所有设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/list")
	public ResultVO listDeviceByPage(@Validated(DeviceValidatedGroup.QueryDeviceGroup.class)@RequestBody QueryPage queryPage){
		return ResultVOUtil.success(deviceService.listDeviceByPage(queryPage));
	}

	@ApiOperation("删除设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/delete-device")
	public ResultVO deleteDeviceById(@RequestBody List<String> ids){
		deviceService.deleteDeviceById(ids);
		return ResultVOUtil.success();
	}

	public ResultVO listDevice(@Validated(DeviceValidatedGroup.QueryDeviceGroup.class)
							   @RequestBody DeviceDTO deviceDTO){
		return ResultVOUtil.success(deviceService.listDevice(deviceDTO));
	}
}
