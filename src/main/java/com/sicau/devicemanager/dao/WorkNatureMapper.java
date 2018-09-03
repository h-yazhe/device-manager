package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.WorkNature;

import java.util.List;

public interface WorkNatureMapper {
    int deleteByPrimaryKey(String id);

    int insert(WorkNature record);

    int insertSelective(WorkNature record);

    WorkNature selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkNature record);

    int updateByPrimaryKey(WorkNature record);

    List<WorkNature> listAll();
}