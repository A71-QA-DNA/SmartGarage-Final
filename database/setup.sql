-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.11.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for smart_garage
CREATE DATABASE IF NOT EXISTS `smart_garage` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `smart_garage`;

-- Dumping structure for table smart_garage.avatars
CREATE TABLE IF NOT EXISTS `avatars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.avatars: ~1 rows (approximately)
/*!40000 ALTER TABLE `avatars` DISABLE KEYS */;
INSERT INTO `avatars` (`id`, `avatar_url`) VALUES
	(1, '/images/DefaultUserAvatar.jpg');
/*!40000 ALTER TABLE `avatars` ENABLE KEYS */;

-- Dumping structure for table smart_garage.base_services
CREATE TABLE IF NOT EXISTS `base_services` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.base_services: ~6 rows (approximately)
/*!40000 ALTER TABLE `base_services` DISABLE KEYS */;
INSERT INTO `base_services` (`Id`, `name`) VALUES
	(1, 'Engine Diagnostics'),
	(2, 'Lube, Oil and Filters'),
	(3, 'Belts and Hoses'),
	(4, 'Air Conditioning'),
	(5, 'Brake Repair'),
	(6, 'Tire and Wheel Services');
/*!40000 ALTER TABLE `base_services` ENABLE KEYS */;

-- Dumping structure for table smart_garage.brands
CREATE TABLE IF NOT EXISTS `brands` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.brands: ~3 rows (approximately)
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` (`id`, `brand_name`) VALUES
	(1, 'Audi'),
	(2, 'Porsche'),
	(3, 'Volkswagen');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;

-- Dumping structure for table smart_garage.cars_services
CREATE TABLE IF NOT EXISTS `cars_services` (
  `clients_cars_id` int(11) DEFAULT NULL,
  `service_id` int(11) DEFAULT NULL,
  `service_date` date DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calculated_price` double DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cars_services_pk_2` (`id`),
  KEY `fk_order_id` (`order_id`),
  KEY `clients_cars_id` (`clients_cars_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `cars_services_ibfk_1` FOREIGN KEY (`clients_cars_id`) REFERENCES `clients_cars` (`id`) ON DELETE CASCADE,
  CONSTRAINT `cars_services_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.cars_services: ~25 rows (approximately)
/*!40000 ALTER TABLE `cars_services` DISABLE KEYS */;
INSERT INTO `cars_services` (`clients_cars_id`, `service_id`, `service_date`, `id`, `calculated_price`, `order_id`) VALUES
	(1, 1, '2023-08-01', 1, 100, 1),
	(2, 2, '2023-08-05', 2, 200, 2),
	(3, 3, '2023-08-10', 3, 390, 3),
	(4, 4, '2023-08-15', 4, 120, 4),
	(5, 5, '2023-08-20', 5, 204, 5),
	(6, 6, '2023-08-25', 6, 255, 6),
	(7, 7, '2023-08-30', 7, 50, 7),
	(8, 8, '2023-09-01', 8, 90, 8),
	(9, 9, '2023-09-05', 9, 156, 9),
	(10, 10, '2023-09-10', 10, 105, 10),
	(11, 11, '2023-09-15', 11, 187, 11),
	(12, 12, '2023-09-20', 12, 221, 12),
	(13, 13, '2023-09-25', 13, 80, 13),
	(14, 14, '2023-09-30', 14, 150, 14),
	(15, 15, '2023-10-01', 15, 260, 15),
	(16, 16, '2023-10-05', 16, 375, 16),
	(17, 17, '2023-10-10', 17, 153, 17),
	(18, 18, '2023-10-15', 18, 102, 18),
	(19, 19, '2023-10-20', 19, 70, 19),
	(20, 20, '2023-10-25', 20, 120, 20),
	(21, 21, '2023-11-01', 21, 234, 21),
	(22, 22, '2023-11-05', 22, 150, 22),
	(23, 23, '2023-11-10', 23, 153, 23),
	(24, 24, '2023-11-15', 24, 187, 24),
	(25, 25, '2023-11-20', 25, 120, 25);
/*!40000 ALTER TABLE `cars_services` ENABLE KEYS */;

-- Dumping structure for table smart_garage.clients_cars
CREATE TABLE IF NOT EXISTS `clients_cars` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vin` varchar(50) NOT NULL,
  `plate` varchar(20) NOT NULL,
  `owner` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `plate` (`plate`),
  UNIQUE KEY `vin` (`vin`),
  KEY `owner` (`owner`),
  KEY `vehicle_id` (`vehicle_id`),
  CONSTRAINT `clients_cars_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `clients_cars_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.clients_cars: ~25 rows (approximately)
/*!40000 ALTER TABLE `clients_cars` DISABLE KEYS */;
INSERT INTO `clients_cars` (`id`, `vin`, `plate`, `owner`, `vehicle_id`, `is_deleted`) VALUES
	(1, 'WAUZZZ8P4AA000001', 'A1234BC', 1, 1, 0),
	(2, 'WAUZZZ8P4AA000002', 'B2345CH', 2, 2, 0),
	(3, 'WAUZZZ8P4AA000003', 'CH3456AE', 3, 3, 0),
	(4, 'WAUZZZ8P4AA000004', 'E4567PA', 4, 4, 0),
	(5, 'WAUZZZ8P4AA000005', 'KH5678BH', 5, 5, 0),
	(6, 'WAUZZZ8P4AA000006', 'A6789TM', 6, 6, 0),
	(7, 'WAUZZZ8P4AA000007', 'M7890CO', 7, 7, 0),
	(8, 'WAUZZZ8P4AA000008', 'CH8901BA', 8, 8, 0),
	(9, 'WAUZZZ8P4AA000009', 'K9012KM', 9, 9, 0),
	(10, 'WAUZZZ8P4AA000010', 'P1235AP', 10, 10, 0),
	(11, 'WAUZZZ8P4AA000011', 'CO1234HK', 11, 11, 0),
	(12, 'WAUZZZ8P4AA000012', 'CA345OP', 12, 12, 0),
	(13, 'WAUZZZ8P4AA000013', 'B3456PK', 13, 13, 0),
	(14, 'WAUZZZ8P4AA000014', 'PK4567EH', 14, 14, 0),
	(15, 'WAUZZZ8P4AA000015', 'EH5678MA', 15, 15, 0),
	(16, 'WAUZZZ8P4AA000016', 'TX6789TA', 16, 16, 0),
	(17, 'WAUZZZ8P4AA000017', 'PB7890BB', 17, 17, 0),
	(18, 'WAUZZZ8P4AA000018', 'KH8901AM', 18, 18, 0),
	(19, 'WAUZZZ8P4AA000019', 'PP9012AC', 19, 19, 0),
	(20, 'WAUZZZ8P4AA000020', 'H0123PO', 20, 20, 0),
	(21, 'WAUZZZ8P4AA000021', 'EB1234KK', 1, 21, 0),
	(22, 'WAUZZZ8P4AA000022', 'X2345MM', 2, 22, 0),
	(23, 'WAUZZZ8P4AA000023', 'Y3456CA', 3, 23, 0),
	(24, 'WAUZZZ8P4AA000024', 'CC4567AB', 4, 24, 0),
	(25, 'WAUZZZ8P4AA000025', 'OB5678BC', 5, 25, 0);
/*!40000 ALTER TABLE `clients_cars` ENABLE KEYS */;

-- Dumping structure for table smart_garage.engines
CREATE TABLE IF NOT EXISTS `engines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `engine_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.engines: ~39 rows (approximately)
/*!40000 ALTER TABLE `engines` DISABLE KEYS */;
INSERT INTO `engines` (`id`, `engine_type`) VALUES
	(1, '1.6 TDI'),
	(2, '2.0 TDI'),
	(3, '3.0 TDI'),
	(4, '2.0 TFSI'),
	(5, '3.0 TFSI'),
	(6, '4.0 TFSI'),
	(7, '5.2 FSI V10'),
	(8, '2.0 TSI'),
	(9, '3.0 V6'),
	(10, '4.0 V8'),
	(11, '2.0 Boxer'),
	(12, '3.0 Boxer'),
	(13, '3.6 Boxer'),
	(14, '4.0 Boxer'),
	(15, '4.8 V8'),
	(16, '5.0 V10'),
	(17, '2.0 Hybrid'),
	(18, '3.0 Hybrid'),
	(19, 'Electric'),
	(20, '2.5 TDI'),
	(21, '1.4 TSI'),
	(22, '1.8 TSI'),
	(23, '3.6 V6'),
	(24, '3.8 V8'),
	(25, '1.0 TSI'),
	(26, '2.0 TSI Hybrid'),
	(27, '6.0 W12'),
	(28, '2.7 BiTurbo'),
	(29, '5.0 TFSI V8'),
	(30, '2.9 V6'),
	(31, '4.0 W12'),
	(32, '2.5 Turbo'),
	(33, '2.7 Turbo'),
	(34, '4.5 V8'),
	(35, '1.6 CRDi'),
	(36, '1.4 TDI'),
	(37, '2.0 BiTDI'),
	(38, '1.0 MPI'),
	(39, '1.5 TFSI');
/*!40000 ALTER TABLE `engines` ENABLE KEYS */;

-- Dumping structure for table smart_garage.models
CREATE TABLE IF NOT EXISTS `models` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.models: ~39 rows (approximately)
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` (`id`, `model_name`) VALUES
	(1, 'A4'),
	(2, 'A6'),
	(3, 'Q5'),
	(4, 'Q7'),
	(5, 'TT'),
	(6, 'R8'),
	(7, 'S3'),
	(8, 'S4'),
	(9, 'S5'),
	(10, 'Q3'),
	(11, 'A1'),
	(12, 'Q8'),
	(13, '911'),
	(14, 'Cayenne'),
	(15, 'Macan'),
	(16, 'Panamera'),
	(17, 'Taycan'),
	(18, 'Boxster'),
	(19, 'Cayman'),
	(20, '718'),
	(21, '918 Spyder'),
	(22, 'Carrera'),
	(23, 'Targa'),
	(24, 'GT3'),
	(25, 'Golf'),
	(26, 'Passat'),
	(27, 'Tiguan'),
	(28, 'Touareg'),
	(29, 'Polo'),
	(30, 'Beetle'),
	(31, 'Jetta'),
	(32, 'Arteon'),
	(33, 'ID.4'),
	(34, 'T-Roc'),
	(35, 'Up'),
	(36, 'Scirocco'),
	(37, 'Sharan'),
	(38, 'Transporter'),
	(39, 'Amarok');
/*!40000 ALTER TABLE `models` ENABLE KEYS */;

-- Dumping structure for table smart_garage.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_car_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `order_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_ibfk_1` (`client_car_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`client_car_id`) REFERENCES `clients_cars` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.orders: ~27 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `client_car_id`, `status`, `order_date`) VALUES
	(1, 1, 'IN_PROGRESS', '2023-08-01'),
	(2, 2, 'NOT_STARTED', '2023-08-05'),
	(3, 3, 'READY_FOR_PICKUP', '2023-08-10'),
	(4, 4, 'IN_PROGRESS', '2023-08-15'),
	(5, 5, 'NOT_STARTED', '2023-08-20'),
	(6, 6, 'READY_FOR_PICKUP', '2023-08-25'),
	(7, 7, 'IN_PROGRESS', '2023-08-30'),
	(8, 8, 'NOT_STARTED', '2023-09-01'),
	(9, 9, 'READY_FOR_PICKUP', '2023-09-05'),
	(10, 10, 'IN_PROGRESS', '2023-09-10'),
	(11, 11, 'NOT_STARTED', '2023-09-15'),
	(12, 12, 'READY_FOR_PICKUP', '2023-09-20'),
	(13, 13, 'IN_PROGRESS', '2023-09-25'),
	(14, 14, 'READY_FOR_PICKUP', '2023-09-30'),
	(15, 15, 'READY_FOR_PICKUP', '2023-10-01'),
	(16, 16, 'IN_PROGRESS', '2023-10-05'),
	(17, 17, 'NOT_STARTED', '2023-10-10'),
	(18, 18, 'READY_FOR_PICKUP', '2023-10-15'),
	(19, 19, 'IN_PROGRESS', '2023-10-20'),
	(20, 20, 'READY_FOR_PICKUP', '2023-10-25'),
	(21, 21, 'READY_FOR_PICKUP', '2023-11-01'),
	(22, 22, 'IN_PROGRESS', '2023-11-05'),
	(23, 23, 'NOT_STARTED', '2023-11-10'),
	(24, 24, 'READY_FOR_PICKUP', '2023-11-15'),
	(25, 25, 'IN_PROGRESS', '2023-11-20'),
	(26, 20, 'NOT_STARTED', '2023-11-20'),
	(27, 14, 'NOT_STARTED', '2023-11-20');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table smart_garage.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.roles: ~3 rows (approximately)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role_name`) VALUES
	(1, 'Customer'),
	(2, 'Employee'),
	(3, 'Mechanic');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table smart_garage.services
CREATE TABLE IF NOT EXISTS `services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `base_service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_base_service` (`base_service_id`),
  CONSTRAINT `fk_base_service` FOREIGN KEY (`base_service_id`) REFERENCES `base_services` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.services: ~36 rows (approximately)
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` (`id`, `name`, `price`, `is_deleted`, `base_service_id`) VALUES
	(1, 'Basic Engine Diagnostics', 100.00, 0, 1),
	(2, 'Advanced Engine Diagnostics', 200.00, 0, 1),
	(3, 'Complete Engine Diagnostics', 300.00, 0, 1),
	(4, 'Quick Engine Check', 80.00, 0, 1),
	(5, 'Engine Fault Finding', 120.00, 0, 1),
	(6, 'Engine Performance Test', 150.00, 0, 1),
	(7, 'Basic Lube, Oil and Filters', 50.00, 0, 2),
	(8, 'Advanced Lube, Oil and Filters', 90.00, 0, 2),
	(9, 'Premium Lube Service', 120.00, 0, 2),
	(10, 'Oil Change and Inspection', 70.00, 0, 2),
	(11, 'Synthetic Oil Change', 110.00, 0, 2),
	(12, 'Complete Lube Service', 130.00, 0, 2),
	(13, 'Basic Belts and Hoses Replacement', 80.00, 0, 3),
	(14, 'Advanced Belts and Hoses Replacement', 150.00, 0, 3),
	(15, 'Complete Belts and Hoses Service', 200.00, 0, 3),
	(16, 'Timing Belt Replacement', 250.00, 0, 3),
	(17, 'Accessory Belt Replacement', 90.00, 0, 3),
	(18, 'Hose Leak Repair', 60.00, 0, 3),
	(19, 'Basic Air Conditioning Service', 70.00, 0, 4),
	(20, 'Advanced Air Conditioning Service', 120.00, 0, 4),
	(21, 'Complete Air Conditioning Service', 180.00, 0, 4),
	(22, 'AC System Recharge', 100.00, 0, 4),
	(23, 'AC Filter Replacement', 90.00, 0, 4),
	(24, 'AC Leak Repair', 110.00, 0, 4),
	(25, 'Basic Brake Repair', 120.00, 0, 5),
	(26, 'Advanced Brake Repair', 180.00, 0, 5),
	(27, 'Complete Brake Service', 240.00, 0, 5),
	(28, 'Brake Pad Replacement', 140.00, 0, 5),
	(29, 'Brake Fluid Change', 90.00, 0, 5),
	(30, 'Brake System Inspection', 70.00, 0, 5),
	(31, 'Basic Tire and Wheel Service', 60.00, 0, 6),
	(32, 'Advanced Tire and Wheel Service', 100.00, 0, 6),
	(33, 'Complete Tire and Wheel Service', 150.00, 0, 6),
	(34, 'Tire Rotation and Balancing', 80.00, 0, 6),
	(35, 'Wheel Alignment', 110.00, 0, 6),
	(36, 'Tire Replacement', 130.00, 0, 6);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;

-- Dumping structure for table smart_garage.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `phone_number` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `users_pk` (`phone_number`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.users: ~22 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `first_name`, `last_name`, `email`, `password`, `role_id`, `phone_number`) VALUES
	(1, 'alex_rider', 'Alex', 'Rider', 'alex.rider@gmail.com', 'password123%D', 1, '0877000001'),
	(2, 'bella_harper', 'Bella', 'Harper', 'bella.harper@yahoo.com', 'password123%D', 1, '0877000002'),
	(3, 'chris_oakley', 'Chris', 'Oakley', 'chris.oakley@abv.bg', 'password123%D', 1, '0877000003'),
	(4, 'diana_smith', 'Diana', 'Smith', 'diana.smith@gmail.com', 'password123%D', 1, '0877000004'),
	(5, 'ethan_jones', 'Ethan', 'Jones', 'ethan.jones@yahoo.com', 'password123%D', 1, '0877000005'),
	(6, 'felix_jackson', 'Felix', 'Jackson', 'felix.jackson@abv.bg', 'password123%D', 2, '0877000006'),
	(7, 'george_hill', 'George', 'Hill', 'george.hill@gmail.com', 'password123%D', 2, '0877000007'),
	(8, 'hannah_white', 'Hannah', 'White', 'hannah.white@yahoo.com', 'password123%D', 2, '0877000008'),
	(9, 'ian_black', 'Ian', 'Black', 'ian.black@abv.bg', 'password123%D', 2, '0877000009'),
	(10, 'julia_grey', 'Julia', 'Grey', 'julia.grey@gmail.com', 'password123%D', 3, '0877000010'),
	(11, 'kevin_brown', 'Kevin', 'Brown', 'kevin.brown@yahoo.com', 'password123%D', 3, '0877000011'),
	(12, 'lucas_morgan', 'Lucas', 'Morgan', 'lucas.morgan@abv.bg', 'password123%D', 3, '0877000012'),
	(13, 'mia_clark', 'Mia', 'Clark', 'mia.clark@gmail.com', 'password123%D', 1, '0877000013'),
	(14, 'noah_lee', 'Noah', 'Lee', 'noah.lee@yahoo.com', 'password123%D', 1, '0877000014'),
	(15, 'oliver_wilson', 'Oliver', 'Wilson', 'oliver.wilson@abv.bg', 'password123%D', 1, '0877000015'),
	(16, 'peter_moore', 'Peter', 'Moore', 'peter.moore@gmail.com', 'password123%D', 1, '0877000016'),
	(17, 'quinn_walker', 'Quinn', 'Walker', 'quinn.walker@yahoo.com', 'password123%D', 1, '0877000017'),
	(18, 'rachel_smith', 'Rachel', 'Smith', 'rachel.smith@abv.bg', 'password123%D', 1, '0877000018'),
	(19, 'sophia_thomas', 'Sophia', 'Thomas', 'sophia.thomas@gmail.com', 'password123%D', 1, '0877000019'),
	(20, 'tyler_king', 'Tyler', 'King', 'tyler.king@yahoo.com', 'password123%D', 1, '0877000020'),
	(21, 'ilian19', 'Ilian', 'Karagyozov', 'ikaragyozov19@gmail.com', 'password123%D', 2, '0877000021'),
	(22, 'Annihilati0N', 'Todor', 'Raynov', 't.raynov@abv.bg', 'password123%D', 2, '0877000022');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table smart_garage.users_avatars
CREATE TABLE IF NOT EXISTS `users_avatars` (
  `user_id` int(11) NOT NULL,
  `avatar_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`avatar_id`),
  KEY `avatar_id` (`avatar_id`),
  CONSTRAINT `users_avatars_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `users_avatars_ibfk_2` FOREIGN KEY (`avatar_id`) REFERENCES `avatars` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.users_avatars: ~22 rows (approximately)
/*!40000 ALTER TABLE `users_avatars` DISABLE KEYS */;
INSERT INTO `users_avatars` (`user_id`, `avatar_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 1),
	(13, 1),
	(14, 1),
	(15, 1),
	(16, 1),
	(17, 1),
	(18, 1),
	(19, 1),
	(20, 1),
	(21, 1),
	(22, 1);
/*!40000 ALTER TABLE `users_avatars` ENABLE KEYS */;

-- Dumping structure for table smart_garage.vehicles
CREATE TABLE IF NOT EXISTS `vehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_id` int(200) DEFAULT NULL,
  `model_id` int(200) DEFAULT NULL,
  `year_id` int(200) DEFAULT NULL,
  `engine_type_id` int(200) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `brand_id` (`brand_id`),
  KEY `engine_type_id` (`engine_type_id`),
  KEY `model_id` (`model_id`),
  KEY `year_id` (`year_id`),
  CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  CONSTRAINT `vehicles_ibfk_2` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`),
  CONSTRAINT `vehicles_ibfk_3` FOREIGN KEY (`year_id`) REFERENCES `years` (`id`),
  CONSTRAINT `vehicles_ibfk_4` FOREIGN KEY (`engine_type_id`) REFERENCES `engines` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.vehicles: ~30 rows (approximately)
/*!40000 ALTER TABLE `vehicles` DISABLE KEYS */;
INSERT INTO `vehicles` (`id`, `brand_id`, `model_id`, `year_id`, `engine_type_id`, `is_deleted`) VALUES
	(1, 1, 1, 16, 2, 0),
	(2, 1, 3, 25, 9, 0),
	(3, 1, 4, 18, 5, 0),
	(4, 1, 2, 22, 3, 0),
	(5, 1, 10, 14, 8, 0),
	(6, 1, 8, 28, 13, 0),
	(7, 1, 9, 19, 12, 0),
	(8, 1, 5, 23, 6, 0),
	(9, 2, 13, 26, 14, 0),
	(10, 2, 14, 24, 15, 0),
	(11, 2, 16, 20, 16, 0),
	(12, 2, 15, 17, 20, 0),
	(13, 2, 17, 29, 11, 0),
	(14, 2, 19, 12, 19, 0),
	(15, 2, 18, 11, 14, 0),
	(16, 2, 20, 27, 18, 0),
	(17, 2, 22, 30, 17, 0),
	(18, 2, 24, 21, 10, 0),
	(19, 3, 25, 13, 21, 0),
	(20, 3, 27, 6, 24, 0),
	(21, 3, 26, 4, 25, 0),
	(22, 3, 28, 8, 22, 0),
	(23, 3, 30, 2, 1, 0),
	(24, 3, 29, 9, 7, 0),
	(25, 3, 31, 15, 23, 0),
	(26, 3, 33, 29, 32, 0),
	(27, 3, 34, 5, 35, 0),
	(28, 3, 32, 10, 38, 0),
	(29, 3, 35, 3, 39, 0),
	(30, 3, 36, 1, 34, 0);
/*!40000 ALTER TABLE `vehicles` ENABLE KEYS */;

-- Dumping structure for table smart_garage.years
CREATE TABLE IF NOT EXISTS `years` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- Dumping data for table smart_garage.years: ~30 rows (approximately)
/*!40000 ALTER TABLE `years` DISABLE KEYS */;
INSERT INTO `years` (`id`, `year`) VALUES
	(1, 1995),
	(2, 1996),
	(3, 1997),
	(4, 1998),
	(5, 1999),
	(6, 2000),
	(7, 2001),
	(8, 2002),
	(9, 2003),
	(10, 2004),
	(11, 2005),
	(12, 2006),
	(13, 2007),
	(14, 2008),
	(15, 2009),
	(16, 2010),
	(17, 2011),
	(18, 2012),
	(19, 2013),
	(20, 2014),
	(21, 2015),
	(22, 2016),
	(23, 2017),
	(24, 2018),
	(25, 2019),
	(26, 2020),
	(27, 2021),
	(28, 2022),
	(29, 2023),
	(30, 2024);
/*!40000 ALTER TABLE `years` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
