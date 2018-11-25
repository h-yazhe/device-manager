package com.sicau.devicemanager.POJO.DTO;

import com.sicau.devicemanager.POJO.DO.Permission;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 19:00 2018/5/14
 */
@Data
public class RoleDTO {

    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 是否锁定
     */
    private Boolean locked;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 权限
     */
    private List<Permission> permissionList;
}
