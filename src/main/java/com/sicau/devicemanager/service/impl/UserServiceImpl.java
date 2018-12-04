package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.sicau.devicemanager.POJO.DO.User;
import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.POJO.DO.UserRole;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.POJO.DTO.UserRegisterDTO;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.config.exception.ResourceException;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.constants.ResourceExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.UserAuthMapper;
import com.sicau.devicemanager.dao.UserMapper;
import com.sicau.devicemanager.dao.UserRoleMapper;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.KeyUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BeFondOfTaro
 * Created at 13:05 2018/5/14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    public UserDTO getUserById(String userId) {
        UserDTO userDTO = userMapper.getUserById(userId);
        if (userDTO == null) {
            throw new ResourceException(ResourceExceptionEnum.RESOURCE_NOT_FOUND, ResourceConstants.USER);
        }
        return userDTO;
    }

    @Override
    public Map<String,Object> listUser(QueryPage queryPage) {
		Map<String,Object> resMap = new HashMap<>(2);
		Integer startNum = (queryPage.getPageNum()-1)*queryPage.getPageSize();
		resMap.put("list",userMapper.listUser(startNum,startNum+queryPage.getPageSize()));
		resMap.put("total",userMapper.countUser());
        return resMap;
    }

    @Override
    public String getPasswordByUserId(String userId) {
        return userAuthMapper.getPasswordByUserId(userId);
    }

    @Override
    public void addUser(UserRegisterDTO userRegisterDTO) {
        //用户信息写入
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        String userId = KeyUtil.genUniqueKey();
        user.setId(userId);
        if (1 != userMapper.insertUser(user)) {
            throw new CommonException(ResultEnum.UNKNOWN_ERROR);
        }
        //用户身份认证信息写入
        UserAuth userAuth = new UserAuth();
        userAuth.setId(KeyUtil.genUniqueKey());
        userAuth.setUserId(userId);
        userAuth.setIdentifyType(0);
        userAuth.setIdentifier(userRegisterDTO.getUsername());
        userAuth.setCredential(userRegisterDTO.getPassword());
        if (1 != userMapper.insertUserAuth(userAuth)) {
            throw new CommonException(ResultEnum.UNKNOWN_ERROR);
        }
        //写入用户角色
        UserRole userRole = new UserRole();
        userRole.setId(KeyUtil.genUniqueKey());
        userRole.setUserId(userId);
        userRole.setRoleId(userRegisterDTO.getRoleId());
        if (1 != userRoleMapper.insertUserRole(userRole)) {
            throw new CommonException(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public void modifyUser(UserRegisterDTO userRegisterDTO) {
        //用户信息写入
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setId(userRegisterDTO.getUserId());
        UserDTO savedUser = userMapper.getUserById(user.getId());
        if (null == savedUser) {
            throw new CommonException(ResultEnum.USER_NOT_FOUND);
        }
        if (1 != userMapper.updateUser(user)) {
            throw new CommonException(ResultEnum.UNKNOWN_ERROR);
        }

        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRegisterDTO,userRole);
        if (null!=userRole.getRoleId()){
            if (1 != userRoleMapper.updateUserRole(userRole)) {
                throw new CommonException(ResultEnum.UNKNOWN_ERROR);
            }
        }
    }

    @Override
    public void updateUserRole(String userId, List<String> roleIdList) {
        //删除原来的角色
        userRoleMapper.deleteUserRoleByUserId(userId);
        //添加要更新的角色
        UserRole userRole = new UserRole();
        for (String roleId : roleIdList) {
            userRole.setId(KeyUtil.genUniqueKey());
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insertUserRole(userRole);
        }
    }

    @Override
    public void deleteUserById(String userId) {
        userMapper.deleteUserAuthByUserId(userId);
        userRoleMapper.deleteUserRoleByUserId(userId);
        userMapper.deleteUserById(userId);
    }
}
