drop user 'bookdb_admin'@'localhost';

CREATE USER 'bookdb_admin'@'localhost' IDENTIFIED BY 'admin_password';

GRANT ALL PRIVILEGES ON *.* TO 'bookdb_admin'@'localhost';
create database if not exists book_store;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `book_store` DEFAULT CHARACTER SET latin1 ;
USE `book_store` ;

drop table if exists `BOOK_STORE`.`publisher`;
drop table if exists `BOOK_STORE`.`book`;
drop table if exists `BOOK_STORE`.`order`;
drop table if exists `BOOK_STORE`.`user`;
drop table if exists `BOOK_STORE`.`sales`;
-- -----------------------------------------------------
-- Table `book_store`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`publisher` (
  `NAME` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(45) NOT NULL,
  `TELEPHONE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`NAME`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `book_store`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`book` (
  `ISBN` VARCHAR(45) NOT NULL,
  `TITLE` VARCHAR(45) NOT NULL,
  `AUTHOR` VARCHAR(45) NOT NULL,
  `PUBLISHER` VARCHAR(45) NOT NULL,
  `PUBLICATION_YEAR` VARCHAR(45) NULL DEFAULT NULL,
  `SELLING_PRICE` FLOAT NOT NULL DEFAULT '0',
  `CATEGORY` VARCHAR(45) NOT NULL,
  `THRESHOLD` INT(11) NOT NULL DEFAULT '1',
  `COPIES` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `title_UNIQUE` (`TITLE` ASC),
  INDEX `fk_BOOK_1_idx` (`PUBLISHER` ASC),
  INDEX `index4` (`CATEGORY` ASC),
  CONSTRAINT `fk_BOOK_1`
    FOREIGN KEY (`PUBLISHER`)
    REFERENCES `book_store`.`publisher` (`NAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `book_store`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`order` (
  `ORDER_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(45) NOT NULL,
  `QUANTITY` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ORDER_ID`, `ISBN`),
  INDEX `fk_ORDER_1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_ORDER_1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `book_store`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`user` (
  `USER_NAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `L_NAME` VARCHAR(45) NOT NULL,
  `F_NAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `TELEPHONE` VARCHAR(45) NOT NULL,
  `ADDRESS` VARCHAR(45) NOT NULL,
  `PRIVILAGE` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`USER_NAME`),
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `book_store`.`sales`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`sales` (
   
  `SALES_ID` INT(11) NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(45) NOT NULL,
  `USER_NAME` VARCHAR(45) NOT NULL,
  `DATE`  VARCHAR(45) NOT NULL,
  `QUANTITY` INT(11) NOT NULL,
  PRIMARY KEY (`SALES_ID`, `ISBN`, `USER_NAME`),
  INDEX `fk_SALES_1_idx` (`ISBN` ASC),
  INDEX `fk_SALES_2_idx` (`USER_NAME` ASC),
  CONSTRAINT `fk_SALES_1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_SALES_2`
    FOREIGN KEY (`USER_NAME`)
    REFERENCES `book_store`.`user` (`USER_NAME`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `PUBLISHER` VALUES ('PUB_1', 'alex-1', '011987654321');
INSERT INTO `PUBLISHER` VALUES ('PUB_2', 'alex-2', '011987654321');
INSERT INTO `PUBLISHER` VALUES ('PUB_3', 'alex-3', '011987654321');
INSERT INTO `PUBLISHER` VALUES ('PUB_4', 'alex-4', '011987654321');

INSERT INTO `USER` VALUES ('User1', 'java', 'Tarek', 'Mostafa', 'Mostafa@gmail.com', '01221072230','alex-manshia', FALSE);
INSERT INTO `USER` VALUES ('User2', 'java', 'Mohammed', 'Mohammed', 'Mohammed@gmail.com', '01221072230','alex', FALSE);
INSERT INTO `USER` VALUES ('Admin', 'java', 'Wasfy', 'Omar', 'Omar@gmail.com', '0123456789','miami', TRUE);

INSERT INTO `BOOK` VALUES ('1000', 'BOOK1' ,'Auther1' ,'PUB_1','2001-1-1',1000.0,'Science',10,20);
INSERT INTO `BOOK` VALUES ('2000', 'BOOK2' ,'Auther2','PUB_2','2002-2-2',2000.0,'Art',30,40);
INSERT INTO `BOOK` VALUES ('3000', 'BOOK1_2' ,'Auther1','PUB_1','2003-3-3',3000.0,'Religion',10,20);
INSERT INTO `BOOK` VALUES ('4000', 'BOOK2_2' ,'Auther2','PUB_2','2004-4-4',4000.0,'History',30,40);
INSERT INTO `BOOK` VALUES ('1500', 'BOOK3' ,'Auther3','PUB_3','2005-5-5',5000.0,'History',30,40);
INSERT INTO `BOOK` VALUES ('2500', 'BOOK4' ,'Auther4','PUB_4','2006-6-6',6000.0,'Religion',10,20);
INSERT INTO `BOOK` VALUES ('3500', 'BOOK1_3' ,'Auther1','PUB_1','2007-7-7',7000.0,'Science',50,60);

INSERT INTO `SALES` VALUES (1, '1000', 'User1', '2011-1-1', 5);
INSERT INTO `SALES` VALUES (2, '2000', 'User1', '2012-2-2', 5);
INSERT INTO `SALES` VALUES (3, '3000', 'Mohammed', '2013-3-3', 5);


