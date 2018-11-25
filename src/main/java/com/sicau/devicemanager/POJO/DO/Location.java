package com.sicau.devicemanager.POJO.DO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTree;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertTreeByPId;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 地点
 * @author Yazhe
 * Created at 9:37 2018/7/31
 */
@Data
@ApiModel("地点")
@JsonInclude(Include.NON_NULL)
public class Location {

    @NotNull(message = "id不能为空", groups = {InsertTree.class})
    private String id;

    /**
     * 父级地点id
     */
    @ApiModelProperty("父级地点id")
    @NotNull(message = "父级地点id不能为空", groups = {InsertTree.class, ListTreeByPId.class, InsertTreeByPId.class})
    private String parentId;

    /**
     * 地名
     */
    @ApiModelProperty("地名")
    @NotNull(message = "地名不能为空", groups = {InsertTree.class, InsertTreeByPId.class})
    private String name;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

    /**
     * 路径
     */
    @ApiModelProperty("路径")
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
