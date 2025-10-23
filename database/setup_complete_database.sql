-- Script completo para executar todas as migrações e seeds
-- Arquivo: setup_complete_database.sql

-- Execute este arquivo para configurar completamente o banco de dados
-- com cursos, turmas e unidades curriculares

-- ========================================
-- 1. CRIAR TABELAS (MIGRATIONS)
-- ========================================

-- 1.1 Criar tabela de cursos
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

-- 1.2 Criar função para atualizar timestamp
CREATE OR REPLACE FUNCTION update_atualizado_em_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.atualizado_em = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 1.3 Criar trigger para cursos
CREATE TRIGGER update_cursos_atualizado_em BEFORE UPDATE ON cursos 
    FOR EACH ROW EXECUTE FUNCTION update_atualizado_em_column();

-- 1.4 Criar índices para cursos
CREATE INDEX IF NOT EXISTS idx_cursos_codigo ON cursos(codigo);
CREATE INDEX IF NOT EXISTS idx_cursos_ativo ON cursos(ativo);
CREATE INDEX IF NOT EXISTS idx_cursos_nivel ON cursos(nivel);

-- 1.5 Criar tabela de turmas
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
    
    CONSTRAINT chk_vagas_ocupadas CHECK (vagas_ocupadas <= vagas),
    CONSTRAINT chk_datas CHECK (data_fim IS NULL OR data_inicio IS NULL OR data_fim > data_inicio)
);

-- 1.6 Criar trigger para turmas
CREATE TRIGGER update_turmas_atualizado_em BEFORE UPDATE ON turmas 
    FOR EACH ROW EXECUTE FUNCTION update_atualizado_em_column();

-- 1.7 Criar índices para turmas
CREATE INDEX IF NOT EXISTS idx_turmas_codigo ON turmas(codigo);
CREATE INDEX IF NOT EXISTS idx_turmas_curso_uuid ON turmas(curso_uuid);
CREATE INDEX IF NOT EXISTS idx_turmas_ativa ON turmas(ativa);
CREATE INDEX IF NOT EXISTS idx_turmas_ano ON turmas(ano);
CREATE INDEX IF NOT EXISTS idx_turmas_periodo ON turmas(periodo);

-- ========================================
-- 2. INSERIR DADOS (SEEDS)
-- ========================================

-- 2.1 Inserir cursos
INSERT INTO cursos (nome, codigo, descricao, carga_horaria, modalidade, nivel, ativo) VALUES 
('Técnico em Desenvolvimento de Sistemas', 'TDS', 'Curso técnico focado no desenvolvimento de aplicações web, mobile e desktop utilizando tecnologias modernas.', 1200, 'PRESENCIAL', 'TECNICO', TRUE),
('Técnico em Automação Industrial', 'TAI', 'Curso técnico voltado para automação de processos industriais, programação de CLPs e sistemas SCADA.', 1200, 'PRESENCIAL', 'TECNICO', TRUE),
('Técnico em Mecânica', 'MEC', 'Curso técnico em mecânica industrial com foco em manutenção, usinagem e projetos mecânicos.', 1200, 'PRESENCIAL', 'TECNICO', TRUE),
('Técnico em Eletrônica', 'ELE', 'Curso técnico em eletrônica abrangendo circuitos analógicos, digitais e microcontroladores.', 1200, 'PRESENCIAL', 'TECNICO', TRUE),
('Técnico em Redes de Computadores', 'RDC', 'Curso técnico especializado em infraestrutura de TI, redes e segurança da informação.', 1200, 'PRESENCIAL', 'TECNICO', TRUE)
ON CONFLICT (codigo) DO NOTHING;

-- 2.2 Inserir turmas para cada curso
-- TDS
INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'TDS 2024.1 - Manhã', 'TDS-2024-1-M', c.uuid, 2024, 'MANHA', 35, 32, '2024-02-05', '2025-12-20', TRUE
FROM cursos c WHERE c.codigo = 'TDS'
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'TDS 2025.1 - Manhã', 'TDS-2025-1-M', c.uuid, 2025, 'MANHA', 35, 15, '2025-02-03', '2026-12-18', TRUE
FROM cursos c WHERE c.codigo = 'TDS'
ON CONFLICT (codigo) DO NOTHING;

-- TAI
INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'TAI 2024.1 - Tarde', 'TAI-2024-1-T', c.uuid, 2024, 'TARDE', 30, 25, '2024-02-05', '2025-12-20', TRUE
FROM cursos c WHERE c.codigo = 'TAI'
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'TAI 2025.1 - Tarde', 'TAI-2025-1-T', c.uuid, 2025, 'TARDE', 30, 12, '2025-02-03', '2026-12-18', TRUE
FROM cursos c WHERE c.codigo = 'TAI'
ON CONFLICT (codigo) DO NOTHING;

-- MEC
INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'MEC 2024.1 - Manhã', 'MEC-2024-1-M', c.uuid, 2024, 'MANHA', 30, 27, '2024-02-05', '2025-12-20', TRUE
FROM cursos c WHERE c.codigo = 'MEC'
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'MEC 2025.1 - Manhã', 'MEC-2025-1-M', c.uuid, 2025, 'MANHA', 30, 18, '2025-02-03', '2026-12-18', TRUE
FROM cursos c WHERE c.codigo = 'MEC'
ON CONFLICT (codigo) DO NOTHING;

-- ELE
INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'ELE 2024.1 - Tarde', 'ELE-2024-1-T', c.uuid, 2024, 'TARDE', 25, 23, '2024-02-05', '2025-12-20', TRUE
FROM cursos c WHERE c.codigo = 'ELE'
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'ELE 2025.1 - Tarde', 'ELE-2025-1-T', c.uuid, 2025, 'TARDE', 25, 10, '2025-02-03', '2026-12-18', TRUE
FROM cursos c WHERE c.codigo = 'ELE'
ON CONFLICT (codigo) DO NOTHING;

-- RDC
INSERT INTO turmas (nome, codigo, curso_uuid, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 'RDC 2025.1 - Noite', 'RDC-2025-1-N', c.uuid, 2025, 'NOITE', 30, 14, '2025-02-03', '2026-12-18', TRUE
FROM cursos c WHERE c.codigo = 'RDC'
ON CONFLICT (codigo) DO NOTHING;

-- ========================================
-- 3. CONSULTAS DE VERIFICAÇÃO
-- ========================================

-- 3.1 Verificar cursos inseridos
SELECT 'CURSOS INSERIDOS:' as info;
SELECT codigo, nome, carga_horaria, modalidade, ativo FROM cursos ORDER BY codigo;

-- 3.2 Verificar turmas inseridas
SELECT 'TURMAS INSERIDAS:' as info;
SELECT 
    t.codigo as turma_codigo,
    t.nome as turma_nome,
    c.codigo as curso_codigo,
    t.periodo,
    t.vagas,
    t.vagas_ocupadas,
    t.ativa
FROM turmas t 
JOIN cursos c ON t.curso_uuid = c.uuid 
ORDER BY c.codigo, t.codigo;

-- 3.3 Verificar relacionamentos
SELECT 'RESUMO POR CURSO:' as info;
SELECT 
    c.codigo as curso,
    c.nome,
    COUNT(t.uuid) as total_turmas,
    SUM(t.vagas) as total_vagas,
    SUM(t.vagas_ocupadas) as total_ocupadas,
    ROUND((SUM(t.vagas_ocupadas::decimal) / SUM(t.vagas::decimal)) * 100, 2) as percentual_ocupacao
FROM cursos c
LEFT JOIN turmas t ON c.uuid = t.curso_uuid
WHERE c.ativo = TRUE
GROUP BY c.uuid, c.codigo, c.nome
ORDER BY c.codigo;
