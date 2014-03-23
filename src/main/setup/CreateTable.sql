-- Project Name : MyClip
-- Date/Time    : 2013/06/02 20:37:59
-- Author       : toru
-- RDBMS Type   : MySQL
-- Application  : A5:SQL Mk-2

-- �J�e�S��-�T�C�g
drop table if exists CATEGORY_SITE cascade;

create table CATEGORY_SITE (
  CATEGORY_ID INT comment '�J�e�S��ID'
  , SITE_ID INT comment '�T�C�gID'
  , constraint CATEGORY_SITE_PKC primary key (CATEGORY_ID,SITE_ID)
) comment '�J�e�S��-�T�C�g' ;

-- �J�e�S��
drop table if exists CATEGORY cascade;

create table CATEGORY (
  CATEGORY_ID INT AUTO_INCREMENT comment '�J�e�S��ID'
  , CATEGORY_NAME VARCHAR(256) comment '�J�e�S����'
  , constraint CATEGORY_PKC primary key (CATEGORY_ID)
) comment '�J�e�S��' ;

-- �^�O
drop table if exists TAG cascade;

create table TAG (
  ARTICLE_ID INT not null comment '�L��ID'
  , SEQ INT comment 'SEQ'
  , TAG VARCHAR(256) comment '�^�O'
  , constraint TAG_PKC primary key (ARTICLE_ID,SEQ)
) comment '�^�O' ;

-- ���W�L��
drop table if exists COLLECT_ARTICLE cascade;

create table COLLECT_ARTICLE (
  ID INT AUTO_INCREMENT comment '���W�L��ID'
  , COLLECT_ID INT comment '���WID'
  , ARTICLE_ID INT comment '�L��ID'
  , constraint COLLECT_ARTICLE_PKC primary key (ID)
) comment '���W�L��' ;

-- ���W���
drop table if exists COLLECT_INFO cascade;

create table COLLECT_INFO (
  COLLECT_ID INT AUTO_INCREMENT comment '���WID'
  , COLLECT_DATE TIMESTAMP default NOW() comment '���W����'
  , constraint COLLECT_INFO_PKC primary key (COLLECT_ID)
) comment '���W���' ;

create index COLLECT_INFO_IX1
  on COLLECT_INFO(COLLECT_DATE);

-- �A�N�Z�X����
drop table if exists ACCESS_HISTORY cascade;

create table ACCESS_HISTORY (
  HISTORY_ID INT AUTO_INCREMENT comment '����ID'
  , FROM_IP VARCHAR(32) default 0 not null comment '�A�N�Z�X��IP'
  , TO_ARTICLE_ID INT not null comment '���_�C���N�g��L��ID'
  , ACCESS_DATE_TIME TIMESTAMP default NOW() not null comment '�A�N�Z�X����'
  , constraint ACCESS_HISTORY_PKC primary key (HISTORY_ID)
) comment '�A�N�Z�X����' ;

-- �L��
drop table if exists ARTICLE cascade;

create table ARTICLE (
  ARTICLE_ID INT AUTO_INCREMENT comment '�L��ID'
  , SITE_ID INT comment '�T�C�gID'
  , ARTICLE_URL VARCHAR(512) not null comment '�L��URL'
  , ARTICLE_TITLE VARCHAR(512) default 'UNKNOWN' not null comment '�L����'
  , ARTICLE_CONTENTS BLOB comment '�L�����e'
  , CRETE_DATE_TIME TIMESTAMP comment '�z�M����'
  , UPDATE_DATE_TIME TIMESTAMP comment '�X�V����'
  , constraint ARTICLE_PKC primary key (ARTICLE_ID)
) comment '�L��' CHARACTER SET 'utf8';

create index ARTICLE_IX1
  on ARTICLE(ARTICLE_URL);

-- ���W�Ώ�
drop table if exists COLLECT_TARGET cascade;

create table COLLECT_TARGET (
  SITE_ID INT AUTO_INCREMENT comment '�T�C�gID'
  , URL VARCHAR(512) comment 'URL'
  , SITE_NAME VARCHAR(512) comment '�T�C�g��'
  , DELETE_FLG CHAR(1) default '0' not null comment '�폜�t���O'
  , LAST_PUB_DATE_TIME TIMESTAMP comment '�ŏI���s����'
  , constraint COLLECT_TARGET_PKC primary key (SITE_ID)
) comment '���W�Ώ�' CHARACTER SET 'utf8';

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

