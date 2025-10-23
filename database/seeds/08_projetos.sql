-- 🚀 SCRIPT 08: PROJETOS DOS ALUNOS
-- Cria projetos inovadores e realistas
-- Data: 2025-06-19

BEGIN;

-- Criar projetos variados e interessantes
WITH alunos_lideres AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM alunos LIMIT 8
),
disciplinas_disponiveis AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM disciplina
),
unidades_disponiveis AS (
    SELECT uuid, ROW_NUMBER() OVER (ORDER BY uuid) as rn 
    FROM unidade_curricular LIMIT 8
)
INSERT INTO projeto (
    uuid, titulo, descricao, codigo, turma, curso, status, visibilidade, 
    visibilidade_codigo, visibilidade_anexos, itinerario, lab_maker, 
    participou_saga, banner_url, lider_projeto_uuid, disciplina_uuid, 
    unidade_curricular_uuid, criado_em, atualizado_em
)
SELECT 
    gen_random_uuid(),
    CASE al.rn
        WHEN 1 THEN 'Sistema de Gestão Escolar Web'
        WHEN 2 THEN 'Aplicativo Mobile para Delivery'
        WHEN 3 THEN 'Robô Seguidor de Linha'
        WHEN 4 THEN 'Dashboard de Monitoramento IoT'
        WHEN 5 THEN 'E-commerce com React e Node.js'
        WHEN 6 THEN 'Sistema de Segurança Residencial'
        WHEN 7 THEN 'Chatbot com Inteligência Artificial'
        WHEN 8 THEN 'Automação de Estacionamento'
    END,
    CASE al.rn
        WHEN 1 THEN 'Sistema completo para gestão de alunos, notas e frequência utilizando Spring Boot e React. Inclui módulos para professores, coordenação e secretaria.'
        WHEN 2 THEN 'Aplicativo mobile desenvolvido em React Native para pedidos online com integração de pagamento e geolocalização em tempo real.'
        WHEN 3 THEN 'Robô autônomo programado em Arduino que utiliza sensores para seguir linhas pretas em superfícies, com controle PID implementado.'
        WHEN 4 THEN 'Dashboard web para monitoramento de sensores IoT distribuídos, com alertas em tempo real e histórico de dados usando MQTT.'
        WHEN 5 THEN 'Plataforma de e-commerce completa com carrinho de compras, sistema de pagamento via API e painel administrativo responsivo.'
        WHEN 6 THEN 'Sistema residencial com câmeras IP, sensores de movimento e controle via aplicativo mobile com notificações push.'
        WHEN 7 THEN 'Chatbot inteligente utilizando processamento de linguagem natural para atendimento ao cliente integrado ao WhatsApp.'
        WHEN 8 THEN 'Sistema automatizado para controle de vagas, cancelas e cobrança em estacionamentos usando RFID e reconhecimento de placas.'
    END,
    CASE al.rn
        WHEN 1 THEN 'SGE2025'
        WHEN 2 THEN 'DELMOB'
        WHEN 3 THEN 'ROBOLINE'
        WHEN 4 THEN 'IOTDASH'
        WHEN 5 THEN 'ECOMM'
        WHEN 6 THEN 'SECRES'
        WHEN 7 THEN 'AIBOT'
        WHEN 8 THEN 'AUTOPARK'
    END,
    CASE al.rn % 4
        WHEN 1 THEN 'TDS-2025A'
        WHEN 2 THEN 'TRC-2025B'
        WHEN 3 THEN 'TEL-2025A'
        WHEN 0 THEN 'TMC-2025B'
    END,
    CASE al.rn % 6 + 1
        WHEN 1 THEN 'Técnico em Desenvolvimento de Sistemas'
        WHEN 2 THEN 'Técnico em Redes de Computadores'
        WHEN 3 THEN 'Técnico em Eletrônica'
        WHEN 4 THEN 'Técnico em Mecatrônica'
        WHEN 5 THEN 'Técnico em Informática para Internet'
        WHEN 6 THEN 'Técnico em Segurança do Trabalho'
    END,
    CASE al.rn % 4 + 1
        WHEN 1 THEN 'ATIVO'
        WHEN 2 THEN 'ATIVO'
        WHEN 3 THEN 'ATIVO'
        WHEN 4 THEN 'CONCLUIDO'
    END,
    CASE al.rn % 2
        WHEN 0 THEN 'PUBLICO'
        WHEN 1 THEN 'PRIVADO'
    END,
    'PUBLICO',
    CASE al.rn % 3
        WHEN 0 THEN 'PUBLICO'
        ELSE 'PRIVADO'
    END,
    CASE al.rn % 2 = 0 WHEN true THEN true ELSE false END,
    CASE al.rn % 3 = 0 WHEN true THEN true ELSE false END,
    CASE al.rn % 4 = 0 WHEN true THEN true ELSE false END,
    'https://via.placeholder.com/800x400/0066cc/ffffff?text=Projeto+' || al.rn,
    al.uuid,
    dd.uuid,
    ud.uuid,
    NOW() - INTERVAL '30 days' * RANDOM(),
    NOW()
FROM alunos_lideres al
JOIN disciplinas_disponiveis dd ON al.rn = dd.rn
JOIN unidades_disponiveis ud ON al.rn = ud.rn;

-- Verificar quantos projetos foram criados
SELECT 
    '✅ 08_projetos.sql executado com sucesso!' as status,
    COUNT(*) as total_projetos,
    'Projetos inovadores criados' as descricao
FROM projeto;

COMMIT;
