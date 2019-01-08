package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.Brand;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.exception.CommonException;
import com.sicau.devicemanager.dao.BrandMapper;
import com.sicau.devicemanager.service.BrandService;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BeFondOfTaro
 * Created at 12:31 2018/5/31
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> listBrand() {
        return brandMapper.listBrand();
    }

    @Override
    public PageInfo<Brand> listBrandByPage(QueryPage queryPage) {
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize());
        return new PageInfo<>(brandMapper.listBrand());
    }

    @Override
    public void insertBrand(Brand brand) {
    	if (brandMapper.getIdByName(brand.getName()) != null){
    		throw new CommonException("名称已存在！");
		}
        brand.setId(KeyUtil.genUniqueKey());
        brandMapper.insertBrand(brand);
    }

    @Override
    public void deleteBrandById(String id) {
        brandMapper.deleteBrandById(id);
    }
}
