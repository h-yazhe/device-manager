package com.sicau.devicemanager.POJO.DO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.UpdateDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddDeviceGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@ApiModel("设备")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

    @NotNull(groups = {UpdateDeviceGroup.class, DeviceValidatedGroup.UpdateRepairedStatusByDeviceId.class})
    @Pattern(regexp = "^$|^\\d{19}$", message = "id只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String id;

    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5_0-9]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "设备名只能为汉字、英文字母、数字、下划线的组合")
    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class}, message = "设备名不能为空")
    private String name;

    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    private String locationId;

    @Pattern(regexp = "^[0-9a-zA-Z]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "国资编号只能为英文字母、数字的组合")
    private String nationalId;

    @Pattern(regexp = "^[0-9a-zA-Z]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "序列号只能为英文字母、数字的组合")
    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    private String serialNumber;

    /**
     * 设备型号id
     */
    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    private Integer deviceModelId;

    private Date useTime;

    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    @Pattern(regexp = "^$|^\\d{19}$", message = "workNatureId只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String workNatureId;

    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    @Pattern(regexp = "\\d+", message = "custodianId只能是数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String custodianId;

    @NotNull(groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
    private BigDecimal unitPrice;

    /**
     * 计量单位id
     * 预留字段，暂不使用
     */
    private String amountUnitId;

    /**
     * 设备描述，用户输入，例如配置信息等
     */
    private String description;

    @NotNull(groups = {DeviceValidatedGroup.UpdateRepairedStatusByDeviceId.class})
    private Integer statusId;

    /**
     * 逻辑删除状态
     */
    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDeviceModelId() {
        return deviceModelId;
    }

    public void setDeviceModelId(Integer deviceModelId) {
        this.deviceModelId = deviceModelId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId == null ? null : locationId.trim();
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId == null ? null : nationalId.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public String getWorkNatureId() {
        return workNatureId;
    }

    public void setWorkNatureId(String workNatureId) {
        this.workNatureId = workNatureId == null ? null : workNatureId.trim();
    }

    public String getCustodianId() {
        return custodianId;
    }

    public void setCustodianId(String custodianId) {
        this.custodianId = custodianId == null ? null : custodianId.trim();
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAmountUnitId() {
        return amountUnitId;
    }

    public void setAmountUnitId(String amountUnitId) {
        this.amountUnitId = amountUnitId == null ? null : amountUnitId.trim();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(name, device.name) &&
                Objects.equals(nationalId, device.nationalId) &&
                Objects.equals(serialNumber, device.serialNumber) &&
                Objects.equals(deviceModelId, device.deviceModelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationalId, serialNumber, deviceModelId);
    }
}