CREATE TABLE `complexity` (
  `COMPLEXITY_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `BASE_HOUR` float NOT NULL DEFAULT '0',
  `MULTIPLEXOR` float NOT NULL DEFAULT '1',
  PRIMARY KEY (`COMPLEXITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `method` (
  `METHOD_ID` int NOT NULL AUTO_INCREMENT,
  `PROEJCT_ID` int NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  PRIMARY KEY (`METHOD_ID`),
  UNIQUE KEY `METHOD_ID_UNIQUE` (`METHOD_ID`),
  UNIQUE KEY `DESCRIPTION_UNIQUE` (`DESCRIPTION`),
  KEY `FK_METHOD_PROJECT_idx` (`PROEJCT_ID`),
  CONSTRAINT `FK_METHOD_PROJECT` FOREIGN KEY (`PROEJCT_ID`) REFERENCES `project` (`PROJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `project` (
  `PROJECT_ID` int NOT NULL AUTO_INCREMENT,
  `PROJECT_NUMBER` int NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`PROJECT_ID`),
  UNIQUE KEY `PROJECT_NUMBER_UNIQUE` (`PROJECT_NUMBER`),
  UNIQUE KEY `PROJECT_ID_UNIQUE` (`PROJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask` (
  `SUBTASK_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `SUBTASK_TYPE_ID` int NOT NULL,
  `TIME` float NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_ID`),
  KEY `FK_SUBTASK_TYPE_idx` (`SUBTASK_TYPE_ID`),
  CONSTRAINT `FK_SUBTASK_TYPE` FOREIGN KEY (`SUBTASK_TYPE_ID`) REFERENCES `subtask_type` (`SUBTASK_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask_method` (
  `SUBTASK_METHOD_ID` int NOT NULL,
  `SUBTASK_ID` int DEFAULT NULL,
  `METHOD_ID` int DEFAULT NULL,
  `REUSED` bit(1) DEFAULT NULL,
  `FIELD_COUNT` int DEFAULT NULL,
  `COMPLEXITY_ID` int DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_METHOD_ID`),
  KEY `FK_SUBTASK_METHOD_SUBTASK_idx` (`SUBTASK_ID`),
  KEY `FK_SUBTASK_METHOD_METHOD_idx` (`METHOD_ID`),
  KEY `FK_SUBTASK_METHOD_COMPLEXITY_idx` (`COMPLEXITY_ID`),
  CONSTRAINT `FK_SUBTASK_METHOD_COMPLEXITY` FOREIGN KEY (`COMPLEXITY_ID`) REFERENCES `complexity` (`COMPLEXITY_ID`),
  CONSTRAINT `FK_SUBTASK_METHOD_METHOD` FOREIGN KEY (`METHOD_ID`) REFERENCES `method` (`METHOD_ID`),
  CONSTRAINT `FK_SUBTASK_METHOD_SUBTASK` FOREIGN KEY (`SUBTASK_ID`) REFERENCES `subtask` (`SUBTASK_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask_type` (
  `SUBTASK_TYPE_ID` int NOT NULL AUTO_INCREMENT,
  `SUBTASK_CATEGORY_TYPE_ID` int DEFAULT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  `CALCULATION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_TYPE_ID`,`DESCRIPTION`),
  UNIQUE KEY `SUBTASK_TYPE_ID_UNIQUE` (`SUBTASK_TYPE_ID`),
  KEY `FK_SUBTASK_CATEGORY_idx` (`SUBTASK_CATEGORY_TYPE_ID`),
  CONSTRAINT `FK_SUBTASK_CATEGORY` FOREIGN KEY (`SUBTASK_CATEGORY_TYPE_ID`) REFERENCES `subtask_type_category` (`SUBTASK_TYPE_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask_type_category` (
  `SUBTASK_TYPE_CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_TYPE_CATEGORY_ID`),
  UNIQUE KEY `SUBTASK_TYPE_CATEGORY_ID_UNIQUE` (`SUBTASK_TYPE_CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task` (
  `TAKS_ID` int NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` int NOT NULL,
  `CODE` varchar(45) NOT NULL,
  `SEQUENCE` int NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`TAKS_ID`),
  UNIQUE KEY `TAKS_ID_UNIQUE` (`TAKS_ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `FK_PROJECT_TASK_idx` (`PROJECT_ID`),
  CONSTRAINT `FK_PROJECT_TASK` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`PROJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
