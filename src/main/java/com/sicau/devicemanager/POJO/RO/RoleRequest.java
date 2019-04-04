package com.sicau.devicemanager.POJO.RO;

import com.sicau.devicemanager.config.validation.group.ValidationGroup.RoleIdGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Yazhe
 * Created at 13:38 2019/4/4
 */
@Data
public class RoleRequest {

	@NotNull(groups = {RoleIdGroup.class})
	private String roleId;
}
