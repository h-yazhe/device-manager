package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.Brand;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.constants.HttpParamKey;
import com.sicau.devicemanager.constants.PermissionActionConstant;
import com.sicau.devicemanager.constants.ResourceConstants;
import com.sicau.devicemanager.service.BrandService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ApiOperation("查询所有品牌")
    @ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
    @GetMapping(ResourceConstants.BRAND)
    @RequiresPermissions(ResourceConstants.BRAND + PermissionActionConstant.GET)
    public ResultVO listBrand(){
        return ResultVOUtil.success(brandService.listBrand());
    }

    @ApiOperation("新增品牌")
    @ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
    @PostMapping(ResourceConstants.BRAND)
    public ResultVO insertBrand(Brand brand){
        brandService.insertBrand(brand);
        return ResultVOUtil.success();
    }

    @ApiOperation("根据id删除品牌")
    @ApiImplicitParam(name = HttpParamKey.TOKEN,required = true, paramType = "header")
    @DeleteMapping(ResourceConstants.BRAND + "/{id}")
    public ResultVO deleteBrandById(@PathVariable String id){
        brandService.deleteBrandById(id);
        return ResultVOUtil.success();
    }
}
