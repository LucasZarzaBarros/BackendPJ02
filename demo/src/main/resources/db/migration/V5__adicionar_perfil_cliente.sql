ALTER TABLE cliente
    ADD COLUMN perfil perfil_usuario DEFAULT 'CLIENTE';

UPDATE cliente
SET perfil = 'CLIENTE'
WHERE perfil IS NULL;

ALTER TABLE cliente
    ALTER COLUMN perfil SET NOT NULL;