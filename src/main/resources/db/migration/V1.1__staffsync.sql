-- MySQL dump 10.13  Distrib 5.7.36, for Win64 (x86_64)
--
-- Host: localhost    Database: staffsync
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1.0','staffsync','SQL','V1.0__staffsync.sql',-1261064842,'root','2023-11-04 11:54:21',1662,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_permission`
--

DROP TABLE IF EXISTS `ss_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_permission` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `permission_id` varchar(100) NOT NULL COMMENT '权限id',
  `permission_name` varchar(100) NOT NULL COMMENT '权限描述',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `permission_un` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_permission`
--

LOCK TABLES `ss_permission` WRITE;
/*!40000 ALTER TABLE `ss_permission` DISABLE KEYS */;
INSERT INTO `ss_permission` VALUES (1,'*','全部权限','0'),(2,'info.update','个人信息修改','0');
/*!40000 ALTER TABLE `ss_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_role`
--

DROP TABLE IF EXISTS `ss_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_role` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `role_id` varchar(100) NOT NULL COMMENT '角色id',
  `role_name` varchar(100) NOT NULL COMMENT '角色描述',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `role_un` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_role`
--

LOCK TABLES `ss_role` WRITE;
/*!40000 ALTER TABLE `ss_role` DISABLE KEYS */;
INSERT INTO `ss_role` VALUES (1,'*','超级管理员','0'),(2,'engineer','运维工程师','0'),(3,'default','基础用户组','0');
/*!40000 ALTER TABLE `ss_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_role_permission_relevance`
--

DROP TABLE IF EXISTS `ss_role_permission_relevance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_role_permission_relevance` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `role_id` varchar(100) NOT NULL COMMENT '角色id',
  `permission_id` varchar(100) NOT NULL COMMENT '权限id',
  `is_delete` varchar(1) DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限关联关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_role_permission_relevance`
--

LOCK TABLES `ss_role_permission_relevance` WRITE;
/*!40000 ALTER TABLE `ss_role_permission_relevance` DISABLE KEYS */;
INSERT INTO `ss_role_permission_relevance` VALUES (1,'*','*','0'),(2,'default','info.update','0'),(3,'engineer','*','0');
/*!40000 ALTER TABLE `ss_role_permission_relevance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_user`
--

DROP TABLE IF EXISTS `ss_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_user` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `user_un` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_user`
--

LOCK TABLES `ss_user` WRITE;
/*!40000 ALTER TABLE `ss_user` DISABLE KEYS */;
INSERT INTO `ss_user` VALUES (1,'admin','admin','0');
/*!40000 ALTER TABLE `ss_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_user_role_relevance`
--

DROP TABLE IF EXISTS `ss_user_role_relevance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_user_role_relevance` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `role_id` varchar(100) NOT NULL COMMENT '角色id',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户-角色关联关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_user_role_relevance`
--

LOCK TABLES `ss_user_role_relevance` WRITE;
/*!40000 ALTER TABLE `ss_user_role_relevance` DISABLE KEYS */;
INSERT INTO `ss_user_role_relevance` VALUES (1,'admin','*','0'),(2,'engineer','default','0');
/*!40000 ALTER TABLE `ss_user_role_relevance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_userinfo`
--

DROP TABLE IF EXISTS `ss_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_userinfo` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `name` varchar(100) NOT NULL DEFAULT 'guest' COMMENT '姓名',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `ss_userInfo_un` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户个人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_userinfo`
--

LOCK TABLES `ss_userinfo` WRITE;
/*!40000 ALTER TABLE `ss_userinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ss_userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'staffsync'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-06 13:28:00
