CREATE DATABASE POO_ADVANCED

USE POO_ADVANCED

CREATE TABLE Country (
    name VARCHAR(50) PRIMARY KEY,
    isSupported BIT NOT NULL
);

CREATE TABLE Employee (
    id INT PRIMARY KEY,
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
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    priceExVAT DECIMAL(8, 2) NOT NULL,
    VATPercentage DECIMAL(5, 2) NOT NULL,
    leftInStock INT NOT NULL,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL
);

CREATE TABLE Locality (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    zipCode VARCHAR(20),
    isSupported BIT,
    countryName VARCHAR(50),
    FOREIGN KEY (countryName) REFERENCES Country(name)
);

CREATE TABLE Address (
    id INT PRIMARY KEY,
    isPickupPoint BIT,
    numInStreet VARCHAR(20),
    streetName VARCHAR(255),
    box VARCHAR(20),
    localityId INT,
    FOREIGN KEY (localityId) REFERENCES Locality(id)
);

CREATE TABLE Referent (
    id INT PRIMARY KEY,
    designation VARCHAR(25) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    birthDate DATE NOT NULL,
    isAlive BIT NOT NULL,
    address INT NOT NULL,
    website VARCHAR(255) NULL,
    nickname VARCHAR(100) NULL
	FOREIGN KEY (address) REFERENCES Address(id)

);

CREATE TABLE OrderTable (
    id INT PRIMARY KEY,
    receiverFirstName VARCHAR(100) NOT NULL,
    receiverLastName VARCHAR(100) NOT NULL,
    creationTime DATETIME NOT NULL,
    employeeId INT NOT NULL,
    shippingAddressId INT NOT NULL,
    FOREIGN KEY (employeeId) REFERENCES Employee(id),
    FOREIGN KEY (shippingAddressId) REFERENCES Address(id)
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