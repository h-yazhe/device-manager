package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.RoleAddDTO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.RoleService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色操作
 * @author BeFondOfTaro
 * Created at 23:41 2018/5/15
 */
@Api(tags = "角色操作")
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     * @param roleAddDTO 角色信息
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN,paramType = "header")
    )
    @PostMapping(ResourceConstants.ROLE)
    @RequiresPermissions(ResourceConstants.ROLE + PermissionActionConstant.ADD)
    public ResultVO addRole(RoleAddDTO roleAddDTO){
        roleService.addRole(roleAddDTO);
        return ResultVOUtil.success();
    }

    /**
     * 根据角色id删除角色
     * @param roleId 角色id
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN,paramType = "header")
    )
    @DeleteMapping(ResourceConstants.ROLE + "/{roleId}")
    @RequiresPermissions(ResourceConstants.ROLE + PermissionActionConstant.DELETE)
    public ResultVO deleteRoleById(@PathVariable String roleId){
        roleService.deleteRoleById(roleId);
        return ResultVOUtil.success();
    }

    /**
     * 更新角色的权限
     * @param roleId 角色id
     * @param permissionIdList 权限id列表
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN,paramType = "header")
    )
    @PutMapping(ResourceConstants.ROLE + "/{roleId}")
    public ResultVO updateRolePermission(@PathVariable String roleId, @RequestParam List<String> permissionIdList){
        roleService.updateRolePermission(roleId,permissionIdList);
        return ResultVOUtil.success();
    }

    /**
     * 分页查询所有角色
     * @param queryPage 分页参数
     * @return
     */
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN,paramType = "header")
    )
    @GetMapping(ResourceConstants.ROLE)
    @RequiresPermissions(ResourceConstants.ROLE + PermissionActionConstant.GET)
    public ResultVO listRole(QueryPage queryPage){
        return ResultVOUtil.success(roleService.listRole(queryPage));
    }
}
