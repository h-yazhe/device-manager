package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * @author BeFondOfTaro
 * Created at 10:45 2018/5/15
 */
public interface UserRoleMapper {

    /**
     * 根据角色id删除用户角色表信息
     * @param roleId 角色id
     * @return
     */
    int deleteUserRoleByRoleId(@Param("roleId") String roleId);

    /**
     * 根据用户id删除user_role表信息
     * @param userId 用户id
     * @return
     */
    int deleteUserRoleByUserId(@Param("userId") String userId);

    /**
     * 插入信息到user_role表
     * @param userRole
     * @return
     */
    int insertUserRole(UserRole userRole);
}
