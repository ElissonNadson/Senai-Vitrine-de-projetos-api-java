package com.exemplo.meuapp.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração avançada do OpenAPI/Swagger para documentação da API SENAI
 * 
 * Esta configuração fornece uma documentação completa da API com:
 * - Metadados da aplicação
 * - Esquemas de segurança (JWT)
 * - Informações de contato e licença
 * - Configuração de servidores
 * 
 * @author Time SENAI
 * @version 2.0
 */
@Configuration
public class SwaggerConfig {
    
    /**
     * Configuração principal do OpenAPI com metadados completos
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(apiInfo())
                .servers(apiServers())
                .addSecurityItem(securityRequirement())
                .components(apiComponents());
    }
    
    /**
     * Informações detalhadas da API
     */
    private Info apiInfo() {
        return new Info()
                .title("🎓 SENAI API - Sistema de Gestão Acadêmica")
                .description("""
                        **API REST para Sistema de Gestão Acadêmica do SENAI**
                        
                        Esta API oferece funcionalidades completas para gerenciamento de:
                        - 👥 **Usuários**: Alunos, Professores e Administradores
                        - 📚 **Projetos**: Criação e acompanhamento de projetos acadêmicos
                        - 🏢 **Unidades Curriculares**: Gestão de disciplinas e conteúdos
                        - 📍 **Endereços**: Localização de estudantes e unidades
                        - 📋 **Etapas de Projeto**: Controle de progresso e entregas
                        - 📎 **Anexos**: Upload e gestão de documentos
                        
                        ### 🔗 Frontend
                        Interface web disponível em: [@dudurtg2/SENAI-SITE](https://github.com/dudurtg2/SENAI-SITE)
                        
                        ### 🏗️ Arquitetura
                        Desenvolvida seguindo **Clean Architecture (Hexagonal Architecture)** com:
                        - **Domain Layer**: Entidades e regras de negócio
                        - **Application Layer**: Casos de uso e portas
                        - **Infrastructure Layer**: Adaptadores externos (BD, APIs)
                        - **Presentation Layer**: Controllers REST
                        
                        ### 🔐 Autenticação
                        API protegida com **JWT tokens**. Use o endpoint de login para obter o token.
                        
                        ### 💾 Banco de Dados
                        - **Desenvolvimento**: H2 Database (em memória)  
                        - **Produção**: PostgreSQL
                        - Console H2: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
                        """)
                .version("v1.0.0")
                .contact(apiContact())
                .license(apiLicense());
    }
    
    /**
     * Informações de contato do time de desenvolvimento
     */
    private Contact apiContact() {
        return new Contact()
                .name("Time SENAI - Desenvolvimento")
                .email("dev@senai.br")
                .url("https://github.com/dudurtg2/SENAI-API");
    }
    
    /**
     * Informações de licença
     */
    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");
    }
    
    /**
     * Configuração dos servidores disponíveis
     */
    private List<Server> apiServers() {
        return List.of(
                new Server()
                        .url("http://localhost:8080")
                        .description("🛠️ Servidor de Desenvolvimento"),
                new Server()
                        .url("https://api.senai.edu.br")
                        .description("🚀 Servidor de Produção")
        );
    }
    
    /**
     * Requisito de segurança padrão para todos os endpoints
     */
    private SecurityRequirement securityRequirement() {
        return new SecurityRequirement().addList("Bearer Authentication");
    }
    
    /**
     * Componentes de segurança e esquemas reutilizáveis
     */
    private Components apiComponents() {
        return new Components()
                .addSecuritySchemes("Bearer Authentication", securityScheme());
    }
    
    /**
     * Esquema de autenticação JWT
     */
    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("""
                        **Autenticação via JWT Token**
                        
                        Para usar endpoints protegidos:
                        1. Faça login no endpoint `/api/v1/auth/login`
                        2. Copie o token retornado
                        3. Clique em "🔒 Authorize" e cole o token
                        4. O token será incluído automaticamente nos headers das requisições
                        
                        **Formato**: `Bearer <seu-jwt-token>`
                        """);
    }
}
