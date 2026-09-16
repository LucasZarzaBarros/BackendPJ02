CREATE EXTENSION IF NOT EXISTS "pg_trgm";
CREATE EXTENSION IF NOT EXISTS "unaccent";
SET TIMEZONE TO 'America/Sao_Paulo';

CREATE TYPE perfil_usuario AS ENUM (
    'CLIENTE',       -- Compra e navega pelo site
    'ATENDENTE',     -- Visualiza os pedidos recebidos via WhatsApp
    'ADMIN',          -- Gerencia produtos, estoque e preços
    'DESATIVADO'
);