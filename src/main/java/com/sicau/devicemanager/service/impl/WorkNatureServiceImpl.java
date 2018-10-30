package com.sicau.devicemanager.service.impl;

import com.sicau.devicemanager.POJO.DO.WorkNature;
import com.sicau.devicemanager.dao.WorkNatureMapper;
import com.sicau.devicemanager.service.WorkNatureService;
import com.sicau.devicemanager.util.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By Chq ${Date}
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class WorkNatureServiceImpl implements WorkNatureService {

    @Autowired
    WorkNatureMapper workNatureMapper;

    /**
     * 添加一条工作性质的记录
     * @param record 记录
     */
    @Override
    public void addWorkNature(WorkNature record) {
        record.setId(KeyUtil.genUniqueKey());
        workNatureMapper.insert(record);
    }

    /**
     * 查询所有工作性质的记录
     * @return
     */
    @Override
    public List<WorkNature> findAllWorkNature() {
        return workNatureMapper.listAll();
    }
}
