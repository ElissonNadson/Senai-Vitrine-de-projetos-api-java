package com.exemplo.meuapp.presentation.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.meuapp.application.port.in.usuarios.AtualizarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.common.mapper.UsuariosMapper;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Usuarios;
import com.exemplo.meuapp.infrastructure.config.security.JwtTokenProvider;
import com.exemplo.meuapp.infrastructure.webclient.CollectEmailForTokenService;
import com.exemplo.meuapp.presentation.dto.AuthorizationDTO;
import com.exemplo.meuapp.presentation.dto.NovoPerfil;
import com.exemplo.meuapp.presentation.dto.PerfilUsuario;
import com.exemplo.meuapp.presentation.dto.RegistroUnificadoDTO;
import com.exemplo.meuapp.presentation.dto.TokenUpdateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
@Tag(
    name = "🔐 Autenticação", 
    description = "Endpoints para gerenciamento de usuários, login, registro e autenticação JWT"
)
public class UsuariosController {

    private final CriarUsuariosUseCase criarUsuariosUseCase;
    private final EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    private final AtualizarUsuariosUseCase atualizarUsuariosUseCase;

    private final UsuariosMapper usuariosMapper;
    private final AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;
    private CollectEmailForTokenService collectEmailForTokenService;

    @Autowired
    public UsuariosController(CriarUsuariosUseCase criarUsuariosUseCase,
                              EncontrarUsuariosUseCase encontrarUsuariosUseCase,
                              UsuariosMapper usuariosMapper,
                              AuthenticationManager authenticationManager,
                              JwtTokenProvider jwtTokenProvider,
                              CollectEmailForTokenService collectEmailForTokenService,
                              AtualizarUsuariosUseCase atualizarUsuariosUseCase) {
        this.criarUsuariosUseCase = criarUsuariosUseCase;
        this.atualizarUsuariosUseCase = atualizarUsuariosUseCase;
        this.collectEmailForTokenService = collectEmailForTokenService;
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
        this.usuariosMapper = usuariosMapper;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }    @PostMapping("/login")
    @Operation(
        summary = "🔐 Realizar login com email e senha",
        description = """
            **Autentica um usuário no sistema usando email e senha.**
            
            **Como usar:**
            1. Primeiro cadastre um usuário em `/api/user/register`
            2. Use o email e senha para fazer login
            3. Receba os tokens JWT para autenticação
            
            **Resposta de sucesso:** Tokens JWT (access + refresh) e dados do usuário
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "✅ Login realizado com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                    {
                      "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                      "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                      "user": {
                        "uuid": "123e4567-e89b-12d3-a456-426614174000",
                        "email": "usuario@senai.br",
                        "nome": "usuario.teste",
                        "tipo": "ALUNO"
                      }
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "❌ Credenciais inválidas",
            content = @Content(
                examples = @ExampleObject(
                    value = "\"Credenciais inválidas ou autenticação falhou.\""
                )
            )
        )
    })
    public ResponseEntity<?> login(
        @Parameter(
            description = "Credenciais de login (email e senha)",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de login",
                    value = """
                    {
                      "login": "usuario@senai.br",
                      "senha": "123456"
                    }
                    """
                )
            )        )
        @RequestBody AuthorizationDTO data
    ) {
        try {
            System.out.println("🔐 LOGIN ATTEMPT:");
            System.out.println("   - Email: " + data.login());
            System.out.println("   - Senha (primeira parte): " + data.senha().substring(0, Math.min(5, data.senha().length())) + "...");
            
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.login(), data.senha())
            );
            
            System.out.println("✅ Autenticação bem-sucedida!");
            
            return ResponseEntity.ok(
                    jwtTokenProvider.generateTokens(
                    
                                    encontrarUsuariosUseCase.buscarPorEmail(
                                            data.login()
                                    )
                            
                    )
            );

        } catch (Exception e) {
            System.out.println("❌ Erro na autenticação: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(401).body("Credenciais inválidas ou autenticação falhou.");
        }
    }    @GetMapping("/login/google")
    @Operation(
        summary = "🔐 Login/Registro via Google OAuth2",
        description = """
            Autentica ou registra um usuário usando conta Google.
            
            **Fluxo:**
            1. Usuário é redirecionado para Google
            2. Após autorização, Google retorna com dados
            3. Sistema verifica se usuário existe:
               - ✅ Existe: Login direto
               - ❌ Não existe: Registro automático
            4. Retorna tokens JWT
            
            **Acesse:** `/api/user/login/google`
            """,
        responses = {
            @ApiResponse(responseCode = "302", description = "🔄 Redirecionamento para frontend com tokens"),
            @ApiResponse(responseCode = "400", description = "❌ Erro na autenticação Google")
        }
    )
    public ResponseEntity<?> loginGoogle(@AuthenticationPrincipal OidcUser user, HttpServletResponse response) {
        try {
            System.out.println("🔐 GOOGLE LOGIN ATTEMPT:");
            System.out.println("   - User: " + (user != null ? user.getEmail() : "null"));
            
            if (user == null) {
                return ResponseEntity.badRequest().body("❌ Usuário Google não autenticado");
            }
            
            String email = user.getEmail();
            String nome = user.getGivenName() + " " + user.getFamilyName();
            String googleId = user.getSubject();
            
            System.out.println("✅ Dados Google recebidos:");
            System.out.println("   - Email: " + email);
            System.out.println("   - Nome: " + nome);
            System.out.println("   - Google ID: " + googleId);
              // Verificar se usuário já existe
            PerfilUsuario perfilExistente = null;
            try {
                perfilExistente = encontrarUsuariosUseCase.buscarPorEmail(email);
                System.out.println("👤 Usuário encontrado no banco: " + (perfilExistente != null));
            } catch (Exception e) {
                System.out.println("❌ Usuário não encontrado no banco");
            }
            
            PerfilUsuario perfil;
            if (perfilExistente != null) {
                // LOGIN: Usuário já existe
                System.out.println("🔑 Fazendo LOGIN de usuário existente");
                perfil = perfilExistente;            } else {
                // REGISTRO: Criar novo usuário
                System.out.println("📝 Fazendo REGISTRO de novo usuário");
                criarUsuarioGoogle(email, nome, googleId);
                // Buscar o perfil completo do usuário recém-criado
                perfil = encontrarUsuariosUseCase.buscarPorEmail(email);
            }
            
            // Gerar tokens JWT
            var tokens = jwtTokenProvider.generateTokens(perfil);
              // Redirecionar para frontend com tokens
            String redirectUrl = String.format(
                "http://localhost:3000/auth/callback?accessToken=%s&refreshToken=%s&email=%s&nome=%s",
                tokens.getAccessToken(),
                tokens.getRefreshToken(),
                email,
                nome.replace(" ", "%20")
            );
            
            response.sendRedirect(redirectUrl);
            return null;
            
        } catch (Exception e) {
            System.out.println("❌ Erro no login Google: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("❌ Erro na autenticação Google: " + e.getMessage());
        }
    }
    
    private Usuarios criarUsuarioGoogle(String email, String nome, String googleId) {
        System.out.println("🆕 Criando novo usuário Google:");
        System.out.println("   - Email: " + email);
        System.out.println("   - Nome: " + nome);
        
        // Criar usuário com dados do Google
        Usuarios novoUsuario = new Usuarios();
        novoUsuario.setEmail(email);
        novoUsuario.setUsuario(nome.toLowerCase().replace(" ", "."));
        novoUsuario.setSenha("GOOGLE_OAUTH"); // Senha placeholder para OAuth
        novoUsuario.setTipo(UsuarioTipo.ALUNO); // Padrão ALUNO
        novoUsuario.setStatus(UsuariosStatus.ATIVO);
        novoUsuario.setCriadoEm(LocalDateTime.now());
        novoUsuario.setAtualizadoEm(LocalDateTime.now());
        
        // Usar o service para criar (que já cria o registro de aluno automaticamente)
        return criarUsuariosUseCase.criar(novoUsuario);
    }
    @GetMapping("/online")
    @Operation(
        summary = "🟢 Verificar status online do usuário",
        description = """
            **Verifica se o usuário está autenticado e online.**
            
            **Funcionalidades:**
            - ✅ Valida token JWT ativo
            - ✅ Retorna dados básicos do usuário
            - ✅ Health check da sessão
            - ✅ Status da conta
            
            **Headers necessários:**
            - `Authorization: Bearer <token>`
            
            **Uso típico:** Verificar se usuário ainda está logado antes de operações sensíveis.
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Usuário online e autenticado",
                content = @Content(
                    examples = @ExampleObject(
                        value = """
                        {
                          "status": "online",
                          "message": "✅ Usuário autenticado",
                          "user": {
                            "uuid": "123e4567-e89b-12d3-a456-426614174000",
                            "email": "usuario@senai.br",
                            "nome": "João Silva",
                            "tipo": "ALUNO",
                            "status": "ATIVO",
                            "lastActivity": "2025-06-19T05:30:00"
                          },
                          "session": {
                            "tokenValid": true,
                            "expiresIn": "23h 45m"
                          }
                        }
                        """
                    )
                )
            ),
            @ApiResponse(responseCode = "401", description = "❌ Token inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "❌ Usuário não autorizado")
        }
    )
    public ResponseEntity<?> online(HttpServletRequest request) {
        try {
            String email = collectEmailForTokenService.execute(request);
            if (email == null) {
                return ResponseEntity.status(401).body(Map.of(
                    "status", "offline",
                    "message", "❌ Token não fornecido ou inválido"
                ));
            }
            
            PerfilUsuario user = encontrarUsuariosUseCase.buscarPorEmail(email);
            
            return ResponseEntity.ok(Map.of(
                "status", "online",
                "message", "✅ Usuário autenticado",                "user", Map.of(
                    "uuid", user.uuid(),
                    "email", user.email(),
                    "nome", user.nome(),
                    "tipo", user.tipo(),
                    "status", user.status(),
                    "lastActivity", java.time.LocalDateTime.now()
                ),
                "session", Map.of(
                    "tokenValid", true,
                    "timestamp", java.time.LocalDateTime.now()
                )
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of(
                "status", "offline",
                "message", "❌ Erro na autenticação: " + e.getMessage()
            ));
        }
    }
    @PostMapping("/refresh-token")
    @Operation(
        summary = "🔄 Renovar token de acesso",
        description = """
            **Renova o token de acesso usando o refresh token.**
            
            **Quando usar:**
            - Quando o access token expira (erro 401)
            - Para manter o usuário logado sem precisar fazer login novamente
            
            **Como funciona:**
            1. Envie o refresh token recebido no login
            2. Receba um novo access token válido
            3. Continue usando a API normalmente
            
            **Segurança:** Este endpoint requer autenticação prévia
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "✅ Token renovado com sucesso",
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "401", 
            description = "❌ Refresh token inválido ou expirado"
        )
    })
    public ResponseEntity<?> refreshToken(
        @Parameter(
            description = "Refresh token para renovação",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                    }
                    """
                )
            )
        )
        @RequestBody TokenUpdateDTO refreshTokenRequest
    ) {

        String login = jwtTokenProvider.validateRefreshToken(refreshTokenRequest.token());
        if (login == null) {
            return ResponseEntity.status(401).body("Refresh Token inválido ou expirado.");
        }

        PerfilUsuario user = encontrarUsuariosUseCase.buscarPorEmail(login);

        return ResponseEntity
                .ok(new TokenUpdateDTO(jwtTokenProvider.generateAccessToken(user)));
    }    @PutMapping("/update")
    @Operation(
        summary = "✏️ Atualizar dados do perfil do usuário",
        description = """
            **Atualiza as informações do perfil do usuário autenticado.**
            
            **Funcionalidades:**
            - ✅ Atualiza dados pessoais
            - ✅ Valida token JWT automaticamente
            - ✅ Busca usuário pelo token
            - ✅ Preserva dados não informados
            
            **Headers necessários:**
            - `Authorization: Bearer <token>`
            
            **Campos atualizáveis:**
            - `usuario`: Nome de usuário
            - `email`: Email (se único)
            - `senha`: Nova senha
            - `status`: Status da conta
            - `tipo`: Tipo de usuário (apenas admin)
            
            **Segurança:** Apenas o próprio usuário pode atualizar seus dados.
            """,
        responses = {
            @ApiResponse(
                responseCode = "200", 
                description = "✅ Dados atualizados com sucesso",
                content = @Content(
                    examples = @ExampleObject(
                        value = """
                        {
                          "message": "✅ Perfil atualizado com sucesso!",
                          "usuario": {
                            "uuid": "123e4567-e89b-12d3-a456-426614174000",
                            "usuario": "joao.silva.updated",
                            "email": "joao.updated@senai.br",
                            "tipo": "ALUNO",
                            "status": "ATIVO",
                            "atualizadoEm": "2025-06-19T05:30:00"
                          }
                        }
                        """
                    )
                )
            ),
            @ApiResponse(responseCode = "400", description = "❌ Dados inválidos"),
            @ApiResponse(responseCode = "401", description = "❌ Token inválido ou expirado"),
            @ApiResponse(responseCode = "404", description = "❌ Usuário não encontrado")
        }
    )
    public ResponseEntity<?> update(
        @Parameter(
            description = "Novos dados do usuário",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de atualização",
                    value = """
                    {
                      "usuario": "joao.silva.updated",
                      "email": "joao.updated@senai.br",
                      "senha": "NovaSenha123!",
                      "status": "ATIVO"
                    }
                    """
                )
            )
        )
        @RequestBody NovoPerfil user, 
        HttpServletRequest request
    ) {
        try {
            String email = collectEmailForTokenService.execute(request);
            if (email == null) {
                return ResponseEntity.status(401).body(Map.of(
                    "error", "❌ Token não fornecido ou inválido",
                    "message", "Faça login novamente para continuar."
                ));
            }
            
            UUID uuid = encontrarUsuariosUseCase.buscarPorEmailUser(email).getUuid();
            Usuarios updatedUser = atualizarUsuariosUseCase.atualizar(uuid, usuariosMapper.toDomain(user));
            
            return ResponseEntity.ok(Map.of(
                "message", "✅ Perfil atualizado com sucesso!",
                "usuario", Map.of(
                    "uuid", updatedUser.getUuid(),
                    "usuario", updatedUser.getUsuario(),
                    "email", updatedUser.getEmail(),
                    "tipo", updatedUser.getTipo(),
                    "status", updatedUser.getStatus(),
                    "atualizadoEm", updatedUser.getAtualizadoEm()
                )
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "❌ Erro ao atualizar usuário",
                "message", e.getMessage(),
                "details", "Verifique se todos os dados estão corretos e tente novamente."
            ));
        }
    }
    
    // COMENTADO PARA EVITAR CONFLITO COM /auth/register
    // @PostMapping("/register")
    @Operation(
        summary = "➕ Cadastrar novo usuário",
        description = """
            **Cria uma nova conta de usuário no sistema.**
            
            **Campos obrigatórios:**
            - `usuario`: Nome de usuário único
            - `email`: Email válido e único
            - `senha`: Senha segura (mínimo 6 caracteres)
            - `status`: Status da conta (ATIVO/INATIVO)
            
            **Tipos de usuário:** ALUNO, PROFESSOR, ADMIN
            
            **Após o cadastro:** Use `/api/user/login` para fazer login
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "✅ Usuário cadastrado com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                    {
                      "message": "✅ Usuário cadastrado com sucesso!",
                      "usuario": {
                        "uuid": "123e4567-e89b-12d3-a456-426614174000",
                        "nome": "joao.silva",
                        "email": "joao@senai.br",
                        "tipo": "ALUNO",
                        "status": "ATIVO",
                        "criadoEm": "2025-06-19T00:47:30.123456"
                      },
                      "instruction": "Use /api/user/login para fazer login com as credenciais cadastradas."
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "❌ Erro nos dados fornecidos",
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "error": "❌ Erro ao cadastrar usuário",
                      "message": "Email já está em uso",
                      "details": "Verifique se o email já não está em uso ou se todos os campos obrigatórios foram preenchidos."
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<?> register(
        @Parameter(
            description = "Dados do novo usuário",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de cadastro",
                    value = """
                    {
                      "usuario": "joao.silva",
                      "email": "joao@senai.br",
                      "senha": "minhasenha123",
                      "status": "ATIVO"
                    }
                    """
                )
            )
        )
        @RequestBody NovoPerfil novoUsuario
    ) {
        try {
            // Criar novo usuário usando o Use Case
            Usuarios usuarioCriado = criarUsuariosUseCase.criar(usuariosMapper.toDomain(novoUsuario));
              // Retornar resposta de sucesso com dados do usuário (sem senha)
            return ResponseEntity.status(201).body(Map.of(
                "message", "✅ Usuário cadastrado com sucesso!",
                "usuario", Map.of(
                    "uuid", usuarioCriado.getUuid(),
                    "nome", usuarioCriado.getUsuario(),
                    "email", usuarioCriado.getEmail(),
                    "tipo", usuarioCriado.getTipo(),
                    "status", usuarioCriado.getStatus(),
                    "criadoEm", usuarioCriado.getCriadoEm()
                ),
                "instruction", "Use /api/user/login para fazer login com as credenciais cadastradas."
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of(
                "error", "❌ Erro ao cadastrar usuário",
                "message", e.getMessage(),
                "details", "Verifique se o email já não está em uso ou se todos os campos obrigatórios foram preenchidos."
            ));        }
    }

    @PostMapping("/auth/register")
    @Operation(
        summary = "🆕 Registro unificado (compatível com frontend React)",
        description = """
            **Endpoint moderno para registro de usuários integrado com o frontend React.**
            
            **Melhorias:**
            - ✅ Validação de aceite de termos obrigatória
            - ✅ Status definido automaticamente como ATIVO
            - ✅ Campos unificados (login = email = usuario)
            - ✅ Validação de tipo de usuário (ALUNO/PROFESSOR)
            - ✅ Compatível com estrutura do frontend
            
            **Validações implementadas:**
            - Email único no sistema
            - Senha mínima de 6 caracteres
            - Aceite de termos obrigatório
            - Nome com mínimo 2 caracteres
            """
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201", 
            description = "✅ Usuário registrado com sucesso",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = """
                    {
                      "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                      "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                      "usuariosEntity": {
                        "uuid": "123e4567-e89b-12d3-a456-426614174000",
                        "usuario": "maria@senai.br",
                        "email": "maria@senai.br",
                        "tipo": "ALUNO",
                        "status": "ATIVO",
                        "aceiteTermos": true,
                        "criadoEm": "2025-06-22T10:30:00"
                      }
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "❌ Dados inválidos ou aceite de termos não confirmado",
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "error": "❌ Erro na validação",
                      "message": "É obrigatório aceitar os termos de uso para se cadastrar",
                      "details": "Verifique todos os campos obrigatórios"
                    }
                    """
                )
            )
        ),
        @ApiResponse(
            responseCode = "409", 
            description = "❌ Email já está em uso",
            content = @Content(
                examples = @ExampleObject(
                    value = """
                    {
                      "error": "❌ Conflito de dados",
                      "message": "Este email já está cadastrado no sistema",
                      "details": "Tente fazer login ou use outro email"
                    }
                    """
                )
            )
        )
    })
    public ResponseEntity<?> registerAuth(
        @Parameter(
            description = "Dados para registro unificado",
            required = true,
            content = @Content(
                examples = @ExampleObject(
                    name = "Exemplo de registro",
                    value = """
                    {
                      "login": "maria.silva@senai.br",
                      "senha": "senai123",
                      "nome": "Maria Silva",
                      "tipo": "ALUNO",
                      "aceiteTermos": true
                    }
                    """
                )
            )
        )
        @RequestBody RegistroUnificadoDTO registroData
    ) {
        try {
            System.out.println("🆕 REGISTRO UNIFICADO ATTEMPT:");
            System.out.println("   - Email: " + registroData.login());
            System.out.println("   - Nome: " + registroData.nome());
            System.out.println("   - Tipo: " + registroData.tipo());
            System.out.println("   - Aceite de Termos: " + registroData.aceiteTermos());
            
            // Validação obrigatória do aceite de termos
            if (!registroData.aceiteTermos()) {
                return ResponseEntity.status(400).body(Map.of(
                    "error", "❌ Erro na validação",
                    "message", "É obrigatório aceitar os termos de uso para se cadastrar",
                    "details", "Marque a opção de aceite dos termos e tente novamente"
                ));
            }
            
            // Verificar se email já existe
            try {
                encontrarUsuariosUseCase.buscarPorEmail(registroData.login());
                return ResponseEntity.status(409).body(Map.of(
                    "error", "❌ Conflito de dados",
                    "message", "Este email já está cadastrado no sistema",
                    "details", "Tente fazer login ou use outro email"
                ));
            } catch (Exception e) {
                // Email não existe, pode prosseguir
            }
              // Criar novo usuário com valores obrigatórios garantidos
            Usuarios novoUsuario = Usuarios.builder()
                .usuario(registroData.login())  // Usuario = email
                .email(registroData.login())    // Email = login
                .senha(registroData.senha())    // Senha será criptografada no Use Case
                .tipo(registroData.tipo())
                .status(UsuariosStatus.ATIVO)   // Status definido explicitamente
                .aceiteTermos(registroData.aceiteTermos() != null ? registroData.aceiteTermos() : Boolean.FALSE)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();
            
            // Log para debug
            System.out.println("🔧 Dados do usuário a ser criado:");
            System.out.println("   - Status: " + novoUsuario.getStatus());
            System.out.println("   - AceiteTermos: " + novoUsuario.getAceiteTermos());
            System.out.println("   - Tipo: " + novoUsuario.getTipo());
            
            // Criar usuário usando o Use Case
            Usuarios usuarioCriado = criarUsuariosUseCase.criar(novoUsuario);
            System.out.println("✅ Usuário criado com sucesso: " + usuarioCriado.getUuid());            // Gerar resposta com dados do usuário (sem senha) e sucesso
            return ResponseEntity.status(201).body(Map.of(
                "message", "✅ Usuário registrado com sucesso!",
                "usuariosEntity", Map.of(
                    "uuid", usuarioCriado.getUuid(),
                    "usuario", usuarioCriado.getUsuario(),
                    "email", usuarioCriado.getEmail(),
                    "tipo", usuarioCriado.getTipo(),
                    "status", usuarioCriado.getStatus(),
                    "aceiteTermos", usuarioCriado.getAceiteTermos(),
                    "criadoEm", usuarioCriado.getCriadoEm()
                ),
                "instruction", "✅ Conta criada! Use /api/user/login para fazer login."
            ));
            
        } catch (Exception e) {
            System.out.println("❌ Erro no registro unificado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            e.printStackTrace();
            
            return ResponseEntity.status(400).body(Map.of(
                "error", "❌ Erro ao criar conta",
                "message", e.getMessage(),
                "details", "Verifique os dados fornecidos e tente novamente"
            ));
        }
    }

}