/*==============================================================*/
/* create database : data2chain                                          */
/*==============================================================*/

CREATE DATABASE IF NOT EXISTS `data2chain` CHARACTER SET utf8 COLLATE utf8_general_ci;


drop table if exists nl_user_info;

drop table if exists nl_comp_base_info;

drop table if exists nl_user_base_info;

drop table if exists nl_assets_info;

drop table if exists nl_assets_flow_info;

drop table if exists nl_operate_log;

drop table if exists nl_app_info;

drop table if exists nl_app_relation_tablespace_info;

drop table if exists nl_tablespace_info;

drop table if exists nl_tablestructure_info;

drop table if exists nl_tablefield_info;

drop table if exists nl_system_dict;


/*==============================================================*/
/* table: nl_user_info                                          */
/*==============================================================*/
create table nl_user_info
(
   id                   int(11) not null AUTO_INCREMENT,
   user_name            varchar(100) not null comment '用户名',
   tel            varchar(50) not null comment '联系电话',
   password              varchar(50) not null comment '密码',
   account_no              varchar(50) not null comment '资产账户',
   open_id              varchar(50) not null comment '用户的openId，访问应用时使用',
   user_type              TINYINT not null default 1 comment '用户类型：1-私人用户；2-对公用户',
   is_real_name           TINYINT not null default 0 comment '是否实名：0-未实名，1-已实名',
   user_status            TINYINT not null default 1 comment '用户状态：1：正常；2-冻结',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table nl_user_info comment '用户信息表';

/*==============================================================*/
/* table: nl_comp_base_info                                    */
/*==============================================================*/
create table nl_comp_base_info
(
   id                   int(11) not null AUTO_INCREMENT,
   user_id              varchar(50) not null comment '用户ID',
   comp_name            varchar(100) not null comment '公司名称',
   comp_address         varchar(300) not null comment '公司地址',
   social_code          varchar(100) null comment '社会统一编码',
   business_license     varchar(100) null comment '营业执照',
   contacts             varchar(50) null comment '联系人姓名',
   contract_phone       varchar(50) null comment '联系人电话',
   primary key (id)
);

alter table nl_comp_base_info comment '企业基本信息表';

/*==============================================================*/
/* table: nl_user_base_info                                        */
/*==============================================================*/
create table nl_user_base_info
(
   id                   int(11) not null AUTO_INCREMENT,
   user_id              varchar(50) not null comment '用户ID',
   real_name            varchar(100) not null comment '姓名',
   mail                 varchar(50) null comment '邮箱地址',
   identity_card       varchar(100) not null comment '身份证号',
   primary key (id)
);

alter table nl_user_base_info comment '个人基本信息表';

/*==============================================================*/
/* table: nl_assets_info                                            */
/*==============================================================*/
create table nl_assets_info
(
   id                   int(11) not null AUTO_INCREMENT,
   assets_id            varchar(50) not null comment '个人资产ID',
   account_no           varchar(50) not null comment '资产账户',
   assets_type          varchar(20) not null comment '资产类型',
   balance              decimal(30,8) not null comment '资产余额',
   income_sum           decimal(30,8) not null comment '充值总金额',
   outcome_sum          decimal(30,8) not null comment '消费总金额',
   status                TINYINT not null default 1 comment '资产账户状态',
   update_time           date not null comment '更新时间',
   remark                varchar(200) not null comment '备注',
   primary key (id)
);

alter table nl_assets_info comment '资产信息表';

/*==============================================================*/
/* table: nl_assets_flow_info                                 */
/*==============================================================*/
create table nl_assets_flow_info
(
   id                   int(11) not null AUTO_INCREMENT,
   assets_id            varchar(50) not null comment '个人资产ID',
   type                 varchar(20) not null comment '明细类型：in：充值；out：消费',
   amount              decimal(30,8) not null comment '明细金额',
   create_time       date not null  comment '时间',
   remark            varchar(200) null comment '备注',
   primary key (id)
);

alter table nl_assets_flow_info comment '资产明细流水表';

/*==============================================================*/
/* table: nl_operate_log                                        */
/*==============================================================*/
create table nl_operate_log
(
   id                   int(11) not null AUTO_INCREMENT,
   user_id              varchar(50) not null comment '用户ID',
   oper_time            datetime not null comment '操作时间',
   oper_subject          varchar(20) not null comment '操作科目',
   oper_detail          varchar(100) not null comment '操作科目',
   oper_ip              varchar(20) not null comment '客户端IP地址',
   remark               varchar(200) comment '备注',
   primary key (id)
);

alter table nl_operate_log comment '操作日志';

/*==============================================================*/
/* table: nl_app_info                                  */
/*==============================================================*/
create table nl_app_info
(
   id                   int(11) not null AUTO_INCREMENT,
   app_name            varchar(50) not null comment '应用名称',
   app_desc             varchar(50) comment '应用介绍',
   app_key               varchar(50) not null comment '应用key，访问应用下面的数据时使用',
   create_time            date not null comment '创建时间',
   update_time           date not null comment '更新时间',
   creator_id           varchar(50) not null comment '用户ID',
   primary key (id)
);

alter table nl_app_info comment '应用信息表';

/*==============================================================*/
/* table: nl_app_relation_tablespace_info                                  */
/*==============================================================*/
create table nl_app_relation_tablespace_info 
(
   id                   int(11) not null AUTO_INCREMENT,
   app_id               int(11) not null comment '应用ID',
   tablespace_id         varchar(50) not null comment '表空间ID',
   relation_time         date not null comment '关联时间',
   del_relation          TINYINT not null default 0 comment '解除关联：0:-未解除;1-解除',
   creator_id            varchar(50) not null comment '用户ID',
   primary key (id)
);

alter table nl_app_relation_tablespace_info comment '应用关联表空间信息表';

/*==============================================================*/
/* table: nl_tablespace_info                                          */
/*==============================================================*/
create table nl_tablespace_info
(
   id                   int(11) not null AUTO_INCREMENT,
   tablespace_name      varchar(50) not null comment '表空间名称',
   tablespace_desc      varchar(50) comment '表空间描述',
   code_mode             varchar(10) not null comment '编码格式:ASCII,ISO-8859-1,GB2312,GBK,GB18030,UTF-16,UTF-8',
   encryption_mode       varchar(10) not null comment '加密模式：NO,BASE64, MD5, DES,AES,HMAC,SHA,RSA,PBE',
   to_chain           varchar(10) not null  comment '上链位置：MAIN-NET,TEST-NET',
   create_time         date not null comment '创建时间',
   update_time         date not null comment '更新时间',
   creator_id          varchar(50) not null comment '用户ID',
   primary key (id)
);

alter table nl_tablespace_info comment '表空间信息表';

/*==============================================================*/
/* table: nl_tablestructure_info                                    */
/*==============================================================*/
create table nl_tablestructure_info
(
   id                   int(11) not null AUTO_INCREMENT,
   tablespace_id        int(11) not null comment '表空间ID',
   table_name            varchar(50)  not null comment '表名称',
   table_desc              varchar(50) comment '表描述',
   create_time         date not null comment '创建时间',
   update_time         date not null comment '更新时间',
   creator_id          varchar(50) not null comment '用户ID',
   is_create           TINYINT not null default 0 comment '是否生成表结构：0:-否；1-是',
   primary key (id)
);

alter table nl_tablestructure_info comment '表结构信息表';

/*==============================================================*/
/* table: nl_tablefield_info                                   */
/*==============================================================*/
create table nl_tablefield_info
(
   id                   int(11) not null AUTO_INCREMENT,
   table_id             int(11)  not null comment '表结构ID',
   field_name            varchar(50)  not null comment '字段名称',
   field_desc             varchar(50)  not null comment '字段名称',
   field_type             varchar(10)  not null comment '字段类型:TINYINT,FLOAT,DECIMAL,date,TIMESTAMP,CHAR,VARCHAR,BLOB,TEXT',
   field_length            TINYINT not null default 0 comment '字段长度',
   field_is_key            TINYINT not null default 0 comment '是否为主键：0:-否；1-是', 
   field_decimal_length    TINYINT not null default 0 comment '类型小数长度',
   allow_null              TINYINT not null default 0 comment '是否允许为空：0:-允许；1-不允许',
   field_default_value     varchar(50)  not null comment '字段默认值',
   creator_id           varchar(50) not null comment '用户ID',
   primary key (id)
);

alter table nl_tablefield_info comment '表字段信息表';

/*==============================================================*/
/* table: nl_system_dict                                 */
/*==============================================================*/
create table nl_system_dict
(
   id                   int(11) not null AUTO_INCREMENT,
   dict_key              varchar(100) not null comment '参数key值',
   dict_value          varchar(100) not null comment '参数值',
   dict_desc           varchar(50) null comment '参数描述',
   primary key (id)
);

alter table nl_system_dict comment '系统参数字典表';