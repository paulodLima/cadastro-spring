CREATE SCHEMA  if not exists DBPERSON;

use DBPERSON;

SET FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE TB_ADDRESS;
TRUNCATE TABLE TB_PERSON;
SET FOREIGN_KEY_CHECKS=1;

-- Person
insert into TB_PERSON (ID_PERSON, DT_BIRTH, DOCUMENT_NUMBER, EMAIL, FULL_NAME,PHONE)
values                (101 , '1996-05-22', '52278512048', 'brancante@foo.com', 'brincante da silva','99999999');


insert into TB_PERSON (ID_PERSON, DT_BIRTH, DOCUMENT_NUMBER, EMAIL, FULL_NAME,PHONE)
values                (102 , '1998-03-24', '15286351085', 'brancantedasilva@foo.com', 'mister','99999999');

-- Address
insert into TB_ADDRESS (ID_ADDRESS, CITY, COMPLEMENT, NEIGHBORHOOD, ID_PERSON, STATE, STREET, ZIPCODE)
values               (103, 'Brasilia', 'la manos', 'Setor de Mansões de Sobradinho', 101, 'DF', 'qms 30a BSB', '73080100');