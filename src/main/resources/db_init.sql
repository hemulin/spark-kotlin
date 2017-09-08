DROP DATABASE if exists shiro;
CREATE DATABASE shiro;
USE shiro;

CREATE TABLE users (
  id varchar(32),
  email varchar(320),
  password varchar(100),
  password_salt varchar(100),
  CONSTRAINT pk_users PRIMARY KEY(id)
) charset=utf8 ENGINE=InnoDB;
CREATE UNIQUE INDEX idx_users_username ON users(email);

CREATE TABLE user_roles(
  id bigint auto_increment,
  user_id varchar(32),
  role_name varchar(100),
  CONSTRAINT pk_user_roles PRIMARY KEY(id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id)
) charset=utf8 ENGINE=InnoDB;
CREATE UNIQUE INDEX idx_user_roles ON user_roles(user_id, role_name);

CREATE TABLE roles_permissions(
  id bigint auto_increment,
  role_name varchar(100),
  permission varchar(100),
  CONSTRAINT pk_roles_permissions PRIMARY KEY(id)
) charset=utf8 ENGINE=InnoDB;
CREATE UNIQUE INDEX idx_roles_permissions ON roles_permissions(role_name, permission);