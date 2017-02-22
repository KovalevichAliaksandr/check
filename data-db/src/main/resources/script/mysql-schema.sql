DROP SCHEMA IF EXISTS job;
CREATE SCHEMA IF NOT EXISTS `job` DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS `job`.`department` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name_department` VARCHAR(45) NOT NULL UNIQUE ,
  PRIMARY KEY (`id`))
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `job`.`employee` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL ,
  `dob` DATE NULL,
  `salary` INT NULL,
  `id_department` BIGINT NOT NULL ,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_department`)  REFERENCES job.`department` (`id`)
    )
  DEFAULT CHARACTER SET = utf8;
COMMIT;
# SELECT * FROM job.department