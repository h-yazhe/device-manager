/*
SQLyog v10.2 
MySQL - 5.7.22 : Database - device_manager
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`device_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `device_manager`;

/*Table structure for table `amount_unit` */

DROP TABLE IF EXISTS `amount_unit`;

CREATE TABLE `amount_unit` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '计量单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `amount_unit` */

insert  into `amount_unit`(`id`,`name`) values ('','未选择'),('1','个');

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '设备品牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`) values ('1527744129765806748','三星'),('1527745606203104480','华硕'),('2','苹果'),('3','联想'),('987bd405b8af11e8b2a50242ac110002','三星'),('9882f347b8af11e8b2a50242ac110002','三星'),('988a0567b8af11e8b2a50242ac110002','三星'),('989146ddb8af11e8b2a50242ac110002','三星'),('98985c6ab8af11e8b2a50242ac110002','三星'),('989fc72cb8af11e8b2a50242ac110002','三星'),('98a6e2eab8af11e8b2a50242ac110002','三星'),('98adffecb8af11e8b2a50242ac110002','三星'),('98b51732b8af11e8b2a50242ac110002','三星'),('98bc2f14b8af11e8b2a50242ac110002','三星'),('98c349b1b8af11e8b2a50242ac110002','三星'),('98ca6dcbb8af11e8b2a50242ac110002','三星'),('98d18d5bb8af11e8b2a50242ac110002','三星'),('98d8ab40b8af11e8b2a50242ac110002','三星'),('98dfd18ab8af11e8b2a50242ac110002','三星'),('98e6f24ab8af11e8b2a50242ac110002','三星'),('98edff47b8af11e8b2a50242ac110002','三星'),('99016424b8af11e8b2a50242ac110002','三星'),('99087a39b8af11e8b2a50242ac110002','三星'),('990fba5cb8af11e8b2a50242ac110002','三星'),('9916db44b8af11e8b2a50242ac110002','三星'),('991ddeaeb8af11e8b2a50242ac110002','三星'),('9924eda7b8af11e8b2a50242ac110002','三星'),('992c0906b8af11e8b2a50242ac110002','三星'),('99332645b8af11e8b2a50242ac110002','三星');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级类别id',
  `name` varchar(32) NOT NULL COMMENT '类别名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `category` */

insert  into `category`(`id`,`parent_id`,`name`,`level`,`path`) values ('0','','默认分类',1,'/'),('1533627421723595723','','电脑',1,'/'),('1533627421723695018','1533627421723595723','笔记本',2,'/1533627421723595723/'),('1533627421723747003','1533627421723777410','神舟',3,'/1533627421723595723/1533627421723777410/'),('1533627421723777410','1533627421723595723','台式机',2,'/1533627421723595723/');

/*Table structure for table `custodian` */

DROP TABLE IF EXISTS `custodian`;

CREATE TABLE `custodian` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '保管人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `custodian` */

insert  into `custodian`(`id`,`name`) values ('1','李四');

/*Table structure for table `device` */

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
  PRIMARY KEY (`id`),
  KEY `location_id` (`location_id`),
  KEY `work_nature_id` (`work_nature_id`),
  KEY `custodian_id` (`custodian_id`),
  KEY `amount_unit_id` (`amount_unit_id`),
  KEY `status_id` (`status_id`),
  KEY `idx_deleted` (`deleted`),
  CONSTRAINT `device_ibfk_2` FOREIGN KEY (`work_nature_id`) REFERENCES `work_nature` (`id`),
  CONSTRAINT `device_ibfk_3` FOREIGN KEY (`custodian_id`) REFERENCES `custodian` (`id`),
  CONSTRAINT `device_ibfk_4` FOREIGN KEY (`amount_unit_id`) REFERENCES `amount_unit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device` */

insert  into `device`(`id`,`name`,`location_id`,`national_id`,`serial_number`,`device_model_id`,`use_time`,`use_department_id`,`work_nature_id`,`custodian_id`,`unit_price`,`amount_unit_id`,`description`,`status_id`,`deleted`,`create_time`,`update_time`) values ('1533799349223338772','测试设备1','1538713948587196951','a111345423','44444444',0,'2018-09-11 17:14:01',0,'1','1','100.40','1','',2,0,'2018-08-09 07:21:21','2018-10-08 10:50:29'),('1536213059571642477','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-06 05:51:03',0,'1','1','100.40','1','',3,0,'2018-09-06 05:51:03','2018-10-08 10:50:29'),('1536213106484899323','测试设备4','1538713948587196951','a111345423','44444444',0,'2018-09-11 18:28:44',0,'1','1','100.40','1','',3,0,'2018-09-06 05:51:50','2018-10-08 10:50:29'),('1536216465902587143','测试设备4','1538713948587196951','a111345423','44444444',0,'2018-09-11 18:37:52',0,'1','1','100.40','1','',3,0,'2018-09-06 06:47:49','2018-10-08 10:50:29'),('1536305580581335246','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-07 07:33:05',0,'1','1','100.40','1','',1,0,'2018-09-07 07:33:05','2018-10-08 10:50:29'),('1536309422667678325','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-07 08:37:07',0,'1','1','0.00','1','',1,0,'2018-09-07 08:37:07','2018-10-08 10:50:29'),('1536310033567267675','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-07 08:47:18',0,'1','1','0.00','1','',1,0,'2018-09-07 08:47:18','2018-10-08 10:50:29'),('1536311552905414791','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-07 09:12:37',0,'1','1','0.00','1','',1,0,'2018-09-07 09:12:37','2018-10-08 10:50:29'),('1536311581393974181','测试设备','1538713948587196951','a111345423','44444444',2,'2018-09-07 09:13:06',0,'1','1','0.00','1','',1,0,'2018-09-07 09:13:06','2018-10-08 10:50:29'),('1536338663917548726','','1538713948587196951','','',0,'2018-09-07 16:44:29',0,'1','1','0.00','','',1,0,'2018-09-07 16:44:29','2018-10-08 10:50:29'),('1536510045396168455','','1538713948587196951','','',0,'2018-09-09 16:20:46',0,'1','1','0.00','','',1,0,'2018-09-09 16:20:46','2018-10-08 10:50:29'),('1536510089586221435','asdas','1538713948587196951','','',0,'2018-09-09 16:21:31',0,'1','1','0.00','','',1,0,'2018-09-09 16:21:31','2018-10-08 10:50:29'),('1536569771008817578','asda','1538713948587196951','111','dd',1,'2018-09-10 08:56:12',0,'1','1','0.00','','',1,0,'2018-09-10 08:56:12','2018-10-08 10:50:29'),('1536668527567596321','aaa','1538713948587196951','123123a','asda111',2,'2018-09-11 12:22:09',0,'1','1','5000.00','','',1,0,'2018-09-11 12:22:09','2018-10-08 10:50:29'),('1536823305720914344','平板','1538713948587196951','123456','123',2,'2018-09-13 07:21:45',0,'1','1','0.00','','',1,0,'2018-09-13 07:21:45','2018-10-08 10:50:29'),('1537970859830356002','惠普','1538713948587196951','22','7879',1,'2018-09-26 14:07:39',0,'1','1','0.00','','',1,0,'2018-09-26 14:07:39','2018-10-08 10:50:29'),('1538576913014360484','惠普','1538713948587196951','22','7879',1,'2018-10-03 14:28:33',0,'1','1','0.00','','',1,0,'2018-10-03 14:28:33','2018-10-08 10:50:29'),('1538576924166415323','1','1538713948587196951','123213','213123',1,'2018-10-03 14:28:44',0,'1','1','111.00','','',1,0,'2018-10-03 14:28:44','2018-10-08 10:50:29'),('7','测试设备3','1538713948587196951','a111345423','44444444',0,'2018-08-09 07:21:21',0,'1','1','100.40','1','',1,0,'2018-08-09 07:21:21','2018-10-08 10:50:29');

/*Table structure for table `device_brand` */

DROP TABLE IF EXISTS `device_brand`;

CREATE TABLE `device_brand` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `brand_id` varchar(32) NOT NULL COMMENT '品牌id',
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `brand_id` (`brand_id`),
  CONSTRAINT `device_brand_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `device_brand_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_brand` */

insert  into `device_brand`(`id`,`device_id`,`brand_id`) values ('1533800141382885094','1533799349223338772','2'),('1536213059850922394','1536213059571642477','2'),('1536213106664158865','1536213106484899323','2'),('1536216466179302342','1536216465902587143','2'),('1536305580762661098','1536305580581335246','2'),('1536309422843138802','1536309422667678325','2'),('1536310033740581244','1536310033567267675','2'),('1536311553081859670','1536311552905414791','2'),('1536311581569160774','1536311581393974181','2'),('1536338664090379814','1536338663917548726','2'),('1536510045580594136','1536510045396168455','2'),('1536510089764607037','1536510089586221435','2'),('1536569771182542116','1536569771008817578','2'),('1536668527738871642','1536668527567596321','2'),('1536823305765676862','1536823305720914344','2'),('1537970859841140499','1537970859830356002','3'),('1538576913025578620','1538576913014360484','98d8ab40b8af11e8b2a50242ac110002'),('1538576924172982799','1538576924166415323','98ca6dcbb8af11e8b2a50242ac110002');

/*Table structure for table `device_category` */

DROP TABLE IF EXISTS `device_category`;

CREATE TABLE `device_category` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `category_id` varchar(32) NOT NULL COMMENT '类别id',
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `device_category_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `device_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_category` */

insert  into `device_category`(`id`,`device_id`,`category_id`) values ('1533800141538980826','1533799349223338772','1533627421723747003'),('1536213059942931390','1536213059571642477','1533627421723747003'),('1536213106754974547','1536213106484899323','1533627421723747003'),('1536216466271404510','1536216465902587143','1533627421723747003'),('1536305580849195038','1536305580581335246','1533627421723747003'),('1536309422929736763','1536309422667678325','1533627421723747003'),('1536310033826998201','1536310033567267675','1533627421723747003'),('1536311553168585152','1536311552905414791','1533627421723747003'),('1536311581655659708','1536311581393974181','1533627421723747003'),('1536338664175891540','1536338663917548726','0'),('1536510045668399655','1536510045396168455','0'),('1536510089851921114','1536510089586221435','0'),('1536569771270344506','1536569771008817578','1533627421723595723'),('1536668527818193675','1536668527567596321','1533627421723595723'),('1536823305772723944','1536823305720914344','1533627421723595723'),('1537970859846841657','1537970859830356002','1533627421723595723'),('1538576913030162077','1538576913014360484','1533627421723595723'),('1538576924177909052','1538576924166415323','0'),('2','7','1533627421723777410');

/*Table structure for table `device_model` */

DROP TABLE IF EXISTS `device_model`;

CREATE TABLE `device_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '型号名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='设备型号表';

/*Data for the table `device_model` */

insert  into `device_model`(`id`,`name`,`create_time`,`update_time`) values (1,'联想Y50','2018-09-10 07:12:05','2018-09-10 07:12:05'),(2,'iphoneX','2018-09-11 12:14:55','2018-09-11 12:14:55');

/*Table structure for table `device_status_record` */

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `device_status_record` */

insert  into `device_status_record`(`id`,`device_id`,`from_status`,`to_status`,`operate_time`,`from_location_id`,`to_location_id`,`operate_user_id`) values ('1536213059760958102','1536213059571642477',-1,1,'2018-09-06 05:51:03','','','1532999182292648171'),('1536213106574327805','1536213106484899323',-1,1,'2018-09-06 05:51:50','','','1532999182292648171'),('1536216466089886609','1536216465902587143',-1,1,'2018-09-06 06:47:49','','','1532999182292648171'),('1536305580674249206','1536305580581335246',-1,1,'2018-09-07 07:33:05','','','1526467363362171844'),('1536309422757573702','1536309422667678325',-1,1,'2018-09-07 08:37:07','','','1526467363362171844'),('1536310033655458404','1536310033567267675',-1,1,'2018-09-07 08:47:18','','','1526467363362171844'),('1536311552992637476','1536311552905414791',-1,1,'2018-09-07 09:12:37','','','1526467363362171844'),('1536311581481173280','1536311581393974181',-1,1,'2018-09-07 09:13:06','','','1526467363362171844'),('1536338664003152887','1536338663917548726',-1,1,'2018-09-07 16:44:29','','','1526467363362171844'),('1536510045493732117','1536510045396168455',-1,1,'2018-09-09 16:20:46','','','1526467363362171844'),('1536510089674933798','1536510089586221435',-1,1,'2018-09-09 16:21:31','','','1526467363362171844'),('1536569771096846923','1536569771008817578',-1,1,'2018-09-10 08:56:12','','','1526467363362171844'),('1536668527657680311','1536668527567596321',-1,1,'2018-09-11 12:22:09','','','1526467363362171844'),('1536823305751400863','1536823305720914344',-1,1,'2018-09-13 07:21:45','','','1536670751779403896'),('1537970859852284993','1537970859830356002',-1,1,'2018-09-26 14:07:39','1536914080429756317','2','1526467363362171844'),('1538576913035473787','1538576913014360484',-1,1,'2018-10-03 14:28:33','','','1532999182292648171'),('1538576924182869035','1538576924166415323',-1,1,'2018-10-03 14:28:44','','','1536670751779403896');

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级地点id',
  `name` varchar(32) NOT NULL COMMENT '地名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `location` */

insert  into `location`(`id`,`parent_id`,`name`,`level`,`path`) values ('1538713948587196951','','雅安',1,'/'),('1538714317100315207','','成都',1,'/');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `resource` varchar(32) NOT NULL DEFAULT '' COMMENT '权限对应的资源',
  `resource_name` varchar(128) NOT NULL DEFAULT '' COMMENT '资源的中文名，前端展示',
  `permission_code` varchar(128) NOT NULL DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(128) NOT NULL DEFAULT '' COMMENT '权限的中文释义',
  `required` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否本菜单必选权限,通常是"列表"权限是必选',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `permission` */

insert  into `permission`(`id`,`resource`,`resource_name`,`permission_code`,`permission_name`,`required`) values ('0','user','用户','user:add','添加',0),('1','user','用户','user:delete','删除',0),('10','brand','品牌','brand:delete','删除',0),('11','brand','品牌','brand:update','更新',0),('12','brand','品牌','brand:get','查询',0),('13','location','地点','location:add','添加',0),('14','location','地点','location:get','查询',0),('15','location','地点','location:update','更新',0),('16','location','地点','location:delete','删除',0),('2','user','用户','user:update','更新',0),('3','user','用户','user:get','查询',0),('4','role','角色','role:get','查询',0),('5','role','角色','role:delete','删除',0),('6','role','角色','role:update','更新',0),('7','role','角色','role:add','添加',0),('8','permission','权限','permission:get','查询',0),('9','brand','品牌','brand:add','添加',0);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`locked`,`create_time`,`update_time`) values ('1','管理员',0,'2018-05-13 17:11:42','2018-05-13 17:11:38'),('2','招新用',0,'2018-09-15 05:04:20','2018-09-15 05:04:20');

/*Table structure for table `role_location` */

DROP TABLE IF EXISTS `role_location`;

CREATE TABLE `role_location` (
  `id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `role_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `location_id` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `bind_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '绑定时间',
  `operate_user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '进行该绑定操作的用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `role_location` */

insert  into `role_location`(`id`,`role_id`,`location_id`,`bind_time`,`operate_user_id`) values ('1','1','1533106692421936787','2018-08-20 02:51:04','1'),('2','1','1','2018-09-10 09:18:22','1'),('3','1','2','2018-09-10 09:18:29','1'),('4','1','3','2018-09-10 09:18:33','1');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `permission_id` varchar(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`role_id`,`permission_id`,`locked`,`create_time`) values ('','2','12',0,'2018-09-15 06:17:53'),('1','1','0',0,'2018-05-13 17:54:04'),('10','1','15',0,'2018-08-01 06:59:23'),('11','1','16',0,'2018-08-01 06:59:27'),('2','1','1',0,'2018-05-13 17:54:04'),('3','1','2',0,'2018-05-13 17:54:04'),('4','1','3',0,'2018-05-13 17:54:04'),('5','1','8',0,'2018-05-18 13:55:41'),('6','1','4',0,'2018-05-18 14:09:20'),('7','1','12',0,'2018-07-31 01:19:13'),('8','1','13',0,'2018-08-01 06:25:53'),('9','1','14',0,'2018-08-01 06:59:18');

/*Table structure for table `user` */

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`real_name`,`email`,`phone`,`address`,`locked`,`create_time`,`update_time`,`last_time`) values ('1526467363362171844','hyz','黄雅哲','609927332@qq.com','18728193205',' ',0,'2018-05-16 18:42:43','2018-05-16 18:42:43','2018-05-16 18:42:43'),('1532999182292648171','test','zzz','609927332@qq.com','11111111111','aaa',0,'2018-07-31 01:05:09','2018-07-31 01:05:09','2018-07-31 01:05:09'),('1536670730690452459','test1','张三','111123@163.com','11111','',0,'2018-09-11 12:58:52','2018-09-11 12:58:52','2018-09-11 12:58:52'),('1536670751779403896','test2','李四','111123@163.com','11111','',0,'2018-09-11 12:59:13','2018-09-11 12:59:13','2018-09-11 12:59:13'),('1536988112568365851','new_user','招新账户','','','',0,'2018-09-15 05:08:32','2018-09-15 05:08:32','2018-09-15 05:08:32');

/*Table structure for table `user_auth` */

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
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `user_auth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录认证表';

/*Data for the table `user_auth` */

insert  into `user_auth`(`id`,`user_id`,`identify_type`,`identifier`,`credential`,`locked`,`create_time`,`update_time`) values ('1526467363565941170','1526467363362171844',0,'hyz','123456',0,'2018-05-16 18:42:43','2018-05-16 18:42:43'),('1532999182370532626','1532999182292648171',0,'test','123456',0,'2018-07-31 01:05:09','2018-07-31 01:05:09'),('1536670730774339083','1536670730690452459',0,'test1','123456',0,'2018-09-11 12:58:52','2018-09-11 12:58:52'),('1536670751916210020','1536670751779403896',0,'test2','123456',0,'2018-09-11 12:59:13','2018-09-11 12:59:13'),('1536988112589694759','1536988112568365851',0,'new_user','123456',0,'2018-09-15 05:08:32','2018-09-15 05:08:32');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `user_id` varchar(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `role_id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`,`create_time`) values ('1526467363636855881','1526467363362171844','1','2018-05-16 18:42:43'),('1532999182464614155','1532999182292648171','1','2018-07-31 01:05:09'),('1536670730858629311','1536670730690452459','1','2018-09-11 12:58:52'),('1536670752000264344','1536670751779403896','1','2018-09-11 12:59:13'),('1536988112599603434','1536988112568365851','2','2018-09-15 05:08:32');

/*Table structure for table `work_nature` */

DROP TABLE IF EXISTS `work_nature`;

CREATE TABLE `work_nature` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '工作性质',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `work_nature` */

insert  into `work_nature`(`id`,`name`) values ('1','自用');

/* Procedure structure for procedure `insertCategory` */

/*!50003 DROP PROCEDURE IF EXISTS  `insertCategory` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `insertCategory`()
BEGIN
DECLARE i INT DEFAULT 1;
WHILE i<=1000
DO
INSERT INTO category VALUES(REPLACE(UUID(),"-",""),NULL,"测试分类",1,"/");
SET i=i+1;
END WHILE;
COMMIT;
    END */$$
DELIMITER ;

DROP TABLE IF EXISTS `repair_order`;
CREATE TABLE `repair_order`  (
  `id` int(11) NOT NULL,
  `apply_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '申请用户的id',
  `deal_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '处理该订单的用户的id',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题描述',
  `status_code` tinyint(1) NOT NULL COMMENT '订单状态，0:待维修，1:维修中，2:已维修,3:维修失败',
  `expected_time` datetime(0) NOT NULL COMMENT '预计时间',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_apply_user_id`(`apply_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
