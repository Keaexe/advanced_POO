CREATE DATABASE IF NOT EXISTS POO_ADVANCED;
USE POO_ADVANCED;

CREATE TABLE Category (
    name VARCHAR(100) PRIMARY KEY,
    description TEXT
);

CREATE TABLE Country (
    name VARCHAR(50) PRIMARY KEY,
    isSupported BOOLEAN NOT NULL
);

CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    hiringDate DATE NOT NULL,
    salary DECIMAL(8, 2) NOT NULL
);

CREATE TABLE Coupon (
    code VARCHAR(20) PRIMARY KEY,
    discountPercentage DECIMAL(5, 2) NOT NULL
);

CREATE TABLE Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    priceExVAT DECIMAL(8, 2) NOT NULL,
    VATPercentage DECIMAL(5, 2) NOT NULL,
    leftInStock INT NOT NULL,
    description TEXT NOT NULL,
    imagePath VARCHAR(255) NOT NULL,
    categoryName VARCHAR(100) NOT NULL,
    FOREIGN KEY (categoryName) REFERENCES Category(name)
);

CREATE TABLE Locality (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    zipCode VARCHAR(20),
    isSupported BOOLEAN,
    countryName VARCHAR(50),
    FOREIGN KEY (countryName) REFERENCES Country(name)
);

CREATE TABLE DeliveryAddress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isPickupPoint BOOLEAN,
    numInStreet VARCHAR(20),
    streetName VARCHAR(255),
    box VARCHAR(20),
    localityId INT,
    FOREIGN KEY (localityId) REFERENCES Locality(id)
);

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50),
    lastName VARCHAR(100),
    deliveryAddressId INT,
    FOREIGN KEY (deliveryAddressId) REFERENCES DeliveryAddress(id)
);

CREATE TABLE SchoolOfThough (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT
);

CREATE TABLE Referent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    designation VARCHAR(25) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birthDate DATE NOT NULL,
    isAlive BOOLEAN NOT NULL,
    website VARCHAR(255) NULL,
    nickname VARCHAR(100) NULL,
    schoolOfThoughId INT NULL,
    FOREIGN KEY (schoolOfThoughId) REFERENCES SchoolOfThough(id)
);

CREATE TABLE OrderTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    creationTime DATETIME NOT NULL,
    employeeId INT NOT NULL,
    clientId INT NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(id),
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

CREATE TABLE OrderLine (
    orderId INT NOT NULL,
    itemId INT NOT NULL,
    quantity INT NOT NULL,
    priceAtTheTime DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (orderId, itemId),
    FOREIGN KEY (orderId) REFERENCES OrderTable(id),
    FOREIGN KEY (itemId) REFERENCES Item(id)
);

CREATE TABLE OrderCoupon (
    orderId INT NOT NULL,
    couponCode VARCHAR(20) NOT NULL,
    PRIMARY KEY (orderId, couponCode),
    FOREIGN KEY (orderId) REFERENCES OrderTable(id),
    FOREIGN KEY (couponCode) REFERENCES Coupon(code)
);

CREATE TABLE ItemReferent (
    itemId INT NOT NULL,
    referentId INT NOT NULL,
    PRIMARY KEY (itemId, referentId),
    FOREIGN KEY (itemId) REFERENCES Item(id),
    FOREIGN KEY (referentId) REFERENCES Referent(id)
);