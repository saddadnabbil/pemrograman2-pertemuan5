-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 29, 2024 at 01:06 PM
-- Server version: 5.7.39
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pertemuan5_pemrograman2`
--

-- --------------------------------------------------------

--
-- Table structure for table `penjualan_rumah`
--

CREATE TABLE `penjualan_rumah` (
  `id` int(11) NOT NULL,
  `nama_pemesan` varchar(255) NOT NULL,
  `luas_tanah_tersedia` varchar(255) NOT NULL,
  `harga` varchar(255) NOT NULL,
  `dp` varchar(255) NOT NULL,
  `lama_cicilan` varchar(255) NOT NULL,
  `ppn` varchar(255) NOT NULL,
  `cicilan_per_bulan` varchar(255) NOT NULL,
  `id_area_rumah` int(255) NOT NULL,
  `id_tipe_rumah` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `penjualan_rumah`
--

INSERT INTO `penjualan_rumah` (`id`, `nama_pemesan`, `luas_tanah_tersedia`, `harga`, `dp`, `lama_cicilan`, `ppn`, `cicilan_per_bulan`, `id_area_rumah`, `id_tipe_rumah`) VALUES
(11, 'saddad', '100', '135,000,000', '100000', '12', '13,500,000', '12,366,666.67', 1, 1),
(12, 'saddad2', '190', '150,000,000', '170000', '1', '15,000,000', '164,830,000', 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penjualan_rumah`
--
ALTER TABLE `penjualan_rumah`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `penjualan_rumah`
--
ALTER TABLE `penjualan_rumah`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
