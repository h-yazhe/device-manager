package com.sicau.devicemanager.util.tree;

import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 树操作的工具类
 * @author Yazhe
 * Created at 11:07 2018/8/1
 */
public class TreeUtil {

	private TreeDao treeDao;

	/**
	 * @param treeDao 树在数据库操作的接口
	 */
	public TreeUtil(TreeDao treeDao) {
		this.treeDao = treeDao;
	}

	public void insert(List<? extends BasicTreeNode> nodeList){
		//根节点数量
		int rootCount = 0;
		for (BasicTreeNode node :
				nodeList) {
			//查找根节点
			if (node.getParentId() == null){
				rootCount++;
				//初始化根节点
				node.setLevel(1);
				node.setPath("/");
				//设置节点的属性
				setBasicTreeNodeInfo(node,nodeList);
			}
		}
		if (rootCount == 0){
			throw new RuntimeException("根节点数量不能为0");
		}
		treeDao.insertBasicTreeNodeList(nodeList);
	}

	/**
	 * 设置当前节点极其子孙节点的主键，level和path
	 * @param node 当前地点
	 * @param nodeList 地点树的节点集合
	 */
	private void setBasicTreeNodeInfo(BasicTreeNode node,List<? extends BasicTreeNode> nodeList){
		//查找子节点
		List<BasicTreeNode> childs = new ArrayList<>();
		for (BasicTreeNode child : nodeList){
			if (node.getId().equals(child.getParentId())){
				childs.add(child);
			}
		}
		//生成主键
		node.setId(KeyUtil.genUniqueKey());
		//再继续设置子节点的属性，没有子节点时跳出递归
		for (BasicTreeNode child:childs){
			child.setParentId(node.getId());
			child.setLevel(node.getLevel() + 1);
			//拼接path
			child.setPath(node.getPath() + node.getId() + "/");
			setBasicTreeNodeInfo(child,nodeList);
		}
	}

	public void deleteTree(String rootId) {
		for (String childId: treeDao.getChildrenIdById(rootId)){
			deleteTree(childId);
			treeDao.deleteNodeById(childId);
		}
		treeDao.deleteNodeById(rootId);
	}

	public void updateLocationTree(String rootId, List<? extends BasicTreeNode> nodeList) {
		deleteTree(rootId);
		insert(nodeList);
	}

	public List<BasicTreeNodeDTO> listLocationTree() {
		List<BasicTreeNode> nodeList = treeDao.getNodeList();
		List<BasicTreeNodeDTO> nodeDTOList = new ArrayList<>();
		for (BasicTreeNode node :
				nodeList) {
			//查找根节点
			if (node.getParentId() == null){
				nodeDTOList.add(createChildren(node,nodeList));
			}
		}
		return nodeDTOList;
	}

	/**
	 * 由当前节点生成子节点的DTO
	 * @param root 当前节点
	 * @param nodeList 所有节点
	 * @return 根节点
	 */
	private BasicTreeNodeDTO createChildren(BasicTreeNode root, List<? extends BasicTreeNode> nodeList){
		//生成根节点DTO
		BasicTreeNodeDTO rootDTO = new BasicTreeNodeDTO();
		BeanUtils.copyProperties(root,rootDTO);
		//查找子节点
		for (BasicTreeNode node : nodeList){
			//查找到了子节点
			if (root.getId().equals(node.getParentId())){
				//懒创建子节点的列表
				if (rootDTO.getChildren() == null){
					rootDTO.setChildren(new ArrayList<>());
				}
				rootDTO.getChildren().add(createChildren(node,nodeList));
			}
		}
		//返回根节点
		return rootDTO;
	}
}
