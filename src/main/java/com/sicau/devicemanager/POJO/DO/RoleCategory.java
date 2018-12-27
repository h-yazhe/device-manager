package com.sicau.devicemanager.POJO.DO;

import java.util.Date;

/**
 * Created By Chq
 */
public class RoleCategory {
    private String id;

    private String roleId;

    private String categoryId;

    private Date bindTime;

    private String operateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public RoleCategory(){

    }

    public RoleCategory(String id, String roleId, String categoryId, Date bindTime, String operateUserId) {
        this.id = id;
        this.roleId = roleId;
        this.categoryId = categoryId;
        this.bindTime = bindTime;
        this.operateUserId = operateUserId;
    }
}
