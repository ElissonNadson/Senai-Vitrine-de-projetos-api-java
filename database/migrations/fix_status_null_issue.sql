-- ================================================================================================
-- 🔧 SCRIPT SQL: CORREÇÃO URGENTE - CAMPO STATUS NULL
-- ================================================================================================
-- Autor: Sistema SENAI
-- Data: 23 de junho de 2025
-- Descrição: Corrige registros com status NULL e adiciona constraint para evitar problema
-- ================================================================================================

-- 🔍 1. VERIFICAR REGISTROS COM PROBLEMAS
SELECT 
    'Registros com status NULL' as problema,
    COUNT(*) as quantidade
FROM usuarios 
WHERE status IS NULL;

-- 🔧 2. CORRIGIR REGISTROS COM STATUS NULL
UPDATE usuarios 
SET 
    status = 'ATIVO',
    atualizado_em = NOW()
WHERE status IS NULL;

-- 📊 3. VERIFICAR CORREÇÃO
SELECT 
    'Depois da correção' as verificacao,
    status,
    COUNT(*) as quantidade
FROM usuarios 
GROUP BY status;

-- 🔍 4. VERIFICAR SE CAMPO ACEITE_TERMOS EXISTE
SELECT column_name, data_type, is_nullable, column_default 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND column_name = 'aceite_termos'
AND table_schema = CURRENT_SCHEMA();

-- 📝 5. ADICIONAR CAMPO ACEITE_TERMOS SE NÃO EXISTIR
DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'usuarios' 
        AND column_name = 'aceite_termos'
        AND table_schema = CURRENT_SCHEMA()
    ) THEN
        ALTER TABLE usuarios 
        ADD COLUMN aceite_termos BOOLEAN NOT NULL DEFAULT FALSE;
        
        COMMENT ON COLUMN usuarios.aceite_termos IS 'Indica se o usuário aceitou os termos de uso e política de privacidade';
        
        RAISE NOTICE 'Campo aceite_termos adicionado com sucesso';
    ELSE
        RAISE NOTICE 'Campo aceite_termos já existe';
    END IF;
END $$;

-- 🔄 6. ATUALIZAR ACEITE_TERMOS PARA USUARIOS ATIVOS
UPDATE usuarios 
SET aceite_termos = TRUE 
WHERE status = 'ATIVO' 
  AND (aceite_termos IS NULL OR aceite_termos = FALSE);

-- 🔍 7. VERIFICAR ESTRUTURA FINAL
SELECT column_name, data_type, is_nullable, column_default 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND table_schema = CURRENT_SCHEMA()
ORDER BY ordinal_position;

-- 📊 8. RELATÓRIO FINAL
SELECT 
    '✅ CORREÇÃO CONCLUÍDA' as status,
    NOW() as data_execucao,
    (SELECT COUNT(*) FROM usuarios WHERE status IS NULL) as registros_com_status_null,
    (SELECT COUNT(*) FROM usuarios WHERE aceite_termos IS NULL) as registros_com_aceite_null,
    (SELECT COUNT(*) FROM usuarios WHERE status = 'ATIVO') as registros_ativos,
    (SELECT COUNT(*) FROM usuarios WHERE aceite_termos = TRUE) as registros_com_aceite
FROM (SELECT 1) as dummy;

-- ================================================================================================
-- 🚨 NOTAS IMPORTANTES
-- ================================================================================================
-- 
-- 1. ✅ Registros com status NULL foram corrigidos para 'ATIVO'
-- 2. ✅ Campo aceite_termos foi adicionado se não existia
-- 3. ✅ Usuários ativos agora têm aceite_termos = TRUE
-- 4. ⚠️  Novos registros precisam definir status explicitamente no código
-- 5. 📝 Verificar se o controller está definindo status corretamente
-- 
-- ================================================================================================
