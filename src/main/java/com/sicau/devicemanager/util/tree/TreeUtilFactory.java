package com.sicau.devicemanager.util.tree;

/**
 * @author Yazhe
 * Created at 16:44 2018/8/9
 */
public class TreeUtilFactory {

	public static TreeUtil getTreeUtil(TreeDao treeDao, Class<BasicTreeNodeDTO> nodeDTOClass){
		TreeUtil treeUtil = new TreeUtil(treeDao);
		return treeUtil;
	}
}
