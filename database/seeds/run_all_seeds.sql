-- 🚀 SCRIPT MASTER: EXECUTAR TODOS OS SEEDS
-- Executa todos os scripts de população do banco em ordem
-- Data: 2025-06-19

-- ⚠️ IMPORTANTE: Este script irá popular o banco com dados de exemplo
-- 📝 Certifique-se de estar no ambiente de desenvolvimento

BEGIN;

-- Header de execução
SELECT 
    '🎯 INICIANDO POPULAÇÃO DO BANCO SENAI' as inicio,
    NOW() as data_execucao,
    current_database() as banco_atual;

-- Executar scripts em ordem
\echo '🏫 01. Criando cursos...'
\i 01_cursos.sql

\echo '🏠 02. Criando endereços...'
\i 02_enderecos.sql

\echo '👥 03. Criando usuários...'
\i 03_usuarios.sql

\echo '👨‍🏫 04. Criando professores...'
\i 04_professores.sql

\echo '👨‍🎓 05. Criando alunos...'
\i 05_alunos.sql

\echo '🎓 06. Criando unidades curriculares...'
\i 06_unidades_curriculares.sql

\echo '📚 07. Criando disciplinas...'
\i 07_disciplinas.sql

\echo '🚀 08. Criando projetos...'
\i 08_projetos.sql

\echo '📋 09. Criando etapas dos projetos...'
\i 09_etapas_projetos.sql

\echo '👥 10. Criando relacionamentos...'
\i 10_relacionamentos.sql

\echo '📎 11. Criando anexos...'
\i 11_anexos.sql

-- Resumo final
SELECT 
    '🎉 POPULAÇÃO DO BANCO CONCLUÍDA!' as status,
    '✅ Todos os dados foram inseridos com sucesso' as resultado,
    NOW() as finalizado_em;

-- Estatísticas finais
SELECT 
    'RESUMO FINAL' as categoria,
    'Dados criados' as tipo,
    COUNT(*) as quantidade
FROM (
    SELECT 'Cursos' as item FROM cursos
    UNION ALL SELECT 'Usuários' FROM usuarios WHERE email LIKE '%senai%'
    UNION ALL SELECT 'Professores' FROM professores
    UNION ALL SELECT 'Alunos' FROM alunos
    UNION ALL SELECT 'Endereços' FROM endereco
    UNION ALL SELECT 'Disciplinas' FROM disciplina
    UNION ALL SELECT 'Unidades Curriculares' FROM unidade_curricular
    UNION ALL SELECT 'Projetos' FROM projeto
    UNION ALL SELECT 'Etapas' FROM etapas_projeto
    UNION ALL SELECT 'Colaborações' FROM projeto_aluno
    UNION ALL SELECT 'Orientações' FROM projeto_professor
    UNION ALL SELECT 'Anexos' FROM anexo_etapa
) dados

UNION ALL

SELECT 
    'CREDENCIAIS DE ACESSO' as categoria,
    'Email/Senha para testes' as tipo,
    0 as quantidade

UNION ALL

SELECT 
    'Professor' as categoria,
    'carlos.silva@senai.br / senai123' as tipo,
    0 as quantidade

UNION ALL

SELECT 
    'Aluno' as categoria,
    'lucas.pereira@aluno.senai.br / senai123' as tipo,
    0 as quantidade;

COMMIT;
