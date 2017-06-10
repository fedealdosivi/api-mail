CREATE DATABASE `mail`;
use mail;

DROP TABLE IF EXISTS DESTINATARIOXMENSAJE;
DROP TABLE IF EXISTS MENSAJES;
DROP TABLE IF EXISTS USUARIOS; 

create table USUARIOS
(
   IDUSUARIO            integer not null,
   NOMBRE               varchar(40) not null,
   APELLIDO             VARCHAR(40) not null,
   EMAIL                VARCHAR(40) not null,
   PASSWORD             VARCHAR(20) not null,
   DIRECCION            VARCHAR(20) not null,
   TELEFONO             integer not null,
   PAIS                 VARCHAR(20) not null,
   PROVINCIA            VARCHAR(20) not null,
   CIUDAD               VARCHAR(20) not null,
   primary key (IDUSUARIO)
);

create table MENSAJES
(
   IDMENSAJE integer not null,
   IDREMITENTE integer not null,
   ASUNTO VARCHAR(40) not null,
   BODY text not null,
   primary key (IDMENSAJE)
);

create table DESTINATARIOXMENSAJE
(
   IDMENSAJE integer not null,
   IDDESTINATARIO integer not null,
   primary key(IDMENSAJE,IDDESTINATARIO)
);

alter table MENSAJES add constraint FK_MENSAJE_REMITENTE foreign key (IDREMITENTE)
references USUARIOS (IDUSUARIO);

alter table DESTINATARIOXMENSAJE add constraint FK_MENSAJE_DESTINATARIO foreign key (IDMENSAJE)
references MENSAJES (IDMENSAJE);

alter table DESTINATARIOXMENSAJE add constraint FK_DESTINATARIO_MENSAJE foreign key (IDDESTINATARIO)
references USUARIOS (IDUSUARIO);

DROP TRIGGER IF EXISTS Tig_DESTXMENSAJE;

delimiter $$
CREATE TRIGGER Tig_DESTXMENSAJE
AFTER 
DELETE
   ON MENSAJES FOR EACH ROW

BEGIN

   
	delete from DESTINATARIOXMENSAJE
   
	where DESTINATARIOXMENSAJE.IDMENSAJE = old.IDMENSAJE;



END

$$

insert into USUARIOS(IDUSUARIO,NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (1,'FEDE','PALOMERO','HOLA@HOLA','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
