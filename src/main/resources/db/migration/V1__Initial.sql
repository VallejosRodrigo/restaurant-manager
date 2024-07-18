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
(4, 8),
(5, 5),
(6, 3),
(7, 7),
(8, 10),
(9, 1);

INSERT INTO reservations (customer_dni, customer_name, table_id, reservation_date, reservation_time, state) VALUES
(37262721, 'VALLEJOS RODRIGO', 4, '2024-07-04', '22:30:00', 'CONFIRMED'),
(42654789, 'ALEJO CZOMBOS', 3, '2024-07-11', '19:15:00', 'PENDING'),
(26548917, 'PERDO EL ESCAMOSO', 1, '2024-07-02', '21:00:00', 'CONFIRMED'),
(40968706, 'HELENA ROMAGIALLI', 7, '2024-07-01', '20:00:00', 'CANCELLED'),
(32258694, 'SHAKIRA GOMEZ', 3, '2024-07-05', '12:40:00', 'PENDING');
