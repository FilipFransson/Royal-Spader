SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `spade_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `spade_db` ;

-- -----------------------------------------------------
-- Table `spade_db`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `SALT` VARCHAR(45) NULL,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`companies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`companies` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `organisation_number` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idcompanies_UNIQUE` (`id` ASC),
  INDEX `fk_companies_users_idx` (`users_id` ASC),
  CONSTRAINT `fk_companies_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `spade_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `spade_db`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`grocery_lists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`grocery_lists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_grocery_lists_users1_idx` (`users_id` ASC),
  CONSTRAINT `fk_grocery_lists_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `spade_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `volume` DOUBLE NULL,
  `unit` VARCHAR(45) NULL,
  `companies_id` INT NOT NULL,
  `categories_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_companies1_idx` (`companies_id` ASC),
  INDEX `fk_products_categories1_idx` (`categories_id` ASC),
  CONSTRAINT `fk_products_companies1`
    FOREIGN KEY (`companies_id`)
    REFERENCES `spade_db`.`companies` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`categories_id`)
    REFERENCES `spade_db`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`grocery_lists_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`grocery_lists_has_products` (
  `grocery_lists_id` INT NOT NULL,
  `products_id` INT NOT NULL,
  `volume` INT NULL DEFAULT 1,
  PRIMARY KEY (`grocery_lists_id`, `products_id`),
  INDEX `fk_grocery_lists_has_products_products1_idx` (`products_id` ASC),
  INDEX `fk_grocery_lists_has_products_grocery_lists1_idx` (`grocery_lists_id` ASC),
  CONSTRAINT `fk_grocery_lists_has_products_grocery_lists1`
    FOREIGN KEY (`grocery_lists_id`)
    REFERENCES `spade_db`.`grocery_lists` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_grocery_lists_has_products_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `spade_db`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`companies_has_products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spade_db`.`companies_has_products` (
  `companies_id` INT NOT NULL,
  `products_id` INT NOT NULL,
  `categories_id` INT NOT NULL,
  PRIMARY KEY (`companies_id`, `products_id`, `categories_id`),
  INDEX `fk_companies_has_products_products1_idx` (`products_id` ASC),
  INDEX `fk_companies_has_products_companies1_idx` (`companies_id` ASC),
  INDEX `fk_companies_has_products_categories1_idx` (`categories_id` ASC),
  CONSTRAINT `fk_companies_has_products_companies1`
    FOREIGN KEY (`companies_id`)
    REFERENCES `spade_db`.`companies` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_companies_has_products_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `spade_db`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_companies_has_products_categories1`
    FOREIGN KEY (`categories_id`)
    REFERENCES `spade_db`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE USER 'cn' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'cn';
GRANT SELECT ON TABLE `spade_db`.* TO 'cn';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'cn';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'cn';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'cn';
CREATE USER 'es' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'es';
GRANT SELECT ON TABLE `spade_db`.* TO 'es';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'es';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'es';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'es';
CREATE USER 'fs' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'fs';
GRANT SELECT ON TABLE `spade_db`.* TO 'fs';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'fs';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'fs';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'fs';
CREATE USER 'eg' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'eg';
GRANT SELECT ON TABLE `spade_db`.* TO 'eg';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'eg';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'eg';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'eg';
CREATE USER 'vo' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'vo';
GRANT SELECT ON TABLE `spade_db`.* TO 'vo';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'vo';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'vo';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'vo';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `spade_db`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (1, 'Fredrik', 'Svensson', 'admin', 'fredrik@spades.com', 'fs', 'pw', 'asd');
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (2, 'Erik', 'Gustavsson', 'admin', 'erik@spades.com', 'eg', 'pw', 'asd');
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (3, 'Victor', 'Ohlsson', 'admin', 'victor@spades.com', 'vo', 'pw', 'asd');
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (4, 'Cecilia', 'Nilsson', 'admin', 'cissi@spades.com', 'cn', 'pw', 'asd');
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (5, 'Emil', 'Svensson', 'admin', 'emil@spades.com', 'es', 'pw', 'asd');
INSERT INTO `spade_db`.`users` (`id`, `first_name`, `last_name`, `role`, `email`, `username`, `password`, `SALT`) VALUES (6, 'Filip', 'Fransson', 'admin', 'filip@spades.com', 'ff', 'pw', 'asd');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`companies`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `users_id`) VALUES (1, 'Coca-Cola', '1234567890', 'colavägen 13 ', '072-302921', 'producer', 1);
INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `users_id`) VALUES (2, 'ICA', '0987654321', 'icavägen 69', '666-321789', 'butik', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`categories`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`categories` (`id`, `name`) VALUES (1, 'Soda');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`grocery_lists`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`grocery_lists` (`id`, `name`, `users_id`) VALUES (1, 'First', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `companies_id`, `categories_id`) VALUES (1, 'Coca-Cola', 33, 'cc', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`grocery_lists_has_products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`grocery_lists_has_products` (`grocery_lists_id`, `products_id`, `volume`) VALUES (1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`companies_has_products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`companies_has_products` (`companies_id`, `products_id`, `categories_id`) VALUES (2, 1, 1);

COMMIT;

