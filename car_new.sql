-- MySQL dump 10.13  Distrib 5.6.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: car_new
-- ------------------------------------------------------
-- Server version	5.6.20-log

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '车主id',
  `car_id` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `type` varchar(20) DEFAULT NULL COMMENT '车辆类型',
  `car_describe` varchar(255) DEFAULT NULL COMMENT '描述',
  `rent` int(11) DEFAULT NULL COMMENT '价格',
  `deposit` int(11) DEFAULT NULL COMMENT '押金',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `thumbnail_url` varchar(255) DEFAULT NULL COMMENT '略缩图',
  `slide_url` varchar(255) DEFAULT NULL COMMENT '轮播图',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `contact_name` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `contact_phone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='汽车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (35,'2022-03-25 22:15:12',0,'001','轿车','大黄蜂',66,66,1,'/upload/2022-03-25-22-14-47.jpeg','[\"/upload/2022-03-25-22-14-47.jpeg\",\"/upload/2022-03-25-22-14-50.jpeg\"]','马鞍山','张三','16666666666',0),(36,'2022-03-25 22:18:28',0,'002','轿车','略',88,88,0,'/upload/2022-03-25-22-17-50.jpeg','[\"/upload/2022-03-25-22-17-50.jpeg\"]','马鞍山','叶','15555021375',0),(37,'2022-03-25 22:23:37',0,'003','SUV','略',66,66,1,'/upload/2022-03-25-22-23-03.jpeg','[\"/upload/2022-03-25-22-23-03.jpeg\"]','马鞍山','叶','15555021375',0),(38,'2022-03-25 22:24:27',0,'004','SUV','略',88,88,-1,'/upload/2022-03-25-22-24-14.jpeg','[\"/upload/2022-03-25-22-24-14.jpeg\"]','马鞍山','叶','15555021375',0),(39,'2022-04-25 15:01:50',0,'005','SUV','阿尔法·罗密欧（Alfa Romeo）是意大利著名的轿车和跑车制造商，创建于1910年，总部设在米兰。公司原名ALFA（Anonima Lombarda Fabbrica Automobili，伦巴第汽车制造厂），其前身最早可追溯至1906年由Alexandre Darracq创立于那不勒斯后迁至米兰的一个汽车公司。',88,88,1,'/upload/2022-04-22-15-00-13.jpg','[\"/upload/2022-04-22-15-00-13.jpg\",\"/upload/2022-04-22-15-00-16.jpg\",\"/upload/2022-04-22-15-00-20.jpg\",\"/upload/2022-04-22-15-00-23.jpg\",\"/upload/2022-04-22-15-00-26.jpg\",\"/upload/2022-04-22-15-00-30.jpg\"]','马鞍山安徽工业大学','燚旻阿','15555021375',0),(40,'2022-04-04 10:48:55',0,'006','跑车','小蓝车，很蓝的啦，哈哈哈哈',66,66,1,'/upload/2022-03-25-22-25-53.jpeg','[\"/upload/2022-03-25-22-25-53.jpeg\"]','滁州','叶','15555021375',0),(41,'2022-03-25 22:26:46',0,'007','跑车','小绿车',88,88,0,'/upload/2022-03-25-22-26-26.jpeg','[\"/upload/2022-03-25-22-26-26.jpeg\"]','马鞍山','叶','15555021375',0),(42,'2022-03-25 22:29:30',0,'008','跑车','小红车',88,88,1,'/upload/2022-03-25-22-29-19.jpeg','[\"/upload/2022-03-25-22-29-19.jpeg\"]','马鞍山','叶','15555021375',0),(43,'2022-04-02 09:03:14',0,'009','跑车','小黄',88,888,1,'/upload/2022-04-02-09-02-54.jpeg','[\"/upload/2022-04-02-09-02-54.jpeg\"]','马鞍山','叶','15555021375',0),(44,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,0,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(57,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(58,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(59,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(60,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(61,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(62,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0),(63,'2022-04-03 16:56:55',0,'test','SUV','test',2222,2222,1,'/upload/2022-04-03-16-56-37.jpeg','[\"/upload/2022-04-03-16-56-37.jpeg\"]','马鞍山','张三','16666666666',0);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_order`
--

DROP TABLE IF EXISTS `car_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `owner_id` bigint(20) DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '-3已取消，-2待签合同，-1待付款，0生效中，1已到期,2换车',
  `rent` int(11) DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL,
  `days` mediumtext,
  `total` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_order`
--

LOCK TABLES `car_order` WRITE;
/*!40000 ALTER TABLE `car_order` DISABLE KEYS */;
INSERT INTO `car_order` VALUES (2,'2022-04-21 12:44:34',0,0,35,-3,66,66,'30',1980,'2022-04-21 00:00:00','2022-05-21 00:00:00'),(3,'2022-04-21 13:19:02',0,0,35,-3,66,66,'30',1980,'2022-04-21 00:00:00','2022-05-21 00:00:00'),(4,'2022-04-21 16:24:13',0,0,35,-2,66,66,'30',1980,'2022-04-21 00:00:00','2022-05-21 00:00:00'),(5,'2022-04-21 17:27:18',0,0,35,-3,66,66,'30',1980,'2022-04-21 00:00:00','2022-05-21 00:00:00'),(6,'2022-04-21 22:20:35',0,0,35,-1,66,66,'30',1980,'2022-04-21 00:00:00','2022-05-21 00:00:00'),(7,'2022-04-22 14:42:38',0,0,35,1,66,66,'6',462,'2022-04-22 00:00:00','2022-04-28 21:25:09'),(8,'2022-04-25 16:36:53',0,0,39,0,88,88,'30',2640,'2022-04-25 00:00:00','2022-05-25 00:00:00'),(9,'2022-05-01 14:27:21',1,0,44,0,2222,2222,'2',4444,'2022-05-01 00:00:00','2022-05-03 00:00:00');
/*!40000 ALTER TABLE `car_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '0：待处理\n1：已处理',
  `reply` varchar(255) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='反馈表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'2022-05-02 21:46:04','反馈测试','这里是内容这里是内容这里是内容这里是内容',1,'zhangsan',0,NULL),(2,'2022-05-02 22:52:30','网站做的真好看','网站做的真好看',1,'zhangsan',0,NULL);
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mark`
--

LOCK TABLES `mark` WRITE;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` VALUES (89,'2022-05-01 14:39:54',1,57),(95,'2022-05-01 16:14:01',1,39),(100,'2022-05-01 16:19:23',1,40),(133,'2022-05-02 00:05:57',0,39),(134,'2022-05-02 00:06:03',0,40),(136,'2022-05-02 00:06:28',0,35),(138,'2022-05-02 16:19:57',0,42);
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资讯表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2022-05-02 18:33:36','第一条资讯哈哈哈','这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容这里是内容哈哈哈哈',0),(2,'2022-05-02 18:36:35','蔡徐坤鸡你太美','蔡徐坤（KUN），1998年8月2日出生于浙江省温州市，籍贯湖南吉首 [142]  ，中国内地男歌手、原创音乐制作人 [1]  、MV导演 [109]  、演员。',0),(3,'2022-05-02 17:11:32','刘畊宏走红后，我们和他背后的“推手”聊了聊','刘畊宏抖音账号从4月上旬的几百万粉丝，一路飙升到如今已有近6000万人关注。热浪数据显示，在过去一个月，刘畊宏发布的30多个短视频作品，平均播放量超5000万，其4月22日发布的一条毽子操分解版短视频，播放量更是达到了惊人的1.05亿。\n与此同时，刘畊宏背后的“推手”——一家MCN公司，也开始引起人们的关注。近日，无忧传媒创始人兼CEO雷彬艺接受了中新财经记者专访，以他的视角讲述了刘畊宏的走红过程。',0),(4,'2022-05-02 17:24:03','汽车租赁','汽车租赁业被称为交通运输服务行，它因为无须办理保险、无须年检维修、车型可随意更换等优点，以租车代替买车来控制企业成本，这种在外企中十分流行的管理方式，正慢慢受到国内企事业单位和个人用户的青睐。\n汽车租赁是指将汽车的资产使用权从拥有权中分开，出租人具有资产所有权，承租人拥有资产使用权，出租人与承租人签订租赁合同，以交换使用权利的一种交易形式。',0),(5,'2022-05-02 17:25:32','bilibili','哔哩哔哩（NASDAQ:BILI；HKEX:9626 [232]  ），英文名称：bilibili，简称B站，现为中国年轻世代高度聚集的文化社区和视频平台，该网站于2009年6月26日创建，被粉丝们亲切地称为“B站” [1-2]  。2018年3月28日，哔哩哔哩在美国纳斯达克上市 [3]  。2021年3月29日，哔哩哔哩正式在香港二次上市',0),(6,'2022-05-02 22:47:45','qqq','qqqq',0),(7,'2022-05-02 22:48:05','qqq','qqqqq',0);
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` int(11) DEFAULT '0' COMMENT '1正常，0禁用',
  `role` varchar(20) DEFAULT NULL COMMENT '角色',
  `sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `is_deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'admin','admin','123','15555021375','3258990296@qq.com',NULL,'000000000000000000','/img/default-avatar.jpg',1,'admin',NULL,0),(1,'张三','zhangsan','123456','13333333332','111222@qq.com','2022-04-25 14:23:01','341181200000000000','/img/default-avatar.jpg',1,'customer','保密',0),(5,'李四','lisi','123456','12222222222','lisi@qq.com','2022-03-14 11:23:00','还没有填写','/img/default-avatar.jpg',0,'customer','保密',0),(7,'王五','wangwu','123','18888888888','wangwu@qq.com','2022-03-14 15:00:13','还没有填写','/img/default-avatar.jpg',0,'owner','保密',0),(8,'test','test','123','15511111111','test@qq.com','2022-03-21 15:40:08','111111111111111','/img/default-avatar.jpg',1,'customer','保密',1),(11,'test','test1','123','15511111111','test@qq.com','2022-03-21 15:40:47','111111111111111','/img/default-avatar.jpg',1,'customer','保密',0),(14,'test3','test3','123','15512351234','test3@qq.com','2022-03-22 20:27:03','111111111111111','/img/default-avatar.jpg',1,'customer','保密',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-02 23:08:20
