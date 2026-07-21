
-- =====================================================
-- Tabela de Usuários
-- Bounded Context: Identity and Access
-- Propósito: Armazenar dados de autenticação
-- =====================================================
CREATE TABLE users (
    -- UUID gerado automaticamente pelo PostgreSQL
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    -- Email único (constraint UNIQUE garante)
    email VARCHAR(255) NOT NULL UNIQUE,

    -- Senha criptografada (nunca armazenar em texto plano)
    password VARCHAR(255) NOT NULL,

    -- Timestamps gerenciados pelo Hibernate
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,

    -- Soft delete: NULL = ativo, NOT NULL = deletado
    deleted_at TIMESTAMP
);