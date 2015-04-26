-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2015 at 10:39 AM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rmu`
--

-- --------------------------------------------------------

--
-- Table structure for table `master_carriage`
--

CREATE TABLE IF NOT EXISTS `master_carriage` (
`carriage_id` bigint(20) NOT NULL,
  `carriage_code` varchar(50) DEFAULT NULL,
  `carriage_no` varchar(2) DEFAULT NULL,
  `carriage_status` smallint(6) DEFAULT NULL,
  `carriage_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `master_carriage`
--

INSERT INTO `master_carriage` (`carriage_id`, `carriage_code`, `carriage_no`, `carriage_status`, `carriage_remarks`) VALUES
(1, 'A', '01', 1, 'Available'),
(2, 'B', '02', 1, 'Available'),
(3, 'C', '03', 1, 'Available'),
(4, 'D', '04', 1, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `master_menu`
--

CREATE TABLE IF NOT EXISTS `master_menu` (
`menu_id` bigint(20) NOT NULL,
  `menu_code` varchar(50) DEFAULT NULL,
  `menu_name` varchar(100) DEFAULT NULL,
  `menu_type` smallint(6) DEFAULT NULL,
  `menu_price` decimal(10,2) DEFAULT NULL,
  `menu_image_url` varchar(250) DEFAULT NULL,
  `menu_status` smallint(6) DEFAULT NULL,
  `menu_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `master_menu`
--

INSERT INTO `master_menu` (`menu_id`, `menu_code`, `menu_name`, `menu_type`, `menu_price`, `menu_image_url`, `menu_status`, `menu_remarks`) VALUES
(1, 'NG', 'Nasi Goreng', 1, '10000.00', 'http://localhost/', 1, 'Available'),
(2, 'MA', 'Mie Ayam', 1, '15000.00', 'http://localhost/', 1, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `master_seat`
--

CREATE TABLE IF NOT EXISTS `master_seat` (
`seat_id` bigint(20) NOT NULL,
  `seat_code` varchar(50) DEFAULT NULL,
  `seat_no` varchar(3) DEFAULT NULL,
  `seat_status` smallint(6) DEFAULT NULL,
  `seat_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `master_seat`
--

INSERT INTO `master_seat` (`seat_id`, `seat_code`, `seat_no`, `seat_status`, `seat_remarks`) VALUES
(1, 'A01', 'A01', 1, 'Available'),
(2, 'A02', 'A02', 1, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `master_train`
--

CREATE TABLE IF NOT EXISTS `master_train` (
`train_id` bigint(20) NOT NULL,
  `train_code` varchar(50) DEFAULT NULL,
  `train_no` varchar(5) DEFAULT NULL,
  `train_status` smallint(6) DEFAULT NULL,
  `train_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `master_train`
--

INSERT INTO `master_train` (`train_id`, `train_code`, `train_no`, `train_status`, `train_remarks`) VALUES
(1, '1234', '1', 1, 'bengawan solo'),
(2, '1235', '2', 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `security_role`
--

CREATE TABLE IF NOT EXISTS `security_role` (
`role_id` bigint(20) NOT NULL,
  `role_code` varchar(50) DEFAULT NULL,
  `role_status` smallint(6) DEFAULT NULL,
  `role_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `security_role`
--

INSERT INTO `security_role` (`role_id`, `role_code`, `role_status`, `role_remarks`) VALUES
(1, 'ROLE_REST_HTTP_USER', 1, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `security_user`
--

CREATE TABLE IF NOT EXISTS `security_user` (
`user_id` bigint(20) NOT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_enabled` smallint(6) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `user_status` smallint(6) DEFAULT NULL,
  `user_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `security_user`
--

INSERT INTO `security_user` (`user_id`, `user_code`, `user_password`, `user_enabled`, `role_id`, `user_status`, `user_remarks`) VALUES
(1, 'SPRING', 'gI/OZ3yejUtSxsF9XySg5TfFJFo=', 1, 1, 0, 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `trx_order_detail`
--

CREATE TABLE IF NOT EXISTS `trx_order_detail` (
`order_detail_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  `order_detail_total_order` smallint(6) DEFAULT NULL,
  `order_detail_total_amount` decimal(10,2) DEFAULT NULL,
  `order_header_no` varchar(100) DEFAULT NULL,
  `order_detail_status` smallint(6) DEFAULT NULL,
  `order_detail_remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `trx_order_detail`
--

INSERT INTO `trx_order_detail` (`order_detail_id`, `menu_id`, `order_detail_total_order`, `order_detail_total_amount`, `order_header_no`, `order_detail_status`, `order_detail_remarks`) VALUES
(1, 1, 1, '10000.00', 'OR0001', 1, 'Order'),
(2, 2, 1, '15000.00', 'OR0001', 1, 'Order'),
(3, 2, 1, '15000.00', 'OR0002', 1, 'Order');

-- --------------------------------------------------------

--
-- Table structure for table `trx_order_header`
--

CREATE TABLE IF NOT EXISTS `trx_order_header` (
  `order_header_no` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `train_id` bigint(20) NOT NULL,
  `carriage_id` bigint(20) NOT NULL,
  `seat_id` bigint(20) NOT NULL,
  `order_header_order_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `order_header_total_paid` decimal(20,2) DEFAULT NULL,
  `order_header_status` smallint(6) DEFAULT NULL,
  `order_header_remarks` varchar(255) DEFAULT NULL,
  `order_header_is_archive` smallint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `trx_order_header`
--

INSERT INTO `trx_order_header` (`order_header_no`, `user_id`, `train_id`, `carriage_id`, `seat_id`, `order_header_order_datetime`, `order_header_total_paid`, `order_header_status`, `order_header_remarks`, `order_header_is_archive`) VALUES
('OR0001', 1, 1, 1, 1, '2015-04-17 18:37:11', '25000.00', 1, 'Order', 0),
('OR0002', 1, 2, 2, 2, '2015-04-17 18:37:19', '15000.00', 1, 'Order', 0);

-- --------------------------------------------------------

--
-- Table structure for table `trx_stan`
--

CREATE TABLE IF NOT EXISTS `trx_stan` (
`stan_id` bigint(20) NOT NULL,
  `stan_counter` bigint(20) NOT NULL,
  `stan_max` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `trx_stan`
--

INSERT INTO `trx_stan` (`stan_id`, `stan_counter`, `stan_max`, `user_id`) VALUES
(1, 1, 99999, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `master_carriage`
--
ALTER TABLE `master_carriage`
 ADD PRIMARY KEY (`carriage_id`);

--
-- Indexes for table `master_menu`
--
ALTER TABLE `master_menu`
 ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `master_seat`
--
ALTER TABLE `master_seat`
 ADD PRIMARY KEY (`seat_id`);

--
-- Indexes for table `master_train`
--
ALTER TABLE `master_train`
 ADD PRIMARY KEY (`train_id`);

--
-- Indexes for table `security_role`
--
ALTER TABLE `security_role`
 ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `security_user`
--
ALTER TABLE `security_user`
 ADD PRIMARY KEY (`user_id`), ADD KEY `role_id` (`role_id`);

--
-- Indexes for table `trx_order_detail`
--
ALTER TABLE `trx_order_detail`
 ADD PRIMARY KEY (`order_detail_id`), ADD KEY `menu_id` (`menu_id`), ADD KEY `order_header_no` (`order_header_no`);

--
-- Indexes for table `trx_order_header`
--
ALTER TABLE `trx_order_header`
 ADD PRIMARY KEY (`order_header_no`), ADD KEY `user_id` (`user_id`), ADD KEY `carriage_id` (`carriage_id`), ADD KEY `seat_id` (`seat_id`), ADD KEY `train_id` (`train_id`);

--
-- Indexes for table `trx_stan`
--
ALTER TABLE `trx_stan`
 ADD PRIMARY KEY (`stan_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `master_carriage`
--
ALTER TABLE `master_carriage`
MODIFY `carriage_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `master_menu`
--
ALTER TABLE `master_menu`
MODIFY `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `master_seat`
--
ALTER TABLE `master_seat`
MODIFY `seat_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `master_train`
--
ALTER TABLE `master_train`
MODIFY `train_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `security_role`
--
ALTER TABLE `security_role`
MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `security_user`
--
ALTER TABLE `security_user`
MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `trx_order_detail`
--
ALTER TABLE `trx_order_detail`
MODIFY `order_detail_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `trx_stan`
--
ALTER TABLE `trx_stan`
MODIFY `stan_id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `security_user`
--
ALTER TABLE `security_user`
ADD CONSTRAINT `security_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`role_id`);

--
-- Constraints for table `trx_order_detail`
--
ALTER TABLE `trx_order_detail`
ADD CONSTRAINT `trx_order_detail_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `master_menu` (`menu_id`),
ADD CONSTRAINT `trx_order_detail_ibfk_2` FOREIGN KEY (`order_header_no`) REFERENCES `trx_order_header` (`order_header_no`);

--
-- Constraints for table `trx_order_header`
--
ALTER TABLE `trx_order_header`
ADD CONSTRAINT `trx_order_header_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`user_id`),
ADD CONSTRAINT `trx_order_header_ibfk_2` FOREIGN KEY (`carriage_id`) REFERENCES `master_carriage` (`carriage_id`),
ADD CONSTRAINT `trx_order_header_ibfk_3` FOREIGN KEY (`seat_id`) REFERENCES `master_seat` (`seat_id`),
ADD CONSTRAINT `trx_order_header_ibfk_4` FOREIGN KEY (`train_id`) REFERENCES `master_train` (`train_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
