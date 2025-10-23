package com.exemplo.meuapp.infrastructure.config.security;
import java.io.IOException;

import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.common.mapper.UsuariosMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.UsuariosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter {

    private EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    private JwtTokenProvider jwtTokenProvider;
    private UsuariosMapper usuariosMapper;

    @Autowired
    public JwtAuthenticationFilter(
            EncontrarUsuariosUseCase encontrarUsuariosUseCase,
            JwtTokenProvider jwtTokenProvider,
            UsuariosMapper usuariosMapper) {
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuariosMapper = usuariosMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var subject = jwtTokenProvider.validateToken(token, "access");
            UserDetails userDetails = usuariosMapper.toEntity(encontrarUsuariosUseCase.buscarPorEmailUser(subject));

            if (userDetails != null) {
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
