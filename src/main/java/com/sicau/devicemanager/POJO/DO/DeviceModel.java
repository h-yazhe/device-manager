package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class DeviceModel {
    private Integer id;

    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5_0-9]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "name只能为汉字、英文字母、数字、下划线的组合")
    private String name;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}