package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.VO.CategoryVO;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.CategoryService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类
 * @author Yazhe
 * Created at 17:02 2018/7/31
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
@Api(tags = "分类")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
//
//	@ApiOperation("查询所有分类")
//	@ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
//	@GetMapping("category")
//	@RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.GET)
//	public ResultVO listCategoryTree(){
//		return ResultVOUtil.success(categoryService.listCategoryTree());
//	}

    @ApiOperation("根据父id查询分类")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("list-category-by-pId")
    @RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.GET)
    public ResultVO listCategoryByPId(@Validated({ListTreeByPId.class, CommonValidatedGroup.LegalityGroup.class}) @RequestBody CategoryVO categoryVO) {
        return ResultVOUtil.success(categoryService.listCategoryByPId(categoryVO));
    }

    @ApiOperation("删除该节点为根的树")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("delete-category-by-id/{rootId}")
    @RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.DELETE)
    public ResultVO deleteCategoryTree(@PathVariable("rootId") String rootId) {
        categoryService.deleteCategoryTree(rootId);
        return ResultVOUtil.success();
    }

    @ApiOperation("更新分类树")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PutMapping("category/{rootId}")
    @RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.UPDATE)
    public ResultVO updateCategoryTree(@PathVariable String rootId, @RequestBody List<Category> categoryList) {
        categoryService.updateCategoryTree(rootId, categoryList);
        return ResultVOUtil.success();
    }

    @ApiOperation("插入一个分类树")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("category")
    @RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.ADD)
    public ResultVO insertCategoryTree(@RequestBody List<Category> categoryList) {
        categoryService.insertCategoryTree(categoryList);
        return ResultVOUtil.success();
    }

    /**
     * 根据父id插入一个子分类
     * @param category
     * @return
     */
    @PostMapping("insert-category-by-pid")
    @RequiresPermissions(ResourceConstants.LOCATION + PermissionActionConstant.ADD)
    public ResultVO insertCategoryByPId(@RequestBody Category category) {
        categoryService.insertCategoryByPId(category);
        return ResultVOUtil.success();
    }
}
