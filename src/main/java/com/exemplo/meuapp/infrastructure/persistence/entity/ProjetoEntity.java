package com.exemplo.meuapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.exemplo.meuapp.domain.enums.ProjetoStatus;
import com.exemplo.meuapp.domain.enums.Visibilidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(length = 100)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(length = 100)
    private String curso;

    @Column(length = 20)
    private String turma;

    @Column(nullable = false)
    private boolean labMaker;

    @Column(nullable = false)
    private boolean participouSaga;

    @Column(nullable = false)
    private boolean itinerario;

    @ManyToOne
    @JoinColumn(name = "unidade_curricular_uuid")
    private UnidadeCurricularEntity unidadeCurricular;

    @ManyToOne
    @JoinColumn(name = "lider_projeto_uuid")
    private AlunosEntity liderProjeto;

    @Column(length = 255)
    private String bannerUrl;

    @Column(length = 20)
    private String codigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Visibilidade visibilidadeCodigo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Visibilidade visibilidadeAnexos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProjetoStatus status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
