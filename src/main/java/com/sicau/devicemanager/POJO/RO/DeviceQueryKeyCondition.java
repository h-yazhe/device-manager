package com.sicau.devicemanager.POJO.RO;

import lombok.Data;

/**
 * 设备模糊查询字段列举，仅当值为true时查询该字段
 * @author Yazhe
 * Created at 12:31 2019/3/2
 */
@Data
public class DeviceQueryKeyCondition {

	/**
	 * 设备id
	 */
	private Boolean deviceId;

	/**
	 * 设备名
	 */
	private Boolean deviceName;

	/**
	 * 序列号
	 */
	private Boolean serialNumber;

	private Boolean locationName;
}
