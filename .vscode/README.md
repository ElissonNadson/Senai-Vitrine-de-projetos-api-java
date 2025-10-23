# 📁 Configurações do VS Code - SENAI API

Este diretório contém todas as configurações necessárias para desenvolver no **VS Code** com máxima produtividade.

---

## 📋 Arquivos de Configuração

### 🎯 **extensions.json**
Lista de extensões recomendadas que serão sugeridas automaticamente quando você abrir o projeto.

**Extensões principais:**
- ☕ **Java Extension Pack** - Suporte completo ao Java
- 🌿 **Spring Boot Extensions** - Ferramentas Spring Boot
- 🔧 **Lombok** - Suporte a annotations Lombok
- 🎨 **Material Icon Theme** - Ícones bonitos
- 🔍 **GitLens** - Recursos avançados de Git

### ⚙️ **settings.json** 
Configurações específicas do workspace que otimizam o desenvolvimento Java/Spring Boot.

**Principais configurações:**
- Auto-organização de imports
- Formatação automática ao salvar
- Configurações do compilador Java
- Exclusão de pastas desnecessárias

### 🐛 **launch.json**
Configurações de debug pre-definidas para diferentes cenários.

**Configurações disponíveis:**
- 🚀 **Debug SENAI API** - Debug padrão da aplicação
- 🧪 **Debug Tests** - Debug específico para testes
- 🔧 **Debug with Profile** - Debug com perfil customizável
- 🌐 **Debug on Port 8080** - Debug na porta 8080

### 🔧 **tasks.json** 
Tarefas Maven pré-configuradas acessíveis via Command Palette.

**Tarefas disponíveis:**
- 🧹 **Maven Clean** - Limpar build
- 🔨 **Maven Compile** - Compilar projeto
- 🧪 **Maven Test** - Executar testes
- 🚀 **Spring Boot Run** - Executar aplicação
- 📦 **Maven Package** - Gerar JAR
- 🔍 **Maven Verify** - Verificação completa

### 💡 **java.code-snippets**
Snippets personalizados para acelerar o desenvolvimento Java/Spring Boot.

**Snippets disponíveis:**
- `scontroller` - REST Controller completo
- `susecase` - Service Use Case
- `jentity` - JPA Entity
- `jrepository` - Repository Interface
- `dto` - DTO com validações
- `mapper` - MapStruct Mapper
- `exhandler` - Exception Handler
- `test` - Classe de teste com Mockito

---

## 🚀 Como Usar

### 1. **Abrir o Projeto**
```bash
code .
```

### 2. **Instalar Extensões**
- Aceite o popup de instalação automática das extensões
- Ou vá em `Extensions` → `Show Recommended Extensions`

### 3. **Executar Tarefas**
- `Ctrl+Shift+P` → "Tasks: Run Task"
- Escolha uma das tarefas disponíveis

### 4. **Debug**
- Pressione `F5` para debug
- Ou vá em `Run and Debug` (Ctrl+Shift+D)
- Escolha a configuração de debug desejada

### 5. **Usar Snippets**
- Digite o prefixo (ex: `scontroller`)
- Pressione `Tab` para expandir
- Preencha os placeholders

---

## ⌨️ Shortcuts Importantes

| Ação | Shortcut |
|------|----------|
| Command Palette | `Ctrl+Shift+P` |
| Debug | `F5` |
| Run without Debug | `Ctrl+F5` |
| Run Task | `Ctrl+Shift+P` → "Tasks: Run Task" |
| Organize Imports | `Ctrl+Shift+O` |
| Format Document | `Ctrl+Shift+I` |
| Quick Fix | `Ctrl+.` |
| Go to Definition | `F12` |
| Find References | `Shift+F12` |

---

## 🔧 Personalização

### Modificar Configurações
Edite os arquivos diretamente ou use as configurações do VS Code:
```
File → Preferences → Settings (Workspace)
```

### Adicionar Novos Snippets
Edite `.vscode/java.code-snippets` ou crie novos arquivos.

### Configurar Novas Tasks
Adicione entradas no `.vscode/tasks.json`.

### Novas Configurações de Debug
Adicione novas configurações em `.vscode/launch.json`.

---

## 🆘 Troubleshooting

### Extensões não carregam
1. Feche o VS Code
2. Reabra o projeto
3. Vá em `Extensions` → `Show Recommended Extensions`
4. Instale manualmente se necessário

### Java não compila
1. `Ctrl+Shift+P` → "Java: Rebuild Projects"
2. Verifique se JAVA_HOME está configurado
3. Restart VS Code

### Debug não funciona
1. Verifique se o projeto compila sem erros
2. Confirme que `.vscode/launch.json` existe
3. Tente compilar: `./mvnw compile`

### Tasks não aparecem
1. `Ctrl+Shift+P` → "Tasks: Configure Task"
2. Verifique se `.vscode/tasks.json` existe
3. Restart VS Code

---

## 📚 Documentação Adicional

- 📖 **[Setup Completo](../docs/vscode-setup.md)** - Guia detalhado
- 🏗️ **[Arquitetura](../docs/architecture.md)** - Estrutura do projeto
- 🚀 **[README Principal](../README.md)** - Visão geral

---

<div align="center">
  <p>⚡ <strong>Configurado para máxima produtividade!</strong> ⚡</p>
  <p>Desenvolvido com ❤️ pelo time SENAI</p>
</div>
