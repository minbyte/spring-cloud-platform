/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.39 : Database - scp_user
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `scp_user`;

DROP TABLE IF EXISTS `tb_token`;

CREATE TABLE `tb_token` (
  `token` varchar(128) NOT NULL,
  `user_id` bigint(64) NOT NULL,
  `expire_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_token` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` int(32) NOT NULL COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户账号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮箱',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `avatar` varchar(1000) NOT NULL COMMENT '头像',
  `name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `sex` int(1) DEFAULT '0' COMMENT '性别 0未知，1男，2女',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `county` varchar(32) DEFAULT NULL COMMENT '县',
  `address` varchar(256) DEFAULT NULL COMMENT '详细地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uni_username` (`username`),
  UNIQUE KEY `uni_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `tb_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
