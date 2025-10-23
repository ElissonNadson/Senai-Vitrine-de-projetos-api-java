-- Migration para criar tabela de cursos
-- Arquivo: V001__create_cursos_table.sql

CREATE TABLE IF NOT EXISTS cursos (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao TEXT,
    carga_horaria INTEGER NOT NULL,
    modalidade VARCHAR(50) NOT NULL CHECK (modalidade IN ('PRESENCIAL', 'HIBRIDO', 'EAD')),
    nivel VARCHAR(50) NOT NULL CHECK (nivel IN ('TECNICO', 'SUPERIOR', 'QUALIFICACAO')),
    ativo BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Índices para performance
CREATE INDEX idx_cursos_codigo ON cursos(codigo);
CREATE INDEX idx_cursos_ativo ON cursos(ativo);
CREATE INDEX idx_cursos_nivel ON cursos(nivel);

-- Trigger para atualizar atualizado_em automaticamente
CREATE OR REPLACE FUNCTION update_atualizado_em_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.atualizado_em = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_cursos_atualizado_em BEFORE UPDATE ON cursos 
    FOR EACH ROW EXECUTE FUNCTION update_atualizado_em_column();
