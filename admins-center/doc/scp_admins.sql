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
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

/*Data for the table `tb_menu` */

insert  into `tb_menu`(`menu_id`,`parent_id`,`name`,`url`,`perms`,`type`,`icon`,`order_num`) values
(1,0,'系统管理',NULL,NULL,0,'system',0),
(2,1,'管理员管理','admins/admin',NULL,1,'admin',1),
(3,1,'角色管理','admins/role',NULL,1,'role',2),
(4,1,'菜单管理','admins/menu',NULL,1,'menu',3),
(5,2,'查看',NULL,'admins:admin:view',2,NULL,0),
(6,2,'新增',NULL,'admins:admin:save',2,NULL,0),
(7,2,'修改',NULL,'admins:admin:update',2,NULL,0),
(8,2,'删除',NULL,'admins:admin:delete',2,NULL,0),
(9,3,'查看',NULL,'admins:role:view',2,NULL,0),
(10,3,'新增',NULL,'admins:role:save',2,NULL,0),
(11,3,'修改',NULL,'admins:role:update',2,NULL,0),
(12,3,'删除',NULL,'admins:role:delete',2,NULL,0),
(13,4,'查看',NULL,'admins:menu:view',2,NULL,0),
(14,4,'新增',NULL,'admins:menu:save',2,NULL,0),
(15,4,'修改',NULL,'admins:menu:update',2,NULL,0),
(16,4,'删除',NULL,'admins:menu:delete',2,NULL,0),
(17,1,'客户端管理','security/clientdetails',NULL,1,'app',6),
(18,17,'查看',NULL,'security:clientdetails:view',2,NULL,6),
(19,17,'新增',NULL,'security:clientdetails:save',2,NULL,6),
(20,17,'修改',NULL,'security:clientdetails:update',2,NULL,6),
(21,17,'删除',NULL,'security:clientdetails:delete',2,NULL,6),
(22,0,'用户管理',NULL,NULL,0,'users',1),
(23,22,'用户列表','users/user',NULL,1,'user',6),
(24,23,'查看',NULL,'users:user:view',2,NULL,6),
(25,23,'新增',NULL,'users:user:save',2,NULL,6),
(26,23,'修改',NULL,'users:user:update',2,NULL,6),
(27,23,'删除',NULL,'users:user:delete',2,NULL,6);

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
