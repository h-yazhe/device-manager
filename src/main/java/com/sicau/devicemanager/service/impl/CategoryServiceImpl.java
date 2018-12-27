package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DO.Role;
import com.sicau.devicemanager.POJO.DO.RoleCategory;
import com.sicau.devicemanager.POJO.DTO.CategoryDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import com.sicau.devicemanager.POJO.VO.CategoryVO;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.CategoryMapper;
import com.sicau.devicemanager.dao.DeviceCategoryMapper;
import com.sicau.devicemanager.dao.RoleCategoryMapper;
import com.sicau.devicemanager.service.CategoryService;
import com.sicau.devicemanager.service.UserService;
import com.sicau.devicemanager.util.KeyUtil;
import com.sicau.devicemanager.util.web.RequestUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yazhe
 * Created at 15:10 2018/8/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DeviceCategoryMapper deviceCategoryMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleCategoryMapper roleCategoryMapper;

    @Override
    public void insertCategoryTree(List<Category> categoryList) {
        //根节点数量
        int rootCount = 0;
        for (Category category :
                categoryList) {
            //查找根节点
            if ("".equals(category.getParentId())) {
                rootCount++;
                //初始化根节点
                category.setLevel(1);
                category.setPath("/");
                //设置节点的属性
                setCategoryInfo(category, categoryList);
            }
        }
        if (rootCount == 0) {
            throw new RuntimeException("根节点数量不能为0");
        }
        categoryMapper.insertList(categoryList);
    }

    /**
     * 设置当前节点极其子孙节点的主键，level和path
     * @param category     当前地点
     * @param categoryList 地点树的节点集合
     */
    private void setCategoryInfo(Category category, List<Category> categoryList) {
        //查找子节点
        List<Category> childs = new ArrayList<>();
        for (Category child : categoryList) {
            if (category.getId().equals(child.getParentId())) {
                childs.add(child);
            }
        }
        //生成主键
        category.setId(KeyUtil.genUniqueKey());
        //再继续设置子节点的属性，没有子节点时跳出递归
        for (Category child : childs) {
            child.setParentId(category.getId());
            child.setLevel(category.getLevel() + 1);
            //拼接path
            child.setPath(category.getPath() + category.getId() + "/");
            setCategoryInfo(child, categoryList);
        }
    }

    @Override
    public void deleteCategoryTree(String rootId) {
        //该分类下的所有设备全部转移到默认分类下
        List<String> categoryIds = new ArrayList<>();
        categoryMapper.getDescendants(rootId).forEach(category -> {
            categoryIds.add(category.getId());
        });
        categoryIds.add(rootId);
        deviceCategoryMapper.updateCategoryIdInCategoryIds("0", categoryIds);
        //删除分类
        categoryMapper.deleteByIds(categoryIds);
    }

    @Override
    public void updateCategoryTree(String rootId, List<Category> categoryList) {
        deleteCategoryTree(rootId);
        insertCategoryTree(categoryList);
    }

    @Override
    public List<CategoryDTO> listCategoryTree() {
        List<Category> categoryList = categoryMapper.selectAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category :
                categoryList) {
            //查找根节点
            if (category.getParentId() == null) {
                categoryDTOList.add(createChildren(category, categoryList));
            }
        }
        return categoryDTOList;
    }

    @Override
    public List<Category> listCategoryByPId(CategoryVO categoryVO) {
        int flag=0;
        QueryPage queryPage = categoryVO.getQueryPage();
        UserDTO user = userService.getUserById(RequestUtil.getCurrentUserId());
        String pid=categoryVO.getParentId();
        List<String> roleIds=new ArrayList<>();
        List<String> categoryIds= new ArrayList<>();
        List<Category> categoryList;
        List<RoleCategory> roleCategories;

        for (Role role:user.getRoleList()){
            roleIds.add(role.getId());
        }

        roleCategories=roleCategoryMapper.selectByRoleIds(roleIds);

        for (RoleCategory roleCategory:roleCategories){
            categoryIds.add(roleCategory.getCategoryId());
        }

        categoryList=categoryMapper.getAllChildIdByIds(categoryIds);

        for (Category location:categoryList){
            if (location.getId().equals(pid)){
                flag=1;
                break;
            }
        }

        if (flag==0){
            throw new CommonException(ResultEnum.UNAUTHORIZED);
        }

        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), "name");
        return categoryMapper.getChildrenById(categoryVO.getParentId());
    }

    @Override
    public void insertCategoryByPId(Category category) {
        List<Category> categories = new ArrayList<>(1);
        category.setId(KeyUtil.genUniqueKey());
        //根据父节点信息设置当前节点其他信息
        if (category.getParentId().isEmpty()) {
            category.setPath("/");
            category.setLevel(1);
        } else {
            Category pCategory = categoryMapper.getById(category.getParentId());
            category.setLevel(pCategory.getLevel() + 1);
            category.setPath(pCategory.getPath() + pCategory.getId() + "/");
        }
		categories.add(category);
        categoryMapper.insertList(categories);
    }

    /**
     * 由当前节点生成子节点的DTO
     * @param root         当前节点
     * @param categoryList 所有节点
     * @return 根节点
     */
    private CategoryDTO createChildren(Category root, List<Category> categoryList) {
        //生成根节点DTO
        CategoryDTO rootDTO = new CategoryDTO();
        BeanUtils.copyProperties(root, rootDTO);
        //查找子节点
        for (Category category : categoryList) {
            //查找到了子节点
            if (root.getId().equals(category.getParentId())) {
                //懒创建子节点的列表
                if (rootDTO.getChildren() == null) {
                    rootDTO.setChildren(new ArrayList<>());
                }
                //添加子节点
                rootDTO.getChildren().add(createChildren(category, categoryList));
            }
        }
        //返回根节点
        return rootDTO;
    }
}
