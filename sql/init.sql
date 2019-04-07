/*
SQLyog Ultimate v12.09 (64 bit)
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`) values ('1551590394304131467','232'),('1542798418424443804','nova'),('1542541404962456384','one plus'),('1541684574551464473','oppo'),('98d8ab40b8af11e8b2a50242ac110002','三星'),('1542191326263258851','华为'),('1527745606203104480','华硕'),('3','联想'),('2','苹果');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级类别id',
  `name` varchar(32) NOT NULL COMMENT '类别名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `category` */

insert  into `category`(`id`,`parent_id`,`name`,`level`,`path`) values ('0','','默认分类',1,'/'),('1542116122736743507','0','123',2,'/0/'),('1543148728236647828','1542116122736743507','电脑',3,'/0/1542116122736743507/'),('1544270910463982366','','测试分类',1,'/'),('1544271309361527232','1544270910463982366','test',2,'/1544270910463982366/'),('1544271372602585650','0','test123',2,'/0/'),('1544271418974946061','1544270910463982366','test666',2,'/1544270910463982366/'),('1544271675189260482','1544270910463982366','test234',2,'/1544270910463982366/'),('1544620601224562910','','电脑',1,'/'),('1544620601224959287','','test',1,'/'),('1544620665940564466','','test',1,'/'),('1544620665940924493','','电脑',1,'/');

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

insert  into `device`(`id`,`name`,`location_id`,`national_id`,`serial_number`,`device_model_id`,`use_time`,`use_department_id`,`work_nature_id`,`custodian_id`,`unit_price`,`amount_unit_id`,`description`,`status_id`,`deleted`,`create_time`,`update_time`) values ('11','11','11','11','11',0,'2018-12-23 08:03:11',0,'1','1','11.00','1','11',1,1,'2018-12-23 08:03:11','2018-12-23 08:05:30'),('1533799349223338772','测试设备1','1538713948587196951','a111345423','44444444',0,'2018-09-11 17:14:01',0,'1','1','100.40','1','',3,0,'2018-08-09 07:21:21','2018-10-11 07:36:15'),('1536213059571642477','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-06 05:51:03',0,'1','1','100.40','1','',3,0,'2018-09-06 05:51:03','2018-10-08 10:50:29'),('1536213106484899323','测试设备4','1538713948587196951','a111345423','44444444',0,'2018-09-11 18:28:44',0,'1','1','100.40','1','',3,0,'2018-09-06 05:51:50','2018-10-08 10:50:29'),('1536216465902587143','测试设备4','1538713948587196951','a111345423','44444444',0,'2018-09-11 18:37:52',0,'1','1','100.40','1','',3,0,'2018-09-06 06:47:49','2018-10-08 10:50:29'),('1536305580581335246','测试设备','1538713948587196951','a111345423','44444444',0,'2018-12-02 15:27:32',0,'1','1','100.40','1','',3,0,'2018-09-07 07:33:05','2019-03-26 05:35:40'),('1536309422667678325','测试设备','1538713948587196951','a111345423','44444444',0,'2018-09-07 08:37:07',0,'1','1','0.00','1','',3,0,'2018-09-07 08:37:07','2018-10-31 03:21:22'),('1536310033567267675','测试设备','1231242141231231231','a111345423','44444444',0,'2018-09-07 08:47:18',0,'1','1','0.00','1','',2,0,'2018-08-09 07:21:21','2018-10-11 07:36:15'),('1536311552905414791','测试设备','1538714317100315207','a111345423','44444444',0,'2019-03-26 05:35:04',0,'1','1','0.00','1','',2,0,'2018-09-07 09:12:37','2019-03-26 05:35:03'),('1536311581393974181','测试设备','1542559566326133525','a111345423','44444444',2,'2018-12-22 20:31:56',0,'1','1','0.00','1','',3,0,'2018-09-07 09:13:06','2019-03-21 03:33:52'),('1536338663917548726','','1538714317100315207','','',0,'2019-03-21 03:33:59',0,'1','1','0.00','','',2,0,'2018-09-07 16:44:29','2019-03-21 03:33:59'),('1536510045396168455','','1538713948587196951','','',0,'2018-09-09 16:20:46',0,'1','1','0.00','','',1,0,'2018-09-09 16:20:46','2018-10-08 10:50:29'),('1536510089586221435','asdas','1538713948587196951','','',0,'2018-09-09 16:21:31',0,'1','1','0.00','','',1,0,'2018-09-09 16:21:31','2018-10-08 10:50:29'),('1536569771008817578','asda','1538713948587196951','111','dd',1,'2018-09-10 08:56:12',0,'1','1','0.00','','',1,0,'2018-09-10 08:56:12','2018-10-08 10:50:29'),('1536668527567596321','aaa','1538713948587196951','123123a','asda111',2,'2018-12-04 13:21:24',0,'1','1','5000.00','','',2,0,'2018-09-11 12:22:09','2018-12-04 06:00:17'),('1536823305720914344','平板','1538713948587196951','123456','123',2,'2018-09-13 07:21:45',0,'1','1','0.00','','',1,0,'2018-09-13 07:21:45','2018-10-08 10:50:29'),('1537970859830356002','惠普','1538713948587196951','22','7879',1,'2018-09-26 14:07:39',0,'1','1','0.00','','',1,0,'2018-09-26 14:07:39','2018-10-08 10:50:29'),('1538576913014360484','惠普','1538713948587196951','22','7879',1,'2018-10-03 14:28:33',0,'1','1','0.00','','',1,0,'2018-10-03 14:28:33','2018-10-08 10:50:29'),('1538576924166415323','1','1538713948587196951','123213','213123',1,'2018-10-03 14:28:44',0,'1','1','111.00','','',1,0,'2018-10-03 14:28:44','2018-10-08 10:50:29'),('1539070039650377109','测试设备','1538713948587196951','a111345423','44444444',1,'2018-10-09 07:27:19',0,'1','1','0.00','','',3,0,'2018-10-09 07:27:19','2018-10-20 07:31:11'),('1542105511536587844','11111','1538714317100315207','11','11111',1,'2018-11-13 10:38:31',0,'1','1','0.00','','',1,1,'2018-11-13 10:38:31','2018-12-12 13:11:46'),('1545206918459889435','哈哈哈哈','1538713948587196951','55','15651',17,'2018-12-19 08:08:38',0,'1543145239391167022','1','6588.00','','',1,0,'2018-12-19 08:08:38','2018-12-19 08:08:38'),('1551590394304240803','110','','12','12',1107200,'2019-03-03 05:19:56',0,'1','1','12.00','1','',5,0,'2019-03-03 05:19:56','2019-03-03 05:19:56'),('1553578226042293569','adsd','','add','1111',6,'2019-03-26 05:30:26',0,'1541741261584163412','1','2.50','','',1,0,'2019-03-26 05:30:26','2019-03-26 05:30:26'),('1554181243305377240','qqqq','1538713948587196951','1111','212312',8,'2019-04-02 05:00:43',0,'1541741261584163412','1','0.00','','',1,0,'2019-04-02 05:00:43','2019-04-02 05:00:43'),('7','测试设备3','1538713948587196951','a111345423','44444444',0,'2018-08-09 07:21:21',0,'1','1','100.40','1','',1,1,'2018-08-09 07:21:21','2018-12-12 13:11:46');

/*Table structure for table `device_brand` */

DROP TABLE IF EXISTS `device_brand`;

CREATE TABLE `device_brand` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `brand_id` varchar(32) NOT NULL COMMENT '品牌id',
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `brand_id` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_brand` */

insert  into `device_brand`(`id`,`device_id`,`brand_id`) values ('1533800141382885094','1533799349223338772','2'),('1536213059850922394','1536213059571642477','2'),('1536213106664158865','1536213106484899323','2'),('1536216466179302342','1536216465902587143','2'),('1536305580762661098','1536305580581335246','2'),('1536309422843138802','1536309422667678325','2'),('1536310033740581244','1536310033567267675','2'),('1536311553081859670','1536311552905414791','2'),('1536311581569160774','1536311581393974181','2'),('1536338664090379814','1536338663917548726','2'),('1536510045580594136','1536510045396168455','2'),('1536510089764607037','1536510089586221435','2'),('1536569771182542116','1536569771008817578','2'),('1536668527738871642','1536668527567596321','2'),('1536823305765676862','1536823305720914344','2'),('1537970859841140499','1537970859830356002','3'),('1538576913025578620','1538576913014360484','98d8ab40b8af11e8b2a50242ac110002'),('1538576924172982799','1538576924166415323','98ca6dcbb8af11e8b2a50242ac110002'),('1539070039666171266','1539070039650377109','1527745606203104480'),('1542105511549278479','1542105511536587844','1541684574551464473'),('1545206918470658918','1545206918459889435','1541684574551464473'),('1551590394304945377','1551590394304240803','1551590394304131467'),('1553578226060160910','1553578226042293569','1542191326263258851'),('1554181243404323173','1554181243305377240','98d8ab40b8af11e8b2a50242ac110002');

/*Table structure for table `device_category` */

DROP TABLE IF EXISTS `device_category`;

CREATE TABLE `device_category` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `category_id` varchar(32) NOT NULL COMMENT '类别id',
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_category` */

insert  into `device_category`(`id`,`device_id`,`category_id`) values ('1533800141538980826','1533799349223338772','0'),('1536213059942931390','1536213059571642477','0'),('1536213106754974547','1536213106484899323','0'),('1536216466271404510','1536216465902587143','0'),('1536305580849195038','1536305580581335246','0'),('1536309422929736763','1536309422667678325','0'),('1536310033826998201','1536310033567267675','0'),('1536311553168585152','1536311552905414791','0'),('1536311581655659708','1536311581393974181','0'),('1536338664175891540','1536338663917548726','0'),('1536510045668399655','1536510045396168455','0'),('1536510089851921114','1536510089586221435','0'),('1536569771270344506','1536569771008817578','0'),('1536668527818193675','1536668527567596321','0'),('1536823305772723944','1536823305720914344','0'),('1537970859846841657','1537970859830356002','0'),('1538576913030162077','1538576913014360484','0'),('1538576924177909052','1538576924166415323','0'),('1539070039673159273','1539070039650377109','0'),('1542105511555609912','1542105511536587844','0'),('1545206918476532399','1545206918459889435','1544270910463982366'),('1551590394906383044','1551590394304240803','0'),('1553578226065155157','1553578226042293569','1544620601224562910'),('1554181243409173771','1554181243305377240',''),('2','7','0');

/*Table structure for table `device_model` */

DROP TABLE IF EXISTS `device_model`;

CREATE TABLE `device_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '型号名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='设备型号表';

/*Data for the table `device_model` */

insert  into `device_model`(`id`,`name`,`create_time`,`update_time`) values (7,'xs max','2018-11-09 06:20:02','2018-11-09 06:20:02'),(8,'P20','2018-11-09 06:30:07','2018-11-09 06:30:07'),(9,'mate20','2018-11-09 06:31:43','2018-11-09 06:31:43'),(10,'xs','2018-11-13 14:28:42','2018-11-13 14:28:42'),(11,'mate20pro','2018-11-13 14:37:04','2018-11-13 14:37:04'),(12,'honor','2018-11-13 14:37:53','2018-11-13 14:37:53'),(13,'mate5','2018-11-13 14:39:44','2018-11-13 14:39:44'),(14,'p9','2018-11-13 14:40:12','2018-11-13 14:40:12'),(16,'联想','2018-11-20 15:08:47','2018-11-20 15:08:47'),(18,'mate20x','2018-11-21 11:06:27','2018-11-21 11:06:27'),(21,'12','2019-03-03 05:19:56','2019-03-03 05:19:56');

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

insert  into `device_status_record`(`id`,`device_id`,`from_status`,`to_status`,`operate_time`,`from_location_id`,`to_location_id`,`operate_user_id`) values ('1536213059760958102','1536213059571642477',-1,1,'2018-09-06 05:51:03','','','1532999182292648171'),('1536213106574327805','1536213106484899323',-1,1,'2018-09-06 05:51:50','','','1532999182292648171'),('1536216466089886609','1536216465902587143',-1,1,'2018-09-06 06:47:49','','','1532999182292648171'),('1536305580674249206','1536305580581335246',-1,1,'2018-09-07 07:33:05','','','1526467363362171844'),('1536309422757573702','1536309422667678325',-1,1,'2018-09-07 08:37:07','','','1526467363362171844'),('1536310033655458404','1536310033567267675',-1,1,'2018-09-07 08:47:18','','','1526467363362171844'),('1536311552992637476','1536311552905414791',-1,1,'2018-09-07 09:12:37','','','1526467363362171844'),('1536311581481173280','1536311581393974181',-1,1,'2018-09-07 09:13:06','','','1526467363362171844'),('1536338664003152887','1536338663917548726',-1,1,'2018-09-07 16:44:29','','','1526467363362171844'),('1536510045493732117','1536510045396168455',-1,1,'2018-09-09 16:20:46','','','1526467363362171844'),('1536510089674933798','1536510089586221435',-1,1,'2018-09-09 16:21:31','','','1526467363362171844'),('1536569771096846923','1536569771008817578',-1,1,'2018-09-10 08:56:12','','','1526467363362171844'),('1536668527657680311','1536668527567596321',-1,1,'2018-09-11 12:22:09','','','1526467363362171844'),('1536823305751400863','1536823305720914344',-1,1,'2018-09-13 07:21:45','','','1536670751779403896'),('1537970859852284993','1537970859830356002',-1,1,'2018-09-26 14:07:39','1536914080429756317','2','1526467363362171844'),('1538576913035473787','1538576913014360484',-1,1,'2018-10-03 14:28:33','','','1532999182292648171'),('1538576924182869035','1538576924166415323',-1,1,'2018-10-03 14:28:44','','','1536670751779403896'),('1539070039681528560','1539070039650377109',-1,1,'2018-10-09 07:27:19','','1538713948587196951','1536670730690452459'),('1539243375575328461','1533799349223338772',2,3,'2018-10-11 07:36:15','1538713948587196951','1538713948587196951','1526467363362171844'),('1539863479289340399','1539070039650377109',1,4,'2018-10-18 11:51:16','1538713948587196951','1538713948587196951','123456'),('1540020670738732672','1539070039650377109',4,3,'2018-10-20 07:31:11','1538713948587196951','1538713948587196951','1532999182292648171'),('1540020736594835076','1539070039650377109',3,3,'2018-10-20 07:32:17','1538713948587196951','1538713948587196951','1532999182292648171'),('1540020737985172099','1539070039650377109',3,3,'2018-10-20 07:32:18','1538713948587196951','1538713948587196951','1532999182292648171'),('1540956080555482925','1536309422667678325',4,3,'2018-10-31 03:21:22','1538713948587196951','1538713948587196951','1532999182292648171'),('1542105511560313824','1542105511536587844',-1,1,'2018-11-13 10:38:31','','1538714317100315207','1536670730690452459'),('1543735652363982905','1536305580581335246',1,2,'2018-12-02 07:27:38','1538713948587196951','1539255351311105757','1526467363362171844'),('1543900884035966281','1536668527567596321',1,2,'2018-12-04 05:21:25','1538713948587196951','1539255351311105757','1526467363362171844'),('1545206918481723463','1545206918459889435',-1,1,'2018-12-19 08:08:38','','1538713948587196951','1536670751779403896'),('1545481916449998726','1536311581393974181',1,2,'2018-12-22 12:32:01','1538713948587196951','1542559566326133525','1526467363362171844'),('1551590394982191227','1551590394304240803',-1,5,'2019-03-03 05:19:56','','','1'),('1553139232614888939','1536311581393974181',2,3,'2019-03-21 03:33:52','1542559566326133525','1542559566326133525','1526467363362171844'),('1553139239178521343','1536338663917548726',1,2,'2019-03-21 03:33:59','1538713948587196951','1538714317100315207','1526467363362171844'),('1553578226070712473','1553578226042293569',-1,1,'2019-03-26 05:30:26','','','1526467363362171844'),('1553578503926891146','1536311552905414791',1,2,'2019-03-26 05:35:03','1538713948587196951','1538714317100315207','1526467363362171844'),('1553578540976341571','1536305580581335246',2,3,'2019-03-26 05:35:40','1538713948587196951','1538713948587196951','1526467363362171844'),('1554181243418368029','1554181243305377240',-1,1,'2019-04-02 05:00:43','','1538713948587196951','1526467363362171844');

/*Table structure for table `location` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级地点id',
  `name` varchar(32) NOT NULL COMMENT '地名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `location` */

insert  into `location`(`id`,`parent_id`,`name`,`level`,`path`) values ('1231242141231231231','1542559264111455786','一楼',3,'/1538713948587196951/1542559264111455786/'),('1538713948587196951','','雅安',1,'/'),('1538714317100315207','','温江',1,'/'),('1539253774423422225','1','测试',1,'/1/'),('1542559264111455786','1538713948587196951','十教A区',2,'/1538713948587196951/'),('1542559539998834763','1538714317100315207','图书馆',2,'/1538714317100315207/'),('1542559566326133525','1538714317100315207','小礼堂',2,'/1538714317100315207/'),('1543901206379762847','','都江堰',1,'/'),('4324324','1542559539998834763','1楼',3,'/1538714317100315207/1542559539998834763/');

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

insert  into `permission`(`id`,`resource`,`resource_name`,`permission_code`,`permission_name`,`required`) values ('0','user','用户','user:add','添加',0),('1','user','用户','user:delete','删除',0),('10','brand','品牌','brand:delete','删除',0),('11','brand','品牌','brand:update','更新',0),('12','brand','品牌','brand:get','查询',1),('13','location','地点','location:add','添加',0),('14','location','地点','location:get','查询',1),('15','location','地点','location:update','更新',0),('16','location','地点','location:delete','删除',0),('17','order','订单','order:add','添加',0),('18','order','订单','order:finish-admin','管理员完结',0),('19','order','订单','order:finish-user','用户完结(管理员不能用户完结)',0),('2','user','用户','user:update','更新',0),('20','model','型号','model:add','添加',0),('21','model','型号','model:get','查询',1),('22','nature','工作性质','nature:add','添加',0),('23','nature','工作性质','nature:get','查询',0),('24','nature','工作性质','nature:delete','删除',0),('25','device','设备','device:add','添加设备',0),('26','device','设备','device:update','更新设备',0),('27','device','设备','device:delete','删除设备',0),('28','device','设备','device:get','查询设备',1),('29','device','设备','device:distribute','分发设备',0),('3','user','用户','user:get','查询',0),('30','device','设备','device:discard','报废设备',0),('31','category','分类','category:add','添加分类',0),('32','category','分类','category:delete','删除分类',0),('33','category','分类','category:update','更新分类',0),('34','category','分类','category:get','查询分类',0),('4','role','角色','role:get','查询',0),('5','role','角色','role:delete','删除',0),('6','role','角色','role:update','更新',0),('7','role','角色','role:add','添加',0),('8','permission','权限','permission:get','查询',1),('9','brand','品牌','brand:add','添加',0);

/*Table structure for table `repair_order` */

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
  PRIMARY KEY (`id`),
  KEY `idx_apply_user_id` (`apply_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `repair_order` */

insert  into `repair_order`(`id`,`device_id`,`apply_user_id`,`deal_user_id`,`description`,`status_code`,`expected_time`,`create_time`,`update_time`) values (1,'1539070039650377108','123456','郭效坤','test',2,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-20 07:27:22'),(2,'1536309422667678325','123456','郭效坤','test',3,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-31 03:18:50'),(3,'1539070039650377159','123456','郭效坤','test',2,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-20 07:27:22'),(4,'1539070039650377562','123456','郭效坤','test',3,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-22 16:26:28'),(5,'1539070039650377187','123456','郭效坤','test',4,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-22 16:26:30'),(6,'1539070039650377569','123457','郭效坤','test',2,'2018-10-18 19:51:19','2018-10-18 11:51:15','2018-10-20 08:53:03'),(7,'1529668662622323236','1526467363362171844','hyz','hhhhh',3,'2018-10-09 00:27:35','2018-10-22 16:27:30','2018-10-23 02:20:44');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名',
  `locked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`locked`,`create_time`,`update_time`,`deleted`) values ('1','系统管理员',0,'2019-01-08 14:42:22','2018-05-13 17:11:38',0),('2','普通用户',0,'2019-01-08 14:42:17','2018-09-15 05:04:20',0),('3','设备管理员',0,'2019-02-28 09:07:32','2019-01-08 14:42:11',0),('4','雅安校区',0,'2019-03-26 05:16:44','2019-03-26 05:16:44',0);

/*Table structure for table `role_category` */

DROP TABLE IF EXISTS `role_category`;

CREATE TABLE `role_category` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `category_id` varchar(32) NOT NULL,
  `bind_time` datetime NOT NULL,
  `operate_user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_category` */

insert  into `role_category`(`id`,`role_id`,`category_id`,`bind_time`,`operate_user_id`) values ('1','1','0','2018-08-20 02:51:04','1'),('2','1','1544270910463982366','2018-09-10 09:18:22','1'),('3','1','1544620601224562910','2018-09-10 09:18:29','1'),('4','1','1544620601224959287','2018-12-04 05:25:00','1'),('5','1','1544620665940564466','2018-12-04 05:25:57','1'),('6','2','1544271372602585650','2018-12-24 23:16:48','1');

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

insert  into `role_location`(`id`,`role_id`,`location_id`,`bind_time`,`operate_user_id`) values ('1','1','1538713948587196951','2018-08-20 02:51:04','1'),('2','1','1538714317100315207','2018-09-10 09:18:22','1'),('3','1','1','2018-09-10 09:18:29','1'),('4','1','1539421239538615762','2018-12-04 05:25:00','1'),('5','1','1539255351311105757','2018-12-04 05:25:57','1'),('6','2','1538713948587196951','2019-01-08 15:12:43',''),('7','3','1538713948587196951','2019-01-08 15:12:50','');

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

insert  into `role_permission`(`id`,`role_id`,`permission_id`,`locked`,`create_time`) values ('0','2','12',0,'2018-10-22 01:28:15'),('1','1','0',0,'2018-05-13 17:54:04'),('10','1','15',0,'2018-08-01 06:59:23'),('11','1','16',0,'2018-08-01 06:59:27'),('12','1','18',0,'2018-10-22 01:27:57'),('13','2','17',0,'2018-10-22 01:28:52'),('14','2','19',0,'2018-10-22 01:29:05'),('15','1','9',0,'2018-11-03 06:10:41'),('16','1','10',0,'2018-11-03 06:13:37'),('17','1','20',0,'2018-11-08 06:12:24'),('18','1','21',0,'2018-11-08 06:12:30'),('19','1','23',0,'2018-11-18 06:09:26'),('2','1','1',0,'2018-05-13 17:54:04'),('20','1','22',0,'2018-11-18 06:09:31'),('21','1','24',0,'2018-11-18 06:09:39'),('22','3','23',0,'2019-01-08 14:46:01'),('23','3','21',0,'2019-01-08 14:46:34'),('24','3','14',0,'2019-01-08 14:46:46'),('25','3','28',0,'2019-01-08 14:47:40'),('26','3','25',0,'2019-01-08 14:48:10'),('27','3','26',0,'2019-01-08 14:48:14'),('28','3','27',0,'2019-01-08 14:48:21'),('29','3','29',0,'2019-01-08 14:48:28'),('3','1','2',0,'2018-05-13 17:54:04'),('30','3','30',0,'2019-01-08 14:48:32'),('31','3','12',0,'2019-01-08 14:48:48'),('32','1','25',0,'2019-01-08 14:57:45'),('33','1','26',0,'2019-01-08 14:57:50'),('34','1','27',0,'2019-01-08 14:57:54'),('35','1','28',0,'2019-01-08 14:58:02'),('36','1','29',0,'2019-01-08 14:58:10'),('37','1','30',0,'2019-01-08 14:58:16'),('38','2','4',0,'2019-01-08 15:17:19'),('39','1','31',0,'2019-01-09 13:37:08'),('4','1','3',0,'2018-05-13 17:54:04'),('40','1','32',0,'2019-01-09 13:37:12'),('41','1','33',0,'2019-01-09 13:37:16'),('42','1','34',0,'2019-01-09 13:37:24'),('43','2','34',0,'2019-01-09 13:38:16'),('44','3','31',0,'2019-01-09 13:38:40'),('45','3','32',0,'2019-01-09 13:38:54'),('46','3','33',0,'2019-01-09 13:39:06'),('47','3','34',0,'2019-01-09 13:39:11'),('48','3','15',0,'2019-01-09 13:51:50'),('49','3','13',0,'2019-01-09 13:51:27'),('5','1','8',0,'2018-05-18 13:55:41'),('50','3','16',0,'2019-01-09 13:51:48'),('51','2','28',0,'2019-01-09 14:12:59'),('52','1','7',0,'2019-03-29 12:40:43'),('53','1','5',0,'2019-04-05 09:57:18'),('6','1','4',0,'2018-05-18 14:09:20'),('7','1','12',0,'2018-07-31 01:19:13'),('8','1','13',0,'2018-08-01 06:25:53'),('9','1','14',0,'2018-08-01 06:59:18');

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
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`real_name`,`email`,`phone`,`address`,`locked`,`create_time`,`update_time`,`last_time`,`deleted`) values ('1526467363362171844','hyz','黄雅','1@gmail.com','4444444444','六舍',0,'2018-05-16 18:42:43','2019-01-07 07:40:01','2018-05-16 18:42:43',0),('1532999182292648171','test','教务处','609927332@qq.com','11111111111','教务处',0,'2018-07-31 01:05:09','2018-10-22 01:25:47','2018-07-31 01:05:09',0),('1536670730690452459','test1','张三','111123@163.com','11111','',0,'2018-09-11 12:58:52','2018-09-11 12:58:52','2018-09-11 12:58:52',0),('1536670751779403896','test2','李四','111123@163.com','11111','',0,'2018-09-11 12:59:13','2018-09-11 12:59:13','2018-09-11 12:59:13',0),('1542099325902881459','111','test','1234567@qq.com','11111111','1111111111',0,'2018-11-13 08:55:25','2018-11-13 08:55:25','2018-11-13 08:55:25',0),('1542105269573961105','1','111','111111@qq.com','134444444','111111111',0,'2018-11-13 10:34:29','2018-11-13 10:34:29','2018-11-13 10:34:29',0),('1542121950095432310','1111','111111','11@qq.com','123444444444','111',0,'2018-11-13 15:12:30','2019-03-21 15:04:28','2018-11-13 15:12:30',0),('1542121982725382704','11','111','111111@qq.com','12222','11',0,'2018-11-13 15:13:02','2019-03-21 15:16:03','2018-11-13 15:13:02',1),('1546870942007596247','test11111','测试','','','',1,'2019-01-07 14:22:22','2019-03-21 15:17:31','2019-01-07 14:22:22',1),('1546875115213334954','黄雅','add','zz','','asd',0,'2019-01-07 15:31:55','2019-03-21 15:03:04','2019-01-07 15:31:55',1),('1553157132709903490','yangyue','杨越','123@qq.com','12312311','无',0,'2019-03-21 08:32:12','2019-03-21 08:32:12','2019-03-21 08:32:12',0),('1553333340050540630','lll','zzz','123@qq.com','11111111111','',0,'2019-03-23 09:29:00','2019-03-23 09:29:00','2019-03-23 09:29:00',0),('1553333689779883506','11114','asdasdasd','2@qq.com','11111111111','',0,'2019-03-23 09:34:49','2019-03-23 09:34:49','2019-03-23 09:34:49',0);

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

insert  into `user_auth`(`id`,`user_id`,`identify_type`,`identifier`,`credential`,`locked`,`create_time`,`update_time`) values ('1526467363565941170','1526467363362171844',0,'hyz','123456',0,'2018-05-16 18:42:43','2018-05-16 18:42:43'),('1532999182370532626','1532999182292648171',0,'test','123456',0,'2018-07-31 01:05:09','2018-07-31 01:05:09'),('1536670730774339083','1536670730690452459',0,'test1','123456',0,'2018-09-11 12:58:52','2018-09-11 12:58:52'),('1536670751916210020','1536670751779403896',0,'test2','123456',0,'2018-09-11 12:59:13','2018-09-11 12:59:13'),('1542099325912795497','1542099325902881459',0,'111','123',0,'2018-11-13 08:55:25','2018-11-13 08:55:25'),('1542105269583545690','1542105269573961105',0,'1','11',0,'2018-11-13 10:34:29','2018-11-13 10:34:29'),('1542121950102421643','1542121950095432310',0,'1111','11',0,'2018-11-13 15:12:30','2018-11-13 15:12:30'),('1542121982733884780','1542121982725382704',0,'11','111',0,'2018-11-13 15:13:02','2018-11-13 15:13:02'),('1546870942022600327','1546870942007596247',0,'test11111','11111',0,'2019-01-07 14:22:22','2019-01-07 14:22:22'),('1546875115236332623','1546875115213334954',0,'黄雅','11',0,'2019-01-07 15:31:55','2019-01-07 15:31:55'),('1553157132720849096','1553157132709903490',0,'yangyue','99233',0,'2019-03-21 08:32:12','2019-03-21 08:32:12'),('1553333340058961064','1553333340050540630',0,'lll','aaaaaa',0,'2019-03-23 09:29:00','2019-03-23 09:29:00'),('1553333689788621489','1553333689779883506',0,'11114','aaaaaa',0,'2019-03-23 09:34:49','2019-03-23 09:34:49');

/*Table structure for table `user_location` */

DROP TABLE IF EXISTS `user_location`;

CREATE TABLE `user_location` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `location_id` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_location` */

insert  into `user_location`(`id`,`user_id`,`location_id`) values ('23141234','1526467363362171844','1538714317100315207'),('423423','1532999182292648171','1542559539998834763');

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

insert  into `user_role`(`id`,`user_id`,`role_id`,`create_time`) values ('1526467363636855881','1526467363362171844','1','2018-05-16 18:42:43'),('1532999182464614155','1532999182292648171','1','2018-07-31 01:05:09'),('1536670730858629311','1536670730690452459','1','2018-09-11 12:58:52'),('1536670752000264344','1536670751779403896','1','2018-09-11 12:59:13'),('1542099325920352213','1542099325902881459','1','2018-11-13 08:55:25'),('1542105269592991136','1542105269573961105','1','2018-11-13 10:34:29'),('1542121950109399478','1542121950095432310','1','2018-11-13 15:12:30'),('1542121982741345306','1542121982725382704','1','2018-11-13 15:13:02'),('1546870942031511477','1546870942007596247','1','2019-01-07 14:22:22'),('1546875115245462247','1546875115213334954','1','2019-01-07 15:31:55'),('1553157132728834261','1553157132709903490','2','2019-03-21 08:32:12'),('1553333340067221674','1553333340050540630','2','2019-03-23 09:29:00'),('1553333689795815032','1553333689779883506','2','2019-03-23 09:34:49');

/*Table structure for table `work_nature` */

DROP TABLE IF EXISTS `work_nature`;

CREATE TABLE `work_nature` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '工作性质',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `work_nature` */

insert  into `work_nature`(`id`,`name`) values ('1','自用'),('1541741261584163412','他用'),('1543145230749756287','ziyong'),('1543145239391167022','tayong'),('1543145249598361145','wurenshiyong'),('1543145391015403884','zy'),('1543145398724307569','ty');

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

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
