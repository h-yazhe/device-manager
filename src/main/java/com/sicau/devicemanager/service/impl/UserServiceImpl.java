package com.sicau.devicemanager.service.impl;

import com.sicau.devicemanager.POJO.DO.User;
import com.sicau.devicemanager.POJO.DO.UserAuth;
import com.sicau.devicemanager.POJO.DO.UserRole;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.POJO.DTO.UserRegisterDTO;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.config.exception.ResourceException;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.constants.ResourceExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.UserAuthMapper;
import com.sicau.devicemanager.dao.UserMapper;
import com.sicau.devicemanager.dao.UserRoleMapper;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<String, Object> listUser(QueryPage queryPage) {
        Map<String, Object> resMap = new HashMap<>(2);
        Integer startNum = (queryPage.getPageNum() - 1) * queryPage.getPageSize();
        //如果用户被锁定了或者删除了就不再显示了；删除用户就是将locked和deleted都设为1
        List<UserDTO> users = userMapper.listUser(startNum, startNum + queryPage.getPageSize())
                .stream().filter((user -> !user.getLocked())).collect(Collectors.toList());
        resMap.put("list", users);
        resMap.put("total", users.size());
        return resMap;
    }

    @Override
    public String getPasswordByUserId(String userId) {
        return userAuthMapper.getPasswordByUserId(userId);
    }

    @Override
    public void addUser(UserRegisterDTO userRegisterDTO) {
        //用户名重复校验
        if (userMapper.getIdByUsername(userRegisterDTO.getUsername()) != null) {
            throw new BusinessException(BusinessExceptionEnum.USERNAME_DUPLICATED);
        }
        //用户信息写入
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        String userId = KeyUtil.genUniqueKey();
        user.setId(userId);
        if (1 != userMapper.insertUser(user)) {
            throw new BusinessException("新增用户失败");
        }
        //用户身份认证信息写入
        UserAuth userAuth = new UserAuth();
        userAuth.setId(KeyUtil.genUniqueKey());
        userAuth.setUserId(userId);
        userAuth.setIdentifyType(0);
        userAuth.setIdentifier(userRegisterDTO.getUsername());
        userAuth.setCredential(userRegisterDTO.getPassword());
        if (1 != userMapper.insertUserAuth(userAuth)) {
            throw new BusinessException(ResultEnum.UNKNOWN_ERROR);
        }
        //写入用户角色
        UserRole userRole = new UserRole();
        userRole.setId(KeyUtil.genUniqueKey());
        userRole.setUserId(userId);
        userRole.setRoleId(userRegisterDTO.getRoleId());
        if (1 != userRoleMapper.insertUserRole(userRole)) {
            throw new BusinessException(ResultEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public void modifyUser(UserRegisterDTO userRegisterDTO) {
        //用户名重复校验
        if (userMapper.getIdByUsername(userRegisterDTO.getUsername()) != null) {
            throw new BusinessException(BusinessExceptionEnum.USERNAME_DUPLICATED);
        }
        //用户信息写入
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);
        user.setId(userRegisterDTO.getUserId());
        UserDTO savedUser = userMapper.getUserById(user.getId());
        if (null == savedUser) {
            throw new BusinessException("未找到该用户");
        }
        if (1 != userMapper.updateUser(user)) {
            throw new BusinessException(ResultEnum.UNKNOWN_ERROR);
        }

        UserRole userRole = new UserRole();
        BeanUtils.copyProperties(userRegisterDTO, userRole);
        if (null != userRole.getRoleId()) {
            if (1 != userRoleMapper.updateUserRole(userRole)) {
                throw new BusinessException(ResultEnum.UNKNOWN_ERROR);
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
        userMapper.deleteUserLogically(userId);
/*        userMapper.deleteUserAuthByUserId(userId);
        userRoleMapper.deleteUserRoleByUserId(userId);
        userMapper.deleteUserById(userId);*/
    }
}
