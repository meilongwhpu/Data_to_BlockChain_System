# 数据上链管理平台

## 关于项目

- web项目启动类：[DatatochainApp.java](/datatochain-web/src/main/java/io/nuls/data/DatatochainApp.java)
- 单元测试入口类：[Main.java](/datatochain-web/src/test/java/io/nuls/data/Main.java)
- mysql建表语句：[datatochain.sql](/datatochain-web/src/test/resources/DB/datatochain.sql)

## 表结构

### 【nl_operate_log】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| user_id | varchar(50) |  否  |  | 用户ID |
| oper_time | datetime |  否  |  | 操作时间 |
| oper_subject | varchar(20) |  否  |  | 操作科目 |
| oper_detail | varchar(100) |  否  |  | 操作明细 |
| oper_ip | varchar(20) |  否  |  | 客户端IP地址 |
| remark | varchar(200) |  否  |  | 备注 |

### 【nl_app_info】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| app_name | varchar(50) | 是 |  | 应用名称 |
| app_desc | varchar(50) |  否  |  | 应用介绍 |
| app_key | varchar(50) | 是 |  | 应用key，访问应用下面的数据时使用 |
| create_time | datetime |  否  |  | 创建时间 |
| update_time | datetime |  否  |  | 更新时间 |
| creator_id | varchar(50) |  否  |  | 创建者ID |

### 表空间信息表【nl_tablespace_info】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| tablespace_name | varchar(50) | 是 |  | 表空间名称 |
| tablespace_desc | varchar(50) |  否  |  | 表空间描述 |
| code_mode | tinyint(4) | 是 |  | 编码格式 |
| encryption_mode | tinyint(4) | 是 |  | 加密模式 |
| to_chain | tinyint(4) | 是 |  | 上链位置 |
| create_time | datetime | 是 |  | 创建时间 |
| update_time | datetime |  否  |  | 更新时间 |
| creator_id | varchar(50) |  否  |  | 创建者ID |

### 【nl_tablestructure_info】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| tablespace_id | int(11) | 是 |  | 表空间ID |
| table_name | varchar(50) | 是 |  | 表名称 |
| table_desc | varchar(50) |  否  |  | 表描述 |
| create_time | datetime | 是 |  | 创建时间 |
| update_time | datetime |  否  |  | 更新时间 |
| creator_id | varchar(50) |  否  |  | 创建者ID |
| is_create | tinyint(4) |  否  |  | 是否创建表结构 |

### 表字段信息【nl_tablefield_info】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| table_id | int(11) | 是 |  | 表结构ID |
| field_name | varchar(50) |  否  |  | 字段名称 |
| field_desc | varchar(50) |  否  |  | 字段描述 |
| field_type | tinyint(4) | 是 |  | 字段类型 |
| field_length | int(11) | 是 |  | 字段长度 |
| field_decimal_length | int(11) |  否  |  | 类型小数长度 |
| field_is_key | tinyint(4) |  否  |  | 是否为主键 |
| allow_null | tinyint(4) | 是 |  | 是否允许为空 |
| field_default_value | varchar(50) |  否  |  | 字段默认值 |
| creator_id | varchar(50) |  否  |  | 创建者ID |

### 系统参数字典表【nl_system_dict】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| id | int(11) | 是 | 主键 | 主键ID |
| dict_key | varchar(100) | 是 |  | 参数key值 |
| dict_value | varchar(100) | 是 |  | 参数值 |
| dict_desc | varchar(50) |  否  |  | 参数描述 |

### 【nl_app_relation_tablespace_info】
|字段名 | 类型 | 非空 | 键 | 注释 |
|------ | ---- | --- | --- | ---- |
| app_id | int(11) | 是 |  | 主键ID |
| tablespace_id | int(11) | 是 |  | 主键ID |
