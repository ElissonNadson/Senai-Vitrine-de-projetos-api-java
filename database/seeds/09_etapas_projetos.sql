-- 📋 SCRIPT 09: ETAPAS DOS PROJETOS
-- Cria etapas realistas para cada projeto
-- Data: 2025-06-19

BEGIN;

-- Criar etapas realistas para cada projeto
WITH projetos_criados AS (
    SELECT uuid, titulo, ROW_NUMBER() OVER (ORDER BY criado_em) as rn 
    FROM projeto
)
INSERT INTO etapas_projeto (uuid, nome_etapa, descricao, ordem, status, projeto_uuid, criado_em, atualizado_em)
SELECT 
    gen_random_uuid(),
    etapa.nome,
    etapa.descricao,
    etapa.ordem,
    CASE 
        WHEN etapa.ordem <= 2 THEN 'CONCLUIDA'
        WHEN etapa.ordem = 3 THEN 'EM_ANDAMENTO'
        ELSE 'PENDENTE'
    END,
    pc.uuid,
    NOW() - INTERVAL '20 days' * RANDOM(),
    NOW()
FROM projetos_criados pc
CROSS JOIN (
    VALUES 
        (1, 'Planejamento', 'Definição do escopo, requisitos e cronograma do projeto'),
        (2, 'Análise e Design', 'Modelagem do sistema, criação de diagramas e protótipos'),
        (3, 'Desenvolvimento', 'Implementação das funcionalidades principais do sistema'),
        (4, 'Testes', 'Realização de testes unitários, integração e aceitação'),
        (5, 'Documentação', 'Criação da documentação técnica e manual do usuário'),
        (6, 'Apresentação', 'Preparação e execução da apresentação final do projeto')
) AS etapa(ordem, nome, descricao);

-- Verificar quantas etapas foram criadas
SELECT 
    '✅ 09_etapas_projetos.sql executado com sucesso!' as status,
    COUNT(*) as total_etapas,
    'Etapas de projetos criadas' as descricao
FROM etapas_projeto;

COMMIT;
