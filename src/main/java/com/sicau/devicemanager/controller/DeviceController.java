package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DeviceStatusRecordDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.exception.VerificationException;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.DistributeDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.GetDeviceStatusRecordByDeviceId;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.util.JWTUtil;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/add")
	@RequiresPermissions(ResourceConstants.DEVICE + PermissionActionConstant.ADD)
    //验证分成两部分：1.非空验证(AddDeviceGroup)，2.判断一个字段是否符合格式要求(DefaultGroup)，格式要求是一致的
    public ResultVO addDevice(@Validated({DeviceValidatedGroup.AddDeviceGroup.class, CommonValidatedGroup.LegalityGroup.class})
                              @RequestBody DeviceDTO deviceDTO) {
        deviceService.addDevice(deviceDTO);
        return ResultVOUtil.success();
    }

    @ApiOperation("更新设备")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/update")
	@RequiresPermissions(ResourceConstants.DEVICE + PermissionActionConstant.UPDATE)
    public ResultVO updateDeviceById(@Validated({DeviceValidatedGroup.UpdateDeviceGroup.class, CommonValidatedGroup.LegalityGroup.class})
                                     @RequestBody DeviceDTO deviceDTO) {
        deviceService.updateDeviceById(deviceDTO);
        return ResultVOUtil.success();
    }

    @ApiOperation("根据条件查询设备列表")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/list")
	@RequiresPermissions(ResourceConstants.DEVICE + PermissionActionConstant.GET)
    public ResultVO listDeviceByCondition(@Validated({DeviceValidatedGroup.QueryDeviceGroup.class, CommonValidatedGroup.LegalityGroup.class})
                                          @RequestBody DeviceDTO deviceDTO, HttpServletRequest request) throws VerificationException {
        deviceDTO.setUserId(JWTUtil.getUserId(request.getHeader(HttpParamKey.TOKEN)));
        return ResultVOUtil.success(deviceService.listDevice(deviceDTO));

    }

    @ApiOperation("删除设备")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/delete-device")
	@RequiresPermissions(ResourceConstants.DEVICE + PermissionActionConstant.DELETE)
    public ResultVO deleteDeviceById(@RequestBody List<String> ids) {
        deviceService.deleteDeviceById(ids);
        return ResultVOUtil.success();
    }

    @ApiOperation("分发设备")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/distribute")
	@RequiresPermissions(ResourceConstants.DEVICE + ":distribute")
    public ResultVO distributeDevice(@Validated(DistributeDeviceGroup.class) @RequestBody DistributeDeviceDTO distributeDeviceDTO) {
        deviceService.distributeDevice(distributeDeviceDTO);
        return ResultVOUtil.success();
    }

    @ApiOperation("报废设备")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/discard")
	@RequiresPermissions(ResourceConstants.DEVICE + ":discard")
    public ResultVO discardDevice(@RequestBody String deviceId) {
        deviceService.discardDevice(deviceId);
        return ResultVOUtil.success();
    }

    @ApiOperation("获取搜索的选项卡第一页数据")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("/search-selection/{pageSize}")
    public ResultVO getSearchSelections(@PathVariable Integer pageSize) {
        return ResultVOUtil.success(deviceService.getSearchSelections(pageSize));
    }


    /**
     * 根据设备id查询设备状态变更记录
     * @param deviceStatusRecordDTO
     * @return
     */
    @PostMapping("get-status-record-by-deviceId")
	@RequiresPermissions(ResourceConstants.DEVICE + PermissionActionConstant.GET)
    public ResultVO getDeviceStatusRecordByDeviceId(@Validated({GetDeviceStatusRecordByDeviceId.class, CommonValidatedGroup.LegalityGroup.class})
                                                    @RequestBody DeviceStatusRecordDTO deviceStatusRecordDTO) {
        return ResultVOUtil.success(deviceService.getDeviceStatusRecordByDeviceId(deviceStatusRecordDTO));
    }

    /**
     * 根据设备id更新设备状态
     * @param device
     * @return
     */
    @PostMapping("/update-repair-status-by-deviceId")
    public ResultVO updateRepairedStatusByDeviceId(@Validated({DeviceValidatedGroup.UpdateRepairedStatusByDeviceId.class, CommonValidatedGroup.LegalityGroup.class})
                                                   @RequestBody Device device) {
        deviceService.updateRepairedStatusByDeviceId(device.getId(), device.getStatusId());
        return ResultVOUtil.success();
    }

    @PostMapping("/download-device-template")
    public ResultVO downloadTemplate(@PathVariable String path, HttpServletResponse response){
        System.out.println(path);
        deviceService.downloadTemplate(path,"device-template",response);
        return ResultVOUtil.success();
    }
}
