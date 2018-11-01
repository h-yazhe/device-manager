package com.sicau.devicemanager.POJO.DO;

import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.QueryDeviceGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:22 2018/5/31
 */
@ApiModel("品牌")
@Data
public class Brand {

    @NotNull(groups = {QueryDeviceGroup.class}, message = "品牌id不能为空")
    private String id;

    /**
     * 品牌名
     */
    @ApiModelProperty("品牌名")
    private String name;
}
