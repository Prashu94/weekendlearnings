create schema springlearning;
use springlearning;
drop table if exists T_ACCOUNT_BENEFICIARY;
drop table if exists T_ACCOUNT_CREDIT_CARD;
drop table if exists T_ACCOUNT;
drop table if exists T_RESTAURANT;
drop table if exists T_REWARD;
drop table if exists DUAL_REWARD_CONFIRMATION_NUMBER;

create table T_ACCOUNT (ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID), NUMBER varchar(9), NAME varchar(50));
create table T_ACCOUNT_CREDIT_CARD (ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID), ACCOUNT_ID integer, NUMBER varchar(16), unique(ACCOUNT_ID, NUMBER));
create table T_ACCOUNT_BENEFICIARY (ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID), ACCOUNT_ID integer, NAME varchar(50), ALLOCATION_PERCENTAGE decimal(3,2), SAVINGS decimal(8,2));
create table T_RESTAURANT (ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID), MERCHANT_NUMBER varchar(10), NAME varchar(80), BENEFIT_PERCENTAGE decimal(3,2), BENEFIT_AVAILABILITY_POLICY varchar(1));
create table T_REWARD (ID INT NOT NULL AUTO_INCREMENT, PRIMARY KEY(ID), CONFIRMATION_NUMBER varchar(25), REWARD_AMOUNT decimal(8,2), REWARD_DATE date, ACCOUNT_NUMBER varchar(9), DINING_AMOUNT decimal(8,2), DINING_MERCHANT_NUMBER varchar(10), DINING_DATE date);

create table DUAL_REWARD_CONFIRMATION_NUMBER (ZERO int);
insert into DUAL_REWARD_CONFIRMATION_NUMBER values (0);
       
insert into T_ACCOUNT (NUMBER, NAME) values ('123456789', 'Keith and Keri Donald');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456001', 'Dollie R. Adams');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456002', 'Cornelia J. Andresen');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456003', 'Coral Villareal Betancourt');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456004', 'Chad I. Cobbs');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456005', 'Michael C. Feller');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456006', 'Michael J. Grover');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456007', 'John C. Howard');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456008', 'Ida Ketterer');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456009', 'Laina Ochoa Lucero');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456010', 'Wesley M. Mayo');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456011', 'Leslie F. Mcclary');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456012', 'John D. Mudra');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456013', 'Pietronella J. Nielsen');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456014', 'John S. Oleary');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456015', 'Glenda D. Smith');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456016', 'Willemina O. Thygesen');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456017', 'Antje Vogt');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456018', 'Julia Weber');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456019', 'Mark T. Williams');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456020', 'Christine J. Wilson');

insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (1, '1234123412341234');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (2, '1234123412340001');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (3, '1234123412340002');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (4, '1234123412340003');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (5, '1234123412340004');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (6, '1234123412340005');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (7, '1234123412340006');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (8, '1234123412340007');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (9, '1234123412340008');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (10, '1234123412340009');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (11, '1234123412340010');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (12, '1234123412340011');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (13, '1234123412340012');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (14, '1234123412340013');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (15, '1234123412340014');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (16, '1234123412340015');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (17, '1234123412340016');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (18, '1234123412340017');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (19, '1234123412340018');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (20, '1234123412340019');
insert into T_ACCOUNT_CREDIT_CARD (ACCOUNT_ID, NUMBER) values (21, '1234123412340020');

insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (1, 'Annabelle', .5, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (1, 'Corgan', .5, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (4, 'Antolin', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (4, 'Argus', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (4, 'Gian', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (4, 'Argeo', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (9, 'Kai', .33, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (9, 'Kasper', .33, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (9, 'Ernst', .34, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (13, 'Brian', .75, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (13, 'Shelby', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (16, 'Charles', .50, 0.00);	
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (16, 'Thomas', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (16, 'Neil', .25, 0.00);
insert into T_ACCOUNT_BENEFICIARY (ACCOUNT_ID, NAME, ALLOCATION_PERCENTAGE, SAVINGS) 
	values (18, 'Daniel', 1.0, 0.00);

insert into T_RESTAURANT (MERCHANT_NUMBER, NAME, BENEFIT_PERCENTAGE, BENEFIT_AVAILABILITY_POLICY) 
	values ('1234567890', 'AppleBees', .08, 'A');
    
    
select * from t_account_beneficiary;

-- TACO ORDER 
CREATE TABLE IF NOT EXISTS Taco_Order (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  delivery_Name VARCHAR(50) NOT NULL,
  delivery_Street VARCHAR(50) NOT NULL,
  delivery_City VARCHAR(50) NOT NULL,
  delivery_State VARCHAR(2) NOT NULL,
  delivery_Zip VARCHAR(10) NOT NULL,
  cc_number VARCHAR(16) NOT NULL,
  cc_expiration VARCHAR(5) NOT NULL,
  cc_cvv VARCHAR(3) NOT NULL,
  placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  taco_order BIGINT NOT NULL,
  taco_order_key BIGINT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  FOREIGN KEY (taco_order) REFERENCES Taco_Order(id)
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
  ingredient VARCHAR(4) NOT NULL,
  taco BIGINT NOT NULL,
  taco_key BIGINT NOT NULL,
  FOREIGN KEY (ingredient) REFERENCES Ingredient(id)
);

CREATE TABLE IF NOT EXISTS Ingredient (
  id VARCHAR(4) NOT NULL PRIMARY KEY,
  name VARCHAR(25) NOT NULL,
  type VARCHAR(10) NOT NULL
);


delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;
insert into Ingredient (id, name, type) 
                values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type) 
                values ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) 
                values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type) 
                values ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) 
                values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) 
                values ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) 
                values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) 
                values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) 
                values ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) 
                values ('SRCR', 'Sour Cream', 'SAUCE');

select * from Taco;
select * from Ingredient;
select * from Ingredient_Ref;
select * from Taco_Order;
select * from taco_order_tacos;

create schema springdatajpa;
use springdatajpa;
select * from Taco;
select * from Ingredient;
select * from Taco_Order;
select * from taco_order_tacos;

CREATE TABLE `ingredient` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taco_ingredients` (
  `taco_id` bigint NOT NULL,
  `ingredients_id` varchar(255) NOT NULL,
  KEY `FK7y679y77n5e75s3ss1v7ff14j` (`ingredients_id`),
  KEY `FK27rycuh3mjaepnba0j6m8xl4q` (`taco_id`),
  CONSTRAINT `FK27rycuh3mjaepnba0j6m8xl4q` FOREIGN KEY (`taco_id`) REFERENCES `taco` (`id`),
  CONSTRAINT `FK7y679y77n5e75s3ss1v7ff14j` FOREIGN KEY (`ingredients_id`) REFERENCES `ingredient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taco_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cc_cvv` varchar(255) DEFAULT NULL,
  `cc_expiration` varchar(255) DEFAULT NULL,
  `cc_number` varchar(255) DEFAULT NULL,
  `delivery_city` varchar(255) DEFAULT NULL,
  `delivery_name` varchar(255) DEFAULT NULL,
  `delivery_state` varchar(255) DEFAULT NULL,
  `delivery_street` varchar(255) DEFAULT NULL,
  `delivery_zip` varchar(255) DEFAULT NULL,
  `placed_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `taco_order_tacos` (
  `taco_order_id` bigint NOT NULL,
  `tacos_id` bigint NOT NULL,
  UNIQUE KEY `UK_r4rma0h2pka5irll8qnlnwlwh` (`tacos_id`),
  KEY `FKs8yteduju5tndbivxbmdrbsyy` (`taco_order_id`),
  CONSTRAINT `FKfwvqtnjfview9e5f7bfqtd1ns` FOREIGN KEY (`tacos_id`) REFERENCES `taco` (`id`),
  CONSTRAINT `FKs8yteduju5tndbivxbmdrbsyy` FOREIGN KEY (`taco_order_id`) REFERENCES `taco_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
