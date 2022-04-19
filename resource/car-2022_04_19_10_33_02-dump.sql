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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评价表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_assess`
--

LOCK TABLES `car_assess` WRITE;
/*!40000 ALTER TABLE `car_assess` DISABLE KEYS */;
INSERT INTO `car_assess` VALUES (11,'2022041616363251198660861798526',11,1,'测试评价cus',0,0,'2022-04-17 14:11:51',NULL,0,10086,0),(21,'2022041818194187899718569412824',31,11,'测试评价112',0,0,'2022-04-18 10:19:41',NULL,10086,10086,0);
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
INSERT INTO `car_info` VALUES (1,'苏AT00001',41,'宝马 X6','宝马','2022-01-01','红色','baoma.jpg',500,3000,NULL,'未出租',0,'2022-04-15 08:33:04',NULL,1,NULL,0),(11,'苏AT00002',1,'迈腾','上汽大众','2021-12-27','黑色','maiteng.jpg',280,1000,NULL,'未出租',5,'2022-04-15 08:33:04',NULL,1,10086,0),(21,'苏AT00003',11,'奥迪 A6L','奥迪','2022-01-01','黑色','aodi.jpg',400,3000,NULL,'未出租',0,'2022-04-15 08:33:04',NULL,1,NULL,0),(31,'苏AT00004',41,'奔驰 GLC','奔驰','2021-12-31','黑色','benchi.jpg',500,3000,NULL,'未出租',1,'2022-04-15 08:33:04',NULL,1,1,0),(41,'苏AT0005',11,'小鹏 P1','小鹏汽车','2021-12-28','白色','2022-4-16-1650099665831xiaopeng_P1.jpg',260,2000,'小鹏','已出租',3,'2022-04-16 09:04:07',NULL,21,10086,0),(51,'苏AT0006',11,'Tesla  Model3','特斯拉上海','2022-01-30',' 黑色','2022-4-16-1650101568833tesla_Model3.jpg',290,2000,'','未出租',1,'2022-04-16 09:33:52',NULL,1,1,0),(61,'苏ATP001',51,'保时捷 911','保时捷','2020-04-29',' 灰色','2022-4-16-1650102426460porsche_911.jpg',1000,10000,'','未出租',0,'2022-04-16 09:47:46',NULL,1,NULL,0);
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
  `start_date` timestamp NULL DEFAULT NULL COMMENT '订单开始时间',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '订单结束时间',
  `out_date` timestamp NULL DEFAULT NULL COMMENT '车辆出库时间',
  `in_date` timestamp NULL DEFAULT NULL COMMENT '车辆入库时间',
  `money` decimal(10,0) DEFAULT NULL COMMENT '汽车租金',
  `cash_pledge` decimal(10,0) DEFAULT NULL COMMENT '押金',
  `tenancy_term` int DEFAULT '0' COMMENT '租期',
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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_order`
--

LOCK TABLES `car_order` WRITE;
/*!40000 ALTER TABLE `car_order` DISABLE KEYS */;
INSERT INTO `car_order` VALUES (11,'2022041616363251198660861798526',11,1,'2022-04-16 00:36:33','2022-04-17 00:36:33','2022-04-16 00:37:38','2022-04-16 00:42:08',280,1000,1,'同意',6,3,'2022-04-16 08:36:32',NULL,10086,1,0),(21,'2022041617484639292316536421590',41,1,'2022-04-16 01:48:46','2022-04-17 01:48:46',NULL,NULL,260,2000,1,'车辆检修',3,1,'2022-04-16 09:48:46',NULL,10086,1,0),(31,'2022041619593207997436765400784',11,1,'2022-04-16 03:59:32','2022-04-17 03:59:32',NULL,NULL,280,1000,1,'',8,1,'2022-04-16 11:59:32',NULL,10086,10086,0),(41,'2022041620022392397676616806659',11,1,'2022-04-16 04:02:24','2022-04-17 04:02:24','2022-04-16 04:03:28','2022-04-17 19:06:52',480,1000,1,'事故已处理',6,10,'2022-04-16 12:02:23',NULL,10086,1,0),(51,'2022041719481477095477464655953',41,1,'2022-04-17 03:48:15','2022-04-18 03:48:15',NULL,NULL,260,2000,1,'',8,1,'2022-04-17 11:48:14',NULL,10086,10086,0),(61,'2022041817073660998803115130045',11,1,'2022-04-18 01:07:37','2022-04-19 01:07:37','2022-04-18 01:19:59','2022-04-18 01:43:15',280,1000,1,' 同意',6,3,'2022-04-18 09:07:36',NULL,10086,1,0),(71,'2022041817522665099388262547453',51,11,'2022-04-18 01:52:27','2022-04-19 01:52:27','2022-04-18 01:52:53','2022-04-18 01:53:35',290,2000,1,' ',6,3,'2022-04-18 09:52:26',NULL,10086,1,0),(81,'2022041818194187899718569412824',31,11,'2022-04-18 02:19:42','2022-04-19 02:19:42','2022-04-18 02:19:58','2022-04-18 02:20:15',500,3000,1,' ',6,3,'2022-04-18 10:19:41',NULL,10086,1,0),(91,'2022041821074127899280601749166',11,11,'2022-04-18 05:07:41','2022-04-19 05:07:41',NULL,NULL,280,1000,1,'',8,1,'2022-04-18 13:07:41',NULL,10086,10086,0),(101,'2022041821074190499125660128596',11,11,'2022-04-18 05:07:42','2022-04-19 05:07:42',NULL,NULL,280,1000,1,'',8,1,'2022-04-18 13:07:41',NULL,10086,10086,0),(111,'2022041821074190999830088596386',11,11,'2022-04-18 05:07:42','2022-04-19 05:07:42',NULL,NULL,280,1000,1,'',8,1,'2022-04-18 13:07:41',NULL,10086,10086,0),(121,'2022041901435147999255910502286',41,11,'2022-04-18 09:43:51','2022-04-20 09:43:51','2022-04-18 09:44:32',NULL,520,2000,2,' ',2,4,'2022-04-18 17:43:51',NULL,10086,1,0);
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
  `fversion` int DEFAULT '0' COMMENT '版本',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `creator_id` int DEFAULT '0' COMMENT '创建人',
  `modify_id` int DEFAULT NULL COMMENT '修改人',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记，0 代表未删除，1 代表已删除',
  PRIMARY KEY (`id`) USING BTREE COMMENT '检修单 ID 主键'
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='检修单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
INSERT INTO `check` VALUES (11,11,'2022041616363251198660861798526','无',0.00,'',0,'2022-04-16 08:39:32',NULL,10086,NULL,0),(21,41,'2022041620022392397676616806659','',0.00,'',0,'2022-04-17 11:09:22',NULL,10086,NULL,0),(31,41,'2022041620022392397676616806659','用户未发现异常',0.00,'无',0,'2022-04-17 11:46:18',NULL,10086,NULL,0),(41,41,'2022041620022392397676616806659','用户未发现异常',0.00,'测试',0,'2022-04-17 19:34:49',NULL,10086,NULL,0),(51,41,'2022041620022392397676616806659','闯红灯',200.00,'',0,'2022-04-18 02:32:02',NULL,10086,NULL,0),(61,61,'2022041817073660998803115130045','用户未发现异常',0.00,'测试测试',0,'2022-04-18 09:20:26',NULL,10086,NULL,0),(71,71,'2022041817522665099388262547453','用户未发现异常',0.00,'',0,'2022-04-18 09:53:09',NULL,10086,NULL,0),(81,81,'2022041818194187899718569412824','用户未发现异常',0.00,'',0,'2022-04-18 10:20:05',NULL,10086,NULL,0),(91,121,'2022041901435147999255910502286','用户未发现异常',0.00,'test',0,'2022-04-18 18:00:29',NULL,10086,NULL,0);
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

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
  `money` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '余额',
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
INSERT INTO `customer` VALUES (1,'customer','123456','on','customer@car.com','17612341111','男',4820,NULL,NULL,NULL,NULL,4,'2022-04-15 08:33:05',NULL,0,10086,0),(11,'test1','123456','on','test1@car.com','17612341112','男',2210,'','','',NULL,2,'2022-04-15 08:33:05',NULL,0,10086,0),(21,'test2','123456','on','test2@car.com','17612341113','男',1000,NULL,NULL,NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'test3','123456','on','test3@car.com','17612341114','女',0,NULL,NULL,NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_log`
--

LOCK TABLES `login_log` WRITE;
/*!40000 ALTER TABLE `login_log` DISABLE KEYS */;
INSERT INTO `login_log` VALUES (11,1,'2022-04-15 00:49:54','127.0.0.1',0,'2022-04-15 08:49:53',NULL,10086,NULL,0),(21,1,'2022-04-15 06:32:22','127.0.0.1',0,'2022-04-15 14:32:21',NULL,10086,NULL,0),(31,1,'2022-04-15 07:17:28','127.0.0.1',0,'2022-04-15 15:17:28',NULL,10086,NULL,0),(41,1,'2022-04-16 00:35:50','127.0.0.1',0,'2022-04-16 08:35:50',NULL,10086,NULL,0),(51,1,'2022-04-16 00:37:15','127.0.0.1',0,'2022-04-16 08:37:15',NULL,10086,NULL,0),(61,1,'2022-04-16 00:38:15','127.0.0.1',0,'2022-04-16 08:38:15',NULL,10086,NULL,0),(71,1,'2022-04-16 00:53:33','127.0.0.1',0,'2022-04-16 08:53:32',NULL,10086,NULL,0),(81,11,'2022-04-16 00:53:59','127.0.0.1',0,'2022-04-16 08:53:59',NULL,10086,NULL,0),(91,21,'2022-04-16 00:58:19','127.0.0.1',0,'2022-04-16 08:58:18',NULL,10086,NULL,0),(101,1,'2022-04-16 01:12:12','127.0.0.1',0,'2022-04-16 09:12:11',NULL,10086,NULL,0),(111,1,'2022-04-16 01:48:36','127.0.0.1',0,'2022-04-16 09:48:36',NULL,10086,NULL,0),(121,1,'2022-04-16 01:49:11','127.0.0.1',0,'2022-04-16 09:49:10',NULL,10086,NULL,0),(131,1,'2022-04-16 02:16:28','127.0.0.1',0,'2022-04-16 10:16:28',NULL,10086,NULL,0),(141,1,'2022-04-16 02:25:07','127.0.0.1',0,'2022-04-16 10:25:07',NULL,10086,NULL,0),(151,1,'2022-04-16 03:44:11','172.17.0.1',0,'2022-04-16 11:44:11',NULL,10086,NULL,0),(161,1,'2022-04-16 03:56:50','127.0.0.1',0,'2022-04-16 11:56:49',NULL,10086,NULL,0),(171,1,'2022-04-16 04:02:58','127.0.0.1',0,'2022-04-16 12:02:57',NULL,10086,NULL,0),(181,1,'2022-04-17 00:58:48','127.0.0.1',0,'2022-04-17 08:58:48',NULL,10086,NULL,0),(191,1,'2022-04-17 01:03:25','127.0.0.1',0,'2022-04-17 09:03:24',NULL,1,NULL,0),(201,1,'2022-04-17 01:04:24','127.0.0.1',0,'2022-04-17 09:04:24',NULL,1,NULL,0),(211,1,'2022-04-17 01:04:50','127.0.0.1',0,'2022-04-17 09:04:49',NULL,10086,NULL,0),(221,1,'2022-04-17 01:33:53','127.0.0.1',0,'2022-04-17 09:33:53',NULL,10086,NULL,0),(231,1,'2022-04-17 01:53:18','127.0.0.1',0,'2022-04-17 09:53:18',NULL,10086,NULL,0),(241,1,'2022-04-17 02:00:22','127.0.0.1',0,'2022-04-17 10:00:21',NULL,10086,NULL,0),(251,1,'2022-04-17 03:06:40','127.0.0.1',0,'2022-04-17 11:06:40',NULL,10086,NULL,0),(261,1,'2022-04-17 03:10:39','127.0.0.1',0,'2022-04-17 11:10:39',NULL,10086,NULL,0),(271,1,'2022-04-17 03:47:01','127.0.0.1',0,'2022-04-17 11:47:01',NULL,10086,NULL,0),(281,1,'2022-04-17 05:56:59','127.0.0.1',0,'2022-04-17 13:56:59',NULL,10086,NULL,0),(291,1,'2022-04-17 07:12:06','127.0.0.1',0,'2022-04-17 15:12:06',NULL,10086,NULL,0),(301,1,'2022-04-17 09:15:27','127.0.0.1',0,'2022-04-17 17:15:27',NULL,10086,NULL,0),(311,1,'2022-04-17 11:01:11','127.0.0.1',0,'2022-04-17 19:01:11',NULL,10086,NULL,0),(321,1,'2022-04-17 11:23:30','127.0.0.1',0,'2022-04-17 19:23:29',NULL,10086,NULL,0),(331,1,'2022-04-17 11:24:25','127.0.0.1',0,'2022-04-17 19:24:25',NULL,10086,NULL,0),(341,1,'2022-04-17 18:32:57','127.0.0.1',0,'2022-04-18 02:32:57',NULL,10086,NULL,0),(351,1,'2022-04-17 19:09:23','127.0.0.1',0,'2022-04-18 03:09:23',NULL,10086,NULL,0),(361,1,'2022-04-17 19:20:44','127.0.0.1',0,'2022-04-18 03:20:43',NULL,10086,NULL,0),(371,1,'2022-04-17 21:39:20','127.0.0.1',0,'2022-04-18 05:39:20',NULL,10086,NULL,0),(381,1,'2022-04-17 21:54:31','127.0.0.1',0,'2022-04-18 05:54:30',NULL,10086,NULL,0),(391,1,'2022-04-17 23:30:13','127.0.0.1',0,'2022-04-18 07:30:12',NULL,10086,NULL,0),(401,1,'2022-04-18 01:01:30','127.0.0.1',0,'2022-04-18 09:01:30',NULL,10086,NULL,0),(411,1,'2022-04-18 01:08:22','127.0.0.1',0,'2022-04-18 09:08:22',NULL,10086,NULL,0),(421,1,'2022-04-18 01:31:21','127.0.0.1',0,'2022-04-18 09:31:20',NULL,10086,NULL,0),(431,1,'2022-04-18 01:47:03','127.0.0.1',0,'2022-04-18 09:47:02',NULL,10086,NULL,0),(441,11,'2022-04-18 01:51:54','127.0.0.1',0,'2022-04-18 09:51:54',NULL,10086,NULL,0),(451,11,'2022-04-18 05:00:56','127.0.0.1',0,'2022-04-18 13:00:56',NULL,10086,NULL,0),(461,11,'2022-04-18 09:43:28','127.0.0.1',0,'2022-04-18 17:43:28',NULL,10086,NULL,0),(471,1,'2022-04-18 09:44:19','127.0.0.1',0,'2022-04-18 17:44:18',NULL,10086,NULL,0);
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
INSERT INTO `menu` VALUES (1,'员工管理',1,0,'无','on','无',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(11,'权限管理',1,0,'无','on','这个是权限管理的根目录',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(21,'菜单管理',2,11,'menuView','on','菜单管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'角色管理',2,11,'roleView','on','角色管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(41,'员工信息管理',2,1,'userView','on','用户管理',0,'2022-04-15 08:33:05',NULL,0,1,0),(51,'我的信息',1,0,'userInfo','on','用户信息',0,'2022-04-15 08:33:05',NULL,0,1,0),(61,'汽车管理',1,0,'无','on','汽车管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(71,'汽车信息管理',2,61,'carInfo','on','汽车信息',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(81,'汽车类型管理',2,61,'carType','on','汽车类型信息',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(91,'后台登录日志',1,0,'loginLog','on','后台登录日志',0,'2022-04-15 08:33:05',NULL,0,1,0),(101,'客户管理',1,0,'customer','on','客户信息管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(111,'公告管理',1,0,'notice','on','公告管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(121,'订单管理',1,0,'order','on','订单信息管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(131,'轮播管理',1,0,'carousel','on','轮播管理',0,'2022-04-15 08:33:05',NULL,0,NULL,0),(141,'事故单管理',1,0,'accident','on','事故单管理',0,'2022-04-17 09:57:22',NULL,1,1,1);
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
INSERT INTO `menu_role` VALUES (1,1),(1,11),(11,1),(21,1),(31,1),(41,1),(41,11),(51,1),(51,11),(51,21),(61,1),(61,11),(61,21),(61,31),(61,41),(71,1),(71,11),(71,21),(71,31),(71,41),(81,1),(81,11),(81,21),(81,31),(81,41),(91,1),(101,1),(101,11),(101,21),(101,31),(111,1),(111,11),(121,1),(121,11),(121,21),(121,31),(121,41),(131,1);
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
INSERT INTO `user` VALUES (1,'admin','123456','on','admin@back.com','17600001111','男',NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(11,'manage','123456','on','manage@back.com','17600001112',NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(21,'sale','123456','on','sale@back.com','17600001113',NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0),(31,'supporter','123456','on','supporter@back.com','17600001114',NULL,NULL,0,'2022-04-15 08:33:05',NULL,0,NULL,0);
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-19 10:33:03
