-- Criação da tabela de fabricantes
CREATE TABLE IF NOT EXISTS manufacturers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela de versões de durabilidade dos fabricantes
CREATE TABLE IF NOT EXISTS manufacturer_durability_versions (
    id BIGSERIAL PRIMARY KEY,
    manufacturer_id BIGINT NOT NULL,
    component_type VARCHAR(10) NOT NULL,
    durability INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)
);

-- Criação da tabela de unidades de potência
CREATE TABLE IF NOT EXISTS power_units (
    id BIGSERIAL PRIMARY KEY,
    car_identifier VARCHAR(255) NOT NULL UNIQUE,
    total_laps INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criação da tabela de componentes
CREATE TABLE IF NOT EXISTS components (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(10) NOT NULL,
    base_durability INTEGER NOT NULL,
    current_durability DOUBLE PRECISION NOT NULL,
    last_replacement_date TIMESTAMP NOT NULL,
    manufacturer_id BIGINT NOT NULL,
    power_unit_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id),
    FOREIGN KEY (power_unit_id) REFERENCES power_units(id)
);

-- Criação da tabela de histórico de trocas
CREATE TABLE IF NOT EXISTS replacement_history (
    id BIGSERIAL PRIMARY KEY,
    component_id BIGINT NOT NULL,
    power_unit_id BIGINT NOT NULL,
    replacement_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (component_id) REFERENCES components(id),
    FOREIGN KEY (power_unit_id) REFERENCES power_units(id)
); 