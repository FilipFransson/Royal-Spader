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

INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `user_id`) VALUES (1, 'Coca-Cola', '1234567890', 'colavägen 13 ', '072-302921', 'producer', 1);

INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `user_id`) VALUES (2, 'ICA', '0987654321', 'icavägen 69', '666-321789', 'butik', 3);

INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `user_id`) VALUES (3, 'COOP', '9876543456789', 'vwbivubv', '98-8765678', 'butik', 4);

INSERT INTO `spade_db`.`companies` (`id`, `name`, `organisation_number`, `address`, `phone`, `type`, `user_id`) VALUES (4, 'Konsum', '98656785567', 'adhbvwow 456', '345-87654', 'butik', 2);


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

INSERT INTO `spade_db`.`grocery_lists` (`id`, `name`, `user_id`) VALUES (1, 'First', 3);


COMMIT;



-- -----------------------------------------------------

-- Data for table `spade_db`.`products`

-- -----------------------------------------------------

START TRANSACTION;

USE `spade_db`;

INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `category_id`, `company_id`) VALUES (1, 'Coca-Cola', 33, 'cc', 1, 1);

INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `category_id`, `company_id`) VALUES (2, 'Pepsi', 50, 'cc', 1, 1);

INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `category_id`, `company_id`) VALUES (3, 'Pasta', 500, 'g', 1, 1);

INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `category_id`, `company_id`) VALUES (4, 'Fanta', 1, 'l', 1, 1);

INSERT INTO `spade_db`.`products` (`id`, `name`, `volume`, `unit`, `category_id`, `company_id`) VALUES (5, 'Sprite', 2, 'l', 1, 1);


COMMIT;



-- -----------------------------------------------------

-- Data for table `spade_db`.`grocery_lists_has_products`

-- -----------------------------------------------------

START TRANSACTION;
USE `spade_db`;

INSERT INTO `spade_db`.`grocery_lists_has_products` (`grocery_lists_id`, `product_id`, `volume`) VALUES (1, 1, NULL);


COMMIT;



-- -----------------------------------------------------

-- Data for table `spade_db`.`companies_has_products`

-- -----------------------------------------------------

START TRANSACTION;
USE `spade_db`;

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (2, 1, 1);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (2, 2, 2);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (2, 3, 1);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (2, 4, 2);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (3, 1, 1);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (3, 3, 2);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (3, 5, 1);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (4, 2, 2);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (4, 3, 1);

INSERT INTO `spade_db`.`companies_has_products` (`company_id`, `product_id`, `category_id`) VALUES (4, 4, 2);


COMMIT;
