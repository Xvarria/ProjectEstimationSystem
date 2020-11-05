CREATE TABLE `complexity` (
  `COMPLEXITY_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `BASE_HOUR` float NOT NULL DEFAULT '0',
  `MULTIPLEXOR` float NOT NULL DEFAULT '1',
  PRIMARY KEY (`COMPLEXITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `project` (
  `PROJECT_ID` int NOT NULL AUTO_INCREMENT,
  `PROJECT_NUMBER` int NOT NULL,
  `NAME` varchar(100) NOT NULL,
  PRIMARY KEY (`PROJECT_ID`),
  UNIQUE KEY `PROJECT_NUMBER_UNIQUE` (`PROJECT_NUMBER`),
  UNIQUE KEY `PROJECT_ID_UNIQUE` (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `task` (
  `TASK_ID` int NOT NULL AUTO_INCREMENT,
  `PROJECT_ID` int NOT NULL,
  `CODE` varchar(45) NOT NULL,
  `SEQUENCE` int NOT NULL DEFAULT '0',
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`TASK_ID`),
  UNIQUE KEY `TAKS_ID_UNIQUE` (`TASK_ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  KEY `FK_PROJECT_TASK_idx` (`PROJECT_ID`),
  CONSTRAINT `FK_PROJECT_TASK` FOREIGN KEY (`PROJECT_ID`) REFERENCES `project` (`PROJECT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask_type_category` (
  `SUBTASK_TYPE_CATEGORY_ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_TYPE_CATEGORY_ID`),
  UNIQUE KEY `SUBTASK_TYPE_CATEGORY_ID_UNIQUE` (`SUBTASK_TYPE_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask_type` (
  `SUBTASK_TYPE_ID` int NOT NULL AUTO_INCREMENT,
  `SUBTASK_TYPE_CATEGORY_ID` int NOT NULL,
  `DESCRIPTION` varchar(45) NOT NULL,
  `CALCULATION` varchar(1000) DEFAULT NULL,
  `REFERENCE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SUBTASK_TYPE_ID`,`DESCRIPTION`),
  UNIQUE KEY `SUBTASK_TYPE_ID_UNIQUE` (`SUBTASK_TYPE_ID`),
  KEY `FK_SUBTASK_CATEGORY_idx` (`SUBTASK_TYPE_CATEGORY_ID`),
  CONSTRAINT `FK_SUBTASK_CATEGORY` FOREIGN KEY (`SUBTASK_TYPE_CATEGORY_ID`) REFERENCES `subtask_type_category` (`SUBTASK_TYPE_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `subtask` (
  `SUBTASK_ID` int unsigned NOT NULL AUTO_INCREMENT,
  `SUBTASK_TYPE_ID` int NOT NULL,
  `TIME` float NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `AUTO_CALCULATION` tinyint NOT NULL DEFAULT '0',
  `REFERENCE_MODEL` varchar(1000) DEFAULT NULL,
  `TASK_ID` int NOT NULL,
  `CALCULATED_TIME` float NOT NULL DEFAULT '0',
  `COMPLEXITY_ID` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`SUBTASK_ID`),
  KEY `FK_SUBTASK_TYPE_idx` (`SUBTASK_TYPE_ID`),
  KEY `FK_SUBTASK_TASK_idx` (`TASK_ID`),
  KEY `FK_SUBTASK_COMPLEXITY_idx` (`COMPLEXITY_ID`),
  CONSTRAINT `FK_SUBTASK_COMPLEXITY` FOREIGN KEY (`COMPLEXITY_ID`) REFERENCES `complexity` (`COMPLEXITY_ID`),
  CONSTRAINT `FK_SUBTASK_TASK` FOREIGN KEY (`TASK_ID`) REFERENCES `task` (`TASK_ID`),
  CONSTRAINT `FK_SUBTASK_TYPE` FOREIGN KEY (`SUBTASK_TYPE_ID`) REFERENCES `subtask_type` (`SUBTASK_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
