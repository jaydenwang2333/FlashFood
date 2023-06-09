/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : reggie

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 21/05/2023 13:08:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address_book
-- ----------------------------
DROP TABLE IF EXISTS `address_book`;
CREATE TABLE `address_book`  (
  `id` bigint NOT NULL COMMENT 'primay key',
  `user_id` bigint NOT NULL,
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'receiver',
  `sex` tinyint NOT NULL COMMENT 'gender 0 female 1 male',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'phone number',
  `zip_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'zip code',
  `state_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'state',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'city',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'address',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'unit or floor',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'deafult 0 no 1 yes',
  `create_time` datetime NOT NULL COMMENT 'crate time',
  `update_time` datetime NOT NULL COMMENT 'update time',
  `create_user` bigint NOT NULL COMMENT 'creater',
  `update_user` bigint NOT NULL COMMENT 'changer',
  `is_deleted` int NOT NULL DEFAULT 0 COMMENT 'delte?',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'address management' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of address_book
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL COMMENT 'primay key',
  `type` int NULL DEFAULT NULL COMMENT 'type   1 dish 2 mealseat',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'catelogy type',
  `sort` int NOT NULL DEFAULT 0 COMMENT 'order',
  `create_time` datetime NOT NULL COMMENT 'create time',
  `update_time` datetime NOT NULL COMMENT 'update time',
  `create_user` bigint NOT NULL COMMENT 'creater',
  `update_user` bigint NOT NULL COMMENT 'changer',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_category_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'dish and meal set' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1397844263642378242, 1, 'Chinese food', 1, '2022-05-27 09:16:58', '2023-04-19 12:50:31', 1, 1);
INSERT INTO `category` VALUES (1397844303408574465, 1, 'Sushi', 2, '2023-05-19 09:17:07', '2022-06-02 14:27:22', 1, 1);
INSERT INTO `category` VALUES (1397844391040167938, 1, 'Fast food', 3, '2023-05-19 09:17:28', '2022-07-09 14:37:13', 1, 1);
INSERT INTO `category` VALUES (1413341197421846529, 1, 'drink', 11, '2021-07-09 11:36:15', '2023-07-09 14:39:15', 1, 1);
INSERT INTO `category` VALUES (1413342269393674242, 2, 'lunch combo', 5, '2021-07-09 11:40:30', '2022-07-09 14:43:45', 1, 1);
INSERT INTO `category` VALUES (1413384954989060097, 1, 'entire food', 12, '2021-07-09 14:30:07', '2022-07-09 14:39:19', 1, 1);
INSERT INTO `category` VALUES (1413386191767674881, 2, 'kid meal', 6, '2021-07-09 14:35:02', '2022-07-09 14:39:05', 1, 1);

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `price` decimal(10, 2) NULL DEFAULT 0.00,
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `image` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `description` varchar(400) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `status` int(11) NOT NULL DEFAULT 1 ,
  `sort` int(11) NOT NULL DEFAULT 0 ,
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL ,
  `create_user` bigint(20) NOT NULL,
  `update_user` bigint(20) NOT NULL ,
  `is_deleted` int(11) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_dish_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES (1397849739276890114, 'Spicy Chicken', 1397844263642378242, 18.00, '222222222', 'f966a38e-0780-40be-bb52-5699d13cb3d9.jpg', ' ', 1, 0, '2023-05-19 09:38:43', '2023-05-19 09:38:43', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850140982161409, 'Braised pork', 1397844263642378242, 20.00, '123412341234', '0a3b3288-3446-4420-bbff-f263d0c02d8e.jpg', ' ', 1, 0, '2023-05-19 09:40:19', '2023-05-19 09:40:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850392090947585, 'Fried wings', 1397844263642378242, 10.00, '123412341234', '740c79ce-af29-41b8-b78d-5f49c96e38c4.jpg', ' ', 1, 0, '2023-05-19 09:41:19', '2023-05-19 09:41:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397850851245600769, 'Fried Rice', 1397844263642378242, 12.00, '123412341234', '057dd338-e487-4bbc-a74c-0384c44a9ca3.jpg', ' ', 1, 0, '2023-05-19 09:43:08', '2023-05-19 09:43:08', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851099502260226, 'Peking Duck', 1397844263642378242, 11.00, '23412341234', 'a53a4e6a-3b83-4044-87f9-9d49b30a8fdc.jpg', ' ', 1, 0, '2023-05-19 09:44:08', '2023-05-19 09:44:08', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851370462687234, 'Stinky Tofu', 1397844263642378242, 13.00, '1246812345678', '2a50628e-7758-4c51-9fbb-d37c61cdacad.jpg', ' ', 1, 0, '2023-05-19 09:45:12', '2023-05-19 09:45:12', 1, 1, 0);
INSERT INTO `dish` VALUES (1397851668262465537, 'Chow Mein', 1397844263642378242, 16.00, '1234567812345678', '0f4bd884-dc9c-4cf9-b59e-7d5958fec3dd.jpg', ' ', 1, 0, '2023-05-19 09:46:23', '2023-05-19 09:46:23', 1, 1, 0);
INSERT INTO `dish` VALUES (1397852391150759938, 'Congee', 1397844303408574465, 18.00, '2346812468', 'ef2b73f2-75d1-4d3a-beea-22da0e1421bd.jpg', ' ', 1, 0, '2023-05-19 09:49:16', '2023-05-19 09:49:16', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853183287013378, 'Kung Pao Chicken', 1397844303408574465, 19.00, '123456787654321', '2a2e9d66-b41d-4645-87bd-95f2cfeed218.jpg', ' ', 1, 0, '2023-05-19 09:52:24', '2023-05-19 09:52:24', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853709101740034, 'Chinese Hamburger', 1397844303408574465, 9.00, '1234321234321', 'd2f61d70-ac85-4529-9b74-6d9a2255c6d7.jpg', ' ', 1, 0, '2023-05-19 09:54:30', '2023-05-19 09:54:30', 1, 1, 0);
INSERT INTO `dish` VALUES (1397853890262118402, 'Mapo Tofu', 1397844303408574465, 13.00, '1234212321234', '8dcfda14-5712-4d28-82f7-ae905b3c2308.jpg', ' ', 1, 0, '2023-05-19 09:55:13', '2023-05-19 09:55:13', 1, 1, 0);
INSERT INTO `dish` VALUES (1397854652581064706, 'Baozi', 1397844303408574465, 14.00, '2345312·345321', '1fdbfbf3-1d86-4b29-a3fc-46345852f2f8.jpg', ' ', 1, 0, '2023-05-19 09:58:15', '2023-05-19 09:58:15', 1, 1, 0);
INSERT INTO `dish` VALUES (1397854865672679425, 'Char Siu', 1397844303408574465, 20.00, '23456431·23456', '0f252364-a561-4e8d-8065-9a6797a6b1d3.jpg', ' ', 1, 0, '2023-05-19 09:59:06', '2023-05-19 09:59:06', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860242057375745, 'Soup Dumplings', 1397844391040167938, 12.00, '123456786543213456', 'e476f679-5c15-436b-87fa-8c4e9644bf33.jpeg', ' ', 1, 0, '2023-05-19 10:20:27', '2023-05-19 10:20:27', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860578738352129, 'Hainanese Chicken Rice', 1397844391040167938, 16.00, '12345678654', '9ec6fc2d-50d2-422e-b954-de87dcd04198.jpeg', ' ', 1, 0, '2023-05-19 10:21:48', '2023-05-19 10:21:48', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860792492666881, 'Seasoned Steamed Eggplant', 1397844391040167938, 13.00, '213456432123456', '2e96a7e3-affb-438e-b7c3-e1430df425c9.jpeg', ' ', 1, 0, '2023-05-19 10:22:39', '2023-05-19 10:22:39', 1, 1, 0);
INSERT INTO `dish` VALUES (1397860963880316929, 'Jiaozi', 1397844391040167938, 10.00, '1234563212345', '3fabb83a-1c09-4fd9-892b-4ef7457daafa.jpeg', ' ', 1, 0, '2023-05-19 10:23:19', '2023-05-19 10:23:19', 1, 1, 0);
INSERT INTO `dish` VALUES (1397861683434139649, 'Dried Pot Tofu', 1397844391040167938, 9.00, '1234567876543213456', '1405081e-f545-42e1-86a2-f7559ae2e276.jpeg', ' ', 1, 0, '2023-05-19 10:26:11', '2023-05-19 10:26:11', 1, 1, 0);
INSERT INTO `dish` VALUES (1397862198033297410, 'Potato Croquet', 1397844391040167938, 10.00, '123456786532455', '583df4b7-a159-4cfc-9543-4f666120b25f.jpeg', ' ', 1, 0, '2023-05-19 10:28:14', '2023-05-19 10:28:14', 1, 1, 0);
INSERT INTO `dish` VALUES (1397862477831122945, 'Oyster omelette', 1397844391040167938, 10.00, '1234567865432', '5b8d2da3-3744-4bb3-acdc-329056b8259d.jpeg', ' ', 1, 0, '2023-05-19 10:29:20', '2023-05-19 10:29:20', 1, 1, 0);
INSERT INTO `dish` VALUES (1413342036832100354, 'Boba tea', 1413341197421846529, 5.00, '', 'c99e0aab-3cb7-4eaa-80fd-f47d4ffea694.png', ' ', 1, 0, '2021-07-09 11:39:35', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO `dish` VALUES (1413384757047271425, 'Coke', 1413341197421846529, 2.50, '', '00874a5e-0df2-446b-8f69-a30eb7d88ee8.png', ' ', 1, 0, '2021-07-09 14:29:20', '2021-07-12 09:09:16', 1, 1, 0);
INSERT INTO `dish` VALUES (1413385247889891330, 'Rice', 1413384954989060097, 1.50, '', 'ee04a05a-1230-46b6-8ad5-1a95b140fff3.png', ' ', 1, 0, '2021-07-09 14:31:17', '2021-07-11 16:35:26', 1, 1, 0);
INSERT INTO `dish` VALUES (1516345768159182850, 'Twice cooked pork', 1397844303408574465, 16.00, '', 'c5f0e48a-b356-4351-aec9-90f8bc6707eb.jpg', ' ', 1, 0, '2022-04-19 17:19:38', '2022-04-20 15:47:36', 1, 1, 0);

-- ----------------------------
-- Table structure for dish_flavor
-- ----------------------------
DROP TABLE IF EXISTS `dish_flavor`;
CREATE TABLE `dish_flavor`  (
  `id` bigint NOT NULL,
  `dish_id` bigint NOT NULL ,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL,
  `create_user` bigint NOT NULL ,
  `update_user` bigint NOT NULL ,
  `is_deleted` int NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dish_flavor
-- ----------------------------
INSERT INTO `dish_flavor` VALUES (1397849417888346113, 1397849417854791681,'Spicy',  '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:37:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849739297861633, 1397849739276890114, 'Avoid', '[\"No onion\"]', '2023-05-19 09:38:43', '2023-05-19 09:38:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849739323027458, 1397849739276890114, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:38:43', '2023-05-19 09:38:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936421761025, 1397849936404983809, 'Avoid', '[\"No onion\"]', '2023-05-19 09:39:30', '2023-05-19 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397849936438538241, 1397849936404983809, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:39:30', '2023-05-19 09:39:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850141015715841, 1397850140982161409, 'Avoid', '[\"No onion\"]', '2023-05-19 09:40:19', '2023-05-19 09:40:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850141040881665, 1397850140982161409, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:40:19', '2023-05-19 09:40:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850392120307713, 1397850392090947585, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:41:19', '2023-05-19 09:41:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850392137084929, 1397850392090947585, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:41:19', '2023-05-19 09:41:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630734262274, 1397850630700707841, 'Avoid', '[\"No onion\"]', '2023-05-19 09:42:16', '2023-05-19 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850630755233794, 1397850630700707841, 'Spicy', '[\"Slightly spicy\",\"Medium spicy\",\"Heavy spicy\"]', '2023-05-19 09:42:16', '2023-05-19 09:42:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850851274960898, 1397850851245600769, 'Avoid', '[\"No garlic\",\"No coriander\",\No spicy\"]', '2023-05-19 09:43:08', '2023-05-19 09:43:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397850851283349505, 1397850851245600769, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:43:08', '2023-05-19 09:43:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851099523231745, 1397851099502260226, 'Avoid', '[\"No onion\"]', '2023-05-19 09:44:08', '2023-05-19 09:44:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851099527426050, 1397851099502260226, 'Spicy', '[\"Slightly spicy\",\"Medium spicy\",\"Heavy spicy\"]', '2023-05-19 09:44:08', '2023-05-19 09:44:08', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658754, 1397851370462687234, 'Temp', '[\"No ice\"]', '2023-05-19 09:45:12', '2023-05-19 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658755, 1397851370462687234, 'Avoid', '[\"No onion\"]', '2023-05-19 09:45:12', '2023-05-19 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851370483658756, 1397851370462687234, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:45:12', '2023-05-19 09:45:12', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397851668283437058, 1397851668262465537, 'Temp', '[\"No ice\"]', '2023-05-19 09:46:23', '2023-05-19 09:46:23', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397852391180120065, 1397852391150759938,'Avoid', '[\"No onion\"]', '2023-05-19 09:49:16', '2023-05-19 09:49:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397852391196897281, 1397852391150759938, 'Spicy', '[\"Not spicy\",\"Slightly spicy\",\"Heavy spicy\"]', '2023-05-19 09:49:16', '2023-05-19 09:49:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853183307984898, 1397853183287013378, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:52:24', '2023-05-19 09:52:24', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853423486414850, 1397853423461249026, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:53:22', '2023-05-19 09:53:22', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853709126905857, 1397853709101740034, 'Avoid', '[\"No onion\"]', '2023-05-19 09:54:30', '2023-05-19 09:54:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397853890283089922, 1397853890262118402, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:55:13', '2023-05-19 09:55:13', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854133632413697, 1397854133603053569, 'Temp', '[\"No ice\"]', '2023-05-19 09:56:11', '2023-05-19 09:56:11', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854652623007745, 1397854652581064706, 'Avoid', '[\"No onion\"]', '2023-05-19 09:58:15', '2023-05-19 09:58:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854652635590658, 1397854652581064706, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:58:15', '2023-05-19 09:58:15', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397854865735593986, 1397854865672679425, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 09:59:06', '2023-05-19 09:59:06', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855742303186946, 1397855742273826817, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:02:35', '2023-05-19 10:02:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397855906497605633, 1397855906468245506, 'Avoid', '[\"No onion\"]', '2023-05-19 10:03:14', '2023-05-19 10:03:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397856190573621250, 1397856190540066818, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:04:21', '2023-05-19 10:04:21', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859056709316609, 1397859056684150785, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:15:45', '2023-05-19 10:15:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859277837217794, 1397859277812051969, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:16:37', '2023-05-19 10:16:37', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859487502086146, 1397859487476920321, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:17:27', '2023-05-19 10:17:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397859757061615618, 1397859757036449794, 'Sugar', '[\"no sugar\"]', '2023-05-19 10:18:32', '2023-05-19 10:18:32', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397860242086735874, 1397860242057375745, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:20:27', '2023-05-19 10:20:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397860963918065665, 1397860963880316929, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:23:19', '2023-05-19 10:23:19', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861135754506242, 1397861135733534722, 'Sugar', '[\"no sugar\"]', '2023-05-19 10:24:00', '2023-05-19 10:24:00', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861370035744769, 1397861370010578945, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2023-05-19 10:24:56', '2023-05-19 10:24:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861683459305474, 1397861683434139649, 'Avoid', '[\"No onion\"]', '2023-05-19 10:26:11', '2023-05-19 10:26:11', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397861898467717121, 1397861898438356993, 'Avoid', '[\"No onion\"]', '2023-05-19 10:27:02', '2023-05-19 10:27:02', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397862198054268929, 1397862198033297410, 'Avoid', '[\"No onion\"]', '2023-05-19 10:28:14', '2023-05-19 10:28:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1397862477835317250, 1397862477831122945, 'Spicy', '[\"Slightly spicy\",\"Medium spicy\",\"Heavy spicy\"]', '2023-05-19 10:29:20', '2023-05-19 10:29:20', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089545865015297, 1398089545676271617, 'Temp', '[\"No ice\"]', '2021-05-28 01:31:38', '2021-05-28 01:31:38', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398089782323097601, 1398089782285348866, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:32:34', '2021-05-28 01:32:34', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090003262255106, 1398090003228700673, 'Avoid', '[\"No onion\"]', '2021-05-28 01:33:27', '2021-05-28 01:33:27', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090264554811394, 1398090264517062657, 'Avoid', '[\"No onion\"]', '2021-05-28 01:34:29', '2021-05-28 01:34:29', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090455399837698, 1398090455324340225, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:35:14', '2021-05-28 01:35:14', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090685449023490, 1398090685419663362, 'Temp', '[\"No ice\"]', '2021-05-28 01:36:09', '2021-05-28 01:36:09', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398090825358422017, 1398090825329061889, 'Avoid', '[\"No onion\"]', '2021-05-28 01:36:43', '2021-05-28 01:36:43', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091007051476993, 1398091007017922561, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:37:26', '2021-05-28 01:37:26', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091296164851713, 1398091296131297281, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:38:35', '2021-05-28 01:38:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091546531246081, 1398091546480914433, 'Avoid', '[\"No onion\"]', '2021-05-28 01:39:35', '2021-05-28 01:39:35', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091729809747969, 1398091729788776450, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:40:18', '2021-05-28 01:40:18', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398091889499484161, 1398091889449152513, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:40:56', '2021-05-28 01:40:56', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092095179763713, 1398092095142014978, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:41:45', '2021-05-28 01:41:45', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398092283877306370, 1398092283847946241, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:42:30', '2021-05-28 01:42:30', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094018939236354, 1398094018893099009, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:49:24', '2021-05-28 01:49:24', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1398094391494094850, 1398094391456346113, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-05-28 01:50:53', '2021-05-28 01:50:53', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1399574026165727233, 1399305325713600514, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2021-06-01 03:50:25', '2021-06-01 03:50:25', 1399309715396669441, 1399309715396669441, 0);
INSERT INTO `dish_flavor` VALUES (1413389540592263169, 1413384757047271425, 'Temp', '[\"normal Temp\",\"Cold\"]', '2021-07-12 09:09:16', '2021-07-12 09:09:16', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1413389684020682754, 1413342036832100354, 'Temp', '[\"normal Temp\",\"Cold\"]', '2021-07-09 15:12:18', '2021-07-09 15:12:18', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1516345768255651842, 1516345768159182850, 'Sugar', '[\"less sugar\"]', '2022-04-20 15:47:36', '2022-04-20 15:47:36', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1516345768326955010, 1516345768159182850, 'Temp', '[\"normal Temp\"]', '2022-04-20 15:47:36', '2022-04-20 15:47:36', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1516654191429943298, 1516345768159182850, 'Sugar', '[\"no sugar\"]', '2022-04-20 15:47:36', '2022-04-20 15:47:36', 1, 1, 0);
INSERT INTO `dish_flavor` VALUES (1516654481738694657, 1516345768159182850, 'Spicy', '[\"NotSpicy\","Spicy\"]', '2023-05-19 09:37:27', '2022-04-20 15:47:36', '2022-04-20 15:47:36', 1, 1, 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint NOT NULL ,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL  ,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL  ,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` int NOT NULL DEFAULT 1,
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL ,
  `create_user` bigint NOT NULL,
  `update_user` bigint NOT NULL ,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'Manager', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '15875515765', '1', '110101199001010047', 1, '2021-05-06 17:20:07', '2022-04-18 16:24:02', 1, 1);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `order_id` bigint NOT NULL,
  `dish_id` bigint NULL DEFAULT NULL,
  `setmeal_id` bigint NULL DEFAULT NULL,
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `number` int NOT NULL DEFAULT 1,
  `amount` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL,
  `number` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` int NOT NULL DEFAULT 1 ,
  `user_id` bigint NOT NULL,
  `address_book_id` bigint NOT NULL,
  `order_time` datetime NOT NULL ,
  `checkout_time` datetime NOT NULL,
  `pay_method` int NOT NULL DEFAULT 1 ,
  `amount` decimal(10, 2) NOT NULL ,
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `consignee` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal`  (
  `id` bigint NOT NULL 
  `category_id` bigint NOT NULL ,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `price` decimal(10, 2) NOT NULL ,
  `status` int NULL DEFAULT NULL ,
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `description` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `create_time` datetime NOT NULL ,
  `update_time` datetime NOT NULL ,
  `create_user` bigint NOT NULL ,
  `update_user` bigint NOT NULL ,
  `is_deleted` int NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_setmeal_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setmeal
-- ----------------------------
INSERT INTO `setmeal` VALUES (1516675706506670081, 1413342269393674242, 'Special meal', 12000.00, 1, '', 'Chef specials', '78c7ac1d-ed72-4ef8-a285-b59959629800.jpg', '2022-04-20 15:10:42', '2022-04-21 20:11:13', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1516730887785127937, 1413342269393674242, 'Single meal', 18000.00, 1, '', 'Lunch', '5e1ab974-015b-421c-a3c1-20f9f932122b.jpg', '2022-04-20 18:49:58', '2022-04-20 18:49:58', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1516731233496440833, 1413342269393674242, 'Luxury meal', 99900.00, 1, '', 'Seafood', 'b82652c9-3afc-4307-88fa-dd19e6d66994.jpg', '2022-04-20 18:51:20', '2022-04-20 18:51:20', 1, 1, 0);
INSERT INTO `setmeal` VALUES (1516731519585722369, 1413342269393674242, 'Family meal', 17000.00, 1, '', 'Good deal', '66e91749-3c3c-4f48-8ec4-511ec8c5ba58.jpg', '2022-04-20 18:52:28', '2022-04-20 18:52:28', 1, 1, 0);

-- ----------------------------
-- Table structure for setmeal_dish
-- ----------------------------
DROP TABLE IF EXISTS `setmeal_dish`;
CREATE TABLE `setmeal_dish`  (
  `id` bigint NOT NULL ,
  `setmeal_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `dish_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `copies` int NOT NULL ,
  `sort` int NOT NULL DEFAULT 0 ,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `create_user` bigint NOT NULL,
  `update_user` bigint NOT NULL ,
  `is_deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of setmeal_dish
-- ----------------------------
INSERT INTO `setmeal_dish` VALUES (1516730887864819713, '1516730887785127937', '1397850851245600769', '	Panera Soup', 12.00, 1, 0, '2022-04-20 18:49:58', '2022-04-20 18:49:58', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516730887864819714, '1516730887785127937', '1397850392090947585', 'Original Thickburger', 8.00, 1, 0, '2022-04-20 18:49:58', '2022-04-20 18:49:58', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516730887864819715, '1516730887785127937', '1413385247889891330', 'Filet-O-Fish Sandwich', 12.00, 1, 0, '2022-04-20 18:49:58', '2022-04-20 18:49:58', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516731233630658561, '1516731233496440833', '1397851370462687234', 'Caesars Crazy Bread', 13.00, 1, 0, '2022-04-20 18:51:20', '2022-04-20 18:51:20', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516731233630658562, '1516731233496440833', '1397862198033297410', 'Crispy Chicken', 5.00, 1, 0, '2022-04-20 18:51:20', '2022-04-20 18:51:20', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516731519652831233, '1516731519585722369', '1397860578738352129', 'Wisconsin Cheese Curds', 6.00, 1, 0, '2022-04-20 18:52:28', '2022-04-20 18:52:28', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1516731519652831234, '1516731519585722369', '1397860963880316929', 'Baked Potato', 10.00, 1, 0, '2022-04-20 18:52:28', '2022-04-20 18:52:28', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1517113723868332034, '1516675706506670081', '1397851099502260226', 'Two-Piece Fish Meal', 11.00, 1, 0, '2022-04-21 20:11:13', '2022-04-21 20:11:13', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1517113723868332035, '1516675706506670081', '1413342036832100354', 'Chicken Fries', 5.00, 1, 0, '2022-04-21 20:11:13', '2022-04-21 20:11:13', 1, 1, 0);
INSERT INTO `setmeal_dish` VALUES (1517113723868332036, '1516675706506670081', '1397850392090947585', 'Chicken Nuggets', 4.00, 1, 0, '2022-04-21 20:11:13', '2022-04-21 20:11:13', 1, 1, 0);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `id` bigint NOT NULL ,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `image` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `dish_id` bigint NULL DEFAULT NULL,
  `setmeal_id` bigint NULL DEFAULT NULL ,
  `dish_flavor` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `number` int NOT NULL DEFAULT 1,
  `amount` decimal(10, 2) NOT NULL ,
  `create_time` datetime NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL ,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
  `status` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
