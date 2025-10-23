# 🛠️ VS Code Setup Guide - SENAI API

## 📋 Índice
- [Instalação Básica](#-instalação-básica)
- [Extensões Essenciais](#-extensões-essenciais)
- [Configurações do Workspace](#️-configurações-do-workspace)
- [Debug Configuration](#-debug-configuration)
- [Snippets Úteis](#-snippets-úteis)
- [Shortcuts Recomendados](#⌨️-shortcuts-recomendados)
- [Troubleshooting](#-troubleshooting)

---

## 📥 Instalação Básica

### 1. Pré-requisitos
- ☕ **Java 21 JDK** ([Download Adoptium](https://adoptium.net/))
- 🎯 **VS Code** ([Download](https://code.visualstudio.com/))
- 🔧 **Maven** (incluído via wrapper no projeto)

### 2. Verificar Instalação Java
```bash
# Verificar versão do Java
java -version

# Deve retornar algo como:
# openjdk version "21.0.7" 2024-10-15
```

### 3. Configurar JAVA_HOME
```bash
# Windows (PowerShell)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.7.6-hotspot"

# Linux/Mac
export JAVA_HOME=/path/to/jdk-21
```

---

## 🔌 Extensões Essenciais

### 📦 Extension Pack for Java (Obrigatório)
```json
"vscjava.vscode-java-pack"
```
**Inclui:**
- Language Support for Java
- Debugger for Java  
- Test Runner for Java
- Maven for Java
- Project Manager for Java
- Visual Studio IntelliCode

### 🔧 Extensões Específicas do Projeto

```json
{
  "recommendations": [
    "vscjava.vscode-java-pack",        // Java support essencial
    "vscjava.vscode-lombok",           // Lombok annotations
    "redhat.vscode-xml",               // XML/Maven support
    "pivotal.vscode-spring-boot",      // Spring Boot tools
    "ms-vscode.vscode-json",           // JSON validation
    "gabrielbb.vscode-lombok",         // Lombok alternative
    "humao.rest-client",               // REST client for testing
    "ms-vscode.vscode-eslint",         // Code quality
    "esbenp.prettier-vscode",          // Code formatting
    "aaron-bond.better-comments",      // Enhanced comments
    "bradlc.vscode-tailwindcss",       // CSS utilities
    "formulahendry.auto-rename-tag",   // HTML/XML tags
    "ms-vscode.live-server",           // Live preview
    "ms-vscode.vscode-typescript-next" // TypeScript support
  ]
}
```

### 🚀 Instalação Automática das Extensões

1. **Abra o projeto no VS Code**
2. **Aceite o popup** de instalação das extensões recomendadas
3. **Ou instale manualmente** via Command Palette (`Ctrl+Shift+P`):
   ```
   Extensions: Show Recommended Extensions
   ```

---

## ⚙️ Configurações do Workspace

### 📁 Arquivo `.vscode/settings.json`

```json
{
  // ☕ Java Configuration
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.compile.nullAnalysis.mode": "disabled", 
  "java.saveActions.organizeImports": true,
  "java.imports.removeUnused": true,
  "java.codeGeneration.insertionLocation": "afterCursor",
  "java.errors.incompleteClasspath.severity": "warning",
  "java.clean.workspace": false,

  // 🌿 Spring Boot
  "spring-boot.ls.problem.application-properties.enabled": true,
  "spring-boot.ls.problem.application-yaml.enabled": true,

  // 🔧 Maven
  "maven.downloadSources": true,
  "maven.downloadJavadoc": true,

  // 📝 Editor
  "editor.formatOnSave": true,
  "editor.codeActionsOnSave": {
    "source.organizeImports": true,
    "source.fixAll": true
  },
  "editor.rulers": [120],
  "editor.tabSize": 4,
  "editor.insertSpaces": true,

  // 📁 Files
  "files.exclude": {
    "**/target": true,
    "**/.classpath": true,
    "**/.project": true,
    "**/.settings": true,
    "**/.factorypath": true
  },

  // 🔍 Search
  "search.exclude": {
    "**/target": true,
    "**/node_modules": true,
    "**/.git": true
  },

  // 🎨 Workbench
  "workbench.iconTheme": "vs-seti",
  "workbench.colorTheme": "Default Dark+",

  // 🔗 Terminal
  "terminal.integrated.defaultProfile.windows": "PowerShell",
  "terminal.integrated.cwd": "${workspaceFolder}",

  // 📋 Emmet
  "emmet.includeLanguages": {
    "java": "html"
  }
}
```

---

## 🐛 Debug Configuration

### 📁 Arquivo `.vscode/launch.json`

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "🚀 Debug SENAI API",
      "request": "launch",
      "mainClass": "com.exemplo.meuapp.Main",
      "projectName": "meuapp",
      "args": "",
      "vmArgs": "-Dspring.profiles.active=dev",
      "env": {
        "SPRING_PROFILES_ACTIVE": "dev"
      },
      "console": "integratedTerminal",
      "internalConsoleOptions": "neverOpen"
    },
    {
      "type": "java", 
      "name": "🧪 Debug Tests",
      "request": "launch",
      "mainClass": "",
      "projectName": "meuapp",
      "vmArgs": "-Dspring.profiles.active=test",
      "console": "integratedTerminal"
    },
    {
      "type": "java",
      "name": "🔧 Debug with Profile",
      "request": "launch", 
      "mainClass": "com.exemplo.meuapp.Main",
      "projectName": "meuapp",
      "args": "",
      "vmArgs": "-Dspring.profiles.active=${input:profile}",
      "console": "integratedTerminal"
    }
  ],
  "inputs": [
    {
      "id": "profile",
      "description": "Spring Profile",
      "default": "dev",
      "type": "pickString",
      "options": [
        "dev",
        "test", 
        "prod"
      ]
    }
  ]
}
```

### 🎮 Como Usar o Debug

1. **Colocar breakpoints** clicando na margem esquerda
2. **Pressionar F5** ou ir em `Run and Debug`
3. **Escolher configuração** de debug
4. **Usar controles** de debug (step over, step into, etc.)

---

## 🎯 Tasks Configuration

### 📁 Arquivo `.vscode/tasks.json`

```json
{
  "version": "2.0.0", 
  "tasks": [
    {
      "label": "🧹 Maven Clean",
      "type": "shell",
      "command": "./mvnw",
      "args": ["clean"],
      "group": "build",
      "presentation": {
        "echo": true,
        "reveal": "always",
        "focus": false,
        "panel": "shared"
      }
    },
    {
      "label": "🔨 Maven Compile", 
      "type": "shell",
      "command": "./mvnw",
      "args": ["compile"],
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "presentation": {
        "echo": true,
        "reveal": "always", 
        "focus": false,
        "panel": "shared"
      },
      "problemMatcher": ["$tsc"]
    },
    {
      "label": "🧪 Maven Test",
      "type": "shell", 
      "command": "./mvnw",
      "args": ["test"],
      "group": "test",
      "presentation": {
        "echo": true,
        "reveal": "always",
        "focus": false,
        "panel": "shared"
      }
    },
    {
      "label": "🚀 Spring Boot Run",
      "type": "shell",
      "command": "./mvnw", 
      "args": ["spring-boot:run"],
      "group": "build",
      "isBackground": true,
      "presentation": {
        "echo": true,
        "reveal": "always",
        "focus": false,
        "panel": "shared"
      },
      "problemMatcher": {
        "pattern": {
          "regexp": "^(.*):(\\d+):(\\d+):\\s+(warning|error):\\s+(.*)$",
          "file": 1,
          "line": 2,
          "column": 3,
          "severity": 4,
          "message": 5
        },
        "background": {
          "activeOnStart": true,
          "beginsPattern": "^.*Tomcat initialized.*$",
          "endsPattern": "^.*Started.*Main.*$"
        }
      }
    },
    {
      "label": "📦 Maven Package",
      "type": "shell",
      "command": "./mvnw",
      "args": ["clean", "package", "-DskipTests"],
      "group": "build",
      "presentation": {
        "echo": true,
        "reveal": "always",
        "focus": false,
        "panel": "shared"
      }
    }
  ]
}
```

---

## 💡 Snippets Úteis

### 📝 Java Snippets

Criar arquivo: `.vscode/java.code-snippets`

```json
{
  "Spring REST Controller": {
    "prefix": "scontroller",
    "body": [
      "@RestController",
      "@RequestMapping(\"/api/v1/${1:resource}\")",
      "@RequiredArgsConstructor",
      "@Slf4j",
      "public class ${2:Resource}Controller {",
      "",
      "    private final ${3:Service} ${4:service};",
      "",
      "    @GetMapping",
      "    public ResponseEntity<List<${5:DTO}>> findAll() {",
      "        List<${5:DTO}> result = ${4:service}.findAll();",
      "        return ResponseEntity.ok(result);",
      "    }",
      "",
      "    @GetMapping(\"/{id}\")",
      "    public ResponseEntity<${5:DTO}> findById(@PathVariable UUID id) {",
      "        ${5:DTO} result = ${4:service}.findById(id);", 
      "        return ResponseEntity.ok(result);",
      "    }",
      "",
      "    @PostMapping",
      "    public ResponseEntity<${5:DTO}> create(@RequestBody @Valid ${6:CreateDTO} dto) {",
      "        ${5:DTO} result = ${4:service}.create(dto);",
      "        return ResponseEntity.status(HttpStatus.CREATED).body(result);",
      "    }",
      "}"
    ],
    "description": "Create a Spring REST Controller"
  },

  "Spring Service Use Case": {
    "prefix": "susecase",
    "body": [
      "@Service",
      "@RequiredArgsConstructor", 
      "@Transactional",
      "@Slf4j",
      "public class ${1:Resource}UseCase {",
      "",
      "    private final ${2:Gateway} ${3:gateway};",
      "",
      "    public ${4:DTO} execute(${5:RequestDTO} request) {",
      "        log.info(\"Executing ${1:Resource} use case with: {}\", request);",
      "        ",
      "        // Business logic here",
      "        $0",
      "        ",
      "        return result;",
      "    }",
      "}"
    ],
    "description": "Create a Spring Service Use Case"
  },

  "JPA Entity": {
    "prefix": "jentity",
    "body": [
      "@Entity",
      "@Table(name = \"${1:table_name}\")",
      "@Data",
      "@Builder",
      "@NoArgsConstructor", 
      "@AllArgsConstructor",
      "public class ${2:Entity} {",
      "",
      "    @Id",
      "    @GeneratedValue(strategy = GenerationType.UUID)",
      "    private UUID uuid;",
      "",
      "    @Column(nullable = false)",
      "    private String ${3:name};",
      "",
      "    @CreationTimestamp",
      "    @Column(name = \"criado_em\")",
      "    private LocalDateTime criadoEm;",
      "",
      "    @UpdateTimestamp", 
      "    @Column(name = \"atualizado_em\")",
      "    private LocalDateTime atualizadoEm;",
      "",
      "    $0",
      "}"
    ],
    "description": "Create a JPA Entity"
  }
}
```

---

## ⌨️ Shortcuts Recomendados

### 🔥 Essenciais
| Shortcut | Ação |
|----------|------|
| `Ctrl+Shift+P` | Command Palette |
| `Ctrl+P` | Quick Open |
| `F5` | Start Debug |
| `Ctrl+F5` | Run without Debug |
| `Ctrl+Shift+F` | Search in Files |
| `Ctrl+Shift+E` | Explorer |
| `Ctrl+J` | Toggle Terminal |
| `Ctrl+B` | Toggle Sidebar |

### ☕ Java Específicos
| Shortcut | Ação |
|----------|------|
| `Ctrl+Shift+O` | Organize Imports |
| `Alt+Shift+O` | Remove Unused Imports |
| `Ctrl+.` | Quick Fix |
| `F2` | Rename Symbol |
| `F12` | Go to Definition |
| `Shift+F12` | Find All References |
| `Ctrl+Shift+I` | Format Document |

### 🔧 Maven Tasks
| Shortcut | Ação |
|----------|------|
| `Ctrl+Shift+P` → "Tasks: Run Task" | Executar tarefas Maven |
| `Ctrl+Shift+P` → "Java: Clean Workspace" | Limpar workspace |
| `Ctrl+Shift+P` → "Java: Rebuild Projects" | Rebuild projetos |

---

## 🧩 Plugins Adicionais Recomendados

### 🎨 Visual & UX
```json
{
  "recommendations": [
    "pkief.material-icon-theme",      // Material Icons
    "zhuangtongfa.material-theme",    // Material Theme
    "oderwat.indent-rainbow",         // Rainbow Indents  
    "usernamehw.errorlens",           // Error Lens
    "gruntfuggly.todo-tree",          // TODO Tree
    "alefragnani.bookmarks",          // Bookmarks
    "christian-kohler.path-intellisense" // Path IntelliSense
  ]
}
```

### 🔧 Productivity
```json
{
  "recommendations": [
    "formulahendry.code-runner",      // Code Runner
    "ms-vscode.powershell",          // PowerShell
    "ms-vscode.vscode-github-pr",    // GitHub PR
    "eamodio.gitlens",               // GitLens
    "donjayamanne.githistory",       // Git History
    "mhutchie.git-graph"             // Git Graph
  ]
}
```

---

## 🔧 Troubleshooting

### ❌ Problemas Comuns

#### 🚫 "Java não foi encontrado"
```bash
# Verificar JAVA_HOME
echo $JAVA_HOME

# Configurar corretamente
export JAVA_HOME=/path/to/jdk-21
```

#### 🚫 "Maven wrapper não funciona"
```bash
# Dar permissão (Linux/Mac)
chmod +x mvnw

# Windows - usar mvnw.cmd
mvnw.cmd clean compile
```

#### 🚫 "Lombok não funciona"
1. Instalar extensão Lombok
2. Restart VS Code
3. Rebuild projeto: `Ctrl+Shift+P` → "Java: Rebuild Projects"

#### 🚫 "Imports não são organizados"
1. Verificar se extensão Java está ativa
2. Configurar: `"java.saveActions.organizeImports": true`
3. Manual: `Ctrl+Shift+O`

#### 🚫 "Debug não funciona"
1. Verificar se arquivo `.vscode/launch.json` existe
2. Verificar se projeto foi compilado
3. Restart VS Code

### 🔄 Reset Completo

```bash
# 1. Fechar VS Code
# 2. Deletar pastas temporárias
rm -rf target/
rm -rf .vscode/
rm -rf .metadata/

# 3. Reabrir projeto no VS Code
# 4. Aceitar instalação de extensões
# 5. Aguardar indexação Java
```

---

## 📋 Checklist Final

### ✅ Verificação Pré-Desenvolvimento

- [ ] Java 21 instalado e configurado
- [ ] VS Code instalado
- [ ] Extension Pack for Java instalado
- [ ] Lombok extension instalado  
- [ ] Spring Boot extension instalado
- [ ] Projeto compila sem erros: `./mvnw compile`
- [ ] Projeto executa: `./mvnw spring-boot:run`
- [ ] Debug funciona (F5)
- [ ] Organize imports funciona (Ctrl+Shift+O)
- [ ] Format document funciona (Ctrl+Shift+I)

### 🎯 Configurações Aplicadas

- [ ] `.vscode/settings.json` configurado
- [ ] `.vscode/launch.json` configurado  
- [ ] `.vscode/tasks.json` configurado
- [ ] `.vscode/extensions.json` configurado
- [ ] Snippets personalizados criados
- [ ] Themes e icons configurados

---

## 🆘 Suporte

**Problemas não resolvidos?**

1. 📧 **Email**: dev-support@senai.br
2. 💬 **Teams**: Canal #vscode-help  
3. 📖 **Wiki**: [VS Code SENAI Wiki](https://senai.atlassian.net/wiki/vscode)
4. 🎫 **Ticket**: [Service Desk SENAI](https://senai.atlassian.net/servicedesk)

---

<div align="center">
  <p>🛠️ <strong>Happy Coding!</strong> 🛠️</p>
  <p>Este guia foi criado com ❤️ pelo time SENAI</p>
</div>
