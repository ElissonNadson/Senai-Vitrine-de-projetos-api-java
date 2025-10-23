package com.exemplo.meuapp.domain.model;

import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Curso {

    private UUID uuid;
    private String nome;
    private String codigo;
    private String descricao;
    private Integer cargaHoraria;
    private String modalidade; // PRESENCIAL, HIBRIDO, EAD
    private String nivel; // TECNICO, SUPERIOR, QUALIFICACAO
    private Boolean ativo;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Curso correct() {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("Nome do curso não pode ser nulo ou vazio");
        }
        if (codigo == null || codigo.isBlank()) {
            throw new DadosInvalidosException("Código do curso não pode ser nulo ou vazio");
        }
        if (cargaHoraria == null || cargaHoraria <= 0) {
            throw new DadosInvalidosException("Carga horária deve ser maior que zero");
        }
        if (modalidade == null || modalidade.isBlank()) {
            throw new DadosInvalidosException("Modalidade não pode ser nula ou vazia");
        }
        if (nivel == null || nivel.isBlank()) {
            throw new DadosInvalidosException("Nível não pode ser nulo ou vazio");
        }
        
        return this;
    }
}
