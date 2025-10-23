-- Seed para inserir turmas iniciais
-- Arquivo: seed_turmas.sql

-- Inserir turmas para cada curso
-- Primeiro, vamos buscar os UUIDs dos cursos para referenciar corretamente

-- Turmas para Técnico em Desenvolvimento de Sistemas (TDS)
INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TDS 2024.1 - Manhã',
    'TDS-2024-1-M',
    c.uuid,
    '1',
    2024,
    'MANHA',
    35,
    32,
    '2024-02-05'::DATE,
    '2025-12-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TDS';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TDS 2024.2 - Noite',
    'TDS-2024-2-N',
    c.uuid,
    '2',
    2024,
    'NOITE',
    35,
    28,
    '2024-08-05'::DATE,
    '2026-06-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TDS';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TDS 2025.1 - Manhã',
    'TDS-2025-1-M',
    c.uuid,
    '1',
    2025,
    'MANHA',
    35,
    15,
    '2025-02-03'::DATE,
    '2026-12-18'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TDS';

-- Turmas para Técnico em Automação Industrial (TAI)
INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TAI 2024.1 - Tarde',
    'TAI-2024-1-T',
    c.uuid,
    '1',
    2024,
    'TARDE',
    30,
    25,
    '2024-02-05'::DATE,
    '2025-12-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TAI';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TAI 2024.2 - Noite',
    'TAI-2024-2-N',
    c.uuid,
    '2',
    2024,
    'NOITE',
    30,
    22,
    '2024-08-05'::DATE,
    '2026-06-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TAI';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'TAI 2025.1 - Tarde',
    'TAI-2025-1-T',
    c.uuid,
    '1',
    2025,
    'TARDE',
    30,
    12,
    '2025-02-03'::DATE,
    '2026-12-18'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'TAI';

-- Turmas para Técnico em Mecânica (MEC)
INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'MEC 2024.1 - Manhã',
    'MEC-2024-1-M',
    c.uuid,
    '1',
    2024,
    'MANHA',
    30,
    27,
    '2024-02-05'::DATE,
    '2025-12-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'MEC';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'MEC 2024.2 - Tarde',
    'MEC-2024-2-T',
    c.uuid,
    '2',
    2024,
    'TARDE',
    30,
    20,
    '2024-08-05'::DATE,
    '2026-06-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'MEC';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'MEC 2025.1 - Manhã',
    'MEC-2025-1-M',
    c.uuid,
    '1',
    2025,
    'MANHA',
    30,
    18,
    '2025-02-03'::DATE,
    '2026-12-18'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'MEC';

-- Turmas para Técnico em Eletrônica (ELE)
INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'ELE 2024.1 - Tarde',
    'ELE-2024-1-T',
    c.uuid,
    '1',
    2024,
    'TARDE',
    25,
    23,
    '2024-02-05'::DATE,
    '2025-12-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'ELE';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'ELE 2024.2 - Noite',
    'ELE-2024-2-N',
    c.uuid,
    '2',
    2024,
    'NOITE',
    25,
    19,
    '2024-08-05'::DATE,
    '2026-06-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'ELE';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'ELE 2025.1 - Tarde',
    'ELE-2025-1-T',
    c.uuid,
    '1',
    2025,
    'TARDE',
    25,
    10,
    '2025-02-03'::DATE,
    '2026-12-18'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'ELE';

-- Turmas para Técnico em Redes de Computadores (RDC)
INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'RDC 2024.2 - Noite',
    'RDC-2024-2-N',
    c.uuid,
    '2',
    2024,
    'NOITE',
    30,
    26,
    '2024-08-05'::DATE,
    '2026-06-20'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'RDC';

INSERT INTO turmas (nome, codigo, curso_uuid, semestre, ano, periodo, vagas, vagas_ocupadas, data_inicio, data_fim, ativa)
SELECT 
    'RDC 2025.1 - Noite',
    'RDC-2025-1-N',
    c.uuid,
    '1',
    2025,
    'NOITE',
    30,
    14,
    '2025-02-03'::DATE,
    '2026-12-18'::DATE,
    TRUE
FROM cursos c WHERE c.codigo = 'RDC';

-- Verificar se os dados foram inseridos
-- SELECT t.nome, t.codigo, c.codigo as curso_codigo, t.vagas, t.vagas_ocupadas 
-- FROM turmas t 
-- JOIN cursos c ON t.curso_uuid = c.uuid 
-- ORDER BY c.codigo, t.codigo;
