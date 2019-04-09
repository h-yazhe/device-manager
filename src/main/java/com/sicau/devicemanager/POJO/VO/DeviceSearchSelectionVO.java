package com.sicau.devicemanager.POJO.VO;

import com.sicau.devicemanager.POJO.DO.*;
import com.sicau.devicemanager.POJO.DTO.UserDTO;
import lombok.Data;

import java.util.List;

/**
 * 设备搜索选项卡数据
 * @author Yazhe
 * Created at 17:15 2018/9/3
 */
@Data
public class DeviceSearchSelectionVO {

    private List<Category> categoryList;

    private List<Location> locationList;

    private List<Brand> brandList;

    private List<DeviceModel> deviceModelList;

    private List<WorkNature> workNatureList;

    private List<UserDTO> custodianList;
}
