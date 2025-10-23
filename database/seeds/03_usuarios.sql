-- 👥 SCRIPT 03: USUÁRIOS DO SISTEMA
-- Cria professores e alunos com credenciais
-- Data: 2025-06-19

BEGIN;

-- 👨‍🏫 PROFESSORES
INSERT INTO usuarios (uuid, usuario, email, senha, tipo, status, criado_em, atualizado_em) VALUES 
(gen_random_uuid(), 'prof.carlos.silva', 'carlos.silva@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'prof.maria.santos', 'maria.santos@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'prof.joao.oliveira', 'joao.oliveira@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'prof.ana.costa', 'ana.costa@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'prof.pedro.lima', 'pedro.lima@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'prof.lucia.ferreira', 'lucia.ferreira@senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'PROFESSOR', 'ATIVO', NOW(), NOW());

-- 👨‍🎓 ALUNOS
INSERT INTO usuarios (uuid, usuario, email, senha, tipo, status, criado_em, atualizado_em) VALUES 
(gen_random_uuid(), 'lucas.pereira', 'lucas.pereira@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'amanda.rodrigues', 'amanda.rodrigues@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'rafael.martins', 'rafael.martins@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'isabella.alves', 'isabella.alves@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'gabriel.souza', 'gabriel.souza@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'julia.araujo', 'julia.araujo@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'matheus.barbosa', 'matheus.barbosa@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'sophia.nascimento', 'sophia.nascimento@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'daniel.cardoso', 'daniel.cardoso@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'laura.gomes', 'laura.gomes@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'henrique.dias', 'henrique.dias@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW()),
(gen_random_uuid(), 'beatriz.silva', 'beatriz.silva@aluno.senai.br', '$2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm', 'ALUNO', 'ATIVO', NOW(), NOW());

-- Verificar quantos usuários foram inseridos
SELECT 
    '✅ 03_usuarios.sql executado com sucesso!' as status,
    COUNT(*) FILTER (WHERE tipo = 'PROFESSOR') as total_professores,
    COUNT(*) FILTER (WHERE tipo = 'ALUNO') as total_alunos,
    COUNT(*) as total_usuarios
FROM usuarios 
WHERE email LIKE '%@senai.br' OR email LIKE '%@aluno.senai.br';

-- 📝 NOTA: Senha padrão para todos: "senai123"
-- Hash BCrypt: $2a$10$LZ97/NZQx2Bj0FgvWk6X7OlRK52p.HYk0LbLCp2MSAPH5FlpUq.Wm

COMMIT;
