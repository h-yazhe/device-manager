package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class WorkNature {
    private String id;

    @NotNull(message = "名称不能为空", groups = {DeviceValidatedGroup.addWorkNature.class})
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5_0-9]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "name只能为汉字、英文字母、数字、下划线的组合")
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