create schema IF NOT EXISTS report collate utf8mb4_general_ci;
use
report;

create table if not exists report.filter_info
(
    id          bigint            not null comment '主键',
    table_code  varchar(100)      null comment '表编码',
    column_code varchar(100)      null comment '字段码',
    column_name varchar(100)      null comment '字段名称',
    type        varchar(32)       null comment '类型',
    dim_class   varchar(100)      null comment '对应维度值',
    sort        int               null comment '排序',
    is_required tinyint default 0 null comment '是否必须'
    ) comment '表筛选项表';

create table if not exists report.meta_info_mapping
(
    sys_code      varchar(100) null comment '元数据编码',
    sys_name      varchar(100) null comment '元数据名称',
    sys_dim_class varchar(100) null comment '元数据类型'
    ) comment '元数据<下拉框使用>';