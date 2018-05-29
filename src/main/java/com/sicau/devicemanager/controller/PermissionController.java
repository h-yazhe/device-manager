package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Permission;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.PermissionService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 权限操作
 * @author BeFondOfTaro
 * Created at 13:34 2018/5/18
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN,paramType = "header",required = true)
    )
    @GetMapping(ResourceConstants.PERMISSION)
    @RequiresPermissions(ResourceConstants.PERMISSION + PermissionActionConstant.GET)
    public ResultVO listPermission(){
        return ResultVOUtil.success(permissionService.listPermission());
    }
}
