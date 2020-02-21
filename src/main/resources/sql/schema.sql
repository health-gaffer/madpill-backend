SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mp_drug
-- ----------------------------
DROP TABLE IF EXISTS `mp_drug`;
CREATE TABLE `mp_drug`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) NOT NULL,
    `produced_date`    date         NOT NULL,
    `expire_date`      date         NOT NULL,
    `description`      varchar(255) DEFAULT NULL,
    `indication`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `contraindication` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `user_id`          bigint(20)   NOT NULL,
    `group_id`         bigint(20)   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100000009
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for mp_drug_tag
-- ----------------------------
DROP TABLE IF EXISTS `mp_drug_tag`;
CREATE TABLE `mp_drug_tag`
(
    `drug_id` bigint(20) NOT NULL,
    `tag_id`  bigint(20) NOT NULL,
    PRIMARY KEY (`drug_id`, `tag_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for mp_group
-- ----------------------------
DROP TABLE IF EXISTS `mp_group`;
CREATE TABLE `mp_group`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`      varchar(255) NOT NULL,
    `create_at` datetime     NOT NULL,
    `create_by` bigint(20)   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for mp_tag
-- ----------------------------
DROP TABLE IF EXISTS `mp_tag`;
CREATE TABLE `mp_tag`
(
    `id`      bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`    varchar(20) NOT NULL,
    `user_id` bigint(20)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1002
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for mp_user
-- ----------------------------
DROP TABLE IF EXISTS `mp_user`;
CREATE TABLE `mp_user`
(
    `id`         bigint(20)                                                 NOT NULL AUTO_INCREMENT,
    `open_id`    char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `created_at` datetime                                                   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for mp_user_group
-- ----------------------------
DROP TABLE IF EXISTS `mp_user_group`;
CREATE TABLE `mp_user_group`
(
    `user_id`  bigint(20) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    PRIMARY KEY (`group_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mp_user_group
-- ----------------------------
BEGIN;
INSERT INTO `mp_user_group`
VALUES (12, 1);
INSERT INTO `mp_user_group`
VALUES (12, 2);
COMMIT;

-- ----------------------------
-- Table structure for mp_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `mp_warehouse`;
CREATE TABLE `mp_warehouse`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) NOT NULL,
    `manufacture`      varchar(255) NOT NULL,
    `indication`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `contraindication` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
