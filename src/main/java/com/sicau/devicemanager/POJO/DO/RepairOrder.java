package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AdminFinishOrder;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.UserFinishOrder;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ModifyRepairOrder;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.SubmitRepairOrder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class RepairOrder {

    @NotNull(message = "订单id不能为空", groups = {ModifyRepairOrder.class, AdminFinishOrder.class, UserFinishOrder.class})
    private Integer id;

    /**
     * 设备id
     */
    @NotNull(message = "设备id不能为空", groups = {SubmitRepairOrder.class})
    @Pattern(regexp = "^$|^\\d{19}$", message = "id只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String deviceId;

    /**
     * 设备状态
     */
    @NotNull(message = "设备状态不能为空", groups = {UserFinishOrder.class})
    private Integer deviceStatus;

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
    @NotNull(message = "问题描述不能为空", groups = {SubmitRepairOrder.class})
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5_0-9]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "description只能为汉字、英文字母、数字、下划线的组合")
    private String description;

    /**
     * 状态代码 0待维修，1维修中，2已维修，3无法维修
     */
    @NotNull(message = "订单状态不能为空", groups = {AdminFinishOrder.class})
    private Integer statusCode;

    /**
     * 预计维修时间
     */
    @NotNull(message = "预计时间不能为空", groups = {SubmitRepairOrder.class})
    private Date expectedTime;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

}