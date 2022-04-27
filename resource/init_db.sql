CREATE DATABASE IF NOT EXISTS `car` DEFAULT CHARACTER SET utf8mb4;
USE `car`;

DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info`
(
    id            BIGINT(12)   NOT NULL AUTO_INCREMENT COMMENT '车辆 ID',
    plate_number  VARCHAR(20)           DEFAULT NULL COMMENT '车牌',
    car_type      INTEGER(11)           DEFAULT NULL COMMENT '车辆类型',
    brand         VARCHAR(255) NOT NULL COMMENT '品牌',
    manufacturer  VARCHAR(255) NOT NULL COMMENT '制造商',
    produced_date DATE                  DEFAULT NULL COMMENT '生产日期',
    car_color     VARCHAR(40)           DEFAULT NULL COMMENT '车辆颜色',
    image         VARCHAR(255)          DEFAULT NULL COMMENT '图片',
    money         DOUBLE       NOT NULL DEFAULT 0 COMMENT '租金',
    cash_pledge   DOUBLE       NOT NULL COMMENT '押金',
    remark        VARCHAR(510)          DEFAULT NULL COMMENT '描述',
    status        VARCHAR(50)  NOT NULL DEFAULT '未出租' COMMENT '车辆状态',
    fversion      INTEGER(11)           DEFAULT 0 COMMENT '版本',
    create_time   TIMESTAMP             DEFAULT now() COMMENT '创建时间',
    modify_time   TIMESTAMP             DEFAULT NULL COMMENT '修改时间',
    creator_id    INTEGER(11)           DEFAULT 0 COMMENT '创建人',
    modify_id     INTEGER(11)           DEFAULT NULL COMMENT '修改人',
    deleted       TINYINT(1)            DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '车辆 ID 主键'
)
    COMMENT '车辆表'
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `car_order`;
CREATE TABLE `car_order`
(
    id              INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT '订单 ID',
    order_number    VARCHAR(64)          DEFAULT NULL COMMENT '订单号',
    car_info_id     INTEGER(11) NOT NULL COMMENT '车辆 ID',
    customer_id     INTEGER(11) NOT NULL COMMENT '客户 ID',
    customer_id_num VARCHAR(20) NOT NULL DEFAULT '41010120220101123X' COMMENT '客户身份证号码',
    start_date      TIMESTAMP            DEFAULT NULL COMMENT '订单开始时间',
    end_date        TIMESTAMP            DEFAULT NULL COMMENT '订单结束时间',
    out_date        TIMESTAMP            DEFAULT NULL COMMENT '车辆出库时间',
    in_date         TIMESTAMP            DEFAULT NULL COMMENT '车辆入库时间',
    money           DECIMAL(10, 2)       DEFAULT NULL COMMENT '汽车租金',
    cash_pledge     DECIMAL(10, 0)       DEFAULT NULL COMMENT '押金',
    fine            DECIMAL(10, 2)       DEFAULT 0.0 COMMENT '罚金',
    penalty         DECIMAL(10, 2)       DEFAULT 0.0 COMMENT '滞纳金',
    total           DECIMAL(10, 2)       DEFAULT 0.0 COMMENT '总金额',
    tenancy_term    DECIMAL(10, 2)       DEFAULT 0 COMMENT '租期',
    feedback        VARCHAR(400)         DEFAULT NULL COMMENT '处理意见',
    state           INTEGER(11)          DEFAULT NULL COMMENT '订单状态',
    fversion        INTEGER(11)          DEFAULT 0 COMMENT '版本',
    create_time     TIMESTAMP            DEFAULT now() COMMENT '创建时间',
    modify_time     TIMESTAMP            DEFAULT NULL COMMENT '修改时间',
    creator_id      INTEGER(11)          DEFAULT 0 COMMENT '创建人',
    modify_id       INTEGER(11)          DEFAULT NULL COMMENT '修改人',
    deleted         TINYINT(1)           DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '订单 ID 主键',
    UNIQUE KEY uni_ord_num (order_number) USING BTREE COMMENT '订单号唯一键'
)
    COMMENT '订单表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `car_assess`;
CREATE TABLE `car_assess`
(
    id           INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT '评价 ID',
    order_number VARCHAR(64)  DEFAULT NULL COMMENT '订单号',
    car_info_id  INTEGER(11) NOT NULL COMMENT '车辆 ID',
    customer_id  INTEGER(11) NOT NULL COMMENT '客户 ID',
    remark       VARCHAR(500) DEFAULT '系统默认评价' COMMENT '详情',
    state        INTEGER(11)  DEFAULT 0 COMMENT '评价状态',
    fversion     INTEGER(11)  DEFAULT 0 COMMENT '版本',
    create_time  TIMESTAMP    DEFAULT now() COMMENT '创建时间',
    modify_time  TIMESTAMP    DEFAULT NULL COMMENT '修改时间',
    creator_id   INTEGER(11)  DEFAULT 0 COMMENT '创建人',
    modify_id    INTEGER(11)  DEFAULT NULL COMMENT '修改人',
    deleted      TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '评价 ID 主键'
)
    COMMENT '评价表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `car_type`;
CREATE TABLE `car_type`
(
    id          INTEGER(11)  NOT NULL AUTO_INCREMENT COMMENT '车型 ID',
    name        VARCHAR(255) NOT NULL COMMENT '类型名称',
    remark      VARCHAR(500) NOT NULL COMMENT '详情',
    fversion    INTEGER(11) DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP   DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP   DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11) DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11) DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)  DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '车型 ID 主键'
)
    COMMENT '车型表'
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel`
(
    id        INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT '轮播图 ID',
    image_url VARCHAR(200) DEFAULT NULL COMMENT '图片 URL',
    remark    VARCHAR(200) DEFAULT NULL COMMENT '注释',
    deleted   TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '轮播图 ID 主键'
)
    COMMENT '轮播图表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `check`;
CREATE TABLE `check`
(
    id           INTEGER(11)  NOT NULL AUTO_INCREMENT COMMENT '检修单 ID',
    order_id     INTEGER(11)  NOT NULL COMMENT '订单 ID',
    order_number VARCHAR(64)  NOT NULL COMMENT '订单编号',
    question     VARCHAR(500) NOT NULL DEFAULT '用户未发现异常' COMMENT '问题',
    money        DECIMAL(10, 2)        DEFAULT 0 COMMENT '赔付金额',
    remark       VARCHAR(500)          DEFAULT '无' COMMENT '详细经过',
    state        INTEGER(11)           DEFAULT 0 COMMENT '检修单状态',
    fversion     INTEGER(11)           DEFAULT 0 COMMENT '版本',
    create_time  TIMESTAMP             DEFAULT now() COMMENT '创建时间',
    modify_time  TIMESTAMP             DEFAULT NULL COMMENT '修改时间',
    creator_id   INTEGER(11)           DEFAULT 0 COMMENT '创建人',
    modify_id    INTEGER(11)           DEFAULT NULL COMMENT '修改人',
    deleted      TINYINT(1)            DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '检修单 ID 主键'
)
    COMMENT '检修单表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`
(
    id          INTEGER(11)    NOT NULL AUTO_INCREMENT COMMENT '顾客 ID',
    username    VARCHAR(255)   NOT NULL COMMENT '顾客名',
    password    VARCHAR(255)   NOT NULL COMMENT '密码',
    activate    VARCHAR(200)            DEFAULT NULL COMMENT '激活标记',
    email       VARCHAR(255)            DEFAULT NULL COMMENT '邮箱',
    phone       VARCHAR(255)            DEFAULT NULL COMMENT '手机号码',
    gender      VARCHAR(10)             DEFAULT NULL COMMENT '性别',
    money       DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '余额',
    freeze      DECIMAL(10, 2)          DEFAULT 0 COMMENT '余额',
    id_number   VARCHAR(18)             DEFAULT NULL COMMENT '身份证号',
    address     VARCHAR(255)            DEFAULT NULL COMMENT '住址',
    profession  VARCHAR(255)            DEFAULT NULL COMMENT '职业',
    remark      VARCHAR(500)            DEFAULT NULL COMMENT '备注',
    fversion    INTEGER(11)             DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP               DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP               DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11)             DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11)             DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)              DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '顾客 ID 主键'
)
    COMMENT '顾客表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`
(
    id          INTEGER(11) NOT NULL AUTO_INCREMENT COMMENT '日志 ID',
    user        INTEGER(11) NOT NULL COMMENT '登录人',
    login_time  TIMESTAMP   NOT NULL COMMENT '登录时间',
    login_ip    VARCHAR(32) NOT NULL COMMENT '登录 IP',
    fversion    INTEGER(11) DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP   DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP   DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11) DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11) DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)  DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '日志 ID 主键'
)
    COMMENT '登录日志表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    id             INTEGER(20)  NOT NULL AUTO_INCREMENT COMMENT '菜单 ID',
    menu_name      VARCHAR(255) NOT NULL COMMENT '菜单名',
    menu_level     INTEGER(20)  NOT NULL COMMENT '菜单等级',
    menu_parent_id INTEGER(20)  NOT NULL COMMENT '父菜单',
    menu_url       VARCHAR(255) NOT NULL COMMENT 'url',
    menu_activate  VARCHAR(255) NOT NULL COMMENT '是否激活',
    remark         VARCHAR(500) DEFAULT NULL COMMENT '备注',
    fversion       INTEGER(11)  DEFAULT 0 COMMENT '版本',
    create_time    TIMESTAMP    DEFAULT now() COMMENT '创建时间',
    modify_time    TIMESTAMP    DEFAULT NULL COMMENT '修改时间',
    creator_id     INTEGER(11)  DEFAULT 0 COMMENT '创建人',
    modify_id      INTEGER(11)  DEFAULT NULL COMMENT '修改人',
    deleted        TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '菜单 ID 主键'
)
    COMMENT '菜单表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`
(
    menu_id INTEGER(20) NOT NULL COMMENT '菜单 ID',
    role_id INTEGER(20) NOT NULL COMMENT '角色 ID',
    PRIMARY KEY pk (menu_id, role_id) USING BTREE COMMENT '菜单角色表主键'
)
    COMMENT '菜单_角色表'
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    id          INTEGER(12)  NOT NULL AUTO_INCREMENT COMMENT '公告 ID',
    title       VARCHAR(200) NOT NULL COMMENT '标题',
    context     VARCHAR(500) NOT NULL COMMENT '内容',
    remark      VARCHAR(500) DEFAULT NULL COMMENT '备注',
    fversion    INTEGER(11)  DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP    DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP    DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11)  DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11)  DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '公告 ID 主键'
)
    COMMENT '公告表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    id          INTEGER(20)  NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
    role_name   VARCHAR(200) NOT NULL COMMENT '角色名称',
    activate    VARCHAR(200) NOT NULL COMMENT '激活标记',
    remark      VARCHAR(500) DEFAULT NULL COMMENT '备注',
    fversion    INTEGER(11)  DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP    DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP    DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11)  DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11)  DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '角色 ID 主键'
)
    COMMENT '角色表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    id          INTEGER(11)  NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
    username    VARCHAR(255) NOT NULL COMMENT '用户名',
    password    VARCHAR(255) NOT NULL COMMENT '密码',
    activate    VARCHAR(200) DEFAULT NULL COMMENT '激活标记',
    email       VARCHAR(255) DEFAULT NULL COMMENT '邮箱',
    phone       VARCHAR(255) DEFAULT NULL COMMENT '手机号码',
    gender      VARCHAR(10)  DEFAULT NULL COMMENT '性别',
    remark      VARCHAR(500) DEFAULT NULL COMMENT '备注',
    fversion    INTEGER(11)  DEFAULT 0 COMMENT '版本',
    create_time TIMESTAMP    DEFAULT now() COMMENT '创建时间',
    modify_time TIMESTAMP    DEFAULT NULL COMMENT '修改时间',
    creator_id  INTEGER(11)  DEFAULT 0 COMMENT '创建人',
    modify_id   INTEGER(11)  DEFAULT NULL COMMENT '修改人',
    deleted     TINYINT(1)   DEFAULT 0 COMMENT '删除标记，0 代表未删除，1 代表已删除',
    PRIMARY KEY pk_id (id) USING BTREE COMMENT '用户 ID 主键'
)
    COMMENT '用户表'
    ENGINE = InnoDB
    AUTO_INCREMENT = 10
    DEFAULT CHARACTER SET = utf8mb4;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    user_id INTEGER(20) NOT NULL COMMENT '用户 ID',
    role_id INTEGER(20) NOT NULL COMMENT '角色 ID',
    PRIMARY KEY pk (user_id, role_id) USING BTREE COMMENT '用户角色表主键'
)
    COMMENT '用户_角色表'
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4;

DROP VIEW IF EXISTS `check_view`;
CREATE VIEW `check_view` AS
SELECT c.*,
       ci.plate_number AS car_plate_number,
       cus.id          AS customer_id,
       cus.username    AS customer_name
FROM car.`check` c
         LEFT JOIN car.car_order co ON c.order_id = co.id
         LEFT JOIN car.car_info ci ON co.car_info_id = ci.id
         LEFT JOIN car.customer cus ON co.customer_id = cus.id;

SET @@auto_increment_increment = 10;