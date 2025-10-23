-- 📎 SCRIPT 11: ANEXOS DOS PROJETOS
-- Cria anexos de exemplo para as etapas concluídas
-- Data: 2025-06-19

BEGIN;

-- Criar anexos para etapas concluídas
WITH etapas_concluidas AS (
    SELECT uuid, nome_etapa, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM etapas_projeto 
    WHERE status = 'CONCLUIDA' 
    LIMIT 15
)
INSERT INTO anexo_etapa (uuid, nome_arquivo, tipo, url, etapa_uuid, data_upload)
SELECT 
    gen_random_uuid(),
    CASE ec.rn % 5 + 1
        WHEN 1 THEN 'Documento_Requisitos.pdf'
        WHEN 2 THEN 'Diagrama_Classes.png'
        WHEN 3 THEN 'Prototipo_Interface.figma'
        WHEN 4 THEN 'Codigo_Fonte.zip'
        WHEN 5 THEN 'Manual_Usuario.docx'
    END,
    CASE ec.rn % 5 + 1
        WHEN 1 THEN 'PDF'
        WHEN 2 THEN 'PNG'
        WHEN 3 THEN 'ZIP'
        WHEN 4 THEN 'ZIP'
        WHEN 5 THEN 'DOCX'
    END,
    'https://senai-storage.s3.amazonaws.com/projetos/anexos/' || 
    CASE ec.rn % 5 + 1
        WHEN 1 THEN 'documento_requisitos_' || ec.rn || '.pdf'
        WHEN 2 THEN 'diagrama_classes_' || ec.rn || '.png'
        WHEN 3 THEN 'prototipo_interface_' || ec.rn || '.zip'
        WHEN 4 THEN 'codigo_fonte_' || ec.rn || '.zip'
        WHEN 5 THEN 'manual_usuario_' || ec.rn || '.docx'
    END,
    ec.uuid,
    NOW() - INTERVAL '15 days' * RANDOM()
FROM etapas_concluidas ec;

-- Criar anexos adicionais para demonstração
INSERT INTO anexo (uuid, nome_arquivo, tipo, url, etapa_uuid, data_upload)
SELECT 
    gen_random_uuid(),
    'Apresentacao_Final.pptx',
    'PPTX',
    'https://senai-storage.s3.amazonaws.com/projetos/anexos/apresentacao_final_' || ep.uuid || '.pptx',
    ep.uuid,
    NOW() - INTERVAL '5 days'
FROM etapas_projeto ep
WHERE ep.nome_etapa = 'Apresentação' AND ep.status = 'CONCLUIDA'
LIMIT 3;

-- Verificar quantos anexos foram criados
SELECT 
    '✅ 11_anexos.sql executado com sucesso!' as status,
    (SELECT COUNT(*) FROM anexo_etapa) as anexos_etapa,
    (SELECT COUNT(*) FROM anexo) as anexos_gerais,
    'Anexos dos projetos criados' as descricao;

COMMIT;
