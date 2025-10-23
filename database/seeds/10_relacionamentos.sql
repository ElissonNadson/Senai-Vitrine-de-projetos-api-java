-- 👥 SCRIPT 10: RELACIONAMENTOS ENTRE ENTIDADES
-- Cria relacionamentos projeto-aluno e projeto-professor
-- Data: 2025-06-19

BEGIN;

-- Relacionamentos Projeto-Aluno (colaboradores)
WITH projetos_criados AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM projeto
),
alunos_colaboradores AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM alunos
)
INSERT INTO projeto_aluno (uuid, projeto_uuid, aluno_uuid)
SELECT 
    gen_random_uuid(),
    pc.uuid,
    ac.uuid
FROM projetos_criados pc
JOIN alunos_colaboradores ac ON ac.rn != pc.rn AND ac.rn <= pc.rn + 2
WHERE pc.rn <= 8;  -- Limite aos 8 projetos criados

-- Relacionamentos Projeto-Professor (orientadores)
WITH projetos_criados AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM projeto
),
professores_orientadores AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM professores
)
INSERT INTO projeto_professor (uuid, projeto_uuid, professor_uuid, is_orientador)
SELECT 
    gen_random_uuid(),
    pc.uuid,
    po.uuid,
    CASE WHEN po.rn = ((pc.rn - 1) % 6) + 1 THEN true ELSE false END
FROM projetos_criados pc
JOIN professores_orientadores po ON po.rn <= 6  -- Todos os professores podem orientar
WHERE pc.rn <= 8 AND po.rn <= 3;  -- Máximo 3 professores por projeto

-- Relacionamentos Disciplina-Projeto
WITH projetos_criados AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM projeto
),
disciplinas_projetos AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM disciplina LIMIT 8
)
INSERT INTO disciplina_projeto (uuid, projeto_uuid, disciplina_uuid)
SELECT 
    gen_random_uuid(),
    pc.uuid,
    dp.uuid
FROM projetos_criados pc
JOIN disciplinas_projetos dp ON pc.rn = dp.rn
WHERE pc.rn <= 8;

-- Verificar relacionamentos criados
SELECT 
    '✅ 10_relacionamentos.sql executado com sucesso!' as status,
    (SELECT COUNT(*) FROM projeto_aluno) as colaboracoes_aluno,
    (SELECT COUNT(*) FROM projeto_professor) as orientacoes_professor,
    (SELECT COUNT(*) FROM disciplina_projeto) as disciplina_projeto
;

COMMIT;
