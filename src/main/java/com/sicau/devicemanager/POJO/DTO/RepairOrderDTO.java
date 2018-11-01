package com.sicau.devicemanager.POJO.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: 郭效坤
 * @Date: 2018/10/18 20:58
 * @Description: 设备报修订单的信息传递类，用作分页查询
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepairOrderDTO {

    /**
     * 订单id
     */
    private Integer id;

    /**
     * 设备id
     */
    @NotNull(message = "设备id不能为空", groups = {DeviceValidatedGroup.SubmitRepairOrder.class})
    private String deviceId;

    /**
     * 申请人id
     */
    private String applyUserId;

    /**
     * 处理该订单的用户id
     */
    private String dealUserId;

    /**
     * 设备问题描述
     */
    @NotNull(message = "问题描述不能为空", groups = {DeviceValidatedGroup.SubmitRepairOrder.class})
    private String description;

    /**
     * 状态代码 0待维修，1维修中，2已维修，3无法维修
     */
    private Integer statusCode;

    /**
     * 预计维修时间
     */
    @NotNull(message = "预计时间不能为空", groups = {DeviceValidatedGroup.SubmitRepairOrder.class})
    private Date expectedTime;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date createTime;

    /**
     * 订单更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    private Date updateTime;

    @Valid
    @NotNull(groups = {DeviceValidatedGroup.QueryDeviceGroup.class}, message = "分页参数不能为空")
    private QueryPage queryPage;

}
