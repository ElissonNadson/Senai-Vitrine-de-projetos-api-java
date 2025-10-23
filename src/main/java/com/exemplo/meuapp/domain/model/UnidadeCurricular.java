package com.exemplo.meuapp.domain.model;

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
public class UnidadeCurricular {

    private UUID uuid;
    private String nome;
    private String descricao;
    private String cargaHoraria;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public UnidadeCurricular correct() {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("Nome não pode ser nulo ou vazio");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new DadosInvalidosException("Descrição não pode ser nula ou vazia");
        }
        if (cargaHoraria == null || cargaHoraria.isBlank()) {
            throw new DadosInvalidosException("Carga horária não pode ser nula ou vazia");
        }

        if (nome.length() > 200) {
            throw new DadosInvalidosException("Nome excede o tamanho máximo permitido.");
        }
        if (descricao.length() > 1000) {
            throw new DadosInvalidosException("Descrição excede o tamanho máximo permitido.");
        }
        if (cargaHoraria.length() > 20) {
            throw new DadosInvalidosException("Carga horária excede o tamanho máximo permitido.");
        }
        return this;
    }
}
