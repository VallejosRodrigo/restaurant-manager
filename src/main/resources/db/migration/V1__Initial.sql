CREATE TABLE tables (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    number INT NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_dni BIGINT NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    table_id BIGINT NOT NULL,
    reservation_date DATE NOT NULL,
    reservation_time TIME NOT NULL,
    state VARCHAR(50) DEFAULT 'PENDING',
    FOREIGN KEY (table_id) REFERENCES tables(id)
);


INSERT INTO tables (number, capacity) VALUES
(1, 4),
(2, 2),
(3, 6),
(4, 8);

INSERT INTO reservations (customer_dni, customer_name, table_id, reservation_date, reservation_time, state) VALUES
(37262721, 'VALLEJOS RODRIGO', 1, '2024-07-01', '18:00:00', 'CONFIRMED'),
(42654789, 'ALEJO CZOMBOS', 2, '2024-07-02', '19:00:00', 'PENDING'),
(40968706, 'HELENA ROMAGIALLI', 3, '2024-07-03', '20:00:00', 'CANCELLED'),
(36478931, 'GONZALO LENTATI', 4, '2024-07-04', '21:00:00', 'CONFIRMED');