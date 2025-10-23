-- 🧹 SCRIPT DE LIMPEZA: REMOVER TODOS OS DADOS
-- ⚠️ CUIDADO: Este script remove TODOS os dados do banco
-- Use apenas em ambiente de desenvolvimento!
-- Data: 2025-06-19

-- ⚠️ CONFIRMAÇÃO DE SEGURANÇA
-- Descomente a linha abaixo APENAS se tiver certeza:
-- SET session_replication_role = replica;

BEGIN;

-- Verificar ambiente antes de continuar
DO $$
BEGIN
    IF current_database() = 'production' OR current_database() LIKE '%prod%' THEN
        RAISE EXCEPTION '❌ OPERAÇÃO CANCELADA: Banco de produção detectado!';
    END IF;
    
    RAISE NOTICE '⚠️ LIMPANDO BANCO: %', current_database();
END $$;

-- Desabilitar verificações de chave estrangeira temporariamente
SET session_replication_role = replica;

-- Limpar dados em ordem reversa (respeitando dependências)
DELETE FROM anexo_etapa;
DELETE FROM anexo;
DELETE FROM projeto_anexos;
DELETE FROM disciplina_projeto;
DELETE FROM projeto_professor;
DELETE FROM projeto_aluno;
DELETE FROM etapas_projeto;
DELETE FROM projeto;
DELETE FROM disciplina;
DELETE FROM unidade_curricular;
DELETE FROM alunos;
DELETE FROM professores;
DELETE FROM usuarios WHERE email LIKE '%@senai.br' OR email LIKE '%@aluno.senai.br';
DELETE FROM endereco;
DELETE FROM cursos;

-- Reabilitar verificações de chave estrangeira
SET session_replication_role = DEFAULT;

-- Resetar sequências (se houver)
-- SELECT setval('tabela_id_seq', 1, false); -- Descomente se necessário

-- Verificar limpeza
SELECT 
    '🧹 LIMPEZA CONCLUÍDA!' as status,
    '✅ Todos os dados de exemplo foram removidos' as resultado,
    NOW() as executado_em;

-- Estatísticas pós-limpeza
SELECT 
    'CONTAGEM PÓS-LIMPEZA' as categoria,
    (SELECT COUNT(*) FROM cursos) as cursos,
    (SELECT COUNT(*) FROM usuarios) as usuarios,
    (SELECT COUNT(*) FROM professores) as professores,
    (SELECT COUNT(*) FROM alunos) as alunos,
    (SELECT COUNT(*) FROM projeto) as projetos,
    (SELECT COUNT(*) FROM etapas_projeto) as etapas;

COMMIT;

-- Mensagem final
SELECT 
    '✅ Banco limpo com sucesso!' as resultado,
    'Agora você pode executar run_all_seeds.sql para recriar os dados' as proxima_acao;

-- 📝 NOTAS:
-- 1. Este script preserva a estrutura do banco (tabelas, colunas, constraints)
-- 2. Remove apenas os dados inseridos pelos seeds
-- 3. Não afeta outros dados que possam existir
-- 4. É seguro executar múltiplas vezes
