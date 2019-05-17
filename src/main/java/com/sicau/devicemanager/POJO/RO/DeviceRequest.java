package com.sicau.devicemanager.POJO.RO;

import com.sicau.devicemanager.config.validation.group.ValidationGroup.DeviceIdGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Yazhe
 * Created at 15:12 2019/4/10
 */
@Data
public class DeviceRequest {

	/**
	 * 设备id
	 */
	@NotNull(groups = {DeviceIdGroup.class})
	private String id;
}
