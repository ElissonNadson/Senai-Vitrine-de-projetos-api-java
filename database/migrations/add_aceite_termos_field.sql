-- ================================================================================================
-- 📋 SCRIPT SQL: ATUALIZAÇÃO CAMPO ACEITE_TERMOS
-- ================================================================================================
-- Autor: Sistema SENAI
-- Data: 22 de junho de 2025
-- Descrição: Adiciona campo aceite_termos na tabela usuarios para conformidade legal
-- ================================================================================================

-- 🔍 1. VERIFICAR ESTRUTURA ATUAL DA TABELA
SELECT column_name, data_type, is_nullable, column_default 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND table_schema = CURRENT_SCHEMA()
ORDER BY ordinal_position;

-- 📝 2. ADICIONAR CAMPO ACEITE_TERMOS
ALTER TABLE usuarios 
ADD COLUMN aceite_termos BOOLEAN NOT NULL DEFAULT FALSE;

-- 📝 3. ADICIONAR COMENTÁRIO NO CAMPO
COMMENT ON COLUMN usuarios.aceite_termos IS 'Indica se o usuário aceitou os termos de uso e política de privacidade';

-- 🔄 4. ATUALIZAR REGISTROS EXISTENTES (OPCIONAL)
-- Define aceiteTermos = TRUE para usuários ativos existentes
UPDATE usuarios 
SET aceite_termos = TRUE 
WHERE status = 'ATIVO' 
  AND aceite_termos = FALSE;

-- 📊 5. VERIFICAR RESULTADOS
SELECT 
    COUNT(*) as total_usuarios,
    COUNT(CASE WHEN aceite_termos = TRUE THEN 1 END) as usuarios_com_aceite,
    COUNT(CASE WHEN aceite_termos = FALSE THEN 1 END) as usuarios_sem_aceite
FROM usuarios;

-- 🔍 6. VERIFICAR ESTRUTURA ATUALIZADA
SELECT column_name, data_type, is_nullable, column_default 
FROM information_schema.columns 
WHERE table_name = 'usuarios' 
AND table_schema = CURRENT_SCHEMA()
ORDER BY ordinal_position;

-- 📋 7. EXIBIR ALGUNS REGISTROS PARA VERIFICAÇÃO
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
-- ✅ VALIDAÇÕES DE INTEGRIDADE
-- ================================================================================================

-- 🔍 Verificar se todos os campos obrigatórios estão preenchidos
SELECT 
    'Verificação de campos obrigatórios' as verificacao,
    COUNT(*) as total_registros,
    COUNT(CASE WHEN usuario IS NULL OR usuario = '' THEN 1 END) as sem_usuario,
    COUNT(CASE WHEN email IS NULL OR email = '' THEN 1 END) as sem_email,
    COUNT(CASE WHEN senha IS NULL OR senha = '' THEN 1 END) as sem_senha,
    COUNT(CASE WHEN tipo IS NULL THEN 1 END) as sem_tipo,
    COUNT(CASE WHEN status IS NULL THEN 1 END) as sem_status,
    COUNT(CASE WHEN aceite_termos IS NULL THEN 1 END) as sem_aceite_termos
FROM usuarios;

-- 🔍 Verificar emails duplicados
SELECT 
    'Verificação de emails duplicados' as verificacao,
    email,
    COUNT(*) as quantidade
FROM usuarios 
GROUP BY email 
HAVING COUNT(*) > 1;

-- 🔍 Verificar distribuição por tipo de usuário
SELECT 
    'Distribuição por tipo' as verificacao,
    tipo,
    COUNT(*) as quantidade,
    COUNT(CASE WHEN aceite_termos = TRUE THEN 1 END) as com_aceite,
    ROUND(
        (COUNT(CASE WHEN aceite_termos = TRUE THEN 1 END) * 100.0 / COUNT(*)), 
        2
    ) as percentual_aceite
FROM usuarios 
GROUP BY tipo;

-- ================================================================================================
-- 📊 RELATÓRIO FINAL
-- ================================================================================================

SELECT 
    '🎉 ATUALIZAÇÃO CONCLUÍDA' as status,
    NOW() as data_execucao,
    'Campo aceite_termos adicionado com sucesso' as resultado;

-- ================================================================================================
-- 🔧 ROLLBACK (EM CASO DE EMERGÊNCIA)
-- ================================================================================================
-- CUIDADO: Execute apenas se necessário fazer rollback

-- Para remover o campo (USE COM CUIDADO):
-- ALTER TABLE usuarios DROP COLUMN aceite_termos;

-- Para verificar se o rollback foi bem-sucedido:
-- SELECT column_name FROM information_schema.columns 
-- WHERE table_name = 'usuarios' AND column_name = 'aceite_termos';
-- (Não deve retornar nenhum resultado após o rollback)

-- ================================================================================================
-- 📝 NOTAS IMPORTANTES
-- ================================================================================================
-- 
-- 1. ✅ Campo aceite_termos é NOT NULL com DEFAULT FALSE
-- 2. ✅ Registros existentes são atualizados para TRUE se status = ATIVO
-- 3. ✅ Campo possui comentário descritivo
-- 4. ✅ Validações de integridade incluídas
-- 5. ⚠️  Rollback disponível mas use com cuidado
-- 6. 📊 Relatórios incluídos para verificação
-- 
-- ================================================================================================
