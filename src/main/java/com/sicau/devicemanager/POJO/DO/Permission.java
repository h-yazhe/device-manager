package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 权限
 * @author BeFondOfTaro
 * Created at 23:18 2018/5/13
 */
@Data
public class Permission {

    @NotNull
    private String id;

    /**
     * 权限对应的资源
     */
    private String resource;

    /**
     * 资源的中文名，前端展示
     */
    private String resourceName;

    /**
     * 权限的代码/通配符,对应代码中@RequiresPermissions 的value
     */
    private String permissionCode;

    /**
     * 权限的中文释义
     */
    private String permissionName;

    /**
     * 是否本菜单必选权限,通常是"列表"权限是必选
     */
    private Boolean required;
}
