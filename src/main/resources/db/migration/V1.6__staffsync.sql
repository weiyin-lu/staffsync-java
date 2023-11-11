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
INSERT INTO `flyway_schema_history` VALUES (1,'1.0','staffsync','SQL','V1.0__staffsync.sql',-1261064842,'root','2023-11-04 11:54:21',1662,1),(2,'1.1','staffsync','SQL','V1.1__staffsync.sql',855494954,'root','2023-11-06 05:35:40',3076,1),(3,'1.2','staffsync','SQL','V1.2__staffsync.sql',-648341155,'root','2023-11-07 03:01:47',2478,1),(4,'1.3','staffsync','SQL','V1.3__staffsync.sql',1371679838,'root','2023-11-07 09:26:15',3460,1),(5,'1.4','staffsync','SQL','V1.4__staffsync.sql',1208860855,'root','2023-11-07 15:09:52',3186,1),(6,'1.5','staffsync','SQL','V1.5__staffsync.sql',99761580,'root','2023-11-10 09:58:56',1042,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_department`
--

DROP TABLE IF EXISTS `ss_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_department` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `dept_code` varchar(100) NOT NULL COMMENT '部门编码',
  `dept_name` varchar(100) NOT NULL COMMENT '部门名称',
  `superior` varchar(100) DEFAULT NULL COMMENT '上级部门',
  `is_delete` varchar(100) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `ss_department_un` (`dept_code`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_department`
--

LOCK TABLES `ss_department` WRITE;
/*!40000 ALTER TABLE `ss_department` DISABLE KEYS */;
INSERT INTO `ss_department` VALUES (1,'ZCB','总裁办',NULL,'0'),(2,'XSZX','销售中心','ZCB','0'),(3,'YWZX','运维中心','ZCB','0'),(4,'YFZX','研发中心','ZCB','0'),(5,'XZZX','行政中心','ZCB','0'),(6,'XS1','销售一部','XSZX','0'),(7,'XS2','销售二部','XSZX','0'),(8,'YW1','运维一部','YWZX','0'),(9,'YW2','运维二部','YWZX','0'),(10,'YFJC','基础研发部','YFZX','0'),(11,'YF1','研发一部','YFZX','0'),(12,'CPWH','产品维护部','YFZX','0'),(13,'RSXZ','人事行政部','XZZX','0'),(14,'ZCGL','资产管理部','XZZX','0'),(15,'CWB','财务部','ZCB','0'),(16,'YF3','研发三部','YFZX','0'),(17,'YW3','运维3部','YWZX','0'),(21,'GGSWB','公关事务部','XZZX','0');
/*!40000 ALTER TABLE `ss_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_menu`
--

DROP TABLE IF EXISTS `ss_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_menu` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `menu_id` varchar(100) NOT NULL COMMENT '菜单id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component_path` varchar(100) NOT NULL COMMENT '组件路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '菜单描述',
  `is_delete` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `ss_menu_un` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_menu`
--

LOCK TABLES `ss_menu` WRITE;
/*!40000 ALTER TABLE `ss_menu` DISABLE KEYS */;
INSERT INTO `ss_menu` VALUES (1,'all','/index/**','*.vue','所有菜单权限','0'),(2,'system-deptConfig','/index/system-deptConfig','DeptConfig.vue','系统配置管理-部门信息配置','0'),(3,'system-roleConfig','/index/system-roleConfig','RoleConfig.vue','系统配置管理-角色信息配置','0'),(4,'test','test','test','test','1');
/*!40000 ALTER TABLE `ss_menu` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_permission`
--

LOCK TABLES `ss_permission` WRITE;
/*!40000 ALTER TABLE `ss_permission` DISABLE KEYS */;
INSERT INTO `ss_permission` VALUES (1,'*','全部权限','0'),(2,'admin.role.add','[系统]角色管理-添加','0'),(3,'admin.role.remove','[系统]角色管理-删除','0'),(4,'admin.relevance.permission.add','[系统]为角色添加权限','0'),(5,'admin.relevance.permission.remove','[系统]为角色移除权限','0'),(6,'role.add','为用户添加角色','0'),(7,'role.remove','为用户移除角色','0'),(8,'info.update','修改用户信息','0'),(9,'admin.dept.add','[系统]部门管理-添加','0'),(10,'admin.dept.remove','[系统]部门管理-删除','0'),(11,'admin.dept.update','[系统]部门管理-修改','0'),(12,'admin.role.update','[系统]角色管理-修改','0'),(13,'admin.dept.*','[系统]部门管理-全部','0'),(14,'admin.dept.show','[系统]部门管理-查看','0'),(15,'admin.role.show','[系统]角色管理-查看','0'),(16,'admin.role.*','[系统]角色管理-全部','0'),(17,'admin.permission.*','[系统]权限管理-全部','0'),(18,'admin.permission.add','[系统]权限管理-查看','0'),(19,'admin.permission.show','[系统]权限管理-添加','0'),(20,'admin.relevance.menu.add','[系统]为角色添加菜单','0'),(21,'admin.relevance.menu.remove','[系统]为角色移除菜单','0'),(22,'admin.menu.add','[系统]菜单管理-添加','0'),(23,'admin.menu.show','[系统]菜单管理-查看','0'),(24,'admin.menu.update','[系统]菜单管理-修改','0'),(25,'admin.menu.remove','[系统]菜单管理-删除','0'),(26,'admin.menu.*','[系统]菜单管理-全部','0'),(27,'admin.relevance.menu.show','[系统]查看角色拥有的菜单','0'),(29,'admin.relevance.permission.show','[系统]查看角色拥有的权限','0');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_role`
--

LOCK TABLES `ss_role` WRITE;
/*!40000 ALTER TABLE `ss_role` DISABLE KEYS */;
INSERT INTO `ss_role` VALUES (1,'administrator','超级管理员','0'),(2,'engineer','系统运维工程师','0'),(3,'employee','正式人员','0'),(4,'CenterManager','大区经理','0'),(5,'test','test','1');
/*!40000 ALTER TABLE `ss_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_role_menu_relevance`
--

DROP TABLE IF EXISTS `ss_role_menu_relevance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_role_menu_relevance` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `role_id` varchar(100) NOT NULL COMMENT '角色id',
  `menu_id` varchar(100) NOT NULL COMMENT '菜单id',
  `is_delete` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`row_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-菜单关联关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_role_menu_relevance`
--

LOCK TABLES `ss_role_menu_relevance` WRITE;
/*!40000 ALTER TABLE `ss_role_menu_relevance` DISABLE KEYS */;
INSERT INTO `ss_role_menu_relevance` VALUES (1,'administrator','all','0');
/*!40000 ALTER TABLE `ss_role_menu_relevance` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色-权限关联关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_role_permission_relevance`
--

LOCK TABLES `ss_role_permission_relevance` WRITE;
/*!40000 ALTER TABLE `ss_role_permission_relevance` DISABLE KEYS */;
INSERT INTO `ss_role_permission_relevance` VALUES (1,'administrator','*','0'),(2,'employee','info.update','0'),(3,'engineer','admin.role.*','0'),(4,'engineer','admin.dept.*','0');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_user`
--

LOCK TABLES `ss_user` WRITE;
/*!40000 ALTER TABLE `ss_user` DISABLE KEYS */;
INSERT INTO `ss_user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','0'),(8,'test1','e10adc3949ba59abbe56e057f20f883e','0'),(9,'test2','e10adc3949ba59abbe56e057f20f883e','0'),(11,'test3','e10adc3949ba59abbe56e057f20f883e','0'),(16,'test4','e10adc3949ba59abbe56e057f20f883e','0');
/*!40000 ALTER TABLE `ss_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ss_user_info`
--

DROP TABLE IF EXISTS `ss_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ss_user_info` (
  `row_key` int NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `user_id` varchar(100) NOT NULL COMMENT '用户id',
  `name` varchar(100) NOT NULL DEFAULT 'guest' COMMENT '姓名',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `is_delete` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `department` varchar(100) DEFAULT NULL COMMENT '部门',
  `post` varchar(100) DEFAULT NULL COMMENT '岗位',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`row_key`),
  UNIQUE KEY `ss_userInfo_un` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户个人信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_user_info`
--

LOCK TABLES `ss_user_info` WRITE;
/*!40000 ALTER TABLE `ss_user_info` DISABLE KEYS */;
INSERT INTO `ss_user_info` VALUES (1,'admin','admin','12345678911','0',NULL,NULL,'weiyin2002@outlook.com'),(2,'test1','guest',NULL,'0',NULL,NULL,NULL),(3,'test2','guest',NULL,'0',NULL,NULL,NULL),(4,'test3','guest',NULL,'0',NULL,NULL,NULL),(5,'test4','guest',NULL,'0',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ss_user_info` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户-角色关联关系';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ss_user_role_relevance`
--

LOCK TABLES `ss_user_role_relevance` WRITE;
/*!40000 ALTER TABLE `ss_user_role_relevance` DISABLE KEYS */;
INSERT INTO `ss_user_role_relevance` VALUES (1,'admin','administrator','0'),(2,'test1','employee','0'),(3,'test1','engineer','0'),(4,'test2','employee','0');
/*!40000 ALTER TABLE `ss_user_role_relevance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'staffsync'
--
/*!50003 DROP FUNCTION IF EXISTS `get_dept_list` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_dept_list`(in_id varchar(100)) RETURNS varchar(1000) CHARSET utf8mb4
    READS SQL DATA
begin
   -- 定义变量
    declare ids varchar(1000) default '';
    declare tempids varchar(1000);
    set tempids = in_id;
   -- 如果tempids无法再获取到值，就说明递归已经结束
    while tempids is not null do
          -- 每次循环把上一次的查询结果拼接到ids返回值中
           set ids = CONCAT_WS(',',ids,tempids);
          -- 拼接dept_code，条件:记录的superior字段包含在本次查询的tempids中（find_in_set返回不为0），并将结果覆盖到tempids中
           select GROUP_CONCAT(d.dept_code) into tempids from ss_department d where FIND_IN_SET(d.superior ,tempids)>0;
        end while;
   -- 返回逗号分割的dept_code字符串，在外侧传入字符串查询
    return ids;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-12  2:35:43
