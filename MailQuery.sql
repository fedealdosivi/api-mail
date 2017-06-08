CREATE DATABASE `mail`;
use mail;

create table USUARIOS
(
   IDUSUARIO            integer not null,
   NOMBRE               varchar(40) not null,
   APELLIDO             VARCHAR(40) not null,
   EMAIL                VARCHAR(40) not null,
   PASSWORD             VARCHAR(20) not null,
   NICKNAME             VARCHAR(20) not null,
   primary key (IDUSUARIO)
);

insert into USUARIOS(IDUSUARIO,NOMBRE,APELLIDO,EMAIL,PASSWORD,NICKNAME) values ('1','FEDE','PALOMERO','HOLA@HOLA','123456','FEFE');
