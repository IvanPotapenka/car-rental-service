
INSERT INTO car
(color, quantity_doors, numbers, quantity_places, trunk_volume, vin_code, brand, engine_capacity,
 fuel_consumption, fuel, horse_power, transmission, model, price, year_of_release)
VALUES ('WHITE', 5, '1092873', 5, 587, '123-0948098j802d', 'Audy', 1.5, 6.0,  'DIESEL', 125, 'AUTOMATIC',
        'A8', 102, 2020);

INSERT INTO car
(color, quantity_doors, numbers, quantity_places, trunk_volume, vin_code, brand, engine_capacity,
 fuel_consumption, fuel, horse_power, transmission, model, price, year_of_release)
VALUES ('BLACK', 5, '1092871', 5, 587, '113-0948098j865d', 'BMW', 1.5, 6.0, 'DIESEL', 125, 'AUTOMATIC',
        'X6', 102, 2023);

INSERT INTO car
(color, quantity_doors, numbers, quantity_places, trunk_volume, vin_code, brand, engine_capacity,
 fuel_consumption, fuel, horse_power, transmission, model, price, year_of_release)
VALUES ('GREEN', 5, '1092872', 5, 587, '133-0948095j802d', 'Mercedes', 1.5, 6.0, 'DIESEL', 125, 'AUTOMATIC',
        'F1', 102, 2021);

INSERT INTO client (first_name, last_name, phone, address, date_of_birthday,
                    date_of_creation)
VALUES ('Ivan', 'Potapenko', '+375447399840', 'Gomel,Mazurova,21', '1986-05-04',
        '2023-05-04');
INSERT INTO client (first_name, last_name, phone, address, date_of_birthday,
                    date_of_creation)
VALUES ('Anna', 'Potapenkov', '+375442899840', 'Gomel,Mazurova,23', '1986-05-03',
        '2023-05-03');
INSERT INTO client (first_name, last_name, phone, address, date_of_birthday,
                    date_of_creation)
VALUES ('Egor', 'Potap', '+375447899340', 'Gomel,Mazurova,25', '1986-05-01',
        '2023-05-01');
INSERT INTO rental (car_id, client_id, rental_date, return_date, price, rental_days, status, date_of_creation)
VALUES (1, 1, '2023-05-21', '2023-05-24', 105, 3, 'REJECTED', '2023-05-21');

INSERT INTO rental (car_id, client_id, rental_date, return_date, price, rental_days, status, date_of_creation)
VALUES (2, 1, '2023-05-22', '2023-05-25', 123, 3, 'CHECK', '2023-05-22');

INSERT INTO rental (car_id, client_id, rental_date, return_date, price, rental_days, status, date_of_creation)
VALUES (3, 1, '2023-05-23', '2023-05-26', 133, 3, 'ALLOWED', '2023-05-23');

INSERT INTO car_client (car_id, client_id)
VALUES (1, 1);