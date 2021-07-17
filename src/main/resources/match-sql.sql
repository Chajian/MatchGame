/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : matchgame

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 17/07/2021 22:06:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for integral
-- ----------------------------
DROP TABLE IF EXISTS `integral`;
CREATE TABLE `integral`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `player_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `game_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `score` int(11) NULL DEFAULT 0,
  `wins` int(11) NOT NULL DEFAULT 0,
  `losers` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of integral
-- ----------------------------
INSERT INTO `integral` VALUES (1, '997', 'bedwar', 100, 100, 0);

SET FOREIGN_KEY_CHECKS = 1;
