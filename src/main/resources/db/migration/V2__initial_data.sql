-- Inserção dos fabricantes
INSERT INTO manufacturers (name) VALUES 
('Mercedes'),
('Ferrari'),
('Honda'),
('Renault');

-- Inserção das versões de durabilidade por fabricante
INSERT INTO manufacturer_durability_versions (manufacturer_id, component_type, durability) VALUES
-- Mercedes
(1, 'ICE', 5000),
(1, 'TC', 4000),
(1, 'MGU_H', 3000),
(1, 'MGU_K', 3000),
(1, 'ES', 2000),
(1, 'CE', 2000),
-- Ferrari
(2, 'ICE', 4500),
(2, 'TC', 3500),
(2, 'MGU_H', 2500),
(2, 'MGU_K', 2500),
(2, 'ES', 1500),
(2, 'CE', 1500),
-- Honda
(3, 'ICE', 4800),
(3, 'TC', 3800),
(3, 'MGU_H', 2800),
(3, 'MGU_K', 2800),
(3, 'ES', 1800),
(3, 'CE', 1800),
-- Renault
(4, 'ICE', 4600),
(4, 'TC', 3600),
(4, 'MGU_H', 2600),
(4, 'MGU_K', 2600),
(4, 'ES', 1600),
(4, 'CE', 1600);

-- Inserção das unidades de potência
INSERT INTO power_units (car_identifier, total_laps) VALUES
('MERCEDES_1', 0),
('MERCEDES_2', 0),
('FERRARI_1', 0),
('FERRARI_2', 0),
('REDBULL_1', 0),
('REDBULL_2', 0),
('ALPINE_1', 0),
('ALPINE_2', 0);

-- Inserção dos componentes para a primeira unidade de potência da Mercedes
INSERT INTO components (name, type, base_durability, current_durability, last_replacement_date, manufacturer_id, power_unit_id) VALUES
('ICE V1', 'ICE', 5000, 100.0, CURRENT_TIMESTAMP, 1, 1),
('TC V1', 'TC', 4000, 100.0, CURRENT_TIMESTAMP, 1, 1),
('MGU-H V1', 'MGU_H', 3000, 100.0, CURRENT_TIMESTAMP, 1, 1),
('MGU-K V1', 'MGU_K', 3000, 100.0, CURRENT_TIMESTAMP, 1, 1),
('ES V1', 'ES', 2000, 100.0, CURRENT_TIMESTAMP, 1, 1),
('CE V1', 'CE', 2000, 100.0, CURRENT_TIMESTAMP, 1, 1);

-- Inserção dos componentes para a segunda unidade de potência da Mercedes
INSERT INTO components (name, type, base_durability, current_durability, last_replacement_date, manufacturer_id, power_unit_id) VALUES
('ICE V1', 'ICE', 5000, 100.0, CURRENT_TIMESTAMP, 1, 2),
('TC V1', 'TC', 4000, 100.0, CURRENT_TIMESTAMP, 1, 2),
('MGU-H V1', 'MGU_H', 3000, 100.0, CURRENT_TIMESTAMP, 1, 2),
('MGU-K V1', 'MGU_K', 3000, 100.0, CURRENT_TIMESTAMP, 1, 2),
('ES V1', 'ES', 2000, 100.0, CURRENT_TIMESTAMP, 1, 2),
('CE V1', 'CE', 2000, 100.0, CURRENT_TIMESTAMP, 1, 2); 