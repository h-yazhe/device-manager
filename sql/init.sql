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

/*Table structure for table `brand` */

DROP TABLE IF EXISTS `brand`;

CREATE TABLE `brand` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '设备品牌',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `brand` */

insert  into `brand`(`id`,`name`) values ('1527744129765806748','三星'),('1527745606203104480','华硕'),('2','苹果'),('3','联想');

/*Table structure for table `categoryList` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL DEFAULT '' COMMENT '父级类别id',
  `name` varchar(32) NOT NULL COMMENT '类别名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `categoryList` */

/*Table structure for table `custodian` */

DROP TABLE IF EXISTS `custodian`;

CREATE TABLE `custodian` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '保管人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `custodian` */

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `location_id` varchar(32) NOT NULL COMMENT '所处地点id',
  `national_id` varchar(32) NOT NULL COMMENT '国资编号',
  `serial_number` varchar(32) NOT NULL COMMENT '序列号',
  `use_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领用时间',
  `work_nature_id` varchar(32) NOT NULL COMMENT '工作性质id',
  `custodian_id` varchar(32) NOT NULL COMMENT '保管人id',
  `unit_price` decimal(11,2) NOT NULL COMMENT '单价',
  `amount_unit_id` varchar(32) NOT NULL COMMENT '计量单位id',
  `status_id` varchar(32) NOT NULL COMMENT '当前设备状态id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，即第一次入库的时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `location_id` (`location_id`),
  KEY `work_nature_id` (`work_nature_id`),
  KEY `custodian_id` (`custodian_id`),
  KEY `amount_unit_id` (`amount_unit_id`),
  KEY `status_id` (`status_id`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `device_ibfk_2` FOREIGN KEY (`work_nature_id`) REFERENCES `work_nature` (`id`),
  CONSTRAINT `device_ibfk_3` FOREIGN KEY (`custodian_id`) REFERENCES `custodian` (`id`),
  CONSTRAINT `device_ibfk_4` FOREIGN KEY (`amount_unit_id`) REFERENCES `amount_unit` (`id`),
  CONSTRAINT `device_ibfk_5` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device` */

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

/*Table structure for table `device_cateory` */

DROP TABLE IF EXISTS `device_cateory`;

CREATE TABLE `device_cateory` (
  `id` varchar(32) NOT NULL,
  `device_id` varchar(32) NOT NULL COMMENT '设备id',
  `category_id` varchar(32) NOT NULL COMMENT '类别id',
  PRIMARY KEY (`id`),
  KEY `device_id` (`device_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `device_cateory_ibfk_1` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  CONSTRAINT `device_cateory_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_cateory` */

/*Table structure for table `device_status` */

DROP TABLE IF EXISTS `device_status`;

CREATE TABLE `device_status` (
  `id` varchar(32) NOT NULL,
  `status_id` varchar(32) NOT NULL COMMENT '设备状态id',
  `create_time` datetime NOT NULL COMMENT '创建时间，即设备更改为该状态的时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `device_status` */

/*Table structure for table `locationList` */

DROP TABLE IF EXISTS `location`;

CREATE TABLE `location` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级地点id',
  `name` varchar(32) NOT NULL COMMENT '地名',
  `level` tinyint(2) NOT NULL COMMENT '层级',
  `path` varchar(256) NOT NULL COMMENT '路径',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `location_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `locationList` */

insert  into `location`(`id`,`parent_id`,`name`,`level`,`path`) values ('1',NULL,'十教',1,'1'),('1533027557317293583','1533027557317946773','2舍',2,'/1533027557317946773/'),('1533027557317307900','1533027557317946773','109',2,'/1533027557317946773/'),('1533027557317363146','1533027557317946773','1舍',2,'/1533027557317946773/'),('1533027557317946773',NULL,'老区',1,'/');

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

insert  into `permission`(`id`,`resource`,`resource_name`,`permission_code`,`permission_name`,`required`) values ('0','user','用户','user:add','添加',0),('1','user','用户','user:delete','删除',0),('10','brand','品牌','brand:delete','删除',0),('11','brand','品牌','brand:update','更新',0),('12','brand','品牌','brand:get','查询',0),('2','user','用户','user:update','更新',0),('3','user','用户','user:get','查询',0),('4','role','角色','role:get','查询',0),('5','role','角色','role:delete','删除',0),('6','role','角色','role:update','更新',0),('7','role','角色','role:add','添加',0),('8','permission','权限','permission:get','查询',0),('9','brand','品牌','brand:add','添加',0);

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

insert  into `role`(`id`,`name`,`locked`,`create_time`,`update_time`) values ('1','管理员',0,'2018-05-13 17:11:42','2018-05-13 17:11:38');

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

insert  into `role_permission`(`id`,`role_id`,`permission_id`,`locked`,`create_time`) values ('1','1','0',0,'2018-05-13 17:54:04'),('2','1','1',0,'2018-05-13 17:54:04'),('3','1','2',0,'2018-05-13 17:54:04'),('4','1','3',0,'2018-05-13 17:54:04'),('5','1','8',0,'2018-05-18 13:55:41'),('6','1','4',0,'2018-05-18 14:09:20'),('7','1','12',0,'2018-07-31 01:19:13');

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '设备状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `status` */

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

insert  into `user`(`id`,`username`,`real_name`,`email`,`phone`,`address`,`locked`,`create_time`,`update_time`,`last_time`) values ('1526467363362171844','hyz','黄雅哲','609927332@qq.com','18728193205',' ',0,'2018-05-16 18:42:43','2018-05-16 18:42:43','2018-05-16 18:42:43'),('1532999182292648171','test','zzz','609927332@qq.com','11111111111','aaa',0,'2018-07-31 01:05:09','2018-07-31 01:05:09','2018-07-31 01:05:09');

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

insert  into `user_auth`(`id`,`user_id`,`identify_type`,`identifier`,`credential`,`locked`,`create_time`,`update_time`) values ('1526467363565941170','1526467363362171844',0,'hyz','123456',0,'2018-05-16 18:42:43','2018-05-16 18:42:43'),('1532999182370532626','1532999182292648171',0,'test','123456',0,'2018-07-31 01:05:09','2018-07-31 01:05:09');

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

insert  into `user_role`(`id`,`user_id`,`role_id`,`create_time`) values ('1526467363636855881','1526467363362171844','1','2018-05-16 18:42:43'),('1532999182464614155','1532999182292648171','1','2018-07-31 01:05:09');

/*Table structure for table `work_nature` */

DROP TABLE IF EXISTS `work_nature`;

CREATE TABLE `work_nature` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL COMMENT '工作性质',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `work_nature` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
