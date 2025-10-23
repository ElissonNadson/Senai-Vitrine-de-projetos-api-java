-- Seed para inserir mais unidades curriculares
-- Arquivo: seed_unidades_curriculares.sql

-- Inserir unidades curriculares específicas para cada área/curso
INSERT INTO unidade_curricular (nome, descricao, carga_horaria, criado_em, atualizado_em) VALUES 

-- Unidades Curriculares para Desenvolvimento de Sistemas
('Programação Web Front-end', 'Desenvolvimento de interfaces web usando HTML5, CSS3, JavaScript, React e frameworks modernos', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Programação Web Back-end', 'Desenvolvimento de APIs REST, Node.js, Java Spring Boot, Python Django/Flask', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Banco de Dados', 'Modelagem, criação e manipulação de bancos relacionais e NoSQL', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Desenvolvimento Mobile', 'Criação de aplicativos móveis usando React Native, Flutter ou desenvolvimento nativo', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Projetos de Sistemas', 'Metodologias ágeis, gestão de projetos, Git/GitHub, DevOps básico', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Análise e Projeto de Sistemas', 'UML, levantamento de requisitos, arquitetura de software', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Teste de Software', 'Testes unitários, integração, automatizados e metodologias de QA', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Unidades Curriculares para Automação Industrial
('Controladores Lógicos Programáveis (CLPs)', 'Programação e configuração de CLPs industriais', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sistemas SCADA', 'Supervisão e controle de processos industriais', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Instrumentação Industrial', 'Sensores, transdutores e instrumentos de medição', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Redes Industriais', 'Protocolos de comunicação industrial, Ethernet/IP, Modbus', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Pneumática e Hidráulica', 'Sistemas pneumáticos e hidráulicos aplicados à automação', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Projetos de Automação', 'Desenvolvimento de projetos completos de automação industrial', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Unidades Curriculares para Mecânica
('Desenho Técnico Mecânico', 'Leitura e interpretação de desenhos técnicos mecânicos', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Processos de Usinagem', 'Torneamento, fresamento, furação e processos de usinagem', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Soldagem', 'Processos de soldagem, eletrodo revestido, MIG/MAG, TIG', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Metrologia', 'Instrumentos de medição, tolerâncias e controle dimensional', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Manutenção Mecânica', 'Manutenção preventiva, preditiva e corretiva de equipamentos', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Materiais e Tratamentos Térmicos', 'Propriedades dos materiais e tratamentos térmicos', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Unidades Curriculares para Eletrônica
('Circuitos Analógicos', 'Análise e projeto de circuitos analógicos', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Circuitos Digitais', 'Lógica digital, microprocessadores e sistemas digitais', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Microcontroladores', 'Programação e aplicação de microcontroladores', 120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sistemas Embarcados', 'Desenvolvimento de sistemas embarcados e IoT', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Manutenção Eletrônica', 'Diagnóstico e reparo de equipamentos eletrônicos', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Projetos Eletrônicos', 'Desenvolvimento de projetos eletrônicos completos', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Unidades Curriculares para Redes de Computadores
('Fundamentos de Redes', 'Protocolos TCP/IP, modelo OSI, topologias de rede', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Administração de Servidores', 'Windows Server, Linux, virtualização', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Segurança da Informação', 'Firewall, criptografia, políticas de segurança', 80, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cabeamento Estruturado', 'Normas de cabeamento, fibra óptica, infraestrutura física', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Redes Wireless', 'Configuração e gerenciamento de redes sem fio', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Projetos de Infraestrutura', 'Projeto e implementação de infraestrutura de TI', 100, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Unidades Curriculares Gerais/Transversais
('Empreendedorismo e Inovação', 'Desenvolvimento do espírito empreendedor e inovação tecnológica', 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ética e Responsabilidade Profissional', 'Ética profissional e responsabilidade social', 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Comunicação e Expressão', 'Desenvolvimento da comunicação oral e escrita', 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Inglês Técnico', 'Inglês aplicado à área técnica', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Gestão da Qualidade', 'Normas ISO, controle de qualidade, melhoria contínua', 60, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sustentabilidade e Meio Ambiente', 'Práticas sustentáveis e responsabilidade ambiental', 40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Verificar se os dados foram inseridos
-- SELECT nome, carga_horaria FROM unidade_curricular ORDER BY nome;
