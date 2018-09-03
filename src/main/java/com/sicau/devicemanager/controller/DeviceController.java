package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.DistributeDeviceGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.JWTUtil;
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

import javax.servlet.http.HttpServletRequest;
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

	@ApiOperation("根据条件查询设备列表")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/list")
	public ResultVO listDeviceByCondition(@Validated(DeviceValidatedGroup.QueryDeviceGroup.class)@RequestBody DeviceDTO deviceDTO,
										  HttpServletRequest request){
		deviceDTO.setUserId(JWTUtil.getUserId(request.getHeader(HttpParamKey.TOKEN)));
		return ResultVOUtil.success(deviceService.listDevice(deviceDTO));
	}

	@ApiOperation("删除设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/delete-device")
	public ResultVO deleteDeviceById(@RequestBody List<String> ids){
		deviceService.deleteDeviceById(ids);
		return ResultVOUtil.success();
	}

	@ApiOperation("分发设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/distribute")
	public ResultVO distributeDevice(@Validated(DistributeDeviceGroup.class)@RequestBody DistributeDeviceDTO distributeDeviceDTO){
		deviceService.distributeDevice(distributeDeviceDTO);
		return ResultVOUtil.success();
	}

	@ApiOperation("报废设备")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/discard")
	public ResultVO discardDevice(@RequestBody String deviceId){
		deviceService.discardDevice(deviceId);
		return ResultVOUtil.success();
	}

	@ApiOperation("获取搜索的选项卡第一页数据")
	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
	@PostMapping("/search-selection")
	public ResultVO getSearchSelections(){
		return ResultVOUtil.success(deviceService.getSearchSelections(10));
	}
}
