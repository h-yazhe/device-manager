package com.sicau.devicemanager.util;

import com.sicau.devicemanager.POJO.DO.Brand;
import com.sicau.devicemanager.POJO.DO.Device;
import com.sicau.devicemanager.POJO.DO.DeviceBrand;
import com.sicau.devicemanager.POJO.DO.DeviceModel;
import com.sicau.devicemanager.config.exception.VerificationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 用于读取.xlsx格式的文件
 */
public class XssfUtil {
    /**
     * 默认要解析的sheet号
     */
    private static final int SHEET_NUMBER = 0;
    /**
     * excel表格字段数目
     */
    private static final int FILE_FILED_SUM = 6;
    /**
     * 文件格式不正确提示
     */
    private static final String FILE_FORMAT_NOT_CORRECT = "excel文件格式不正确";

    /**
     * 解析流中的excel文件，并返回一个列表，包含设备列表、品牌列表、设备品牌关系列表以及型号列表
     */
    public static List<List> parseExcel(InputStream in) throws Exception {
        Workbook workbook = new XSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(SHEET_NUMBER);
        List<Device> devices = new ArrayList<>();
        List<Brand> brands = new ArrayList<>();
        List<DeviceBrand> deviceBrands = new ArrayList<>();
        List<DeviceModel> deviceModels = new ArrayList<>();

        boolean firstRow = true;
        for (Row row : sheet) {
            //不解析第一行
            if (firstRow) {
                firstRow = false;
                continue;
            }
            List<String> rawItem = new ArrayList<>();
            double unitPrice = 0.0;
            Iterator<Cell> cellIterator = row.iterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    rawItem.add(cell.getStringCellValue());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    //单价应该保持double类型
                    if (!cellIterator.hasNext()) {
                        unitPrice = cell.getNumericCellValue();
                    } else {
                        rawItem.add(String.valueOf((int) cell.getNumericCellValue()));
                    }
                }
            }

            //如果解析正确，单价字段不会在rawItem中，所以少一个
            if (rawItem.size() != FILE_FILED_SUM - 1) {
                throw new VerificationException(FILE_FORMAT_NOT_CORRECT);
            }

            Device device = new Device();
            device.setId(KeyUtil.genUniqueKey());
            device.setName(rawItem.get(0));
            device.setNationalId(rawItem.get(3));
            device.setSerialNumber(rawItem.get(4));
            device.setUnitPrice(new BigDecimal(unitPrice));
            devices.add(device);

            Brand brand = new Brand();
            brand.setId(KeyUtil.genUniqueKey());
            brand.setName(rawItem.get(1));
            brands.add(brand);

            DeviceBrand deviceBrand = new DeviceBrand();
            deviceBrand.setId(KeyUtil.genUniqueKey());
            deviceBrand.setDeviceId(device.getId());
            deviceBrand.setBrandId(brand.getId());
            deviceBrands.add(deviceBrand);

            DeviceModel deviceMode = new DeviceModel();
            deviceMode.setId((int) System.currentTimeMillis() / 1000);
            deviceMode.setName(rawItem.get(2));
            deviceModels.add(deviceMode);
            device.setDeviceModelId(deviceMode.getId());
        }
        workbook.close();
        in.close();
        return Arrays.asList(devices, brands, deviceBrands, deviceModels);
    }
}
