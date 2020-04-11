CREATE SCHEMA  if not exists DBPERSON;

use DBPERSON;

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE TB_PERSON;
TRUNCATE TABLE TB_PHONE;
TRUNCATE TABLE TB_OPERATOR;
SET FOREIGN_KEY_CHECKS=1;

-- Operator
insert into TB_OPERATOR (ID_OPERATOR, NAME,LOGIN,PASSWORD,PERFIL,DT_REGISTER,ADMIN) values (1 , 'ADMINISTRADOR', 'admin', '$2a$10$ddCpH9mSFemgSA7bfj/luOo7vABV4KCeR84y1gdAPd/r4SyKEKZj6', 'GESTOR',sysdate,1);

--Operator 2
insert into TB_OPERATOR (ID_OPERATOR, NAME,LOGIN,PASSWORD,PERFIL,DT_REGISTER,ADMIN) values (2 , 'GESTOR', 'gestor', '$2a$10$MtCy87ospbCAN4Vm59O0oeux2mpmuUwoAToCzNpq7SbHYfPyt05Ta', 'GESTOR',sysdate,0);

-- Persons
insert into TB_PERSON (ID_PERSON,FULL_NAME,DOCUMENT_NUMBER, DT_BIRTH, NM_FATHER,NM_MOTHER, DT_REGISTER,TP_PERSON) values (1 ,  'Jose miguel da sila', '00000000000', '1996-04-20','Maria joaquina','Marcio ze', sysdate,'FISICA');
insert into TB_PERSON (ID_PERSON,FULL_NAME,DOCUMENT_NUMBER, DT_BIRTH, NM_FATHER,NM_MOTHER, DT_REGISTER,TP_PERSON) values (2 ,'Andre da sila', '000000000012', '1996-04-20','Maria Antonia','Marcelo Andre', sysdate,'JURIDICA');

insert into TB_PHONE (ID_PHONE,ID_PERSON,NUMBER,COD_AREA, PHONE_TYPE, DT_REGISTER,OPERATOR_LOGIN)
values (1,1,'0000000',061,'CELULAR',sysdate,'paulo');