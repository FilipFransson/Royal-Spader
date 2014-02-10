SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `spade_db` ;
CREATE SCHEMA IF NOT EXISTS `spade_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `spade_db` ;

-- -----------------------------------------------------
-- Table `spade_db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`users` ;

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
-- Table `spade_db`.`stores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`stores` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`stores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `organisation_number` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idcompanies_UNIQUE` (`id` ASC),
  INDEX `fk_companies_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_companies_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `spade_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `spade_db`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`categories` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`grocery_lists`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`grocery_lists` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`grocery_lists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_grocery_lists_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_grocery_lists_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `spade_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`brands`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`brands` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`brands` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `organisation_number` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idcompanies_UNIQUE` (`id` ASC),
  INDEX `fk_brands_users1_idx` (`user_id` ASC),
  CONSTRAINT `fk_brands_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `spade_db`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `spade_db`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`products` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `volume` DOUBLE NULL,
  `unit` VARCHAR(45) NULL,
  `brand_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_products_brands1_idx` (`brand_id` ASC),
  INDEX `fk_products_categories1_idx` (`category_id` ASC),
  CONSTRAINT `fk_products_brands1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `spade_db`.`brands` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `spade_db`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`grocery_lists_has_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`grocery_lists_has_products` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`grocery_lists_has_products` (
  `grocery_list_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `volume` INT NULL DEFAULT 1,
  PRIMARY KEY (`grocery_list_id`, `product_id`),
  INDEX `fk_grocery_lists_has_products_products1_idx` (`product_id` ASC),
  INDEX `fk_grocery_lists_has_products_grocery_lists1_idx` (`grocery_list_id` ASC),
  CONSTRAINT `fk_grocery_lists_has_products_grocery_lists1`
    FOREIGN KEY (`grocery_list_id`)
    REFERENCES `spade_db`.`grocery_lists` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_grocery_lists_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `spade_db`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spade_db`.`companies_has_products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spade_db`.`companies_has_products` ;

CREATE TABLE IF NOT EXISTS `spade_db`.`companies_has_products` (
  `store_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`store_id`, `product_id`, `category_id`),
  INDEX `fk_companies_has_products_products1_idx` (`product_id` ASC),
  INDEX `fk_companies_has_products_companies1_idx` (`store_id` ASC),
  INDEX `fk_companies_has_products_categories1_idx` (`category_id` ASC),
  CONSTRAINT `fk_companies_has_products_companies1`
    FOREIGN KEY (`store_id`)
    REFERENCES `spade_db`.`stores` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_companies_has_products_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `spade_db`.`products` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_companies_has_products_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `spade_db`.`categories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO cn;
 DROP USER cn;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'cn' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'cn';
GRANT SELECT ON TABLE `spade_db`.* TO 'cn';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'cn';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'cn';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'cn';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO es;
 DROP USER es;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'es' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'es';
GRANT SELECT ON TABLE `spade_db`.* TO 'es';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'es';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'es';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'es';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO fs;
 DROP USER fs;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'fs' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'fs';
GRANT SELECT ON TABLE `spade_db`.* TO 'fs';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'fs';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'fs';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'fs';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO eg;
 DROP USER eg;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'eg' IDENTIFIED BY 'pw';

GRANT ALL ON `spade_db`.* TO 'eg';
GRANT SELECT ON TABLE `spade_db`.* TO 'eg';
GRANT SELECT, INSERT, TRIGGER ON TABLE `spade_db`.* TO 'eg';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spade_db`.* TO 'eg';
GRANT EXECUTE ON ROUTINE `spade_db`.* TO 'eg';
SET SQL_MODE = '';
GRANT USAGE ON *.* TO vo;
 DROP USER vo;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
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
-- Data for table `spade_db`.`stores`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`stores` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (2, 'ICA', '0987654321', 'icavägen 69', '666-321789', 2);
INSERT INTO `spade_db`.`stores` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (3, 'COOP', '9876543456789', 'vwbivubv', '98-8765678', 4);
INSERT INTO `spade_db`.`stores` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (4, 'Konsum', '98656785567', 'adhbvwow 456', '345-87654', 5);
INSERT INTO `spade_db`.`stores` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (1, 'Carlsberg AB', '1234567890', 'colavägen 13 ', '072-302921', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`categories`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`categories` (`id`, `name`) VALUES (1, 'Soda');
INSERT INTO `spade_db`.`categories` (`id`, `name`) VALUES (2, 'Stuff');

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`grocery_lists`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`grocery_lists` (`id`, `name`, `user_id`) VALUES (1, 'First', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`brands`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`brands` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (1, 'Carlsberg', '1234567890', 'colavägen 13 ', '072-302921', 1);
INSERT INTO `spade_db`.`brands` (`id`, `name`, `organisation_number`, `address`, `phone`, `user_id`) VALUES (2, 'Östers', '98765678', 'sdhbfvwhi 12', '098-345676', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `brand_id`, `category_id`) VALUES (1, 'Coca-Cola', 33, 'cc', 1, 1);
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `brand_id`, `category_id`) VALUES (2, 'Pepsi', 50, 'cc', 1, 2);
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `brand_id`, `category_id`) VALUES (3, 'Pasta', 500, 'g', 1, 1);
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `brand_id`, `category_id`) VALUES (4, 'Fanta', 1, 'l', 1, 2);
INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `brand_id`, `category_id`) VALUES (5, 'Sprite', 2, 'l', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`grocery_lists_has_products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`grocery_lists_has_products` (`grocery_list_id`, `product_id`, `volume`) VALUES (1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `spade_db`.`companies_has_products`
-- -----------------------------------------------------
START TRANSACTION;
USE `spade_db`;
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (2, 1, 1);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (2, 2, 2);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (2, 3, 1);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (2, 4, 2);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (3, 1, 1);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (3, 3, 2);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (3, 5, 1);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (4, 2, 2);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (4, 3, 1);
INSERT INTO `spade_db`.`companies_has_products` (`store_id`, `product_id`, `category_id`) VALUES (4, 4, 2);

COMMIT;

