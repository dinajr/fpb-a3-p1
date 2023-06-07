-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS mydb DEFAULT CHARACTER SET utf8 ;
USE mydb ;

-- -----------------------------------------------------
-- Table mydb.series
-- -----------------------------------------------------
CREATE TABLE series (
  idseries INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  temporadas INT NOT NULL,
  anolancamento INT NOT NULL,
  streamingplat VARCHAR(100) NOT NULL
  );

insert into series( nome, temporadas, anolancamento, streamingplat)
values('One Piece', 1000, 1998, 'Netflix'); 

select * from series;
-- -----------------------------------------------------
-- Table mydb.genero
-- -----------------------------------------------------
CREATE TABLE genero (
  idgenero INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  descricao VARCHAR(100) NOT NULL
);
insert into genero (nome, descricao)
values('Aventura', 'pirataria');
select * from genero;


-- -----------------------------------------------------
-- Table mydb.series_has_genero
-- -----------------------------------------------------
CREATE TABLE series_genero (
  series INT NOT NULL,
  genero INT NOT NULL,
  PRIMARY KEY (series, genero),
  INDEX fk_series_genero_genero1_idx (genero ASC) VISIBLE,
  INDEX fk_series_genero_series_idx (series ASC) VISIBLE,
  CONSTRAINT fk_series_genero_series
    FOREIGN KEY (series)
    REFERENCES series (idseries)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_series_genero_genero1
    FOREIGN KEY (genero)
    REFERENCES genero (idgenero)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
