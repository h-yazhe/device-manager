package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.POJO.DTO.UserRegisterDTO;

import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 19:29 2018/5/13
 */
public interface UserService {

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return
     */
    UserDTO getUserById(String userId);

    /**
     * 分页查询所有用户
     * @param queryPage 分页参数
     * @return
     */
    List<UserDTO> listUser(QueryPage queryPage);

    /**
     * 根据用户id查询密码(不需要暴露endpoint)
     * @param userId 用户id
     * @return
     */
    String getPasswordByUserId(String userId);

    /**
     * 添加用户
     * @param userRegisterDTO 用户信息
     */
    void addUser(UserRegisterDTO userRegisterDTO);

    /**
     * 修改用户
     * @param userRegisterDTO 用户信息
     */
    void modifyUser(UserRegisterDTO userRegisterDTO);

    /**
     * 为用户批量更新角色
     * @param userId     用户id
     * @param roleIdList 角色id列表
     */
    void updateUserRole(String userId, List<String> roleIdList);

    /**
     * 通过用户id删除用户
     * @param userId 用户id
     */
    void deleteUserById(String userId);

}
