package com.sicau.devicemanager.POJO.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sicau.devicemanager.POJO.DO.Brand;
import com.sicau.devicemanager.POJO.DO.Category;
import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.QueryDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.UpdateDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddDeviceGroup;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Yazhe
 * Created at 17:17 2018/8/7
 */
@JsonInclude(Include.NON_NULL)
public class DeviceDTO extends Device {

	/**
	 * 品牌
	 */
	@NotNull(message = "品牌不能为空",groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
	@ApiModelProperty("设备品牌")
	@Valid
	private Brand brand;

	/**
	 * 分类id
	 */
	@NotNull(message = "分类不能为空",groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
	@NotEmpty(message = "分类不能为空",groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
	private List<String> categoryId;

	@ApiModelProperty(value = "所处地点")
	private String location;

	@ApiModelProperty(value = "设备分类")
	private String category;

	@ApiModelProperty("工作性质")
	private String workNature;

	@ApiModelProperty("保管人")
	private String custodian;

	@ApiModelProperty("计量单位")
	private String amountUnit;

	@ApiModelProperty("设备状态")
	private String status;

	@ApiModelProperty("入库的开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date startTime;

	@ApiModelProperty("入库的结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
	private Date endTime;

	@Valid
	@NotNull(groups = {QueryDeviceGroup.class},message = "分页参数不能为空")
	private QueryPage queryPage;

	@ApiModelProperty("模糊查询的key")
	private String queryKey;

	@ApiModelProperty("地点列表")
	private List<Location> locationList;

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public QueryPage getQueryPage() {
		return queryPage;
	}

	public void setQueryPage(QueryPage queryPage) {
		this.queryPage = queryPage;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWorkNature() {
		return workNature;
	}

	public void setWorkNature(String workNature) {
		this.workNature = workNature;
	}

	public String getCustodian() {
		return custodian;
	}

	public void setCustodian(String custodian) {
		this.custodian = custodian;
	}

	public String getAmountUnit() {
		return amountUnit;
	}

	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "DeviceDTO{" +
				"brand=" + brand +
				", categoryId=" + categoryId +
				", location='" + location + '\'' +
				", category='" + category + '\'' +
				", workNature='" + workNature + '\'' +
				", custodian='" + custodian + '\'' +
				", amountUnit='" + amountUnit + '\'' +
				", status='" + status + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				", queryPage=" + queryPage +
				", queryKey='" + queryKey + '\'' +
				", locationList=" + locationList +
				"} " + super.toString();
	}
}
