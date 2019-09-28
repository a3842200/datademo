/*
Navicat MySQL Data Transfer

Source Server         : heima
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : basedate

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-09-29 01:25:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL COMMENT 'z',
  `type` varchar(50) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL COMMENT '父ID，0为根节点',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
