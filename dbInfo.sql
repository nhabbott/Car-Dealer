/*
SQLyog Community v13.1.8 (64 bit)
MySQL - 8.0.27-0ubuntu0.20.04.1 : Database - carsdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`carsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `carsdb`;

/*Table structure for table `listing` */

DROP TABLE IF EXISTS `listing`;

CREATE TABLE `listing` (
  `listingId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `soldToId` int DEFAULT '0',
  `wishlistId` int DEFAULT NULL,
  `price` float NOT NULL,
  `datePosted` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `publishListing` tinyint(1) NOT NULL DEFAULT '0',
  `isSold` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`listingId`),
  KEY `listings_ibfk_1` (`wishlistId`),
  CONSTRAINT `listings_ibfk_1` FOREIGN KEY (`wishlistId`) REFERENCES `wishlist` (`wishlistId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `listing` */

insert  into `listing`(`listingId`,`userId`,`soldToId`,`wishlistId`,`price`,`datePosted`,`publishListing`,`isSold`) values 
(1,6,2,NULL,33873,'12/05/2021',0,1),
(2,3,0,NULL,60381,'12/05/2021',0,0),
(3,3,2,NULL,57638,'12/05/2021',0,1),
(4,2,0,NULL,42266,'12/05/2021',1,0),
(5,4,0,NULL,66320,'12/05/2021',0,0),
(6,6,0,NULL,28397,'12/05/2021',1,0),
(7,5,0,NULL,65995,'12/05/2021',0,0),
(8,2,0,NULL,99081,'12/05/2021',1,0),
(9,6,0,NULL,67743,'12/05/2021',0,0),
(10,2,0,NULL,15309,'12/05/2021',1,0),
(11,6,0,NULL,79978,'12/05/2021',0,0),
(12,3,0,NULL,83592,'12/05/2021',0,0),
(13,2,0,NULL,72260,'12/05/2021',0,0),
(14,2,0,NULL,44944,'12/05/2021',1,0),
(15,3,0,NULL,58576,'12/05/2021',0,0),
(16,4,0,NULL,60535,'12/05/2021',0,0),
(17,4,0,NULL,26266,'12/05/2021',0,0),
(18,4,0,NULL,94272,'12/05/2021',1,0),
(19,6,0,NULL,86569,'12/05/2021',1,0),
(20,4,0,NULL,26861,'12/05/2021',0,0),
(21,2,0,NULL,64098,'12/05/2021',1,0),
(22,4,0,NULL,86806,'12/05/2021',1,0),
(23,5,0,NULL,85427,'12/05/2021',1,0),
(24,6,0,NULL,91181,'12/05/2021',0,0),
(25,6,0,NULL,31488,'12/05/2021',1,0),
(26,3,0,NULL,63065,'12/05/2021',1,0),
(27,4,0,NULL,24440,'12/05/2021',1,0),
(28,2,0,NULL,83030,'12/05/2021',0,0),
(29,4,0,NULL,47316,'12/05/2021',0,0),
(30,3,0,NULL,29231,'12/05/2021',0,0),
(31,3,0,NULL,93853,'12/05/2021',1,0),
(32,3,0,NULL,84321,'12/05/2021',1,0),
(33,2,0,NULL,46678,'12/05/2021',0,0),
(34,3,0,NULL,87629,'12/05/2021',1,0),
(35,4,0,NULL,54240,'12/05/2021',1,0),
(36,2,0,NULL,67894,'12/05/2021',1,0),
(37,4,0,NULL,50766,'12/05/2021',1,0),
(38,6,0,NULL,35173,'12/05/2021',0,0),
(39,6,0,NULL,58890,'12/05/2021',0,0),
(40,2,0,NULL,90508,'12/05/2021',1,0),
(41,5,0,NULL,51573,'12/05/2021',1,0),
(42,4,0,NULL,68397,'12/05/2021',0,0),
(43,4,0,NULL,48465,'12/05/2021',0,0),
(44,5,0,NULL,80201,'12/05/2021',0,0),
(45,2,0,NULL,41936,'12/05/2021',0,0),
(46,6,0,NULL,26220,'12/05/2021',0,0),
(47,5,0,NULL,59671,'12/05/2021',1,0),
(48,3,0,NULL,80361,'12/05/2021',0,0),
(49,3,0,NULL,23910,'12/05/2021',1,0),
(50,5,0,NULL,24060,'12/05/2021',0,0),
(51,2,0,NULL,32221,'12/05/2021',0,0),
(52,3,0,NULL,35424,'12/05/2021',0,0),
(53,5,0,NULL,31003,'12/05/2021',0,0),
(54,3,0,NULL,45446,'12/05/2021',0,0),
(55,5,0,NULL,60835,'12/05/2021',0,0),
(56,3,0,NULL,25552,'12/05/2021',1,0),
(57,6,0,NULL,42252,'12/05/2021',0,0),
(58,6,0,NULL,39750,'12/05/2021',0,0),
(59,2,0,NULL,81529,'12/05/2021',1,0),
(60,3,0,NULL,80203,'12/05/2021',1,0),
(61,5,0,NULL,48677,'12/05/2021',0,0),
(62,6,0,NULL,27175,'12/05/2021',1,0),
(63,2,0,NULL,73975,'12/05/2021',0,0),
(64,5,0,NULL,72951,'12/05/2021',1,0),
(65,3,0,NULL,15098,'12/05/2021',1,0),
(66,4,0,NULL,94634,'12/05/2021',1,0),
(67,2,0,NULL,84160,'12/05/2021',0,0),
(68,6,0,NULL,42451,'12/05/2021',0,0),
(69,6,0,NULL,42835,'12/05/2021',1,0),
(70,5,0,NULL,98652,'12/05/2021',0,0),
(71,4,0,NULL,24124,'12/05/2021',1,0),
(72,2,0,NULL,46937,'12/05/2021',1,0),
(73,6,0,NULL,71292,'12/05/2021',1,0),
(74,2,0,NULL,83920,'12/05/2021',0,0),
(75,5,0,NULL,38022,'12/05/2021',0,0),
(76,2,0,NULL,32118,'12/05/2021',0,0),
(77,2,0,NULL,98940,'12/05/2021',0,0),
(78,4,0,NULL,18638,'12/05/2021',1,0),
(79,3,0,NULL,86278,'12/05/2021',1,0),
(80,4,0,NULL,34874,'12/05/2021',0,0),
(81,2,0,NULL,36703,'12/05/2021',1,0),
(82,3,0,NULL,80282,'12/05/2021',0,0),
(83,4,0,NULL,32075,'12/05/2021',0,0),
(84,5,0,NULL,77624,'12/05/2021',1,0),
(85,3,0,NULL,39252,'12/05/2021',1,0),
(86,4,0,NULL,47914,'12/05/2021',1,0),
(87,6,0,NULL,96960,'12/05/2021',1,0),
(88,5,0,NULL,30749,'12/05/2021',0,0),
(89,2,0,NULL,22216,'12/05/2021',0,0),
(90,6,0,NULL,69695,'12/05/2021',0,0),
(91,3,0,NULL,44792,'12/05/2021',0,0),
(92,2,0,NULL,12959,'12/05/2021',0,0),
(93,2,0,NULL,65828,'12/05/2021',0,0),
(94,5,0,NULL,56697,'12/05/2021',1,0),
(95,4,0,NULL,65826,'12/05/2021',1,0),
(96,2,0,NULL,31978,'12/05/2021',1,0),
(97,4,0,NULL,97994,'12/05/2021',1,0),
(98,4,0,NULL,16686,'12/05/2021',1,0),
(99,4,0,NULL,55156,'12/05/2021',1,0),
(100,2,0,NULL,20439,'12/05/2021',1,0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastName` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT '0',
  `userName` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resetToken` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `user` */

insert  into `user`(`userId`,`firstName`,`lastName`,`isAdmin`,`userName`,`password`,`salt`,`email`,`resetToken`) values 
(2,'Test','Testerson',1,'test','-79 94 116 22 -86 119 -65 27','52 -45 -84 -40 83 16 104 -62 -102 5 2 54 69 -89 42 123','ttesterson@test.com',NULL),
(3,'Testy','Testerson',0,'test2','-18 -71 -4 121 70 -18 112 102','62 108 27 118 -87 -107 -116 115 2 -83 -124 115 -69 -19 116 -53','tty@test.com',NULL),
(4,'Callan','Noak',1,'cnoak','-121 9 12 115 -120 82 92 -96','-75 -63 -58 77 94 -85 112 -83 101 -117 62 102 -109 -6 120 -4','callannoak@gmail.com',NULL),
(5,'Jamie','McAndrew',0,'jmcandrew','25 121 73 5 -31 -114 -33 -68','-116 -56 86 -28 -84 54 -26 73 95 26 -67 84 97 -80 30 -81','jamiemcandrew@gmail.com',NULL),
(6,'Kaitlin','Lunt',0,'klunt','116 118 -26 -46 -101 -109 -126 43','36 66 -112 56 -16 -63 92 -80 -54 -72 39 -65 -103 -118 26 5','klunt1@lamar.edu',NULL);

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `vid` int NOT NULL AUTO_INCREMENT,
  `vin` varchar(17) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `size` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `year` int NOT NULL,
  `make` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `model` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cylinders` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `trans` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fuel` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `countryOfProd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mileage` int NOT NULL,
  `age` int NOT NULL,
  PRIMARY KEY (`vid`),
  UNIQUE KEY `VIN` (`vin`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `vehicle` */

insert  into `vehicle`(`vid`,`vin`,`type`,`size`,`year`,`make`,`model`,`cylinders`,`trans`,`fuel`,`countryOfProd`,`mileage`,`age`) values 
(1,'6TJID2CN1BVFX8KFW','sedan','full',2017,'Jeep','Grand Cherokee','twelve','automatic','gasoline','Germany',69680,4),
(2,'4UFZRGTR0YCE2CUIF','hatchback','compact',1993,'Volkswagen','Beetle','four','automatic','gasoline','Japan',55702,28),
(3,'9ZXKM91N8XMV8Y2LU','coupe','full',1996,'Toyota','Camry','four','manual','gasoline','Mexico',53993,25),
(4,'8ZE8D7SF7MZ0BZ4CV','coupe','full',2009,'Dodge','Charge','six','manual','electric','USA',69767,12),
(5,'3FANW0UH0NBDKUP3C','sportscar','full',1971,'Jeep','Grand Cherokee','four','automatic','electric','Germany',33941,50),
(6,'6JR566YA3JOXXQPKF','coupe','full',2009,'Nissan','Altima','eight','automatic','diesel','Germany',160697,12),
(7,'8AFVA1W50JQG8N81R','coupe','mid',1974,'Mercedes-Benz','C-Class','four','manual','diesel','Mexico',65295,47),
(8,'5RBQMH4U9YQ6FDRSO','sportscar','compact',2015,'Nissan','Altima','eight','automatic','diesel','USA',134490,6),
(9,'1ZZ802QC5HB2SN77C','truck','compact',1972,'Ford','Mustang','eight','manual','gasoline','Japan',28436,49),
(10,'4WVQ4WYI5FNIIUTMD','sportscar','full',2004,'Volkswagen','Beetle','four','automatic','diesel','Japan',10083,17),
(11,'6CHPK2NQ1ROQ9FJBX','hatchback','full',1975,'Ford','Mustang','twelve','manual','gasoline','Mexico',22153,46),
(12,'0MW0B5AD6FW4CA7JQ','sedan','compact',2019,'Chevy','Equinox','twelve','automatic','gasoline','USA',40340,2),
(13,'9GORW9LY9JC4AMSNN','sedan','mid',2019,'Volkswagen','Beetle','four','automatic','gasoline','China',146790,2),
(14,'4YK6O22Q4GNSBS09R','crossover','mid',2005,'Ford','Mustang','twelve','manual','diesel','USA',126428,16),
(15,'3VJG688I1NAHRLZP7','coupe','compact',1985,'Dodge','Charge','eight','manual','diesel','Germany',133630,36),
(16,'5VS7VKOO6ETE82OIZ','hatchback','full',2002,'Mercedes-Benz','C-Class','six','automatic','electric','Mexico',153043,19),
(17,'1VAJR2971EG2X7G3X','truck','compact',1996,'Dodge','Charge','eight','automatic','hybrid','Germany',25405,25),
(18,'4UEJQMXJ9KK10FPXJ','sedan','full',1971,'Dodge','Charge','six','automatic','hybrid','China',55529,50),
(19,'6EZ41NPY5YHSKHWP0','crossover','mid',1976,'Mercedes-Benz','C-Class','six','automatic','hybrid','India',125721,45),
(20,'2US4HAAL6KK7I63RI','truck','mid',1977,'Chevy','Equinox','four','manual','diesel','Mexico',115864,44),
(21,'6FSD7EFV9LKAZ2D3W','sportscar','mid',1977,'Toyota','Camry','eight','automatic','electric','India',112858,44),
(22,'5JEG4G0Q0DWZH1XEZ','coupe','mid',1973,'Dodge','Charge','six','automatic','hybrid','USA',22698,48),
(23,'0SXTQNYH6IOL6P6RP','coupe','compact',2002,'Nissan','Altima','twelve','automatic','electric','Mexico',55992,19),
(24,'5ELIC3379CO40HQ0E','hatchback','mid',1977,'Dodge','Charge','twelve','manual','electric','India',121291,44),
(25,'1QW1M7AZ9WWX3HCRS','hatchback','mid',2003,'Honda','Civic','four','automatic','gasoline','Mexico',104268,18),
(26,'9BTWCYCF1LLBG4EL9','crossover','full',1993,'Honda','Civic','six','automatic','electric','Germany',72073,28),
(27,'5FSW0L0B9DB830G6K','sportscar','compact',2004,'Jeep','Grand Cherokee','six','manual','electric','Japan',91387,17),
(28,'1SRJIOL20VKYL1JU1','truck','mid',2000,'Nissan','Altima','twelve','manual','gasoline','India',156906,21),
(29,'5JNOPQSY2ROWFDYGZ','coupe','full',1989,'Nissan','Altima','twelve','manual','diesel','Germany',160474,32),
(30,'1ELI316T2BLEXGEL9','sedan','full',2015,'Dodge','Charge','six','manual','diesel','USA',44702,6),
(31,'1XJ89DUM5PYNKRACF','hatchback','compact',2011,'Mercedes-Benz','C-Class','twelve','automatic','gasoline','Germany',165908,10),
(32,'5JA822HT4UMDIEGIJ','hatchback','compact',2017,'Volkswagen','Beetle','six','manual','electric','Japan',78067,4),
(33,'5RFBJJDH3UYC4MBZO','truck','mid',2005,'Volkswagen','Beetle','six','manual','diesel','India',75574,16),
(34,'3VYWZ4WW1PBYWOGOP','truck','compact',2009,'Dodge','Charge','twelve','automatic','gasoline','Mexico',119930,12),
(35,'6ZD1KGL68CPW8O75Q','sedan','mid',1974,'Honda','Civic','four','manual','hybrid','USA',128609,47),
(36,'6IZKZB2X6NMOFAMV1','sportscar','mid',1972,'Mercedes-Benz','C-Class','four','manual','hybrid','India',78805,49),
(37,'8VRG01UV5QQU1LPUN','truck','compact',2014,'Nissan','Altima','eight','manual','diesel','USA',177,7),
(38,'5QIBACL95PPAP70MT','truck','mid',2013,'Jeep','Grand Cherokee','twelve','automatic','electric','Mexico',113326,8),
(39,'8JK77Y0G5WV39OX0J','coupe','compact',2016,'Dodge','Charge','four','manual','hybrid','Germany',161113,5),
(40,'9YTHTCNF5XNKZX8NB','coupe','mid',1972,'Dodge','Charge','eight','manual','gasoline','China',126047,49),
(41,'2HMFQ4AZ8CMDLOXHH','coupe','full',2008,'Dodge','Charge','twelve','automatic','electric','Mexico',71419,13),
(42,'8OUMMWF47NF1KGSOC','sedan','compact',1978,'Nissan','Altima','eight','automatic','diesel','China',143189,43),
(43,'5UR9YTHV9NGJ1HRZN','truck','full',1984,'Dodge','Charge','eight','manual','hybrid','Germany',62589,37),
(44,'1LBP3BO97EYNEHCXI','truck','full',1998,'Chevy','Equinox','four','automatic','gasoline','Germany',116080,23),
(45,'8UOUAZPR9HGQRXGOM','sedan','compact',2002,'Volkswagen','Beetle','four','automatic','diesel','USA',19578,19),
(46,'7FJU4WD48GC9BY4PH','sportscar','full',2020,'Mercedes-Benz','C-Class','eight','automatic','gasoline','China',71369,1),
(47,'4QRTP1SU9TE8I1L8X','hatchback','mid',1989,'Volkswagen','Beetle','eight','automatic','electric','China',68899,32),
(48,'3XISX5QE7SMQKDWB3','sedan','compact',2011,'Jeep','Grand Cherokee','eight','manual','gasoline','China',105871,10),
(49,'5KHTXQ7Q2HRFLNSFG','coupe','full',1988,'Mercedes-Benz','C-Class','twelve','automatic','diesel','Mexico',39867,33),
(50,'9GSBGY9N7KIIUFJ5V','crossover','mid',2009,'Chevy','Equinox','twelve','manual','hybrid','USA',67934,12),
(51,'3NSW41PU6KB3FV3P3','sedan','compact',1978,'Mercedes-Benz','C-Class','six','manual','hybrid','China',141363,43),
(52,'9GRL8K472JTR3ADGH','hatchback','full',1993,'Toyota','Camry','eight','manual','diesel','Japan',15643,28),
(53,'9AXJ20GR8ZGWGJVQ1','crossover','full',1998,'Chevy','Equinox','eight','manual','diesel','India',61714,23),
(54,'9QWKF0JS4UTVAX04X','crossover','compact',2014,'Dodge','Charge','eight','automatic','electric','China',142940,7),
(55,'4BQAOWTN2NBAFWT11','sportscar','full',1989,'Ford','Mustang','twelve','automatic','hybrid','Mexico',129329,32),
(56,'6UNLO06E5PLDKYP4Q','sportscar','compact',1984,'Dodge','Charge','six','automatic','gasoline','Japan',167832,37),
(57,'9ZV5K5ZX5XURPF1JD','sportscar','mid',1999,'Dodge','Charge','eight','manual','hybrid','Germany',16622,22),
(58,'3LU2HY882TBQQZ9ZJ','crossover','full',1970,'Toyota','Camry','six','automatic','gasoline','Germany',32302,51),
(59,'4SKK5OYB8GNIO244V','truck','compact',1990,'Volkswagen','Beetle','six','manual','gasoline','USA',104520,31),
(60,'9MBPXXXY9DB7AIOSQ','sportscar','compact',1986,'Dodge','Charge','twelve','manual','diesel','China',46327,35),
(61,'0GBI3R8Z4HVQWUUX5','truck','full',1987,'Ford','Mustang','four','manual','gasoline','India',74412,34),
(62,'7QRGJ4BY1MYSMUYIZ','truck','compact',1996,'Dodge','Charge','six','automatic','gasoline','Germany',168550,25),
(63,'8XVJGBG36IQRHW6AJ','crossover','mid',2000,'Mercedes-Benz','C-Class','four','manual','electric','Japan',33830,21),
(64,'1XSO1PDE9KOL0OII7','crossover','compact',1988,'Toyota','Camry','six','automatic','diesel','Japan',53611,33),
(65,'1IJDDDJO5CFN3C6O4','crossover','compact',1994,'Dodge','Charge','four','automatic','gasoline','India',36444,27),
(66,'3CRL23CZ8ZX4TQY2J','sedan','full',2011,'Volkswagen','Beetle','twelve','automatic','hybrid','Japan',135967,10),
(67,'0BBMFO9I7VNSALGZR','sedan','mid',1976,'Ford','Mustang','eight','automatic','electric','China',101040,45),
(68,'0UMHBRUR6OMI72B61','sedan','compact',2000,'Volkswagen','Beetle','four','manual','electric','Japan',62211,21),
(69,'3CVFK4JO0PL4PROSJ','coupe','mid',1976,'Nissan','Altima','twelve','manual','hybrid','Japan',93130,45),
(70,'6YMSA1KX7MUWQ0EPI','crossover','compact',2005,'Jeep','Grand Cherokee','eight','automatic','hybrid','India',153374,16),
(71,'1HP91X8D7VTHH80CY','coupe','mid',1993,'Nissan','Altima','four','manual','hybrid','Japan',56647,28),
(72,'5HT6CJIW9MDUEACOE','coupe','mid',2011,'Chevy','Equinox','six','automatic','hybrid','China',150576,10),
(73,'5FQLVTCA1YK5HBFBX','sedan','compact',1993,'Toyota','Camry','four','automatic','gasoline','China',121808,28),
(74,'2VSCPZCY4BX28BR82','coupe','compact',1996,'Mercedes-Benz','C-Class','twelve','manual','diesel','Mexico',146571,25),
(75,'4CT3Y0E84ZQTYXYY2','sportscar','mid',1970,'Nissan','Altima','eight','automatic','gasoline','China',43746,51),
(76,'9JWZD59P6WQS6INLF','crossover','full',2003,'Chevy','Equinox','four','manual','diesel','USA',32271,18),
(77,'3AIX1GLU3RRS0FLR6','sedan','mid',2020,'Mercedes-Benz','C-Class','six','manual','diesel','USA',169815,1),
(78,'1NBLVGZI3GTECNNMN','sedan','compact',1973,'Volkswagen','Beetle','six','automatic','diesel','Germany',116208,48),
(79,'9TIHJ8Q73INO7N33R','coupe','compact',2007,'Volkswagen','Beetle','eight','automatic','diesel','India',141680,14),
(80,'0QPUGDFB0GQW016H3','coupe','mid',2008,'Jeep','Grand Cherokee','eight','automatic','gasoline','China',31144,13),
(81,'6XODD0H73JEF22BGP','crossover','compact',2007,'Jeep','Grand Cherokee','six','automatic','electric','Germany',86316,14),
(82,'9HES6BKR1HXST3O7Y','sportscar','full',2017,'Ford','Mustang','four','automatic','diesel','Japan',125576,4),
(83,'0YO1XYG46OLA6IESM','sportscar','full',1985,'Mercedes-Benz','C-Class','six','manual','hybrid','China',112890,36),
(84,'9AQV8TZ56NBB9KPAW','hatchback','mid',1994,'Nissan','Altima','six','automatic','hybrid','Japan',23820,27),
(85,'8FK9LP0N1UT9CQABO','coupe','mid',1980,'Dodge','Charge','twelve','manual','diesel','India',160238,41),
(86,'5FHH06C69EQMFBE0X','sedan','mid',2004,'Honda','Civic','eight','automatic','electric','India',26603,17),
(87,'7ZHZKPHP0NTNHSODB','crossover','mid',1998,'Honda','Civic','eight','manual','diesel','Japan',7183,23),
(88,'9BTZT8OZ5TC40FPCG','sedan','mid',1977,'Nissan','Altima','eight','automatic','gasoline','Japan',164693,44),
(89,'6LLFAFJ86RJJDFN53','sedan','full',2005,'Jeep','Grand Cherokee','six','manual','electric','China',115349,16),
(90,'9SUS9E2Z8AGWNPAOM','coupe','mid',2016,'Dodge','Charge','twelve','manual','diesel','USA',136052,5),
(91,'5NEWOULG6HMG0JYW5','sedan','mid',1981,'Honda','Civic','twelve','automatic','gasoline','Mexico',46349,40),
(92,'9WCQ0IDH0YNN0NS5X','sportscar','mid',1993,'Toyota','Camry','six','manual','diesel','USA',77272,28),
(93,'2QRXAW6B8WMEKCLJY','truck','mid',1984,'Volkswagen','Beetle','twelve','manual','hybrid','India',157340,37),
(94,'0LWHXBCD7SM7YCNQF','sedan','compact',1985,'Honda','Civic','four','automatic','electric','Mexico',92685,36),
(95,'7LZ1VY2R0APVUM610','sportscar','compact',2017,'Jeep','Grand Cherokee','six','automatic','gasoline','India',5838,4),
(96,'5WCB1P3Z7XZXFP44U','sportscar','full',2005,'Mercedes-Benz','C-Class','eight','manual','gasoline','India',64540,16),
(97,'2RZR2HC77BC0UQBI8','sportscar','full',1995,'Honda','Civic','four','manual','hybrid','Japan',111465,26),
(98,'9REMVMAC8LA5LZTRU','hatchback','full',1972,'Dodge','Charge','six','manual','electric','Germany',169909,49),
(99,'7MCA10965LCUHP2EB','sportscar','mid',1985,'Volkswagen','Beetle','twelve','automatic','diesel','USA',103944,36),
(100,'7SMUDJJB3TC3E1GVV','crossover','full',1973,'Dodge','Charge','four','automatic','electric','Germany',59705,48);

/*Table structure for table `wishlist` */

DROP TABLE IF EXISTS `wishlist`;

CREATE TABLE `wishlist` (
  `wishlistId` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`wishlistId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `wishlist` */

insert  into `wishlist`(`wishlistId`) values 
(1),
(2),
(3),
(4),
(5),
(6);

/*!50106 set global event_scheduler = 1*/;

/* Event structure for event `clearResetTokens` */

/*!50106 DROP EVENT IF EXISTS `clearResetTokens`*/;

DELIMITER $$

/*!50106 CREATE DEFINER=`cars`@`%` EVENT `clearResetTokens` ON SCHEDULE EVERY 1 DAY STARTS '2021-12-04 01:00:00' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
	    UPDATE `user` SET `resetToken` = NULL WHERE `resetToken` IS NOT NULL;
	END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
