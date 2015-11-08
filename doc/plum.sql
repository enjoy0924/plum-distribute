/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : cas

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-11-08 21:26:19
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cas_app`
-- ----------------------------
DROP TABLE IF EXISTS `cas_app`;
CREATE TABLE `cas_app` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `app_key` varchar(100) DEFAULT NULL,
  `app_secret` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_cas_app_app_key` (`app_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_app
-- ----------------------------
INSERT INTO `cas_app` VALUES ('1', '中心服务器', '645ba616-370a-43a8-a8e0-993e7a590cf0', 'bb74abb6-bae0-47dd-a7b1-9571ea3a0f33', '1');
INSERT INTO `cas_app` VALUES ('2', 'APP-1', '645ba612-370a-43a8-a8e0-993e7a590cf0', 'bb74abb2-bae0-47dd-a7b1-9571ea3a0f33', '1');
INSERT INTO `cas_app` VALUES ('3', 'APP-2', '645ba613-370a-43a8-a8e0-993e7a590cf0', 'bb74abb3-bae0-47dd-a7b1-9571ea3a0f33', '1');

-- ----------------------------
-- Table structure for `cas_organization`
-- ----------------------------
DROP TABLE IF EXISTS `cas_organization`;
CREATE TABLE `cas_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_cas_organization_parent_id` (`parent_id`),
  KEY `idx_cas_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_organization
-- ----------------------------
INSERT INTO `cas_organization` VALUES ('1', '总公司', '0', '0/', '1');
INSERT INTO `cas_organization` VALUES ('2', '分公司1', '1', '0/1/', '1');
INSERT INTO `cas_organization` VALUES ('3', '分公司2', '1', '0/1/', '1');
INSERT INTO `cas_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '1');

-- ----------------------------
-- Table structure for `cas_resource`
-- ----------------------------
DROP TABLE IF EXISTS `cas_resource`;
CREATE TABLE `cas_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_cas_resource_parent_id` (`parent_id`),
  KEY `idx_cas_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_resource
-- ----------------------------
INSERT INTO `cas_resource` VALUES ('1', '资源', 'menu', '', '0', '0/', '', '1');
INSERT INTO `cas_resource` VALUES ('11', '组织机构管理', 'menu', '/organization', '1', '0/1/', 'organization:*', '1');
INSERT INTO `cas_resource` VALUES ('12', '组织机构新增', 'button', '', '11', '0/1/11/', 'organization:create', '1');
INSERT INTO `cas_resource` VALUES ('13', '组织机构修改', 'button', '', '11', '0/1/11/', 'organization:update', '1');
INSERT INTO `cas_resource` VALUES ('14', '组织机构删除', 'button', '', '11', '0/1/11/', 'organization:delete', '1');
INSERT INTO `cas_resource` VALUES ('15', '组织机构查看', 'button', '', '11', '0/1/11/', 'organization:view', '1');
INSERT INTO `cas_resource` VALUES ('16', '应用管理', 'menu', '/app', '1', '0/1/', 'app:*', '1');
INSERT INTO `cas_resource` VALUES ('17', '应用新增', 'button', '', '16', '0/1/16/', 'app:create', '1');
INSERT INTO `cas_resource` VALUES ('18', '应用修改', 'button', '', '16', '0/1/16/', 'app:update', '1');
INSERT INTO `cas_resource` VALUES ('19', '应用删除', 'button', '', '16', '0/1/16/', 'app:delete', '1');
INSERT INTO `cas_resource` VALUES ('20', '应用查看', 'button', '', '16', '0/1/16/', 'app:view', '1');
INSERT INTO `cas_resource` VALUES ('21', '用户管理', 'menu', '/user', '1', '0/1/', 'user:*', '1');
INSERT INTO `cas_resource` VALUES ('22', '用户新增', 'button', '', '21', '0/1/21/', 'user:create', '1');
INSERT INTO `cas_resource` VALUES ('23', '用户修改', 'button', '', '21', '0/1/21/', 'user:update', '1');
INSERT INTO `cas_resource` VALUES ('24', '用户删除', 'button', '', '21', '0/1/21/', 'user:delete', '1');
INSERT INTO `cas_resource` VALUES ('25', '用户查看', 'button', '', '21', '0/1/21/', 'user:view', '1');
INSERT INTO `cas_resource` VALUES ('31', '资源管理', 'menu', '/resource', '1', '0/1/', 'resource:*', '1');
INSERT INTO `cas_resource` VALUES ('32', '资源新增', 'button', '', '31', '0/1/31/', 'resource:create', '1');
INSERT INTO `cas_resource` VALUES ('33', '资源修改', 'button', '', '31', '0/1/31/', 'resource:update', '1');
INSERT INTO `cas_resource` VALUES ('34', '资源删除', 'button', '', '31', '0/1/31/', 'resource:delete', '1');
INSERT INTO `cas_resource` VALUES ('35', '资源查看', 'button', '', '31', '0/1/31/', 'resource:view', '1');
INSERT INTO `cas_resource` VALUES ('41', '角色管理', 'menu', '/role', '1', '0/1/', 'role:*', '1');
INSERT INTO `cas_resource` VALUES ('42', '角色新增', 'button', '', '41', '0/1/41/', 'role:create', '1');
INSERT INTO `cas_resource` VALUES ('43', '角色修改', 'button', '', '41', '0/1/41/', 'role:update', '1');
INSERT INTO `cas_resource` VALUES ('44', '角色删除', 'button', '', '41', '0/1/41/', 'role:delete', '1');
INSERT INTO `cas_resource` VALUES ('45', '角色查看', 'button', '', '41', '0/1/41/', 'role:view', '1');
INSERT INTO `cas_resource` VALUES ('51', '授权管理', 'menu', '/authorization', '1', '0/1/', 'authorization:*', '1');
INSERT INTO `cas_resource` VALUES ('52', '授权新增', 'button', '', '51', '0/1/51/', 'authorization:create', '1');
INSERT INTO `cas_resource` VALUES ('53', '授权修改', 'button', '', '51', '0/1/51/', 'authorization:update', '1');
INSERT INTO `cas_resource` VALUES ('54', '授权删除', 'button', '', '51', '0/1/51/', 'authorization:delete', '1');
INSERT INTO `cas_resource` VALUES ('55', '授权查看', 'button', '', '51', '0/1/51/', 'authorization:view', '1');

-- ----------------------------
-- Table structure for `cas_role`
-- ----------------------------
DROP TABLE IF EXISTS `cas_role`;
CREATE TABLE `cas_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_cas_role_resource_ids` (`resource_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_role
-- ----------------------------
INSERT INTO `cas_role` VALUES ('1', 'admin', '超级管理员', '11,16,21,31,41,51', '1');
INSERT INTO `cas_role` VALUES ('2', 'role1', 'APP1管理员', '11,16,21,31,41,51', '1');
INSERT INTO `cas_role` VALUES ('3', 'role2', 'APP2管理员', '11,16,21,31,41,51', '1');

-- ----------------------------
-- Table structure for `cas_sessions`
-- ----------------------------
DROP TABLE IF EXISTS `cas_sessions`;
CREATE TABLE `cas_sessions` (
  `id` varchar(200) NOT NULL DEFAULT '',
  `session` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_sessions
-- ----------------------------

-- ----------------------------
-- Table structure for `cas_token`
-- ----------------------------
DROP TABLE IF EXISTS `cas_token`;
CREATE TABLE `cas_token` (
  `id` varchar(0) DEFAULT NULL,
  `user_id` varchar(0) DEFAULT NULL COMMENT '用户的ID',
  `auth_token` varchar(0) DEFAULT NULL COMMENT '用于和客户端交换的token',
  `session_id` varchar(0) DEFAULT NULL COMMENT '访问客户端标识',
  `expire_time` int(11) DEFAULT NULL COMMENT 'token期过时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='同一客户端再次登录的时候不需要输入用户名和密码直接登录';

-- ----------------------------
-- Records of cas_token
-- ----------------------------

-- ----------------------------
-- Table structure for `cas_user`
-- ----------------------------
DROP TABLE IF EXISTS `cas_user`;
CREATE TABLE `cas_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_cas_user_username` (`username`),
  KEY `idx_cas_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_user
-- ----------------------------
INSERT INTO `cas_user` VALUES ('1', '1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '18983188515', 'enjoy0924@163.com', '0');

-- ----------------------------
-- Table structure for `cas_user_app_roles`
-- ----------------------------
DROP TABLE IF EXISTS `cas_user_app_roles`;
CREATE TABLE `cas_user_app_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `app_id` bigint(20) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cas_user_app_roles_user_id_app_id` (`user_id`,`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cas_user_app_roles
-- ----------------------------
INSERT INTO `cas_user_app_roles` VALUES ('1', '1', '1', '1');
INSERT INTO `cas_user_app_roles` VALUES ('2', '1', '2', '1,2');
INSERT INTO `cas_user_app_roles` VALUES ('3', '1', '3', '1,3');

-- ----------------------------
-- Table structure for `fs_ace`
-- ----------------------------
DROP TABLE IF EXISTS `fs_ace`;
CREATE TABLE `fs_ace` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `type` int(11) DEFAULT '2' COMMENT '1- 允许(Allowed); 2-禁止(Denied);3-待决(Undeterminated)',
  `role_or_user` varchar(0) DEFAULT NULL COMMENT ' 角色(组)名称或者个人ID',
  `perm` varchar(0) DEFAULT NULL COMMENT '"read" "write" "execute"',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fs_ace
-- ----------------------------

-- ----------------------------
-- Table structure for `fs_acl`
-- ----------------------------
DROP TABLE IF EXISTS `fs_acl`;
CREATE TABLE `fs_acl` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `fid` varchar(36) DEFAULT NULL COMMENT '文件(夹)的ID',
  `ace_ids` varchar(36) DEFAULT NULL COMMENT 'ACE列表集合',
  `inherited` int(11) NOT NULL COMMENT ' 是否集成自父级目录的ACL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fs_acl
-- ----------------------------

-- ----------------------------
-- Table structure for `fs_file`
-- ----------------------------
DROP TABLE IF EXISTS `fs_file`;
CREATE TABLE `fs_file` (
  `id` varchar(36) NOT NULL DEFAULT '',
  `permission` varchar(30) DEFAULT '-----------' COMMENT ' 这里使用linux的权限drwxr-xr-x-',
  `pid` varchar(36) DEFAULT NULL COMMENT '父级目录',
  `owner` varchar(30) DEFAULT NULL COMMENT '文件拥有者',
  `groups` varchar(64) DEFAULT NULL COMMENT ' 拥有者所在的群组',
  `check_sum` int(11) DEFAULT NULL COMMENT '如果是文件，文件的校验和',
  `size` int(11) DEFAULT NULL,
  `filename` varchar(64) DEFAULT NULL,
  `content_url` varchar(64) DEFAULT NULL COMMENT '如果是文件，文件的内容存储的URL',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fs_file
-- ----------------------------
