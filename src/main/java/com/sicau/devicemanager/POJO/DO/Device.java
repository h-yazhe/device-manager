package com.sicau.devicemanager.POJO.DO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.UpdateDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddDeviceGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("设备")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Device {

	@NotNull(groups = {UpdateDeviceGroup.class})
    private String id;

	@ApiModelProperty("设备名")
	private String name;

    @ApiModelProperty("所处地点id")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String locationId;

    @ApiModelProperty("国资编号")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String nationalId;

    @ApiModelProperty("序列号")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String serialNumber;

    @ApiModelProperty("领用时间")
    private Date useTime;

    @ApiModelProperty("工作性质id")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String workNatureId;

    @ApiModelProperty("保管人id")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String custodianId;

    @ApiModelProperty("单价")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private BigDecimal unitPrice;

    @ApiModelProperty("计量单位id")
	@NotNull(groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
    private String amountUnitId;

    @ApiModelProperty("当前设备状态id")
    private String statusId;

    @ApiModelProperty("创建时间，即第一次入库的时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;


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

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
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
}