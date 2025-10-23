-- Seed para inserir cursos iniciais
-- Arquivo: seed_cursos.sql

-- Limpar dados existentes (opcional - remover se não quiser limpar)
-- TRUNCATE TABLE turmas CASCADE;
-- TRUNCATE TABLE cursos CASCADE;

-- Inserir cursos técnicos do SENAI
INSERT INTO cursos (uuid, nome, codigo, descricao, carga_horaria, modalidade, nivel, ativo) VALUES 
(
    gen_random_uuid(),
    'Técnico em Desenvolvimento de Sistemas',
    'TDS',
    'Curso técnico focado no desenvolvimento de aplicações web, mobile e desktop utilizando tecnologias modernas como Java, Python, JavaScript, React, Angular e banco de dados.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Automação Industrial',
    'TAI',
    'Curso técnico voltado para automação de processos industriais, programação de CLPs, sistemas SCADA, instrumentação e controle de processos.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Mecânica',
    'MEC',
    'Curso técnico em mecânica industrial com foco em manutenção, usinagem, soldagem, metrologia e projetos mecânicos.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Eletrônica',
    'ELE',
    'Curso técnico em eletrônica abrangendo circuitos analógicos e digitais, microcontroladores, sistemas embarcados e manutenção de equipamentos eletrônicos.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Redes de Computadores',
    'RDC',
    'Curso técnico especializado em infraestrutura de TI, configuração de redes, segurança da informação, servidores e telecomunicações.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Logística',
    'LOG',
    'Curso técnico em logística com foco em gestão de supply chain, armazenagem, distribuição, transporte e comércio exterior.',
    800,
    'HIBRIDO',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Segurança do Trabalho',
    'TST',
    'Curso técnico voltado para prevenção de acidentes, segurança industrial, higiene ocupacional e legislação trabalhista.',
    1200,
    'PRESENCIAL',
    'TECNICO',
    TRUE
),
(
    gen_random_uuid(),
    'Técnico em Administração',
    'ADM',
    'Curso técnico em administração empresarial, gestão de pessoas, finanças, marketing e empreendedorismo.',
    800,
    'EAD',
    'TECNICO',
    TRUE
);

-- Verificar se os dados foram inseridos
-- SELECT * FROM cursos ORDER BY codigo;
