-- 📚 SCRIPT 07: DISCIPLINAS DOS CURSOS
-- Cria disciplinas e relaciona com professores
-- Data: 2025-06-19

BEGIN;

-- Inserir disciplinas relacionadas aos professores
WITH professores_disponiveis AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM professores
)
INSERT INTO disciplina (uuid, nome, descricao, carga_horaria, professor_uuid, criado_em, atualizado_em)
SELECT 
    gen_random_uuid(),
    CASE ROW_NUMBER() OVER (ORDER BY pd.uuid)
        WHEN 1 THEN 'Fundamentos de Programação'
        WHEN 2 THEN 'Eletrônica Digital'
        WHEN 3 THEN 'Configuração de Redes'
        WHEN 4 THEN 'Desenvolvimento Frontend'
        WHEN 5 THEN 'Automação Industrial'
        WHEN 6 THEN 'Normas de Segurança'
        WHEN 7 THEN 'Banco de Dados Relacionais'
        WHEN 8 THEN 'Programação Web Avançada'
    END,
    CASE ROW_NUMBER() OVER (ORDER BY pd.uuid)
        WHEN 1 THEN 'Lógica de programação e algoritmos básicos'
        WHEN 2 THEN 'Circuitos digitais e componentes eletrônicos'
        WHEN 3 THEN 'Instalação e configuração de equipamentos de rede'
        WHEN 4 THEN 'Interface de usuário com HTML, CSS e JavaScript'
        WHEN 5 THEN 'Sistemas automatizados e controle de processos'
        WHEN 6 THEN 'Regulamentações e procedimentos de segurança'
        WHEN 7 THEN 'Modelagem e implementação de bases de dados'
        WHEN 8 THEN 'Frameworks modernos e APIs RESTful'
    END,
    CASE ROW_NUMBER() OVER (ORDER BY pd.uuid)
        WHEN 1 THEN '120h'
        WHEN 2 THEN '100h'
        WHEN 3 THEN '80h'
        WHEN 4 THEN '100h'
        WHEN 5 THEN '90h'
        WHEN 6 THEN '60h'
        WHEN 7 THEN '80h'
        WHEN 8 THEN '110h'
    END,
    pd.uuid,
    NOW(),
    NOW()
FROM professores_disponiveis pd;

-- Verificar quantas disciplinas foram inseridas
SELECT 
    '✅ 07_disciplinas.sql executado com sucesso!' as status,
    COUNT(*) as total_disciplinas,
    'Disciplinas criadas e relacionadas aos professores' as descricao
FROM disciplina;

COMMIT;
