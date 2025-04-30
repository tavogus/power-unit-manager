ALTER TABLE replacement_history
ADD COLUMN durability_at_replacement DOUBLE PRECISION,
ADD COLUMN replacement_reason TEXT;