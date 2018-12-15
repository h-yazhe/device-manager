package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.GetDeviceStatusRecordByDeviceId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 设备状态记录表
 * @author hyz
 */
public class DeviceStatusRecord {
    private String id;

    /**
     * 设备id
     */
    @NotNull(message = "设备id不能为空", groups = {GetDeviceStatusRecordByDeviceId.class})
    @Pattern(regexp = "\\d+", message = "deviceId只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    @Size(min = 19, max = 19, message = "deviceId只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String deviceId;

    /**
     * 本来的状态，若为-1，则表示新添加的设备
     */
    private Integer fromStatus;

    /**
     * 改变的状态
     */
    private Integer toStatus;

    /**
     * 本来的地点id
     */
    private String fromLocationId;

    /**
     * 变更的地点id
     */
    private String toLocationId;

    /**
     * 改变设备状态的操作时间
     */
    private Date operateTime;

    /**
     * 操作的用户
     */
    private String operateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(Integer fromStatus) {
        this.fromStatus = fromStatus;
    }

    public Integer getToStatus() {
        return toStatus;
    }

    public void setToStatus(Integer toStatus) {
        this.toStatus = toStatus;
    }

    public String getFromLocationId() {
        return fromLocationId;
    }

    public void setFromLocationId(String fromLocationId) {
        this.fromLocationId = fromLocationId;
    }

    public String getToLocationId() {
        return toLocationId;
    }

    public void setToLocationId(String toLocationId) {
        this.toLocationId = toLocationId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    public DeviceStatusRecord() {
    }

    public DeviceStatusRecord(String id, String deviceId,
                              Integer fromStatus, Integer toStatus,
                              String fromLocationId, String toLocationId, String operateUserId) {
        this.id = id;
        this.deviceId = deviceId;
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.fromLocationId = fromLocationId;
        this.toLocationId = toLocationId;
        this.operateUserId = operateUserId;
    }
}