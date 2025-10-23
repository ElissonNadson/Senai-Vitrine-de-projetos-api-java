package com.exemplo.meuapp.domain.model;

import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import lombok.*;
import java.util.UUID;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Professores {

    private UUID uuid;
    private Usuarios usuarios;
    
    private String especialidade;
    private String departamento;

    private String telefonePessoal;
    private String telefoneProfissional;
    private String linkedin;
    private Endereco endereco;
    private UsuariosStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Professores correct() {
        if (usuarios == null) {
            throw new DadosInvalidosException("Usuário não pode ser nulo");
        }
        if (especialidade == null || especialidade.isBlank()) {
            throw new DadosInvalidosException("Especialidade não pode ser nula ou vazia");
        }
        if (departamento == null || departamento.isBlank()) {
            throw new DadosInvalidosException("Departamento não pode ser nulo ou vazio");
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
        if (especialidade.length() > 100) {
            throw new DadosInvalidosException("Especialidade excede o tamanho máximo permitido.");
        }
        if (departamento.length() > 100) {
            throw new DadosInvalidosException("Departamento excede o tamanho máximo permitido.");
        }
        if (linkedin != null && linkedin.length() > 200) {
            throw new DadosInvalidosException("Linkedin excede o tamanho máximo permitido.");
        }

        return this;
    }
}
