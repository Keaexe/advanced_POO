IF DB_ID('exam_db') IS NULL
    EXEC('CREATE DATABASE exam_db');

USE exam_db;

CREATE TABLE category
(
    name VARCHAR(100) PRIMARY KEY CHECK (TRIM(name) <> ''),
    description VARCHAR(MAX) NOT NULL CHECK (TRIM(description) <> '')
);

CREATE TABLE country
(
    name VARCHAR(50) PRIMARY KEY CHECK (TRIM(name) <> ''),
    is_supported BIT NOT NULL
);

CREATE TABLE employee
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL CHECK (TRIM(first_name) <> ''),
    last_name VARCHAR(100) NOT NULL CHECK (TRIM(last_name) <> ''),
    hiring_date DATE NOT NULL,
    salary DECIMAL(8, 2) NOT NULL CHECK (salary >= 0)
);

CREATE TABLE coupon
(
    code VARCHAR(20) PRIMARY KEY CHECK (TRIM(code) <> ''),
    discount_percentage DECIMAL(5, 2) NOT NULL CHECK (discount_percentage > 0 AND discount_percentage <= 100)
);

CREATE TABLE item
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (TRIM(name) <> ''),
    price_ex_vat DECIMAL(8, 2) NOT NULL CHECK (price_ex_vat >= 0),
    vat_percentage DECIMAL(5, 2) NOT NULL CHECK (vat_percentage >= 0 AND vat_percentage <= 100),
    left_in_stock INT NOT NULL CHECK (left_in_stock >= 0),
    description VARCHAR(MAX) NOT NULL CHECK (TRIM(description) <> ''),
    image_url VARCHAR(255) NOT NULL CHECK (TRIM(image_url) <> ''),
    category_name VARCHAR(100) NOT NULL CHECK (TRIM(category_name) <> ''),

    FOREIGN KEY (category_name) REFERENCES category(name)
);

CREATE TABLE locality
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (TRIM(name) <> ''),
    zipCode VARCHAR(20) NOT NULL CHECK (TRIM(zipCode) <> ''),
    is_supported BIT NOT NULL,
    country_name VARCHAR(50) NOT NULL CHECK (TRIM(country_name) <> ''),

    FOREIGN KEY (country_name) REFERENCES country(name)
);

CREATE TABLE delivery_address
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    is_pickup_point BIT NOT NULL,
    num_in_street VARCHAR(20) NOT NULL CHECK (TRIM(num_in_street) <> ''),
    street_name VARCHAR(255) NOT NULL CHECK (TRIM(street_name) <> ''),
    box VARCHAR(10) NULL CHECK (box IS NULL OR (TRIM(box)) <> ''),
    locality_id INT NOT NULL,

    FOREIGN KEY (locality_id) REFERENCES locality(id)
);

CREATE TABLE client
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL CHECK (TRIM(first_name) <> ''),
    last_name VARCHAR(100) NOT NULL CHECK (TRIM(last_name) <> ''),
    delivery_address_id INT NOT NULL,

    FOREIGN KEY (delivery_address_id) REFERENCES delivery_address(id)
);

CREATE TABLE school_of_thought
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE CHECK (TRIM(name) <> ''),
    description VARCHAR(MAX) NOT NULL CHECK (TRIM(description) <> '')
);

CREATE TABLE referent
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    designation VARCHAR(100) NOT NULL CHECK (TRIM(designation) <> ''),
    first_name VARCHAR(50) NOT NULL CHECK (TRIM(first_name) <> ''),
    last_name VARCHAR(100) NOT NULL CHECK (TRIM(last_name) <> ''),
    birth_date DATE NOT NULL,
    is_alive BIT NOT NULL,
    website VARCHAR(255) NULL CHECK (website IS NULL OR (TRIM(website) <> '')),
    nickname VARCHAR(100) NULL CHECK (nickname IS NULL OR (TRIM(nickname) <> '')),
    school_of_thought_id INT NULL,

    FOREIGN KEY (school_of_thought_id) REFERENCES school_of_thought(id)
);

CREATE TABLE order_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    creation_time DATETIME NOT NULL,
    employee_id INT NOT NULL,
    client_id INT NOT NULL,

    FOREIGN KEY (employee_id) REFERENCES employee(id),
    FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE order_line
(
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    price_at_the_time DECIMAL(10, 2) NOT NULL CHECK (price_at_the_time >= 0),

    PRIMARY KEY (order_id, item_id),

    FOREIGN KEY (order_id) REFERENCES order_table(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE order_coupon
(
    order_id INT NOT NULL,
    coupon_code VARCHAR(20) NOT NULL CHECK (TRIM(coupon_code) <> ''),

    PRIMARY KEY (order_id, coupon_code),

    FOREIGN KEY (order_id) REFERENCES order_table(id),
    FOREIGN KEY (coupon_code) REFERENCES coupon(code)
);

CREATE TABLE item_referent
(
    item_id INT NOT NULL,
    referent_id INT NOT NULL,

    PRIMARY KEY (item_id, referent_id),

    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (referent_id) REFERENCES referent(id)
);