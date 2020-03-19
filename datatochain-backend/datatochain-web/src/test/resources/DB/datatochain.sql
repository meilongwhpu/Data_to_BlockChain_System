DROP TABLE IF EXISTS `nl_operate_log`;

CREATE TABLE `nl_operate_log` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `user_id` varchar(50) DEFAULT NULL COMMENT '用户ID',
    `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
    `oper_subject` varchar(20) DEFAULT NULL COMMENT '操作科目',
    `oper_detail` varchar(100) DEFAULT NULL COMMENT '操作明细',
    `oper_ip` varchar(20) DEFAULT NULL COMMENT '客户端IP地址',
    `remark` varchar(200) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `nl_app_info`;

CREATE TABLE `nl_app_info` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `app_name` varchar(50) NOT NULL COMMENT '应用名称',
    `app_desc` varchar(50) DEFAULT NULL COMMENT '应用介绍',
    `app_key` varchar(50) NOT NULL COMMENT '应用key，访问应用下面的数据时使用',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `creator_id` varchar(50) DEFAULT NULL COMMENT '创建者ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `nl_tablespace_info`;

CREATE TABLE `nl_tablespace_info` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `tablespace_name` varchar(50) NOT NULL COMMENT '表空间名称',
    `tablespace_desc` varchar(50) DEFAULT NULL COMMENT '表空间描述',
    `code_mode` tinyint(4) NOT NULL COMMENT '编码格式',
    `encryption_mode` tinyint(4) NOT NULL COMMENT '加密模式',
    `to_chain` tinyint(4) NOT NULL COMMENT '上链位置',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `creator_id` varchar(50) DEFAULT NULL COMMENT '创建者ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表空间信息表';

DROP TABLE IF EXISTS `nl_tablestructure_info`;

CREATE TABLE `nl_tablestructure_info` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `tablespace_id` varchar(50) NOT NULL COMMENT '表空间ID',
    `table_name` varchar(50) NOT NULL COMMENT '表名称',
    `table_desc` varchar(50) DEFAULT NULL COMMENT '表描述',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `creator_id` varchar(50) DEFAULT NULL COMMENT '创建者ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `nl_tablefield_info`;

CREATE TABLE `nl_tablefield_info` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `table_id` varchar(50) NOT NULL COMMENT '表结构ID',
    `field_name` varchar(50) DEFAULT NULL COMMENT '字段名称',
    `field_desc` varchar(50) DEFAULT NULL COMMENT '字段描述',
    `field_type` tinyint(4) NOT NULL COMMENT '字段类型',
    `field_length` varchar(10) NOT NULL COMMENT '字段长度',
    `allow_null` tinyint(4) NOT NULL COMMENT '是否允许为空',
    `creator_id` varchar(50) DEFAULT NULL COMMENT '创建者ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='表字段信息';

DROP TABLE IF EXISTS `nl_system_dict`;

CREATE TABLE `nl_system_dict` (
    `id` varchar(50) AUTO_INCREMENT COMMENT '主键ID',
    `dict_key` varchar(100) NOT NULL COMMENT '参数key值',
    `dict_value` varchar(100) NOT NULL COMMENT '参数值',
    `dict_desc` varchar(50) DEFAULT NULL COMMENT '参数描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数字典表';

DROP TABLE IF EXISTS `nl_app_relation_tablespace_info`;

CREATE TABLE `nl_app_relation_tablespace_info` (
    `id` bigint(20) AUTO_INCREMENT COMMENT '主键',
    `app_id` varchar(50) NOT NULL COMMENT '主键ID',
    `tablespace_id` varchar(50) NOT NULL COMMENT '主键ID',
    `created_time` datetime DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `IDX_NL_APP_RELATION_TABLESPACE_INFO_1` (`app_id`),
    KEY `IDX_NL_APP_RELATION_TABLESPACE_INFO_2` (`tablespace_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

