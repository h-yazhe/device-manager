package com.sicau.devicemanager.POJO.DO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 品牌
 * @author BeFondOfTaro
 * Created at 12:22 2018/5/31
 */
@ApiModel("品牌")
@Data
public class Brand {

    private String id;

    /**
     * 品牌名
     */
    @ApiModelProperty("品牌名")
    private String name;
}
