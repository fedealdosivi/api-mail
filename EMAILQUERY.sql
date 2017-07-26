DROP DATABASE IF EXISTS `email`;
create database `email`;
use email;

DROP TABLE IF EXISTS RECIPIENTBYMESSAGE;
DROP TABLE IF EXISTS MESSAGES;
DROP TABLE IF EXISTS USERS; 

create table USERS
(
   IDUSER            integer AUTO_INCREMENT not null,
   NAME              VARCHAR(40) not null,
   SURNAME           VARCHAR(40) not null,
   EMAIL             VARCHAR(40) UNIQUE not null,
   PASSWORD          VARCHAR(20) not null,
   ADRESS            VARCHAR(20) not null,
   CELLPHONE         integer not null,
   COUNTRY           VARCHAR(20) not null,
   STATE             VARCHAR(20) not null,
   CITY              VARCHAR(20) not null,
   primary key (IDUSER)
);


create table MESSAGES
(
   IDMESSAGE         integer AUTO_INCREMENT not null,
   IDSENDER          integer not null,
   SUBJECT           VARCHAR(40) not null,
   BODY              VARCHAR(200) not null,
   DELETED           boolean default false,
   TS                TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   primary key (IDMESSAGE)
);


create table RECIPIENTBYMESSAGE
 (
   IDRBYM               integer AUTO_INCREMENT not null,
   IDMESSAGE            integer not null,
   IDRECIPIENT          integer not null,
   SEEN                 boolean default false,
   primary key (IDRBYM)
 );


alter table MESSAGES add constraint FK_MESSAGE_SENDER foreign key (IDSENDER)
references USERS (IDUSER);

alter table RECIPIENTBYMESSAGE add constraint FK_RECIPIENT foreign key (IDRECIPIENT)
references USERS (IDUSER);

alter table RECIPIENTBYMESSAGE add constraint FK_MESSAGE foreign key (IDMESSAGE)
references MESSAGES (IDMESSAGE);

/*==============================================================*/
/*TRIGGERS*/
/*==============================================================*/

/*=====================================================================*/
/* TRIGGER: ELIMINA DE LA TABLA INTERMEDIA CUANDO SE ELIMINA UN MENSAJE*/
/*=====================================================================*/

delimiter $$
 CREATE TRIGGER Tig_RBYM
 
 BEFORE DELETE
    ON MESSAGES FOR EACH ROW
 
 BEGIN
    
 	delete from RECIPIENTBYMESSAGE
    
 	where RECIPIENTBYMESSAGE.IDMESSAGE = old.IDMESSAGE;

 END
 $$
delimiter ;

/*==========================================================================================*/
/* TRIGGER: ELIMINA TODOS LOS MENSAJES DONDE EL REMITENTE ES EL USUARIO QUE SE VA A ELIMINAR*/
/*==========================================================================================*/


delimiter $$

CREATE TRIGGER Tig_SENDER

BEFORE DELETE
   ON USERS FOR EACH ROW


BEGIN

   
	delete from MESSAGES
   
	where  MESSAGES.IDSENDER= old.IDUSER;


END

$$
delimiter ;


/*=====================================================================*/
/* TRIGGER: ELIMINA DE LA TABLA INTERMEDIA CUANDO SE ELIMINA UN USER   */
/*=====================================================================*/

delimiter $$
 CREATE TRIGGER Tig_RECIPIENT
 
 BEFORE DELETE
    ON USERS FOR EACH ROW
 
 BEGIN
    
 	delete from RECIPIENTBYMESSAGE
    
 	where RECIPIENTBYMESSAGE.IDRECIPIENT = old.IDUSER;

 END
 $$
delimiter ;

/*==============================================================*/
/*PROCEDURES*/
/*==============================================================*/

/*==============================================================*/
/*==============================================================*/
/*USERS*/
/*==============================================================*/
/*==============================================================*/

/*====================LOGIN============================*/
DROP PROCEDURE IF EXISTS login;
DELIMITER $$
 
CREATE PROCEDURE login(IN EMAIL VARCHAR(40) , IN PASSWORD VARCHAR(20))
BEGIN

select * from USERS where USERS.EMAIL=EMAIL and USERS.PASSWORD=PASSWORD;

END
$$

delimiter ;

/*====================TRAERPORNOMBRE============================*/

DROP PROCEDURE IF EXISTS getUserByName;
DELIMITER $$
 
CREATE PROCEDURE getUserByName(IN NAME VARCHAR(40))
BEGIN

select * from USERS where USERS.NAME LIKE NAME;

END
$$

delimiter ;

/*====================TRAERPORID============================*/

DROP PROCEDURE IF EXISTS getUserById;
DELIMITER $$
 
CREATE PROCEDURE getUserById(IN id INT)
BEGIN

select * from USERS where USERS.IDUSER=id;

END
$$

delimiter ;

/*====================TRAERPORID============================*/

DROP PROCEDURE IF EXISTS getUserById;
DELIMITER $$
 
CREATE PROCEDURE getUserById(IN id INT)
BEGIN

select * from USERS where USERS.IDUSER=id;

END
$$

delimiter ;

/*====================TRAERTODOS============================*/

DROP PROCEDURE IF EXISTS getAllUsers;
DELIMITER $$
 
CREATE PROCEDURE getAllUsers()
BEGIN

select * from USERS;

END
$$

delimiter ;

/*====================EliminarUsuario============================*/

DROP PROCEDURE IF EXISTS deleteUser;
DELIMITER $$
 
CREATE PROCEDURE deleteUser(IN id INT)
BEGIN

delete from USERS where USERS.IDUSER=id;

END
$$

delimiter ;

/*====================CargarUsuario============================*/

DROP PROCEDURE IF EXISTS saveUser;
DELIMITER $$
 
CREATE PROCEDURE saveUser( 
IN NAME              VARCHAR(40), 
IN SURNAME           VARCHAR(40), 
IN EMAIL             VARCHAR(40), 
IN PASSWORD          VARCHAR(20), 
IN ADRESS            VARCHAR(20), 
IN CELLPHONE         integer, 
IN COUNTRY           VARCHAR(20), 
IN STATE             VARCHAR(20), 
IN CITY              VARCHAR(20) 
)

BEGIN

INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) 
values (NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY);

END
$$

delimiter ;



/*==============================================================*/
/*==============================================================*/
/*==============================================================*/
/*Mensaje*/
/*==============================================================*/
/*==============================================================*/
/*==============================================================*/

/*====================GuardarMensajeDestinatario============================*/

DROP PROCEDURE IF EXISTS saveRecipientByMessage;
DELIMITER $$
 
CREATE PROCEDURE saveRecipientByMessage(IN idM INT,IN email VARCHAR(40))
BEGIN

SELECT IDUSER INTO @id from USERS WHERE USERS.EMAIL=email;

INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(idM,@id);

END
$$

delimiter ;

/*====================Eliminar Mensaje============================*/

DROP PROCEDURE IF EXISTS deleteMessage;
DELIMITER $$
 
CREATE PROCEDURE deleteMessage(IN idM INT)
BEGIN

DELETE FROM MESSAGES WHERE MESSAGES.IDMESSAGE=idM;

END
$$

delimiter ;

/*====================TRAER MENSAJE POR ID============================*/

DROP PROCEDURE IF EXISTS getMessageById;
DELIMITER $$
 
CREATE PROCEDURE getMessageById(IN idMessage INT)

BEGIN


SELECT
   ME.IDSENDER,
   ME.BODY,
   ME.SUBJECT,
   ME.IDMESSAGE,
   SENDER.NAME,
   SENDER.SURNAME,
   SENDER.EMAIL,
   SENDER.PASSWORD,
   SENDER.ADRESS,
   SENDER.CELLPHONE,
   SENDER.COUNTRY,
   SENDER.STATE,
   SENDER.CITY,
   RECIPIENT.IDUSER,
   RECIPIENT.NAME,
   RECIPIENT.SURNAME,
   RECIPIENT.EMAIL,
   RECIPIENT.PASSWORD,
   RECIPIENT.ADRESS,
   RECIPIENT.CELLPHONE,
   RECIPIENT.COUNTRY,
   RECIPIENT.STATE,
   RECIPIENT.CITY
FROM MESSAGES AS ME
JOIN USERS AS SENDER ON SENDER.IDUSER=ME.IDSENDER
JOIN RECIPIENTBYMESSAGE AS RBM ON RBM.IDMESSAGE=ME.IDMESSAGE
JOIN USERS AS RECIPIENT ON RECIPIENT.IDUSER=RBM.IDRECIPIENT
WHERE ME.IDMESSAGE=idMessage AND ME.DELETED=FALSE;


END
$$

delimiter ;



/*====================TRAER TODOS LOS MENSAJES ENVIADOS POR UN USUARIO============================*/

DROP PROCEDURE IF EXISTS getSent;

DELIMITER $$
 
CREATE PROCEDURE getSent(IN idSender INT)

BEGIN

SELECT
   ME.IDSENDER,
   ME.BODY,
   ME.SUBJECT,
   ME.IDMESSAGE,
   SENDER.NAME,
   SENDER.SURNAME,
   SENDER.EMAIL,
   SENDER.PASSWORD,
   SENDER.ADRESS,
   SENDER.CELLPHONE,
   SENDER.COUNTRY,
   SENDER.STATE,
   SENDER.CITY,
   RECIPIENT.IDUSER,
   RECIPIENT.NAME,
   RECIPIENT.SURNAME,
   RECIPIENT.EMAIL,
   RECIPIENT.PASSWORD,
   RECIPIENT.ADRESS,
   RECIPIENT.CELLPHONE,
   RECIPIENT.COUNTRY,
   RECIPIENT.STATE,
   RECIPIENT.CITY
FROM MESSAGES AS ME
JOIN USERS AS SENDER ON SENDER.IDUSER=ME.IDSENDER
JOIN RECIPIENTBYMESSAGE AS RBM ON RBM.IDMESSAGE=ME.IDMESSAGE
JOIN USERS AS RECIPIENT ON RECIPIENT.IDUSER=RBM.IDRECIPIENT
WHERE ME.IDSENDER=idSender AND ME.DELETED=FALSE;

END
$$

delimiter ;

/*====================TRAER TODOS LOS MENSAJES ELIMINADOS POR EL EMISOR============================*/

DROP PROCEDURE IF EXISTS getTrash;

DELIMITER $$
 
CREATE PROCEDURE getTrash(IN idSender INT)

BEGIN

SELECT
   ME.IDSENDER,
   ME.BODY,
   ME.SUBJECT,
   ME.IDMESSAGE,
   SENDER.NAME,
   SENDER.SURNAME,
   SENDER.EMAIL,
   SENDER.PASSWORD,
   SENDER.ADRESS,
   SENDER.CELLPHONE,
   SENDER.COUNTRY,
   SENDER.STATE,
   SENDER.CITY,
   RECIPIENT.IDUSER,
   RECIPIENT.NAME,
   RECIPIENT.SURNAME,
   RECIPIENT.EMAIL,
   RECIPIENT.PASSWORD,
   RECIPIENT.ADRESS,
   RECIPIENT.CELLPHONE,
   RECIPIENT.COUNTRY,
   RECIPIENT.STATE,
   RECIPIENT.CITY
FROM MESSAGES AS ME
JOIN USERS AS SENDER ON SENDER.IDUSER=ME.IDSENDER
JOIN RECIPIENTBYMESSAGE AS RBM ON RBM.IDMESSAGE=ME.IDMESSAGE
JOIN USERS AS RECIPIENT ON RECIPIENT.IDUSER=RBM.IDRECIPIENT
WHERE ME.IDSENDER=idSender AND ME.DELETED=TRUE;

END
$$

delimiter ;


/*====================TRAER TODOS LOS MENSAJES RECIBIDOS POR UN USUARIO============================*/

DROP PROCEDURE IF EXISTS getInbox;

DELIMITER $$
 
CREATE PROCEDURE getInbox(IN idRecipient INT)

BEGIN


SELECT
   ME.IDSENDER,
   ME.BODY,
   ME.SUBJECT,
   ME.IDMESSAGE,
   SENDER.NAME,
   SENDER.SURNAME,
   SENDER.EMAIL,
   SENDER.PASSWORD,
   SENDER.ADRESS,
   SENDER.CELLPHONE,
   SENDER.COUNTRY,
   SENDER.STATE,
   SENDER.CITY,
   RECIPIENT.IDUSER,
   RECIPIENT.NAME,
   RECIPIENT.SURNAME,
   RECIPIENT.EMAIL,
   RECIPIENT.PASSWORD,
   RECIPIENT.ADRESS,
   RECIPIENT.CELLPHONE,
   RECIPIENT.COUNTRY,
   RECIPIENT.STATE,
   RECIPIENT.CITY
FROM MESSAGES AS ME
JOIN USERS AS SENDER ON SENDER.IDUSER=ME.IDSENDER
JOIN RECIPIENTBYMESSAGE AS RBM ON RBM.IDMESSAGE=ME.IDMESSAGE AND RBM.SEEN=FALSE
JOIN USERS AS RECIPIENT ON RECIPIENT.IDUSER=RBM.IDRECIPIENT
WHERE RBM.IDRECIPIENT=idRecipient AND ME.DELETED=FALSE;


END
$$

delimiter ;

/*=========================MANDAR UN MENSAJE A LA PAPELERA===================================*/

DROP PROCEDURE IF EXISTS setTrash;

DELIMITER $$
 
CREATE PROCEDURE setTrash(IN idM INT)

BEGIN

UPDATE MESSAGES SET DELETED=TRUE WHERE MESSAGES.IDMESSAGE=idM;

END
$$

delimiter ;


/*=========================INSERTS===================================*/

INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('FEDE','PALOMERO','admin@admin','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('USER','USER','user@user','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('PRUEBA','PRUEBA','prueba@prueba','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('JUAN','CARLOS','juan@carlos','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('DAMIAN','ROSALES','damian@rosales','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('ROBERTO','BRUM','roberto@brum','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('PEPE','SAND','pepe@sand','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');
INSERT INTO USERS(NAME,SURNAME,EMAIL,PASSWORD,ADRESS,CELLPHONE,COUNTRY,STATE,CITY) values ('DALAI','LAMA','dalai@lama','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');

INSERT INTO MESSAGES(IDSENDER,SUBJECT,BODY) values (1,'pruebatabla','PRUEBA 1');
INSERT INTO MESSAGES(IDSENDER,SUBJECT,BODY) values (1,'hola','hola');


/*EL USUARIO 1 LE ENVIA UN MENSAJE A LOS USUARIOS 4,3 Y 2) INSERTA EN LA TABLA INTERMEDIA*/
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(1,4);
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(1,3);
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(1,2);


/*EL USUARIO 1 LE ENVIA UN MENSAJE A LOS USUARIOS 5,6 y 7)*/
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(2,5);
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(2,6);
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(2,7);
INSERT INTO RECIPIENTBYMESSAGE(IDMESSAGE,IDRECIPIENT)values(2,4);


/*====================LLAMANDO PROCEDURES============================*/

CALL login('admin@admin',123456);
CALL getUserByName('PEPE');
CALL getUserById(4);
CALL getAllUsers();
CALL deleteUser(11);
CALL saveUser('LEANDRO','SOMOZA','leandro@somoza','123456','CALLEFALSA123',123456,'ANTARTIDA','AAA','AAA');

CALL saveRecipientByMessage(2,'prueba@prueba');
CALL deleteMessage(2);
CALL getSent(1);
CALL getInbox(6);
UPDATE MESSAGES SET DELETED=FALSE WHERE IDMESSAGE=1;
UPDATE recipientbymessage SET SEEN=FALSE WHERE IDMESSAGE=2;
CALL getMessageById(1);
CALL setTrash(1);
CALL getTrash(1);

select * from messages;