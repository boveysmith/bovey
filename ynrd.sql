/*
Navicat MySQL Data Transfer

Source Server         : zhanchuan_localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : ynrd

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2020-08-04 16:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `permission` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES ('1', 'admin', '*');
INSERT INTO `roles_permissions` VALUES ('2', 'cmanager', 'sys:c:save');
INSERT INTO `roles_permissions` VALUES ('3', 'cmanager', 'sys:c:delete');
INSERT INTO `roles_permissions` VALUES ('4', 'cmanager', 'sys:c:update');
INSERT INTO `roles_permissions` VALUES ('5', 'cmanager', 'sys:c:find');
INSERT INTO `roles_permissions` VALUES ('6', 'xmanager', 'sys:x:save');
INSERT INTO `roles_permissions` VALUES ('7', 'xmanager', 'sys:x:update');
INSERT INTO `roles_permissions` VALUES ('8', 'xmanager', 'sys:x:delete');
INSERT INTO `roles_permissions` VALUES ('9', 'xmanager', 'sys:x:find');
INSERT INTO `roles_permissions` VALUES ('10', 'kmanager', 'sys:k:save');
INSERT INTO `roles_permissions` VALUES ('11', 'kmanager', 'sys:k:update');
INSERT INTO `roles_permissions` VALUES ('12', 'kmanager', 'sys:k:delete');
INSERT INTO `roles_permissions` VALUES ('13', 'kmanager', 'sys:k:find');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `password_salt` varchar(100) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '张三', '123456', null, '1');
INSERT INTO `users` VALUES ('2', '李四', '123456', null, '2');
INSERT INTO `users` VALUES ('3', '王五', '123456', null, '1');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(36) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '张三', 'admin');
INSERT INTO `user_roles` VALUES ('2', '李四', 'cmanager');
INSERT INTO `user_roles` VALUES ('3', '王五', 'xmanager');
INSERT INTO `user_roles` VALUES ('4', '马六', 'kmanager');
