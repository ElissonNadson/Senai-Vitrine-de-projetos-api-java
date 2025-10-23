# 🗄️ Seeds do Banco de Dados SENAI

Este diretório contém scripts SQL para popular o banco de dados com dados de exemplo.

## 📁 Estrutura dos Arquivos

### 🚀 Execução Rápida
- **`run_all_seeds.sql`** - Script principal que executa todos os seeds

### 📋 Scripts Individuais
1. **`01_cursos.sql`** - Cursos técnicos do SENAI
2. **`02_enderecos.sql`** - Endereços residenciais
3. **`03_usuarios.sql`** - Usuários (professores e alunos)
4. **`04_professores.sql`** - Perfis dos professores
5. **`05_alunos.sql`** - Perfis dos alunos
6. **`06_unidades_curriculares.sql`** - Módulos de ensino
7. **`07_disciplinas.sql`** - Disciplinas dos cursos
8. **`08_projetos.sql`** - Projetos dos alunos
9. **`09_etapas_projetos.sql`** - Etapas dos projetos
10. **`10_relacionamentos.sql`** - Relacionamentos entre entidades
11. **`11_anexos.sql`** - Anexos dos projetos

### 🧹 Limpeza
- **`clean_database.sql`** - Remove todos os dados (CUIDADO!)

## 🚀 Como Usar

### Opção 1: Executar Tudo de Uma Vez
```sql
-- No seu cliente SQL, execute:
\i database/seeds/run_all_seeds.sql
```

### Opção 2: Executar Scripts Individuais
```sql
-- Execute na ordem numérica:
\i database/seeds/01_cursos.sql
\i database/seeds/02_enderecos.sql
-- ... e assim por diante
```

### Opção 3: Via Terminal (PostgreSQL)
```bash
# Executar tudo
psql -d senai -f database/seeds/run_all_seeds.sql

# Ou individual
psql -d senai -f database/seeds/01_cursos.sql
```

## 📊 Dados Criados

- **🏫 6 Cursos** técnicos realistas
- **👥 31 Usuários** (professores e alunos)
- **🏠 20 Endereços** de São Paulo
- **🚀 8 Projetos** inovadores
- **📋 48 Etapas** de projetos
- **📎 10 Anexos** de exemplo

## ⚠️ Observações

- ✅ **Seguro para desenvolvimento** - Dados de exemplo
- ❌ **Não usar em produção** - Senhas são padrão
- 🔄 **Idempotente** - Pode executar múltiplas vezes
- 🧹 **Limpeza disponível** - Use `clean_database.sql` se necessário

## 🎯 Casos de Uso

- 🧪 **Testes de API** - Dados realistas para testar endpoints
- 📊 **Demonstrações** - Apresentar funcionalidades do sistema
- 👨‍💻 **Desenvolvimento** - Ambiente local com dados
- 🎓 **Treinamento** - Ensinar uso do sistema

---

**📝 Criado em:** 19 de junho de 2025  
**🎯 Versão:** 1.0  
**👤 Autor:** Sistema SENAI-API
