DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(48) NOT NULL COMMENT '应用标识',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '资源限定串(逗号分割)',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '应用密钥(bcyt) 加密',
  `client_secret_str` varchar(256) DEFAULT NULL COMMENT '应用密钥(明文)',
  `scope` varchar(256) DEFAULT NULL COMMENT '范围',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL COMMENT '回调地址 ',
  `authorities` varchar(256) DEFAULT NULL COMMENT '权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT 'access_token有效期',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT 'refresh_token有效期',
  `additional_information` varchar(4096) DEFAULT '{}' COMMENT '{}',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '是否自动授权 是-true',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;