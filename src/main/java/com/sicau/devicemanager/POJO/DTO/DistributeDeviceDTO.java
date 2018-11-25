package com.sicau.devicemanager.POJO.DTO;

import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.DistributeDeviceGroup;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 分发设备
 * @author Yazhe
 * Created at 11:09 2018/8/22
 */
public class DistributeDeviceDTO {

    @NotNull(groups = {DistributeDeviceGroup.class}, message = "设备列表不能为空")
    private List<String> deviceIdList;

    /**
     * 分发地点id
     */
    @NotNull(groups = {DistributeDeviceGroup.class}, message = "分发的地点不能为空")
    private String locationId;

    /**
     * 领用时间
     */
    private Date useTime;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public List<String> getDeviceIdList() {
        return deviceIdList;
    }

    public void setDeviceIdList(List<String> deviceIdList) {
        this.deviceIdList = deviceIdList;
    }

    @Override
    public String toString() {
        return "DistributeDeviceDTO{" +
                "deviceIdList=" + deviceIdList +
                ", locationId='" + locationId + '\'' +
                ", useTime=" + useTime +
                '}';
    }
}
