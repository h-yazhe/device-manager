package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.POJO.DTO.UserRegisterDTO;
import com.sicau.devicemanager.POJO.VO.PageResult;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户操作
 * @author BeFondOfTaro
 * Created at 19:21 2018/5/15
 */
@Api(tags = "用户操作")
@RestController
@RequestMapping(CommonConstants.API_PREFIX + "/" + ResourceConstants.USER)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return
     */
    @ApiOperation("根据用户id查询用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header")
    )
    @GetMapping(ResourceConstants.USER + "/{userId}")
    @RequiresPermissions(ResourceConstants.USER + PermissionActionConstant.GET)
    public ResultVO getUserById(@PathVariable("userId") String userId) {
        return ResultVOUtil.success(userService.getUserById(userId));
    }

    /**
     * 分页查询所有用户
     * @param queryPage 分页参数
     * @return
     */
    @ApiOperation("分页查询所有用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header")
    )
    @PostMapping("list-by-page")
    @RequiresPermissions(ResourceConstants.USER + PermissionActionConstant.GET)
    public ResultVO<PageResult<UserDTO>> listUser(@RequestBody @Valid QueryPage queryPage) {
        return ResultVOUtil.success(userService.listUser(queryPage));
    }

    /**
     * 修改用户
     * @param userRegisterDTO 用户信息
     */
    @ApiOperation("修改用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header")
    )
    @PostMapping("modify")
    public ResultVO modifyUser(@Validated({DeviceValidatedGroup.modifyUser.class, CommonValidatedGroup.LegalityGroup.class}) @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.modifyUser(userRegisterDTO);
        return ResultVOUtil.success();
    }

    /**
     * 添加用户
     * @param userRegisterDTO 用户信息
     */
    @ApiOperation("添加用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header")
    )
    @PostMapping("add")
    @RequiresPermissions(ResourceConstants.USER + PermissionActionConstant.ADD)
    public ResultVO addUser(@Validated({DeviceValidatedGroup.addUser.class}) @RequestBody UserRegisterDTO userRegisterDTO) {
        userService.addUser(userRegisterDTO);
        return ResultVOUtil.success();
    }

    /**
     * 为用户批量更新角色
     * @param userId     用户id
     * @param roleIdList 角色id列表
     */
    @ApiOperation("为用户批量更新角色")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header", required = true)
    )
    @PostMapping(ResourceConstants.USER + "/{userId}/" + ResourceConstants.ROLE)
    @RequiresPermissions(ResourceConstants.USER + PermissionActionConstant.UPDATE)
    public ResultVO updateUserRole(@PathVariable String userId, @RequestBody List<String> roleIdList) {
        userService.updateUserRole(userId, roleIdList);
        return ResultVOUtil.success();
    }

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     */
    @ApiOperation("通过用户id删除用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = HttpParamKey.TOKEN, paramType = "header")
    )
    @PostMapping("delete/{userId}")
    @RequiresPermissions(ResourceConstants.USER + PermissionActionConstant.DELETE)
    public ResultVO deleteUserById(@PathVariable String userId) {
        userService.deleteUserById(userId);
        return ResultVOUtil.success();
    }
}
