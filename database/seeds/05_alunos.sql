-- 👨‍🎓 SCRIPT 05: PERFIS DOS ALUNOS
-- Cria perfis específicos dos alunos com cursos e matrículas
-- Data: 2025-06-19

BEGIN;

-- Criar perfis dos alunos com cursos e endereços
WITH aluno_usuarios AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM usuarios 
    WHERE tipo = 'ALUNO' 
    AND email LIKE '%@aluno.senai.br'
    LIMIT 12
),
enderecos_aluno AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM endereco 
    WHERE uuid NOT IN (SELECT endereco_uuid FROM professores WHERE endereco_uuid IS NOT NULL)
    LIMIT 12
),
cursos_disponiveis AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM cursos
)
INSERT INTO alunos (
    uuid, usuarios_uuid, endereco_uuid, curso_uuid, curso, matricula, 
    telefone_pessoal, telefone_profissional, linkedin, status, 
    criado_em, atualizado_em
)
SELECT 
    gen_random_uuid(),
    au.uuid,
    CASE WHEN ea.uuid IS NOT NULL THEN ea.uuid ELSE NULL END,
    cd.uuid,
    CASE (au.rn - 1) % 6 + 1
        WHEN 1 THEN 'Técnico em Desenvolvimento de Sistemas'
        WHEN 2 THEN 'Técnico em Redes de Computadores'
        WHEN 3 THEN 'Técnico em Eletrônica'
        WHEN 4 THEN 'Técnico em Mecatrônica'
        WHEN 5 THEN 'Técnico em Informática para Internet'
        WHEN 6 THEN 'Técnico em Segurança do Trabalho'
    END,
    '2025' || LPAD((1000 + au.rn)::text, 3, '0'),
    CASE au.rn 
        WHEN 1 THEN '(11) 91234-5678'
        WHEN 2 THEN '(11) 92345-6789'
        WHEN 3 THEN '(11) 93456-7890'
        WHEN 4 THEN '(11) 94567-8901'
        WHEN 5 THEN '(11) 95678-9012'
        WHEN 6 THEN '(11) 96789-0123'
        WHEN 7 THEN '(11) 97890-1234'
        WHEN 8 THEN '(11) 98901-2345'
        WHEN 9 THEN '(11) 99012-3456'
        WHEN 10 THEN '(11) 90123-4567'
        WHEN 11 THEN '(11) 91111-2222'
        WHEN 12 THEN '(11) 92222-3333'
    END,
    '', -- telefone_profissional vazio para alunos
    CASE au.rn 
        WHEN 1 THEN 'linkedin.com/in/lucas-pereira-dev'
        WHEN 2 THEN 'linkedin.com/in/amanda-rodrigues-redes'
        WHEN 3 THEN 'linkedin.com/in/rafael-martins-eletronica'
        WHEN 4 THEN 'linkedin.com/in/isabella-alves-mecatronica'
        WHEN 5 THEN 'linkedin.com/in/gabriel-souza-web'
        WHEN 6 THEN 'linkedin.com/in/julia-araujo-seguranca'
        ELSE ''
    END,
    'ATIVO',
    NOW(),
    NOW()
FROM aluno_usuarios au
LEFT JOIN enderecos_aluno ea ON au.rn = ea.rn
JOIN cursos_disponiveis cd ON ((au.rn - 1) % 6 + 1) = cd.rn;

-- Verificar quantos alunos foram criados
SELECT 
    '✅ 05_alunos.sql executado com sucesso!' as status,
    COUNT(*) as total_alunos,
    'Perfis de alunos criados' as descricao
FROM alunos;

COMMIT;
