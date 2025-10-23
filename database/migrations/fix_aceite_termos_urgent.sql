-- ================================================================================================
-- 🚨 CORREÇÃO URGENTE: PREPARAR BANCO PARA CAMPO ACEITE_TERMOS
-- ================================================================================================
-- PROBLEMA: Hibernate falha ao adicionar aceite_termos NOT NULL porque existem valores NULL
-- SOLUÇÃO: Corrigir registros existentes ANTES de reiniciar o servidor
-- ================================================================================================

-- 🔍 1. VERIFICAR ESTADO ATUAL DO BANCO
SELECT 
    'Estado atual da tabela usuarios' as verificacao,
    COUNT(*) as total_usuarios,
    COUNT(CASE WHEN status IS NULL THEN 1 END) as status_null,
    COUNT(CASE WHEN aceite_termos IS NULL THEN 1 END) as aceite_null
FROM usuarios;

-- 🔧 2. CORRIGIR REGISTROS COM STATUS NULL
UPDATE usuarios 
SET 
    status = 'ATIVO',
    atualizado_em = NOW()
WHERE status IS NULL;

-- 🔧 3. VERIFICAR SE COLUNA ACEITE_TERMOS JÁ EXISTE
SELECT column_name 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND column_name = 'aceite_termos'
AND table_schema = CURRENT_SCHEMA();

-- 🔧 4. ADICIONAR COLUNA ACEITE_TERMOS SE NÃO EXISTIR (COM VALOR PADRÃO)
DO $$
BEGIN
    -- Verifica se a coluna não existe
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.columns 
        WHERE table_name = 'usuarios' 
        AND column_name = 'aceite_termos'
        AND table_schema = CURRENT_SCHEMA()
    ) THEN
        -- Adiciona coluna com valor padrão FALSE e NULLABLE primeiro
        ALTER TABLE usuarios 
        ADD COLUMN aceite_termos BOOLEAN DEFAULT FALSE;
        
        RAISE NOTICE 'Coluna aceite_termos adicionada (nullable)';
        
        -- Atualizar registros existentes para TRUE (usuários ativos)
        UPDATE usuarios 
        SET aceite_termos = TRUE 
        WHERE status = 'ATIVO';
        
        -- Atualizar registros inativos para FALSE
        UPDATE usuarios 
        SET aceite_termos = FALSE 
        WHERE status != 'ATIVO' OR status IS NULL;
        
        -- Agora tornar a coluna NOT NULL
        ALTER TABLE usuarios 
        ALTER COLUMN aceite_termos SET NOT NULL;
        
        RAISE NOTICE 'Coluna aceite_termos agora é NOT NULL';
    ELSE
        RAISE NOTICE 'Coluna aceite_termos já existe';
        
        -- Se existe mas tem valores NULL, corrigir
        UPDATE usuarios 
        SET aceite_termos = CASE 
            WHEN status = 'ATIVO' THEN TRUE
            ELSE FALSE
        END
        WHERE aceite_termos IS NULL;
        
        RAISE NOTICE 'Valores NULL corrigidos';
    END IF;
END $$;

-- 📊 5. VERIFICAÇÃO FINAL
SELECT 
    '✅ VERIFICAÇÃO FINAL' as status,
    COUNT(*) as total_usuarios,
    COUNT(CASE WHEN status IS NULL THEN 1 END) as status_null,
    COUNT(CASE WHEN aceite_termos IS NULL THEN 1 END) as aceite_null,
    COUNT(CASE WHEN status = 'ATIVO' THEN 1 END) as usuarios_ativos,
    COUNT(CASE WHEN aceite_termos = TRUE THEN 1 END) as usuarios_com_aceite
FROM usuarios;

-- 🔍 6. MOSTRAR ESTRUTURA FINAL DA TABELA
SELECT 
    column_name, 
    data_type, 
    is_nullable, 
    column_default 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND table_schema = CURRENT_SCHEMA()
ORDER BY ordinal_position;

-- 📋 7. EXIBIR ALGUNS REGISTROS PARA CONFIRMAÇÃO
SELECT 
    uuid,
    usuario,
    email,
    tipo,
    status,
    aceite_termos,
    criado_em
FROM usuarios 
ORDER BY criado_em DESC 
LIMIT 5;

-- ================================================================================================
-- ✅ BANCO CORRIGIDO - PODE REINICIAR O SERVIDOR AGORA
-- ================================================================================================
