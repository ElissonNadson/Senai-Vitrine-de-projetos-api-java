package com.exemplo.meuapp.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.exemplo.meuapp.domain.enums.UsuariosStatus;

import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Alunos {

    private UUID uuid;
    private Usuarios usuarios;

    private String matricula;
    private String curso;
    
    private String telefonePessoal;
    private String telefoneProfissional;
    private String linkedin;
    private Endereco endereco;
    private UsuariosStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Alunos correct() {
        if (usuarios == null) {
            throw new DadosInvalidosException("Usuário não pode ser nulo");
        }
        if (matricula == null || matricula.isBlank()) {
            throw new DadosInvalidosException("Matrícula não pode ser nula ou vazia");
        }
        if (curso == null || curso.isBlank()) {
            throw new DadosInvalidosException("Curso não pode ser nulo ou vazio");
        }
        if (telefoneProfissional == null || telefoneProfissional.isBlank()) {
            throw new DadosInvalidosException("Telefone profissional não pode ser nulo ou vazio");
        }
        if (endereco == null) {
            throw new DadosInvalidosException("Endereço não pode ser nulo");
        }
        if (status == null) {
            throw new DadosInvalidosException("Status não pode ser nulo");
        }
        return this;
    }

}
