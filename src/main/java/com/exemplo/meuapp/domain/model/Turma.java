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
public class Turma {

    private UUID uuid;
    private String nome;
    private String codigo;
    private Curso curso;
    private String semestre;
    private Integer ano;
    private String periodo; // MANHA, TARDE, NOITE, INTEGRAL
    private Integer vagas;
    private Integer vagasOcupadas;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Boolean ativa;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Turma correct() {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("Nome da turma não pode ser nulo ou vazio");
        }
        if (codigo == null || codigo.isBlank()) {
            throw new DadosInvalidosException("Código da turma não pode ser nulo ou vazio");
        }
        if (curso == null) {
            throw new DadosInvalidosException("Curso não pode ser nulo");
        }
        if (ano == null || ano < 2020) {
            throw new DadosInvalidosException("Ano deve ser válido");
        }
        if (periodo == null || periodo.isBlank()) {
            throw new DadosInvalidosException("Período não pode ser nulo ou vazio");
        }
        if (vagas == null || vagas <= 0) {
            throw new DadosInvalidosException("Número de vagas deve ser maior que zero");
        }
        if (vagasOcupadas == null) {
            vagasOcupadas = 0;
        }
        if (vagasOcupadas > vagas) {
            throw new DadosInvalidosException("Vagas ocupadas não pode ser maior que o total de vagas");
        }
        
        return this;
    }
}
