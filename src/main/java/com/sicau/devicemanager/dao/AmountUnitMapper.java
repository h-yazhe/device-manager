package com.sicau.devicemanager.dao;

import com.sicau.devicemanager.POJO.DO.AmountUnit;

public interface AmountUnitMapper {
    int deleteByPrimaryKey(String id);

    int insert(AmountUnit record);

    int insertSelective(AmountUnit record);

    AmountUnit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AmountUnit record);

    int updateByPrimaryKey(AmountUnit record);
}