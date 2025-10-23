-- 🎓 SCRIPT 06: UNIDADES CURRICULARES
-- Cria módulos de ensino e disciplinas base
-- Data: 2025-06-19

BEGIN;

-- Inserir unidades curriculares dos cursos
INSERT INTO unidade_curricular (uuid, nome, descricao, carga_horaria, criado_em, atualizado_em) VALUES 
(gen_random_uuid(), 'Programação Orientada a Objetos', 'Conceitos fundamentais de POO usando Java', '80h', NOW(), NOW()),
(gen_random_uuid(), 'Banco de Dados', 'Modelagem e desenvolvimento de bancos de dados relacionais', '60h', NOW(), NOW()),
(gen_random_uuid(), 'Desenvolvimento Web', 'HTML, CSS, JavaScript e frameworks modernos', '100h', NOW(), NOW()),
(gen_random_uuid(), 'Redes de Computadores', 'Fundamentos de redes e protocolos', '80h', NOW(), NOW()),
(gen_random_uuid(), 'Segurança da Informação', 'Conceitos de cybersecurity e proteção de dados', '60h', NOW(), NOW()),
(gen_random_uuid(), 'Metodologias Ágeis', 'Scrum, Kanban e práticas de desenvolvimento ágil', '40h', NOW(), NOW()),
(gen_random_uuid(), 'Sistemas Embarcados', 'Programação de microcontroladores e IoT', '80h', NOW(), NOW()),
(gen_random_uuid(), 'Inteligência Artificial', 'Fundamentos de IA e Machine Learning', '60h', NOW(), NOW()),
(gen_random_uuid(), 'Eletrônica Digital', 'Circuitos digitais e sistemas combinacionais', '70h', NOW(), NOW()),
(gen_random_uuid(), 'Automação Industrial', 'PLCs e sistemas de controle industrial', '90h', NOW(), NOW());

-- Verificar quantas unidades curriculares foram inseridas
SELECT 
    '✅ 06_unidades_curriculares.sql executado com sucesso!' as status,
    COUNT(*) as total_unidades,
    'Unidades curriculares criadas' as descricao
FROM unidade_curricular;

COMMIT;
