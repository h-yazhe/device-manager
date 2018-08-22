package com.sicau.devicemanager.POJO.DO;

import java.util.Date;

public class DeviceStatusRecord {
    private String id;

    private String deviceId;

    private Integer fromStatus;

    private Integer toStatus;

    private Date operateTime;

    private String operateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
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
        this.operateUserId = operateUserId == null ? null : operateUserId.trim();
    }

	public DeviceStatusRecord() {
	}

	public DeviceStatusRecord(String id, String deviceId, Integer fromStatus, Integer toStatus, String operateUserId) {
		this.id = id;
		this.deviceId = deviceId;
		this.fromStatus = fromStatus;
		this.toStatus = toStatus;
		this.operateUserId = operateUserId;
	}
}