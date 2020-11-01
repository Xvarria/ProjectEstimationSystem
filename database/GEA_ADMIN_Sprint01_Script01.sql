/**
Autor: Marcos Chavarria Fallas
Fecha: 01/04/2019
Crea el esquema nuevo dentro de la base de datos
User/Schema dba.
*/
CREATE SCHEMA `GEA_WEB` ;

/*Create user GEA_ADMIN / S9**QF4u=JkcC8dG*/
/*GRANT ALL ACCESS TO GEA_WEB*/
/*Create user GEA_CON /MzaDwK_7+p#rtng$*/
DROP TABLE GEA_WEB.LECTURA;
DROP TABLE GEA_WEB.LECTURA_DIARIA;
DROP TABLE GEA_WEB.MEDIDOR;
DROP TABLE GEA_WEB.ERROR_CATALAGO;
DROP TABLE GEA_WEB.ERROR_LECTURA;

CREATE TABLE GEA_WEB.ERROR_CATALAGO (
	ERROR_CATALAGO_IDMEDIDOR  INTEGER(10)  NOT NULL,
    DESCRIPCION VARCHAR(200),
    PRIMARY KEY (ERROR_CATALAGO_ID)
);
CREATE UNIQUE INDEX PK_ERROR_CATALAGO ON GEA_WEB.ERROR_CATALAGO (ERROR_CATALAGO_ID);
INSERT INTO GEA_WEB.ERROR_CATALAGO (ERROR_CATALAGO_ID, DESCRIPCION) VALUES (1,'APP-KEY-ID INVÁLIDO');
INSERT INTO GEA_WEB.ERROR_CATALAGO (ERROR_CATALAGO_ID, DESCRIPCION) VALUES (2,'LECTURA MENOR A LECTURA ACTUAL');

CREATE TABLE GEA_WEB.ERROR_LECTURA (
	ERROR_LECTURA_ID INTEGER(10) NOT NULL AUTO_INCREMENT,
    FECHA_LECTURA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ERROR_CATALAGO_ID INTEGER(10),
    REQUEST JSON,
    PRIMARY KEY(ERROR_LECTURA_ID)
);
CREATE UNIQUE INDEX PK_ERROR_LECTURA_ID ON GEA_WEB.ERROR_LECTURA(ERROR_LECTURA_ID);
ALTER TABLE GEA_WEB.ERROR_CATALAGO ADD CONSTRAINT FK_ERROR_LECTURA_CAT foreign key (ERROR_CATALAGO_ID) references GEA_WEB.ERROR_CATALAGO (ERROR_CATALAGO_ID);

CREATE TABLE GEA_WEB.MEDIDOR (
	MEDIDOR_ID INTEGER(10) NOT NULL,
    FECHA_INCLUSION DATETIME,
    ULTIMA_LECUTRA FLOAT(18,8),
    FECHA_ULTIMA_LECTURA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (MEDIDOR_ID)
);
CREATE UNIQUE INDEX PK_MEDIDOR ON GEA_WEB.MEDIDOR (MEDIDOR_ID);

CREATE TABLE GEA_WEB.LECTURA (
	LECTURA_ID INTEGER(10) NOT NULL AUTO_INCREMENT,
    MEDIDOR_ID INTEGER(10) NOT NULL,
    TEMPERATURA FLOAT(18,8),
    FECHA_LECTURA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ERROR VARCHAR(100),
    VOLUMEN FLOAT(18,8),
    PROCESADA BOOLEAN NOT NULL,
    DIA_NUMERO INTEGER(6) NOT NULL,
    PRIMARY KEY (LECTURA_ID)
);
ALTER TABLE GEA_WEB.LECTURA ADD CONSTRAINT FK_LECTURA_MEDIDOR FOREIGN KEY (MEDIDOR_ID) REFERENCES GEA_WEB.MEDIDOR (MEDIDOR_ID);
CREATE INDEX IX_LECTURA_DN ON GEA_WEB.LECTURA (DIA_NUMERO);

-- DROP TABLE  GEA_WEB.LECTURA_DIARIA;
CREATE TABLE GEA_WEB.LECTURA_DIARIA (
	LECTURA_DIARIA_ID INTEGER(10) NOT NULL AUTO_INCREMENT,
    MEDIDOR_ID INTEGER(10) NOT NULL,
    TEMPERATURA FLOAT(18,8),
    MAX_LECTURA FLOAT(18,8),
    CONSUMO FLOAT(18,8),
    DIA_NUMERO INTEGER(8),
    CANTIDAD_LECTURA INTEGER(6),
    PRIMARY KEY (LECTURA_DIARIA_ID)
);
ALTER TABLE GEA_WEB.LECTURA_DIARIA ADD CONSTRAINT FK_LECTURA_DIARIA_MEDIDOR FOREIGN KEY (MEDIDOR_ID) REFERENCES GEA_WEB.MEDIDOR (MEDIDOR_ID);


DROP PROCEDURE IF EXISTS GEA_WEB.SP_REGISTRAR_LECUTRA;
/*
Autor: Marcos Chavarria Fallas
Fecha: 03/04/2019
Revisa el JSON proveniente de los medidores, por cada lectura,
revisa los medidores, si el medidor existe lo crea, sino actualiza la última lecutura para el día actual
User/Schema gea_web.
*/
DELIMITER //
CREATE PROCEDURE GEA_WEB.SP_REGISTRAR_LECUTRA(IN P_LECTURA JSON)
BEGIN
	/*cONSTANTES*/
    DECLARE C_APP_KEY_INVALIDO INT(1) default 1;
    DECLARE C_LECTURA_NEGATIVA INT(1) default 2;
    /*VARIABLES select json*/
	DECLARE v_api_key VARCHAR(36) DEFAULT '';
    DECLARE v_timestamp TIMESTAMP DEFAULT NULL;
    DECLARE V_VOLUMEN FLOAT (14,4) DEFAULT 0;
    DECLARE v_temperature FLOAT (14,4) DEFAULT 0;
    DECLARE v_data JSON;
    DECLARE V_CURRENT_DATA JSON;
    /*VARIABLES*/
    DECLARE V_INDEX INT DEFAULT 0;
    DECLARE V_MEDIDOR_COUNT INT DEFAULT 0;
    DECLARE V_LECTURA_REF FLOAT (14,4) DEFAULT 0;
	DECLARE V_LECTURA_VALIDA BOOLEAN DEFAULT FALSE;
	DECLARE V_MEDIDOR_ID INTEGER(10) default 0;
    /*Definicion de Handlres*/
    DECLARE EXIT HANDLER FOR NOT FOUND SELECT 'No data found';
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
    END;
    
    START TRANSACTION;
	/*Obtiene api key y fecha para procesar*/
    SET v_api_key = JSON_UNQUOTE(JSON_EXTRACT(P_LECTURA, '$.api_key'));
    SET v_timestamp = FROM_UNIXTIME(JSON_EXTRACT(P_LECTURA, '$.timestamp'));
    SET v_data = JSON_EXTRACT(P_LECTURA, '$.data');
    /*Revisa todos los datos del arreglo*/
    WHILE V_INDEX < JSON_LENGTH(v_data) DO
		SET V_LECTURA_VALIDA = FALSE;
		SET V_CURRENT_DATA = JSON_EXTRACT(v_data,CONCAT('$[',V_INDEX,']'));
        set V_VOLUMEN = JSON_EXTRACT(V_CURRENT_DATA, '$.volumen');
		set v_temperature = JSON_EXTRACT(V_CURRENT_DATA, '$.temperature');
        set V_MEDIDOR_ID = JSON_EXTRACT(V_CURRENT_DATA, '$.measurer_internal_id');
        /*1. Revisa si internal ID existe como medidor*/
        /*2. */
        SELECT COUNT(MEDIDOR_ID), IFNULL(MAX(ULTIMA_LECUTRA),0)  INTO V_MEDIDOR_COUNT, V_LECTURA_REF FROM GEA_WEB.MEDIDOR WHERE MEDIDOR_ID = V_MEDIDOR_ID;
        select CONCAT('-REVISA SI MEDIDOR EXISTE ID;', V_MEDIDOR_ID, 'CONT;', V_MEDIDOR_COUNT, ' REF:', V_LECTURA_REF);
        IF (V_MEDIDOR_COUNT = 0) THEN 
			select '-- MEDIDOR NO EXISTE - CREA' ;
			/*1.a registra nuevo medidor*/
            INSERT INTO GEA_WEB.MEDIDOR(MEDIDOR_ID, FECHA_INCLUSION, ULTIMA_LECUTRA, FECHA_ULTIMA_LECTURA) VALUES (V_MEDIDOR_ID, v_timestamp, V_VOLUMEN, v_timestamp);
        ELSE
			select CONCAT('V_LECTURA_REF ', V_LECTURA_REF, ' - vOLUMEN: ',  V_VOLUMEN);
			IF (V_LECTURA_REF <= V_VOLUMEN) THEN
				select '--MEDIDOR EXISTE, ACTUALIZA' ;
				/*1.b Actualiza registro de medidor*/
				UPDATE GEA_WEB.MEDIDOR SET FECHA_ULTIMA_LECTURA = v_timestamp, FECHA_INCLUSION = FECHA_INCLUSION, ULTIMA_LECUTRA = V_VOLUMEN WHERE MEDIDOR_ID = V_MEDIDOR_ID;
			else
				select '--ERROR LECUTRA NEGATIVA' ;
				INSERT INTO GEA_WEB.ERROR_LECTURA (FECHA_LECTURA, ERROR_CATALAGO_ID, REQUEST) VALUES (v_timestamp, C_LECTURA_NEGATIVA, V_CURRENT_DATA);
			END IF;
		END IF;
        SET V_LECTURA_VALIDA = (V_LECTURA_REF <= V_VOLUMEN);
        /*Registrar lectura*/
		IF(V_LECTURA_VALIDA) THEN
			select '-iNSERTA LECTURA valida' ;
			INSERT INTO GEA_WEB.LECTURA (MEDIDOR_ID, TEMPERATURA, FECHA_LECTURA, VOLUMEN, PROCESADA, DIA_NUMERO) VALUES (V_MEDIDOR_ID, v_temperature, v_timestamp, V_VOLUMEN, 0, DATE_FORMAT(DATE(v_timestamp),'%Y%m%d'));
        END IF;
		SET V_INDEX = V_INDEX + 1;
	END WHILE;
    select 'fin DE WHILE' ;
    COMMIT;
END //
DELIMITER ;

GRANT EXECUTE ON PROCEDURE GEA_WEB.SP_REGISTRAR_LECUTRA TO 'GEA_CON';
GRANT SELECT ON GEA_WEB.MEDIDOR TO 'GEA_CON';
GRANT SELECT ON GEA_WEB.LECTURA TO 'GEA_CON';