/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.39 : Database - scp_admins
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `scp_admins`;

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `admin_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `username` varchar(50) NOT NULL COMMENT '管理员账号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `avatar` varchar(1000) DEFAULT NULL COMMENT '头像',
  `name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `create_admin_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `enabled` bit(1) DEFAULT b'1' COMMENT '状态 1正常 0禁用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `uni_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`admin_id`,`username`,`password`,`nickname`,`mobile`,`email`,`avatar`,`name`,`create_admin_id`,`enabled`,`create_time`,`update_time`) values

(1,'admin','admin','管理员','17377878899','hmiter@sina.com','https://img.mgtv.com/imgotv-member/user/avt.jpg',NULL,0,'','2018-12-21 17:17:58','2018-12-24 16:17:34');

/*Table structure for table `tb_admin_role` */

DROP TABLE IF EXISTS `tb_admin_role`;

CREATE TABLE `tb_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与角色对应关系';

/*Data for the table `tb_admin_role` */

/*Table structure for table `tb_config` */

DROP TABLE IF EXISTS `tb_config`;

CREATE TABLE `tb_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT 'key',
  `value` varchar(2000) DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

/*Table structure for table `tb_dict` */

DROP TABLE IF EXISTS `tb_dict`;

CREATE TABLE `tb_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态，1正常 0禁用',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';


/*Table structure for table `tb_menu` */

DROP TABLE IF EXISTS `tb_menu`;

CREATE TABLE `tb_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `tb_menu` */

insert  into `tb_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`sort`) values
(1,0,'系统管理',NULL,NULL,0,'system',1),
(2,1,'管理员列表','admins/admin',NULL,1,'user',1),
(3,1,'角色管理','admins/role',NULL,1,'role',2),
(4,1,'菜单管理','admins/menu',NULL,1,'menu',3),
(5,2,'查看',NULL,'admins:admin:view',2,NULL,1),
(6,2,'新增',NULL,'admins:admin:save',2,NULL,2),
(7,2,'修改',NULL,'admins:admin:update',2,NULL,3),
(8,2,'删除',NULL,'admins:admin:delete',2,NULL,4),
(9,3,'查看',NULL,'admins:role:view',2,NULL,1),
(10,3,'新增',NULL,'admins:role:save',2,NULL,2),
(11,3,'修改',NULL,'admins:role:update',2,NULL,3),
(12,3,'删除',NULL,'admins:role:delete',2,NULL,4),
(13,4,'查看',NULL,'admins:menu:view',2,NULL,1),
(14,4,'新增',NULL,'admins:menu:save',2,NULL,2),
(15,4,'修改',NULL,'admins:menu:update',2,NULL,3),
(16,4,'删除',NULL,'admins:menu:delete',2,NULL,4),
(18,1,'参数管理','admins/config',NULL,1,'config',4),
(19,18,'查看',NULL,'admins:config:view',2,NULL,1),
(20,18,'新增',NULL,'admins:config:save',2,NULL,2),
(21,18,'修改',NULL,'admins:config:update',2,NULL,3),
(22,18,'删除',NULL,'admins:config:delete',2,NULL,4),
(23,1,'字典管理','admins/dict',NULL,1,'dict',5),
(24,23,'查看',NULL,'admins:dict:view',2,NULL,1),
(25,23,'新增',NULL,'admins:dict:save',2,NULL,2),
(26,23,'修改',NULL,'admins:dict:update',2,NULL,3),
(27,23,'删除',NULL,'admins:dict:delete',2,NULL,4),
(28,1,'文件管理',NULL,'admins:files:view',1,'file',6),
(29,1,'客户端管理','security/clientdetails',NULL,1,'app',7),
(30,29,'查看',NULL,'security:clientdetails:view',2,NULL,1),
(31,29,'新增',NULL,'security:clientdetails:save',2,NULL,2),
(32,29,'修改',NULL,'security:clientdetails:update',2,NULL,3),
(33,29,'删除',NULL,'security:clientdetails:delete',2,NULL,4),
(50,0,'系统监控',NULL,NULL,0,'monitor',2),
(51,50,'注册中心','http://localhost:7000/',NULL,1,NULL,1),
(52,50,'服务监控','http://localhost:7040/#/wallboard',NULL,1,NULL,2),
(53,50,'hystrix监控','http://localhost:7041/hystrix',NULL,1,NULL,3),
(54,50,'zipkin监控','http://localhost:7042/zipkin/',NULL,1,NULL,4),
(55,50,'接口文档','http://localhost:7010/swagger-ui.html',NULL,1,NULL,5);

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_admin_id` bigint(20) DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

/*Data for the table `tb_role` */

/*Table structure for table `tb_role_menu` */

DROP TABLE IF EXISTS `tb_role_menu`;

CREATE TABLE `tb_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

/*Data for the table `tb_role_menu` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
