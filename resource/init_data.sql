USE `car`;
TRUNCATE `car_type`;
INSERT INTO `car_type`(id, name, remark, creator_id)
VALUES (1, '经济型轿车', '一种发动机排量较小、车身尺寸较小、油耗相对较经济、价格也相应较低的一类轿车。', 1),
       (11, '舒适型轿车', '配置就在基本型和豪华型配置之间，要比豪华型差，但是比基本型好。', 1),
       (21, '豪华型轿车', '豪华型则是比较全面多功能的，多了些附加值、舒适性和娱乐性等的部件。倒车雷达，天窗，后视镜除霜，安全气囊，大屏幕液晶，多碟cd/dvd等都会有。', 1),
       (31, 'MPV', '商务车即多用途汽车。集轿车、旅行车和厢式货车的功能于一身，车内每个座椅都可调整，并有多种组合的方式', 1),
       (41, 'SUV', 'SUV是指运动型多用途汽车，不同于可在崎岖地面使用的ORV越野车,即城郊多用途汽车，是一种拥有旅行车般的空间机能，配以货卡车的越野能力的车型。', 1);

TRUNCATE `car_info`;
INSERT INTO `car_info`(id, plate_number, car_type, brand, manufacturer, produced_date, car_color, image, cash_pledge,
                       money, creator_id)
VALUES (1, '苏AT00001', 41, '宝马 X6', '宝马', '2022-01-01', '红色', 'baoma.jpg', 3000, 500, 1),
       (11, '苏AT00002', 1, '迈腾', '上汽大众', '2022-01-01', '黑色', 'maiteng.jpg', 1000, 280, 1),
       (21, '苏AT00003', 11, '奥迪 A6L', '奥迪', '2022-01-01', '黑色', 'aodi.jpg', 3000, 400, 1),
       (31, '苏AT00004', 41, '奔驰 GLC', '奔驰', '2022-01-01', '黑色', 'benchi.jpg', 3000, 500, 1);

TRUNCATE `carousel`;
INSERT INTO `carousel`(id, image_url, remark)
VALUES (1, 'aodi.jpg', '奥迪'),
       (11, 'baoma.jpg', '宝马'),
       (21, 'benchi.jpg', '奔驰');

TRUNCATE `customer`;
INSERT INTO `customer`(id, username, password, activate, email, phone, gender, money)
VALUES (1, 'customer', '123456', 'on', 'customer@car.com', '17612341111', '男', 2000),
       (11, 'test1', '123456', 'on', 'test1@car.com', '17612341112', '女', 3000),
       (21, 'test2', '123456', 'on', 'test2@car.com', '17612341113', '男', 1000),
       (31, 'test3', '123456', 'on', 'test3@car.com', '17612341114', '女', 0);

TRUNCATE `menu`;
INSERT INTO `menu`(id, menu_name, menu_level, menu_parent_id, menu_url, menu_activate, remark)
VALUES (1, '员工管理', 1, 0, '无', 'on', '无'),
       (11, '员工信息管理', 2, 1, 'userView', 'on', '员工信息'),
       (21, '权限管理', 1, 0, '无', 'on', '这个是权限管理的根目录'),
       (31, '菜单管理', 2, 11, 'menuView', 'on', '菜单管理'),
       (41, '角色管理', 2, 11, 'roleView', 'on', '角色管理'),
       (51, '客户管理', 1, 0, 'customer', 'on', '客户信息管理'),
       (61, '汽车管理', 1, 0, '无', 'on', '汽车管理'),
       (71, '汽车信息管理', 2, 61, 'carInfo', 'on', '汽车信息'),
       (81, '汽车类型管理', 2, 61, 'carType', 'on', '汽车类型信息'),
       (91, '订单管理', 1, 0, 'order', 'on', '订单信息管理'),
       (101, '检查单管理', 1, 0, 'inspect', 'on', '检查单管理'),
       (111, '评价管理', 1, 0, 'evaluation', 'on', '评价管理'),
       (121, '网站运营管理', 1, 0, '无', 'on', '网站运营管理'),
       (131, '公告管理', 2, 121, 'notice', 'on', '公告管理'),
       (141, '轮播管理', 2, 121, 'carousel', 'on', '轮播管理'),
       (151, '登录日志', 1, 0, 'loginLog', 'on', '登录日志'),
       (161, '个人信息', 1, 0, 'userInfo', 'on', '个人信息');

TRUNCATE `role`;
INSERT INTO `role`(id, role_name, activate, remark)
VALUES (1, '管理员', 'on', '管理员'),
       (11, '经理', 'on', '经理'),
       (21, '销售', 'on', '销售'),
       (31, '后勤', 'on', '后勤工作人员'),
       (41,'运维','on','网站运维人员');

TRUNCATE `menu_role`;
INSERT INTO `menu_role`(menu_id, role_id)
VALUES (1, 1),
       (11, 1),
       (21, 1),
       (31, 1),
       (41, 1),
       (51, 1),
       (61, 1),
       (71, 1),
       (81, 1),
       (91, 1),
       (101, 1),
       (111, 1),
       (121, 1),
       (131, 1),
       (141, 1),
       (151, 1),
       (161, 1),
       (51, 11),
       (61, 11),
       (71, 11),
       (81, 11),
       (91, 11),
       (101, 11),
       (111, 11),
       (161, 11),
       (61, 21),
       (71, 21),
       (81, 21),
       (91, 21),
       (161, 21),
       (61, 31),
       (71, 31),
       (81, 31),
       (101, 31),
       (161, 31),
       (111, 41),
       (121, 41),
       (131, 41),
       (141, 41),
       (161, 41);

TRUNCATE `notice`;
INSERT INTO `notice`(id, title, context, remark)
VALUES (1, '新车上架', '2021款上汽大众帕萨特280TSI到底有什么配置，到底值不值。今天我们就细品一下。总之，现在开帕萨特应该不用考虑带不带头盔的问题了。', NULL),
       (11, '租车活动', '租车送水', NULL),
       (21, '发布公告', '租车促销', NULL),
       (31, '新车上架', '特斯拉 Model3、小鹏 P1、保时捷 911 现已入店，欢迎新老用户前来选定', NULL);

TRUNCATE car.`user`;
INSERT INTO car.`user`(id, username, password, activate, email, phone, gender)
VALUES (1, 'admin', '123456', 'on', 'admin@back.com', '17600001111', '男'),
       (11, 'manage', '123456', 'on', 'manage@back.com', '17600001112', NULL),
       (21, 'sale', '123456', 'on', 'sale@back.com', '17600001113', NULL),
       (31, 'supporter', '123456', 'on', 'supporter@back.com', '17600001114', NULL),
       (41, 'worker', '123456', 'on', 'worker@back.com', '17600001115', NULL);

TRUNCATE `user_role`;
INSERT INTO `user_role`(user_id, role_id)
VALUES (1, 1),
       (11, 11),
       (21, 21),
       (31, 31),
       (41, 41);