# 🚀 Deploy no Render - Senai Vitrine de Projetos API

Este guia explica como fazer o deploy da API Java Spring Boot no Render.

## 📋 Pré-requisitos

- Conta no [Render](https://render.com)
- Banco de dados PostgreSQL já criado no Render
- Código da aplicação em um repositório Git (GitHub, GitLab, etc.)

## 🐳 Arquivos de Configuração

### Dockerfile
O projeto usa um Dockerfile multi-stage para otimizar o build e a imagem final:
- **Estágio 1**: Build com Maven usando Ubuntu e OpenJDK 17
- **Estágio 2**: Imagem final slim apenas com o JRE necessário

### application-prod.yml
Configurações específicas para produção:
- PostgreSQL como banco de dados
- Variáveis de ambiente para credenciais
- Logging otimizado
- Pool de conexões configurado

## 🔧 Configuração no Render

### Opção 1: Deploy Manual via Dashboard

1. **Acesse o Render Dashboard**
   - Vá para [dashboard.render.com](https://dashboard.render.com)

2. **Crie um novo Web Service**
   - Clique em "New +" → "Web Service"
   - Conecte seu repositório Git

3. **Configure o serviço**
   - **Name**: `senai-vitrine-api`
   - **Region**: Oregon (US West)
   - **Branch**: `main`
   - **Runtime**: Docker
   - **Instance Type**: Free

4. **Configure as variáveis de ambiente**
   ```
   SPRING_PROFILES_ACTIVE=prod
   DATABASE_URL=<sua-url-do-postgres>
   DB_USERNAME=<seu-usuario>
   DB_PASSWORD=<sua-senha>
   API_SECRET=<sua-chave-secreta>
   PORT=8080
   ```

5. **Configure Health Check**
   - Health Check Path: `/actuator/health`

6. **Deploy**
   - Clique em "Create Web Service"
   - Aguarde o build e deploy (pode levar alguns minutos)

### Opção 2: Deploy via Blueprint (render.yaml)

1. **Use o arquivo render.yaml**
   - O projeto já inclui um arquivo `render.yaml` configurado

2. **Crie um Blueprint no Render**
   - No dashboard, vá em "Blueprints"
   - Clique em "New Blueprint Instance"
   - Selecione seu repositório
   - O Render detectará automaticamente o `render.yaml`

3. **Ajuste as configurações**
   - Verifique se o nome do banco de dados corresponde ao que você já criou
   - Atualize as variáveis conforme necessário

4. **Apply**
   - Clique em "Apply" para criar os recursos

## 📦 Variáveis de Ambiente Necessárias

| Variável | Descrição | Exemplo |
|----------|-----------|---------|
| `SPRING_PROFILES_ACTIVE` | Profile do Spring Boot | `prod` |
| `DATABASE_URL` | URL de conexão JDBC do PostgreSQL | `jdbc:postgresql://...` |
| `DB_USERNAME` | Usuário do banco de dados | `senai_user` |
| `DB_PASSWORD` | Senha do banco de dados | `sua-senha-segura` |
| `API_SECRET` | Chave secreta para JWT | `uma-chave-muito-secreta` |
| `PORT` | Porta da aplicação | `8080` |

## 🔍 Obtendo a URL do Banco de Dados

1. Acesse seu banco PostgreSQL no Render
2. Vá na aba "Info"
3. Copie as seguintes informações:
   - **Internal Database URL** (formato JDBC)
   - **Username**
   - **Password**

### Formato da DATABASE_URL
```
jdbc:postgresql://dpg-xxxxx.oregon-postgres.render.com:5432/senai_vitrine
```

## ✅ Verificação do Deploy

Após o deploy bem-sucedido:

1. **Health Check**
   ```
   https://sua-app.onrender.com/actuator/health
   ```

2. **Swagger UI**
   ```
   https://sua-app.onrender.com/swagger-ui.html
   ```

3. **API Docs**
   ```
   https://sua-app.onrender.com/api-docs
   ```

## 🐛 Troubleshooting

### Build falhando
- Verifique os logs no Render Dashboard
- Certifique-se de que o Java 17 está configurado
- Verifique se todas as dependências do Maven estão acessíveis

### Erro de conexão com banco
- Verifique se `DATABASE_URL` está no formato JDBC correto
- Confirme que `DB_USERNAME` e `DB_PASSWORD` estão corretos
- Certifique-se de que o banco está na mesma região

### Aplicação não inicia
- Verifique os logs: "Logs" no dashboard do serviço
- Confirme que `SPRING_PROFILES_ACTIVE=prod`
- Verifique se a porta 8080 está exposta

### Timeout no Health Check
- Aumente o timeout nas configurações do serviço
- Verifique se `/actuator/health` está respondendo
- Pode levar alguns minutos para a primeira inicialização

## 🔄 Atualizações e Redeploy

O Render faz redeploy automaticamente quando você:
- Faz push para a branch configurada (main)
- Atualiza variáveis de ambiente (requer redeploy manual)
- Clica em "Manual Deploy" no dashboard

## 📊 Monitoramento

No dashboard do Render você pode:
- Ver logs em tempo real
- Monitorar uso de CPU e memória
- Verificar status do health check
- Ver métricas de requisições

## 🎯 Próximos Passos

Após o deploy:
1. Configure o CORS se necessário
2. Configure domínio customizado (opcional)
3. Configure SSL/TLS (automático no Render)
4. Configure alertas e notificações
5. Documente os endpoints da API

## 📝 Notas Importantes

- ⚠️ Free tier do Render pode ter cold starts (aplicação "dorme" após inatividade)
- 💾 Use PostgreSQL do Render para persistência
- 🔐 Nunca commite senhas ou chaves secretas no código
- 📈 Considere upgrade para tier pago para produção real

## 🆘 Suporte

- [Documentação do Render](https://render.com/docs)
- [Render Community](https://community.render.com)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
