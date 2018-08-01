package com.sicau.devicemanager.util.tree;

import lombok.Data;

import java.util.List;

/**
 * @author Yazhe
 * Created at 11:42 2018/8/1
 */
@Data
public class BasicTreeNodeDTO {

	private String id;

	private List<BasicTreeNodeDTO> children;

	/**
	 * 该节点的值
	 */
	private String value;

	/**
	 * 层级
	 */
	private Integer level;

	/**
	 * 路径
	 */
	private String path;
}
