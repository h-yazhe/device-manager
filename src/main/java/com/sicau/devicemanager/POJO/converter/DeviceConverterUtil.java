package com.sicau.devicemanager.POJO.converter;

import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.VO.DeviceVO;
import org.springframework.beans.BeanUtils;

/**
 * 设备模型转换工具
 * @author Yazhe
 * Created at 17:40 2019/4/10
 */
public class DeviceConverterUtil {

	/**
	 * do转vo
	 * @param device
	 * @return
	 */
	public static DeviceVO convertDo2Vo(Device device) {
		DeviceVO deviceVO = new DeviceVO();
		BeanUtils.copyProperties(device,deviceVO);
		return deviceVO;
	}
}
