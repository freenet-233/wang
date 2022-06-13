CREATE TABLE `user_info`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                              `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
                              `source` tinyint(4) NULL DEFAULT NULL COMMENT '来源',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `source_id`(`source`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `school_info`;
CREATE TABLE `school_info`  (
                                `id` int(11) NOT NULL,
                                `school_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校名称',
                                `school_addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校地址',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `school_info` VALUES (1, 'XXX小学', 'xx区xx街道80号');

-  ----------------------------------------------------------------

CREATE TABLE `class_info`  (
                               `id` int(11) NOT NULL,
                               `class_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
                               `class_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
                               `school_id` int(11) NOT NULL COMMENT '隶属的学校',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `class_info` VALUES (1, '一年级1班', NULL, 1);
INSERT INTO `class_info` VALUES (2, '一年级2班', NULL, 1);

-  ----------------------------------------------------------------

CREATE TABLE `student_info`  (
                                 `id` int(11) NOT NULL,
                                 `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                 `age` int(11) NULL DEFAULT NULL,
                                 `class_id` int(11) NULL DEFAULT NULL,
                                 `school_id` int(11) NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `student_info` VALUES (1, '张三', 7, 1, 1);
INSERT INTO `student_info` VALUES (2, '李四', 7, 2, 1);
INSERT INTO `student_info` VALUES (3, '王五', 8, 1, 1);
INSERT INTO `student_info` VALUES (4, '赵六', 8, 1, 1);
