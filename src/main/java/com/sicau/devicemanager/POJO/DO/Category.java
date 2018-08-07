package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.InsertDeviceCategoryGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 设备分类
 * @author Yazhe
 * Created at 14:57 2018/8/7
 */
@Data
@ApiModel("设备分类")
public class Category {

	private String id;

	/**
	 * 父级地点id
	 */
	@ApiModelProperty("父级分类id")
	private String parentId;

	/**
	 * 地名
	 */
	@ApiModelProperty("分类名")
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
}
