package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:21 2018/5/31
 */
public interface BrandMapper {

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
    int insertBrand(Brand brand);

    /**
     * 根据id删除品牌
     * @param id 品牌id
     * @return
     */
    int deleteBrandById(@Param("id") String id);

	/**
	 * 根据名称查询id
	 * @param name
	 * @return
	 */
	String getIdByName(@Param("name") String name);
}
