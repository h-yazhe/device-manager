package com.sicau.devicemanager.POJO.DTO;

import com.sicau.devicemanager.POJO.DO.Permission;
import com.sicau.devicemanager.POJO.DO.Role;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户
 * @author BeFondOfTaro
 * Created at 23:04 2018/5/13
 */
@Data
public class UserDTO {

    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

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
     * 最后一次登录时间
     */
    private Date lastTime;

    /**
     * 角色列表
     */
    private List<Role> roleList;

    /**
     * 权限列表
     */
    private List<Permission> permissionList;
}
