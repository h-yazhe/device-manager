package com.sicau.devicemanager.service;

import com.sicau.devicemanager.POJO.DO.WorkNature;

import java.util.List;

/**
 * Created By Chq
 */
public interface WorkNatureService {
    /**
     * 添加工作性质记录
     * @param record 记录
     */
    void addWorkNature(WorkNature record);

    /**
     * 查询所有工作性质的记录
     */
    List<WorkNature> findAllWorkNature();
}
