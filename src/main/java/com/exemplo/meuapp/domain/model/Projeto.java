package com.exemplo.meuapp.domain.model;

import com.exemplo.meuapp.domain.enums.ProjetoStatus;
import com.exemplo.meuapp.domain.enums.Visibilidade;
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
public class Projeto {

    private UUID uuid;
    private String titulo;
    private String descricao;
    private String curso;
    private String turma;
    private boolean labMaker;
    private boolean participouSaga;
    private boolean itinerario;
    private UnidadeCurricular unidadeCurricular;
    private Alunos liderProjeto;
    private String bannerUrl;
    private String codigo;
    private Visibilidade visibilidadeCodigo;
    private Visibilidade visibilidadeAnexos;
    private ProjetoStatus status;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public Projeto correct() {
        if (titulo == null || titulo.isBlank()) {
            throw new DadosInvalidosException("Título não pode ser nulo ou vazio");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new DadosInvalidosException("Descrição não pode ser nula ou vazia");
        }
        if (curso == null || curso.isBlank()) {
            throw new DadosInvalidosException("Curso não pode ser nulo ou vazio");
        }
        if (turma == null || turma.isBlank()) {
            throw new DadosInvalidosException("Turma não pode ser nula ou vazia");
        }
        if (unidadeCurricular == null) {
            throw new DadosInvalidosException("Unidade Curricular não pode ser nula");
        }
        if (liderProjeto == null) {
            throw new DadosInvalidosException("Líder do projeto não pode ser nulo");
        }
        if (status == null) {
            throw new DadosInvalidosException("Status não pode ser nulo");
        }
        if (getTitulo().length() > 200) {
            throw new DadosInvalidosException("Título excede o tamanho máximo permitido.");
        }
        if (getDescricao().length() > 2000) {
            throw new DadosInvalidosException("Descrição excede o tamanho máximo permitido.");
        }
        if (getCurso().length() > 100) {
            throw new DadosInvalidosException("Curso excede o tamanho máximo permitido.");
        }
        if (getTurma().length() > 50) {
            throw new DadosInvalidosException("Turma excede o tamanho máximo permitido.");
        }
        if (getBannerUrl() != null && getBannerUrl().length() > 300) {
            throw new DadosInvalidosException("URL do banner excede o tamanho máximo permitido.");
        }
        if (getCodigo() != null && getCodigo().length() > 50) {
            throw new DadosInvalidosException("Código excede o tamanho máximo permitido.");
        }
        return this;
    }
}
