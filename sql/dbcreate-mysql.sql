DROP DATABASE IF EXISTS finaltask;
CREATE SCHEMA `finaltask` CHARACTER SET utf8 COLLATE utf8_bin;
USE finaltask;

CREATE TABLE `finaltask`.`roles` (
                                     `role_id` INT NOT NULL,
                                     `name` VARCHAR(20) NOT NULL,
                                     PRIMARY KEY (`role_id`),
                                     UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `finaltask`.`users` (
                                     `user_id` INT NOT NULL AUTO_INCREMENT,
                                     `login` VARCHAR(20) NOT NULL,
                                     `password` VARCHAR(20) NOT NULL,
                                     `first_name` VARCHAR(20) NOT NULL,
                                     `last_name` VARCHAR(20) NOT NULL,
                                     `user_role` INT NOT NULL,
                                     PRIMARY KEY (`user_id`),
                                     UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE);

ALTER TABLE `finaltask`.`users`
    ADD INDEX `user_role_idx` (`user_role` ASC) VISIBLE;
;
ALTER TABLE `finaltask`.`users`
    ADD CONSTRAINT `user_role`
        FOREIGN KEY (`user_role`)
            REFERENCES `finaltask`.`roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE RESTRICT;



INSERT INTO `finaltask`.`roles` (`role_id`, `name`) VALUES ('0', 'admin');
INSERT INTO `finaltask`.`roles` (`role_id`, `name`) VALUES ('1', 'dispetcher');

CREATE TABLE `finaltask`.`flights` (
                                       `flight_id` INT NOT NULL AUTO_INCREMENT,
                                       `flight_name` VARCHAR(20) NOT NULL,
                                       `whence` VARCHAR(20) NOT NULL,
                                       `whereto` VARCHAR(20) NOT NULL,
                                       `date` DATE NOT NULL,
                                       `flight_status` INT NOT NULL,
                                       `crew_id` INT NOT NULL,
                                       PRIMARY KEY (`flight_id`),
                                       UNIQUE INDEX `name_UNIQUE` (`flight_name` ASC) VISIBLE);

CREATE TABLE `finaltask`.`crew` (
                                    `crew_id` INT NOT NULL,
                                    `name` VARCHAR(20) NOT NULL,
                                    PRIMARY KEY (`crew_id`),
                                    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

CREATE TABLE `finaltask`.`flightstatus` (
                                            `fs_id` INT NOT NULL,
                                            `fs_name` VARCHAR(45) NOT NULL,
                                            PRIMARY KEY (`fs_id`),
                                            UNIQUE INDEX `fs_name_UNIQUE` (`fs_name` ASC) VISIBLE);

ALTER TABLE `finaltask`.`flights`
    ADD INDEX `flight_status_idx` (`flight_status` ASC) VISIBLE,
    ADD INDEX `crew_id_idx` (`crew_id` ASC) VISIBLE;
;
ALTER TABLE `finaltask`.`flights`
    ADD CONSTRAINT `flight_status`
        FOREIGN KEY (`flight_status`)
            REFERENCES `finaltask`.`flightstatus` (`fs_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    ADD CONSTRAINT `crew_id`
        FOREIGN KEY (`crew_id`)
            REFERENCES `finaltask`.`crew` (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

CREATE TABLE `finaltask`.`departaments` (
                                            `depart_id` INT NOT NULL,
                                            `depart_name` VARCHAR(20) NOT NULL,
                                            PRIMARY KEY (`depart_id`),
                                            UNIQUE INDEX `depart_name_UNIQUE` (`depart_name` ASC) VISIBLE);

CREATE TABLE `finaltask`.`staff` (
                                     `staff_id` INT NOT NULL AUTO_INCREMENT,
                                     `staff_fname` VARCHAR(20) NOT NULL,
                                     `staff_lname` VARCHAR(20) NOT NULL,
                                     `departament_id` INT NOT NULL,
                                     PRIMARY KEY (`staff_id`));

ALTER TABLE `finaltask`.`staff`
    ADD INDEX `department_id_idx` (`departament_id` ASC) VISIBLE;
;
ALTER TABLE `finaltask`.`staff`
    ADD CONSTRAINT `department_id`
        FOREIGN KEY (`departament_id`)
            REFERENCES `finaltask`.`departaments` (id)
            ON DELETE CASCADE
            ON UPDATE RESTRICT;

CREATE TABLE `finaltask`.`crew_staff` (
                                          `cr_id` INT NOT NULL,
                                          `st_id` INT NOT NULL,
                                          INDEX `cr_id_idx` (`cr_id` ASC) VISIBLE,
                                          INDEX `st_id_idx` (`st_id` ASC) VISIBLE,
                                          CONSTRAINT `cr_id`
                                              FOREIGN KEY (`cr_id`)
                                                  REFERENCES `finaltask`.`crew` (id)
                                                  ON DELETE CASCADE
                                                  ON UPDATE RESTRICT,
                                          CONSTRAINT `st_id`
                                              FOREIGN KEY (`st_id`)
                                                  REFERENCES `finaltask`.`staff` (id)
                                                  ON DELETE CASCADE
                                                  ON UPDATE RESTRICT);

INSERT INTO `finaltask`.`flightstatus` (`fs_id`, `fs_name`) VALUES ('0', 'opened');
INSERT INTO `finaltask`.`flightstatus` (`fs_id`, `fs_name`) VALUES ('1', 'closed');
INSERT INTO `finaltask`.`flightstatus` (`fs_id`, `fs_name`) VALUES ('2', 'canceled');

INSERT INTO `finaltask`.`departaments` (id, `depart_name`) VALUES ('0', 'pilot');
INSERT INTO `finaltask`.`departaments` (id, `depart_name`) VALUES ('1', 'navigator');
INSERT INTO `finaltask`.`departaments` (id, `depart_name`) VALUES ('2', 'spark');
INSERT INTO `finaltask`.`departaments` (id, `depart_name`) VALUES ('3', 'steward');

INSERT INTO `finaltask`.`users` (`login`, `password`, `first_name`, `last_name`, `user_role`) VALUES ('admin', 'admin', 'stas', 'knyzev', '0');
INSERT INTO `finaltask`.`users` (`login`, `password`, `first_name`, `last_name`, `user_role`) VALUES ('disp', 'disp', 'tolia', 'tolian', '1');

