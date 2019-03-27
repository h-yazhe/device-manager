/*
Navicat MySQL Data Transfer

Source Server         : dm.sc
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : device_manager

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-27 09:45:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for amount_unit
-- ----------------------------
DROP TABLE IF EXISTS `amount_unit`;
CREATE TABLE `amount_unit` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '计量单位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of amount_unit
-- ----------------------------
INSERT INTO `amount_unit` VALUES ('', '未选择');
INSERT INTO `amount_unit` VALUES ('1', '个');

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '设备品牌',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES ('1542459877091970506', 'iphone');
INSERT INTO `brand` VALUES ('1543146057037903167', 'meizu');
INSERT INTO `brand` VALUES ('1542798418424443804', 'nova');
INSERT INTO `brand` VALUES ('1542541404962456384', 'one plus');
INSERT INTO `brand` VALUES ('1541684574551464473', 'oppo');
INSERT INTO `brand` VALUES ('98ca6dcbb8af11e8b2a50242ac110002', '三星');
INSERT INTO `brand` VALUES ('98d8ab40b8af11e8b2a50242ac110002', '三星');
INSERT INTO `brand` VALUES ('1542191326263258851', '华为');
INSERT INTO `brand` VALUES ('1527745606203104480', '华硕');
INSERT INTO `brand` VALUES ('3', '联想');
INSERT INTO `brand` VALUES ('2', '苹果');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级类别id',
  `name` varchar(32) NOT NULL COMMENT '类别名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('0', '', '默认分类', '1', '/');
INSERT INTO `category` VALUES ('1542116122736743507', '0', '123', '2', '/0/');
INSERT INTO `category` VALUES ('1543148728236647828', '1542116122736743507', '电脑', '3', '/0/1542116122736743507/');
INSERT INTO `category` VALUES ('1544270910463982366', '', '测试分类', '1', '/');
INSERT INTO `category` VALUES ('1544271309361527232', '1544270910463982366', 'test', '2', '/1544270910463982366/');
INSERT INTO `category` VALUES ('1544271372602585650', '0', 'test123', '2', '/0/');
INSERT INTO `category` VALUES ('1544271418974946061', '1544270910463982366', 'test666', '2', '/1544270910463982366/');
INSERT INTO `category` VALUES ('1544271675189260482', '1544270910463982366', 'test234', '2', '/1544270910463982366/');
INSERT INTO `category` VALUES ('1544620601224959287', '', 'test', '1', '/');
INSERT INTO `category` VALUES ('1544620665940564466', '', 'test', '1', '/');
INSERT INTO `category` VALUES ('1551864844592907574', '', '一卡通', '1', '/');
INSERT INTO `category` VALUES ('1551864933578781328', '', '办公用品', '1', '/');
INSERT INTO `category` VALUES ('1551864942289847655', '', '门禁', '1', '/');
INSERT INTO `category` VALUES ('1551864949797535868', '', '网络', '1', '/');
INSERT INTO `category` VALUES ('1551864963996260568', '', '多媒体教学', '1', '/');

-- ----------------------------
-- Table structure for custodian
-- ----------------------------
DROP TABLE IF EXISTS `custodian`;
CREATE TABLE `custodian` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '保管人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of custodian
-- ----------------------------
INSERT INTO `custodian` VALUES ('1', '李四');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '设备名',
  `location_id` varchar(32) NOT NULL COMMENT '所处地点id',
  `national_id` varchar(32) NOT NULL COMMENT '国资编号',
  `serial_number` varchar(32) NOT NULL COMMENT '序列号',
  `device_model_id` int(11) NOT NULL DEFAULT '0' COMMENT '设备型号id',
  `use_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领用时间',
  `use_department_id` int(11) NOT NULL DEFAULT '0' COMMENT '使用部门id',
  `work_nature_id` varchar(32) NOT NULL COMMENT '工作性质id',
  `custodian_id` varchar(32) NOT NULL COMMENT '保管人id',
  `unit_price` decimal(11,2) NOT NULL COMMENT '单价',
  `amount_unit_id` varchar(32) NOT NULL COMMENT '计量单位id',
  `description` varchar(1024) NOT NULL DEFAULT '' COMMENT '设备描述，用户输入，例如配置信息等',
  `status_id` tinyint(2) NOT NULL DEFAULT '1' COMMENT '当前设备状态id,1为入库，2为使用，3为报废',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，即第一次入库的时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `location_id` (`location_id`) USING BTREE,
  KEY `work_nature_id` (`work_nature_id`) USING BTREE,
  KEY `custodian_id` (`custodian_id`) USING BTREE,
  KEY `amount_unit_id` (`amount_unit_id`) USING BTREE,
  KEY `status_id` (`status_id`) USING BTREE,
  KEY `idx_deleted` (`deleted`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('11', '11', '11', '11', '11', '0', '2018-12-23 08:03:11', '0', '1', '1', '11.00', '1', '11', '1', '1', '2018-12-23 08:03:11', '2018-12-23 08:05:30');
INSERT INTO `device` VALUES ('1533799349223338772', '测试设备1', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-11 17:14:01', '0', '1', '1', '100.40', '1', '', '3', '0', '2018-08-09 07:21:21', '2018-10-11 07:36:15');
INSERT INTO `device` VALUES ('1536213059571642477', '测试设备', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-06 05:51:03', '0', '1', '1', '100.40', '1', '', '3', '0', '2018-09-06 05:51:03', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536213106484899323', '测试设备4', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-11 18:28:44', '0', '1', '1', '100.40', '1', '', '3', '0', '2018-09-06 05:51:50', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536216465902587143', '测试设备4', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-11 18:37:52', '0', '1', '1', '100.40', '1', '', '3', '0', '2018-09-06 06:47:49', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536305580581335246', '测试设备', '1538713948587196951', 'a111345423', '44444444', '0', '2018-12-02 15:27:32', '0', '1', '1', '100.40', '1', '', '2', '0', '2018-09-07 07:33:05', '2018-12-04 06:00:14');
INSERT INTO `device` VALUES ('1536309422667678325', '测试设备', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-07 08:37:07', '0', '1', '1', '0.00', '1', '', '3', '0', '2018-09-07 08:37:07', '2018-10-31 03:21:22');
INSERT INTO `device` VALUES ('1536310033567267675', '测试设备', '1231242141231231231', 'a111345423', '44444444', '0', '2018-09-07 08:47:18', '0', '1', '1', '0.00', '1', '', '2', '0', '2018-08-09 07:21:21', '2018-10-11 07:36:15');
INSERT INTO `device` VALUES ('1536311552905414791', '测试设备', '1538713948587196951', 'a111345423', '44444444', '0', '2018-09-07 09:12:37', '0', '1', '1', '0.00', '1', '', '1', '0', '2018-09-07 09:12:37', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536311581393974181', '测试设备', '1542559566326133525', 'a111345423', '44444444', '2', '2018-12-22 20:31:56', '0', '1', '1', '0.00', '1', '', '2', '0', '2018-09-07 09:13:06', '2018-12-22 12:32:01');
INSERT INTO `device` VALUES ('1536338663917548726', '', '1538713948587196951', '', '', '0', '2018-09-07 16:44:29', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-07 16:44:29', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536510045396168455', '', '1538713948587196951', '', '', '0', '2018-09-09 16:20:46', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-09 16:20:46', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536510089586221435', 'asdas', '1538713948587196951', '', '', '0', '2018-09-09 16:21:31', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-09 16:21:31', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536569771008817578', 'asda', '1538713948587196951', '111', 'dd', '1', '2018-09-10 08:56:12', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-10 08:56:12', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1536668527567596321', 'aaa', '1538713948587196951', '123123a', 'asda111', '2', '2018-12-04 13:21:24', '0', '1', '1', '5000.00', '', '', '2', '0', '2018-09-11 12:22:09', '2018-12-04 06:00:17');
INSERT INTO `device` VALUES ('1536823305720914344', '平板', '1538713948587196951', '123456', '123', '2', '2018-09-13 07:21:45', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-13 07:21:45', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1537970859830356002', '惠普', '1538713948587196951', '22', '7879', '1', '2018-09-26 14:07:39', '0', '1', '1', '0.00', '', '', '1', '0', '2018-09-26 14:07:39', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1538576913014360484', '惠普', '1538713948587196951', '22', '7879', '1', '2018-10-03 14:28:33', '0', '1', '1', '0.00', '', '', '1', '0', '2018-10-03 14:28:33', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1538576924166415323', '1', '1538713948587196951', '123213', '213123', '1', '2018-10-03 14:28:44', '0', '1', '1', '111.00', '', '', '1', '0', '2018-10-03 14:28:44', '2018-10-08 10:50:29');
INSERT INTO `device` VALUES ('1539070039650377109', '测试设备', '1538713948587196951', 'a111345423', '44444444', '1', '2018-10-09 07:27:19', '0', '1', '1', '0.00', '', '', '3', '0', '2018-10-09 07:27:19', '2018-10-20 07:31:11');
INSERT INTO `device` VALUES ('1542105511536587844', '11111', '1538714317100315207', '11', '11111', '1', '2018-11-13 10:38:31', '0', '1', '1', '0.00', '', '', '1', '1', '2018-11-13 10:38:31', '2018-12-12 13:11:46');
INSERT INTO `device` VALUES ('1545206918459889435', '哈哈哈哈', '1538713948587196951', '55', '15651', '17', '2018-12-19 08:08:38', '0', '1543145239391167022', '1', '6588.00', '', '', '1', '0', '2018-12-19 08:08:38', '2018-12-19 08:08:38');
INSERT INTO `device` VALUES ('1551602860152352072', '上传测试', '1542559539998834763', 'a111345423', '44444', '1119666', '2019-03-03 08:47:40', '0', '', '', '200.00', '', '', '5', '0', '2019-03-03 08:47:40', '2019-03-03 08:47:40');
INSERT INTO `device` VALUES ('7', '测试设备3', '1538713948587196951', 'a111345423', '44444444', '0', '2018-08-09 07:21:21', '0', '1', '1', '100.40', '1', '', '1', '1', '2018-08-09 07:21:21', '2018-12-12 13:11:46');

-- ----------------------------
-- Table structure for device_brand
-- ----------------------------
DROP TABLE IF EXISTS `device_brand`;
CREATE TABLE `device_brand` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `brand_id` varchar(32) NOT NULL COMMENT '品牌id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `device_id` (`device_id`) USING BTREE,
  KEY `brand_id` (`brand_id`) USING BTREE,
  CONSTRAINT `device_brand_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `device_brand_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of device_brand
-- ----------------------------
INSERT INTO `device_brand` VALUES ('1533800141382885094', '1533799349223338772', '2');
INSERT INTO `device_brand` VALUES ('1536213059850922394', '1536213059571642477', '2');
INSERT INTO `device_brand` VALUES ('1536213106664158865', '1536213106484899323', '2');
INSERT INTO `device_brand` VALUES ('1536216466179302342', '1536216465902587143', '2');
INSERT INTO `device_brand` VALUES ('1536305580762661098', '1536305580581335246', '2');
INSERT INTO `device_brand` VALUES ('1536309422843138802', '1536309422667678325', '2');
INSERT INTO `device_brand` VALUES ('1536310033740581244', '1536310033567267675', '2');
INSERT INTO `device_brand` VALUES ('1536311553081859670', '1536311552905414791', '2');
INSERT INTO `device_brand` VALUES ('1536311581569160774', '1536311581393974181', '2');
INSERT INTO `device_brand` VALUES ('1536338664090379814', '1536338663917548726', '2');
INSERT INTO `device_brand` VALUES ('1536510045580594136', '1536510045396168455', '2');
INSERT INTO `device_brand` VALUES ('1536510089764607037', '1536510089586221435', '2');
INSERT INTO `device_brand` VALUES ('1536569771182542116', '1536569771008817578', '2');
INSERT INTO `device_brand` VALUES ('1536668527738871642', '1536668527567596321', '2');
INSERT INTO `device_brand` VALUES ('1536823305765676862', '1536823305720914344', '2');
INSERT INTO `device_brand` VALUES ('1537970859841140499', '1537970859830356002', '3');
INSERT INTO `device_brand` VALUES ('1538576913025578620', '1538576913014360484', '98d8ab40b8af11e8b2a50242ac110002');
INSERT INTO `device_brand` VALUES ('1538576924172982799', '1538576924166415323', '98ca6dcbb8af11e8b2a50242ac110002');
INSERT INTO `device_brand` VALUES ('1539070039666171266', '1539070039650377109', '1527745606203104480');
INSERT INTO `device_brand` VALUES ('1542105511549278479', '1542105511536587844', '1541684574551464473');
INSERT INTO `device_brand` VALUES ('1545206918470658918', '1545206918459889435', '1541684574551464473');
INSERT INTO `device_brand` VALUES ('1551602860152105373', '1551602860152352072', '1542191326263258851');

-- ----------------------------
-- Table structure for device_category
-- ----------------------------
DROP TABLE IF EXISTS `device_category`;
CREATE TABLE `device_category` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `category_id` varchar(32) NOT NULL COMMENT '类别id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `device_id` (`device_id`) USING BTREE,
  KEY `category_id` (`category_id`) USING BTREE,
  CONSTRAINT `device_category_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `device_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of device_category
-- ----------------------------
INSERT INTO `device_category` VALUES ('1533800141538980826', '1533799349223338772', '0');
INSERT INTO `device_category` VALUES ('1536213059942931390', '1536213059571642477', '0');
INSERT INTO `device_category` VALUES ('1536213106754974547', '1536213106484899323', '0');
INSERT INTO `device_category` VALUES ('1536216466271404510', '1536216465902587143', '0');
INSERT INTO `device_category` VALUES ('1536305580849195038', '1536305580581335246', '0');
INSERT INTO `device_category` VALUES ('1536309422929736763', '1536309422667678325', '0');
INSERT INTO `device_category` VALUES ('1536310033826998201', '1536310033567267675', '0');
INSERT INTO `device_category` VALUES ('1536311553168585152', '1536311552905414791', '0');
INSERT INTO `device_category` VALUES ('1536311581655659708', '1536311581393974181', '0');
INSERT INTO `device_category` VALUES ('1536338664175891540', '1536338663917548726', '0');
INSERT INTO `device_category` VALUES ('1536510045668399655', '1536510045396168455', '0');
INSERT INTO `device_category` VALUES ('1536510089851921114', '1536510089586221435', '0');
INSERT INTO `device_category` VALUES ('1536569771270344506', '1536569771008817578', '0');
INSERT INTO `device_category` VALUES ('1536668527818193675', '1536668527567596321', '0');
INSERT INTO `device_category` VALUES ('1536823305772723944', '1536823305720914344', '0');
INSERT INTO `device_category` VALUES ('1537970859846841657', '1537970859830356002', '0');
INSERT INTO `device_category` VALUES ('1538576913030162077', '1538576913014360484', '0');
INSERT INTO `device_category` VALUES ('1538576924177909052', '1538576924166415323', '0');
INSERT INTO `device_category` VALUES ('1539070039673159273', '1539070039650377109', '0');
INSERT INTO `device_category` VALUES ('1542105511555609912', '1542105511536587844', '0');
INSERT INTO `device_category` VALUES ('1545206918476532399', '1545206918459889435', '0');
INSERT INTO `device_category` VALUES ('1551602860175453513', '1551602860152352072', '0');
INSERT INTO `device_category` VALUES ('2', '7', '0');

-- ----------------------------
-- Table structure for device_model
-- ----------------------------
DROP TABLE IF EXISTS `device_model`;
CREATE TABLE `device_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '型号名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='设备型号表';

-- ----------------------------
-- Records of device_model
-- ----------------------------
INSERT INTO `device_model` VALUES ('6', 'mix3', '2018-11-09 06:15:05', '2018-11-09 06:15:05');
INSERT INTO `device_model` VALUES ('7', 'xs max', '2018-11-09 06:20:02', '2018-11-09 06:20:02');
INSERT INTO `device_model` VALUES ('8', 'P20', '2018-11-09 06:30:07', '2018-11-09 06:30:07');
INSERT INTO `device_model` VALUES ('9', 'mate20', '2018-11-09 06:31:43', '2018-11-09 06:31:43');
INSERT INTO `device_model` VALUES ('10', 'xs', '2018-11-13 14:28:42', '2018-11-13 14:28:42');
INSERT INTO `device_model` VALUES ('11', 'mate20pro', '2018-11-13 14:37:04', '2018-11-13 14:37:04');
INSERT INTO `device_model` VALUES ('12', 'honor', '2018-11-13 14:37:53', '2018-11-13 14:37:53');
INSERT INTO `device_model` VALUES ('13', 'mate5', '2018-11-13 14:39:44', '2018-11-13 14:39:44');
INSERT INTO `device_model` VALUES ('14', 'p9', '2018-11-13 14:40:12', '2018-11-13 14:40:12');
INSERT INTO `device_model` VALUES ('16', '联想', '2018-11-20 15:08:47', '2018-11-20 15:08:47');
INSERT INTO `device_model` VALUES ('17', '联想Y50', '2018-11-20 15:43:53', '2018-11-20 15:43:53');
INSERT INTO `device_model` VALUES ('18', 'mate20x', '2018-11-21 11:06:27', '2018-11-21 11:06:27');
INSERT INTO `device_model` VALUES ('19', '5555', '2019-03-03 08:47:40', '2019-03-03 08:47:40');

-- ----------------------------
-- Table structure for device_status_record
-- ----------------------------
DROP TABLE IF EXISTS `device_status_record`;
CREATE TABLE `device_status_record` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `device_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '设备id',
  `from_status` tinyint(2) NOT NULL COMMENT '本来的状态，若为-1，则表示新添加的设备',
  `to_status` tinyint(2) NOT NULL COMMENT '改变的状态',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '改变设备状态的操作时间',
  `from_location_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '本来的locationid',
  `to_location_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '变更的locationid',
  `operate_user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '操作的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of device_status_record
-- ----------------------------
INSERT INTO `device_status_record` VALUES ('1536213059760958102', '1536213059571642477', '-1', '1', '2018-09-06 05:51:03', '', '', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1536213106574327805', '1536213106484899323', '-1', '1', '2018-09-06 05:51:50', '', '', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1536216466089886609', '1536216465902587143', '-1', '1', '2018-09-06 06:47:49', '', '', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1536305580674249206', '1536305580581335246', '-1', '1', '2018-09-07 07:33:05', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536309422757573702', '1536309422667678325', '-1', '1', '2018-09-07 08:37:07', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536310033655458404', '1536310033567267675', '-1', '1', '2018-09-07 08:47:18', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536311552992637476', '1536311552905414791', '-1', '1', '2018-09-07 09:12:37', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536311581481173280', '1536311581393974181', '-1', '1', '2018-09-07 09:13:06', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536338664003152887', '1536338663917548726', '-1', '1', '2018-09-07 16:44:29', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536510045493732117', '1536510045396168455', '-1', '1', '2018-09-09 16:20:46', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536510089674933798', '1536510089586221435', '-1', '1', '2018-09-09 16:21:31', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536569771096846923', '1536569771008817578', '-1', '1', '2018-09-10 08:56:12', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536668527657680311', '1536668527567596321', '-1', '1', '2018-09-11 12:22:09', '', '', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1536823305751400863', '1536823305720914344', '-1', '1', '2018-09-13 07:21:45', '', '', '1536670751779403896');
INSERT INTO `device_status_record` VALUES ('1537970859852284993', '1537970859830356002', '-1', '1', '2018-09-26 14:07:39', '1536914080429756317', '2', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1538576913035473787', '1538576913014360484', '-1', '1', '2018-10-03 14:28:33', '', '', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1538576924182869035', '1538576924166415323', '-1', '1', '2018-10-03 14:28:44', '', '', '1536670751779403896');
INSERT INTO `device_status_record` VALUES ('1539070039681528560', '1539070039650377109', '-1', '1', '2018-10-09 07:27:19', '', '1538713948587196951', '1536670730690452459');
INSERT INTO `device_status_record` VALUES ('1539243375575328461', '1533799349223338772', '2', '3', '2018-10-11 07:36:15', '1538713948587196951', '1538713948587196951', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1539863479289340399', '1539070039650377109', '1', '4', '2018-10-18 11:51:16', '1538713948587196951', '1538713948587196951', '123456');
INSERT INTO `device_status_record` VALUES ('1540020670738732672', '1539070039650377109', '4', '3', '2018-10-20 07:31:11', '1538713948587196951', '1538713948587196951', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1540020736594835076', '1539070039650377109', '3', '3', '2018-10-20 07:32:17', '1538713948587196951', '1538713948587196951', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1540020737985172099', '1539070039650377109', '3', '3', '2018-10-20 07:32:18', '1538713948587196951', '1538713948587196951', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1540956080555482925', '1536309422667678325', '4', '3', '2018-10-31 03:21:22', '1538713948587196951', '1538713948587196951', '1532999182292648171');
INSERT INTO `device_status_record` VALUES ('1542105511560313824', '1542105511536587844', '-1', '1', '2018-11-13 10:38:31', '', '1538714317100315207', '1536670730690452459');
INSERT INTO `device_status_record` VALUES ('1543735652363982905', '1536305580581335246', '1', '2', '2018-12-02 07:27:38', '1538713948587196951', '1539255351311105757', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1543900884035966281', '1536668527567596321', '1', '2', '2018-12-04 05:21:25', '1538713948587196951', '1539255351311105757', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1545206918481723463', '1545206918459889435', '-1', '1', '2018-12-19 08:08:38', '', '1538713948587196951', '1536670751779403896');
INSERT INTO `device_status_record` VALUES ('1545481916449998726', '1536311581393974181', '1', '2', '2018-12-22 12:32:01', '1538713948587196951', '1542559566326133525', '1526467363362171844');
INSERT INTO `device_status_record` VALUES ('1551602860178125229', '1551602860152352072', '-1', '5', '2019-03-03 08:47:40', '', '1542559539998834763', '1532999182292648171');

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级地点id',
  `name` varchar(32) NOT NULL COMMENT '地名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1231242141231231231', '1542559264111455786', '一楼', '3', '/1538713948587196951/1542559264111455786/');
INSERT INTO `location` VALUES ('1538713948587196951', '', '雅安', '1', '/');
INSERT INTO `location` VALUES ('1538714317100315207', '', '温江', '1', '/');
INSERT INTO `location` VALUES ('1538714456455121223', '1538713948587196951', '十教B区', '2', '/1538713948587196951/');
INSERT INTO `location` VALUES ('1539253774423422225', '1', '测试', '1', '/1/');
INSERT INTO `location` VALUES ('1542559264111455786', '1538713948587196951', '十教A区', '2', '/1538713948587196951/');
INSERT INTO `location` VALUES ('1542559539998834763', '1538714317100315207', '图书馆', '2', '/1538714317100315207/');
INSERT INTO `location` VALUES ('1542559566326133525', '1538714317100315207', '小礼堂', '2', '/1538714317100315207/');
INSERT INTO `location` VALUES ('1543901206379762847', '', '都江堰', '1', '/');
INSERT INTO `location` VALUES ('4324324', '1542559539998834763', '1楼', '2', '/1538714317100315207/1542559539998834763/');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `resource` varchar(32) NOT NULL DEFAULT '' COMMENT '权限对应的资源',
  `resource_name` varchar(128) NOT NULL DEFAULT '' COMMENT '资源的中文名，前端展示',
  `permission_code` varchar(128) NOT NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(128) NOT NULL DEFAULT '' COMMENT '权限的中文释义',
  `required` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否本菜单必选权限,通常是"列表"权限是必选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('0', 'user', '用户', 'user:add', '添加', '0');
INSERT INTO `permission` VALUES ('1', 'user', '用户', 'user:delete', '删除', '0');
INSERT INTO `permission` VALUES ('10', 'brand', '品牌', 'brand:delete', '删除', '0');
INSERT INTO `permission` VALUES ('11', 'brand', '品牌', 'brand:update', '更新', '0');
INSERT INTO `permission` VALUES ('12', 'brand', '品牌', 'brand:get', '查询', '1');
INSERT INTO `permission` VALUES ('13', 'location', '地点', 'location:add', '添加', '0');
INSERT INTO `permission` VALUES ('14', 'location', '地点', 'location:get', '查询', '1');
INSERT INTO `permission` VALUES ('15', 'location', '地点', 'location:update', '更新', '0');
INSERT INTO `permission` VALUES ('16', 'location', '地点', 'location:delete', '删除', '0');
INSERT INTO `permission` VALUES ('17', 'order', '订单', 'order:add', '添加', '0');
INSERT INTO `permission` VALUES ('18', 'order', '订单', 'order:finish-admin', '管理员完结', '0');
INSERT INTO `permission` VALUES ('19', 'order', '订单', 'order:finish-user', '用户完结(管理员不能用户完结)', '0');
INSERT INTO `permission` VALUES ('2', 'user', '用户', 'user:update', '更新', '0');
INSERT INTO `permission` VALUES ('20', 'model', '型号', 'model:add', '添加', '0');
INSERT INTO `permission` VALUES ('21', 'model', '型号', 'model:get', '查询', '1');
INSERT INTO `permission` VALUES ('22', 'natrue', '工作性质', 'nature:add', '添加', '0');
INSERT INTO `permission` VALUES ('23', 'nature', '工作性质', 'nature:get', '查询', '0');
INSERT INTO `permission` VALUES ('24', 'natrue', '工作性质', 'nature:delete', '删除', '0');
INSERT INTO `permission` VALUES ('25', 'device', '设备', 'device:add', '添加设备', '0');
INSERT INTO `permission` VALUES ('26', 'device', '设备', 'device:update', '更新设备', '0');
INSERT INTO `permission` VALUES ('27', 'device', '设备', 'device:delete', '删除设备', '0');
INSERT INTO `permission` VALUES ('28', 'device', '设备', 'device:get', '查询设备', '1');
INSERT INTO `permission` VALUES ('29', 'device', '设备', 'device:distribute', '分发设备', '0');
INSERT INTO `permission` VALUES ('3', 'user', '用户', 'user:get', '查询', '0');
INSERT INTO `permission` VALUES ('30', 'device', '设备', 'device:discard', '报废设备', '0');
INSERT INTO `permission` VALUES ('31', 'category', '分类', 'category:add', '添加分类', '0');
INSERT INTO `permission` VALUES ('32', 'category', '分类', 'category:delete', '删除分类', '0');
INSERT INTO `permission` VALUES ('33', 'category', '分类', 'category:update', '更新分类', '0');
INSERT INTO `permission` VALUES ('34', 'category', '分类', 'category:get', '查询分类', '0');
INSERT INTO `permission` VALUES ('4', 'role', '角色', 'role:get', '查询', '0');
INSERT INTO `permission` VALUES ('5', 'role', '角色', 'role:delete', '删除', '0');
INSERT INTO `permission` VALUES ('6', 'role', '角色', 'role:update', '更新', '0');
INSERT INTO `permission` VALUES ('7', 'role', '角色', 'role:add', '添加', '0');
INSERT INTO `permission` VALUES ('8', 'permission', '权限', 'permission:get', '查询', '1');
INSERT INTO `permission` VALUES ('9', 'brand', '品牌', 'brand:add', '添加', '0');

-- ----------------------------
-- Table structure for repair_order
-- ----------------------------
DROP TABLE IF EXISTS `repair_order`;
CREATE TABLE `repair_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(32) NOT NULL,
  `apply_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '申请用户的id',
  `deal_user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '处理该订单的用户的id',
  `description` varchar(512) NOT NULL DEFAULT '' COMMENT '问题描述',
  `status_code` tinyint(1) NOT NULL DEFAULT '0' COMMENT '订单状态，0:待维修，1:维修中，2:已维修,3:维修失败',
  `expected_time` datetime NOT NULL COMMENT '预计时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_apply_user_id` (`apply_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of repair_order
-- ----------------------------
INSERT INTO `repair_order` VALUES ('1', '1539070039650377108', '123456', '郭效坤', 'test', '2', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-20 07:27:22');
INSERT INTO `repair_order` VALUES ('2', '1536309422667678325', '123456', '郭效坤', 'test', '3', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-31 03:18:50');
INSERT INTO `repair_order` VALUES ('3', '1539070039650377159', '123456', '郭效坤', 'test', '2', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-20 07:27:22');
INSERT INTO `repair_order` VALUES ('4', '1539070039650377562', '123456', '郭效坤', 'test', '3', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-22 16:26:28');
INSERT INTO `repair_order` VALUES ('5', '1539070039650377187', '123456', '郭效坤', 'test', '4', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-22 16:26:30');
INSERT INTO `repair_order` VALUES ('6', '1539070039650377569', '123457', '郭效坤', 'test', '2', '2018-10-18 19:51:19', '2018-10-18 11:51:15', '2018-10-20 08:53:03');
INSERT INTO `repair_order` VALUES ('7', '1529668662622323236', '1526467363362171844', 'hyz', 'hhhhh', '3', '2018-10-09 00:27:35', '2018-10-22 16:27:30', '2018-10-23 02:20:44');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '0', '2019-01-08 14:42:22', '2018-05-13 17:11:38', '0');
INSERT INTO `role` VALUES ('2', '普通用户', '0', '2019-01-08 14:42:17', '2018-09-15 05:04:20', '0');
INSERT INTO `role` VALUES ('3', '设备管理员', '0', '2019-01-08 14:42:11', '2019-01-08 14:42:11', '0');
INSERT INTO `role` VALUES ('4', '雅安校区管理员', '0', '2019-03-27 08:52:59', '2019-03-27 08:52:37', '0');

-- ----------------------------
-- Table structure for role_category
-- ----------------------------
DROP TABLE IF EXISTS `role_category`;
CREATE TABLE `role_category` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `category_id` varchar(32) NOT NULL,
  `bind_time` datetime DEFAULT NULL,
  `operate_user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_category
-- ----------------------------
INSERT INTO `role_category` VALUES ('1', '1', '0', '2018-08-20 02:51:04', '1');
INSERT INTO `role_category` VALUES ('2', '1', '1544270910463982366', '2018-09-10 09:18:22', '1');
INSERT INTO `role_category` VALUES ('3', '1', '1544620601224562910', '2018-09-10 09:18:29', '1');
INSERT INTO `role_category` VALUES ('4', '1', '1544620601224959287', '2018-12-04 05:25:00', '1');
INSERT INTO `role_category` VALUES ('5', '1', '1544620665940564466', '2018-12-04 05:25:57', '1');
INSERT INTO `role_category` VALUES ('6', '2', '1544271372602585650', '2018-12-24 23:16:48', '1');

-- ----------------------------
-- Table structure for role_location
-- ----------------------------
DROP TABLE IF EXISTS `role_location`;
CREATE TABLE `role_location` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `location_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `bind_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
  `operate_user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '进行该绑定操作的用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_location
-- ----------------------------
INSERT INTO `role_location` VALUES ('1', '1', '1538713948587196951', '2018-08-20 02:51:04', '1');
INSERT INTO `role_location` VALUES ('2', '1', '1538714317100315207', '2018-09-10 09:18:22', '1');
INSERT INTO `role_location` VALUES ('3', '1', '1', '2018-09-10 09:18:29', '1');
INSERT INTO `role_location` VALUES ('4', '1', '1539421239538615762', '2018-12-04 05:25:00', '1');
INSERT INTO `role_location` VALUES ('5', '1', '1539255351311105757', '2018-12-04 05:25:57', '1');
INSERT INTO `role_location` VALUES ('6', '2', '1538713948587196951', '2019-01-08 15:12:43', '');
INSERT INTO `role_location` VALUES ('7', '3', '1538713948587196951', '2019-01-08 15:12:50', '');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_permission_id` (`permission_id`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色权限关系表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('0', '2', '12', '0', '2018-10-22 01:28:15');
INSERT INTO `role_permission` VALUES ('1', '1', '0', '0', '2018-05-13 17:54:04');
INSERT INTO `role_permission` VALUES ('10', '1', '15', '0', '2018-08-01 06:59:23');
INSERT INTO `role_permission` VALUES ('102', '4', '1', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('103', '4', '2', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('104', '4', '3', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('105', '4', '8', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('106', '4', '4', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('107', '4', '12', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('108', '4', '13', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('109', '4', '14', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('11', '1', '16', '0', '2018-08-01 06:59:27');
INSERT INTO `role_permission` VALUES ('110', '4', '15', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('111', '4', '16', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('112', '4', '18', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('115', '4', '9', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('116', '4', '10', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('117', '4', '20', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('118', '4', '21', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('119', '4', '23', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('12', '1', '18', '0', '2018-10-22 01:27:57');
INSERT INTO `role_permission` VALUES ('120', '4', '22', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('121', '4', '24', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('13', '2', '17', '0', '2018-10-22 01:28:52');
INSERT INTO `role_permission` VALUES ('132', '4', '25', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('133', '4', '26', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('134', '4', '27', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('135', '4', '28', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('136', '4', '29', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('137', '4', '30', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('139', '4', '31', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('14', '2', '19', '0', '2018-10-22 01:29:05');
INSERT INTO `role_permission` VALUES ('140', '4', '32', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('141', '4', '33', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('142', '4', '34', '0', '2019-03-27 09:13:20');
INSERT INTO `role_permission` VALUES ('15', '1', '9', '0', '2018-11-03 06:10:41');
INSERT INTO `role_permission` VALUES ('16', '1', '10', '0', '2018-11-03 06:13:37');
INSERT INTO `role_permission` VALUES ('17', '1', '20', '0', '2018-11-08 06:12:24');
INSERT INTO `role_permission` VALUES ('18', '1', '21', '0', '2018-11-08 06:12:30');
INSERT INTO `role_permission` VALUES ('19', '1', '23', '0', '2018-11-18 06:09:26');
INSERT INTO `role_permission` VALUES ('2', '1', '1', '0', '2018-05-13 17:54:04');
INSERT INTO `role_permission` VALUES ('20', '1', '22', '0', '2018-11-18 06:09:31');
INSERT INTO `role_permission` VALUES ('21', '1', '24', '0', '2018-11-18 06:09:39');
INSERT INTO `role_permission` VALUES ('22', '3', '23', '0', '2019-01-08 14:46:01');
INSERT INTO `role_permission` VALUES ('23', '3', '21', '0', '2019-01-08 14:46:34');
INSERT INTO `role_permission` VALUES ('24', '3', '14', '0', '2019-01-08 14:46:46');
INSERT INTO `role_permission` VALUES ('25', '3', '28', '0', '2019-01-08 14:47:40');
INSERT INTO `role_permission` VALUES ('26', '3', '25', '0', '2019-01-08 14:48:10');
INSERT INTO `role_permission` VALUES ('27', '3', '26', '0', '2019-01-08 14:48:14');
INSERT INTO `role_permission` VALUES ('28', '3', '27', '0', '2019-01-08 14:48:21');
INSERT INTO `role_permission` VALUES ('29', '3', '29', '0', '2019-01-08 14:48:28');
INSERT INTO `role_permission` VALUES ('3', '1', '2', '0', '2018-05-13 17:54:04');
INSERT INTO `role_permission` VALUES ('30', '3', '30', '0', '2019-01-08 14:48:32');
INSERT INTO `role_permission` VALUES ('31', '3', '12', '0', '2019-01-08 14:48:48');
INSERT INTO `role_permission` VALUES ('32', '1', '25', '0', '2019-01-08 14:57:45');
INSERT INTO `role_permission` VALUES ('33', '1', '26', '0', '2019-01-08 14:57:50');
INSERT INTO `role_permission` VALUES ('34', '1', '27', '0', '2019-01-08 14:57:54');
INSERT INTO `role_permission` VALUES ('35', '1', '28', '0', '2019-01-08 14:58:02');
INSERT INTO `role_permission` VALUES ('36', '1', '29', '0', '2019-01-08 14:58:10');
INSERT INTO `role_permission` VALUES ('37', '1', '30', '0', '2019-01-08 14:58:16');
INSERT INTO `role_permission` VALUES ('38', '2', '4', '0', '2019-01-08 15:17:19');
INSERT INTO `role_permission` VALUES ('39', '1', '31', '0', '2019-01-09 13:37:08');
INSERT INTO `role_permission` VALUES ('4', '1', '3', '0', '2018-05-13 17:54:04');
INSERT INTO `role_permission` VALUES ('40', '1', '32', '0', '2019-01-09 13:37:12');
INSERT INTO `role_permission` VALUES ('41', '1', '33', '0', '2019-01-09 13:37:16');
INSERT INTO `role_permission` VALUES ('42', '1', '34', '0', '2019-01-09 13:37:24');
INSERT INTO `role_permission` VALUES ('43', '2', '34', '0', '2019-01-09 13:38:16');
INSERT INTO `role_permission` VALUES ('44', '3', '31', '0', '2019-01-09 13:38:40');
INSERT INTO `role_permission` VALUES ('45', '3', '32', '0', '2019-01-09 13:38:54');
INSERT INTO `role_permission` VALUES ('46', '3', '33', '0', '2019-01-09 13:39:06');
INSERT INTO `role_permission` VALUES ('47', '3', '34', '0', '2019-01-09 13:39:11');
INSERT INTO `role_permission` VALUES ('48', '3', '15', '0', '2019-01-09 13:51:50');
INSERT INTO `role_permission` VALUES ('49', '3', '13', '0', '2019-01-09 13:51:27');
INSERT INTO `role_permission` VALUES ('5', '1', '8', '0', '2018-05-18 13:55:41');
INSERT INTO `role_permission` VALUES ('50', '3', '16', '0', '2019-01-09 13:51:48');
INSERT INTO `role_permission` VALUES ('51', '2', '28', '0', '2019-01-09 14:12:59');
INSERT INTO `role_permission` VALUES ('6', '1', '4', '0', '2018-05-18 14:09:20');
INSERT INTO `role_permission` VALUES ('7', '1', '12', '0', '2018-07-31 01:19:13');
INSERT INTO `role_permission` VALUES ('8', '1', '13', '0', '2018-08-01 06:25:53');
INSERT INTO `role_permission` VALUES ('9', '1', '14', '0', '2018-08-01 06:59:18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `real_name` varchar(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `email` varchar(32) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(16) DEFAULT '' COMMENT '电话',
  `address` varchar(64) DEFAULT '' COMMENT '住址',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1526467363362171844', 'hyz', '黄雅', '1@gmail.com', '4444444444', '六舍', '0', '2018-05-16 18:42:43', '2019-01-07 07:40:01', '2018-05-16 18:42:43', '0');
INSERT INTO `user` VALUES ('1532999182292648171', 'test', '教务处', '609927332@qq.com', '11111111111', '教务处', '0', '2018-07-31 01:05:09', '2018-10-22 01:25:47', '2018-07-31 01:05:09', '0');
INSERT INTO `user` VALUES ('1536670730690452459', 'test1', '张三', '111123@163.com', '11111', '', '0', '2018-09-11 12:58:52', '2018-09-11 12:58:52', '2018-09-11 12:58:52', '0');
INSERT INTO `user` VALUES ('1536670751779403896', 'test2', '李四', '111123@163.com', '11111', '', '0', '2018-09-11 12:59:13', '2018-09-11 12:59:13', '2018-09-11 12:59:13', '0');
INSERT INTO `user` VALUES ('1542099325902881459', '111', 'test', '1234567@qq.com', '11111111', '1111111111', '0', '2018-11-13 08:55:25', '2018-11-13 08:55:25', '2018-11-13 08:55:25', '0');
INSERT INTO `user` VALUES ('1542105269573961105', '1', '111', '111111@qq.com', '134444444', '111111111', '0', '2018-11-13 10:34:29', '2018-11-13 10:34:29', '2018-11-13 10:34:29', '0');
INSERT INTO `user` VALUES ('1542121950095432310', '1111', '111111', '11@qq.com', '123444444444', '111', '0', '2018-11-13 15:12:30', '2018-11-13 15:12:30', '2018-11-13 15:12:30', '0');
INSERT INTO `user` VALUES ('1542121982725382704', '11', '111', '111111@qq.com', '12222', '11', '0', '2018-11-13 15:13:02', '2018-11-13 15:13:02', '2018-11-13 15:13:02', '0');
INSERT INTO `user` VALUES ('1544961352870196938', '', '', '', '', '', '0', '2018-12-16 11:55:52', '2018-12-16 11:55:52', '2018-12-16 11:55:52', '0');
INSERT INTO `user` VALUES ('1546870942007596247', 'test11111', '测试', '', '', '', '0', '2019-01-07 14:22:22', '2019-01-07 14:22:22', '2019-01-07 14:22:22', '0');
INSERT INTO `user` VALUES ('1546875115213334954', '黄雅', 'add', 'zz', '', 'asd', '0', '2019-01-07 15:31:55', '2019-01-07 15:31:55', '2019-01-07 15:31:55', '0');
INSERT INTO `user` VALUES ('1546875115213334980', '胡萍', '胡萍', '', '', '', '0', '2019-02-28 07:32:39', '2019-02-28 07:37:09', '2019-02-28 07:32:39', '0');

-- ----------------------------
-- Table structure for user_auth
-- ----------------------------
DROP TABLE IF EXISTS `user_auth`;
CREATE TABLE `user_auth` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `identify_type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '登录认证类型,0：用户名密码登录，1：微信登录',
  `identifier` varchar(128) NOT NULL DEFAULT '' COMMENT '标识（手机号 邮箱 用户名或第三方应用的唯一标识）',
  `credential` varchar(128) NOT NULL DEFAULT '' COMMENT '登录凭证',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_auth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户登录认证表';

-- ----------------------------
-- Records of user_auth
-- ----------------------------
INSERT INTO `user_auth` VALUES ('1526467363565941170', '1526467363362171844', '0', 'hyz', '123456', '0', '2018-05-16 18:42:43', '2018-05-16 18:42:43');
INSERT INTO `user_auth` VALUES ('1532999182370532626', '1532999182292648171', '0', 'test', '123456', '0', '2018-07-31 01:05:09', '2018-07-31 01:05:09');
INSERT INTO `user_auth` VALUES ('1536670730774339083', '1536670730690452459', '0', 'test1', '123456', '0', '2018-09-11 12:58:52', '2018-09-11 12:58:52');
INSERT INTO `user_auth` VALUES ('1536670751916210020', '1536670751779403896', '0', 'test2', '123456', '0', '2018-09-11 12:59:13', '2018-09-11 12:59:13');
INSERT INTO `user_auth` VALUES ('1542099325912795497', '1542099325902881459', '0', '111', '123', '0', '2018-11-13 08:55:25', '2018-11-13 08:55:25');
INSERT INTO `user_auth` VALUES ('1542105269583545690', '1542105269573961105', '0', '1', '11', '0', '2018-11-13 10:34:29', '2018-11-13 10:34:29');
INSERT INTO `user_auth` VALUES ('1542121950102421643', '1542121950095432310', '0', '1111', '11', '0', '2018-11-13 15:12:30', '2018-11-13 15:12:30');
INSERT INTO `user_auth` VALUES ('1542121982733884780', '1542121982725382704', '0', '11', '111', '0', '2018-11-13 15:13:02', '2018-11-13 15:13:02');
INSERT INTO `user_auth` VALUES ('1544961352880861146', '1544961352870196938', '0', '', '', '0', '2018-12-16 11:55:52', '2018-12-16 11:55:52');
INSERT INTO `user_auth` VALUES ('1546870942022600327', '1546870942007596247', '0', 'test11111', '11111', '0', '2019-01-07 14:22:22', '2019-01-07 14:22:22');
INSERT INTO `user_auth` VALUES ('1546875115236332623', '1546875115213334954', '0', '黄雅', '11', '0', '2019-01-07 15:31:55', '2019-01-07 15:31:55');
INSERT INTO `user_auth` VALUES ('1546875115236332624', '1546875115213334980', '0', 'hp', '123456', '0', '2019-02-28 07:33:01', '2019-02-28 07:36:11');

-- ----------------------------
-- Table structure for user_location
-- ----------------------------
DROP TABLE IF EXISTS `user_location`;
CREATE TABLE `user_location` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `location_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user_location
-- ----------------------------
INSERT INTO `user_location` VALUES ('23141234', '1526467363362171844', '1538714317100315207');
INSERT INTO `user_location` VALUES ('423423', '1532999182292648171', '1542559539998834763');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1526467363636855881', '1526467363362171844', '1', '2018-05-16 18:42:43');
INSERT INTO `user_role` VALUES ('1532999182464614155', '1532999182292648171', '1', '2018-07-31 01:05:09');
INSERT INTO `user_role` VALUES ('1536670730858629311', '1536670730690452459', '1', '2018-09-11 12:58:52');
INSERT INTO `user_role` VALUES ('1536670752000264344', '1536670751779403896', '1', '2018-09-11 12:59:13');
INSERT INTO `user_role` VALUES ('1542099325920352213', '1542099325902881459', '1', '2018-11-13 08:55:25');
INSERT INTO `user_role` VALUES ('1542105269592991136', '1542105269573961105', '1', '2018-11-13 10:34:29');
INSERT INTO `user_role` VALUES ('1542121950109399478', '1542121950095432310', '1', '2018-11-13 15:12:30');
INSERT INTO `user_role` VALUES ('1542121982741345306', '1542121982725382704', '1', '2018-11-13 15:13:02');
INSERT INTO `user_role` VALUES ('1546870942031511477', '1546870942007596247', '1', '2019-01-07 14:22:22');
INSERT INTO `user_role` VALUES ('1546875115245462247', '1546875115213334954', '1', '2019-01-07 15:31:55');
INSERT INTO `user_role` VALUES ('1546875115245462248', '1546875115213334980', '4', '2019-02-28 07:34:55');

-- ----------------------------
-- Table structure for work_nature
-- ----------------------------
DROP TABLE IF EXISTS `work_nature`;
CREATE TABLE `work_nature` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '工作性质',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of work_nature
-- ----------------------------
INSERT INTO `work_nature` VALUES ('1', '自用');
INSERT INTO `work_nature` VALUES ('1541741261584163412', '他用');
INSERT INTO `work_nature` VALUES ('1542521469376592201', '无人使用');
INSERT INTO `work_nature` VALUES ('1543145230749756287', 'ziyong');
INSERT INTO `work_nature` VALUES ('1543145239391167022', 'tayong');
INSERT INTO `work_nature` VALUES ('1543145249598361145', 'wurenshiyong');
INSERT INTO `work_nature` VALUES ('1543145391015403884', 'zy');
INSERT INTO `work_nature` VALUES ('1543145398724307569', 'ty');
INSERT INTO `work_nature` VALUES ('1543145411776402858', 'wrsy');
INSERT INTO `work_nature` VALUES ('1543145438835587275', 'other');
INSERT INTO `work_nature` VALUES ('1543145447452605851', 'noone');
INSERT INTO `work_nature` VALUES ('1543146033988242535', 'self');

-- ----------------------------
-- Procedure structure for insertCategory
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertCategory`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `insertCategory`()
BEGIN
DECLARE i INT DEFAULT 1;
WHILE i<=1000
DO
INSERT INTO category VALUES(REPLACE(UUID(),"-",""),NULL,"测试分类",1,"/");
SET i=i+1;
END WHILE;
COMMIT;
    END
;;
DELIMITER ;
