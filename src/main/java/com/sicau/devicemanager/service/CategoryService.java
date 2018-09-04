package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DTO.CategoryDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.CategoryVO;

import java.util.List;

/**
 * @author Yazhe
 * Created at 15:08 2018/8/7
 */
public interface CategoryService {

	/**
	 * 插入一个分类树
	 * @param locationList 节点列表
	 */
	void insertCategoryTree(List<Category> locationList);

	/**
	 * 删除该节点为根的树
	 * @param rootId 根节点id
	 */
	void deleteCategoryTree(String rootId);

	/**
	 * 更新分类树
	 * @param rootId 根节点
	 * @param locationList 节点列表
	 */
	void updateCategoryTree(String rootId,List<Category> locationList);

	/**
	 * 查询所有分类
	 * @return
	 */
	List<CategoryDTO> listCategoryTree();

	/**
	 * 查找pId的子节点
	 * @param categoryVO
	 * @return
	 */
	List<Category> listCategoryByPId(CategoryVO categoryVO);
}
