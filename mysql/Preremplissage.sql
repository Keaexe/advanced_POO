USE POO_ADVANCED;

-- 1. Lookups & Independent tables
INSERT INTO category (name, description) VALUES ('Books', 'Philosophical and educational literature');
INSERT INTO school_of_thought (id, name, description) VALUES (1, 'Stoicism', 'Philosophy of personal ethics informed by logic');
INSERT INTO school_of_thought (name, description) VALUES ('Chillisme', 'Chill man, on est cool');
INSERT INTO coupon (code, discount_percentage) VALUES ('WELCOME10', 10.00);
INSERT INTO country (name, is_supported) VALUES ('Belgium', TRUE);

-- 2. Dependant on lookups
INSERT INTO item (id, name, price_ex_vat, vat_percentage, left_in_stock, description, image_url, category_name)
VALUES (1, 'Meditations', 15.00, 6.00, 100, 'Marcus Aurelius personal writings', 'http://example.com/med.jpg', 'Books');

INSERT INTO locality (id, name, zipCode, is_supported, country_name)
VALUES (1, 'Anhée', '5537', TRUE, 'Belgium');

-- 3. Dependent on locality / school
INSERT INTO delivery_address (id, is_pickup_point, num_in_street, street_name, box, locality_id)
VALUES (1, FALSE, '12A', 'Rue de la Station', NULL, 1);

INSERT INTO referent (id, designation, first_name, last_name, birth_date, is_alive, website, nickname, school_of_thought_id)
VALUES (1, 'Philosopher', 'Marcus', 'Aurelius', '0121-04-26', FALSE, NULL, 'The Wise', 1);

-- 4. Clients and Staff
INSERT INTO employee (id, first_name, last_name, hiring_date, salary)
VALUES (1, 'Jane', 'Doe', '2026-01-15', 2500.00);

INSERT INTO client (id, first_name, last_name, delivery_address_id)
VALUES (1, 'John', 'Smith', 1);

-- 5. Transactions
INSERT INTO order_table (id, creation_time, employee_id, client_id)
VALUES (1, NOW(), 1, 1);

INSERT INTO oder_line (order_id, item_id, quantity, price_at_the_time)
VALUES (1, 1, 2, 15.00);
