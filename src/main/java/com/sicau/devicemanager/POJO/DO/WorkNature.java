package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;

import javax.validation.constraints.NotNull;

public class WorkNature {
    private String id;

    @NotNull(message = "名称不能为空", groups = {DeviceValidatedGroup.addWorkNature.class})
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}