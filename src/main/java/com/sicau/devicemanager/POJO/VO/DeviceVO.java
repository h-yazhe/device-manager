package com.sicau.devicemanager.POJO.VO;

import com.sicau.devicemanager.POJO.DO.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Yazhe
 * Created at 16:17 2018/8/7
 */
@ApiModel("设备")
@Data
public class DeviceVO {

	private String id;

	@ApiModelProperty(value = "所处地点",notes = "左侧的为右侧的父节点，最后一个即为当前节点")
	private List<Location> locationList;

	@ApiModelProperty("国资编号")
	private String nationalId;

	@ApiModelProperty("序列号")
	private String serialNumber;

	@ApiModelProperty("领用时间")
	private Date useTime;

	@ApiModelProperty("工作性质")
	private WorkNature workNature;

	@ApiModelProperty("保管人")
	private Custodian custodian;

	@ApiModelProperty("单价")
	private BigDecimal unitPrice;

	@ApiModelProperty("计量单位id")
	private AmountUnit amountUnit;

	@ApiModelProperty("当前设备状态id")
	private Status status;

	@ApiModelProperty("创建时间，即第一次入库的时间")
	private Date createTime;

	@ApiModelProperty("更新时间")
	private Date updateTime;

	@ApiModelProperty("设备品牌")
	private Brand brand;

	@ApiModelProperty(value = "设备分类",notes = "左侧的为右侧的父节点，最后一个即为当前节点")
	private List<Category> categoryList;
}
