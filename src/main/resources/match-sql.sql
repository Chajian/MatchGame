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

 Date: 18/07/2021 11:10:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for game_record
-- ----------------------------
DROP TABLE IF EXISTS `game_record`;
CREATE TABLE `game_record` (
  `id` int NOT NULL AUTO_INCREMENT,
  `player_name` varchar(11) NOT NULL,
  `game_name` varchar(11) NOT NULL,
  `score` int DEFAULT '0',
  `game_model` int DEFAULT '0',
  `kills` int DEFAULT '0',
  `deaths` int DEFAULT '0',
  `time` timestamp NOT NULL,
  `others` json DEFAULT NULL,
  PRIMARY KEY (`id`,`player_name`,`game_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of game_record
-- ----------------------------
BEGIN;
INSERT INTO `game_record` VALUES (1, '997', 'bedwar', 100, 0, 100, 0, '2021-07-18 11:10:06', '{}');
INSERT INTO `game_record` VALUES (2, '997', 'pushcar', 100, 0, 200, 0, '2021-07-18 11:10:29', '{}');
COMMIT;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `player_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `game_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int DEFAULT '0',
  `wins` int NOT NULL DEFAULT '0',
  `losers` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of integral
-- ----------------------------
BEGIN;
INSERT INTO `integral` VALUES (1, '997', 'bedwar', 100, 100, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
