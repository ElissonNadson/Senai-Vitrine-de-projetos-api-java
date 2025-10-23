package com.exemplo.meuapp.presentation.dto;

import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para registro unificado de usuários (compatível com frontend React)
 * 
 * @author Time SENAI
 * @version 1.0
 */
public record RegistroUnificadoDTO(
        @NotBlank(message = "Login/email é obrigatório")
        @Email(message = "Email deve ter formato válido")
        String login,
        
        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String senha,
        
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, message = "Nome deve ter pelo menos 2 caracteres")
        String nome,
        
        @NotNull(message = "Tipo de usuário é obrigatório")
        UsuarioTipo tipo,
        
        @NotNull(message = "Aceite de termos é obrigatório")
        Boolean aceiteTermos
) {}
