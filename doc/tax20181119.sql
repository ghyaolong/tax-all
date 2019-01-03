/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost
 Source Database       : tax

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : utf-8

 Date: 11/19/2018 22:46:20 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `QRTZ_BLOB_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_CALENDARS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_CRON_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `QRTZ_CRON_TRIGGERS`
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', 'DEFAULT', '0/10 * * * * ? ', 'Asia/Shanghai'), ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.SampleJob', 'DEFAULT', '0/5 * * * * ? ', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
--  Table structure for `QRTZ_FIRED_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_JOB_DETAILS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `QRTZ_JOB_DETAILS`
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', 'DEFAULT', null, 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c77080000001000000001740009706172616d65746572707800), ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.SampleJob', 'DEFAULT', null, 'com.chinasoft.tax.quartz.jobs.SampleJob', '0', '0', '0', '0', 0xaced0005737200156f72672e71756172747a2e4a6f62446174614d61709fb083e8bfa9b0cb020000787200266f72672e71756172747a2e7574696c732e537472696e674b65794469727479466c61674d61708208e8c3fbc55d280200015a0013616c6c6f77735472616e7369656e74446174617872001d6f72672e71756172747a2e7574696c732e4469727479466c61674d617013e62ead28760ace0200025a000564697274794c00036d617074000f4c6a6176612f7574696c2f4d61703b787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c77080000001000000001740009706172616d65746572707800);
COMMIT;

-- ----------------------------
--  Table structure for `QRTZ_LOCKS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_PAUSED_TRIGGER_GRPS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_SCHEDULER_STATE`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_SIMPLE_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_SIMPROP_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `QRTZ_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `QRTZ_TRIGGERS`
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', 'DEFAULT', 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', 'DEFAULT', null, '1542298600000', '1542298590000', '5', 'PAUSED', 'CRON', '1542297475000', '0', null, '0', ''), ('SchedulerFactory', 'com.chinasoft.tax.quartz.jobs.SampleJob', 'DEFAULT', 'com.chinasoft.tax.quartz.jobs.SampleJob', 'DEFAULT', null, '1542294665000', '1542294660000', '5', 'PAUSED', 'CRON', '1542294239000', '0', null, '0', '');
COMMIT;

-- ----------------------------
--  Table structure for `t_application_material`
-- ----------------------------
DROP TABLE IF EXISTS `t_application_material`;
CREATE TABLE `t_application_material` (
  `id` varchar(36) NOT NULL,
  `application_id` varchar(36) DEFAULT NULL COMMENT '申请人(税务专员)',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `TIN` varchar(255) DEFAULT NULL COMMENT '税务识别号码',
  `country_name` varchar(255) DEFAULT NULL COMMENT '国家',
  `currency` varchar(255) DEFAULT NULL COMMENT '币种',
  `financial_report` varchar(255) DEFAULT NULL COMMENT '财务报表 (存储附件的id),文件保存指文件表',
  `is_upload_financial` int(11) DEFAULT NULL COMMENT '是否已上传财务报表',
  `material_id` varchar(36) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `tax_application_id` varchar(36) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `company_id` varchar(36) DEFAULT NULL,
  `tax_period` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='税金申请和资料表中间表';

-- ----------------------------
--  Records of `t_application_material`
-- ----------------------------
BEGIN;
INSERT INTO `t_application_material` VALUES ('0913c3dde0a34aa28ac383b8f4322de8', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:10:04', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('0bd68a26cac24a4db421d666b812fe0a', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:37:42', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('12583012f3574d968eb5a9242b1b7da5', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-17 03:45:00', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('15d5975a78e24eb3be5c5b2cba4096db', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:32:58', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('19e19e07959246239bc38d92bd61229c', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:25:22', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('1bd3a7bc441b4a7080f209a37e6c0908', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:32:58', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('28ea5479267842d4a1df3fb69d1a419e', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:25:22', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('33563b11162244ee865f9a572818ff5f', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:30:34', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('4b0ae254b2d5478a9bfd573b0acae3d7', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:36:05', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('50c1bbd0273b4cffa60e1c5cd5881ef7', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:29:34', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('564963538014451c8545547059a1f60c', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:34:16', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('56ed2e26d35c4c7a9d1919ce2ad857b7', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:32:58', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('6d4397b76e5f4cd8ae0078adbfddf026', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-17 03:45:00', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('78c52e610b164caab7bcba72eb4c4d72', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:24:10', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('7c56ecd6630743d6a7f2e3daf6b845b0', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:35:37', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('8d3157ba00fd4d7ebc7b4405bb88172b', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:34:56', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('914c7bfd6c6e46918024cc939406e01b', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-17 03:45:00', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('986e217a1f904341acc19294df79bf81', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:23:00', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('9c6940005787478181e5fe999244b35a', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:35:27', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('aca40665e20f4c409254803b5000c51f', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:10:04', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('bb851f47afec41d5a91c333778593072', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:36:23', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('cf26be212e14403ebde540a65ae8ae07', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:10:04', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('d0b5ed6a2da34af499b47051e8b8425e', '682265633886208', '三人行软件公司', null, null, null, null, null, '123123123123', null, '2018-11-19 08:37:39', null, null, null, null, null, 'd0bd4738a02e4a56a9b136d7f5990666', '0', '1', null), ('dc60111ad66c408d9333c8b0e846945c', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:24:09', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('e7d71921fa98429989db8a39a6fad1e1', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:25:22', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null), ('f27288a8431243478214335207423f57', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:37:42', null, null, null, null, null, '4f2c8ae45dbe45f4b46f084b2ef95e16', '1', '1', null), ('f958927597d44f049dff5c4642ac0048', '682265633886208', '三人行软件公司', null, null, null, null, null, '1212', null, '2018-11-19 08:29:45', null, null, null, null, null, '405710ffe69b44ee8734fef7d9dca663', '1', '1', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `TIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税务识别号码',
  `establishment_time` datetime DEFAULT NULL COMMENT '成立日期',
  `country_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所在国家',
  `currency_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币种',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_assign` int(11) DEFAULT '0' COMMENT '0:为分配   1:已分配',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='公司名称';

-- ----------------------------
--  Records of `t_company`
-- ----------------------------
BEGIN;
INSERT INTO `t_company` VALUES ('1', '三人行软件公司', '123123', '2018-11-02 16:21:23', 'CHN', 'CNY', '11', '2018-11-02 16:21:31', '123', null, null, null, null, '1'), ('2', '四人组科技有限公司', '123', '2018-11-02 16:25:59', 'CHN', 'CNY', '11', '2018-11-02 16:26:03', 'admin', null, null, null, null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
--  Records of `t_department`
-- ----------------------------
BEGIN;
INSERT INTO `t_department` VALUES ('1', '0', '总部', 'admin', '2018-11-02 20:58:29', null, null, null, '0'), ('2', '1', '技术部', 'admin', '2018-11-02 20:58:45', null, null, null, '0'), ('4', '2', '开发部', 'admin', '2018-11-05 10:42:40', null, null, null, null), ('5', '2', '测试部', 'admin', '2018-11-05 10:42:52', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_department_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_department_permission`;
CREATE TABLE `t_department_permission` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `permission_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门权限表';

-- ----------------------------
--  Table structure for `t_dict`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '0:国家，1：币种    2：税种  3：纳税期限表',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) unsigned DEFAULT '0',
  `sort_order` decimal(10,0) DEFAULT NULL COMMENT '排序用，数字越小月靠前',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
--  Records of `t_dict`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict` VALUES ('16f02d82045f4198a7371ff995b720ff', '增值税', 'VAT', '2', '税种代码', null, '2018-11-05 15:42:56', null, null, null, '0', null), ('2a48d86334cb49be9da031a556dadfac', '关税', 'Tariff', '2', '税种代码', null, '2018-11-05 15:43:28', null, null, null, '0', null), ('31d129fea1904b87bed5f44d91dc6123', '季度', 'SEANSON', '3', '纳税期限', null, '2018-11-05 16:25:03', null, null, null, '0', null), ('4a933b8dc81c4600b6f004604ecd5caf', '次', 'TIME', '3', '纳税期限', null, '2018-11-05 16:26:16', null, null, null, '0', null), ('727e894f2f13413dac655e8436fc4e82', '美国', 'USA', '0', '国家代码', null, '2018-11-05 15:36:23', null, null, null, null, null), ('7dac0bd07ebb49c389fd8391a691c21e', '年度汇算清缴', 'YEAR', '3', '纳税期限', null, '2018-11-05 16:25:21', null, null, null, '0', null), ('92eb2702d20249db93442569d892997c', '分期缴纳', 'INSTANLLMENT', '3', '纳税期限', null, '2018-11-05 16:25:51', null, null, null, '0', null), ('a9a95becfc8b4687a35ceaac529dd411', '欧盟', 'EU', '0', '国家代码', null, '2018-11-05 15:37:26', null, null, null, null, null), ('ad0e0b9932e449f6b3be0fc5e2f953f9', '中国', 'CHN', '0', '国家代码', null, '2018-11-05 15:35:39', null, null, null, null, null), ('ad7f5972e34a40468182bfb1dc011dd5', '房产税', 'Housing property tax', '2', '税种代码', null, '2018-11-05 15:43:14', null, null, null, '0', null), ('b844825480324ac899095c4bba71859a', '印花税', 'Stamp tax', '2', '税种代码', null, '2018-11-05 15:42:34', null, null, null, '0', null), ('bab10acfed6840a4a6633b5554eac029', '资源税', 'Resources tax', '2', '税种代码', null, '2018-11-05 15:43:41', null, null, null, '0', null), ('f3f77feade04484f977e70f396d0bb0e', '人民币', 'CNY', '1', '币种代码', null, '2018-11-05 15:40:03', null, null, null, '0', null), ('f4014dc7823f4ad496bc469bd2f46701', '次1', 'TIME1', '3', '纳税期限', null, '2018-11-05 20:09:14', null, null, null, '0', null), ('f60c2a9599d149d8a92221e8c12d1526', '月度', 'MONTH', '3', '纳税期限', null, '2018-11-05 16:24:50', null, null, null, '0', null), ('fae7c60dae994f0ca04a9506d52edf53', '欧元', 'EUR', '1', '币种代码', null, '2018-11-05 15:40:36', null, null, null, '0', null), ('fc20170154fd426792ebffd93c55f2c5', '美元', 'USD', '1', '币种代码', null, '2018-11-05 15:40:21', null, null, null, '0', null), ('fec885b646c7445e913bbb30aa559d1e', '英国', 'UK', '0', '国家代码', null, '2018-11-05 15:36:39', null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_element_permisson`
-- ----------------------------
DROP TABLE IF EXISTS `t_element_permisson`;
CREATE TABLE `t_element_permisson` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='页面元素权限表';

-- ----------------------------
--  Table structure for `t_log_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_log_info`;
CREATE TABLE `t_log_info` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用的方法名称',
  `method_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用的代码方法',
  `param` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '操作之前的数据的入参数据',
  `data_before` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作之前的数据',
  `data_after` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作之后的数据',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `del_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
--  Records of `t_log_info`
-- ----------------------------
BEGIN;
INSERT INTO `t_log_info` VALUES ('092b7dd9f35a4c4faaec42dff168b6df', null, 'admin', '2018-11-19 08:32:58', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:32:58', 'admin', null, null, null, null), ('1df99c227f23480090b0949d2882b650', null, 'admin', '2018-11-17 03:44:27', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:44:27', 'admin', null, null, null, null), ('21c6e5836c82416d9e8d2c590a0c9feb', null, 'admin', '2018-11-19 07:55:44', '查询待提任务', 'com.chinasoft.tax.controller.TaxApplicationController.readyCommit', 'taxQo=TaxQo(pageVo=PageVo(pageNumber=1, pageSize=10, sort=null, order=null), userId=null, searchVo=SearchVo(startDate=2018-11-08, endDate=2018-11-10))', null, null, '127.0.0.1', '2018-11-19 07:55:44', 'admin', null, null, null, null), ('3c1b8c7f0c3645338ce952afa6fa9999', null, 'admin', '2018-11-19 08:01:22', '查询待提任务', 'com.chinasoft.tax.controller.TaxApplicationController.readyCommit', 'taxQo=TaxQo(pageVo=PageVo(pageNumber=1, pageSize=10, sort=null, order=null), userId=null, searchVo=SearchVo(startDate=2018-11-08, endDate=2018-11-10))', null, null, '127.0.0.1', '2018-11-19 08:01:22', 'admin', null, null, null, null), ('4e89feabbfbc451fb2f5b88237ed3df9', null, 'admin', '2018-11-19 08:01:09', '查询待提任务', 'com.chinasoft.tax.controller.TaxApplicationController.readyCommit', 'taxQo=TaxQo(pageVo=PageVo(pageNumber=1, pageSize=10, sort=null, order=null), userId=null, searchVo=SearchVo(startDate=2018-11-08, endDate=2018-11-10))', null, null, '127.0.0.1', '2018-11-19 08:01:09', 'admin', null, null, null, null), ('59db9d8741464c7187fbcb221a477070', null, 'admin', '2018-11-19 08:30:57', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13.11, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12.11, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:30:57', 'admin', null, null, null, null), ('5a7ac8629c594a9ea18492a8e3f0fda7', null, 'admin', '2018-11-13 10:37:54', '上传资料', 'com.chinasoft.tax.controller.FileController.uploadFile', 'files=[Lorg.springframework.web.multipart.MultipartFile;@e03a87a;materialTypeDict=DONE_TAX_REPORT', null, null, '127.0.0.1', '2018-11-13 10:37:54', 'admin', null, null, null, null), ('5cb5797993e545df9a9a655a542e5b6e', null, 'admin', '2018-11-17 03:43:54', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:43:54', 'admin', null, null, null, null), ('620eb5223a2d40ab9461a429345bbccd', null, 'admin', '2018-11-19 08:24:10', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:24:10', 'admin', null, null, null, null), ('6b3c136b42e6444c90f7c74b85744f24', null, 'admin', '2018-11-19 08:10:04', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:10:04', 'admin', null, null, null, null), ('6d0c782762d846c593bc38afa6d648e3', null, 'admin', '2018-11-17 03:38:13', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:38:13', 'admin', null, null, null, null), ('763ff435477943c9a5a5f597e805642a', null, 'admin', '2018-11-19 08:39:32', '删除待提任务', 'com.chinasoft.tax.controller.TaxApplicationController.delById', 'id=729e73d35ab5415fb0eae00b5b51947d', null, null, '127.0.0.1', '2018-11-19 08:39:32', 'admin', null, null, null, null), ('7ce90d005bc84abc80a64269f57f7061', null, 'admin', '2018-11-17 03:44:43', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:44:43', 'admin', null, null, null, null), ('82394a28bff64c0c916e29050d079496', null, 'admin', '2018-11-19 08:37:42', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:37:42', 'admin', null, null, null, null), ('8f9429dd1aad4342a72ee940aa5bb295', null, 'admin', '2018-11-17 03:45:00', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:45:00', 'admin', null, null, null, null), ('b00487cbb6d947b6ae0e299edb0fbafa', null, 'admin', '2018-11-17 02:35:18', '上传资料', 'com.chinasoft.tax.controller.FileController.uploadFile', 'files=[Lorg.springframework.web.multipart.MultipartFile;@39493124;materialTypeDict=DONE_TAX_REPORT', null, null, '127.0.0.1', '2018-11-17 02:35:18', 'admin', null, null, null, null), ('b02f00e87c784de7ab70d00d716fd349', null, 'admin', '2018-11-17 03:35:00', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:35:00', 'admin', null, null, null, null), ('b7a131bc13ea4444b65bbf911a51b216', null, 'admin', '2018-11-19 08:37:33', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:37:33', 'admin', null, null, null, null), ('b9d7e8debd0e4291a3ca114065bb83f8', null, 'admin', '2018-11-19 08:25:22', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:25:22', 'admin', null, null, null, null), ('bfe2b12f9310424e8fd7d3861006d76c', null, 'admin', '2018-11-17 03:43:02', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:43:02', 'admin', null, null, null, null), ('cfc73589728944c1af5d4aef71a69eaa', null, 'admin', '2018-11-17 03:39:09', '税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.addTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=null, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Stamp tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=null, taxPeriod=INSTANLLMENT, taxDict=Resources tax, payableTax=12, lateFeePayable=0, applTaxPayment=10, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12, overduePayment=0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=null, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-17 03:39:09', 'admin', null, null, null, null), ('e20467078b76477da004a48e99d7641e', null, 'admin', '2018-11-19 08:35:27', '修改税金申请', 'com.chinasoft.tax.controller.TaxApplicationController.editTaxApplication', 'taxApplicationVo=TaxApplicationVo(id=d0bd4738a02e4a56a9b136d7f5990666, companyId=1, companyName=三人行软件公司, tin=123123123, countryCode=CHN, countryName=中国, applicantId=682265633886208, applicantName=admin, remarks=这是一个备注, status=null, currency=CNY, financialReport=123123123123, isUploadReport=null, details=[TaxApplicationDetailVo(id=405710ffe69b44ee8734fef7d9dca663, taxPeriod=2018-11-01, taxDict=Stamp tax, payableTax=13.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null), TaxApplicationDetailVo(id=4f2c8ae45dbe45f4b46f084b2ef95e16, taxPeriod=2018-11-01, taxDict=Resources tax, payableTax=12.11, lateFeePayable=0, applTaxPayment=10.0, deadline=Fri Nov 09 08:00:00 CST 2018, taxPaid=12.11, overduePayment=0.0, paymentTime=Thu Nov 08 08:00:00 CST 2018, remarks=子备注1, status=null, taxApplicationId=d0bd4738a02e4a56a9b136d7f5990666, taxReturns=1212, taxReturnsPath=c://dd/aa/adf.txt, isUploadTaxReturns=null, paymentCertificate=1212, paymentCertificatePath=c://dfdf//dfdf/aaa.doc, isUploadCertificate=null, otherUpload=, otherUploadId=null)], creator=null, createTime=null, saveTime=null, isDel=null, delTime=null, deletor=null, executeType=0)', null, null, '127.0.0.1', '2018-11-19 08:35:27', 'admin', null, null, null, null), ('e4192af5cdd24d7a8f8efe7c333e7c2b', null, 'admin', '2018-11-19 08:39:52', '删除待提任务', 'com.chinasoft.tax.controller.TaxApplicationController.delById', 'id=729e73d35ab5415fb0eae00b5b51947d', null, null, '127.0.0.1', '2018-11-19 08:39:52', 'admin', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_material`
-- ----------------------------
DROP TABLE IF EXISTS `t_material`;
CREATE TABLE `t_material` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `material_type` int(11) DEFAULT NULL,
  `process_type` int(11) DEFAULT NULL COMMENT '流程类型(0:税金申请   1：滞纳金申请)',
  `ori_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件名称后缀',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件存储路径',
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '文件类型',
  `material_type_dict` varchar(255) DEFAULT NULL COMMENT '资料类型代码-对应资料类型表的code字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='material';

-- ----------------------------
--  Records of `t_material`
-- ----------------------------
BEGIN;
INSERT INTO `t_material` VALUES ('207cb253d59c4ef1a17af98de9be8af4', null, null, '西豆科技有限公司相关账号.pdf', '41743612e7a14ffc86de022a66e678c1.pdf', '.pdf', 'upload/41743612e7a14ffc86de022a66e678c1.pdf', '2018-11-12 06:53:18', null, null, null, null, null, null, 'TAX_REPORT'), ('3a5db97d96304ffcb99e303523bfa8a8', null, null, 'WordRqmErrors.log', '3df1bdd164d841acb1e798b5b0d58977.log', '.log', 'upload/3df1bdd164d841acb1e798b5b0d58977.log', '2018-11-11 07:26:48', null, null, null, null, null, null, 'FINANCE_REPORT'), ('965f34082e454419a5c2d5bf483ad499', null, null, 'WordRqmErrors.log', '19a51c1060dc46cab57dba7a5833242c.log', '.log', 'upload/19a51c1060dc46cab57dba7a5833242c.log', '2018-11-11 07:26:48', null, null, null, null, null, null, 'FINANCE_REPORT'), ('a557d8c596eb4b248c6099987f06badf', null, null, 'WordRqmErrors.log', 'ab191bc0c50940afafcd442e1a07c3b2.log', '.log', 'upload/ab191bc0c50940afafcd442e1a07c3b2.log', '2018-11-11 09:26:55', null, null, null, null, null, null, 'FINANCE_REPORT'), ('a90e961f074641f996184702dfa7e813', null, null, '西豆科技有限公司相关账号.pdf', 'a09bbd009dfc45f59bd543264d36ff4f.pdf', '.pdf', 'upload/a09bbd009dfc45f59bd543264d36ff4f.pdf', '2018-11-12 06:51:48', null, null, null, null, null, null, 'TAX_REPORT'), ('c7c5fd8bbfd74385b1d8d137bb652006', null, null, 'bookmarks_2018_3_20.html', '14298108ef5b4194be8901a3f022dbcd.html', '.html', 'upload/14298108ef5b4194be8901a3f022dbcd.html', '2018-11-13 10:37:54', null, null, null, null, null, null, 'DONE_TAX_REPORT'), ('cb86b842a6c14284a1b56af301ef1798', null, null, '西豆科技有限公司相关账号.pdf', '814079defb434356bfd887d24e3932d4.pdf', '.pdf', 'upload/814079defb434356bfd887d24e3932d4.pdf', '2018-11-12 05:23:50', null, null, null, null, null, null, 'DONE_TAX_REPORT'), ('e92bf6cb61ee4e0a844a4124a3d4e688', null, null, '西豆科技有限公司相关账号.pdf', 'b9ab393ca309425d907872e7d6129c7c.pdf', '.pdf', 'upload/b9ab393ca309425d907872e7d6129c7c.pdf', '2018-11-17 02:35:17', null, null, null, null, null, null, 'DONE_TAX_REPORT'), ('ee39804445964249b512d48ff89cb5c6', null, null, 'WordRqmErrors.log', '6e594a3eec134292bc06aa0101125e14.log', '.log', 'upload/6e594a3eec134292bc06aa0101125e14.log', '2018-11-11 07:26:47', null, null, null, null, null, null, 'FINANCE_REPORT');
COMMIT;

-- ----------------------------
--  Table structure for `t_material_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_material_type`;
CREATE TABLE `t_material_type` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资料类型',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '资料类型',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='资料类型';

-- ----------------------------
--  Records of `t_material_type`
-- ----------------------------
BEGIN;
INSERT INTO `t_material_type` VALUES ('1', '财务报表', 'FINANCE_REPORT', 'admin', '2018-11-11 18:06:53', null, null, null), ('2', '税务申报表', 'TAX_REPORT', 'admin', '2018-11-11 18:07:21', null, null, null), ('3', '完税申报', 'DONE_TAX_REPORT', 'admin', '2018-11-11 18:08:09', null, null, null), ('4', '其他', 'OTHER', 'admin', '2018-11-11 18:08:31', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sort_order` decimal(10,0) DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端组件',
  `level` int(11) DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
--  Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `sort_order` decimal(10,2) DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `button_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_permission` VALUES ('5129710648430592', '', '2018-06-04 19:02:29', '0', '', '2018-09-29 23:11:56', '', 'sys', '', '0', '1.00', 'Main', '/sys', '系统管理', 'ios-settings', '1', '', '0', ''), ('5129710648430593', '', '2018-06-04 19:02:32', '0', '', '2018-08-13 15:15:33', '', 'user-manage', '5129710648430592', '0', '1.10', 'sys/user-manage/userManage', 'user-manage', '用户管理', 'md-person', '2', '', '0', ''), ('5129710648430594', '', '2018-06-04 19:02:35', '0', '', '2018-09-23 23:32:16', '', 'role-manage', '5129710648430592', '0', '1.60', 'sys/role-manage/roleManage', 'role-manage', '角色管理', 'md-contacts', '2', '', '0', ''), ('5129710648430595', '', '2018-06-04 19:02:37', '0', '', '2018-09-23 23:32:02', '', 'menu-manage', '5129710648430592', '0', '1.70', 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'md-menu', '2', '', '0', ''), ('41375330996326400', '', '2018-08-13 18:23:08', '0', '', '2018-08-15 17:13:23', '', 'simple-table', '41373430515240960', '0', '4.10', 'xboot-vue-template/simple-table/simpleTable', 'simple-table', '简单表格', 'ios-grid-outline', '2', '', '0', ''), ('15701400130424832', '', '2018-06-03 22:04:06', '0', '', '2018-09-19 22:16:44', '', '', '5129710648430593', '1', '1.11', '', '/xboot/user/admin/add*', '添加用户', '', '3', 'add', '0', ''), ('15701915807518720', '', '2018-06-03 22:06:09', '0', '', '2018-06-06 14:46:51', '', '', '5129710648430593', '1', '1.13', '', '/xboot/user/admin/disable/**', '禁用用户', '', '3', 'disable', '0', null), ('15708892205944832', '', '2018-06-03 22:33:52', '0', '', '2018-06-28 16:44:48', '', '', '5129710648430593', '1', '1.14', '', '/xboot/user/admin/enable/**', '启用用户', '', '3', 'enable', '0', null), ('16392452747300864', '', '2018-06-05 19:50:06', '0', '', '2018-08-13 18:15:39', '', 'access', '', '0', '4.00', 'Main', '/access', '权限按钮测试页', 'md-lock', '1', '', '0', ''), ('16392767785668608', '', '2018-06-05 19:51:21', '0', '', '2018-09-21 23:31:06', '', 'access_index', '16392452747300864', '0', '4.10', 'access/access', 'index', '权限按钮测试页', 'md-lock', '2', '', '0', ''), ('16438800255291392', '', '2018-06-05 22:54:18', '0', '', '2018-08-13 18:15:51', '', '', '16392767785668608', '1', '4.11', '', 'test-add', '添加按钮测试', '', '3', 'add', '0', ''), ('16438962738434048', '', '2018-06-05 22:54:55', '0', '', '2018-08-13 18:16:29', '', '', '16392767785668608', '1', '4.12', '', 'edit-test', '编辑按钮测试', '', '3', 'edit', '0', ''), ('16439068543946752', '', '2018-06-05 22:55:20', '0', '', '2018-08-13 18:16:12', '', '', '16392767785668608', '1', '4.13', '', 'delete-test', '删除按钮测试', '', '3', 'delete', '0', ''), ('16678126574637056', '', '2018-06-06 14:45:16', '0', '', '2018-09-19 22:16:48', '', '', '5129710648430593', '1', '1.12', '', '/xboot/user/admin/edit*', '编辑用户', '', '3', 'edit', '0', ''), ('16678447719911424', '', '2018-06-06 14:46:32', '0', '', '2018-08-10 21:41:16', '', '', '5129710648430593', '1', '1.15', '', '/xboot/user/delByIds/**', '删除用户', '', '3', 'delete', '0', ''), ('16687383932047360', '', '2018-06-06 15:22:03', '0', '', '2018-09-19 22:07:34', '', '', '5129710648430594', '1', '1.21', '', '/xboot/role/save*', '添加角色', '', '3', 'add', '0', ''), ('16689632049631232', '', '2018-06-06 15:30:59', '0', '', '2018-09-19 22:07:37', '', '', '5129710648430594', '1', '1.22', '', '/xboot/role/edit*', '编辑角色', '', '3', 'edit', '0', ''), ('16689745006432256', '', '2018-06-06 15:31:26', '0', '', '2018-08-10 21:41:23', '', '', '5129710648430594', '1', '1.23', '', '/xboot/role/delAllByIds/**', '删除角色', '', '3', 'delete', '0', ''), ('16689883993083904', null, '2018-06-06 15:31:59', '0', null, '2018-06-06 15:31:59', null, null, '5129710648430594', '1', '1.24', null, '/xboot/role/editRolePerm/**', '分配权限', null, '3', 'editPerm', '0', null), ('16690313745666048', '', '2018-06-06 15:33:41', '0', '', '2018-09-19 22:07:46', '', '', '5129710648430594', '1', '1.25', '', '/xboot/role/setDefault*', '设为默认角色', '', '3', 'setDefault', '0', ''), ('16694861252005888', '', '2018-06-06 15:51:46', '0', '', '2018-09-19 22:07:52', '', '', '5129710648430595', '1', '1.31', '', '/xboot/permission/add*', '添加菜单', '', '3', 'add', '0', ''), ('16695107491205120', '', '2018-06-06 15:52:44', '0', '', '2018-09-19 22:07:57', '', '', '5129710648430595', '1', '1.32', '', '/xboot/permission/edit*', '编辑菜单', '', '3', 'edit', '0', ''), ('16695243126607872', '', '2018-06-06 15:53:17', '0', '', '2018-08-10 21:41:33', '', '', '5129710648430595', '1', '1.33', '', '/xboot/permission/delByIds/**', '删除菜单', '', '3', 'delete', '0', ''), ('41371711400054784', '', '2018-08-13 18:08:45', '0', '', '2018-08-14 12:31:15', '', 'actuator', '39915540965232640', '0', '2.30', 'sys/actuator/actuator', 'actuator', 'Actuator监控[付费]', 'logo-angular', '2', '', '0', ''), ('41370251991977984', null, '2018-08-13 18:02:57', '0', null, '2018-08-13 18:02:57', null, 'quartz-job', '39915540965232640', '0', '2.10', 'sys/quartz-manage/quartzManage', 'quartz-job', '定时任务', 'md-time', '2', '', '0', null), ('25014528525733888', '', '2018-06-29 14:51:09', '0', '', '2018-10-08 11:13:27', '', '', '5129710648430593', '1', '1.16', '', '无', '上传图片', '', '3', 'upload', '0', ''), ('39915540965232640', null, '2018-08-09 17:42:28', '0', null, '2018-08-09 17:42:28', null, 'monitor', null, '0', '2.00', 'Main', '/monitor', '系统监控', 'ios-analytics', '1', null, '0', null), ('39916171171991552', '', '2018-08-09 17:44:57', '0', '', '2018-08-25 12:13:11', '', 'druid', '39915540965232640', '0', '2.40', 'sys/monitor/monitor', 'druid', 'SQL监控', 'md-analytics', '2', '', '0', 'http://xboot.exrick.cn/druid/login.html'), ('39918482854252544', '', '2018-08-09 17:54:08', '0', '', '2018-08-25 12:13:27', '', 'swagger', '39915540965232640', '0', '2.50', 'sys/monitor/monitor', 'swagger', '接口文档', 'md-document', '2', '', '0', 'http://xboot.exrick.cn/swagger-ui.html'), ('40238597734928384', null, '2018-08-10 15:06:10', '0', null, '2018-08-10 15:06:10', null, 'department-manage', '5129710648430592', '0', '1.20', 'sys/department-manage/departmentManage', 'department-manage', '部门管理', 'md-git-branch', '2', '', '0', null), ('42082442672082944', '', '2018-08-15 17:12:57', '0', '', '2018-09-25 15:17:59', '', 'new-window', '41373430515240960', '0', '4.60', 'xboot-vue-template/new-window/newWindow', 'new-window', '新窗口操作[付费]', 'ios-browsers', '2', '', '0', ''), ('41373430515240960', '', '2018-08-13 18:15:35', '0', '', '2018-08-15 14:29:48', '', 'xboot-vue-template', '', '0', '3.00', 'Main', '/xboot-vue-template', '前端Vue模版', 'ios-albums', '1', '', '0', ''), ('41363147411427328', '', '2018-08-13 17:34:43', '0', '', '2018-08-20 20:05:21', '', 'log-manage', '39915540965232640', '0', '2.20', 'sys/log-manage/logManage', 'log-manage', '操作日志管理', 'md-list-box', '2', '', '0', ''), ('41363537456533504', '', '2018-08-13 17:36:16', '0', '', '2018-08-13 17:56:11', '', '', '41363147411427328', '1', '2.11', '', '/xboot/log/delByIds/**', '删除日志', '', '3', 'delete', '0', ''), ('41364927394353152', '', '2018-08-13 17:41:48', '0', '', '2018-09-19 22:08:57', '', '', '41363147411427328', '1', '2.12', '', '/xboot/log/delAll*', '清空日志', '', '3', 'undefined', '0', ''), ('41376192166629376', '', '2018-08-13 18:26:33', '0', '', '2018-08-15 17:13:34', '', 'search-table', '41373430515240960', '0', '4.20', 'xboot-vue-template/search-table/searchTable', 'search-table', '搜索表格[付费]', 'md-grid', '2', '', '0', ''), ('41377034236071936', '', '2018-08-13 18:29:54', '0', '', '2018-08-15 17:13:40', '', 'complex-table', '41373430515240960', '0', '4.30', 'xboot-vue-template/complex-table/complexTable', 'complex-table', '复杂表格[付费]', 'ios-grid', '2', '', '0', ''), ('41378916912336896', '', '2018-08-13 18:37:23', '0', '', '2018-09-25 15:19:50', '', 'tree', '41373430515240960', '0', '4.50', 'xboot-vue-template/tree/tree', 'tree', '树形结构[付费]', 'ios-git-network', '2', '', '0', ''), ('41469219249852416', null, '2018-08-14 00:36:13', '0', null, '2018-08-14 00:36:13', null, '', '41371711400054784', '1', '2.31', '', '无', '查看数据', '', '3', 'view', '0', null), ('42087054753927168', '', '2018-08-15 17:31:16', '0', '', '2018-09-25 15:18:05', '', 'html-edit', '41373430515240960', '0', '4.70', 'xboot-vue-template/html-edit/htmlEdit', 'html-edit', '富文本编辑[付费]', 'ios-create', '2', '', '0', ''), ('43117268627886080', '', '2018-08-18 13:44:58', '0', '', '2018-08-18 20:55:04', '', 'message-manage', '5129710648430592', '0', '1.30', 'sys/message-manage/messageManage', 'message-manage', '消息管理[付费]', 'md-mail', '2', '', '0', ''), ('44986029924421632', '', '2018-08-23 17:30:46', '0', '', '2018-09-23 23:26:53', '', 'social-manage', '5129710648430592', '0', '1.50', 'sys/social-manage/socialManage', 'social-manage', '社交账号绑定[付费]', 'md-share', '2', '', '0', ''), ('45069342940860416', '', '2018-08-23 23:01:49', '0', '', '2018-08-24 10:01:09', '', '', '44986029924421632', '1', '1.42', '', '无', '查看社交账号数据', '', '3', 'view', '0', ''), ('45235228800716800', '', '2018-08-24 10:01:00', '0', '', '2018-09-19 22:07:23', '', '', '44986029924421632', '1', '1.41', '', '/xboot/relate/delByIds*', '删除解绑', '', '3', 'delete', '0', ''), ('45235621697949696', '', '2018-08-24 10:02:33', '0', '', '2018-09-19 22:06:57', '', '', '40238597734928384', '1', '1.21', '', '/xboot/department/add*', '添加部门', '', '3', 'add', '0', ''), ('45235787867885568', '', '2018-08-24 10:03:13', '0', '', '2018-09-19 22:07:02', '', '', '40238597734928384', '1', '1.22', '', '/xboot/department/edit*', '编辑部门', '', '3', 'edit', '0', ''), ('45235939278065664', null, '2018-08-24 10:03:49', '0', null, '2018-08-24 10:03:49', null, '', '40238597734928384', '1', '1.23', '', '/xboot/department/delByIds/*', '删除部门', '', '3', 'delete', '0', null), ('45236734832676864', '', '2018-08-24 10:06:59', '0', '', '2018-09-19 22:07:07', '', '', '43117268627886080', '1', '1.31', '', '/xboot/message/add*', '添加消息', '', '3', 'add', '0', ''), ('45237010692050944', '', '2018-08-24 10:08:04', '0', '', '2018-09-19 22:07:12', '', '', '43117268627886080', '1', '1.32', '', '/xboot/message/edit*', '编辑消息', '', '3', 'edit', '0', ''), ('45237170029465600', null, '2018-08-24 10:08:42', '0', null, '2018-08-24 10:08:42', null, '', '43117268627886080', '1', '1.33', '', '/xboot/message/delByIds/*', '删除消息', '', '3', 'delete', '0', null), ('45264987354042368', '', '2018-08-24 11:59:14', '0', '', '2018-09-19 22:08:11', '', '', '41370251991977984', '1', '2.11', '', '/xboot/quartzJob/add*', '添加定时任务', '', '3', 'add', '0', ''), ('45265487029866496', '', '2018-08-24 12:01:14', '0', '', '2018-09-19 22:08:17', '', '', '41370251991977984', '1', '2.12', '', '/xboot/quartzJob/edit*', '编辑定时任务', '', '3', 'edit', '0', ''), ('45265762415284224', '', '2018-08-24 12:02:19', '0', '', '2018-09-19 22:08:24', '', '', '41370251991977984', '1', '2.13', '', '/xboot/quartzJob/pause*', '暂停定时任务', '', '3', 'disable', '0', ''), ('45265886315024384', '', '2018-08-24 12:02:49', '0', '', '2018-09-19 22:09:13', '', '', '41370251991977984', '1', '2.14', '', '/xboot/quartzJob/resume*', '恢复定时任务', '', '3', 'enable', '0', ''), ('45266070000373760', null, '2018-08-24 12:03:33', '0', null, '2018-08-24 12:03:33', null, '', '41370251991977984', '1', '2.15', '', '/xboot/quartzJob/delByIds/*', '删除定时任务', '', '3', 'delete', '0', null), ('56309618086776832', null, '2018-09-23 23:26:40', '0', null, '2018-09-23 23:26:40', null, 'oss-manage', '5129710648430592', '0', '1.40', 'sys/oss-manage/ossManage', 'oss-manage', 'OSS对象存储[付费]', 'ios-folder', '2', '', '0', null), ('56898976661639168', '', '2018-09-25 14:28:34', '0', '', '2018-09-25 15:12:46', '', '', '5129710648430593', '1', '1.17', '', '/xboot/user/importData*', '导入用户', '', '3', 'input', '0', ''), ('56911328312299520', '', '2018-09-25 15:17:39', '0', '', '2018-09-25 18:31:49', '', 'excel', '41373430515240960', '0', '4.40', 'xboot-vue-template/excel/excel', 'excel', 'Excel导入导出[付费]', 'md-exit', '2', '', '0', ''), ('57009544286441472', null, '2018-09-25 21:47:55', '0', null, '2018-09-25 21:47:55', null, '', '43117268627886080', '1', '1.40', '', '/xboot/messageSend/save*', '添加已发送消息', '', '3', 'add', '0', null), ('57009744761589760', null, '2018-09-25 21:48:43', '0', null, '2018-09-25 21:48:43', null, '', '43117268627886080', '1', '1.50', '', '/xboot/messageSend/update*', '编辑已发送消息', '', '3', 'edit', '0', null), ('57009981228060672', null, '2018-09-25 21:49:39', '0', null, '2018-09-25 21:49:39', null, '', '43117268627886080', '1', '1.60', '', '/xboot/messageSend/delByIds/*', '删除已发送消息', '', '3', 'delete', '0', null), ('57212882168844288', '', '2018-09-26 11:15:55', '0', '', '2018-10-08 11:10:05', '', '', '56309618086776832', '1', '1.41', '', '无', '上传文件', '', '3', 'upload', '0', ''), ('58480609315524608', '', '2018-09-29 23:13:24', '0', '', '2018-09-29 23:17:59', '', 'setting', '5129710648430592', '0', '1.80', 'sys/setting-manage/settingManage', 'setting', '系统配置[付费]', 'ios-settings-outline', '2', '', '0', ''), ('61394706252173312', null, '2018-10-08 00:12:59', '0', null, '2018-10-08 00:12:59', null, '', '58480609315524608', '1', '1.81', '', '/xboot/setting/seeSecret/**', '查看私密配置', '', '3', 'view', '0', null), ('61417744146370560', '', '2018-10-08 01:44:32', '0', '', '2018-10-08 01:50:03', '', '', '58480609315524608', '1', '1.82', '', '/xboot/setting/*/set*', '编辑配置', '', '3', 'edit', '0', ''), ('61560480518377472', null, '2018-10-08 11:11:43', '0', null, '2018-10-08 11:11:43', null, '', '56309618086776832', '1', '1.44', '', '/xboot/file/delete/*', '删除文件', '', '3', 'delete', '0', null), ('61560275261722624', null, '2018-10-08 11:10:54', '0', null, '2018-10-08 11:10:54', null, '', '56309618086776832', '1', '1.43', '', '/xboot/file/copy*', '复制文件', '', '3', 'edit', '0', null), ('61560041605435392', null, '2018-10-08 11:09:58', '0', null, '2018-10-08 11:09:58', null, '', '56309618086776832', '1', '1.42', '', '/xboot/file/rename*', '重命名文件', '', '3', 'edit', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_quartz_job`
-- ----------------------------
DROP TABLE IF EXISTS `t_quartz_job`;
CREATE TABLE `t_quartz_job` (
  `id` varchar(255) NOT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `job_class_name` varchar(255) DEFAULT NULL,
  `parameter` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_quartz_job`
-- ----------------------------
BEGIN;
INSERT INTO `t_quartz_job` VALUES ('7991df4350b9413eaf6f75900dbaadae', 'admin', '2018-11-15 09:57:56', null, 'admin', '2018-11-15 10:16:36', '0/10 * * * * ? ', '邮件提醒专员代办任务', 'com.chinasoft.tax.quartz.jobs.EmailNotificationJob', null, '-1'), ('1749f8f32dd943ecb89dd77f8e7dcddf', 'admin', '2018-11-14 10:57:41', null, 'admin', '2018-11-15 09:11:04', '0/5 * * * * ? ', '无参测试 后台将每隔5秒执行输出日志', 'com.chinasoft.tax.quartz.jobs.SampleJob', null, '-1');
COMMIT;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `default_role` bit(1) DEFAULT NULL COMMENT '是否是默认角色   1：是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('16457350655250432', '测试人员', 'ROLE_TEST', '2018-10-24 19:40:05', 'admin', '0', null, null, null, b'0'), ('39dafa93960a42179f39dc92ff4527ff', '运维', 'ROLE_OPERATION', null, null, null, null, null, null, null), ('496138616573952', '超级管理员', 'ROLE_ADMINISTRATOR', '2018-10-24 19:39:21', 'admin', '0', null, null, null, null), ('496138616573953', '开发人员', 'ROLE_DEVELOP', '2018-10-24 19:39:48', 'admin', '0', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_role_api`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_api`;
CREATE TABLE `t_role_api` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `api_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色接口权限表';

-- ----------------------------
--  Table structure for `t_role_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_company`;
CREATE TABLE `t_role_company` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `company_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色公司表';

-- ----------------------------
--  Table structure for `t_role_element`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_element`;
CREATE TABLE `t_role_element` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `element_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色页面权限表';

-- ----------------------------
--  Table structure for `t_role_material`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_material`;
CREATE TABLE `t_role_material` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `material_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色资料类型';

-- ----------------------------
--  Table structure for `t_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
--  Records of `t_role_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu` VALUES ('1b9b974503844278811c0c3678ada6cf', '1', '2', 'admin', '2018-10-24 20:55:10', '0', null), ('995bde8de36742c693ec9d7f12574534', '1', '1', 'admin', '2018-10-24 20:55:10', '0', null), ('a16470b9037645b7862fdffc55be9d29', '1', '4', 'admin', '2018-10-24 20:55:10', '0', null), ('aab5556b908846daaa8c1e7655938b01', '1', '3', 'admin', '2018-10-24 20:55:10', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_role_permission`
-- ----------------------------
BEGIN;
INSERT INTO `t_role_permission` VALUES ('16457624555884544', null, '2018-06-06 00:09:04', '0', null, '2018-06-06 00:09:04', '16392452747300864', '16457350655250432'), ('16457624597827584', null, '2018-06-06 00:09:04', '0', null, '2018-06-06 00:09:04', '16392767785668608', '16457350655250432'), ('16457624643964928', null, '2018-06-06 00:09:04', '0', null, '2018-06-06 00:09:04', '16439068543946752', '16457350655250432'), ('61560538731122689', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16439068543946752', '496138616573952'), ('61560538726928385', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16438962738434048', '496138616573952'), ('61560538718539777', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16438800255291392', '496138616573952'), ('61414448241315840', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '16439068543946752', '496138616573953'), ('61414448207761408', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '16438962738434048', '496138616573953'), ('61414448165818368', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '16438800255291392', '496138616573953'), ('61414448123875328', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '16392767785668608', '496138616573953'), ('61414448069349376', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '16392452747300864', '496138616573953'), ('61414448014823424', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '42087054753927168', '496138616573953'), ('61414447985463296', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '42082442672082944', '496138616573953'), ('61414447935131648', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41378916912336896', '496138616573953'), ('61414447884800000', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '56911328312299520', '496138616573953'), ('61414447868022784', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41377034236071936', '496138616573953'), ('61414447847051264', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41376192166629376', '496138616573953'), ('61414447826079744', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41375330996326400', '496138616573953'), ('61414447813496832', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41373430515240960', '496138616573953'), ('61414447796719616', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '39918482854252544', '496138616573953'), ('61414447758970880', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '39916171171991552', '496138616573953'), ('61414447717027840', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41371711400054784', '496138616573953'), ('61414447675084800', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41363147411427328', '496138616573953'), ('61414447666696192', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '41370251991977984', '496138616573953'), ('61414447649918976', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '39915540965232640', '496138616573953'), ('61414447633141760', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '58480609315524608', '496138616573953'), ('61414447603781632', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '5129710648430595', '496138616573953'), ('61414447574421504', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '5129710648430594', '496138616573953'), ('61414447524089856', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '44986029924421632', '496138616573953'), ('61414447486341120', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '56309618086776832', '496138616573953'), ('61414447431815168', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '43117268627886080', '496138616573953'), ('61414447381483520', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '40238597734928384', '496138616573953'), ('61414447368900608', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '5129710648430593', '496138616573953'), ('61414447305986048', null, '2018-10-08 01:31:26', '0', null, '2018-10-08 01:31:26', '5129710648430592', '496138616573953'), ('61560538714345473', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16392767785668608', '496138616573952'), ('61560538710151169', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16392452747300864', '496138616573952'), ('61560538705956865', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '42087054753927168', '496138616573952'), ('61560538701762561', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '42082442672082944', '496138616573952'), ('61560538697568256', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41378916912336896', '496138616573952'), ('61560538693373952', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '56911328312299520', '496138616573952'), ('61560538684985345', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41377034236071936', '496138616573952'), ('61560538680791041', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41376192166629376', '496138616573952'), ('61560538676596736', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41375330996326400', '496138616573952'), ('61560538668208128', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41373430515240960', '496138616573952'), ('61560538575933441', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '39918482854252544', '496138616573952'), ('61560538571739137', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '39916171171991552', '496138616573952'), ('61560538567544833', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41469219249852416', '496138616573952'), ('61560538563350529', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41371711400054784', '496138616573952'), ('61560538559156225', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41364927394353152', '496138616573952'), ('61560538554961921', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41363537456533504', '496138616573952'), ('61560538550767616', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41363147411427328', '496138616573952'), ('61560538542379008', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45266070000373760', '496138616573952'), ('61560538529796096', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45265886315024384', '496138616573952'), ('61560538504630272', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45265762415284224', '496138616573952'), ('61560538462687232', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45265487029866496', '496138616573952'), ('61560538437521408', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45264987354042368', '496138616573952'), ('61560538429132801', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '41370251991977984', '496138616573952'), ('61560538424938497', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '39915540965232640', '496138616573952'), ('61560538420744193', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '61417744146370560', '496138616573952'), ('61560538416549889', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '61394706252173312', '496138616573952'), ('61560538412355585', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '58480609315524608', '496138616573952'), ('61560538408161280', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16695243126607872', '496138616573952'), ('61560538403966976', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16695107491205120', '496138616573952'), ('61560538399772672', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16694861252005888', '496138616573952'), ('61560538370412545', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '5129710648430595', '496138616573952'), ('61560538324275200', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16690313745666048', '496138616573952'), ('61560538320080896', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16689883993083904', '496138616573952'), ('61560538315886592', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16689745006432256', '496138616573952'), ('61560538311692288', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16689632049631232', '496138616573952'), ('61560538307497984', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16687383932047360', '496138616573952'), ('61560538303303680', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '5129710648430594', '496138616573952'), ('61560538273943553', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45069342940860416', '496138616573952'), ('61560538232000513', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45235228800716800', '496138616573952'), ('61560538227806209', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '44986029924421632', '496138616573952'), ('61560538219417601', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '61560480518377472', '496138616573952'), ('61560538215223297', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '61560275261722624', '496138616573952'), ('61560538211028993', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '61560041605435392', '496138616573952'), ('61560538206834688', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '57212882168844288', '496138616573952'), ('61560538202640384', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '56309618086776832', '496138616573952'), ('61560538194251777', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '57009981228060672', '496138616573952'), ('61560538190057472', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '57009744761589760', '496138616573952'), ('61560538177474560', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '57009544286441472', '496138616573952'), ('61560538173280256', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45237170029465600', '496138616573952'), ('61560538169085952', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45237010692050944', '496138616573952'), ('61560538143920128', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45236734832676864', '496138616573952'), ('61560538135531520', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '43117268627886080', '496138616573952'), ('61560538097782784', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45235939278065664', '496138616573952'), ('61560538072616960', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45235787867885568', '496138616573952'), ('61560538064228352', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '45235621697949696', '496138616573952'), ('61560538034868225', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '40238597734928384', '496138616573952'), ('61560538030673921', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '56898976661639168', '496138616573952'), ('61560538026479617', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '25014528525733888', '496138616573952'), ('61560538022285313', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16678447719911424', '496138616573952'), ('61560538018091008', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '15708892205944832', '496138616573952'), ('61560538009702400', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '15701915807518720', '496138616573952'), ('61560537946787840', null, '2018-10-08 11:11:57', '0', null, '2018-10-08 11:11:57', '16678126574637056', '496138616573952'), ('61560537896456192', null, '2018-10-08 11:11:56', '0', null, '2018-10-08 11:11:56', '15701400130424832', '496138616573952'), ('61560537850318848', null, '2018-10-08 11:11:56', '0', null, '2018-10-08 11:11:56', '5129710648430593', '496138616573952'), ('61560537766432768', null, '2018-10-08 11:11:56', '0', null, '2018-10-08 11:11:56', '5129710648430592', '496138616573952');
COMMIT;

-- ----------------------------
--  Table structure for `t_schedule_conf`
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_conf`;
CREATE TABLE `t_schedule_conf` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计划名称',
  `db_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备份路径',
  `corn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'corn表达式',
  `creat_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deltor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0:不可用   1：可用    2：停止   3：',
  `is_once` int(11) DEFAULT NULL COMMENT '是否只执行一次： 0：否    1：是',
  `job_class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '需要执行的任务类',
  `parameter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='建立计划任务，对数据库数据进行定期备份，配置备份策略';

-- ----------------------------
--  Table structure for `t_tax_application`
-- ----------------------------
DROP TABLE IF EXISTS `t_tax_application`;
CREATE TABLE `t_tax_application` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '公司名称',
  `TIN` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税务识别码',
  `country_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家代码',
  `country_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家名称',
  `applicant_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申请人id',
  `applicant_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '申请人',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL,
  `currency` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '币种',
  `financial_report` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '财务报表，文件表的外键id',
  `is_upload_report` int(11) DEFAULT NULL COMMENT '是否上传了财务报表',
  `creator` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `save_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='税金申请表单';

-- ----------------------------
--  Records of `t_tax_application`
-- ----------------------------
BEGIN;
INSERT INTO `t_tax_application` VALUES ('d0bd4738a02e4a56a9b136d7f5990666', '1', '三人行软件公司', '123123123', 'CHN', '中国', '682265633886208', 'admin', '这是一个备注', '0', 'CNY', '123123123123', '1', null, null, '2018-11-19 08:37:39', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_tax_application_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_tax_application_detail`;
CREATE TABLE `t_tax_application_detail` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tax_period` date DEFAULT NULL COMMENT '纳税所属期',
  `tax_dict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种',
  `payable_tax` decimal(10,2) DEFAULT NULL COMMENT '应交税额',
  `late_fee_payable` decimal(10,2) DEFAULT NULL COMMENT '应缴滞纳金',
  `appl_tax_payment` decimal(10,2) DEFAULT NULL COMMENT '申请缴纳税款',
  `deadline` datetime DEFAULT NULL COMMENT '缴款截止日期',
  `tax_paid` decimal(10,2) DEFAULT NULL COMMENT '实缴税额',
  `overdue_payment` decimal(10,2) DEFAULT NULL COMMENT '实缴滞纳金',
  `payment_time` datetime DEFAULT NULL COMMENT '实际缴税时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL,
  `tax_application_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主表Id',
  `tax_returns` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税务申报表(存储资料表外键Id)',
  `tax_returns_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税务申报表',
  `is_upload_tax_returns` int(11) DEFAULT NULL COMMENT '是否上传了税务申报表',
  `payment_certificate` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '扣款凭证',
  `payment_certificate_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '扣款凭证附件路径',
  `is_upload_certificate` int(11) DEFAULT NULL COMMENT '是否上传了扣款凭证',
  `other_upload` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '其他上传的文件',
  `other_upload_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='税金申请表单';

-- ----------------------------
--  Records of `t_tax_application_detail`
-- ----------------------------
BEGIN;
INSERT INTO `t_tax_application_detail` VALUES ('405710ffe69b44ee8734fef7d9dca663', '2018-11-01', 'Stamp tax', '13.11', '0.00', '10.00', '2018-11-08 18:00:00', '12.11', '0.00', '2018-11-07 18:00:00', '子备注1', null, 'd0bd4738a02e4a56a9b136d7f5990666', '1212', 'c://dd/aa/adf.txt', null, '1212', 'c://dfdf//dfdf/aaa.doc', '1', '', null), ('4f2c8ae45dbe45f4b46f084b2ef95e16', '2018-11-01', 'Resources tax', '12.11', '0.00', '10.00', '2018-11-08 18:00:00', '12.11', '0.00', '2018-11-07 18:00:00', '子备注1', null, 'd0bd4738a02e4a56a9b136d7f5990666', '1212', 'c://dd/aa/adf.txt', null, '1212', 'c://dfdf//dfdf/aaa.doc', '1', '', null);
COMMIT;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '0:女  1：男',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '盐',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `work_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '工号',
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `roleId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `departId` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '是否冻结0:冻结  1:正常',
  `frozen_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT '1' COMMENT '0：已删除   1：未删除',
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('633f7dbdf41341ccbf410c4d2cb19988', '0', 'test', '王麻子', '123456', 'IEJ5', '2898911401@qq.com', '003', '17792073879', null, null, '1', null, null, null, '1', null, null), ('682265633886208', '1', 'admin', '超级管理员', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '', '289911401@qq.com', '002', '17792073879', '1', '1', '0', null, null, '2018-10-23 17:37:55', '1', null, null), ('eccdcaaf6fd04999bb54e140766c156e', '0', 'zhangsan', '张三', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '', null, '001', null, '0', '0', '0', null, null, '2018-09-23 17:37:12', '1', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_user_company`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_company`;
CREATE TABLE `t_user_company` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `company_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户公司表';

-- ----------------------------
--  Records of `t_user_company`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_company` VALUES ('1', '682265633886208', '张三', '1', '三人行', 'admin', '2018-11-02 16:22:12', null, null, null), ('2', '682265633886208', '张三', '2', '四人组', 'admin', '2018-11-02 16:26:44', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_user_department`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_department`;
CREATE TABLE `t_user_department` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `department_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户部门表';

-- ----------------------------
--  Records of `t_user_department`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_department` VALUES ('1', '682265633886208', '4', null, null, null, null, null, null), ('2', '682265633886208', '5', null, null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '是否可用：0：不可用   1：可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
--  Records of `t_user_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role` VALUES ('1', '496138616573952', '682265633886208', '2018-11-01 22:06:58', '123', '0', null, '0'), ('3b4ef1b76dc3480bafd0136ec53ed76e', '496138616573952', '633f7dbdf41341ccbf410c4d2cb19988', null, null, null, null, '1');
COMMIT;

-- ----------------------------
--  Table structure for `tax_config`
-- ----------------------------
DROP TABLE IF EXISTS `tax_config`;
CREATE TABLE `tax_config` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `country_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家代码',
  `country_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家名称',
  `tax_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种代码',
  `tax_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种名称',
  `tax_period_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种期限代码',
  `tax_period_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种期限名称',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `deletor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
