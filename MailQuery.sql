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
   IDMENSAJE            integer not null,
   IDREMITENTE          integer not null,
   ASUNTO               VARCHAR(40) not null,
   BODY                 VARCHAR(200) not null,
   primary key (IDMENSAJE)
);

create table DESTINATARIOXMENSAJE
(
   IDDESTXMEN integer not null PRIMARY KEY AUTO_INCREMENT,
   IDMENSAJE integer not null,
   IDDESTINATARIO integer not null
   
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

INSERT INTO USUARIOS(IDUSUARIO,NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (1,'FEDE','PALOMERO','HOLA@HOLA','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(IDUSUARIO,NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (2,'FEFE','PALOMERO','HOLA@HOLA','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(IDUSUARIO,NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values (3,'DEFE','PALOMERO','HOLA@HOLA','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');


INSERT INTO MENSAJES(IDMENSAJE,IDREMITENTE,ASUNTO,BODY) values (1,1,'prueba','PRUEBA 1');
INSERT INTO MENSAJES(IDMENSAJE,IDREMITENTE,ASUNTO,BODY) values (2,2,'prueba','PRUEBA 2');
INSERT INTO MENSAJES(IDMENSAJE,IDREMITENTE,ASUNTO,BODY) values (3,1,'prueba','PRUEBA 3');

INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO) values(1,2);
INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO) values(1,3);

INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO) values(2,3);

INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO) values(3,1);



SELECT U.IDUSUARIO,U.NOMBRE,U.APELLIDO,U.EMAIL,U.PASSWORD,U.DIRECCION,U.TELEFONO,U.PAIS,U.PROVINCIA,U.CIUDAD
 FROM USUARIOS AS U JOIN DESTINATARIOXMENSAJE AS D WHERE D.IDMENSAJE=1 AND D.IDDESTINATARIO=U.IDUSUARIO;
