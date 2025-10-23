-- Migration para criar tabela de turmas
-- Arquivo: V002__create_turmas_table.sql

CREATE TABLE IF NOT EXISTS turmas (
    uuid UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255) NOT NULL,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    curso_uuid UUID NOT NULL REFERENCES cursos(uuid) ON DELETE CASCADE,
    semestre VARCHAR(10),
    ano INTEGER NOT NULL CHECK (ano >= 2020),
    periodo VARCHAR(20) NOT NULL CHECK (periodo IN ('MANHA', 'TARDE', 'NOITE', 'INTEGRAL')),
    vagas INTEGER NOT NULL CHECK (vagas > 0),
    vagas_ocupadas INTEGER DEFAULT 0 CHECK (vagas_ocupadas >= 0),
    data_inicio DATE,
    data_fim DATE,
    ativa BOOLEAN DEFAULT TRUE,
    criado_em TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    
    -- Constraint para garantir que vagas_ocupadas não seja maior que vagas
    CONSTRAINT chk_vagas_ocupadas CHECK (vagas_ocupadas <= vagas),
    
    -- Constraint para garantir que data_fim seja maior que data_inicio
    CONSTRAINT chk_datas CHECK (data_fim IS NULL OR data_inicio IS NULL OR data_fim > data_inicio)
);

-- Índices para performance
CREATE INDEX idx_turmas_codigo ON turmas(codigo);
CREATE INDEX idx_turmas_curso_uuid ON turmas(curso_uuid);
CREATE INDEX idx_turmas_ativa ON turmas(ativa);
CREATE INDEX idx_turmas_ano ON turmas(ano);
CREATE INDEX idx_turmas_periodo ON turmas(periodo);

-- Trigger para atualizar atualizado_em automaticamente
CREATE TRIGGER update_turmas_atualizado_em BEFORE UPDATE ON turmas 
    FOR EACH ROW EXECUTE FUNCTION update_atualizado_em_column();
