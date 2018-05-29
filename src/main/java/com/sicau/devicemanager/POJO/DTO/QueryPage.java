package com.sicau.devicemanager.POJO.DTO;

import lombok.Data;

/**
 * 分页查询的参数
 * @author BeFondOfTaro
 * Created at 11:32 2018/5/15
 */
@Data
public class QueryPage {

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数量
     */
    private Integer pageSize = 10;
}
