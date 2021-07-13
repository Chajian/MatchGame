/*
 Navicat Premium Data Transfer

 Source Server         : localhost_330632
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : match

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 11/07/2021 20:19:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `player_name` varchar(20) NOT NULL,
  `game_name` varchar(20) NOT NULL,
  `score` int DEFAULT '0',
  `wins` int DEFAULT '0',
  `losers` int DEFAULT '0',
  PRIMARY KEY (`player_name`,`game_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
