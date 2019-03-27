package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.WorkNature;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.ResultEnum;
import com.sicau.devicemanager.dao.DeviceMapper;
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
    @Autowired
    DeviceMapper deviceMapper;

    /**
     * 添加一条工作性质的记录
     * @author 蔡华庆
     * @param record 记录
     */
    @Override
    public void addWorkNature(WorkNature record) {
        record.setId(KeyUtil.genUniqueKey());
        workNatureMapper.insert(record);
    }

    /**
     * 查询所有工作性质的记录
     * @author 蔡华庆
     * @return
     */
    @Override
    public PageInfo<WorkNature> findAllWorkNature(QueryPage queryPage) {
        //分页查询
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), "id");
        return new PageInfo<>(workNatureMapper.listAll());
    }

    @Override
    public void deleteWordNatureById(String id) {
        if (!deviceMapper.selectDeviceByWorkNatureId(id).isEmpty()){
            throw new BusinessException("还存在设备是此工作性质，删除失败!");
        }
        workNatureMapper.deleteByPrimaryKey(id);
    }
}
