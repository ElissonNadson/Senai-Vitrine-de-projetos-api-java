-- 🏫 SCRIPT 01: CURSOS DO SENAI
-- Cria os cursos técnicos oferecidos pela instituição
-- Data: 2025-06-19

BEGIN;

-- Inserir cursos técnicos do SENAI
INSERT INTO cursos (uuid, nome, descricao, carga_horaria) VALUES 
(gen_random_uuid(), 'Técnico em Desenvolvimento de Sistemas', 'Curso técnico focado em programação e desenvolvimento de software', '1200h'),
(gen_random_uuid(), 'Técnico em Redes de Computadores', 'Formação em infraestrutura e administração de redes', '1000h'),
(gen_random_uuid(), 'Técnico em Eletrônica', 'Curso voltado para eletrônica industrial e automação', '1100h'),
(gen_random_uuid(), 'Técnico em Mecatrônica', 'Integração de mecânica, eletrônica e programação', '1300h'),
(gen_random_uuid(), 'Técnico em Informática para Internet', 'Desenvolvimento web e aplicações online', '800h'),
(gen_random_uuid(), 'Técnico em Segurança do Trabalho', 'Prevenção de acidentes e segurança industrial', '1200h');

-- Verificar quantos cursos foram inseridos
SELECT 
    '✅ 01_cursos.sql executado com sucesso!' as status,
    COUNT(*) as total_cursos,
    'Cursos técnicos criados' as descricao
FROM cursos;

COMMIT;
