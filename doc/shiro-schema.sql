drop table if exists cas_user;
drop table if exists cas_app;
drop table if exists cas_user_app_roles;
drop table if exists cas_organization;
drop table if exists cas_resource;
drop table if exists cas_role;
drop table if exists cas_sessions;

create table cas_sessions (
  id varchar(200),
  session varchar(2000),
  constraint pk_sessions primary key(id)
) charset=utf8 ENGINE=InnoDB;


create table cas_user (
  id bigint auto_increment,
  organization_id bigint,
  username varchar(100),
  password varchar(100),
  salt varchar(100),
  phone varchar(100),
  email varchar(100),
  locked bool default false,
  constraint pk_cas_user primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_cas_user_username on cas_user(username);
create index idx_cas_user_organization_id on cas_user(organization_id);

create table cas_app (
  id bigint auto_increment,
  name varchar(100),
  app_key varchar(100),
  app_secret varchar(100),
  available bool default false,
  constraint pk_cas_app primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_cas_app_app_key on cas_app(app_key);

create table cas_user_app_roles (
  id bigint auto_increment,
  user_id bigint,
  app_id bigint,
  role_ids varchar(100),
  constraint pk_cas_user_app_roles primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index cas_user_app_roles_user_id_app_id on cas_user_app_roles(user_id, app_id);




create table cas_organization (
  id bigint auto_increment,
  name varchar(100),
  parent_id bigint,
  parent_ids varchar(100),
  available bool default false,
  constraint pk_cas_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_cas_organization_parent_id on cas_organization(parent_id);
create index idx_cas_organization_parent_ids on cas_organization(parent_ids);


create table cas_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_cas_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_cas_resource_parent_id on cas_resource(parent_id);
create index idx_cas_resource_parent_ids on cas_resource(parent_ids);

create table cas_role (
  id bigint auto_increment,
  role varchar(100),
  description varchar(100),
  resource_ids varchar(100),
  available bool default false,
  constraint pk_cas_role primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_cas_role_resource_ids on cas_role(resource_ids);