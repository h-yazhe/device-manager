package com.sicau.devicemanager.POJO.DTO;

import lombok.Data;

import java.util.List;

/**
 * @author Yazhe
 * Created at 15:09 2018/8/7
 */
@Data
public class CategoryDTO {

	private String id;

	/**
	 * 子节点
	 */
	private List<CategoryDTO> children;

	/**
	 * 分类名
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
