package com.sicau.devicemanager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sicau.devicemanager.POJO.DO.*;
import com.sicau.devicemanager.POJO.DTO.DeviceDTO;
import com.sicau.devicemanager.POJO.DTO.DeviceStatusRecordDTO;
import com.sicau.devicemanager.POJO.DTO.DistributeDeviceDTO;
import com.sicau.devicemanager.POJO.DTO.QueryPage;
import com.sicau.devicemanager.POJO.VO.DeviceSearchSelectionVO;
import com.sicau.devicemanager.config.exception.BusinessException;
import com.sicau.devicemanager.config.exception.VerificationException;
import com.sicau.devicemanager.constants.BusinessExceptionEnum;
import com.sicau.devicemanager.constants.DeviceStatusEnum;
import com.sicau.devicemanager.dao.*;
import com.sicau.devicemanager.service.DeviceService;
import com.sicau.devicemanager.service.LocationService;
import com.sicau.devicemanager.util.DateUtil;
import com.sicau.devicemanager.util.KeyUtil;
import com.sicau.devicemanager.util.XssfUtil;
import com.sicau.devicemanager.util.web.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.crypto.KeyGenerator;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Yazhe
 * Created at 17:45 2018/8/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceBrandMapper deviceBrandMapper;
    @Autowired
    private DeviceCategoryMapper deviceCategoryMapper;
    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DeviceStatusRecordMapper deviceStatusRecordMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CustodianMapper custodianMapper;
    @Autowired
    private DeviceModelMapper deviceModelMapper;
    @Autowired
    private WorkNatureMapper workNatureMapper;
    @Autowired
    private LocationService locationService;

    @Override
    public void addDevice(DeviceDTO deviceDTO) {
        //插入设备信息
        deviceDTO.setId(KeyUtil.genUniqueKey());
        deviceDTO.setStatusId(DeviceStatusEnum.IN_STORAGE.getCode());
        deviceMapper.insertSelective(deviceDTO);
        insertDeviceBrand(deviceDTO);
        insertDeviceCategory(deviceDTO);
        //插入设备状态记录
        deviceStatusRecordMapper.insert(
                new DeviceStatusRecord(KeyUtil.genUniqueKey(), deviceDTO.getId(),
                        DeviceStatusEnum.UNCONNECTED.getCode(), DeviceStatusEnum.IN_STORAGE.getCode(),
                        "", deviceDTO.getLocationId(), RequestUtil.getCurrentUserId())
        );
    }

    @Override
    public void addDeviceList(InputStream inputStream) throws Exception {
        List<List> bigList = XssfUtil.parseExcel(inputStream);
        List<Device> devices = bigList.get(0);
        List<Brand> brands = bigList.get(1);
        List<DeviceBrand> deviceBrands = bigList.get(2);
        List<DeviceModel> deviceModels = bigList.get(3);

        //BrandId DeviceBrand Map
        Map<String, DeviceBrand> bidDeviceBMap = deviceBrands.stream()
                .collect(Collectors.toMap(DeviceBrand::getBrandId, deviceBrand -> deviceBrand));
        for (Brand brand : brands
        ) {
            String dbBrandId = brandMapper.getIdByName(brand.getName());
            if (dbBrandId != null) {
                bidDeviceBMap.get(brand.getId()).setBrandId(dbBrandId);
                brand.setId(dbBrandId);
            }
        }

        //ModelId Device Map
        Map<Integer, Device> mIdDeviceMap = devices.stream()
                .collect(Collectors.toMap(Device::getDeviceModelId, device -> device));
        for (DeviceModel deviceModel : deviceModels
        ) {
            String dbModelId = deviceModelMapper.getIdByName(deviceModel.getName());
            if (dbModelId != null) {
                Integer dbModeIdInt = Integer.parseInt(dbModelId);
                mIdDeviceMap.get(deviceModel.getId()).setDeviceModelId(dbModeIdInt);
                deviceModel.setId(dbModeIdInt);
            }
        }

        //DeviceId DeviceBrand Map
        Map<String, DeviceBrand> dIdDeviceBMap = deviceBrands.stream()
                .collect(Collectors.toMap(DeviceBrand::getDeviceId, deviceBrand -> deviceBrand));
        //BrandId Brand Map
        Map<String, Brand> bIdBrandMap = brands.stream()
                .collect(Collectors.toMap(Brand::getId, brand -> brand));
        //DeviceId DeviceModel Map
        Map<Integer, DeviceModel> dIdDeviceMMap = deviceModels.stream()
                .collect(Collectors.toMap(DeviceModel::getId, deviceModel -> deviceModel));
        for (Device device : devices
        ) {
            boolean isEqualDbItem = false;
            List<Device> dbDevices = deviceMapper.selectByName(device.getName());
            for (Device dbDeviceItem : dbDevices
            ) {
                if (dbDeviceItem.equals(device)) {
                    isEqualDbItem = true;
                    break;
                }
            }
            if (!isEqualDbItem) {
                //获取用户能管理的结点的根节点
                String rootLocationId = "";
                List<Location> ownedLocations = locationMapper.getOnesLocationByUserId(RequestUtil.getCurrentUserId());
                Map<String, Location> locationMap = ownedLocations.stream()
                        .collect(Collectors.toMap(Location::getId, location -> location));
                for (Location location : ownedLocations
                ) {
                    if (locationMap.get(location.getParentId()) == null) {
                        rootLocationId = location.getId();
                        break;
                    }
                }

                device.setLocationId(rootLocationId);
                //默认“自用”
                device.setWorkNatureId("");
                //默认
                device.setCustodianId("");
                //数量单位，默认为“个”
                device.setAmountUnitId("");
                device.setStatusId(DeviceStatusEnum.UNBOUND.getCode());
                deviceMapper.insertSelective(device);

                brandMapper.insertBrand(bIdBrandMap.get(dIdDeviceBMap.get(device.getId()).getBrandId()));
                deviceBrandMapper.insert(dIdDeviceBMap.get(device.getId()));
                deviceModelMapper.insert(dIdDeviceMMap.get(device.getDeviceModelId()));

                //设置分类为默认分类（id为0）
                DeviceCategory deviceCategory = new DeviceCategory();
                deviceCategory.setId(KeyUtil.genUniqueKey());
                deviceCategory.setCategoryId("0");
                deviceCategory.setDeviceId(device.getId());
                deviceCategoryMapper.insert(deviceCategory);
                //插入设备状态记录
                deviceStatusRecordMapper.insert(
                        new DeviceStatusRecord(KeyUtil.genUniqueKey(), device.getId(),
                                DeviceStatusEnum.UNCONNECTED.getCode(), DeviceStatusEnum.UNBOUND.getCode(),
                                "", rootLocationId, RequestUtil.getCurrentUserId())
                );
            }
        }
    }

    private void insertDeviceBrand(DeviceDTO deviceDTO) {
        DeviceBrand deviceBrand = new DeviceBrand();
        deviceBrand.setId(KeyUtil.genUniqueKey());
        deviceBrand.setBrandId(deviceDTO.getBrandId());
        deviceBrand.setDeviceId(deviceDTO.getId());
        deviceBrandMapper.insert(deviceBrand);
    }

    private void insertDeviceCategory(DeviceDTO deviceDTO) {
        for (String categoryId :
                deviceDTO.getCategoryIds()) {
            DeviceCategory deviceCategory = new DeviceCategory();
            deviceCategory.setId(KeyUtil.genUniqueKey());
            deviceCategory.setCategoryId(categoryId);
            deviceCategory.setDeviceId(deviceDTO.getId());
            deviceCategoryMapper.insert(deviceCategory);
        }
    }

    @Override
    public void updateDeviceById(DeviceDTO deviceDTO) {
        deviceMapper.updateByPrimaryKeySelective(deviceDTO);

        deviceBrandMapper.deleteByDeviceId(deviceDTO.getId());
        insertDeviceBrand(deviceDTO);

        deviceCategoryMapper.deleteByDeviceId(deviceDTO.getId());
        insertDeviceCategory(deviceDTO);
    }

    @Override
    public void deleteDeviceById(List<String> ids) {
        if (ids.isEmpty()) {
            return;
        }
        //逻辑删除设备
        deviceMapper.deleteByIds(ids);
    }

    @Override
    public PageInfo<DeviceDTO> listDevice(DeviceDTO deviceDTO) throws VerificationException {
        //校验时间
        Date startTime = deviceDTO.getStartTime();
        Date endTime = deviceDTO.getEndTime();
        if (startTime != null && endTime != null) {
            int result = startTime.compareTo(endTime);
            //开始时间大于结束时间
            if (result == 1) {
                throw new BusinessException(BusinessExceptionEnum.DATE_INCORRECT, "请选择合适的日期");
            } else if (result == 0) {
                //开始时间=结束时间
                deviceDTO.setStartTime(DateUtil.getStartTimeToday(startTime));
                deviceDTO.setEndTime(DateUtil.getEndTimeToday(endTime));
            }
        }

        //获取查询条件的分类id及所有子分类id
        if (!StringUtils.isEmpty(deviceDTO.getCategoryId())) {
            List<Category> categoryList = categoryMapper.getDescendants(deviceDTO.getCategoryId());
            List<String> categoryIds = new ArrayList<>();
            categoryIds.add(deviceDTO.getCategoryId());
            categoryList.forEach((category) -> categoryIds.add(category.getId()));
            deviceDTO.setCategoryIds(categoryIds);
        }

        //获取查询条件的地点id及所有子地点id，若查询的地点id不属于角色管理区域下的id，则抛出异常
        String userId = deviceDTO.getUserId();
        if (userId == null) {
            throw new VerificationException("用户id不能为空");
        }
        //存储用户管理的所有地点及子孙地点
        List<Location> locationList = locationService.getUserManagedLocations(userId);
        //开始校验地点
        String locationId = deviceDTO.getLocationId();
        if (!StringUtils.isEmpty(locationId)) {
            if (!checkLocationId(locationId, locationList)) {
                throw new BusinessException(BusinessExceptionEnum.LOCATION_UNAUTHORIZED);
            }
            //设置地点id作为查询条件
            List<String> locationIds = new ArrayList<>();
            locationMapper.getDescendants(locationId).forEach(location -> {
                locationIds.add(location.getId());
            });
            locationIds.add(locationId);
            deviceDTO.setLocationIds(locationIds);
        }
        //如果没有传入locationId，则默认查询用户管理的所有地点
        else {
            List<String> locationIds = new ArrayList<>();
            locationList.forEach(location -> {
                locationIds.add(location.getId());
            });
            deviceDTO.setLocationIds(locationIds);
        }

        //分页查询
        QueryPage queryPage = deviceDTO.getQueryPage();
        //默认createTime降序
        if (queryPage.getOrderBy() == null) {
            queryPage.setOrderBy("create_time");
            queryPage.setOrderDirection("desc");
        }
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(),
                queryPage.getOrderBy() + " " + queryPage.getOrderDirection());
        List<DeviceDTO> deviceDTOList = deviceMapper.getDeviceInfo(deviceDTO);
        //组装地点和分类信息
        setLocationAndCategory(deviceDTOList);
        return new PageInfo<>(deviceDTOList);
    }

    @Override
    public void distributeDevice(DistributeDeviceDTO distributeDeviceDTO) {
        //查询设备原始记录
        List<Device> devices = deviceMapper.getByIds(distributeDeviceDTO.getDeviceIdList());
        //分发
        distributeDeviceDTO.setUseTime(new Date());
        deviceMapper.distributeDevice(distributeDeviceDTO);
        //写入分发记录
        List<DeviceStatusRecord> records = new ArrayList<>(1);
        devices.forEach(device -> records.add(new DeviceStatusRecord(
                KeyUtil.genUniqueKey(),
                device.getId(),
                device.getStatusId(),
                DeviceStatusEnum.USING.getCode(),
                device.getLocationId(),
                distributeDeviceDTO.getLocationId(),
                RequestUtil.getCurrentUserId()
        )));
        deviceStatusRecordMapper.insertBatch(records);
    }

    @Override
    public void discardDevice(String deviceId) {
        //查询设备原始记录
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        //报废
        deviceMapper.discardDevice(deviceId);
        //插入报废记录
        deviceStatusRecordMapper.insert(new DeviceStatusRecord(
                KeyUtil.genUniqueKey(),
                deviceId,
                device.getStatusId(),
                DeviceStatusEnum.DISCARDED.getCode(),
                device.getLocationId(),
                device.getLocationId(),
                RequestUtil.getCurrentUserId()
        ));
    }

    @Override
    public DeviceSearchSelectionVO getSearchSelections(int pageSize) {
        DeviceSearchSelectionVO deviceSearchSelectionVO = new DeviceSearchSelectionVO();
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setCategoryList(categoryMapper.getChildrenById(""));
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setLocationList(locationMapper.getByUserId(RequestUtil.getCurrentUserId()));
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setBrandList(brandMapper.listBrand());
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setCustodianList(custodianMapper.listAll());
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setDeviceModelList(deviceModelMapper.listAll());
        PageHelper.startPage(1, pageSize);
        deviceSearchSelectionVO.setWorkNatureList(workNatureMapper.listAll());
        return deviceSearchSelectionVO;
    }

    @Override
    public PageInfo<DeviceStatusRecordDTO> getDeviceStatusRecordByDeviceId(DeviceStatusRecordDTO deviceStatusRecordDTO) {

        QueryPage queryPage = deviceStatusRecordDTO.getQueryPage();
        PageHelper.startPage(queryPage.getPageNum(), queryPage.getPageSize(), "operate_time");
        return new PageInfo<>(deviceStatusRecordMapper.getByDeviceId(deviceStatusRecordDTO.getDeviceId()));

    }

    /**
     * 校验locationId是否在目标list中
     *
     * @param locationId
     * @param locationList
     * @return 存在返回true，否则返回false
     */
    private boolean checkLocationId(String locationId, List<Location> locationList) {
        for (Location location : locationList) {
            if (locationId.equals(location.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据地点的路径来获取地点名称表示路径，如 /雅安/十教
     *
     * @param locationPath 地点的路径
     * @return
     */
    private String getLocationStrBLocationPath(String locationPath) {
        StringBuilder locationStr = new StringBuilder();
        List<String> locationIds = new ArrayList<>(Arrays.asList(locationPath.split("/")));
        //字符串分割后第一个元素为空，去掉
        if (locationIds.size() > 0) {
            locationIds.remove(0);
            List<Location> locationList = locationMapper.getLocationsInIds(locationIds);
            for (int i = 0; i < locationList.size(); i++) {
                locationStr.append(locationList.get(i).getName());
                //最后一个地点后不加斜杠
                if (i != locationList.size() - 1) {
                    locationStr.append("/");
                }
            }
        }
        //否则为顶级区域
        return locationStr.toString();
    }

    private void setLocationAndCategory(List<DeviceDTO> deviceDTOList) {
        for (DeviceDTO deviceDTO : deviceDTOList) {
            //地点信息
            StringBuilder locationStr = new StringBuilder();
            Location location = deviceDTO.getLocation();
            //不给用户返回location数据
            deviceDTO.setLocation(null);
            List<String> locationIds = new ArrayList<>(Arrays.asList(location.getPath().split("/")));
            //字符串分割后第一个元素为空，去掉
            if (locationIds.size() > 0) {
                locationIds.remove(0);
                List<Location> locationList = locationMapper.getLocationsInIds(locationIds);
                for (Location item : locationList) {
                    locationStr.append(item.getName());
                    locationStr.append("/");
                }
                deviceDTO.setLocationStr(locationStr.append(location.getName()).toString());
            } else {
                //否则为顶级区域
                deviceDTO.setLocationStr(location.getName());
            }

            //分类信息
            StringBuilder categoryStr = new StringBuilder();
            Category category = deviceDTO.getCategory();
            //TODO 上线删除，测试用
            if (category == null) {
                deviceDTO.setCategoryStr("未分类");
                continue;
            }
            deviceDTO.setCategory(null);
            List<String> categoryIds = new ArrayList<>(Arrays.asList(category.getPath().split("/")));
            if (categoryIds.size() > 0) {
                categoryIds.remove(0);
                List<Category> categoryList = categoryMapper.getCategoryInIds(categoryIds);
                for (Category item : categoryList) {
                    categoryStr.append(item.getName());
                    categoryStr.append("/");
                }
                deviceDTO.setCategoryStr(categoryStr.append(category.getName()).toString());
            } else {
                deviceDTO.setCategoryStr(category.getName());
            }
        }
    }

    /**
     * 根据设备id修改设备维护状态
     *
     * @param deviceId
     * @param statusId
     */
    @Override
    public void updateRepairedStatusByDeviceId(String deviceId, Integer statusId) {
        //查询设备原始记录
        Device device = deviceMapper.selectByPrimaryKey(deviceId);
        //修改设备状态
        deviceMapper.updateStatusIdById(deviceId, statusId);

        //插入设备状态修改记录
        deviceStatusRecordMapper.insert(new DeviceStatusRecord(
                //生成唯一的主键 格式: 时间+随机数
                KeyUtil.genUniqueKey(),
                deviceId,
                device.getStatusId(),
                statusId,
                device.getLocationId(),
                device.getLocationId(),
                RequestUtil.getCurrentUserId()
        ));
    }

    /**
     * 下载设备导入模板
     *
     * @param url      文件路径
     * @param fileName 文件名
     */
    @Override
    public void downloadTemplate(String url, String fileName, HttpServletResponse resp) {
        //1、设置响应的头文件，会自动识别文件内容
        resp.setContentType("multipart/form-data");

        //2、设置Content-Disposition
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        OutputStream out = null;
        InputStream in = null;
        try {
            String path = url + fileName;
            //3、输出流
            out = resp.getOutputStream();

            //4、获取服务端的excel文件，这里的path等于4.8中的path
            in = DeviceServiceImpl.class.getClassLoader().getResourceAsStream(path);

            //5、输出文件
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
