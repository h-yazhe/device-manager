package com.sicau.devicemanager.POJO.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yazhe
 * Created at 15:17 2019/4/10
 */
@Data
@JsonInclude(Include.NON_NULL)
public class DeviceVO {

	private String id;

	private String name;

	private String locationId;

	private String nationalId;

	private String serialNumber;

	private Integer deviceModelId;

	private Date useTime;

	private String workNatureId;

	private String custodianId;

	private BigDecimal unitPrice;

	/**
	 * 设备描述，用户输入，例如配置信息等
	 */
	private String description;

	private Integer statusId;

	private Date createTime;

	private Date updateTime;
}
