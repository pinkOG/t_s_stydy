-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: education_platform
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `assignment_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `submitted_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `file_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `task_id` (`task_id`),
  KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (3,5,1,'2024-06-13 19:57:58','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业3.docx'),(4,5,2,'2024-06-08 20:58:08','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生2 - 副本.docx'),(5,5,3,'2024-06-08 20:58:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生3 - 副本 (9).docx'),(6,5,4,'2024-06-08 20:58:38','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生4 - 副本 (8).docx'),(7,5,5,'2024-06-08 20:59:04','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生5 - 副本 (7).docx'),(8,5,6,'2024-06-08 20:59:15','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生6 - 副本 (6).docx'),(9,5,7,'2024-06-08 20:59:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生7 - 副本 (5).docx'),(10,5,8,'2024-06-08 20:59:40','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生8 - 副本 (4).docx'),(11,6,1,'2024-06-08 18:36:28','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生1.docx'),(12,6,2,'2024-06-08 20:58:08','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生2 - 副本.docx'),(13,6,3,'2024-06-08 20:58:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生3 - 副本 (9).docx'),(14,6,4,'2024-06-08 20:58:38','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生4 - 副本 (8).docx'),(15,6,5,'2024-06-08 20:59:04','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生5 - 副本 (7).docx'),(16,6,6,'2024-06-08 20:59:15','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生6 - 副本 (6).docx'),(17,6,7,'2024-06-08 20:59:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生7 - 副本 (5).docx'),(18,6,8,'2024-06-08 20:59:40','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生8 - 副本 (4).docx'),(21,7,1,'2024-06-08 18:36:28','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生1.docx'),(22,7,2,'2024-06-08 20:58:08','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生2 - 副本.docx'),(23,7,3,'2024-06-08 20:58:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生3 - 副本 (9).docx'),(24,7,4,'2024-06-08 20:58:38','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生4 - 副本 (8).docx'),(25,7,5,'2024-06-08 20:59:04','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生5 - 副本 (7).docx'),(26,7,6,'2024-06-08 20:59:15','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生6 - 副本 (6).docx'),(27,7,7,'2024-06-08 20:59:29','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生7 - 副本 (5).docx'),(28,7,8,'2024-06-08 20:59:40','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业6 -学生8 - 副本 (4).docx');
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignments_tasks`
--

DROP TABLE IF EXISTS `assignments_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments_tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `description` text,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `file_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  KEY `course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments_tasks`
--

LOCK TABLES `assignments_tasks` WRITE;
/*!40000 ALTER TABLE `assignments_tasks` DISABLE KEYS */;
INSERT INTO `assignments_tasks` VALUES (5,1,1,'关于字符串的应用','2024-06-01 10:00:00','2024-06-01 18:00:00','2024-06-08 10:32:32','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业2.docx'),(6,1,1,'集合的应用','2024-06-01 10:00:00','2024-06-08 18:32:32','2024-06-09 02:21:33','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\作业2.docx'),(7,1,1,'','2024-06-14 00:35:00','2024-06-30 00:35:00','2024-06-13 16:35:52','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\Python语言程序设计实验报告10_312.doc');
/*!40000 ALTER TABLE `assignments_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learning_materials`
--

DROP TABLE IF EXISTS `learning_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `learning_materials` (
  `material_id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `file_path` varchar(255) DEFAULT NULL,
  `file_type` enum('VIDEO','DOCUMENT','ASSIGNMENT','SASSIGNMENT','RESOURCE') NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  KEY `course_id` (`course_id`),
  KEY `learning_materials_users_user_id_fk` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learning_materials`
--

LOCK TABLES `learning_materials` WRITE;
/*!40000 ALTER TABLE `learning_materials` DISABLE KEYS */;
INSERT INTO `learning_materials` VALUES (9,1,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片1.jpg','RESOURCE','2024-06-08 06:02:41',1),(10,2,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片2.jpg','RESOURCE','2024-06-08 06:03:04',1),(11,3,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片3.jpg','RESOURCE','2024-06-08 06:03:17',1),(12,4,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片4.jpg','RESOURCE','2024-06-08 06:03:29',1),(13,5,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片5.jpg','RESOURCE','2024-06-08 06:03:37',1),(14,6,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片6.jpg','RESOURCE','2024-06-08 06:03:44',1),(17,7,NULL,NULL,'D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\课程图片6.jpg','RESOURCE','2024-06-09 08:47:45',0),(18,1,'音标','哈哈','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\48个音标纯示范.mp4','VIDEO','2024-06-10 03:47:50',0),(19,1,'音标2','嘻嘻','D:\\Java学习\\ideaProjects\\finalwork02\\src\\main\\resources\\upload\\48个音标纯示范.mp4','VIDEO','2024-06-10 05:01:41',0);
/*!40000 ALTER TABLE `learning_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `low_priority_queue`
--

DROP TABLE IF EXISTS `low_priority_queue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `low_priority_queue` (
  `queue_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`queue_id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `low_priority_queue_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `low_priority_queue`
--

LOCK TABLES `low_priority_queue` WRITE;
/*!40000 ALTER TABLE `low_priority_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `low_priority_queue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lowest_score`
--

DROP TABLE IF EXISTS `lowest_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lowest_score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL,
  `pass_rate` decimal(5,2) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `final_score` decimal(5,2) DEFAULT NULL,
  `highest_score` decimal(5,2) DEFAULT NULL,
  `lowest_score` decimal(5,2) DEFAULT NULL,
  `average_score` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lowest_score`
--

LOCK TABLES `lowest_score` WRITE;
/*!40000 ALTER TABLE `lowest_score` DISABLE KEYS */;
INSERT INTO `lowest_score` VALUES (209,5,0.00,1,0.00,NULL,NULL,NULL),(210,5,0.00,2,0.00,NULL,NULL,NULL),(211,5,0.00,3,0.00,NULL,NULL,NULL),(212,5,0.00,4,0.00,NULL,NULL,NULL),(213,5,0.00,5,0.00,NULL,NULL,NULL),(214,5,0.00,6,0.00,NULL,NULL,NULL),(215,5,0.00,7,0.00,NULL,NULL,NULL),(216,5,0.00,8,0.00,NULL,NULL,NULL),(217,6,0.00,1,0.00,NULL,NULL,NULL),(218,6,0.00,2,0.00,NULL,NULL,NULL),(219,6,0.00,3,0.00,NULL,NULL,NULL),(220,6,0.00,4,0.00,NULL,NULL,NULL),(221,6,0.00,5,0.00,NULL,NULL,NULL),(222,6,0.00,6,0.00,NULL,NULL,NULL),(223,6,0.00,7,0.00,NULL,NULL,NULL),(224,6,0.00,8,0.00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `lowest_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `over_courses`
--

DROP TABLE IF EXISTS `over_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `over_courses` (
  `course_id` int NOT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `entry_code` varchar(6) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `over_courses`
--

LOCK TABLES `over_courses` WRITE;
/*!40000 ALTER TABLE `over_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `over_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_records`
--

DROP TABLE IF EXISTS `review_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NOT NULL,
  `student_id` int NOT NULL,
  `assigned_to` int DEFAULT NULL,
  `score` decimal(5,2) DEFAULT '0.00',
  `feedback` text,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_records`
--

LOCK TABLES `review_records` WRITE;
/*!40000 ALTER TABLE `review_records` DISABLE KEYS */;
INSERT INTO `review_records` VALUES (69,5,1,4,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(70,5,1,8,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(71,5,1,3,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(72,5,2,8,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(73,5,2,5,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(74,5,2,6,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(75,5,3,4,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(76,5,3,7,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(77,5,3,1,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(78,5,4,8,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(79,5,4,1,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(80,5,4,5,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(81,5,5,1,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(82,5,5,6,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(83,5,5,3,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(84,5,6,7,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(85,5,6,2,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(86,5,6,3,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(87,5,7,2,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(88,5,7,6,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(89,5,7,4,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(90,5,8,5,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(91,5,8,7,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31'),(92,5,8,2,0.00,NULL,'2024-06-14 00:39:32','2024-06-19 00:39:32','2024-06-13 16:39:31');
/*!40000 ALTER TABLE `review_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_analysis`
--

DROP TABLE IF EXISTS `score_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_analysis` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int DEFAULT NULL,
  `total_score` decimal(7,2) DEFAULT NULL,
  `average_score` decimal(5,2) DEFAULT NULL,
  `highest_score` decimal(5,2) DEFAULT NULL,
  `lowest_score` decimal(5,2) DEFAULT NULL,
  `task_count` int DEFAULT NULL,
  `course_rank` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_analysis`
--

LOCK TABLES `score_analysis` WRITE;
/*!40000 ALTER TABLE `score_analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `score_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score_analysis_initial`
--

DROP TABLE IF EXISTS `score_analysis_initial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `score_analysis_initial` (
  `id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL,
  `highest_score` decimal(5,2) DEFAULT NULL,
  `lowest_score` decimal(5,2) DEFAULT NULL,
  `average_score` decimal(5,2) DEFAULT NULL,
  `pass_rate` decimal(5,2) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `final_score` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=321 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score_analysis_initial`
--

LOCK TABLES `score_analysis_initial` WRITE;
/*!40000 ALTER TABLE `score_analysis_initial` DISABLE KEYS */;
INSERT INTO `score_analysis_initial` VALUES (305,5,77.00,66.00,71.50,1.00,1,66.00),(306,5,77.00,66.00,71.50,1.00,2,73.33),(307,5,77.00,66.00,71.50,1.00,3,73.33),(308,5,77.00,66.00,71.50,1.00,4,66.00),(309,5,77.00,66.00,71.50,1.00,5,69.67),(310,5,77.00,66.00,71.50,1.00,6,73.33),(311,5,77.00,66.00,71.50,1.00,7,73.33),(312,5,77.00,66.00,71.50,1.00,8,77.00),(313,6,0.00,0.00,0.00,0.00,1,0.00),(314,6,0.00,0.00,0.00,0.00,2,0.00),(315,6,0.00,0.00,0.00,0.00,3,0.00),(316,6,0.00,0.00,0.00,0.00,4,0.00),(317,6,0.00,0.00,0.00,0.00,5,0.00),(318,6,0.00,0.00,0.00,0.00,6,0.00),(319,6,0.00,0.00,0.00,0.00,7,0.00),(320,6,0.00,0.00,0.00,0.00,8,0.00);
/*!40000 ALTER TABLE `score_analysis_initial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_in_records`
--

DROP TABLE IF EXISTS `sign_in_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sign_in_records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `sign_in_time` datetime DEFAULT NULL,
  `status` enum('ON_TIME','LATE','ABSENT') DEFAULT 'ABSENT',
  PRIMARY KEY (`record_id`),
  KEY `task_id` (`task_id`),
  KEY `sign_in_records_student_details_id_fk` (`student_id`),
  CONSTRAINT `sign_in_records_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `sign_in_tasks` (`task_id`),
  CONSTRAINT `sign_in_records_student_details_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student_details` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_in_records`
--

LOCK TABLES `sign_in_records` WRITE;
/*!40000 ALTER TABLE `sign_in_records` DISABLE KEYS */;
INSERT INTO `sign_in_records` VALUES (3,3,1,'2024-06-09 22:29:02','ON_TIME'),(5,4,1,'2024-06-14 00:40:49','ON_TIME'),(6,5,1,'2024-06-14 00:41:12','ON_TIME');
/*!40000 ALTER TABLE `sign_in_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sign_in_tasks`
--

DROP TABLE IF EXISTS `sign_in_tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sign_in_tasks` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `sign_code` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`task_id`),
  KEY `sign_in_tasks_ibfk_1` (`teacher_id`),
  CONSTRAINT `sign_in_tasks_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher_details` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sign_in_tasks`
--

LOCK TABLES `sign_in_tasks` WRITE;
/*!40000 ALTER TABLE `sign_in_tasks` DISABLE KEYS */;
INSERT INTO `sign_in_tasks` VALUES (1,1,'2023-10-01 08:00:00','2023-10-01 09:00:00','2024-06-02 14:16:13',1234),(2,1,'2024-06-09 09:00:00','2024-06-09 11:00:00','2024-06-09 12:31:53',696926),(3,1,'2024-06-09 09:00:00','2024-06-10 11:00:00','2024-06-09 14:26:09',322984),(4,1,'2024-06-13 16:40:31','2024-06-14 16:40:31','2024-06-13 16:40:39',398712),(5,1,'2024-06-13 16:40:31','2024-06-14 16:40:31','2024-06-13 16:41:05',558775);
/*!40000 ALTER TABLE `sign_in_tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (2,1,5),(3,1,4),(4,1,3),(5,1,2),(6,1,1),(7,2,6),(8,2,5),(9,2,4),(10,2,3),(11,2,2),(12,2,1),(13,3,6),(14,3,5),(15,3,4),(16,3,3),(17,3,2),(18,3,1),(19,4,6),(20,4,5),(21,4,4),(22,4,3),(23,4,2),(24,4,1),(25,5,6),(26,5,5),(27,5,4),(28,5,3),(29,5,2),(30,5,1),(31,6,6),(32,6,5),(33,6,4),(34,6,3),(35,6,2),(36,6,1),(37,7,6),(38,7,5),(39,7,4),(40,7,3),(41,7,2),(42,7,1),(43,8,6),(44,8,5),(45,8,4),(46,8,3),(47,8,2),(48,8,1),(49,9,6),(50,9,5),(51,9,4),(52,9,3),(53,9,2),(54,9,1),(55,10,6),(56,10,5),(57,10,4),(58,10,3),(59,10,2),(60,10,1),(61,11,6),(62,11,5),(63,11,4),(64,11,3),(65,11,2),(66,11,1),(67,12,6),(68,12,5),(69,12,4),(70,12,3),(71,12,2),(72,12,1),(130,1,6);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_details`
--

DROP TABLE IF EXISTS `student_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_details` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `student_number` int DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  KEY `FKt5phq64s517nt34bmyybwlnt4` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_details`
--

LOCK TABLES `student_details` WRITE;
/*!40000 ALTER TABLE `student_details` DISABLE KEYS */;
INSERT INTO `student_details` VALUES (1,2,12345,'School A','Major X','Class 1'),(2,3,12346,'School B','Major Y','Class 2'),(3,4,12347,'School C','Major Z','Class 3'),(4,5,12348,'School D','Major A','Class 4'),(5,6,12349,'School E','Major B','Class 5'),(6,7,12350,'School F','Major C','Class 6'),(7,8,12351,'School G','Major D','Class 7'),(8,9,67890,'QINGHUA University','Computer Science','CS101'),(9,10,12353,'School I','Major F','Class 9'),(10,11,12354,'School J','Major G','Class 10'),(11,12,45875,'shcool f','major z','class 10'),(12,13,45645,'school z','major x','class 10');
/*!40000 ALTER TABLE `student_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_courses`
--

DROP TABLE IF EXISTS `teacher_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_courses` (
  `course_id` int NOT NULL,
  `class_name` varchar(50) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `entry_code` varchar(6) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `teacher_courses___fk` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_courses`
--

LOCK TABLES `teacher_courses` WRITE;
/*!40000 ALTER TABLE `teacher_courses` DISABLE KEYS */;
INSERT INTO `teacher_courses` VALUES (1,'班级1','python','H0A6N3',NULL,1),(2,'班级1','python02','H9YSH3',NULL,1),(3,'班级1','python03','5J4U76',NULL,1),(4,'班级1','python04','SU0QDF',NULL,1),(5,'班级1','python05','X35SA9',NULL,1),(6,'班级1','python06','9L8UIU',NULL,1),(7,'班级1','python07','9L8UIZ',NULL,1);
/*!40000 ALTER TABLE `teacher_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_details`
--

DROP TABLE IF EXISTS `teacher_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_details` (
  `teacher_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `degree` enum('HIGH SCHOOL','JUNIOR COLLEGE','BACHELOR','MASTER','DOCTORATE') DEFAULT NULL,
  `graduate_in` varchar(255) DEFAULT NULL,
  `work_in` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `teacher_details___fk` (`user_id`),
  CONSTRAINT `teacher_details___fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_details`
--

LOCK TABLES `teacher_details` WRITE;
/*!40000 ALTER TABLE `teacher_details` DISABLE KEYS */;
INSERT INTO `teacher_details` VALUES (1,1,'DOCTORATE','北京大学','清华大学');
/*!40000 ALTER TABLE `teacher_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` enum('TEACHER','STUDENT') NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'teacher1','7c6a180b36896a0a8c02787eeafb0e4c','教师一','teacher1@example.com','TEACHER'),(2,'student1','7c6a180b36896a0a8c02787eeafb0e4c','学生一','student1@example.com','STUDENT'),(3,'student2','6cb75f652a9b52798eb6cf2201057c73','学生二','student2@example.com','STUDENT'),(4,'student3','482c811da5d5b4bc6d497ffa98491e38','Ethan Williams','ethan.williams@example.com','STUDENT'),(5,'student4','482c811da5d5b4bc6d497ffa98491e38','Fiona Davis','fiona.davis@example.com','STUDENT'),(6,'student5','482c811da5d5b4bc6d497ffa98491e38','George Harris','george.harris@example.com','STUDENT'),(7,'student6','482c811da5d5b4bc6d497ffa98491e38','Hannah Clark','hannah.clark@example.com','STUDENT'),(8,'student7','482c811da5d5b4bc6d497ffa98491e38','Ian Martinez','ian.martinez@example.com','STUDENT'),(9,'student8','482c811da5d5b4bc6d497ffa98491e38','Julia Anderson','julia.anderson@example.com','STUDENT'),(10,'student9','482c811da5d5b4bc6d497ffa98491e38','Kevin Wilson','kevin.wilson@example.com','STUDENT'),(11,'student10','482c811da5d5b4bc6d497ffa98491e38','Laura Moore','laura.moore@example.com','STUDENT'),(12,'student11','482c811da5d5b4bc6d497ffa98491e38','nimbi','94324123112@qq.con','STUDENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-14  7:44:09
