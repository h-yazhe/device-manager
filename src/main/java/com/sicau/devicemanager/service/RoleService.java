package com.sicau.devicemanager.service;

import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.RoleAddDTO;
import com.sicau.devicemanager.POJO.DTO.RoleDTO;

import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 18:59 2018/5/14
 */
public interface RoleService {

    /**
     * 添加角色
     * @param roleAddDTO 角色信息
     */
    void addRole(RoleAddDTO roleAddDTO);

    /**
     * 根据角色id删除角色
     * @param roleId 角色id
     */
    void deleteRoleById(String roleId);

    /**
     * 更新角色的权限
     * @param roleId           角色id
     * @param permissionIdList 权限id列表
     */
    void updateRolePermission(String roleId, List<String> permissionIdList);

    /**
     * 分页查询所有角色
     * @param queryPage 分页参数
     * @return
     */
    PageInfo<RoleDTO> listRole(QueryPage queryPage);
}
