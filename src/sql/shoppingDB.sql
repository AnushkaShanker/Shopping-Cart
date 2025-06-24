-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shopdb
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `adminId` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`adminId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Admin1','admin1@shop.com','adminpass'),(2,'Admin2','admin2@shop.com','adminpass'),(3,'Admin3','admin3@shop.com','adminpass'),(4,'Admin4','admin4@shop.com','adminpass'),(5,'Admin5','admin5@shop.com','adminpass'),(6,'Admin6','admin6@shop.com','adminpass'),(7,'Admin7','admin7@shop.com','adminpass'),(8,'Admin8','admin8@shop.com','adminpass'),(9,'Admin9','admin9@shop.com','adminpass'),(10,'Admin10','admin10@shop.com','adminpass');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cartitem` (
  `cartItemId` int NOT NULL,
  `cartId` int DEFAULT NULL,
  `productId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`cartItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitem`
--

LOCK TABLES `cartitem` WRITE;
/*!40000 ALTER TABLE `cartitem` DISABLE KEYS */;
INSERT INTO `cartitem` VALUES (1,101,501,2),(2,101,502,1),(3,102,503,5),(4,102,504,2),(5,103,505,3),(6,103,501,2),(7,104,506,4),(8,105,507,1),(9,106,508,6),(10,107,509,1);
/*!40000 ALTER TABLE `cartitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderId` int NOT NULL,
  `userId` int DEFAULT NULL,
  `orderDate` date DEFAULT NULL,
  `totalAmount` double DEFAULT NULL,
  `paymentStatus` varchar(50) DEFAULT NULL,
  `orderStatus` varchar(50) DEFAULT NULL,
  `shippingAddress` text,
  PRIMARY KEY (`orderId`),
  KEY `userId` (`userId`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'2024-06-01',1000,'Paid','Shipped','Bangalore'),(2,2,'2024-06-02',1500,'Unpaid','Processing','Mumbai'),(3,3,'2024-06-03',800,'Paid','Delivered','Pune'),(4,4,'2024-06-04',950,'Paid','Shipped','Hyderabad'),(5,5,'2024-06-05',3000,'Paid','Pending','Chennai'),(6,6,'2024-06-06',1200,'Unpaid','Processing','Agra'),(7,7,'2024-06-07',2200,'Paid','Shipped','Kolkata'),(8,8,'2024-06-08',450,'Paid','Delivered','Goa'),(9,9,'2024-06-09',670,'Unpaid','Pending','Surat'),(10,10,'2024-06-10',1999,'Paid','Processing','Mumbai');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `orderItemId` int NOT NULL,
  `orderId` int DEFAULT NULL,
  `productId` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
INSERT INTO `orderitem` VALUES (1,1,501,2),(2,2,502,1),(3,3,503,3),(4,4,504,1),(5,5,505,2),(6,6,506,1),(7,7,507,4),(8,8,508,1),(9,9,509,2),(10,10,510,3);
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `paymentId` int NOT NULL,
  `orderId` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `paymentStatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,1,1000,'Success'),(2,2,1500,'Failed'),(3,3,800,'Success'),(4,4,950,'Success'),(5,5,3000,'Success'),(6,6,1200,'Failed'),(7,7,2200,'Success'),(8,8,450,'Success'),(9,9,670,'Pending'),(10,10,1999,'Success');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `shipmentId` int NOT NULL,
  `orderId` int DEFAULT NULL,
  `shipmentDate` date DEFAULT NULL,
  `trackingNumber` bigint DEFAULT NULL,
  `carrier` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`shipmentId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `shipment_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (1,1,'2024-06-02',1234567890,'DHL','Shipped'),(2,2,'2024-06-03',1234567891,'FedEx','Pending'),(3,3,'2024-06-04',1234567892,'Bluedart','Delivered'),(4,4,'2024-06-05',1234567893,'IndiaPost','Shipped'),(5,5,'2024-06-06',1234567894,'XpressBees','Processing'),(6,6,'2024-06-07',1234567895,'Delhivery','Pending'),(7,7,'2024-06-08',1234567896,'Ecom','Shipped'),(8,8,'2024-06-09',1234567897,'Gati','Delivered'),(9,9,'2024-06-10',1234567898,'DTDC','Pending'),(10,10,'2024-06-11',1234567899,'Shadowfax','Processing');
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` bigint DEFAULT NULL,
  `shippingAddress` text,
  `billingAddress` text,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Alice','alice@example.com','pass123',9876543210,'Bangalore','Bangalore'),(2,'Bob','bob@example.com','pass123',9876543211,'Mumbai','Delhi'),(3,'Charlie','charlie@example.com','pass123',9876543212,'Pune','Pune'),(4,'David','david@example.com','pass123',9876543213,'Hyderabad','Hyderabad'),(5,'Eve','eve@example.com','pass123',9876543214,'Chennai','Chennai'),(6,'Frank','frank@example.com','pass123',9876543215,'Jaipur','Agra'),(7,'Grace','grace@example.com','pass123',9876543216,'Kolkata','Kolkata'),(8,'Hank','hank@example.com','pass123',9876543217,'Goa','Goa'),(9,'Ivy','ivy@example.com','pass123',9876543218,'Surat','Surat'),(10,'John','john@example.com','pass123',9876543219,'Delhi','Mumbai');
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

-- Dump completed on 2025-06-24 11:26:36
