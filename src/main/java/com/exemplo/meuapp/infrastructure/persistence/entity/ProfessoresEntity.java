package com.exemplo.meuapp.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.exemplo.meuapp.domain.enums.UsuariosStatus;

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
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "usuarios_uuid")
    private UsuariosEntity usuarios;

    @Column(length = 100)
    private String especialidade;

    @Column(length = 100)
    private String departamento;

    @Column(length = 20)
    private String telefonePessoal;

    @Column(length = 20)
    private String telefoneProfissional;

    @Column(length = 255)
    private String linkedin;

    @ManyToOne
    @JoinColumn(name = "endereco_uuid")
    private EnderecoEntity endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuariosStatus status;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
