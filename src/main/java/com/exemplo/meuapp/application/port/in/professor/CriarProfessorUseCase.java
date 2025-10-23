package com.exemplo.meuapp.application.port.in.professor;

import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import com.exemplo.meuapp.application.port.out.UsuariosGateways;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Professores;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class CriarProfessorUseCase {
    private ProfessoresGateways professoresGateways;
    @Autowired
    private UsuariosGateways usuariosGateways;

    public CriarProfessorUseCase(ProfessoresGateways professoresGateways) {
        this.professoresGateways = professoresGateways;
    }

    public Professores criar(Professores professores) {
        var usuarioCompleto = usuariosGateways.getUsuarios(professores.getUsuarios().getUuid());

        if (usuarioCompleto == null) {
            throw new RegraDeNegocioException("Usuário não encontrado.");
        }
        professores.setUsuarios(usuarioCompleto);

        professores.correct();

        if (professores.getUsuarios().getStatus() == null
                || !professores.getUsuarios().getStatus().name().equals("ATIVO")) {
            throw new RegraDeNegocioException("Usuário associado ao professor está inativo ou não informado.");
        }

        if (professoresGateways.existsByUsuariosId(professores.getUsuarios().getUuid())) {
            throw new RegraDeNegocioException("Já existe um professor cadastrado para este usuário.");
        }

        if (professores.getTelefoneProfissional() != null &&
                !professores.getTelefoneProfissional().matches("^\\d{10,15}$")) {
            throw new DadosInvalidosException("Telefone profissional inválido.");
        }
        if (professores.getTelefonePessoal() != null &&
                !professores.getTelefonePessoal().isBlank() &&
                !professores.getTelefonePessoal().matches("^\\d{10,15}$")) {
            throw new DadosInvalidosException("Telefone pessoal inválido.");
        }

        if (professores.getLinkedin() != null && !professores.getLinkedin().isBlank()) {
            if (!professores.getLinkedin().startsWith("https://") || !professores.getLinkedin().contains("linkedin.com")) {
                throw new DadosInvalidosException("URL do LinkedIn inválida.");
            }
        }

        professores.setCriadoEm(LocalDateTime.now());
        professores.setAtualizadoEm(LocalDateTime.now());
        professores.getUsuarios().setTipo(UsuarioTipo.PROFESSOR);
        return professoresGateways.save(professores);
    }
}