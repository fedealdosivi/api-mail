CREATE DATABASE `mail`;
use mail;


DROP TABLE IF EXISTS MENSAJES;
DROP TABLE IF EXISTS USUARIOS; 

create table USUARIOS
(
   IDUSUARIO            integer AUTO_INCREMENT not null,
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
   IDMENSAJE            integer AUTO_INCREMENT not null,
   IDREMITENTE          integer not null,
   IDDESTINATARIO       integer not null,
   ASUNTO               VARCHAR(40) not null,
   BODY                 VARCHAR(200) not null,
   ELIMINADO            boolean default false,
   primary key (IDMENSAJE)
);

alter table MENSAJES add constraint FK_MENSAJE_REMITENTE foreign key (IDREMITENTE)
references USUARIOS (IDUSUARIO);

alter table MENSAJES add constraint FK_MENSAJE_DESTINATARIO foreign key (IDDESTINATARIO)
references USUARIOS (IDUSUARIO);


INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('FEDE','PALOMERO','admin@admin','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('FEFE','PALOMERO','user@user','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('DEFE','PALOMERO','prueba@prueba','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('JEJE','PALOMERO','HOLIS@HOLIS','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');


INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,4,'prueba','PRUEBA 1');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (2,3,'prueba','PRUEBA 2');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,2,'prueba','PRUEBA 3');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,4,'segunda','PRUEBA 4');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (4,1,'mensaje','recibido');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (4,1,'mensaje','recibido');

SELECT * FROM USUARIOS;
SELECT * FROM MENSAJES;

UPDATE MENSAJES SET ELIMINADO = TRUE WHERE IDMENSAJE = 1;
UPDATE MENSAJES SET ELIMINADO = TRUE WHERE IDMENSAJE = 6;
select * from MENSAJES where ELIMINADO=TRUE;

Select m.IDMENSAJE, m.ASUNTO,m.BODY, uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,
uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD,uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,
uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD FROM MENSAJES as m join USUARIOS as uD on m.IDDESTINATARIO = uD.IDUSUARIO
join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO WHERE m.IDDESTINATARIO=4 AND m.ELIMINADO=FALSE;

