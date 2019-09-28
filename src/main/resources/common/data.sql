/*
Navicat MySQL Data Transfer

Source Server         : heima
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : basedate

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2019-09-29 01:25:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data
-- ----------------------------
DROP TABLE IF EXISTS `data`;
CREATE TABLE `data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(50) DEFAULT NULL COMMENT '数据的key',
  `value` varchar(50) DEFAULT NULL COMMENT '数据的value',
  `sourceId` int(11) DEFAULT NULL COMMENT '数据来源Id',
  `parentId` int(11) DEFAULT NULL COMMENT '父Id,为0时是根节点',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data
-- ----------------------------
