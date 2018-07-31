package com.sicau.devicemanager.POJO.DTO;

import lombok.Data;

import java.util.List;

/**
 * 地点树的节点
 * @author Yazhe
 * Created at 11:21 2018/7/31
 */
@Data
public class LocationDTO {

	private String id;

	/**
	 * 子节点
	 */
	private List<LocationDTO> children;

	/**
	 * 地名
	 */
	private String name;

	/**
	 * 层级
	 */
	private Integer level;

	/**
	 * 路径
	 */
	private String path;
}
