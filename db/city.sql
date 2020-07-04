-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 03, 2020 at 05:49 PM
-- Server version: 5.7.26-1+b1
-- PHP Version: 7.3.14-1~deb10u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `city`
--
DROP DATABASE IF EXISTS city;
CREATE DATABASE IF NOT EXISTS `city` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `city`;

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE `building` (
  `id_bui` int(11) NOT NULL,
  `type_bu` int(11) DEFAULT NULL,
  `happiness` int(11) DEFAULT NULL,
  `workers` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `citizen`
--

CREATE TABLE `citizen` (
  `id_cit` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `id_fam` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `family`
--

CREATE TABLE `family` (
  `id_fam` int(11) NOT NULL,
  `id_hou` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `house`
--

CREATE TABLE `house` (
  `id_hou` int(11) NOT NULL,
  `rent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `leisure`
--

CREATE TABLE `leisure` (
  `id_lei` int(11) NOT NULL,
  `type_le` int(11) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lot`
--

CREATE TABLE `lot` (
  `id_lot` int(11) NOT NULL,
  `coor_x` int(11) DEFAULT NULL,
  `coor_y` int(11) DEFAULT NULL,
  `type_lo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `open`
--

CREATE TABLE `open` (
  `id_wor` int(11) NOT NULL,
  `id_bui` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `park`
--

CREATE TABLE `park` (
  `id_par` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `road`
--

CREATE TABLE `road` (
  `id_roa` int(11) NOT NULL,
  `dir` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `school`
--

CREATE TABLE `school` (
  `id_sch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id_sho` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `study`
--

CREATE TABLE `study` (
  `id_stud` int(11) NOT NULL,
  `id_cit` int(11) DEFAULT NULL,
  `id_sch` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `visit`
--

CREATE TABLE `visit` (
  `id_vis` int(11) NOT NULL,
  `id_cit` int(11) DEFAULT NULL,
  `id_lei` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `spent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE `work` (
  `id_wor` int(11) NOT NULL,
  `id_cit` int(11) DEFAULT NULL,
  `id_bui` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `workday`
--

CREATE TABLE `workday` (
  `id_wor` int(11) NOT NULL,
  `name` varchar(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `workday`
--

INSERT INTO `workday` (`id_wor`, `name`) VALUES
(1, 'Monday'),
(2, 'Tuesday'),
(3, 'Wednesday'),
(4, 'Thursday'),
(5, 'Friday'),
(6, 'Saturday'),
(7, 'Sunday');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`id_bui`);

--
-- Indexes for table `citizen`
--
ALTER TABLE `citizen`
  ADD PRIMARY KEY (`id_cit`),
  ADD KEY `id_fam` (`id_fam`);

--
-- Indexes for table `family`
--
ALTER TABLE `family`
  ADD PRIMARY KEY (`id_fam`),
  ADD KEY `id_hou` (`id_hou`);

--
-- Indexes for table `house`
--
ALTER TABLE `house`
  ADD PRIMARY KEY (`id_hou`);

--
-- Indexes for table `leisure`
--
ALTER TABLE `leisure`
  ADD PRIMARY KEY (`id_lei`);

--
-- Indexes for table `lot`
--
ALTER TABLE `lot`
  ADD PRIMARY KEY (`id_lot`);

--
-- Indexes for table `open`
--
ALTER TABLE `open`
  ADD PRIMARY KEY (`id_wor`,`id_bui`),
  ADD KEY `id_bui` (`id_bui`);

--
-- Indexes for table `park`
--
ALTER TABLE `park`
  ADD PRIMARY KEY (`id_par`);

--
-- Indexes for table `road`
--
ALTER TABLE `road`
  ADD PRIMARY KEY (`id_roa`);

--
-- Indexes for table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id_sch`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id_sho`);

--
-- Indexes for table `study`
--
ALTER TABLE `study`
  ADD PRIMARY KEY (`id_stud`),
  ADD KEY `id_cit` (`id_cit`),
  ADD KEY `id_sch` (`id_sch`);

--
-- Indexes for table `visit`
--
ALTER TABLE `visit`
  ADD PRIMARY KEY (`id_vis`),
  ADD KEY `id_cit` (`id_cit`),
  ADD KEY `id_lei` (`id_lei`);

--
-- Indexes for table `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`id_wor`),
  ADD KEY `id_cit` (`id_cit`),
  ADD KEY `id_bui` (`id_bui`);

--
-- Indexes for table `workday`
--
ALTER TABLE `workday`
  ADD PRIMARY KEY (`id_wor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `citizen`
--
ALTER TABLE `citizen`
  MODIFY `id_cit` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `family`
--
ALTER TABLE `family`
  MODIFY `id_fam` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lot`
--
ALTER TABLE `lot`
  MODIFY `id_lot` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `study`
--
ALTER TABLE `study`
  MODIFY `id_stud` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `visit`
--
ALTER TABLE `visit`
  MODIFY `id_vis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `work`
--
ALTER TABLE `work`
  MODIFY `id_wor` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `building_ibfk_1` FOREIGN KEY (`id_bui`) REFERENCES `lot` (`id_lot`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `citizen`
--
ALTER TABLE `citizen`
  ADD CONSTRAINT `citizen_ibfk_1` FOREIGN KEY (`id_fam`) REFERENCES `family` (`id_fam`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `family`
--
ALTER TABLE `family`
  ADD CONSTRAINT `family_ibfk_1` FOREIGN KEY (`id_hou`) REFERENCES `house` (`id_hou`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `house`
--
ALTER TABLE `house`
  ADD CONSTRAINT `house_ibfk_1` FOREIGN KEY (`id_hou`) REFERENCES `building` (`id_bui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `leisure`
--
ALTER TABLE `leisure`
  ADD CONSTRAINT `leisure_ibfk_1` FOREIGN KEY (`id_lei`) REFERENCES `building` (`id_bui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `open`
--
ALTER TABLE `open`
  ADD CONSTRAINT `open_ibfk_1` FOREIGN KEY (`id_wor`) REFERENCES `workday` (`id_wor`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `open_ibfk_2` FOREIGN KEY (`id_bui`) REFERENCES `building` (`id_bui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `park`
--
ALTER TABLE `park`
  ADD CONSTRAINT `park_ibfk_1` FOREIGN KEY (`id_par`) REFERENCES `leisure` (`id_lei`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `road`
--
ALTER TABLE `road`
  ADD CONSTRAINT `road_ibfk_1` FOREIGN KEY (`id_roa`) REFERENCES `lot` (`id_lot`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `school_ibfk_1` FOREIGN KEY (`id_sch`) REFERENCES `building` (`id_bui`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `shop`
--
ALTER TABLE `shop`
  ADD CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`id_sho`) REFERENCES `leisure` (`id_lei`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `study`
--
ALTER TABLE `study`
  ADD CONSTRAINT `study_ibfk_1` FOREIGN KEY (`id_cit`) REFERENCES `citizen` (`id_cit`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `study_ibfk_2` FOREIGN KEY (`id_sch`) REFERENCES `school` (`id_sch`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `visit`
--
ALTER TABLE `visit`
  ADD CONSTRAINT `visit_ibfk_1` FOREIGN KEY (`id_cit`) REFERENCES `citizen` (`id_cit`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `visit_ibfk_2` FOREIGN KEY (`id_lei`) REFERENCES `leisure` (`id_lei`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `work`
--
ALTER TABLE `work`
  ADD CONSTRAINT `work_ibfk_1` FOREIGN KEY (`id_cit`) REFERENCES `citizen` (`id_cit`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `work_ibfk_2` FOREIGN KEY (`id_bui`) REFERENCES `building` (`id_bui`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
