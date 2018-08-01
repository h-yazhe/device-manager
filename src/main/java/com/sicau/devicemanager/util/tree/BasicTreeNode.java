package com.sicau.devicemanager.util.tree;

import lombok.Data;

/**
 * 树的节点
 * @author Yazhe
 * Created at 11:03 2018/8/1
 */
@Data
public class BasicTreeNode {

	private String id;

	private String parentId;

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
