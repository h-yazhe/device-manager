package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Brand;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddBrandGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.GetBrandsGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.BrandService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:16 2018/5/31
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX)
@Api(tags = "品牌")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 分页查询品牌
     * @param queryPage
     * @return
     */
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("get-brands")
    @RequiresPermissions(ResourceConstants.BRAND + PermissionActionConstant.GET)
    public ResultVO listBrandByPage(@Validated(GetBrandsGroup.class) @RequestBody QueryPage queryPage) {
        return ResultVOUtil.success(brandService.listBrandByPage(queryPage));
    }

    @ApiOperation("新增品牌")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping(ResourceConstants.BRAND)
	@RequiresPermissions(ResourceConstants.BRAND + PermissionActionConstant.ADD)
    public ResultVO insertBrand(@Validated({AddBrandGroup.class, CommonValidatedGroup.LegalityGroup.class}) @RequestBody Brand brand) {
        brandService.insertBrand(brand);
        return ResultVOUtil.success();
    }

    @ApiOperation("根据id删除品牌")
    @ApiImplicitParam(name = HttpParamKey.TOKEN, required = true, paramType = "header")
    @PostMapping("delete-brand/{id}")
	@RequiresPermissions(ResourceConstants.BRAND + PermissionActionConstant.DELETE)
    public ResultVO deleteBrandById(@PathVariable String id) {
        
        brandService.deleteBrandById(id);
        return ResultVOUtil.success();
    }
}
