USE POO_ADVANCED;

-- category
INSERT INTO category (name, description) VALUES
    ('Books', 'Philosophical and educational literature'),
    ('Wellness', 'Objects supposed to improve spiritual comfort'),
    ('Accessories', 'Small accessories for digital wellness'),
    ('Software', 'Mystical software and productivity utilities');

-- school_of_thought
INSERT INTO school_of_thought (name, description) VALUES
    ('Stoicism', 'Philosophy of personal ethics informed by logic'),
    ('Chillisme', 'Chill man, on est cool'),
    ('Techno-mysticism', 'Belief that machines can be spiritually aligned'),
    ('Minimalism', 'Less is more, especially in your desktop folder');

-- coupon
INSERT INTO coupon (code, discount_percentage) VALUES
    ('WELCOME10', 10.00),
    ('ZEN20', 20.00),
    ('NOBUGS5', 5.00);

-- country
INSERT INTO country (name, is_supported) VALUES
    ('Belgium', TRUE),
    ('France', TRUE),
    ('Luxembourg', TRUE),
    ('Atlantis', FALSE);

-- locality
INSERT INTO locality (name, zip_code, is_supported, country_name) VALUES
    ('Anhée', '5537', TRUE, 'Belgium'),
    ('Namur', '5000', TRUE, 'Belgium'),
    ('Dinant', '5500', TRUE, 'Belgium'),
    ('Paris', '75000', TRUE, 'France'),
    ('Poseidon City', '0000', FALSE, 'Atlantis');

-- delivery_address
INSERT INTO delivery_address (is_pickup_point, num_in_street, street_name, box, locality_id) VALUES
    (FALSE, '12A', 'Rue de la Station', NULL, 1),
    (FALSE, '42', 'Rue des Philosophes', 'B2', 2),
    (FALSE, '7', 'Avenue du Debug', NULL, 3),
    (FALSE, '101', 'Boulevard du Karma', NULL, 4),
    (TRUE, '1', 'Rue du Magasin', NULL, 2);

-- employee
INSERT INTO employee (first_name, last_name, hiring_date, salary) VALUES
    ('Jane', 'Doe', '2026-01-15', 2500.00),
    ('Luc', 'Martin', '2025-09-01', 2800.00),
    ('Sophie', 'Lambert', '2024-06-10', 3100.00);

-- client
INSERT INTO client (first_name, last_name, delivery_address_id) VALUES
     ('John', 'Smith', 1),
     ('Alice', 'Durand', 2),
     ('Bob', 'Kernel', 3),
     ('Clara', 'Zenman', 4);

-- item
INSERT INTO item (name, price_ex_vat, vat_percentage, left_in_stock, description, image_url, category_name) VALUES
      ('Meditations', 15.00, 6.00, 100, 'Marcus Aurelius personal writings', 'http://example.com/med.jpg', 'Books'),
      ('Stoic Keyboard', 79.99, 21.00, 25, 'A keyboard that remains calm even during stack traces', 'http://example.com/stoic-keyboard.jpg', 'Accessories'),
      ('Debugging Incense', 8.50, 21.00, 80, 'Incense believed to reveal hidden NullPointerExceptions', 'http://example.com/incense.jpg', 'Wellness'),
      ('Zen Mouse Pad', 12.00, 21.00, 60, 'A mouse pad designed to reduce cursor anxiety', 'http://example.com/zen-pad.jpg', 'Accessories'),
      ('Karma Cleaner Pro', 49.99, 21.00, 15, 'Software that cleans temporary files and bad vibes', 'http://example.com/karma-cleaner.jpg', 'Software'),
      ('Minimalist Cable Organizer', 6.99, 21.00, 120, 'A cable organizer for people who fear chaos', 'http://example.com/cable-organizer.jpg', 'Accessories');

-- referent
INSERT INTO referent (designation, first_name, last_name, birth_date, is_alive, website, nickname, school_of_thought_id) VALUES
      ('Philosopher', 'Marcus', 'Aurelius', '0121-04-26', FALSE, NULL, 'The Wise', 1),
      ('Guru', 'Jean', 'Relax', '1985-07-12', TRUE, 'https://example.com/jean-relax', 'Captain Chill', 2),
      ('Tech Mystic', 'Ada', 'Lovecode', '1990-03-05', TRUE, 'https://example.com/ada-lovecode', 'The Compiler Whisperer', 3),
      ('Minimalist', 'Marie', 'KondoJS', '1988-11-20', TRUE, NULL, 'Queen of Clean Code', 4);

-- order_table
INSERT INTO order_table (creation_time, employee_id, client_id) VALUES
       (NOW(), 1, 1),
       (NOW(), 2, 2),
       (NOW(), 3, 3),
       (NOW(), 1, 4);

-- order_line
INSERT INTO order_line (order_id, item_id, quantity, price_at_the_time) VALUES
       (1, 1, 2, 15.00),
       (1, 3, 4, 8.50),
       (2, 2, 1, 79.99),
       (2, 4, 2, 12.00),
       (3, 5, 1, 49.99),
       (3, 6, 3, 6.99),
       (4, 1, 1, 15.00),
    (4, 5, 2, 49.99);

-- item_referent
INSERT INTO item_referent (item_id, referent_id) VALUES
        (1, 1),
        (2, 1),
        (3, 2),
        (4, 2),
        (5, 3),
        (6, 4),
        (5, 4),
        (3, 3);