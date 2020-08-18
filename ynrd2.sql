-- 用户信息表
CREATE table tb_users(
	user_id int PRIMARY key auto_increment,
	username VARCHAR(60) not null UNIQUE,
	password VARCHAR(100) not null,
	password_salt VARCHAR(60)  
);

INSERT INTO tb_users(username, password, password_salt) VALUES ('zhangsan', '212cfc217523bb4e1c8359940f34b790', '51065');
INSERT INTO tb_users(username, password, password_salt) VALUES ('lisi', 'b699353e205bc1b833be955f029910af', '40589');
INSERT INTO tb_users(username, password, password_salt) VALUES ('wangwu', '30fdc0804af9ac450c7a72093ef13d99', '31947');
INSERT INTO tb_users(username, password, password_salt) VALUES ('zhaoliu', '3116d1bf1414abf6f8db26b3b62c9706', '36336');
INSERT INTO tb_users(username, password, password_salt) VALUES ('chenqi', 'cf207899f4a35d44b8ffc96af9448daf', '84995');

-- 角色信息表
CREATE TABLE tb_roles(
	role_id int PRIMARY key auto_increment,
	role_name VARCHAR(60) not NULL
);

INSERT INTO tb_roles(role_name) VALUES ('admin');
INSERT INTO tb_roles(role_name) VALUES ('cmanager'); -- 仓管
INSERT INTO tb_roles(role_name) VALUES ('xmanager'); -- 销售
INSERT INTO tb_roles(role_name) VALUES ('kmanager'); -- 客服
INSERT INTO tb_roles(role_name) VALUES ('zmanager'); -- 行政

-- 权限信息表
CREATE TABLE tb_permissions(
	permission_id int PRIMARY KEY auto_increment,  -- 1
	permission_code VARCHAR(60) not null,          -- sys:c:find
	permission_name VARCHAR(60)                    -- 仓库查询
);

INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:c:save', '入库');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:c:delete', '出库');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:c:update', '修改');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:c:find', '查询');

INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:x:save', '新增订单');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:x:delete', '删除订单');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:x:update', '修改订单');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:x:find', '查询订单');

INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:k:save', '新增客户');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:k:delete', '删除客户');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:k:update', '修改客户');
INSERT INTO tb_permissions(permission_code, permission_name) VALUES ('sys:k:find', '查询客户');

-- 用户角色表
CREATE TABLE tb_urs(
	uid int not null,
	rid int not null
	-- PRIMARY key(uid,rid),   -- 尽量减少数据外键关联，减少数据库压力，逻辑部分处理
	-- CONSTRAINT FK_user FOREIGN KEY(uid) REFERENCES tb_users(user_id),
	-- CONSTRAINT FK_role FOREIGN KEY(rid) REFERENCES tb_roles(role_id)
);

INSERT INTO tb_urs(uid, rid) VALUES (1,1);
INSERT INTO tb_urs(uid, rid) VALUES (2,2);
INSERT INTO tb_urs(uid, rid) VALUES (3,3);
INSERT INTO tb_urs(uid, rid) VALUES (4,4);
INSERT INTO tb_urs(uid, rid) VALUES (5,5);

-- 角色权限表
CREATE TABLE tb_rps(
	rid int not null,
	pid int not null
);

INSERT INTO tb_rps(rid, pid) VALUES (1,1);
INSERT INTO tb_rps(rid, pid) VALUES (1,2);
INSERT INTO tb_rps(rid, pid) VALUES (1,3);
INSERT INTO tb_rps(rid, pid) VALUES (1,4);
INSERT INTO tb_rps(rid, pid) VALUES (1,5);
INSERT INTO tb_rps(rid, pid) VALUES (1,6);
INSERT INTO tb_rps(rid, pid) VALUES (1,7);
INSERT INTO tb_rps(rid, pid) VALUES (1,8);
INSERT INTO tb_rps(rid, pid) VALUES (1,9);
INSERT INTO tb_rps(rid, pid) VALUES (1,10);
INSERT INTO tb_rps(rid, pid) VALUES (1,11);
INSERT INTO tb_rps(rid, pid) VALUES (1,12);

-- 给仓管角色分配权限
INSERT INTO tb_rps(rid, pid) VALUES (2,1);
INSERT INTO tb_rps(rid, pid) VALUES (2,2);
INSERT INTO tb_rps(rid, pid) VALUES (2,3);
INSERT INTO tb_rps(rid, pid) VALUES (2,4);
-- 给销售角色分配权限
INSERT INTO tb_rps(rid, pid) VALUES (3,4);
INSERT INTO tb_rps(rid, pid) VALUES (3,5);
INSERT INTO tb_rps(rid, pid) VALUES (3,6);
INSERT INTO tb_rps(rid, pid) VALUES (3,7);
INSERT INTO tb_rps(rid, pid) VALUES (3,8);
INSERT INTO tb_rps(rid, pid) VALUES (3,9);
INSERT INTO tb_rps(rid, pid) VALUES (3,10);
INSERT INTO tb_rps(rid, pid) VALUES (3,11);
INSERT INTO tb_rps(rid, pid) VALUES (3,12);
-- 给客服角色分配权限
INSERT INTO tb_rps(rid, pid) VALUES (4,11);
INSERT INTO tb_rps(rid, pid) VALUES (4,12);
-- 给行政角色分配权限
INSERT INTO tb_rps(rid, pid) VALUES (5,4);
INSERT INTO tb_rps(rid, pid) VALUES (5,8);
INSERT INTO tb_rps(rid, pid) VALUES (5,12);






