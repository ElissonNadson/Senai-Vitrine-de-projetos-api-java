package com.exemplo.meuapp.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller simples para testar o Swagger/OpenAPI
 * 
 * @author Time SENAI
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/teste")
@Tag(name = "🧪 Teste", description = "Endpoints de teste para verificar o funcionamento da API e documentação Swagger")
@Slf4j
public class TesteController {

    @Operation(
        summary = "❤️ Health Check",
        description = """
                Endpoint simples para verificar se a API está funcionando corretamente.
                
                **Retorna:**
                - Status da aplicação
                - Timestamp atual  
                - Versão da API
                - Ambiente de execução
                - Status do Swagger
                """,
        tags = {"Monitoramento", "Saúde"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "API funcionando normalmente",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                        {
                            "status": "UP",
                            "timestamp": "2025-01-27T10:30:45.123",
                            "version": "1.0.0",
                            "environment": "development",
                            "swagger": "enabled"
                        }
                        """
                )
            )
        )
    })
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        log.info("Health check executado");
        
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now(),
            "version", "1.0.0",
            "environment", "development",
            "swagger", "enabled"
        ));
    }

    @Operation(
        summary = "📋 Listar projetos de exemplo",
        description = """
                Retorna uma lista de projetos de exemplo para demonstrar a API.
                
                **Projetos incluem:**
                - Sistema de Gestão SENAI (Java/Spring Boot)
                - App Mobile Biblioteca (React Native)
                """,
        tags = {"Exemplos", "Projetos"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Lista de projetos retornada com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                        [
                            {
                                "id": 1,
                                "nome": "Sistema de Gestão SENAI",
                                "status": "PUBLICADO",
                                "tecnologias": ["Java", "Spring Boot", "PostgreSQL"]
                            },
                            {
                                "id": 2,
                                "nome": "App Mobile Biblioteca",
                                "status": "EM_DESENVOLVIMENTO",
                                "tecnologias": ["React Native", "Node.js", "MongoDB"]
                            }
                        ]
                        """
                )
            )
        )
    })
    @GetMapping("/projetos")
    public ResponseEntity<List<Map<String, Object>>> listarProjetos() {
        log.info("Listando projetos de exemplo");
        
        List<Map<String, Object>> projetos = List.of(
            Map.of(
                "id", 1,
                "nome", "Sistema de Gestão SENAI",
                "status", "PUBLICADO",
                "tecnologias", List.of("Java", "Spring Boot", "PostgreSQL")
            ),
            Map.of(
                "id", 2,
                "nome", "App Mobile Biblioteca",
                "status", "EM_DESENVOLVIMENTO", 
                "tecnologias", List.of("React Native", "Node.js", "MongoDB")
            )
        );
        
        return ResponseEntity.ok(projetos);
    }

    @Operation(
        summary = "📝 Echo de mensagem", 
        description = """
                Retorna a mensagem enviada no corpo da requisição.
                
                **Útil para:**
                - Testar conectividade
                - Validar formato de dados
                - Debug de requests
                """,
        tags = {"Teste", "Debug"}
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Mensagem ecoada com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                        {
                            "echo": {
                                "mensagem": "Olá SENAI!",
                                "usuario": "João"
                            },
                            "timestamp": "2025-01-27T10:30:45.123",
                            "message": "Mensagem recebida com sucesso!"
                        }
                        """
                )
            )
        )
    })
    @PostMapping("/echo")
    public ResponseEntity<Map<String, Object>> echo(@RequestBody Map<String, Object> request) {
        log.info("Echo recebido: {}", request);
        
        return ResponseEntity.ok(Map.of(
            "echo", request,
            "timestamp", LocalDateTime.now(),
            "message", "Mensagem recebida com sucesso!"
        ));
    }
}
