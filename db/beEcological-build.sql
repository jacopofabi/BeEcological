CREATE SCHEMA beecological;

CREATE TABLE beecological.user(
	username VARCHAR(25) PRIMARY KEY,
	password VARCHAR(50),
    name VARCHAR(25),
    surname VARCHAR(25),
    email VARCHAR(50),
    phone CHAR(10),
    ecopoints INT
);

CREATE TABLE beecological.center(
	centerName VARCHAR(50) PRIMARY KEY,
    centerPhone CHAR(10),
    city VARCHAR(25),
    address VARCHAR(50),
    CAP CHAR(5),
    num VARCHAR(3)
);

CREATE TABLE beecological.owner(
	username VARCHAR(25) PRIMARY KEY,
	password VARCHAR(50),
    name VARCHAR(25),
    surname VARCHAR(25),
    email VARCHAR(50),
    phone CHAR(10),
    center VARCHAR(50),
    FOREIGN KEY (center) REFERENCES beecological.center(centerName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE beecological.bookingRequest(
	ID INT AUTO_INCREMENT PRIMARY KEY, 
	user VARCHAR(25),
    center VARCHAR(50),
    date DATE,
    time TIME,
    status CHAR(1),
    UNIQUE (user,center,date,time),
    FOREIGN KEY (user) REFERENCES beecological.user(username) ON DELETE CASCADE ON UPDATE CASCADE, 
    FOREIGN KEY (center) REFERENCES beecological.center(centerName) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE beecological.unload(
	user VARCHAR(25),
    center VARCHAR(50),
    date DATE,
    time TIME,
    UNIQUE (user,center,date,time),
    FOREIGN KEY (user) REFERENCES beecological.user(username) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (center) REFERENCES beecological.center(centerName) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (user,center,date,time)
);

CREATE TABLE beecological.waste(
    name VARCHAR(50) PRIMARY KEY,
    value_per_kg INT
);

CREATE TABLE beecological.wasteUnloaded(
	user VARCHAR(25),
    center VARCHAR(50),
    date DATE,
    time TIME,
    waste VARCHAR(50),
    wasteQuantity INT,
    ecoPoints INT,
    UNIQUE (user,center,date,time,waste),
    FOREIGN KEY(waste) REFERENCES beecological.waste(name) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY(user,center,date,time) REFERENCES beecological.unload(user,center,date,time) ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY(user,center,date,time,waste)
);

DELIMITER $$
USE `beecological`$$
CREATE DEFINER = CURRENT_USER TRIGGER `beecological`.`wasteunloaded_BEFORE_INSERT` BEFORE INSERT ON `wasteunloaded` FOR EACH ROW
BEGIN
	DECLARE ecoPointsAmount INT;
    SELECT waste.value_per_kg FROM beecological.waste WHERE waste.name = NEW.waste
    INTO ecoPointsAmount;
	SET NEW.ecopoints = ecoPointsAmount*NEW.wasteQuantity;
END$$

DELIMITER $$
USE `beecological`$$
CREATE DEFINER = CURRENT_USER TRIGGER `beecological`.`wasteunloaded_AFTER_INSERT` AFTER INSERT ON `wasteunloaded` FOR EACH ROW
BEGIN
	DECLARE userUnload VARCHAR(25);
	DECLARE ecoPointsAmount INT;
    SET userUnload = NEW.user;
    SET ecoPointsAmount = NEW.ecoPoints;
    
    UPDATE beecological.user SET beecological.user.ecoPoints = beecological.user.ecoPoints + ecoPointsAmount
    WHERE beecological.user.username = userUnload;
END$$

DELIMITER $$
USE `beecological`$$
CREATE DEFINER = CURRENT_USER TRIGGER `beecological`.`wasteunloaded_AFTER_DELETE` AFTER DELETE ON `wasteunloaded` FOR EACH ROW
BEGIN
	DECLARE ecoPointsAmount INT;
    DECLARE userToRemovePoints VARCHAR(25);
    
    SET ecoPointsAmount = OLD.ecoPoints;
    SET userToRemovePoints = OLD.user;
    
    UPDATE beecological.user SET user.ecoPoints = user.ecoPoints-ecoPointsAmount WHERE beecological.user.username = userToRemovePoints;
END$$


ALTER TABLE beecological.bookingRequest auto_increment = 0;
ALTER TABLE beecological.unload auto_increment = 0;
ALTER TABLE beecological.waste auto_increment = 0;