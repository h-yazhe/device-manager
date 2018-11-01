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

    /**
     * 根据id删除工作性质
     * @author Xiao W
     * @param id 工作性质id
     * @return
     */
    void deleteWordNatureById(String id);
}
