/*
Navicat MySQL Data Transfer

Source Server         : 101.42.242.227
Source Server Version : 50633
Source Host           : 101.42.242.227:3306
Source Database       : draven_db

Target Server Type    : MYSQL
Target Server Version : 50633
File Encoding         : 65001

Date: 2022-04-01 17:54:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for alertdetail_hadoop_draven
-- ----------------------------
DROP TABLE IF EXISTS `alertdetail_hadoop_draven`;
CREATE TABLE `alertdetail_hadoop_draven` (
                                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                             `timestamp` varchar(100) DEFAULT NULL,
                                             `site` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `application` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `hostname` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `policyid` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `alertsource` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `sourcestreams` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `alertexecutorid` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `remediationid` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `remediationcallback` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `alertcontext` text CHARACTER SET utf8 COLLATE utf8_bin,
                                             `streamid` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
                                             `create_time` datetime DEFAULT NULL,
                                             `update_time` datetime DEFAULT NULL,
                                             `alert_type` int(11) DEFAULT NULL,
                                             `is_mark` tinyint(10) DEFAULT NULL,
                                             `mark_values` text,
                                             PRIMARY KEY (`id`) USING BTREE,
                                             UNIQUE KEY `uuid_UNIQUE` (`id`) USING BTREE,
                                             KEY `alertdetail_hadoop_application_site_timestamp` (`application`,`site`,`timestamp`),
                                             KEY `index_alert_type` (`alert_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1233 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Ueba告警日志';

-- ----------------------------
-- Records of alertdetail_hadoop_draven
-- ----------------------------
INSERT INTO `alertdetail_hadoop_draven` VALUES ('1056', '1642056177000', 'sanbox', null, '2.2.2.2', 'test3', 'xy121', null, 'hdfsAuditLogAlertExecutor', null, null, null, 0x7B6473743D616161616161612C207372633D2F7373642F746573742E7478742C2069703D322E322E322E322C207069643D31393439332C2074696D655F72657765696768743D352C20636D643D362C2070696C6963795F69643D74657374332C20757365723D78793132312C20707069643D3838382C2074696D657374616D703D313634323035363137373030307D, null, '2022-03-29 15:08:21', null, null, null, null);
INSERT INTO `alertdetail_hadoop_draven` VALUES ('1057', '1642056177000', 'sanbox', null, '2.2.2.2', 'test2', 'xy121', null, 'hdfsAuditLogAlertExecutor', null, null, null, 0x7B6473743D616161616161612C207372633D2F7373642F746573742E7478742C2069703D322E322E322E322C207069643D31393439332C2074696D655F72657765696768743D352C20636D643D362C2070696C6963795F69643D74657374322C20757365723D78793132312C20707069643D3838382C2074696D657374616D703D313634323035363137373030307D, null, '2022-03-29 15:08:26', null, null, null, null);
INSERT INTO `alertdetail_hadoop_draven` VALUES ('1058', '1642056177000', 'sanbox', null, '192.168.1.195', 'test3', 'xy121', null, 'hdfsAuditLogAlertExecutor', null, null, null, 0x7B6473743D34642C207372633D2F6465762F6E756C6C2C2069703D3139322E3136382E312E3139352C207069643D31393439332C2074696D655F72657765696768743D352C20636D643D362C2070696C6963795F69643D74657374332C20757365723D78793132312C20707069643D3838382C2074696D657374616D703D313634323035363137373030307D, null, '2022-03-29 15:40:28', null, null, null, null);
