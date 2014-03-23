-- Project Name : MyClip
-- Date/Time    : 2013/06/02 20:37:59
-- Author       : toru
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- カテゴリ-サイト
drop table if exists CATEGORY_SITE cascade;

create table CATEGORY_SITE (
  CATEGORY_ID INT comment 'カテゴリID'
  , SITE_ID INT comment 'サイトID'
  , constraint CATEGORY_SITE_PKC primary key (CATEGORY_ID,SITE_ID)
) comment 'カテゴリ-サイト' ;

-- カテゴリ
drop table if exists CATEGORY cascade;

create table CATEGORY (
  CATEGORY_ID INT AUTO_INCREMENT comment 'カテゴリID'
  , CATEGORY_NAME VARCHAR(256) comment 'カテゴリ名'
  , constraint CATEGORY_PKC primary key (CATEGORY_ID)
) comment 'カテゴリ' ;

-- タグ
drop table if exists TAG cascade;

create table TAG (
  ARTICLE_ID INT not null comment '記事ID'
  , SEQ INT comment 'SEQ'
  , TAG VARCHAR(256) comment 'タグ'
  , constraint TAG_PKC primary key (ARTICLE_ID,SEQ)
) comment 'タグ' ;

-- 収集記事
drop table if exists COLLECT_ARTICLE cascade;

create table COLLECT_ARTICLE (
  ID INT AUTO_INCREMENT comment '収集記事ID'
  , COLLECT_ID INT comment '収集ID'
  , ARTICLE_ID INT comment '記事ID'
  , constraint COLLECT_ARTICLE_PKC primary key (ID)
) comment '収集記事' ;

-- 収集情報
drop table if exists COLLECT_INFO cascade;

create table COLLECT_INFO (
  COLLECT_ID INT AUTO_INCREMENT comment '収集ID'
  , COLLECT_DATE TIMESTAMP default NOW() comment '収集日時'
  , constraint COLLECT_INFO_PKC primary key (COLLECT_ID)
) comment '収集情報' ;

create index COLLECT_INFO_IX1
  on COLLECT_INFO(COLLECT_DATE);

-- アクセス履歴
drop table if exists ACCESS_HISTORY cascade;

create table ACCESS_HISTORY (
  HISTORY_ID INT AUTO_INCREMENT comment '履歴ID'
  , FROM_IP VARCHAR(32) default 0 not null comment 'アクセス元IP'
  , TO_ARTICLE_ID INT not null comment 'リダイレクト先記事ID'
  , ACCESS_DATE_TIME TIMESTAMP default NOW() not null comment 'アクセス日時'
  , constraint ACCESS_HISTORY_PKC primary key (HISTORY_ID)
) comment 'アクセス履歴' ;

-- 記事
drop table if exists ARTICLE cascade;

create table ARTICLE (
  ARTICLE_ID INT AUTO_INCREMENT comment '記事ID'
  , SITE_ID INT comment 'サイトID'
  , ARTICLE_URL VARCHAR(512) not null comment '記事URL'
  , ARTICLE_TITLE VARCHAR(512) default 'UNKNOWN' not null comment '記事名'
  , ARTICLE_CONTENTS BLOB comment '記事内容'
  , CRETE_DATE_TIME TIMESTAMP comment '配信日時'
  , UPDATE_DATE_TIME TIMESTAMP comment '更新日時'
  , constraint ARTICLE_PKC primary key (ARTICLE_ID)
) comment '記事' CHARACTER SET 'utf8';

create index ARTICLE_IX1
  on ARTICLE(ARTICLE_URL);

-- 収集対象
drop table if exists COLLECT_TARGET cascade;

create table COLLECT_TARGET (
  SITE_ID INT AUTO_INCREMENT comment 'サイトID'
  , URL VARCHAR(512) comment 'URL'
  , SITE_NAME VARCHAR(512) comment 'サイト名'
  , DELETE_FLG CHAR(1) default '0' not null comment '削除フラグ'
  , LAST_PUB_DATE_TIME TIMESTAMP comment '最終発行日時'
  , constraint COLLECT_TARGET_PKC primary key (SITE_ID)
) comment '収集対象' CHARACTER SET 'utf8';

create index COLLECT_TARGET_IX1
  on COLLECT_TARGET(DELETE_FLG);

alter table CATEGORY_SITE
  add constraint CATEGORY_SITE_FK1 foreign key (SITE_ID) references COLLECT_TARGET(SITE_ID)
  on delete cascade;

alter table CATEGORY_SITE
  add constraint CATEGORY_SITE_FK2 foreign key (CATEGORY_ID) references CATEGORY(CATEGORY_ID)
  on delete cascade;

alter table TAG
  add constraint TAG_FK1 foreign key (ARTICLE_ID) references ARTICLE(ARTICLE_ID)
  on delete cascade;

alter table COLLECT_ARTICLE
  add constraint COLLECT_ARTICLE_FK1 foreign key (ARTICLE_ID) references ARTICLE(ARTICLE_ID)
  on delete cascade;

alter table COLLECT_ARTICLE
  add constraint COLLECT_ARTICLE_FK2 foreign key (COLLECT_ID) references COLLECT_INFO(COLLECT_ID)
  on delete cascade;

alter table ARTICLE
  add constraint ARTICLE_FK1 foreign key (SITE_ID) references COLLECT_TARGET(SITE_ID)
  on delete cascade;

alter table ACCESS_HISTORY
  add constraint ACCESS_HISTORY_FK1 foreign key (TO_ARTICLE_ID) references ARTICLE(ARTICLE_ID)
  on delete cascade;

