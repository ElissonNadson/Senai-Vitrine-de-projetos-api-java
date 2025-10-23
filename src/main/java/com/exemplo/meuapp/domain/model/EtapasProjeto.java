package com.exemplo.meuapp.domain.model;

import com.exemplo.meuapp.domain.enums.EtapaStatus;
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
public class EtapasProjeto {

    private UUID uuid;
    private Projeto projeto;
    private String nomeEtapa;
    private String descricao;
    private int ordem;
    private EtapaStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public EtapasProjeto correct() {
        if (projeto == null) {
            throw new DadosInvalidosException("Projeto não pode ser nulo");
        }
        if (nomeEtapa == null || nomeEtapa.isBlank()) {
            throw new DadosInvalidosException("Nome da etapa não pode ser nulo ou vazio");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new DadosInvalidosException("Descrição não pode ser nula ou vazia");
        }
        if (ordem <= 0) {
            throw new DadosInvalidosException("Ordem deve ser maior que zero");
        }
        if (status == null) {
            throw new DadosInvalidosException("Status não pode ser nulo");
        }
        return this;
    }
}
