-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 28, 2023 at 11:10 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `light_rh`
--

-- --------------------------------------------------------

--
-- Table structure for table `absence`
--

CREATE TABLE `absence` (
  `id` int(11) NOT NULL,
  `date_start` datetime(6) DEFAULT NULL,
  `date_end` datetime(6) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `status` enum('EN_ATTENTE_VALIDATION','INITIALE','REJETEE','VALIDEE') DEFAULT NULL,
  `type` enum('EMPLOYEE_RTT','EMPLOYER_RTT','HOLIDAY','PAID_LEAVE','UNPAID_LEAVE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `absence`
--

INSERT INTO `absence` (`id`, `date_start`, `date_end`, `motif`, `label`, `employee_id`, `status`, `type`) VALUES
(1, '2023-07-26 16:24:38.000000', '2023-07-27 16:24:55.000000', '\"Je pars\"', '\"Congé\"', 1, 'INITIALE', 'EMPLOYEE_RTT'),
(2, '2023-07-29 16:24:38.000000', '2023-07-28 16:24:55.000000', '\"Je pars\"', '\"Congé\"', 1, 'INITIALE', 'EMPLOYER_RTT'),
(3, '2023-07-29 16:24:38.000000', '2023-07-30 16:24:55.000000', '\"Je pars\"', '\"Congé\"', 2, 'REJETEE', 'HOLIDAY'),
(5, '2023-07-25 16:24:38.000000', '2023-07-30 16:24:55.000000', '\"Je pars\"', '\"Congé\"', 2, 'INITIALE', 'PAID_LEAVE'),
(8, '2023-07-28 16:24:38.000000', '2023-07-30 16:24:55.000000', '\"Je pars\"', '\"Congé\"', 2, 'VALIDEE', 'UNPAID_LEAVE'),
(10, '2023-07-28 16:24:38.000000', '2023-07-30 16:24:55.000000', 'RTT', 'Congé personnel', 4, 'VALIDEE', 'EMPLOYEE_RTT'),
(11, '2023-07-05 16:24:38.000000', '2023-07-05 16:24:55.000000', 'Férié', 'Congé imposé', 4, 'VALIDEE', 'HOLIDAY'),
(12, '2023-07-08 16:24:38.000000', '2023-07-15 16:24:55.000000', 'vacances', 'Congé personnel', 4, 'VALIDEE', 'PAID_LEAVE'),
(13, '2023-08-02 16:24:38.000000', '2023-08-03 16:24:55.000000', 'RTT imposée', 'Congé imposé', 4, 'VALIDEE', 'EMPLOYER_RTT'),
(14, '2023-07-05 16:24:38.000000', '2023-07-15 16:24:55.000000', NULL, 'Congé personnel', 4, 'REJETEE', 'PAID_LEAVE'),
(15, '2023-08-16 16:24:38.000000', '2023-08-17 16:24:55.000000', 'vacances', 'Congé personnel', 4, 'EN_ATTENTE_VALIDATION', 'PAID_LEAVE');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `is_manager` bit(1) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token_validity` binary(16) DEFAULT NULL,
  `rtt` int(11) DEFAULT NULL,
  `vacation` int(11) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `department`, `email`, `firstname`, `is_active`, `is_admin`, `is_manager`, `lastname`, `password`, `token_validity`, `rtt`, `vacation`, `manager_id`) VALUES
(1, NULL, 'martin.chaillet@gmail.com', 'Martin', b'1', b'1', b'1', 'CHAILLET', '$2a$10$yuG3mspmya3r6tLB9ft3XutVnLhhmBRUXnaZYADDIBUqMdXnQR6yG', 0x191e21b7538e4f8ab3737298d3216461, NULL, NULL, NULL),
(2, '', 'toto@tata.com', 'toto', b'1', b'1', b'1', 'tata', '98203dc3-11f2-4930-b8a9-7d72964a4cd4', 0xf71d6c43ee08495f8dcecb6710e93c4c, NULL, NULL, NULL),
(3, NULL, 'admin@test.diginamic', 'AdminTest', b'1', b'1', b'0', '', '$2a$10$yuG3mspmya3r6tLB9ft3XutVnLhhmBRUXnaZYADDIBUqMdXnQR6yG', 0x191e21b7538e4f8ab3737298d3216461, NULL, NULL, NULL),
(4, NULL, 'user@test.diginamic', 'UserTest', b'1', b'0', b'0', '', '$2a$10$yuG3mspmya3r6tLB9ft3XutVnLhhmBRUXnaZYADDIBUqMdXnQR6yG', 0x191e21b7538e4f8ab3737298d3216461, NULL, NULL, NULL),
(5, NULL, 'manager@test.diginamic', 'ManagerTest', b'1', b'0', b'1', '', '$2a$10$yuG3mspmya3r6tLB9ft3XutVnLhhmBRUXnaZYADDIBUqMdXnQR6yG', 0x191e21b7538e4f8ab3737298d3216461, NULL, NULL, NULL),
(6, NULL, 'adminmanager@test.diginamic', 'AdminManagerTest', b'1', b'1', b'1', '', '$2a$10$yuG3mspmya3r6tLB9ft3XutVnLhhmBRUXnaZYADDIBUqMdXnQR6yG', 0x191e21b7538e4f8ab3737298d3216461, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `employerrtt`
--

CREATE TABLE `employerrtt` (
  `id` int(11) NOT NULL,
  `remaining_days` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employerrtt`
--

INSERT INTO `employerrtt` (`id`, `remaining_days`) VALUES
(1, 5),
(2, 5);

-- --------------------------------------------------------

--
-- Table structure for table `publichollidays`
--

CREATE TABLE `publichollidays` (
  `id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `label` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absence`
--
ALTER TABLE `absence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs69fc883x11wl5lkx9vjhf5ym` (`employee_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKou6wbxug1d0qf9mabut3xqblo` (`manager_id`);

--
-- Indexes for table `employerrtt`
--
ALTER TABLE `employerrtt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `publichollidays`
--
ALTER TABLE `publichollidays`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absence`
--
ALTER TABLE `absence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `employerrtt`
--
ALTER TABLE `employerrtt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `publichollidays`
--
ALTER TABLE `publichollidays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `absence`
--
ALTER TABLE `absence`
  ADD CONSTRAINT `FKs69fc883x11wl5lkx9vjhf5ym` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKou6wbxug1d0qf9mabut3xqblo` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
