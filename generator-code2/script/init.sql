CREATE TABLE `user_info`  (
                              `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                              `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                              `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
                              `source` tinyint(4) NULL DEFAULT NULL COMMENT '来源',
                              PRIMARY KEY (`id`) USING BTREE,
                              INDEX `source_id`(`source`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;



