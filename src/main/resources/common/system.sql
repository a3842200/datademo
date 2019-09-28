/*
Navicat MySQL Data Transfer

Source Server         : heima
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : basedate

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-09-29 01:25:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `id` int(11) NOT NULL,
  `system` varchar(50) DEFAULT NULL COMMENT '系统名',
  `secret` varchar(50) DEFAULT NULL COMMENT '秘钥',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system
-- ----------------------------
