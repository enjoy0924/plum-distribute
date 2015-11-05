/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : cas

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-11-05 20:12:40
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
INSERT INTO `cas_sessions` VALUES ('0df7b372-e34f-4882-a565-d03e7ee83688', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDBkZjdiMzcyLWUzNGYtNDg4Mi1hNTY1LWQwM2U3ZWU4MzY4OHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBCS7KOeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('11dc97d5-dc64-4453-a289-978565c5a919', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDExZGM5N2Q1LWRjNjQtNDQ1My1hMjg5LTk3ODU2NWM1YTkxOXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcKm70eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('1474b6af-e5ca-428d-a88e-d7dd52e48f5e', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDE0NzRiNmFmLWU1Y2EtNDI4ZC1hODhlLWQ3ZGQ1MmU0OGY1ZXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBCNLrKeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('14756c73-500d-438e-8087-3bc0f4d8917e', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDE0NzU2YzczLTUwMGQtNDM4ZS04MDg3LTNiYzBmNGQ4OTE3ZXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcOar4eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('1788d92a-f01d-420d-828c-08653dfa5994', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDE3ODhkOTJhLWYwMWQtNDIwZC04MjhjLTA4NjUzZGZhNTk5NHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcaPqKeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('25ac5faf-72cc-4c4e-8f3d-25d77b80d359', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDI1YWM1ZmFmLTcyY2MtNGM0ZS04ZjNkLTI1ZDc3YjgwZDM1OXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcWzL1eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('30f9deb5-f5bf-4edf-a00d-08ee3b159e9b', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDMwZjlkZWI1LWY1YmYtNGVkZi1hMDBkLTA4ZWUzYjE1OWU5YnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcXhtceHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('36fad832-4e11-45de-a898-c6bb3607630f', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDM2ZmFkODMyLTRlMTEtNDVkZS1hODk4LWM2YmIzNjA3NjMwZnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBWpuyBeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('3c996090-6d4c-4d71-9655-d71297db517a', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDNjOTk2MDkwLTZkNGMtNGQ3MS05NjU1LWQ3MTI5N2RiNTE3YXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcPYSieHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('5127f5b0-857a-4472-8d92-c36dd5ad813c', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDUxMjdmNWIwLTg1N2EtNDQ3Mi04ZDkyLWMzNmRkNWFkODEzY3NyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBWfxK0eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('62f715c1-fa62-40b6-9283-d43137e30ef5', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDYyZjcxNWMxLWZhNjItNDBiNi05MjgzLWQ0MzEzN2UzMGVmNXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcOwkCeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('7d752f3e-caf4-472f-8fa5-71a5cc1d96e0', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDdkNzUyZjNlLWNhZjQtNDcyZi04ZmE1LTcxYTVjYzFkOTZlMHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcY9JxeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('85a90207-637e-477c-844f-b89f9dfee542', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDg1YTkwMjA3LTYzN2UtNDc3Yy04NDRmLWI4OWY5ZGZlZTU0MnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcXPJ/eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('99965209-8af1-4c04-a8c9-bea78602ba77', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDk5OTY1MjA5LThhZjEtNGMwNC1hOGM5LWJlYTc4NjAyYmE3N3NyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBB109leHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('9d97b0ea-4198-4fe8-a108-da746faab175', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJDlkOTdiMGVhLTQxOTgtNGZlOC1hMTA4LWRhNzQ2ZmFhYjE3NXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcZ4o/eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('a513d1d3-9955-4cac-bff9-7bff80a9d267', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGE1MTNkMWQzLTk5NTUtNGNhYy1iZmY5LTdiZmY4MGE5ZDI2N3NyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcM2PEeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('a66d979d-e129-44ec-b5b9-a841c8d37518', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGE2NmQ5NzlkLWUxMjktNDRlYy1iNWI5LWE4NDFjOGQzNzUxOHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcXcsjeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('abf7a24e-d57d-43f9-a2ad-63d1c93e5bbc', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGFiZjdhMjRlLWQ1N2QtNDNmOS1hMmFkLTYzZDFjOTNlNWJiY3NyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBWiIE0eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('ae8c1023-5414-4504-bdda-89862a10ee0a', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGFlOGMxMDIzLTU0MTQtNDUwNC1iZGRhLTg5ODYyYTEwZWUwYXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcQKF2eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('d7b11703-cac1-47cd-ba66-beb2517c7f4f', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGQ3YjExNzAzLWNhYzEtNDdjZC1iYTY2LWJlYjI1MTdjN2Y0ZnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcWteNeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('de0e957b-bea2-42e1-b94d-b37e05721f39', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGRlMGU5NTdiLWJlYTItNDJlMS1iOTRkLWIzN2UwNTcyMWYzOXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcYRB/eHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('e22e66ca-331d-4bbc-8091-3eec4b97fa39', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGUyMmU2NmNhLTMzMWQtNGJiYy04MDkxLTNlZWM0Yjk3ZmEzOXNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcY4IEeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('fbe47423-f7ef-459f-9ebb-13ed0358e98d', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGZiZTQ3NDIzLWY3ZWYtNDU5Zi05ZWJiLTEzZWQwMzU4ZTk4ZHNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBWpReVeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');
INSERT INTO `cas_sessions` VALUES ('ff5ab9ab-72d7-425d-a96a-aab3bf36cff2', 'rO0ABXNyACpvcmcuYXBhY2hlLnNoaXJvLnNlc3Npb24ubWd0LlNpbXBsZVNlc3Npb26dHKG41YxibgMAAHhwdwIAW3QAJGZmNWFiOWFiLTcyZDctNDI1ZC1hOTZhLWFhYjNiZjM2Y2ZmMnNyAA5qYXZhLnV0aWwuRGF0ZWhqgQFLWXQZAwAAeHB3CAAAAVBcXsSjeHEAfgAEdxkAAAAAABt3QAAPMDowOjA6MDowOjA6MDoxeA==');

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
