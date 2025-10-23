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
public class DisciplinaProjeto {

    private UUID uuid;
    private Disciplina disciplina;
    private Projeto projeto;

    public DisciplinaProjeto correct() {
        if (disciplina == null) {
            throw new DadosInvalidosException("Disciplina não pode ser nula");
        }
        if (projeto == null) {
            throw new DadosInvalidosException("Projeto não pode ser nulo");
        }
        return this;
    }
}
