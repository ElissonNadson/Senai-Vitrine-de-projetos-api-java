package com.exemplo.meuapp.domain.model;

import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ProjetoProfessor {

    private UUID uuid;
    private Projeto projeto;
    private Professores professor;
    private Boolean isOrientador;

    public ProjetoProfessor correct() {
        if (projeto == null) {
            throw new DadosInvalidosException("Projeto não pode ser nulo");
        }
        if (professor == null) {
            throw new DadosInvalidosException("Professor não pode ser nulo");
        }
        if(isOrientador == null) {
            throw new DadosInvalidosException("isOrientador não pode ser nulo");
        }
        return this;
    }
}
