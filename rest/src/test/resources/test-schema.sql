DROP TABLE IF EXISTS department;
CREATE TABLE department (
  id    BIGINT          NOT NULL AUTO_INCREMENT,
  name_department       VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id    BIGINT          NOT NULL AUTO_INCREMENT,
  first_name       VARCHAR(45) NOT NULL ,
  `last_name` VARCHAR(45) NOT NULL ,
  `dob` DATE NULL,
  `salary` INT NULL,
  `id_department` BIGINT NOT NULL ,
  PRIMARY KEY (id),
  FOREIGN KEY (`id_department`)  REFERENCES `department` (`id`)
);

COMMIT;