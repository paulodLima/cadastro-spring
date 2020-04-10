CREATE TABLE IF NOT EXISTS TB_PERSON
(
    ID_PERSON       BIGINT UNSIGNED AUTO_INCREMENT,
    FULL_NAME       VARCHAR(60) NOT NULL,
    DOCUMENT_NUMBER VARCHAR(15) NOT NULL UNIQUE,
    DT_BIRTH        DATE        NOT NULL,
    NM_FATHER       VARCHAR(1) NOT NULL,
    NM_MOTHER       VARCHAR(70) NOT NULL,
    DT_REGISTER     TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    TP_PERSON       VARCHAR (20) NOT NULL,
    PRIMARY KEY (ID_PERSON)
) ENGINE = INNODB
  DEFAULT CHARSET = UTF8;