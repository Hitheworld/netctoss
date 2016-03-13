/*
SQLyog v10.2 
MySQL - 5.6.25 : Database - netctoss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`netctoss` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `netctoss`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `ACCOUNT_ID` int(9) NOT NULL COMMENT '帐务帐号ID',
  `RECOMMENDER_ID` int(9) DEFAULT NULL COMMENT '推荐人帐务帐号ID',
  `LOGIN_NAME` varchar(30) NOT NULL COMMENT '登录NetCTOSS系统的名称,用于用户自服务',
  `LOGIN_PASSWD` varbinary(30) NOT NULL COMMENT '登录NetCTOSS的口令',
  `STATUS` char(1) NOT NULL COMMENT '0：开通;1暂停;2删除',
  `CREATE_DATE` date DEFAULT NULL COMMENT '创建日期',
  `PAUSE_DATE` date DEFAULT NULL COMMENT '暂停日期(开通状态时为空)',
  `CLOSE_DATE` date DEFAULT NULL COMMENT '删除日期',
  `REAL_NAME` varchar(20) NOT NULL COMMENT '客户姓名',
  `IDCARD_NO` char(18) NOT NULL COMMENT '身份证号码',
  `BIRTHDATE` date DEFAULT NULL COMMENT '出生日期',
  `GENDER` char(1) NOT NULL COMMENT '性别：0男，1女',
  `OCCUPATION` varchar(50) DEFAULT NULL COMMENT '职业',
  `TELEPHONE` varchar(15) NOT NULL COMMENT '联系电话(座机或手机)',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `MAILADDRESS` varchar(200) DEFAULT NULL COMMENT '通信地址',
  `ZIPCODE` char(6) DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'QQ',
  `LAST_LOGIN_TIME` date DEFAULT NULL COMMENT '最后一次登录时间',
  `LAST_LOGIN_IP` varchar(15) DEFAULT NULL COMMENT '最后一次登录IP地址',
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `ADMIN_ID` int(4) NOT NULL AUTO_INCREMENT,
  `ADMIN_CODE` varchar(30) DEFAULT NULL COMMENT '管理员帐号',
  `PASSWORD` varchar(35) NOT NULL COMMENT '密码',
  `NAME` varchar(30) NOT NULL COMMENT '姓名',
  `TELPHONE` varchar(15) DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '电子邮件',
  `ENROLLDATE` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=10;

/*Data for the table `admin_info` */

insert  into `admin_info`(`ADMIN_ID`,`ADMIN_CODE`,`PASSWORD`,`NAME`,`TELPHONE`,`EMAIL`,`ENROLLDATE`) values (100,'tmm','87654321','李连杰','15955552222','tmm@admin.com','2015-06-23'),(101,'xlz','25d55ad283aa400af464c76d713c07ad','李22','18227632250','lm@tml.com','2015-06-23'),(102,'admin','25d55ad283aa400af464c76d713c07ad','系统管理员','15000000000','admin@admin.com','2015-08-12'),(103,'root','25d55ad283aa400af464c76d713c07ad','椰子','15000000000','yz@tml.com','2015-08-06'),(104,'admin88','25d55ad283aa400af464c76d713c07ad','廓清','15000000000','lq@tml.com','2015-08-06'),(105,'admin1','25d55ad283aa400af464c76d713c07ad','春村','15000000000','cs@tml.com','2015-08-14'),(110,'lp5','12345678','刘那个备','1500000000','lp001@admin.com','2015-08-28'),(113,'lp001','12345678,12345678','刘备1','110','110@admin.com','2015-08-28');

/*Table structure for table `admin_role` */

DROP TABLE IF EXISTS `admin_role`;

CREATE TABLE `admin_role` (
  `ADMIN_ID` int(8) NOT NULL COMMENT '管理员ID，关联管理员表联合主键',
  `ROLE_ID` int(4) NOT NULL COMMENT '角色ID，关联角色表联合主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin_role` */

insert  into `admin_role`(`ADMIN_ID`,`ROLE_ID`) values (100,1),(100,3),(101,1),(101,2),(101,3),(102,1),(102,3),(103,3),(104,2),(105,3),(105,3),(113,4),(113,6);

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL COMMENT '主键,帐单ID',
  `account_id` int(9) NOT NULL COMMENT '帐务帐号ID，关联帐号信息表',
  `bill_month` char(6) NOT NULL COMMENT '帐单月份，格式',
  `cost` double(13,2) NOT NULL COMMENT '费用',
  `payment_mode` char(1) DEFAULT NULL COMMENT '0现金，1银行转帐，2邮局汇款，3其它',
  `pay_state` char(1) DEFAULT NULL COMMENT '支付状态：0未支付，1已支付，默认0',
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bill` */

/*Table structure for table `bill_item` */

DROP TABLE IF EXISTS `bill_item`;

CREATE TABLE `bill_item` (
  `item_id` int(11) NOT NULL COMMENT '主键，帐单条目ID',
  `bill_id` int(11) NOT NULL COMMENT '帐单ID，关联帐单信息表',
  `service_id` int(10) NOT NULL COMMENT '业务帐号ID，关联业务信息表',
  `cost` double(13,2) DEFAULT NULL COMMENT '所花费的费用',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `bill_item` */

/*Table structure for table `cost` */

DROP TABLE IF EXISTS `cost`;

CREATE TABLE `cost` (
  `COST_ID` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键,资费ID',
  `NAME` varchar(50) NOT NULL COMMENT '资费名称',
  `BASE_DURATION` int(11) DEFAULT NULL COMMENT '包在线时长',
  `BASE_COST` double(7,2) DEFAULT NULL COMMENT '月固定费，可能有小数',
  `UNIT_COST` double(7,4) DEFAULT NULL COMMENT '单位费用(元/小时)',
  `STATUS` char(1) NOT NULL COMMENT '0:开通；1：暂时',
  `DESCR` varchar(100) DEFAULT NULL COMMENT '对资费信息的说明',
  `CREATIME` date DEFAULT NULL COMMENT '创建日期',
  `STARTIME` date DEFAULT NULL COMMENT '启用日期',
  `COST_TYPE` char(1) DEFAULT NULL COMMENT '1-包月;2-套餐;3-计时',
  PRIMARY KEY (`COST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `cost` */

insert  into `cost`(`COST_ID`,`NAME`,`BASE_DURATION`,`BASE_COST`,`UNIT_COST`,`STATUS`,`DESCR`,`CREATIME`,`STARTIME`,`COST_TYPE`) values (1,'包月',1,NULL,1.0000,'1','1','2015-08-05','2015-08-17','1'),(3,'包月份1',NULL,NULL,1.0000,'1','1','2015-08-05','2015-08-11',NULL),(4,'2',NULL,NULL,NULL,'1','222222','2015-08-27','2015-08-11','0'),(6,'自费',1,NULL,1.0000,'0','111','2015-08-12','2015-08-12','0'),(7,'一掷千金',1,NULL,1.0000,'0','111','2015-08-19','2015-08-11','0'),(8,'二111',1,NULL,1.0000,'1','1','2015-08-20',NULL,'2'),(9,'下放地物',1,NULL,1.0000,'0','1',NULL,'2015-08-11','2'),(10,'生气及于',12,NULL,1.0000,'0','1',NULL,'2015-08-11','2'),(11,'二环氧树脂物',NULL,NULL,NULL,'1','414442',NULL,NULL,'1'),(12,'53245353',53,NULL,5.0000,'1','555',NULL,NULL,'2'),(13,'5365393',5,NULL,5.0000,'0','5',NULL,NULL,'2'),(14,'54353',55,NULL,0.2500,'0','5678',NULL,'2015-08-11','2'),(15,'5636',858,NULL,54.2500,'1','545635',NULL,NULL,'2'),(16,'223',323,NULL,NULL,'1','333',NULL,NULL,'1'),(19,'111',NULL,NULL,NULL,'1','','2014-01-01',NULL,NULL),(20,'包月资费项目',23,20.00,3.0200,'1','包月资费说明:包一个月，超出部分时间按3.02元收取!','2014-01-01','2015-08-17','2'),(21,'1111111',NULL,NULL,NULL,'1','','2015-08-11','2015-08-11','2'),(22,'778',1,NULL,NULL,'1','','2015-08-11','2015-08-11','2'),(23,'套餐2',1,NULL,1.0000,'1','111',NULL,NULL,'2'),(24,'套餐3',1,NULL,1.0000,'1','1',NULL,NULL,'2');

/*Table structure for table `function_info` */

DROP TABLE IF EXISTS `function_info`;

CREATE TABLE `function_info` (
  `FUNCTION_ID` int(8) NOT NULL AUTO_INCREMENT COMMENT '功能ID',
  `FUNCTION_CODE` varchar(50) NOT NULL COMMENT '功能编码',
  `MODULE_ID` int(4) NOT NULL COMMENT '所属的模板ID，关联模板表',
  `NAME` varchar(50) NOT NULL COMMENT '功能名称',
  `URL` varchar(100) NOT NULL COMMENT '功能对应的URL地址',
  PRIMARY KEY (`FUNCTION_ID`),
  KEY `fk_1` (`MODULE_ID`),
  CONSTRAINT `fk_1` FOREIGN KEY (`MODULE_ID`) REFERENCES `module_info` (`MODULE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `function_info` */

insert  into `function_info`(`FUNCTION_ID`,`FUNCTION_CODE`,`MODULE_ID`,`NAME`,`URL`) values (1,'FEE_LIST',1,'资费列表','fee/fee_list.jsp'),(2,'FEE_ADD',1,'增加资费','fee/fee_add.jsp'),(3,'ACCOUNT_LIST',2,'帐务帐号列表','account/account_list.jsp'),(4,'SERVICe_LIST',3,'业务帐号列表','service/service_list.jsp'),(5,'SERVICe_ADD',3,'增加业务帐号','service/service_add.jsp'),(7,'11',2,'删除帐号','delect/fee/1/2'),(8,'bb',2,'cccc','http//');

/*Table structure for table `host` */

DROP TABLE IF EXISTS `host`;

CREATE TABLE `host` (
  `HOST_ID` varchar(15) NOT NULL COMMENT '主键，记录unix服务器的ip地址',
  `NAME` varchar(50) NOT NULL COMMENT '主机名',
  `LOCATION` varchar(100) DEFAULT NULL COMMENT '主机所在位置',
  PRIMARY KEY (`HOST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `host` */

/*Table structure for table `module_info` */

DROP TABLE IF EXISTS `module_info`;

CREATE TABLE `module_info` (
  `MODULE_ID` int(4) NOT NULL AUTO_INCREMENT COMMENT '模块ID',
  `NAME` varchar(50) NOT NULL COMMENT '模板名称',
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `module_info` */

insert  into `module_info`(`MODULE_ID`,`NAME`) values (1,'资费管理'),(2,'帐务管理'),(3,'业务管理');

/*Table structure for table `role_info` */

DROP TABLE IF EXISTS `role_info`;

CREATE TABLE `role_info` (
  `ROLE_ID` int(4) NOT NULL AUTO_INCREMENT COMMENT '主键，角色ID',
  `NAME` varchar(50) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 AVG_ROW_LENGTH=100;

/*Data for the table `role_info` */

insert  into `role_info`(`ROLE_ID`,`NAME`) values (1,'超级管理员'),(2,'资费管理员'),(3,'帐务帐号管理员'),(4,'测试角色'),(5,'角色管理4'),(6,'角色5');

/*Table structure for table `role_module` */

DROP TABLE IF EXISTS `role_module`;

CREATE TABLE `role_module` (
  `ROLE_ID` int(4) NOT NULL COMMENT '角色ID，关联角色表联合主键',
  `MODULE_ID` int(4) NOT NULL COMMENT '模块ID,关联模块表联合主键',
  PRIMARY KEY (`ROLE_ID`,`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_module` */

insert  into `role_module`(`ROLE_ID`,`MODULE_ID`) values (1,1),(1,2),(2,1),(3,1),(3,2),(4,3),(5,3),(6,1),(7,3),(8,1),(8,3);

/*Table structure for table `role_privilege` */

DROP TABLE IF EXISTS `role_privilege`;

CREATE TABLE `role_privilege` (
  `ROLE_ID` int(100) NOT NULL,
  `PRIVILEGE_ID` int(100) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PRIVILEGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_privilege` */

insert  into `role_privilege`(`ROLE_ID`,`PRIVILEGE_ID`) values (100,1),(100,2),(100,3),(200,1),(300,1),(300,2),(400,3);

/*Table structure for table `service` */

DROP TABLE IF EXISTS `service`;

CREATE TABLE `service` (
  `SERVIE_ID` int(10) NOT NULL COMMENT '业务帐号ID',
  `ACCOUNT_ID` int(9) NOT NULL COMMENT '帐务帐号ID，关联帐务信息表',
  `UNIX_HOST` varchar(15) NOT NULL COMMENT 'UNIX服务器IP地址',
  `OS_USERNAME` varchar(8) NOT NULL COMMENT '登录UNIX服务器的OS帐号',
  `LOGIN_PASSWD` varchar(30) NOT NULL COMMENT '登录UNIX服务器的口令',
  `STATUS` char(1) NOT NULL COMMENT '0开通;1暂停;2删除',
  `CREATE_DATE` date NOT NULL COMMENT '创建日期，创建即开通',
  `PAUSE_DATE` date DEFAULT NULL COMMENT '暂停日期',
  `CLOSE_DATE` date DEFAULT NULL COMMENT '删除日期',
  `COST_ID` varchar(4) NOT NULL COMMENT '资费编码,关联资费信息表',
  PRIMARY KEY (`SERVIE_ID`,`ACCOUNT_ID`,`UNIX_HOST`,`OS_USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service` */

/*Table structure for table `service_detail` */

DROP TABLE IF EXISTS `service_detail`;

CREATE TABLE `service_detail` (
  `detail_id` int(11) NOT NULL COMMENT '主键，业务详单ID',
  `service_id` int(10) NOT NULL COMMENT '业务帐号ID，关联业务信息表',
  `client_host` varchar(15) DEFAULT NULL COMMENT 'OS帐号从该IP地址登录UNIX服务器',
  `os_username` varchar(8) DEFAULT NULL COMMENT 'UNIX服务器上的OS帐号',
  `pid` int(11) DEFAULT NULL COMMENT '进程号',
  `login_time` date DEFAULT NULL COMMENT '开始登录时间',
  `logout_time` date DEFAULT NULL COMMENT '退出登录时间',
  `duration` double(20,9) DEFAULT NULL COMMENT '时长(秒)',
  `cost` double(20,6) DEFAULT NULL COMMENT '费用',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `service_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
