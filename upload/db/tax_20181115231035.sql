-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: tax
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `QRTZ_BLOB_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_BLOB_TRIGGERS`
--

LOCK TABLES `QRTZ_BLOB_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_BLOB_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CALENDARS`
--

DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CALENDARS`
--

LOCK TABLES `QRTZ_CALENDARS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_CALENDARS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_CRON_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(120) COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_CRON_TRIGGERS`
--

LOCK TABLES `QRTZ_CRON_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('SchedulerFactory','com.chinasoft.tax.quartz.jobs.SampleJob','DEFAULT','0/5 * * * * ? ','Asia/Shanghai');
/*!40000 ALTER TABLE `QRTZ_CRON_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_FIRED_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_FIRED_TRIGGERS`
--

LOCK TABLES `QRTZ_FIRED_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('SchedulerFactory','NON_CLUSTERED1542294618061','com.chinasoft.tax.quartz.jobs.SampleJob','DEFAULT','NON_CLUSTERED',1542294635069,1542294640000,5,'ACQUIRED',NULL,NULL,'0','0');
/*!40000 ALTER TABLE `QRTZ_FIRED_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_JOB_DETAILS`
--

DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_JOB_DETAILS`
--

LOCK TABLES `QRTZ_JOB_DETAILS` WRITE;
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` DISABLE KEYS */;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('SchedulerFactory','com.chinasoft.tax.quartz.jobs.SampleJob','DEFAULT',NULL,'com.chinasoft.tax.quartz.jobs.SampleJob','0','0','0','0',_binary '�\�\0sr\0org.quartz.JobDataMap���迩�\�\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�\�\��\�](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap\�.�(v\n\�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	parameterpx\0');
/*!40000 ALTER TABLE `QRTZ_JOB_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_LOCKS`
--

DROP TABLE IF EXISTS `QRTZ_LOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_LOCKS`
--

LOCK TABLES `QRTZ_LOCKS` WRITE;
/*!40000 ALTER TABLE `QRTZ_LOCKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_LOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_PAUSED_TRIGGER_GRPS`
--

LOCK TABLES `QRTZ_PAUSED_TRIGGER_GRPS` WRITE;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_PAUSED_TRIGGER_GRPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SCHEDULER_STATE`
--

DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SCHEDULER_STATE`
--

LOCK TABLES `QRTZ_SCHEDULER_STATE` WRITE;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SCHEDULER_STATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPLE_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPLE_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPLE_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPLE_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_SIMPROP_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_SIMPROP_TRIGGERS`
--

LOCK TABLES `QRTZ_SIMPROP_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `QRTZ_SIMPROP_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QRTZ_TRIGGERS`
--

DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QRTZ_TRIGGERS`
--

LOCK TABLES `QRTZ_TRIGGERS` WRITE;
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` DISABLE KEYS */;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('SchedulerFactory','com.chinasoft.tax.quartz.jobs.SampleJob','DEFAULT','com.chinasoft.tax.quartz.jobs.SampleJob','DEFAULT',NULL,1542294640000,1542294635000,5,'ACQUIRED','CRON',1542294239000,0,NULL,0,'');
/*!40000 ALTER TABLE `QRTZ_TRIGGERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_company`
--

DROP TABLE IF EXISTS `t_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_company`
--

LOCK TABLES `t_company` WRITE;
/*!40000 ALTER TABLE `t_company` DISABLE KEYS */;
INSERT INTO `t_company` VALUES ('1','三人行软件公司','123123','2018-11-02 16:21:23','CHN','CNY','11','2018-11-02 16:21:31','123',NULL,NULL,NULL,NULL,1),('2','四人组科技有限公司','123','2018-11-02 16:25:59','CHN','CNY','11','2018-11-02 16:26:03','admin',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `t_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_department`
--

DROP TABLE IF EXISTS `t_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_department`
--

LOCK TABLES `t_department` WRITE;
/*!40000 ALTER TABLE `t_department` DISABLE KEYS */;
INSERT INTO `t_department` VALUES ('1','0','总部','admin','2018-11-02 20:58:29',NULL,NULL,NULL,0),('2','1','技术部','admin','2018-11-02 20:58:45',NULL,NULL,NULL,0),('4','2','开发部','admin','2018-11-05 10:42:40',NULL,NULL,NULL,NULL),('5','2','测试部','admin','2018-11-05 10:42:52',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_department_permission`
--

DROP TABLE IF EXISTS `t_department_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_department_permission`
--

LOCK TABLES `t_department_permission` WRITE;
/*!40000 ALTER TABLE `t_department_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_department_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dict`
--

DROP TABLE IF EXISTS `t_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict`
--

LOCK TABLES `t_dict` WRITE;
/*!40000 ALTER TABLE `t_dict` DISABLE KEYS */;
INSERT INTO `t_dict` VALUES ('16f02d82045f4198a7371ff995b720ff','增值税','VAT',2,'税种代码',NULL,'2018-11-05 15:42:56',NULL,NULL,NULL,0,NULL),('2a48d86334cb49be9da031a556dadfac','关税','Tariff',2,'税种代码',NULL,'2018-11-05 15:43:28',NULL,NULL,NULL,0,NULL),('31d129fea1904b87bed5f44d91dc6123','季度','SEANSON',3,'纳税期限',NULL,'2018-11-05 16:25:03',NULL,NULL,NULL,0,NULL),('4a933b8dc81c4600b6f004604ecd5caf','次','TIME',3,'纳税期限',NULL,'2018-11-05 16:26:16',NULL,NULL,NULL,0,NULL),('727e894f2f13413dac655e8436fc4e82','美国','USA',0,'国家代码',NULL,'2018-11-05 15:36:23',NULL,NULL,NULL,NULL,NULL),('7dac0bd07ebb49c389fd8391a691c21e','年度汇算清缴','YEAR',3,'纳税期限',NULL,'2018-11-05 16:25:21',NULL,NULL,NULL,0,NULL),('92eb2702d20249db93442569d892997c','分期缴纳','INSTANLLMENT',3,'纳税期限',NULL,'2018-11-05 16:25:51',NULL,NULL,NULL,0,NULL),('a9a95becfc8b4687a35ceaac529dd411','欧盟','EU',0,'国家代码',NULL,'2018-11-05 15:37:26',NULL,NULL,NULL,NULL,NULL),('ad0e0b9932e449f6b3be0fc5e2f953f9','中国','CHN',0,'国家代码',NULL,'2018-11-05 15:35:39',NULL,NULL,NULL,NULL,NULL),('ad7f5972e34a40468182bfb1dc011dd5','房产税','Housing property tax',2,'税种代码',NULL,'2018-11-05 15:43:14',NULL,NULL,NULL,0,NULL),('b844825480324ac899095c4bba71859a','印花税','Stamp tax',2,'税种代码',NULL,'2018-11-05 15:42:34',NULL,NULL,NULL,0,NULL),('bab10acfed6840a4a6633b5554eac029','资源税','Resources tax',2,'税种代码',NULL,'2018-11-05 15:43:41',NULL,NULL,NULL,0,NULL),('f3f77feade04484f977e70f396d0bb0e','人民币','CNY',1,'币种代码',NULL,'2018-11-05 15:40:03',NULL,NULL,NULL,0,NULL),('f4014dc7823f4ad496bc469bd2f46701','次1','TIME1',3,'纳税期限',NULL,'2018-11-05 20:09:14',NULL,NULL,NULL,0,NULL),('f60c2a9599d149d8a92221e8c12d1526','月度','MONTH',3,'纳税期限',NULL,'2018-11-05 16:24:50',NULL,NULL,NULL,0,NULL),('fae7c60dae994f0ca04a9506d52edf53','欧元','EUR',1,'币种代码',NULL,'2018-11-05 15:40:36',NULL,NULL,NULL,0,NULL),('fc20170154fd426792ebffd93c55f2c5','美元','USD',1,'币种代码',NULL,'2018-11-05 15:40:21',NULL,NULL,NULL,0,NULL),('fec885b646c7445e913bbb30aa559d1e','英国','UK',0,'国家代码',NULL,'2018-11-05 15:36:39',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_element_permisson`
--

DROP TABLE IF EXISTS `t_element_permisson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_element_permisson`
--

LOCK TABLES `t_element_permisson` WRITE;
/*!40000 ALTER TABLE `t_element_permisson` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_element_permisson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log_info`
--

DROP TABLE IF EXISTS `t_log_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_log_info` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `operation_time` datetime DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用的方法名称',
  `method_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '调用的代码方法',
  `param` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '操作之前的数据的入参数据',
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log_info`
--

LOCK TABLES `t_log_info` WRITE;
/*!40000 ALTER TABLE `t_log_info` DISABLE KEYS */;
INSERT INTO `t_log_info` VALUES ('5a7ac8629c594a9ea18492a8e3f0fda7',NULL,'admin','2018-11-13 10:37:54','上传资料','com.chinasoft.tax.controller.FileController.uploadFile','files=[Lorg.springframework.web.multipart.MultipartFile;@e03a87a;materialTypeDict=DONE_TAX_REPORT',NULL,NULL,'127.0.0.1','2018-11-13 10:37:54','admin',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_log_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material`
--

DROP TABLE IF EXISTS `t_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material`
--

LOCK TABLES `t_material` WRITE;
/*!40000 ALTER TABLE `t_material` DISABLE KEYS */;
INSERT INTO `t_material` VALUES ('207cb253d59c4ef1a17af98de9be8af4',NULL,NULL,'西豆科技有限公司相关账号.pdf','41743612e7a14ffc86de022a66e678c1.pdf','.pdf','upload/41743612e7a14ffc86de022a66e678c1.pdf','2018-11-12 06:53:18',NULL,NULL,NULL,NULL,NULL,NULL,'TAX_REPORT'),('3a5db97d96304ffcb99e303523bfa8a8',NULL,NULL,'WordRqmErrors.log','3df1bdd164d841acb1e798b5b0d58977.log','.log','upload/3df1bdd164d841acb1e798b5b0d58977.log','2018-11-11 07:26:48',NULL,NULL,NULL,NULL,NULL,NULL,'FINANCE_REPORT'),('965f34082e454419a5c2d5bf483ad499',NULL,NULL,'WordRqmErrors.log','19a51c1060dc46cab57dba7a5833242c.log','.log','upload/19a51c1060dc46cab57dba7a5833242c.log','2018-11-11 07:26:48',NULL,NULL,NULL,NULL,NULL,NULL,'FINANCE_REPORT'),('a557d8c596eb4b248c6099987f06badf',NULL,NULL,'WordRqmErrors.log','ab191bc0c50940afafcd442e1a07c3b2.log','.log','upload/ab191bc0c50940afafcd442e1a07c3b2.log','2018-11-11 09:26:55',NULL,NULL,NULL,NULL,NULL,NULL,'FINANCE_REPORT'),('a90e961f074641f996184702dfa7e813',NULL,NULL,'西豆科技有限公司相关账号.pdf','a09bbd009dfc45f59bd543264d36ff4f.pdf','.pdf','upload/a09bbd009dfc45f59bd543264d36ff4f.pdf','2018-11-12 06:51:48',NULL,NULL,NULL,NULL,NULL,NULL,'TAX_REPORT'),('c7c5fd8bbfd74385b1d8d137bb652006',NULL,NULL,'bookmarks_2018_3_20.html','14298108ef5b4194be8901a3f022dbcd.html','.html','upload/14298108ef5b4194be8901a3f022dbcd.html','2018-11-13 10:37:54',NULL,NULL,NULL,NULL,NULL,NULL,'DONE_TAX_REPORT'),('cb86b842a6c14284a1b56af301ef1798',NULL,NULL,'西豆科技有限公司相关账号.pdf','814079defb434356bfd887d24e3932d4.pdf','.pdf','upload/814079defb434356bfd887d24e3932d4.pdf','2018-11-12 05:23:50',NULL,NULL,NULL,NULL,NULL,NULL,'DONE_TAX_REPORT'),('ee39804445964249b512d48ff89cb5c6',NULL,NULL,'WordRqmErrors.log','6e594a3eec134292bc06aa0101125e14.log','.log','upload/6e594a3eec134292bc06aa0101125e14.log','2018-11-11 07:26:47',NULL,NULL,NULL,NULL,NULL,NULL,'FINANCE_REPORT');
/*!40000 ALTER TABLE `t_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material_type`
--

DROP TABLE IF EXISTS `t_material_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material_type`
--

LOCK TABLES `t_material_type` WRITE;
/*!40000 ALTER TABLE `t_material_type` DISABLE KEYS */;
INSERT INTO `t_material_type` VALUES ('1','财务报表','FINANCE_REPORT','admin','2018-11-11 18:06:53',NULL,NULL,NULL),('2','税务申报表','TAX_REPORT','admin','2018-11-11 18:07:21',NULL,NULL,NULL),('3','完税申报','DONE_TAX_REPORT','admin','2018-11-11 18:08:09',NULL,NULL,NULL),('4','其他','OTHER','admin','2018-11-11 18:08:31',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_material_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES ('5129710648430592','','2018-06-04 19:02:29',0,'','2018-09-29 23:11:56','','sys','',0,1.00,'Main','/sys','系统管理','ios-settings',1,'',0,''),('5129710648430593','','2018-06-04 19:02:32',0,'','2018-08-13 15:15:33','','user-manage','5129710648430592',0,1.10,'sys/user-manage/userManage','user-manage','用户管理','md-person',2,'',0,''),('5129710648430594','','2018-06-04 19:02:35',0,'','2018-09-23 23:32:16','','role-manage','5129710648430592',0,1.60,'sys/role-manage/roleManage','role-manage','角色管理','md-contacts',2,'',0,''),('5129710648430595','','2018-06-04 19:02:37',0,'','2018-09-23 23:32:02','','menu-manage','5129710648430592',0,1.70,'sys/menu-manage/menuManage','menu-manage','菜单权限管理','md-menu',2,'',0,''),('41375330996326400','','2018-08-13 18:23:08',0,'','2018-08-15 17:13:23','','simple-table','41373430515240960',0,4.10,'xboot-vue-template/simple-table/simpleTable','simple-table','简单表格','ios-grid-outline',2,'',0,''),('15701400130424832','','2018-06-03 22:04:06',0,'','2018-09-19 22:16:44','','','5129710648430593',1,1.11,'','/xboot/user/admin/add*','添加用户','',3,'add',0,''),('15701915807518720','','2018-06-03 22:06:09',0,'','2018-06-06 14:46:51','','','5129710648430593',1,1.13,'','/xboot/user/admin/disable/**','禁用用户','',3,'disable',0,NULL),('15708892205944832','','2018-06-03 22:33:52',0,'','2018-06-28 16:44:48','','','5129710648430593',1,1.14,'','/xboot/user/admin/enable/**','启用用户','',3,'enable',0,NULL),('16392452747300864','','2018-06-05 19:50:06',0,'','2018-08-13 18:15:39','','access','',0,4.00,'Main','/access','权限按钮测试页','md-lock',1,'',0,''),('16392767785668608','','2018-06-05 19:51:21',0,'','2018-09-21 23:31:06','','access_index','16392452747300864',0,4.10,'access/access','index','权限按钮测试页','md-lock',2,'',0,''),('16438800255291392','','2018-06-05 22:54:18',0,'','2018-08-13 18:15:51','','','16392767785668608',1,4.11,'','test-add','添加按钮测试','',3,'add',0,''),('16438962738434048','','2018-06-05 22:54:55',0,'','2018-08-13 18:16:29','','','16392767785668608',1,4.12,'','edit-test','编辑按钮测试','',3,'edit',0,''),('16439068543946752','','2018-06-05 22:55:20',0,'','2018-08-13 18:16:12','','','16392767785668608',1,4.13,'','delete-test','删除按钮测试','',3,'delete',0,''),('16678126574637056','','2018-06-06 14:45:16',0,'','2018-09-19 22:16:48','','','5129710648430593',1,1.12,'','/xboot/user/admin/edit*','编辑用户','',3,'edit',0,''),('16678447719911424','','2018-06-06 14:46:32',0,'','2018-08-10 21:41:16','','','5129710648430593',1,1.15,'','/xboot/user/delByIds/**','删除用户','',3,'delete',0,''),('16687383932047360','','2018-06-06 15:22:03',0,'','2018-09-19 22:07:34','','','5129710648430594',1,1.21,'','/xboot/role/save*','添加角色','',3,'add',0,''),('16689632049631232','','2018-06-06 15:30:59',0,'','2018-09-19 22:07:37','','','5129710648430594',1,1.22,'','/xboot/role/edit*','编辑角色','',3,'edit',0,''),('16689745006432256','','2018-06-06 15:31:26',0,'','2018-08-10 21:41:23','','','5129710648430594',1,1.23,'','/xboot/role/delAllByIds/**','删除角色','',3,'delete',0,''),('16689883993083904',NULL,'2018-06-06 15:31:59',0,NULL,'2018-06-06 15:31:59',NULL,NULL,'5129710648430594',1,1.24,NULL,'/xboot/role/editRolePerm/**','分配权限',NULL,3,'editPerm',0,NULL),('16690313745666048','','2018-06-06 15:33:41',0,'','2018-09-19 22:07:46','','','5129710648430594',1,1.25,'','/xboot/role/setDefault*','设为默认角色','',3,'setDefault',0,''),('16694861252005888','','2018-06-06 15:51:46',0,'','2018-09-19 22:07:52','','','5129710648430595',1,1.31,'','/xboot/permission/add*','添加菜单','',3,'add',0,''),('16695107491205120','','2018-06-06 15:52:44',0,'','2018-09-19 22:07:57','','','5129710648430595',1,1.32,'','/xboot/permission/edit*','编辑菜单','',3,'edit',0,''),('16695243126607872','','2018-06-06 15:53:17',0,'','2018-08-10 21:41:33','','','5129710648430595',1,1.33,'','/xboot/permission/delByIds/**','删除菜单','',3,'delete',0,''),('41371711400054784','','2018-08-13 18:08:45',0,'','2018-08-14 12:31:15','','actuator','39915540965232640',0,2.30,'sys/actuator/actuator','actuator','Actuator监控[付费]','logo-angular',2,'',0,''),('41370251991977984',NULL,'2018-08-13 18:02:57',0,NULL,'2018-08-13 18:02:57',NULL,'quartz-job','39915540965232640',0,2.10,'sys/quartz-manage/quartzManage','quartz-job','定时任务','md-time',2,'',0,NULL),('25014528525733888','','2018-06-29 14:51:09',0,'','2018-10-08 11:13:27','','','5129710648430593',1,1.16,'','无','上传图片','',3,'upload',0,''),('39915540965232640',NULL,'2018-08-09 17:42:28',0,NULL,'2018-08-09 17:42:28',NULL,'monitor',NULL,0,2.00,'Main','/monitor','系统监控','ios-analytics',1,NULL,0,NULL),('39916171171991552','','2018-08-09 17:44:57',0,'','2018-08-25 12:13:11','','druid','39915540965232640',0,2.40,'sys/monitor/monitor','druid','SQL监控','md-analytics',2,'',0,'http://xboot.exrick.cn/druid/login.html'),('39918482854252544','','2018-08-09 17:54:08',0,'','2018-08-25 12:13:27','','swagger','39915540965232640',0,2.50,'sys/monitor/monitor','swagger','接口文档','md-document',2,'',0,'http://xboot.exrick.cn/swagger-ui.html'),('40238597734928384',NULL,'2018-08-10 15:06:10',0,NULL,'2018-08-10 15:06:10',NULL,'department-manage','5129710648430592',0,1.20,'sys/department-manage/departmentManage','department-manage','部门管理','md-git-branch',2,'',0,NULL),('42082442672082944','','2018-08-15 17:12:57',0,'','2018-09-25 15:17:59','','new-window','41373430515240960',0,4.60,'xboot-vue-template/new-window/newWindow','new-window','新窗口操作[付费]','ios-browsers',2,'',0,''),('41373430515240960','','2018-08-13 18:15:35',0,'','2018-08-15 14:29:48','','xboot-vue-template','',0,3.00,'Main','/xboot-vue-template','前端Vue模版','ios-albums',1,'',0,''),('41363147411427328','','2018-08-13 17:34:43',0,'','2018-08-20 20:05:21','','log-manage','39915540965232640',0,2.20,'sys/log-manage/logManage','log-manage','操作日志管理','md-list-box',2,'',0,''),('41363537456533504','','2018-08-13 17:36:16',0,'','2018-08-13 17:56:11','','','41363147411427328',1,2.11,'','/xboot/log/delByIds/**','删除日志','',3,'delete',0,''),('41364927394353152','','2018-08-13 17:41:48',0,'','2018-09-19 22:08:57','','','41363147411427328',1,2.12,'','/xboot/log/delAll*','清空日志','',3,'undefined',0,''),('41376192166629376','','2018-08-13 18:26:33',0,'','2018-08-15 17:13:34','','search-table','41373430515240960',0,4.20,'xboot-vue-template/search-table/searchTable','search-table','搜索表格[付费]','md-grid',2,'',0,''),('41377034236071936','','2018-08-13 18:29:54',0,'','2018-08-15 17:13:40','','complex-table','41373430515240960',0,4.30,'xboot-vue-template/complex-table/complexTable','complex-table','复杂表格[付费]','ios-grid',2,'',0,''),('41378916912336896','','2018-08-13 18:37:23',0,'','2018-09-25 15:19:50','','tree','41373430515240960',0,4.50,'xboot-vue-template/tree/tree','tree','树形结构[付费]','ios-git-network',2,'',0,''),('41469219249852416',NULL,'2018-08-14 00:36:13',0,NULL,'2018-08-14 00:36:13',NULL,'','41371711400054784',1,2.31,'','无','查看数据','',3,'view',0,NULL),('42087054753927168','','2018-08-15 17:31:16',0,'','2018-09-25 15:18:05','','html-edit','41373430515240960',0,4.70,'xboot-vue-template/html-edit/htmlEdit','html-edit','富文本编辑[付费]','ios-create',2,'',0,''),('43117268627886080','','2018-08-18 13:44:58',0,'','2018-08-18 20:55:04','','message-manage','5129710648430592',0,1.30,'sys/message-manage/messageManage','message-manage','消息管理[付费]','md-mail',2,'',0,''),('44986029924421632','','2018-08-23 17:30:46',0,'','2018-09-23 23:26:53','','social-manage','5129710648430592',0,1.50,'sys/social-manage/socialManage','social-manage','社交账号绑定[付费]','md-share',2,'',0,''),('45069342940860416','','2018-08-23 23:01:49',0,'','2018-08-24 10:01:09','','','44986029924421632',1,1.42,'','无','查看社交账号数据','',3,'view',0,''),('45235228800716800','','2018-08-24 10:01:00',0,'','2018-09-19 22:07:23','','','44986029924421632',1,1.41,'','/xboot/relate/delByIds*','删除解绑','',3,'delete',0,''),('45235621697949696','','2018-08-24 10:02:33',0,'','2018-09-19 22:06:57','','','40238597734928384',1,1.21,'','/xboot/department/add*','添加部门','',3,'add',0,''),('45235787867885568','','2018-08-24 10:03:13',0,'','2018-09-19 22:07:02','','','40238597734928384',1,1.22,'','/xboot/department/edit*','编辑部门','',3,'edit',0,''),('45235939278065664',NULL,'2018-08-24 10:03:49',0,NULL,'2018-08-24 10:03:49',NULL,'','40238597734928384',1,1.23,'','/xboot/department/delByIds/*','删除部门','',3,'delete',0,NULL),('45236734832676864','','2018-08-24 10:06:59',0,'','2018-09-19 22:07:07','','','43117268627886080',1,1.31,'','/xboot/message/add*','添加消息','',3,'add',0,''),('45237010692050944','','2018-08-24 10:08:04',0,'','2018-09-19 22:07:12','','','43117268627886080',1,1.32,'','/xboot/message/edit*','编辑消息','',3,'edit',0,''),('45237170029465600',NULL,'2018-08-24 10:08:42',0,NULL,'2018-08-24 10:08:42',NULL,'','43117268627886080',1,1.33,'','/xboot/message/delByIds/*','删除消息','',3,'delete',0,NULL),('45264987354042368','','2018-08-24 11:59:14',0,'','2018-09-19 22:08:11','','','41370251991977984',1,2.11,'','/xboot/quartzJob/add*','添加定时任务','',3,'add',0,''),('45265487029866496','','2018-08-24 12:01:14',0,'','2018-09-19 22:08:17','','','41370251991977984',1,2.12,'','/xboot/quartzJob/edit*','编辑定时任务','',3,'edit',0,''),('45265762415284224','','2018-08-24 12:02:19',0,'','2018-09-19 22:08:24','','','41370251991977984',1,2.13,'','/xboot/quartzJob/pause*','暂停定时任务','',3,'disable',0,''),('45265886315024384','','2018-08-24 12:02:49',0,'','2018-09-19 22:09:13','','','41370251991977984',1,2.14,'','/xboot/quartzJob/resume*','恢复定时任务','',3,'enable',0,''),('45266070000373760',NULL,'2018-08-24 12:03:33',0,NULL,'2018-08-24 12:03:33',NULL,'','41370251991977984',1,2.15,'','/xboot/quartzJob/delByIds/*','删除定时任务','',3,'delete',0,NULL),('56309618086776832',NULL,'2018-09-23 23:26:40',0,NULL,'2018-09-23 23:26:40',NULL,'oss-manage','5129710648430592',0,1.40,'sys/oss-manage/ossManage','oss-manage','OSS对象存储[付费]','ios-folder',2,'',0,NULL),('56898976661639168','','2018-09-25 14:28:34',0,'','2018-09-25 15:12:46','','','5129710648430593',1,1.17,'','/xboot/user/importData*','导入用户','',3,'input',0,''),('56911328312299520','','2018-09-25 15:17:39',0,'','2018-09-25 18:31:49','','excel','41373430515240960',0,4.40,'xboot-vue-template/excel/excel','excel','Excel导入导出[付费]','md-exit',2,'',0,''),('57009544286441472',NULL,'2018-09-25 21:47:55',0,NULL,'2018-09-25 21:47:55',NULL,'','43117268627886080',1,1.40,'','/xboot/messageSend/save*','添加已发送消息','',3,'add',0,NULL),('57009744761589760',NULL,'2018-09-25 21:48:43',0,NULL,'2018-09-25 21:48:43',NULL,'','43117268627886080',1,1.50,'','/xboot/messageSend/update*','编辑已发送消息','',3,'edit',0,NULL),('57009981228060672',NULL,'2018-09-25 21:49:39',0,NULL,'2018-09-25 21:49:39',NULL,'','43117268627886080',1,1.60,'','/xboot/messageSend/delByIds/*','删除已发送消息','',3,'delete',0,NULL),('57212882168844288','','2018-09-26 11:15:55',0,'','2018-10-08 11:10:05','','','56309618086776832',1,1.41,'','无','上传文件','',3,'upload',0,''),('58480609315524608','','2018-09-29 23:13:24',0,'','2018-09-29 23:17:59','','setting','5129710648430592',0,1.80,'sys/setting-manage/settingManage','setting','系统配置[付费]','ios-settings-outline',2,'',0,''),('61394706252173312',NULL,'2018-10-08 00:12:59',0,NULL,'2018-10-08 00:12:59',NULL,'','58480609315524608',1,1.81,'','/xboot/setting/seeSecret/**','查看私密配置','',3,'view',0,NULL),('61417744146370560','','2018-10-08 01:44:32',0,'','2018-10-08 01:50:03','','','58480609315524608',1,1.82,'','/xboot/setting/*/set*','编辑配置','',3,'edit',0,''),('61560480518377472',NULL,'2018-10-08 11:11:43',0,NULL,'2018-10-08 11:11:43',NULL,'','56309618086776832',1,1.44,'','/xboot/file/delete/*','删除文件','',3,'delete',0,NULL),('61560275261722624',NULL,'2018-10-08 11:10:54',0,NULL,'2018-10-08 11:10:54',NULL,'','56309618086776832',1,1.43,'','/xboot/file/copy*','复制文件','',3,'edit',0,NULL),('61560041605435392',NULL,'2018-10-08 11:09:58',0,NULL,'2018-10-08 11:09:58',NULL,'','56309618086776832',1,1.42,'','/xboot/file/rename*','重命名文件','',3,'edit',0,NULL);
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_quartz_job`
--

DROP TABLE IF EXISTS `t_quartz_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_quartz_job`
--

LOCK TABLES `t_quartz_job` WRITE;
/*!40000 ALTER TABLE `t_quartz_job` DISABLE KEYS */;
INSERT INTO `t_quartz_job` VALUES ('1749f8f32dd943ecb89dd77f8e7dcddf','admin','2018-11-14 10:57:41',NULL,'admin','2018-11-15 09:09:32','0/5 * * * * ? ','无参测试 后台将每隔5秒执行输出日志','com.chinasoft.tax.quartz.jobs.SampleJob',NULL,0);
/*!40000 ALTER TABLE `t_quartz_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES ('16457350655250432','测试人员','ROLE_TEST','2018-10-24 19:40:05','admin',0,NULL,NULL,NULL,_binary '\0'),('39dafa93960a42179f39dc92ff4527ff','运维','ROLE_OPERATION',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('496138616573952','超级管理员','ROLE_ADMINISTRATOR','2018-10-24 19:39:21','admin',0,NULL,NULL,NULL,NULL),('496138616573953','开发人员','ROLE_DEVELOP','2018-10-24 19:39:48','admin',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_api`
--

DROP TABLE IF EXISTS `t_role_api`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_api`
--

LOCK TABLES `t_role_api` WRITE;
/*!40000 ALTER TABLE `t_role_api` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_api` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_company`
--

DROP TABLE IF EXISTS `t_role_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_company`
--

LOCK TABLES `t_role_company` WRITE;
/*!40000 ALTER TABLE `t_role_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_element`
--

DROP TABLE IF EXISTS `t_role_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_element`
--

LOCK TABLES `t_role_element` WRITE;
/*!40000 ALTER TABLE `t_role_element` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_material`
--

DROP TABLE IF EXISTS `t_role_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_material`
--

LOCK TABLES `t_role_material` WRITE;
/*!40000 ALTER TABLE `t_role_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` VALUES ('1b9b974503844278811c0c3678ada6cf','1','2','admin','2018-10-24 20:55:10',0,NULL),('995bde8de36742c693ec9d7f12574534','1','1','admin','2018-10-24 20:55:10',0,NULL),('a16470b9037645b7862fdffc55be9d29','1','4','admin','2018-10-24 20:55:10',0,NULL),('aab5556b908846daaa8c1e7655938b01','1','3','admin','2018-10-24 20:55:10',0,NULL);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES ('16457624555884544',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16392452747300864','16457350655250432'),('16457624597827584',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16392767785668608','16457350655250432'),('16457624643964928',NULL,'2018-06-06 00:09:04',0,NULL,'2018-06-06 00:09:04','16439068543946752','16457350655250432'),('61560538731122689',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16439068543946752','496138616573952'),('61560538726928385',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16438962738434048','496138616573952'),('61560538718539777',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16438800255291392','496138616573952'),('61414448241315840',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','16439068543946752','496138616573953'),('61414448207761408',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','16438962738434048','496138616573953'),('61414448165818368',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','16438800255291392','496138616573953'),('61414448123875328',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','16392767785668608','496138616573953'),('61414448069349376',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','16392452747300864','496138616573953'),('61414448014823424',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','42087054753927168','496138616573953'),('61414447985463296',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','42082442672082944','496138616573953'),('61414447935131648',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41378916912336896','496138616573953'),('61414447884800000',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','56911328312299520','496138616573953'),('61414447868022784',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41377034236071936','496138616573953'),('61414447847051264',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41376192166629376','496138616573953'),('61414447826079744',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41375330996326400','496138616573953'),('61414447813496832',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41373430515240960','496138616573953'),('61414447796719616',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','39918482854252544','496138616573953'),('61414447758970880',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','39916171171991552','496138616573953'),('61414447717027840',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41371711400054784','496138616573953'),('61414447675084800',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41363147411427328','496138616573953'),('61414447666696192',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','41370251991977984','496138616573953'),('61414447649918976',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','39915540965232640','496138616573953'),('61414447633141760',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','58480609315524608','496138616573953'),('61414447603781632',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','5129710648430595','496138616573953'),('61414447574421504',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','5129710648430594','496138616573953'),('61414447524089856',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','44986029924421632','496138616573953'),('61414447486341120',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','56309618086776832','496138616573953'),('61414447431815168',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','43117268627886080','496138616573953'),('61414447381483520',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','40238597734928384','496138616573953'),('61414447368900608',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','5129710648430593','496138616573953'),('61414447305986048',NULL,'2018-10-08 01:31:26',0,NULL,'2018-10-08 01:31:26','5129710648430592','496138616573953'),('61560538714345473',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16392767785668608','496138616573952'),('61560538710151169',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16392452747300864','496138616573952'),('61560538705956865',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','42087054753927168','496138616573952'),('61560538701762561',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','42082442672082944','496138616573952'),('61560538697568256',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41378916912336896','496138616573952'),('61560538693373952',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','56911328312299520','496138616573952'),('61560538684985345',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41377034236071936','496138616573952'),('61560538680791041',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41376192166629376','496138616573952'),('61560538676596736',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41375330996326400','496138616573952'),('61560538668208128',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41373430515240960','496138616573952'),('61560538575933441',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','39918482854252544','496138616573952'),('61560538571739137',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','39916171171991552','496138616573952'),('61560538567544833',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41469219249852416','496138616573952'),('61560538563350529',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41371711400054784','496138616573952'),('61560538559156225',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41364927394353152','496138616573952'),('61560538554961921',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41363537456533504','496138616573952'),('61560538550767616',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41363147411427328','496138616573952'),('61560538542379008',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45266070000373760','496138616573952'),('61560538529796096',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45265886315024384','496138616573952'),('61560538504630272',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45265762415284224','496138616573952'),('61560538462687232',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45265487029866496','496138616573952'),('61560538437521408',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45264987354042368','496138616573952'),('61560538429132801',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','41370251991977984','496138616573952'),('61560538424938497',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','39915540965232640','496138616573952'),('61560538420744193',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','61417744146370560','496138616573952'),('61560538416549889',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','61394706252173312','496138616573952'),('61560538412355585',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','58480609315524608','496138616573952'),('61560538408161280',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16695243126607872','496138616573952'),('61560538403966976',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16695107491205120','496138616573952'),('61560538399772672',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16694861252005888','496138616573952'),('61560538370412545',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','5129710648430595','496138616573952'),('61560538324275200',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16690313745666048','496138616573952'),('61560538320080896',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16689883993083904','496138616573952'),('61560538315886592',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16689745006432256','496138616573952'),('61560538311692288',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16689632049631232','496138616573952'),('61560538307497984',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16687383932047360','496138616573952'),('61560538303303680',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','5129710648430594','496138616573952'),('61560538273943553',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45069342940860416','496138616573952'),('61560538232000513',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45235228800716800','496138616573952'),('61560538227806209',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','44986029924421632','496138616573952'),('61560538219417601',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','61560480518377472','496138616573952'),('61560538215223297',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','61560275261722624','496138616573952'),('61560538211028993',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','61560041605435392','496138616573952'),('61560538206834688',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','57212882168844288','496138616573952'),('61560538202640384',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','56309618086776832','496138616573952'),('61560538194251777',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','57009981228060672','496138616573952'),('61560538190057472',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','57009744761589760','496138616573952'),('61560538177474560',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','57009544286441472','496138616573952'),('61560538173280256',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45237170029465600','496138616573952'),('61560538169085952',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45237010692050944','496138616573952'),('61560538143920128',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45236734832676864','496138616573952'),('61560538135531520',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','43117268627886080','496138616573952'),('61560538097782784',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45235939278065664','496138616573952'),('61560538072616960',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45235787867885568','496138616573952'),('61560538064228352',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','45235621697949696','496138616573952'),('61560538034868225',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','40238597734928384','496138616573952'),('61560538030673921',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','56898976661639168','496138616573952'),('61560538026479617',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','25014528525733888','496138616573952'),('61560538022285313',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16678447719911424','496138616573952'),('61560538018091008',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','15708892205944832','496138616573952'),('61560538009702400',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','15701915807518720','496138616573952'),('61560537946787840',NULL,'2018-10-08 11:11:57',0,NULL,'2018-10-08 11:11:57','16678126574637056','496138616573952'),('61560537896456192',NULL,'2018-10-08 11:11:56',0,NULL,'2018-10-08 11:11:56','15701400130424832','496138616573952'),('61560537850318848',NULL,'2018-10-08 11:11:56',0,NULL,'2018-10-08 11:11:56','5129710648430593','496138616573952'),('61560537766432768',NULL,'2018-10-08 11:11:56',0,NULL,'2018-10-08 11:11:56','5129710648430592','496138616573952');
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_schedule_conf`
--

DROP TABLE IF EXISTS `t_schedule_conf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_schedule_conf`
--

LOCK TABLES `t_schedule_conf` WRITE;
/*!40000 ALTER TABLE `t_schedule_conf` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_schedule_conf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tax_application`
--

DROP TABLE IF EXISTS `t_tax_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tax_application`
--

LOCK TABLES `t_tax_application` WRITE;
/*!40000 ALTER TABLE `t_tax_application` DISABLE KEYS */;
INSERT INTO `t_tax_application` VALUES ('729e73d35ab5415fb0eae00b5b51947d','1','三人行软件公司','123123123','CHN','中国','eccdcaaf6fd04999bb54e140766c156e','zhangsan','这是一个备注',0,'CNY','123123123123',1,NULL,NULL,'2018-11-12 00:34:51',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_tax_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_tax_application_detail`
--

DROP TABLE IF EXISTS `t_tax_application_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_tax_application_detail` (
  `id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tax_period` date DEFAULT NULL COMMENT '纳税所属期',
  `tax_dict` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '税种',
  `payable_tax` decimal(10,0) DEFAULT NULL COMMENT '应交税额',
  `late_fee_payable` decimal(10,0) DEFAULT NULL COMMENT '应缴滞纳金',
  `appl_tax_payment` decimal(10,0) DEFAULT NULL COMMENT '申请缴纳税款',
  `deadline` datetime DEFAULT NULL COMMENT '缴款截止日期',
  `tax_paid` decimal(10,0) DEFAULT NULL COMMENT '实缴税额',
  `overdue_payment` decimal(10,0) DEFAULT NULL COMMENT '实缴滞纳金',
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_tax_application_detail`
--

LOCK TABLES `t_tax_application_detail` WRITE;
/*!40000 ALTER TABLE `t_tax_application_detail` DISABLE KEYS */;
INSERT INTO `t_tax_application_detail` VALUES ('a95c58bb023f4385a9ef5812e895ab66','2018-11-01','Stamp tax',12,0,10,'2018-11-08 18:00:00',12,0,'2018-11-07 18:00:00','子备注1',NULL,'729e73d35ab5415fb0eae00b5b51947d','1212','c://dd/aa/adf.txt',NULL,'1212','c://dfdf//dfdf/aaa.doc',1,'',NULL),('f4494a63e00e4f3585f8df80acddb309','2018-11-01','Resources tax',12,0,10,'2018-11-08 18:00:00',12,0,'2018-11-07 18:00:00','子备注1',NULL,'729e73d35ab5415fb0eae00b5b51947d','1212','c://dd/aa/adf.txt',NULL,'1212','c://dfdf//dfdf/aaa.doc',1,'',NULL);
/*!40000 ALTER TABLE `t_tax_application_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('633f7dbdf41341ccbf410c4d2cb19988',0,'test','王麻子','123456','IEJ5','2898911401@qq.com','003','17792073879',NULL,NULL,1,NULL,NULL,NULL,1,NULL,NULL),('682265633886208',1,'admin','超级管理员','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy','','289911401@qq.com','002','17792073879','1','1',0,NULL,NULL,'2018-10-23 17:37:55',1,NULL,NULL),('eccdcaaf6fd04999bb54e140766c156e',0,'zhangsan','张三','$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy','',NULL,'001',NULL,'0','0',0,NULL,NULL,'2018-09-23 17:37:12',1,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_company`
--

DROP TABLE IF EXISTS `t_user_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_company`
--

LOCK TABLES `t_user_company` WRITE;
/*!40000 ALTER TABLE `t_user_company` DISABLE KEYS */;
INSERT INTO `t_user_company` VALUES ('1','682265633886208','张三','1','三人行','admin','2018-11-02 16:22:12',NULL,NULL,NULL),('2','682265633886208','张三','2','四人组','admin','2018-11-02 16:26:44',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_department`
--

DROP TABLE IF EXISTS `t_user_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_department`
--

LOCK TABLES `t_user_department` WRITE;
/*!40000 ALTER TABLE `t_user_department` DISABLE KEYS */;
INSERT INTO `t_user_department` VALUES ('1','682265633886208','4',NULL,NULL,NULL,NULL,NULL,NULL),('2','682265633886208','5',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES ('1','496138616573952','682265633886208','2018-11-01 22:06:58','123',0,NULL,0),('3b4ef1b76dc3480bafd0136ec53ed76e','496138616573952','633f7dbdf41341ccbf410c4d2cb19988',NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax_config`
--

DROP TABLE IF EXISTS `tax_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_config`
--

LOCK TABLES `tax_config` WRITE;
/*!40000 ALTER TABLE `tax_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tax_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-15 23:10:35
