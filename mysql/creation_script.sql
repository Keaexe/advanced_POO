CREATE DATABASE IF NOT EXISTS POO_ADVANCED;
USE POO_ADVANCED;

CREATE TABLE Category (
    name VARCHAR(100) PRIMARY KEY,
    description TEXT NOT NULL
);

CREATE TABLE Country (
    name VARCHAR(50) PRIMARY KEY,
    is_supported BOOLEAN NOT NULL
);

CREATE TABLE Employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    hiring_date DATE NOT NULL,
    salary DECIMAL(8, 2) NOT NULL
);

CREATE TABLE Coupon (
    code VARCHAR(20) PRIMARY KEY,
    discount_percentage DECIMAL(5, 2) NOT NULL
);

CREATE TABLE Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price_ex_vat DECIMAL(8, 2) NOT NULL,
    vat_percentage DECIMAL(5, 2) NOT NULL,
    left_in_stock INT NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    category_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (category_name) REFERENCES Category(name)
);

CREATE TABLE Locality (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    zipCode VARCHAR(20) NOT NULL,
    is_supported BOOLEAN NOT NULL,
    country_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (country_name) REFERENCES Country(name)
);

CREATE TABLE DeliveryAddress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    is_pickup_point BOOLEAN NOT NULL,
    num_in_street VARCHAR(20) NOT NULL,
    street_name VARCHAR(255) NOT NULL,
    box VARCHAR(10) NULL,
    locality_id INT NOT NULL,
    FOREIGN KEY (locality_id) REFERENCES Locality(id)
);

CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    delivery_address_id INT NOT NULL,
    FOREIGN KEY (delivery_address_id) REFERENCES DeliveryAddress(id)
);

CREATE TABLE SchoolOfThought (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE Referent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    designation VARCHAR(100) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    is_alive BOOLEAN NOT NULL,
    website VARCHAR(255) NULL,
    nickname VARCHAR(100) NULL,
    school_of_thought_id INT NULL,
    FOREIGN KEY (school_of_thought_id) REFERENCES SchoolOfThought(id)
);

CREATE TABLE OrderTable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    creation_time DATETIME NOT NULL,
    employee_id INT NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(id),
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE OrderLine (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    price_at_the_time DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (order_id, itemId),
    FOREIGN KEY (order_id) REFERENCES OrderTable(id),
    FOREIGN KEY (itemId) REFERENCES Item(id)
);

CREATE TABLE OrderCoupon (
    order_id INT NOT NULL,
    coupon_code VARCHAR(20) NOT NULL,
    PRIMARY KEY (order_id, coupon_code),
    FOREIGN KEY (order_id) REFERENCES OrderTable(id),
    FOREIGN KEY (coupon_code) REFERENCES Coupon(code)
);

CREATE TABLE ItemReferent (
    item_id INT NOT NULL,
    referent_id INT NOT NULL,
    PRIMARY KEY (itemId, referent_id),
    FOREIGN KEY (itemId) REFERENCES Item(id),
    FOREIGN KEY (referent_id) REFERENCES Referent(id)
);