-- 👨‍🏫 SCRIPT 04: PERFIS DOS PROFESSORES
-- Cria perfis específicos dos professores com especialidades
-- Data: 2025-06-19

BEGIN;

-- Criar perfis dos professores com endereços e especialidades
WITH prof_usuarios AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM usuarios 
    WHERE tipo = 'PROFESSOR' 
    AND email LIKE '%@senai.br'
    LIMIT 6
),
enderecos_prof AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM endereco LIMIT 6
)
INSERT INTO professores (
    uuid, usuarios_uuid, endereco_uuid, departamento, especialidade, 
    linkedin, telefone_pessoal, telefone_profissional, status, 
    criado_em, atualizado_em
)
SELECT 
    gen_random_uuid(),
    pu.uuid,
    ep.uuid,
    CASE pu.rn 
        WHEN 1 THEN 'Tecnologia da Informação'
        WHEN 2 THEN 'Eletrônica e Automação'
        WHEN 3 THEN 'Redes e Infraestrutura'
        WHEN 4 THEN 'Desenvolvimento de Software'
        WHEN 5 THEN 'Mecatrônica'
        WHEN 6 THEN 'Segurança do Trabalho'
    END,
    CASE pu.rn 
        WHEN 1 THEN 'Java e Spring Boot'
        WHEN 2 THEN 'Circuitos Eletrônicos'
        WHEN 3 THEN 'Administração de Redes'
        WHEN 4 THEN 'React e JavaScript'
        WHEN 5 THEN 'Robótica Industrial'
        WHEN 6 THEN 'Normas Regulamentadoras'
    END,
    CASE pu.rn 
        WHEN 1 THEN 'linkedin.com/in/carlos-silva-senai'
        WHEN 2 THEN 'linkedin.com/in/maria-santos-eletronica'
        WHEN 3 THEN 'linkedin.com/in/joao-oliveira-redes'
        WHEN 4 THEN 'linkedin.com/in/ana-costa-dev'
        WHEN 5 THEN 'linkedin.com/in/pedro-lima-mecatronica'
        WHEN 6 THEN 'linkedin.com/in/lucia-ferreira-seguranca'
    END,
    CASE pu.rn 
        WHEN 1 THEN '(11) 98765-4321'
        WHEN 2 THEN '(11) 97654-3210'
        WHEN 3 THEN '(11) 96543-2109'
        WHEN 4 THEN '(11) 95432-1098'
        WHEN 5 THEN '(11) 94321-0987'
        WHEN 6 THEN '(11) 93210-9876'
    END,
    CASE pu.rn 
        WHEN 1 THEN '(11) 3456-7890'
        WHEN 2 THEN '(11) 3567-8901'
        WHEN 3 THEN '(11) 3678-9012'
        WHEN 4 THEN '(11) 3789-0123'
        WHEN 5 THEN '(11) 3890-1234'
        WHEN 6 THEN '(11) 3901-2345'
    END,
    'ATIVO',
    NOW(),
    NOW()
FROM prof_usuarios pu
JOIN enderecos_prof ep ON pu.rn = ep.rn;

-- Verificar quantos professores foram criados
SELECT 
    '✅ 04_professores.sql executado com sucesso!' as status,
    COUNT(*) as total_professores,
    'Perfis de professores criados' as descricao
FROM professores;

COMMIT;
