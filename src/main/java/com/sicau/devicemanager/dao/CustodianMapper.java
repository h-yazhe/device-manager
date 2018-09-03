package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.Custodian;

import java.util.List;

public interface CustodianMapper {
    int deleteByPrimaryKey(String id);

    int insert(Custodian record);

    int insertSelective(Custodian record);

    Custodian selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Custodian record);

    int updateByPrimaryKey(Custodian record);

    List<Custodian> listAll();
}