package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Permission;

import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 13:31 2018/5/18
 */
public interface PermissionMapper {

    /**
     * 查询所有权限
     * @return
     */
    List<Permission> listPermission();

	/**
	 * 根据id查询权限
	 * @param ids
	 * @return
	 */
	List<Permission> listPermissionInIds(List<String> ids);
}
