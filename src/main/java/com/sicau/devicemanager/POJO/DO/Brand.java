package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.CommonValidatedGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.AddBrandGroup;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.QueryDeviceGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:22 2018/5/31
 */
@ApiModel("品牌")
@Data
public class Brand {

    @NotNull(groups = {QueryDeviceGroup.class}, message = "品牌id不能为空")
    @Pattern(regexp = "^$|^\\d{19}$", message = "id只能是19位的数字",groups = CommonValidatedGroup.LegalityGroup.class)
    private String id;

    /**
     * 品牌名
     */
    @ApiModelProperty("品牌名")
	@NotNull(groups = {AddBrandGroup.class},message = "品牌名称不能为空")
    @Pattern(regexp = "^[a-zA-Z\u4e00-\u9fa5_0-9]+$", groups = CommonValidatedGroup.LegalityGroup.class, message = "name只能为汉字、英文字母、数字、下划线的组合")
    private String name;
}
