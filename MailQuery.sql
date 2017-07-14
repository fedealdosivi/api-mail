DROP DATABASE IF EXISTS `mail`;
CREATE DATABASE `mail`;
use mail;

DROP TABLE IF EXISTS DESTINATARIOXMENSAJE;
DROP TABLE IF EXISTS MENSAJES;
DROP TABLE IF EXISTS USUARIOS; 
/*==============================================================*/
/* Table: USUARIOS                                               */
/*==============================================================*/

create table USUARIOS
(
   IDUSUARIO            integer AUTO_INCREMENT not null,
   NOMBRE               varchar(40) not null,
   APELLIDO             VARCHAR(40) not null,
   EMAIL                VARCHAR(40) UNIQUE not null,
   PASSWORD             VARCHAR(20) not null,
   DIRECCION            VARCHAR(20) not null,
   TELEFONO             integer not null,
   PAIS                 VARCHAR(20) not null,
   PROVINCIA            VARCHAR(20) not null,
   CIUDAD               VARCHAR(20) not null,
   primary key (IDUSUARIO)
);

/*==============================================================*/
/* Table: MENSAJES//DESACTUALIZADO HAY QUE SACAR EL IDDESTINATARIO    */
/*==============================================================*/

create table MENSAJES
(
   IDMENSAJE            integer AUTO_INCREMENT not null,
   IDREMITENTE          integer not null,
   IDDESTINATARIO       integer not null,/*CAMPO DE MAS*/
   ASUNTO               VARCHAR(40) not null,
   BODY                 VARCHAR(200) not null,
   ELIMINADO            boolean default false,
   primary key (IDMENSAJE)
);

/*==============================================================*/
/* Table: DESTINATARIOS POR MENSAJE                             */
/*==============================================================*/

create table DESTINATARIOXMENSAJE
 (
   IDDXM                integer AUTO_INCREMENT not null,
   IDMENSAJE            integer not null,
   IDDESTINATARIO       integer not null,
   LEIDO                boolean default false,
   primary key ( IDDXM)
 );

alter table MENSAJES add constraint FK_MENSAJE_REMITENTE foreign key (IDREMITENTE)
references USUARIOS (IDUSUARIO);

alter table MENSAJES add constraint FK_MENSAJE_DESTINATARIO foreign key (IDDESTINATARIO)
references USUARIOS (IDUSUARIO);

alter table DESTINATARIOXMENSAJE add constraint FK_DESTINMENSAJE foreign key (IDDESTINATARIO)
references USUARIOS (IDUSUARIO);

alter table DESTINATARIOXMENSAJE add constraint FK_MENSAJEDESTIN foreign key (IDMENSAJE)
references MENSAJES (IDMENSAJE);

/*==============================================================*/
/* TRIGGER: ELIMINA CUANDO SE ELIMINA UN MENSAJE                */
/*==============================================================*/

delimiter $$
 CREATE TRIGGER Tig_DESTXMENSAJE
 
 AFTER DELETE
    ON MENSAJES FOR EACH ROW
 
 BEGIN
    
 	delete from DESTINATARIOXMENSAJE
    
 	where DESTINATARIOXMENSAJE.IDMENSAJE = old.IDMENSAJE;

 END
 $$
delimiter ;

/*==========================================================================================*/
/* TRIGGER: ELIMINA TODOS LOS MENSAJES DONDE EL REMITENTE ES EL USUARIO QUE SE VA A ELIMINAR*/
/*==========================================================================================*/


delimiter $$

CREATE TRIGGER Tig_REMITENTE

BEFORE DELETE
   ON USUARIOS FOR EACH ROW


BEGIN

   
	delete from MENSAJES
   
	where MENSAJES.IDREMITENTE = old.IDUSUARIO;


END

$$
delimiter ;


/*=============================================================================================*/
/* TRIGGER: ELIMINA TODOS LOS MENSAJES DONDE EL DESTINATARIO VA A SER EL USUARIO QUE SE ELIMINA*/
/*=============================================================================================*/


delimiter $$

CREATE TRIGGER Tig_DESTINATARIO

BEFORE DELETE
   ON USUARIOS FOR EACH ROW

BEGIN

   
	delete from MENSAJES
   
	where MENSAJES.IDDESTINATARIO = old.IDUSUARIO;


END

$$
delimiter ;


/*==============================================================*/
/* PRUEBAS                                                      */
/*==============================================================*/


INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('FEDE','PALOMERO','admin@admin','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('FEFE','PALOMERO','user@user','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('DEFE','PALOMERO','prueba@prueba','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USUARIOS(NOMBRE,APELLIDO,EMAIL,PASSWORD,DIRECCION,TELEFONO,PAIS,PROVINCIA,CIUDAD) values ('JEJE','PALOMERO','HOLIS@HOLIS','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');

INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,4,'pruebatabla','PRUEBA 1');

/*EL USUARIO 1 LE ENVIA UN MENSAJE A LOS USUARIOS 4,3 Y 2)*/
INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO)values(1,4);
INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO)values(1,3);
INSERT INTO DESTINATARIOXMENSAJE(IDMENSAJE,IDDESTINATARIO)values(1,2);

SELECT * FROM MENSAJES;
/*==========ANDA PERO FALTAN COSAS=======================*/

SELECT * FROM USUARIOS
JOIN destinatarioxmensaje AS DXM ON DXM.IDDESTINATARIO=usuarios.IDUSUARIO AND DXM.IDMENSAJE=1;


/*========NO ANDA===================TRAER ENVIADOS POR UN USUARIO IDREMITENTE==================*/

Select m.IDMENSAJE, m.ASUNTO,m.BODY,uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,
uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD FROM MENSAJES as m 
join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO WHERE m.IDREMITENTE=1 AND m.ELIMINADO=FALSE
UNION
join(Select uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,
uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD FROM USUARIOS AS uD
join destinatarioxmensaje on destinatarioxmensaje.IDDESTINATARIO=uD.IDUSUARIO) MENSAJES
on m.IDMENSAJE=destinatarioxmensaje.IDMENSAJE;

/*===NO ANDA=================SOLO TRAE EL MENSAJE Y LOS USUARIOS A LOS QUE EL REMITENTE 1 MANDO MENSAJES===========================*/

SELECT m.IDMENSAJE, m.ASUNTO,m.BODY FROM MENSAJES AS m LEFT JOIN
(
SELECT uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,
uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD 
FROM USUARIOS AS uD
INNER JOIN destinatarioxmensaje as dxm 
on 
dxm.IDDESTINATARIO=uD.IDUSUARIO
)
on destinatarioxmensaje.IDMENSAJE=m.IDMENSAJE WHERE m.IDREMITENTE=1;

/*==================TRAE EL MENSAJE, 1 REMITENTE Y UN DESTINATARIO===========================*/

SELECT m.IDMENSAJE, m.ASUNTO,m.BODY,
uR.IDUSUARIO,uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD,
uD.IDUSUARIO,uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD
FROM MENSAJES as m
inner join destinatarioxmensaje as dxm ON m.IDMENSAJE=dxm.IDMENSAJE
inner join USUARIOS as uD ON dxm.IDDESTINATARIO=uD.IDUSUARIO
inner join USUARIOS as uR ON m.IDREMITENTE=uR.IDUSUARIO
WHERE m.IDREMITENTE=1;

/*==================DEBERIA TRAER ===========================*/

SELECT m.IDMENSAJE, m.ASUNTO,m.BODY,
uR.IDUSUARIO,uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD
FROM MENSAJES as m 
inner join USUARIOS as uR ON m.IDREMITENTE=uR.IDUSUARIO
inner join
(SELECT  uD.IDUSUARIO,uD.NOMBRE,uD.APELLIDO,uD.EMAIL,
uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD
from USUARIOS AS uD inner join destinatarioxmensaje dxm ON dxm.IDDESTINATARIO=uD.IDUSUARIO)
ON m.IDMENSAJE=destinatarioxmensaje.IDMENSAJE
WHERE m.IDREMITENTE=1;


/*==================DEBERIA TRAER 2===========================*/

SELECT m.IDMENSAJE, m.ASUNTO,m.BODY,
uD.IDUSUARIO,uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD
FROM destinatarioxmensaje as dm
INNER JOIN mensajes as m
ON dm.IDMENSAJE=m.IDMENSAJE
INNER JOIN usuarios as uD
ON dm.IDDESTINATARIO= uD.IDUSUARIO
WHERE m.IDREMITENTE=1;



/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/
/*==============================EJECUTAR HASTA ACAAAAAAAAAA===========================================*/

INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,4,'prueba','PRUEBA 1');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (2,3,'prueba','PRUEBA 2');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,2,'prueba','PRUEBA 3');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (1,4,'segunda','PRUEBA 4');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (4,1,'mensaje','recibido');
INSERT INTO MENSAJES(IDREMITENTE,IDDESTINATARIO,ASUNTO,BODY) values (4,1,'mensaje','recibido');

SELECT * FROM USUARIOS;
SELECT * FROM MENSAJES;

DELETE FROM USUARIOS WHERE IDUSUARIO=2;


UPDATE MENSAJES SET ELIMINADO = TRUE WHERE IDMENSAJE = 1;
UPDATE MENSAJES SET ELIMINADO = TRUE WHERE IDMENSAJE = 6;
select * from MENSAJES where ELIMINADO=TRUE;

Select m.IDMENSAJE, m.ASUNTO,m.BODY, uD.IDUSUARIO, uD.NOMBRE,uD.APELLIDO,uD.EMAIL,uD.PASSWORD,
uD.DIRECCION,uD.TELEFONO,uD.PAIS,uD.PROVINCIA,uD.CIUDAD,uR.IDUSUARIO, uR.NOMBRE,uR.APELLIDO,uR.EMAIL,uR.PASSWORD,
uR.DIRECCION,uR.TELEFONO,uR.PAIS,uR.PROVINCIA,uR.CIUDAD FROM MENSAJES as m join USUARIOS as uD on m.IDDESTINATARIO = uD.IDUSUARIO
join USUARIOS as uR on m.IDREMITENTE = uR.IDUSUARIO WHERE m.IDDESTINATARIO=4 AND m.ELIMINADO=FALSE;
