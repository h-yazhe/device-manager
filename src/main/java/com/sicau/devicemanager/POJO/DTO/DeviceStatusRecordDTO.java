package com.sicau.devicemanager.POJO.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sicau.devicemanager.POJO.DO.DeviceStatusRecord;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.GetDeviceStatusRecordByDeviceId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Yazhe
 * Created at 23:33 2018/9/28
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceStatusRecordDTO extends DeviceStatusRecord {

	/**
	 * 变更前的地点
	 */
	private String fromLocation;

	/**
	 * 变更后的地点
	 */
	private String toLocation;

	/**
	 * 操作用户的真实姓名
	 */
	private String operateUserRealName;

	@NotNull(groups = {GetDeviceStatusRecordByDeviceId.class})
	@Valid
	private QueryPage queryPage;

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getOperateUserRealName() {
		return operateUserRealName;
	}

	public void setOperateUserRealName(String operateUserRealName) {
		this.operateUserRealName = operateUserRealName;
	}

	public QueryPage getQueryPage() {
		return queryPage;
	}

	public void setQueryPage(QueryPage queryPage) {
		this.queryPage = queryPage;
	}

	@Override
	public String toString() {
		return "DeviceStatusRecordDTO{" +
				"fromLocation='" + fromLocation + '\'' +
				", toLocation='" + toLocation + '\'' +
				", operateUserRealName='" + operateUserRealName + '\'' +
				", queryPage=" + queryPage +
				"} " + super.toString();
	}
}
