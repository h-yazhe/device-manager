package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.Brand;

import java.util.List;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:29 2018/5/31
 */
public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> listBrand();

    /**
     * 新增品牌
     * @param brand 品牌信息
     * @return
     */
    void insertBrand(Brand brand);

    /**
     * 根据id删除品牌
     * @param id 品牌id
     * @return
     */
    void deleteBrandById(String id);
}
