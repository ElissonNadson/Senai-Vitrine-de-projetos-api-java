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
public class ProjetoAluno {

    private UUID uuid;
    private Projeto projeto;
    private Alunos aluno;

    public ProjetoAluno correct() {
        if (projeto == null) {
            throw new DadosInvalidosException("Projeto não pode ser nulo");
        }
        if (aluno == null) {
            throw new DadosInvalidosException("Aluno não pode ser nulo");
        }
        return this;
    }
}
