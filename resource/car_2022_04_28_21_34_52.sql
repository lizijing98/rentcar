-- MySQL dump 10.13  Distrib 8.0.26, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: car
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car_assess`
--

DROP TABLE IF EXISTS `car_assess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_assess` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评价 ID',
  `order_number` varchar(64) DEFAULT NULL COMMENT '订单号',
  `car_info_id` int NOT NULL COMMENT '车辆 ID',
  `customer_id` int NOT NULL COMMENT '客户 ID',
  `remark` varchar(500) NOT NULL COMMENT '详情',
  `state` int DEFAULT NULL COMMENT '评价状态',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '评价 ID 主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_assess`
--

LOCK TABLES `car_assess` WRITE;
/*!40000 ALTER TABLE `car_assess` DISABLE KEYS */;
/*!40000 ALTER TABLE `car_assess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_info`
--

DROP TABLE IF EXISTS `car_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '车辆 ID',
  `plate_number` varchar(20) DEFAULT NULL COMMENT '车牌',
  `car_type` int DEFAULT NULL COMMENT '车辆类型',
  `brand` varchar(255) NOT NULL COMMENT '品牌',
  `manufacturer` varchar(255) NOT NULL COMMENT '制造商',
  `produced_date` date DEFAULT NULL COMMENT '生产日期',
  `car_color` varchar(40) DEFAULT NULL COMMENT '车辆颜色',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `money` double NOT NULL DEFAULT '0' COMMENT '租金',
  `cash_pledge` double NOT NULL COMMENT '押金',
  `remark` varchar(510) DEFAULT NULL COMMENT '描述',
  `status` varchar(50) NOT NULL DEFAULT '未出租' COMMENT '车辆状态',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '车辆 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车辆表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_info`
--

LOCK TABLES `car_info` WRITE;
/*!40000 ALTER TABLE `car_info` DISABLE KEYS */;
INSERT INTO `car_info` VALUES (1,'苏AT00001',41,'宝马 X6','宝马','2022-01-01','红色','baoma.jpg',500,3000,NULL,'未出租',7,'2022-04-15 08:33:04',NULL,1,10086,0),(11,'苏AT00002',1,'迈腾','上汽大众','2021-12-23','黑色','maiteng.jpg',280,1000,NULL,'未出租',13,'2022-04-15 08:33:04',NULL,1,10086,0),(21,'苏AT00003',11,'奥迪 A6L','奥迪','2021-12-31','黑色','aodi.jpg',400,3000,NULL,'未出租',2,'2022-04-15 08:33:04',NULL,1,10086,0),(31,'苏AT00004',41,'奔驰 GLC','奔驰','2021-12-31','黑色','benchi.jpg',500,3000,NULL,'未出租',5,'2022-04-15 08:33:04',NULL,1,1,0),(41,'苏AT00005',11,'小鹏 P1','小鹏汽车','2021-12-27','白色','2022-4-16-1650099665831xiaopeng_P1.jpg',260,2000,'小鹏','未出租',8,'2022-04-16 09:04:07',NULL,21,1,0),(51,'苏AT00006',11,'Tesla  Model3','特斯拉上海','2022-01-30','黑色','2022-4-16-1650101568833tesla_Model3.jpg',290,2000,'','未出租',1,'2022-04-16 09:33:52',NULL,1,1,0),(61,'苏ATP0001',51,'保时捷 911','保时捷','2020-04-29','灰色','2022-4-16-1650102426460porsche_911.jpg',1000,10000,'','未出租',0,'2022-04-16 09:47:46',NULL,1,NULL,0);
/*!40000 ALTER TABLE `car_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_order`
--

DROP TABLE IF EXISTS `car_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_order` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单 ID',
  `order_number` varchar(64) DEFAULT NULL COMMENT '订单号',
  `car_info_id` int NOT NULL COMMENT '车辆 ID',
  `customer_id` int NOT NULL COMMENT '客户 ID',
  `customer_id_num` varchar(20) NOT NULL DEFAULT '41010120220101123X' COMMENT '客户身份证号码',
  `start_date` timestamp NULL DEFAULT NULL COMMENT '订单开始时间',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '订单结束时间',
  `out_date` timestamp NULL DEFAULT NULL COMMENT '车辆出库时间',
  `in_date` timestamp NULL DEFAULT NULL COMMENT '车辆入库时间',
  `money` decimal(10,2) DEFAULT NULL COMMENT '汽车租金',
  `cash_pledge` decimal(10,0) DEFAULT NULL COMMENT '押金',
  `fine` decimal(10,2) DEFAULT '0.00' COMMENT '罚金',
  `penalty` decimal(10,2) DEFAULT '0.00' COMMENT '滞纳金',
  `total` decimal(10,2) DEFAULT '0.00' COMMENT '总金额',
  `tenancy_term` decimal(10,2) DEFAULT '0.00' COMMENT '租期',
  `feedback` varchar(400) DEFAULT NULL COMMENT '处理意见',
  `state` int DEFAULT NULL COMMENT '订单状态',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '订单 ID 主键',
  UNIQUE KEY `uni_ord_num` (`order_number`) USING BTREE COMMENT '订单号唯一键'
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_order`
--

LOCK TABLES `car_order` WRITE;
/*!40000 ALTER TABLE `car_order` DISABLE KEYS */;
INSERT INTO `car_order` VALUES (11,'2022042717383477096252964220459',41,1,'410101200210100001','2022-04-27 09:38:14','2022-04-27 09:59:29','2022-04-27 09:40:35','2022-04-27 09:59:29',260.00,2000,200.00,0.00,460.00,1.00,'同意还车',6,4,'2022-04-27 09:38:34',NULL,10086,10086,0),(21,'2022042718041176794639894587884',1,1,'410101200101011234','2022-04-27 10:01:38','2022-04-28 10:04:00',NULL,NULL,500.00,3000,0.00,0.00,500.00,1.00,'拒绝借车',3,1,'2022-04-27 10:04:11',NULL,10086,10086,0),(31,'2022042718052484394127996840054',1,1,'410101200103020000','2022-04-27 10:05:05','2022-04-27 10:12:19','2022-04-27 10:05:41','2022-04-27 10:12:19',500.00,3000,200.00,0.00,700.00,1.00,'处理完成',6,3,'2022-04-27 10:05:24',NULL,10086,10086,0),(41,'2022042720285878191910644075410',31,1,'410101200002120000','2022-04-27 12:28:34','2022-04-27 12:31:47','2022-04-27 12:29:42','2022-04-27 12:31:47',500.00,3000,200.00,0.00,700.00,1.00,'同意还车',6,4,'2022-04-27 12:28:58',NULL,10086,1,0);
/*!40000 ALTER TABLE `car_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_type`
--

DROP TABLE IF EXISTS `car_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '车型 ID',
  `name` varchar(255) NOT NULL COMMENT '类型名称',
  `remark` varchar(500) NOT NULL COMMENT '详情',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '车型 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='车型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_type`
--

LOCK TABLES `car_type` WRITE;
/*!40000 ALTER TABLE `car_type` DISABLE KEYS */;
INSERT INTO `car_type` VALUES (1,'经济型轿车','一种发动机排量较小、车身尺寸较小、油耗相对较经济、价格也相应较低的一类轿车。',0,'2022-04-15 08:33:04',NULL,1,10086,0),(11,'舒适型轿车','配置就在基本型和豪华型配置之间，要比豪华型差，但是比基本型好。',0,'2022-04-15 08:33:04',NULL,1,NULL,0),(21,'豪华轿车','豪华型则是比较全面多功能的，多了些附加值、舒适性和娱乐性等的部件。倒车雷达，天窗，后视镜除霜，安全气囊，大屏幕液晶，多碟cd/dvd等都会有。',0,'2022-04-15 08:33:04',NULL,1,10086,0),(31,'MPV','商务车即多用途汽车。集轿车、旅行车和厢式货车的功能于一身，车内每个座椅都可调整，并有多种组合的方式',0,'2022-04-15 08:33:04',NULL,1,NULL,0),(41,'SUV','SUV是指运动型多用途汽车，不同于可在崎岖地面使用的ORV越野车,即城郊多用途汽车，是一种拥有旅行车般的空间机能，配以货卡车的越野能力的车型。',0,'2022-04-15 08:33:04',NULL,1,NULL,0),(51,'轿跑车','轿跑车是指双门轿车，或者外观类似跑车的外型，但拥有轿车乘坐的舒适性。轿跑车亦具备一定程度的性能表现，但不像跑车般严格。',0,'2022-04-16 09:26:11',NULL,1,NULL,0);
/*!40000 ALTER TABLE `car_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carousel`
--

DROP TABLE IF EXISTS `carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carousel` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '轮播图 ID',
  `image_url` varchar(200) DEFAULT NULL COMMENT '图片 URL',
  `remark` varchar(200) DEFAULT NULL COMMENT '注释',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '轮播图 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='轮播图表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carousel`
--

LOCK TABLES `carousel` WRITE;
/*!40000 ALTER TABLE `carousel` DISABLE KEYS */;
INSERT INTO `carousel` VALUES (1,'aodi.jpg','奥迪',0),(11,'baoma.jpg','宝马',0),(21,'benchi.jpg','奔驰',0);
/*!40000 ALTER TABLE `carousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '检修单 ID',
  `order_id` int NOT NULL COMMENT '订单 ID',
  `order_number` varchar(64) NOT NULL COMMENT '订单编号',
  `question` varchar(500) NOT NULL DEFAULT '用户未发现异常' COMMENT '问题',
  `money` decimal(10,2) DEFAULT '0.00' COMMENT '赔付金额',
  `remark` varchar(500) DEFAULT '系统默认评价' COMMENT '详细经过',
  `state` int DEFAULT '0' COMMENT '检修单状态',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '检修单 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检修单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
INSERT INTO `check` VALUES (1,11,'2022042717383477096252964220459','闯红灯',200.00,'',1,0,'2022-04-27 09:43:08',NULL,10086,NULL,0),(11,31,'2022042718052484394127996840054','闯红灯',200.00,'用户提交:闯红灯',1,0,'2022-04-27 10:09:17',NULL,10086,NULL,0),(21,41,'2022042720285878191910644075410','闯红灯',200.00,'用户提交:闯红灯',1,0,'2022-04-27 12:31:14',NULL,10086,NULL,0);
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `check_view`
--

DROP TABLE IF EXISTS `check_view`;
/*!50001 DROP VIEW IF EXISTS `check_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `check_view` AS SELECT 
 1 AS `id`,
 1 AS `order_id`,
 1 AS `order_number`,
 1 AS `question`,
 1 AS `money`,
 1 AS `remark`,
 1 AS `state`,
 1 AS `fversion`,
 1 AS `create_time`,
 1 AS `modify_time`,
 1 AS `creator_id`,
 1 AS `modify_id`,
 1 AS `deleted`,
 1 AS `car_plate_number`,
 1 AS `customer_id`,
 1 AS `customer_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '顾客 ID',
  `username` varchar(255) NOT NULL COMMENT '顾客名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `activate` varchar(200) DEFAULT NULL COMMENT '激活标记',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `freeze` decimal(10,2) DEFAULT '0.00' COMMENT '冻结金',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `address` varchar(255) DEFAULT NULL COMMENT '住址',
  `profession` varchar(255) DEFAULT NULL COMMENT '职业',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '顾客 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='顾客表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'customer','123456','on','customer@car.com','17612341111','男',4157.00,0.00,NULL,NULL,NULL,NULL,16,'2022-04-15 08:33:05',NULL,0,10086,0),(11,'test1','123456','on','test1@car.com','17612341112','男',7740.00,0.00,'','','',NULL,4,'2022-04-15 08:33:05',NULL,0,10086,0),(21,'test2','123456','on','test2@car.com','17612341113','男',1000.00,0.00,NULL,NULL,NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'test3','123456','on','test3@car.com','17612341114','男',3000.00,0.00,'','','','',0,'2022-04-15 08:33:05',NULL,0,1,0);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_log`
--

DROP TABLE IF EXISTS `login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_log` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '日志 ID',
  `user` int NOT NULL COMMENT '登录人',
  `login_time` timestamp NOT NULL COMMENT '登录时间',
  `login_ip` varchar(32) NOT NULL COMMENT '登录 IP',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '日志 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=1291 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
INSERT INTO `login_log` VALUES (11,1,'2022-04-15 00:49:54','127.0.0.1',0,'2022-04-15 08:49:53',NULL,10086,NULL,0),(21,1,'2022-04-15 06:32:22','127.0.0.1',0,'2022-04-15 14:32:21',NULL,10086,NULL,0),(31,1,'2022-04-15 07:17:28','127.0.0.1',0,'2022-04-15 15:17:28',NULL,10086,NULL,0),(41,1,'2022-04-16 00:35:50','127.0.0.1',0,'2022-04-16 08:35:50',NULL,10086,NULL,0),(51,1,'2022-04-16 00:37:15','127.0.0.1',0,'2022-04-16 08:37:15',NULL,10086,NULL,0),(61,1,'2022-04-16 00:38:15','127.0.0.1',0,'2022-04-16 08:38:15',NULL,10086,NULL,0),(71,1,'2022-04-16 00:53:33','127.0.0.1',0,'2022-04-16 08:53:32',NULL,10086,NULL,0),(81,11,'2022-04-16 00:53:59','127.0.0.1',0,'2022-04-16 08:53:59',NULL,10086,NULL,0),(91,21,'2022-04-16 00:58:19','127.0.0.1',0,'2022-04-16 08:58:18',NULL,10086,NULL,0),(101,1,'2022-04-16 01:12:12','127.0.0.1',0,'2022-04-16 09:12:11',NULL,10086,NULL,0),(111,1,'2022-04-16 01:48:36','127.0.0.1',0,'2022-04-16 09:48:36',NULL,10086,NULL,0),(121,1,'2022-04-16 01:49:11','127.0.0.1',0,'2022-04-16 09:49:10',NULL,10086,NULL,0),(131,1,'2022-04-16 02:16:28','127.0.0.1',0,'2022-04-16 10:16:28',NULL,10086,NULL,0),(141,1,'2022-04-16 02:25:07','127.0.0.1',0,'2022-04-16 10:25:07',NULL,10086,NULL,0),(151,1,'2022-04-16 03:44:11','172.17.0.1',0,'2022-04-16 11:44:11',NULL,10086,NULL,0),(161,1,'2022-04-16 03:56:50','127.0.0.1',0,'2022-04-16 11:56:49',NULL,10086,NULL,0),(171,1,'2022-04-16 04:02:58','127.0.0.1',0,'2022-04-16 12:02:57',NULL,10086,NULL,0),(181,1,'2022-04-17 00:58:48','127.0.0.1',0,'2022-04-17 08:58:48',NULL,10086,NULL,0),(191,1,'2022-04-17 01:03:25','127.0.0.1',0,'2022-04-17 09:03:24',NULL,1,NULL,0),(201,1,'2022-04-17 01:04:24','127.0.0.1',0,'2022-04-17 09:04:24',NULL,1,NULL,0),(211,1,'2022-04-17 01:04:50','127.0.0.1',0,'2022-04-17 09:04:49',NULL,10086,NULL,0),(221,1,'2022-04-17 01:33:53','127.0.0.1',0,'2022-04-17 09:33:53',NULL,10086,NULL,0),(231,1,'2022-04-17 01:53:18','127.0.0.1',0,'2022-04-17 09:53:18',NULL,10086,NULL,0),(241,1,'2022-04-17 02:00:22','127.0.0.1',0,'2022-04-17 10:00:21',NULL,10086,NULL,0),(251,1,'2022-04-17 03:06:40','127.0.0.1',0,'2022-04-17 11:06:40',NULL,10086,NULL,0),(261,1,'2022-04-17 03:10:39','127.0.0.1',0,'2022-04-17 11:10:39',NULL,10086,NULL,0),(271,1,'2022-04-17 03:47:01','127.0.0.1',0,'2022-04-17 11:47:01',NULL,10086,NULL,0),(281,1,'2022-04-17 05:56:59','127.0.0.1',0,'2022-04-17 13:56:59',NULL,10086,NULL,0),(291,1,'2022-04-17 07:12:06','127.0.0.1',0,'2022-04-17 15:12:06',NULL,10086,NULL,0),(301,1,'2022-04-17 09:15:27','127.0.0.1',0,'2022-04-17 17:15:27',NULL,10086,NULL,0),(311,1,'2022-04-17 11:01:11','127.0.0.1',0,'2022-04-17 19:01:11',NULL,10086,NULL,0),(321,1,'2022-04-17 11:23:30','127.0.0.1',0,'2022-04-17 19:23:29',NULL,10086,NULL,0),(331,1,'2022-04-17 11:24:25','127.0.0.1',0,'2022-04-17 19:24:25',NULL,10086,NULL,0),(341,1,'2022-04-17 18:32:57','127.0.0.1',0,'2022-04-18 02:32:57',NULL,10086,NULL,0),(351,1,'2022-04-17 19:09:23','127.0.0.1',0,'2022-04-18 03:09:23',NULL,10086,NULL,0),(361,1,'2022-04-17 19:20:44','127.0.0.1',0,'2022-04-18 03:20:43',NULL,10086,NULL,0),(371,1,'2022-04-17 21:39:20','127.0.0.1',0,'2022-04-18 05:39:20',NULL,10086,NULL,0),(381,1,'2022-04-17 21:54:31','127.0.0.1',0,'2022-04-18 05:54:30',NULL,10086,NULL,0),(391,1,'2022-04-17 23:30:13','127.0.0.1',0,'2022-04-18 07:30:12',NULL,10086,NULL,0),(401,1,'2022-04-18 01:01:30','127.0.0.1',0,'2022-04-18 09:01:30',NULL,10086,NULL,0),(411,1,'2022-04-18 01:08:22','127.0.0.1',0,'2022-04-18 09:08:22',NULL,10086,NULL,0),(421,1,'2022-04-18 01:31:21','127.0.0.1',0,'2022-04-18 09:31:20',NULL,10086,NULL,0),(431,1,'2022-04-18 01:47:03','127.0.0.1',0,'2022-04-18 09:47:02',NULL,10086,NULL,0),(441,11,'2022-04-18 01:51:54','127.0.0.1',0,'2022-04-18 09:51:54',NULL,10086,NULL,0),(451,11,'2022-04-18 05:00:56','127.0.0.1',0,'2022-04-18 13:00:56',NULL,10086,NULL,0),(461,11,'2022-04-18 09:43:28','127.0.0.1',0,'2022-04-18 17:43:28',NULL,10086,NULL,0),(471,1,'2022-04-18 09:44:19','127.0.0.1',0,'2022-04-18 17:44:18',NULL,10086,NULL,0),(481,1,'2022-04-20 05:30:28','127.0.0.1',0,'2022-04-20 13:30:29',NULL,10086,NULL,0),(491,1,'2022-04-21 22:39:59','127.0.0.1',0,'2022-04-22 06:39:59',NULL,10086,NULL,0),(501,1,'2022-04-22 21:03:22','127.0.0.1',0,'2022-04-23 05:03:22',NULL,10086,NULL,0),(511,11,'2022-04-22 21:04:18','127.0.0.1',0,'2022-04-23 05:04:17',NULL,10086,NULL,0),(521,1,'2022-04-22 21:04:31','127.0.0.1',0,'2022-04-23 05:04:30',NULL,10086,NULL,0),(531,1,'2022-04-22 21:37:48','127.0.0.1',0,'2022-04-23 05:37:48',NULL,10086,NULL,0),(541,1,'2022-04-22 23:12:58','127.0.0.1',0,'2022-04-23 07:12:57',NULL,10086,NULL,0),(551,1,'2022-04-23 00:46:33','127.0.0.1',0,'2022-04-23 08:46:33',NULL,10086,NULL,0),(561,11,'2022-04-23 21:28:36','127.0.0.1',0,'2022-04-24 05:28:35',NULL,10086,NULL,0),(571,11,'2022-04-23 21:44:57','127.0.0.1',0,'2022-04-24 05:44:56',NULL,10086,NULL,0),(581,1,'2022-04-23 22:05:57','127.0.0.1',0,'2022-04-24 06:05:56',NULL,10086,NULL,0),(591,1,'2022-04-23 23:03:12','127.0.0.1',0,'2022-04-24 07:03:11',NULL,10086,NULL,0),(601,1,'2022-04-24 05:52:40','127.0.0.1',0,'2022-04-24 13:52:39',NULL,10086,NULL,0),(611,1,'2022-04-24 15:43:40','127.0.0.1',0,'2022-04-24 15:43:40',NULL,10086,NULL,0),(621,1,'2022-04-24 17:07:11','127.0.0.1',0,'2022-04-24 17:07:11',NULL,10086,NULL,0),(631,21,'2022-04-25 06:40:06','127.0.0.1',0,'2022-04-25 06:40:06',NULL,10086,NULL,0),(641,1,'2022-04-25 06:46:21','127.0.0.1',0,'2022-04-25 06:46:21',NULL,10086,NULL,0),(651,1,'2022-04-25 10:17:37','127.0.0.1',0,'2022-04-25 10:17:37',NULL,10086,NULL,0),(661,1,'2022-04-25 13:36:37','127.0.0.1',0,'2022-04-25 13:36:37',NULL,10086,NULL,0),(671,1,'2022-04-25 14:01:50','127.0.0.1',0,'2022-04-25 14:01:49',NULL,10086,NULL,0),(681,1,'2022-04-25 14:16:50','127.0.0.1',0,'2022-04-25 14:16:49',NULL,10086,NULL,0),(691,1,'2022-04-25 15:12:04','127.0.0.1',0,'2022-04-25 15:12:04',NULL,10086,NULL,0),(701,1,'2022-04-26 06:22:18','127.0.0.1',0,'2022-04-26 06:22:18',NULL,10086,NULL,0),(711,1,'2022-04-26 14:52:13','127.0.0.1',0,'2022-04-26 14:52:13',NULL,10086,NULL,0),(721,1,'2022-04-26 15:40:15','127.0.0.1',0,'2022-04-26 15:40:14',NULL,10086,NULL,0),(731,1,'2022-04-26 18:03:47','127.0.0.1',0,'2022-04-26 18:03:47',NULL,10086,NULL,0),(741,1,'2022-04-26 18:29:22','127.0.0.1',0,'2022-04-26 18:29:22',NULL,10086,NULL,0),(751,1,'2022-04-27 06:22:40','127.0.0.1',0,'2022-04-27 06:22:40',NULL,10086,NULL,0),(761,1,'2022-04-27 06:23:05','127.0.0.1',0,'2022-04-27 06:23:04',NULL,10086,NULL,0),(771,1,'2022-04-27 06:31:38','127.0.0.1',0,'2022-04-27 06:31:37',NULL,10086,NULL,0),(781,1,'2022-04-27 06:37:31','127.0.0.1',0,'2022-04-27 06:37:31',NULL,10086,NULL,0),(791,1,'2022-04-27 06:37:31','127.0.0.1',0,'2022-04-27 06:37:31',NULL,10086,NULL,0),(801,1,'2022-04-27 06:37:31','127.0.0.1',0,'2022-04-27 06:37:31',NULL,10086,NULL,0),(811,1,'2022-04-27 07:19:34','127.0.0.1',0,'2022-04-27 07:19:34',NULL,10086,NULL,0),(821,1,'2022-04-27 08:21:01','127.0.0.1',0,'2022-04-27 08:21:01',NULL,10086,NULL,0),(831,1,'2022-04-27 09:17:38','127.0.0.1',0,'2022-04-27 09:17:38',NULL,10086,NULL,0),(841,1,'2022-04-27 09:25:57','127.0.0.1',0,'2022-04-27 09:25:57',NULL,10086,NULL,0),(851,1,'2022-04-27 09:34:33','127.0.0.1',0,'2022-04-27 09:34:32',NULL,10086,NULL,0),(861,1,'2022-04-27 09:38:00','127.0.0.1',0,'2022-04-27 09:38:00',NULL,10086,NULL,0),(871,1,'2022-04-27 10:00:15','127.0.0.1',0,'2022-04-27 10:00:14',NULL,10086,NULL,0),(881,1,'2022-04-27 10:14:45','127.0.0.1',0,'2022-04-27 10:14:45',NULL,10086,NULL,0),(891,1,'2022-04-27 10:17:59','127.0.0.1',0,'2022-04-27 10:17:59',NULL,10086,NULL,0),(901,1,'2022-04-27 10:30:53','127.0.0.1',0,'2022-04-27 10:30:53',NULL,10086,NULL,0),(911,1,'2022-04-27 10:33:34','127.0.0.1',0,'2022-04-27 10:33:33',NULL,10086,NULL,0),(921,1,'2022-04-27 10:35:45','127.0.0.1',0,'2022-04-27 10:35:44',NULL,10086,NULL,0),(931,1,'2022-04-27 10:49:08','127.0.0.1',0,'2022-04-27 10:49:07',NULL,10086,NULL,0),(941,1,'2022-04-27 10:57:36','127.0.0.1',0,'2022-04-27 10:57:35',NULL,10086,NULL,0),(951,1,'2022-04-27 10:59:10','127.0.0.1',0,'2022-04-27 10:59:09',NULL,10086,NULL,0),(961,1,'2022-04-27 11:01:34','127.0.0.1',0,'2022-04-27 11:01:34',NULL,10086,NULL,0),(971,1,'2022-04-27 12:16:34','127.0.0.1',0,'2022-04-27 12:16:33',NULL,10086,NULL,0),(981,1,'2022-04-27 12:24:38','127.0.0.1',0,'2022-04-27 12:24:37',NULL,10086,NULL,0),(991,1,'2022-04-27 12:28:23','127.0.0.1',0,'2022-04-27 12:28:23',NULL,10086,NULL,0),(1001,1,'2022-04-27 12:29:29','127.0.0.1',0,'2022-04-27 12:29:28',NULL,10086,NULL,0),(1011,1,'2022-04-27 12:41:42','127.0.0.1',0,'2022-04-27 12:41:41',NULL,10086,NULL,0),(1021,1,'2022-04-27 12:48:19','127.0.0.1',0,'2022-04-27 12:48:18',NULL,10086,NULL,0),(1031,1,'2022-04-28 07:07:46','127.0.0.1',0,'2022-04-28 07:07:46',NULL,10086,NULL,0),(1041,1,'2022-04-28 07:18:12','127.0.0.1',0,'2022-04-28 07:18:11',NULL,10086,NULL,0),(1051,1,'2022-04-28 07:20:45','127.0.0.1',0,'2022-04-28 07:20:44',NULL,10086,NULL,0),(1061,1,'2022-04-28 07:22:14','127.0.0.1',0,'2022-04-28 07:22:14',NULL,10086,NULL,0),(1071,1,'2022-04-28 07:23:31','127.0.0.1',0,'2022-04-28 07:23:30',NULL,10086,NULL,0),(1081,1,'2022-04-28 07:24:44','127.0.0.1',0,'2022-04-28 07:24:44',NULL,10086,NULL,0),(1091,1,'2022-04-28 07:26:56','127.0.0.1',0,'2022-04-28 07:26:55',NULL,10086,NULL,0),(1101,1,'2022-04-28 07:30:52','127.0.0.1',0,'2022-04-28 07:30:52',NULL,10086,NULL,0),(1111,1,'2022-04-28 07:45:25','127.0.0.1',0,'2022-04-28 07:45:24',NULL,10086,NULL,0),(1121,1,'2022-04-28 07:46:32','127.0.0.1',0,'2022-04-28 07:46:31',NULL,10086,NULL,0),(1131,1,'2022-04-28 07:50:48','127.0.0.1',0,'2022-04-28 07:50:47',NULL,10086,NULL,0),(1141,1,'2022-04-28 08:11:17','127.0.0.1',0,'2022-04-28 08:11:16',NULL,10086,NULL,0),(1151,1,'2022-04-28 09:08:36','127.0.0.1',0,'2022-04-28 09:08:36',NULL,10086,NULL,0),(1161,1,'2022-04-28 09:18:23','127.0.0.1',0,'2022-04-28 09:18:22',NULL,10086,NULL,0),(1171,1,'2022-04-28 09:19:01','127.0.0.1',0,'2022-04-28 09:19:00',NULL,10086,NULL,0),(1181,1,'2022-04-28 09:28:09','127.0.0.1',0,'2022-04-28 09:28:09',NULL,10086,NULL,0),(1191,1,'2022-04-28 09:29:50','127.0.0.1',0,'2022-04-28 09:29:50',NULL,10086,NULL,0),(1201,1,'2022-04-28 09:32:25','127.0.0.1',0,'2022-04-28 09:32:25',NULL,10086,NULL,0),(1211,1,'2022-04-28 09:37:01','127.0.0.1',0,'2022-04-28 09:37:00',NULL,10086,NULL,0),(1221,1,'2022-04-28 09:39:33','127.0.0.1',0,'2022-04-28 09:39:32',NULL,10086,NULL,0),(1231,1,'2022-04-28 09:40:33','127.0.0.1',0,'2022-04-28 09:40:33',NULL,10086,NULL,0),(1241,1,'2022-04-28 09:44:06','127.0.0.1',0,'2022-04-28 09:44:06',NULL,10086,NULL,0),(1251,1,'2022-04-28 09:47:04','127.0.0.1',0,'2022-04-28 09:47:03',NULL,10086,NULL,0),(1261,1,'2022-04-28 09:49:27','127.0.0.1',0,'2022-04-28 09:49:27',NULL,10086,NULL,0),(1271,1,'2022-04-28 09:50:18','127.0.0.1',0,'2022-04-28 09:50:17',NULL,10086,NULL,0),(1281,1,'2022-04-28 09:50:45','127.0.0.1',0,'2022-04-28 09:50:45',NULL,10086,NULL,0);
/*!40000 ALTER TABLE `login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名',
  `menu_level` int NOT NULL COMMENT '菜单等级',
  `menu_parent_id` int NOT NULL COMMENT '父菜单',
  `menu_url` varchar(255) NOT NULL COMMENT 'url',
  `menu_activate` varchar(255) NOT NULL COMMENT '是否激活',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '菜单 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'员工管理',1,0,'无','on','无',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(11,'权限管理',1,0,'无','on','这个是权限管理的根目录',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(21,'菜单管理',2,11,'menuView','on','菜单管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'角色管理',2,11,'roleView','on','角色管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(41,'员工信息管理',2,1,'userView','on','用户管理',0,'2022-04-15 08:33:05',NULL,0,1,0),(51,'我的信息',1,0,'userInfo','on','用户信息',0,'2022-04-15 08:33:05',NULL,0,1,0),(61,'汽车管理',1,0,'无','on','汽车管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(71,'汽车信息管理',2,61,'carInfo','on','汽车信息',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(81,'汽车类型管理',2,61,'carType','on','汽车类型信息',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(91,'后台登录日志',1,0,'loginLog','on','后台登录日志',0,'2022-04-15 08:33:05',NULL,0,1,0),(101,'客户管理',1,0,'customer','on','客户信息管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(111,'公告管理',1,0,'notice','on','公告管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(121,'订单管理',1,0,'order','on','订单信息管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(131,'轮播管理',1,0,'carousel','on','轮播管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(141,'检查单管理',1,0,'inspect','on','检查单管理',0,'2022-04-17 09:57:22',NULL,1,1,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_role`
--

DROP TABLE IF EXISTS `menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_role` (
  `menu_id` int NOT NULL COMMENT '菜单 ID',
  `role_id` int NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE COMMENT '菜单角色表主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单_角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_role`
--

LOCK TABLES `menu_role` WRITE;
/*!40000 ALTER TABLE `menu_role` DISABLE KEYS */;
INSERT INTO `menu_role` VALUES (1,1),(1,11),(11,1),(21,1),(31,1),(41,1),(41,11),(51,1),(51,11),(51,21),(61,1),(61,11),(61,21),(61,31),(61,41),(71,1),(71,11),(71,21),(71,31),(71,41),(81,1),(81,11),(81,21),(81,31),(81,41),(91,1),(101,1),(101,11),(101,21),(101,31),(111,1),(111,11),(121,1),(121,11),(121,21),(121,31),(121,41),(131,1),(141,1);
/*!40000 ALTER TABLE `menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '公告 ID',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `context` varchar(500) NOT NULL COMMENT '内容',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '公告 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'新车上架','2021款上汽大众帕萨特280TSI到底有什么配置，到底值不值。今天我们就细品一下。总之，现在开帕萨特应该不用考虑带不带头盔的问题了。',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(3,'租车活动','租车送水',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(7,'租车促销','租车促销公告',NULL,0,'2022-04-15 08:33:05',NULL,0,11,0),(8,'租车的公告','通知',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,1),(9,'标题','公告内容',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,1),(11,'新车上架','特斯拉 Model3、小鹏 P1、保时捷 911 现已入店，欢迎新老用户前来选定',NULL,0,'2022-04-16 09:58:17',NULL,1,NULL,0);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `activate` varchar(200) NOT NULL COMMENT '激活标记',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '角色 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'管理员','on','管理员',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(11,'经理','on','经理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(21,'销售','on','销售',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'后勤','on','后勤',0,'2022-04-15 08:33:05',NULL,0,NULL,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `activate` varchar(200) DEFAULT NULL COMMENT '激活标记',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '用户 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','123456','on','admin@back.com','17600001111','男',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(11,'manage','123456','on','manage@back.com','17600001112',NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(21,'sale','123456','on','sale@back.com','17600001113',NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'supporter','123456','on','supporter@back.com','17600001114',NULL,'',0,'2022-04-15 08:33:05',NULL,0,1,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL COMMENT '用户 ID',
  `role_id` int NOT NULL COMMENT '角色 ID',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE COMMENT '用户角色表主键'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户_角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(11,11),(21,21),(31,31);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `check_view`
--

/*!50001 DROP VIEW IF EXISTS `check_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `check_view` AS select `c`.`id` AS `id`,`c`.`order_id` AS `order_id`,`c`.`order_number` AS `order_number`,`c`.`question` AS `question`,`c`.`money` AS `money`,`c`.`remark` AS `remark`,`c`.`state` AS `state`,`c`.`fversion` AS `fversion`,`c`.`create_time` AS `create_time`,`c`.`modify_time` AS `modify_time`,`c`.`creator_id` AS `creator_id`,`c`.`modify_id` AS `modify_id`,`c`.`deleted` AS `deleted`,`ci`.`plate_number` AS `car_plate_number`,`cus`.`id` AS `customer_id`,`cus`.`username` AS `customer_name` from (((`check` `c` left join `car_order` `co` on((`c`.`order_id` = `co`.`id`))) left join `car_info` `ci` on((`co`.`car_info_id` = `ci`.`id`))) left join `customer` `cus` on((`co`.`customer_id` = `cus`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-28 21:34:53
