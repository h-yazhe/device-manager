package com.sicau.devicemanager.POJO.DO;

import lombok.Data;

/**
 * 地点
 * @author Yazhe
 * Created at 9:37 2018/7/31
 */
@Data
public class Location {

	private String id;

	/**
	 * 父级地点id
	 */
	private String parentId;

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
