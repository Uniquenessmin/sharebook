-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: sharebook
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` char(20) NOT NULL,
  `book_author` char(20) NOT NULL,
  `sharer` char(20) DEFAULT NULL,
  `category` int(11) NOT NULL,
  `describute` varchar(300) DEFAULT NULL,
  `book_icon` varchar(20) DEFAULT NULL,
  `book_source` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`book_id`),
  KEY `category_idx` (`category`),
  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'白夜行','aa','aa',1,'这是白夜行,hello.有趣.在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/白夜行.jpg',NULL),(2,'活着','aa','aa',1,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/活着.jpg',NULL),(3,'龙族','李书龙',NULL,3,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/龙族.jpg',NULL),(4,'一个人的朝圣','李书龙',NULL,3,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/一个人的朝圣.jpg',NULL),(5,'你想活出什么样的人生','李书龙',NULL,3,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/你想活出什么样的人生.jpg',NULL),(6,'天之炽','李书龙',NULL,3,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/天之炽.jpg',NULL),(7,'一切皆有可能','李小池',NULL,4,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/一切皆有可能.jpg',NULL),(8,'债务危机','李书龙',NULL,2,'在这部短篇小说集中，童伟格运用乡土、魔幻写实，甚至是历史与神话的嫁接等各种自由的叙事，拓展出九篇面貌繁复的作品，并在这些篇章以滨海山村为原点，反复书写来去其中的人。他们跨过山，越过海，穿行公路，去往城市，最终又回返山村。不断徘徊的人们，重复出现的场景，让小说展示出一幅幅时间冻结的画面，并且在一次次静止的瞬间之中，直面命运。','imgs/债务危机.jpg',NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-29 14:20:15
