-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2026 at 02:27 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jollibee_menu`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_users`
--

CREATE TABLE `admin_users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_users`
--

INSERT INTO `admin_users` (`id`, `username`, `password`, `full_name`, `created_at`) VALUES
(1, 'admin', 'jollibee123', 'Administrator', '2026-03-22 10:13:48');

-- --------------------------------------------------------

--
-- Table structure for table `menu_items`
--

CREATE TABLE `menu_items` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `category` varchar(50) NOT NULL,
  `description` text NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `available` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu_items`
--

INSERT INTO `menu_items` (`id`, `name`, `price`, `category`, `description`, `image_path`, `is_available`, `created_at`, `available`) VALUES
(38, 'Chickenjoy', 89.00, 'Burgers', 'Signature chicken burger', 'D:\\JBFCF-f8098eac.png', 1, '2026-03-22 10:16:05', 1),
(39, 'Yumburger', 50.00, 'Burger', 'Classic beef burger', NULL, 1, '2026-03-22 10:16:05', 1),
(40, 'Cheese Yumburger', 59.00, 'Burger', 'Burger with cheese', NULL, 1, '2026-03-22 10:16:05', 1),
(41, 'Champ', 109.00, 'Burger', 'Premium beef burger', NULL, 1, '2026-03-22 10:16:05', 1),
(42, 'Big Yum', 89.00, 'Burger', 'Double patty burger', NULL, 1, '2026-03-22 10:16:05', 1),
(43, '1pc Chickenjoy', 99.00, 'Chicken', 'Original fried chicken', NULL, 1, '2026-03-22 10:16:05', 1),
(44, '2pc Chickenjoy', 185.00, 'Chicken', 'Two pieces fried chicken', NULL, 1, '2026-03-22 10:16:05', 1),
(45, '3pc Chickenjoy', 269.00, 'Chicken', 'Three pieces fried chicken', NULL, 1, '2026-03-22 10:16:05', 1),
(46, 'Spicy Chickenjoy', 109.00, 'Chicken', 'Spicy fried chicken', NULL, 1, '2026-03-22 10:16:05', 1),
(47, 'Jolly Spaghetti', 69.00, 'Spaghetti', 'Sweet spaghetti', NULL, 1, '2026-03-22 10:16:05', 1),
(48, 'Spaghetti Solo', 59.00, 'Spaghetti', 'Regular spaghetti', NULL, 1, '2026-03-22 10:16:05', 1),
(49, 'Spaghetti Family', 299.00, 'Spaghetti', 'Family size', NULL, 1, '2026-03-22 10:16:05', 1),
(50, 'Palabok', 89.00, 'Noodles', 'Rice noodles', NULL, 1, '2026-03-22 10:16:05', 1),
(51, 'Regular Fries', 59.00, 'Fries', 'Classic fries', NULL, 1, '2026-03-22 10:16:05', 1),
(52, 'Large Fries', 89.00, 'Fries', 'Large fries', NULL, 1, '2026-03-22 10:16:05', 1),
(53, 'Coke', 45.00, 'Drinks', 'Coca-Cola', NULL, 1, '2026-03-22 10:16:05', 1),
(54, 'Sprite', 45.00, 'Drinks', 'Sprite', NULL, 1, '2026-03-22 10:16:05', 1),
(55, 'Pineapple Juice', 49.00, 'Drinks', 'Pineapple juice', NULL, 1, '2026-03-22 10:16:05', 1),
(56, 'Iced Tea', 49.00, 'Drinks', 'Iced tea', NULL, 1, '2026-03-22 10:16:05', 1),
(57, 'Peach Mango Pie', 49.00, 'Dessert', 'Fruit pie', NULL, 1, '2026-03-22 10:16:05', 1),
(58, 'Sundae Chocolate', 39.00, 'Dessert', 'Chocolate sundae', NULL, 1, '2026-03-22 10:16:05', 1),
(59, 'Sundae Ube', 39.00, 'Dessert', 'Ube sundae', NULL, 1, '2026-03-22 10:16:05', 1),
(60, 'Chickenjoy w/ Rice', 119.00, 'Rice Meal', 'Chicken with rice', NULL, 1, '2026-03-22 10:16:05', 1),
(61, 'Chickenjoy Value Meal', 149.00, 'Value Meal', 'Complete meal', NULL, 1, '2026-03-22 10:16:05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_name` varchar(100) DEFAULT 'Walk-in Customer',
  `order_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_amount` decimal(10,2) NOT NULL DEFAULT 0.00,
  `status` varchar(20) DEFAULT 'pending',
  `payment_method` varchar(50) DEFAULT 'Cash'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_name`, `order_date`, `total_amount`, `status`, `payment_method`) VALUES
(7, 'Walk-in Customer', '2026-03-22 10:27:19', 208.00, 'pending', 'Cash'),
(8, 'Walk-in Customer', '2026-03-22 10:36:02', 879.00, 'pending', 'Cash'),
(9, 'Walk-in Customer', '2026-03-22 15:58:51', 89.00, 'pending', 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `menu_item_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT 1,
  `subtotal` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `menu_item_id`, `quantity`, `subtotal`) VALUES
(1, 8, 60, 1, 119.00),
(2, 8, 47, 1, 69.00),
(3, 8, 61, 1, 149.00),
(4, 8, 48, 1, 59.00),
(5, 8, 49, 1, 299.00),
(6, 8, 54, 3, 135.00),
(7, 8, 55, 1, 49.00);

-- --------------------------------------------------------

--
-- Stand-in structure for view `popular_items`
-- (See below for the actual view)
--
CREATE TABLE `popular_items` (
`id` int(11)
,`name` varchar(100)
,`price` decimal(10,2)
,`category` varchar(50)
,`description` text
,`image_path` varchar(255)
,`order_count` bigint(21)
);

-- --------------------------------------------------------

--
-- Structure for view `popular_items`
--
DROP TABLE IF EXISTS `popular_items`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `popular_items`  AS SELECT `m`.`id` AS `id`, `m`.`name` AS `name`, `m`.`price` AS `price`, `m`.`category` AS `category`, `m`.`description` AS `description`, `m`.`image_path` AS `image_path`, count(`oi`.`id`) AS `order_count` FROM (`menu_items` `m` left join `order_items` `oi` on(`m`.`id` = `oi`.`menu_item_id`)) GROUP BY `m`.`id` ORDER BY count(`oi`.`id`) DESC ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_users`
--
ALTER TABLE `admin_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `menu_items`
--
ALTER TABLE `menu_items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `menu_item_id` (`menu_item_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_users`
--
ALTER TABLE `admin_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `menu_items`
--
ALTER TABLE `menu_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`menu_item_id`) REFERENCES `menu_items` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
