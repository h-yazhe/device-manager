package com.sicau.devicemanager.controller;

import com.sicau.devicemanager.POJO.DO.WorkNature;
import com.sicau.devicemanager.POJO.VO.ResultVO;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.constants.CommonConstants;
import com.sicau.devicemanager.service.WorkNatureService;
import com.sicau.devicemanager.util.web.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Chq
 */
@RestController
@RequestMapping(CommonConstants.API_PREFIX + "/work_nature")
public class WorkNatureController {
    @Autowired
    WorkNatureService workNatureService;

    /**
     * 添加一条工作性质的记录
     * @param workNature
     * @return
     */
    @PostMapping("/add")
    public ResultVO addWorkNature(@Validated({DeviceValidatedGroup.addWorkNature.class})
                                  @RequestBody WorkNature workNature) {
        workNatureService.addWorkNature(workNature);
        return ResultVOUtil.success();
    }

    /**
     * 查询所有工作性质的记录
     * @return
     */
    @PostMapping("/listAll")
    public ResultVO listAllWorkNature() {
        return ResultVOUtil.success(workNatureService.findAllWorkNature());
    }

    /**
     * 根据id删除工作性质
     * @return
     * @author Xiao W
     */
    @PostMapping("/delete/{id}")
    public ResultVO deleteById(@PathVariable String id) {
        workNatureService.deleteWordNatureById(id);
        return ResultVOUtil.success();
    }
}
