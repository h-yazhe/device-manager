package com.sicau.devicemanager.POJO.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.UpdateDeviceGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddDeviceGroup;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Yazhe
 * Created at 17:17 2018/8/7
 */
@JsonInclude(Include.NON_NULL)
public class DeviceDTO extends Device {

	/**
	 * 品牌id
	 */
	@NotNull(message = "品牌不能为空",groups = {AddDeviceGroup.class, UpdateDeviceGroup.class})
	private String brandId;

	/**
	 * 分类id
	 */
	@NotNull(message = "分类不能为空",groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
	@NotEmpty(message = "分类不能为空",groups = {AddDeviceGroup.class,UpdateDeviceGroup.class})
	private List<String> categoryId;

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public List<String> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}
}
