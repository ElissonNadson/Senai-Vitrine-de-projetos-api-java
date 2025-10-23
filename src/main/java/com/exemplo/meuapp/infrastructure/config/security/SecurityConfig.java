package com.exemplo.meuapp.infrastructure.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.common.mapper.UsuariosMapper;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    private UsuariosMapper usuariosMapper;
    private JwtTokenProvider jwtTokenProvider;
    private CriarUsuariosUseCase criarUsuariosUseCase;

    @Autowired
    public SecurityConfig(
            EncontrarUsuariosUseCase encontrarUsuariosUseCase,
            UsuariosMapper usuariosMapper,
            JwtTokenProvider jwtTokenProvider,
            CriarUsuariosUseCase criarUsuariosUseCase) {
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
        this.usuariosMapper = usuariosMapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.criarUsuariosUseCase = criarUsuariosUseCase;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {        
        
        http
                // 🔒 Desabilitar CSRF para APIs REST
                .csrf(csrf -> csrf.disable())
                
                // 🖼️ Permitir frames para console H2
                .headers(headers -> headers.frameOptions().sameOrigin())
                
                // 🌐 Habilitar CORS
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOriginPatterns(java.util.Arrays.asList("*"));
                    corsConfig.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                    corsConfig.setAllowedHeaders(java.util.Arrays.asList("*"));
                    corsConfig.setAllowCredentials(true);
                    corsConfig.setMaxAge(3600L);
                    return corsConfig;
                }))
                
                // 📊 Configurar sessões como stateless (JWT)
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)                ).authorizeHttpRequests(auth -> auth
                        // 🔓 Endpoints públicos de autenticação
                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/oauth2/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/user/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/user/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/user/auth/register")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login/oauth2/code/google")).permitAll()
                        
                        // 📚 Endpoints públicos do Swagger/OpenAPI
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api-docs")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v3/api-docs")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/swagger-resources/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                        
                        // 🧪 Endpoints de demonstração (públicos)
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/teste/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/demo/**")).permitAll()
                        
                        // 💾 Console H2 Database (apenas em desenvolvimento)
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        
                        // 👁️ Endpoints públicos para visitantes (limitados)
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/senai/projeto/findAll")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/senai/projeto/findByUUID/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/senai/disciplina/findAll")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/senai/unidadeCurricular/findAll")).permitAll()
                        
                        // 🌐 Novos endpoints públicos para visitantes
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/dashboard/visitor")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/dashboard/public/**")).permitAll()
                        
                        // 🔒 Endpoints que precisam de autenticação
                        .requestMatchers(new AntPathRequestMatcher("/api/user/refresh-token")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/user/update")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/admin/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/dashboard/professor")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/dashboard/aluno")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/api/v1/senai/**")).authenticated()
                        
                        // 🔒 Todos os outros precisam de autenticação
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
