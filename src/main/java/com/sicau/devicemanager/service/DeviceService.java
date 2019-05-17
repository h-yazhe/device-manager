package com.sicau.devicemanager.service;

import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DeviceStatusRecordDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.RO.DeviceRequest;
import com.sicau.devicemanager.POJO.VO.DeviceSearchSelectionVO;
import com.sicau.devicemanager.POJO.VO.DeviceVO;
import com.sicau.devicemanager.config.exception.VerificationException;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * 设备服务
 * @author Yazhe
 * Created at 17:30 2018/8/7
 */
public interface DeviceService {

    /**
     * 添加设备
     * @param deviceDTO 设备信息
     */
    void addDevice(DeviceDTO deviceDTO);

    /**
     * 批量添加设备
     * @param inputStream 包含设备信息的输入流
     */
    void addDeviceList(InputStream inputStream) throws Exception;

    /**
     * 通过设备id更新设备信息
     * @param deviceDTO 设备信息
     */
    void updateDeviceById(DeviceDTO deviceDTO);

    /**
     * 根据设备id删除设备
     * @param ids id列表
     */
    void deleteDeviceById(List<String> ids);

    /**
     * 根据条件查询设备信息
     * @param deviceDTO
     * @return
     */
    PageInfo<DeviceDTO> listDevice(DeviceDTO deviceDTO) throws VerificationException;

	/**
	 * 根据设备id查询设备详细信息
	 * @param deviceRequest
	 * @return
	 */
	DeviceVO getDeviceDetailById(DeviceRequest deviceRequest);

    /**
     * 分发设备
     * @param distributeDeviceDTO
     */
    void distributeDevice(DistributeDeviceDTO distributeDeviceDTO);

    /**
     * 废弃设备
     * @param deviceId
     */
    void discardDevice(String deviceId);

    /**
     * 获取搜索的选项卡数据
     * @param pageSize 每页数量
     * @return
     */
    DeviceSearchSelectionVO getSearchSelections(int pageSize);

    /**
     * 根据设备id查询设备状态变更记录
     * @param deviceStatusRecordDTO
     * @return
     */
    PageInfo<DeviceStatusRecordDTO> getDeviceStatusRecordByDeviceId(DeviceStatusRecordDTO deviceStatusRecordDTO);

    /**
     * 根据设备id修改用户维护状态
     * @param deviceId
     * @param statusId
     */
    void updateRepairedStatusByDeviceId(String deviceId, Integer statusId);

    void downloadTemplate(String url,String fileName,HttpServletResponse resp);
}
