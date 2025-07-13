create sequence user_seq start with 1 increment by 1 nocache nocycle;

create table users (
   id       number(19) primary key,
   name     varchar2(255) not null,
   email    varchar2(100) not null unique,
   password varchar2(255) not null,
   role     varchar2(20) not null
);