/*
Navicat MySQL Data Transfer

Source Server         : 101.42.242.227
Source Server Version : 50633
Source Host           : 101.42.242.227:3306
Source Database       : draven_db

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2022-04-06 15:48:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for error_log
-- ----------------------------
DROP TABLE IF EXISTS `error_log`;
CREATE TABLE `error_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_uuid` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `msg` text COLLATE utf8_bin,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
