package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ModifyRepairOrder;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.SubmitRepairOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RepairOrder {

    @NotNull(message = "订单id不能为空",groups = {ModifyRepairOrder.class})
    private Integer id;

    /** 设备id */
    @NotNull(message = "设备id不能为空",groups = {SubmitRepairOrder.class})
    private String deviceId;

    /** 申请人id */
    private String applyUserId;

    /** 处理该订单的用户id */
    private String dealUserId;

    /** 设备问题描述 */
    @NotNull(message = "问题描述不能为空",groups = {SubmitRepairOrder.class})
    private String description;

    /** 状态代码 0待维修，1维修中，2已维修，3无法维修*/
    private Integer statusCode;

    /** 预计维修时间 */
    @NotNull(message = "预计时间不能为空",groups = {SubmitRepairOrder.class})
    private Date expectedTime;

    /** 订单创建时间 */
    private Date createTime;

    /** 订单更新时间 */
    private Date updateTime;

}