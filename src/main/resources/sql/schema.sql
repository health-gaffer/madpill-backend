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
    `reminders`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `indication`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `contraindication` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
    `user_id`          bigint(20)   NOT NULL,
    `group_id`         bigint(20)   NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
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
    `can_delete` bit(1)     NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
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
  AUTO_INCREMENT = 1
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
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX open_id_idx on mp_user(open_id);

-- ----------------------------
-- Table structure for mp_user_group
-- ----------------------------
DROP TABLE IF EXISTS `mp_user_group`;
CREATE TABLE `mp_user_group`
(
    `user_id`  bigint(20) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    `alias`    varchar(255) NOT NULL,
    PRIMARY KEY (`group_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for mp_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `mp_warehouse`;
CREATE TABLE `mp_warehouse`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `name`             varchar(255) NOT NULL COMMENT '药品名称',
    `ingredient`       text COMMENT '成份',
    `character`        text COMMENT '性状',
    `function`         text COMMENT '功能主治',
    `indication`       text COMMENT '适应症',
    `usage`            text COMMENT '用法用量',
    `adverse_effect`   text COMMENT '不良反应',
    `contraindication` text COMMENT '禁忌',
    `warning`          text COMMENT '注意事项',
    `pregnant`         text COMMENT '孕妇及哺乳期妇女用药',
    `children`         text COMMENT '儿童用药',
    `old`              text COMMENT '老年用药',
    `storage`          text COMMENT '贮藏',
    `interaction`      text COMMENT '药物相互作用',
    `overdose`         text COMMENT '药物过量',
    `packaging`        text COMMENT '包装',
    `indate`           text COMMENT '有效期',
    `specification`    text COMMENT '规格',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
