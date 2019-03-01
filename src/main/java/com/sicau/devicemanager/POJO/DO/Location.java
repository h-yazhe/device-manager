package com.sicau.devicemanager.POJO.DO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTree;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTreeByPId;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 地点
 * @author Yazhe
 * Created at 9:37 2018/7/31
 */
@Data
@JsonInclude(Include.NON_NULL)
public class Location {

    @NotNull(message = "id不能为空", groups = {InsertTree.class})
    @Pattern(regexp = "^\\d*$", message = "location的id只能是数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String id;

    /**
     * 父级地点id
     */
    @Pattern(regexp = "^\\d*$", message = "location的parentId只能是数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String parentId;

    /**
     * 地名
     */
    @NotNull(message = "地名不能为空", groups = {InsertTree.class, InsertTreeByPId.class})
    private String name;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 路径
     */
    @Pattern(regexp = "^/[\\d/]+", message = "location的path必须从“/”开始，且只能包含数字、和“/” ",groups = CommonValidatedGroup.LegalityGroup.class)
    private String path;

    public Location() {
    }

    public Location(String id, String parentId, String name, Integer level, String path) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.level = level;
        this.path = path;
    }

    public Location(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
}
