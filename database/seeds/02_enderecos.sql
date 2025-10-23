-- 🏠 SCRIPT 02: ENDEREÇOS RESIDENCIAIS
-- Cria endereços realistas de São Paulo
-- Data: 2025-06-19

BEGIN;

-- Inserir endereços realistas de São Paulo
INSERT INTO endereco (uuid, logradouro, numero, bairro, cidade, estado, cep, pais, complemento) VALUES 
(gen_random_uuid(), 'Rua das Flores', 123, 'Centro', 'São Paulo', 'SP', '01234-567', 'Brasil', 'Apto 45'),
(gen_random_uuid(), 'Avenida Paulista', 1500, 'Bela Vista', 'São Paulo', 'SP', '01310-100', 'Brasil', ''),
(gen_random_uuid(), 'Rua Augusta', 800, 'Consolação', 'São Paulo', 'SP', '01305-100', 'Brasil', 'Sala 12'),
(gen_random_uuid(), 'Rua da Liberdade', 250, 'Liberdade', 'São Paulo', 'SP', '01503-010', 'Brasil', ''),
(gen_random_uuid(), 'Avenida Faria Lima', 2000, 'Itaim Bibi', 'São Paulo', 'SP', '01451-000', 'Brasil', 'Conjunto 801'),
(gen_random_uuid(), 'Rua Oscar Freire', 300, 'Jardins', 'São Paulo', 'SP', '01426-001', 'Brasil', ''),
(gen_random_uuid(), 'Rua 25 de Março', 100, 'Centro', 'São Paulo', 'SP', '01021-020', 'Brasil', 'Loja 15'),
(gen_random_uuid(), 'Avenida Rebouças', 1200, 'Pinheiros', 'São Paulo', 'SP', '05402-100', 'Brasil', ''),
(gen_random_uuid(), 'Rua Teodoro Sampaio', 500, 'Pinheiros', 'São Paulo', 'SP', '05405-000', 'Brasil', 'Apto 202'),
(gen_random_uuid(), 'Avenida Higienópolis', 800, 'Higienópolis', 'São Paulo', 'SP', '01238-000', 'Brasil', ''),
(gen_random_uuid(), 'Rua Estados Unidos', 400, 'Jardim Paulista', 'São Paulo', 'SP', '01427-000', 'Brasil', ''),
(gen_random_uuid(), 'Avenida Ibirapuera', 600, 'Ibirapuera', 'São Paulo', 'SP', '04029-200', 'Brasil', 'Casa 3'),
(gen_random_uuid(), 'Rua Haddock Lobo', 150, 'Cerqueira César', 'São Paulo', 'SP', '01414-001', 'Brasil', ''),
(gen_random_uuid(), 'Avenida Europa', 900, 'Jardim Europa', 'São Paulo', 'SP', '01449-000', 'Brasil', ''),
(gen_random_uuid(), 'Rua Bela Cintra', 700, 'Consolação', 'São Paulo', 'SP', '01415-002', 'Brasil', 'Apto 1001'),
(gen_random_uuid(), 'Rua Consolação', 1000, 'Consolação', 'São Paulo', 'SP', '01302-000', 'Brasil', ''),
(gen_random_uuid(), 'Avenida Nove de Julho', 1500, 'Bela Vista', 'São Paulo', 'SP', '01313-000', 'Brasil', 'Sala 501'),
(gen_random_uuid(), 'Rua da Consolação', 2000, 'Consolação', 'São Paulo', 'SP', '01416-000', 'Brasil', ''),
(gen_random_uuid(), 'Avenida São João', 800, 'Centro', 'São Paulo', 'SP', '01035-000', 'Brasil', ''),
(gen_random_uuid(), 'Rua Direita', 50, 'Centro', 'São Paulo', 'SP', '01002-000', 'Brasil', 'Andar 12');

-- Verificar quantos endereços foram inseridos
SELECT 
    '✅ 02_enderecos.sql executado com sucesso!' as status,
    COUNT(*) as total_enderecos,
    'Endereços de São Paulo criados' as descricao
FROM endereco;

COMMIT;
