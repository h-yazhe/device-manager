package com.sicau.devicemanager.POJO.VO;

import com.sicau.devicemanager.POJO.DO.Location;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.config.validation.group.DeviceValidatedGroup.ListTreeByPId;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Yazhe
 * Created at 17:17 2018/9/4
 */
public class LocationVO extends Location {

    @NotNull(message = "分页参数不能为空", groups = {ListTreeByPId.class})
    @Valid
    private QueryPage queryPage;

    public QueryPage getQueryPage() {
        return queryPage;
    }

    public void setQueryPage(QueryPage queryPage) {
        this.queryPage = queryPage;
    }

    @Override
    public String toString() {
        return "LocationVO{" +
                "queryPage=" + queryPage +
                "} " + super.toString();
    }
}
