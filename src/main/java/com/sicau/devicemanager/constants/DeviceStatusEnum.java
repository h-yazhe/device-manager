package com.sicau.devicemanager.constants;

/**
 * 设备状态
 * @author Yazhe
 * Created at 16:22 2018/9/3
 */
public enum DeviceStatusEnum {

	UNCONNECTED(-1,"未接入"),

	IN_STORAGE(1,"入库"),
	USING(2,"使用中"),
	DISCARDED(3,"报废")
	;

	/**
	 * 状态码
	 */
	private int code;

	/**
	 * 中文释义，可用于前端展示
	 */
	private String value;

	DeviceStatusEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int getCode() {
		return code;
	}
}
