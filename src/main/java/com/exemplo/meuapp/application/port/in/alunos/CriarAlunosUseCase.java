package com.exemplo.meuapp.application.port.in.alunos;

import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Alunos;

import java.time.LocalDateTime;

public class CriarAlunosUseCase {

    private AlunosGateways alunosGateways;

    public CriarAlunosUseCase(AlunosGateways alunosGateways) {
        this.alunosGateways = alunosGateways;
    }

    public Alunos criar(Alunos alunos) {
        alunos.setCriadoEm(LocalDateTime.now());
        alunos.setAtualizadoEm(LocalDateTime.now());
        alunos.setStatus(UsuariosStatus.ATIVO);
        alunos.correct();


        String email = alunos.getUsuarios().getEmail();
        if (alunosGateways.existsByEmail(email)) {
            throw new RegraDeNegocioException("Já existe um aluno com este e-mail.");
        }


        if (alunosGateways.existsByMatricula(alunos.getMatricula())) {
            throw new RegraDeNegocioException("Já existe um aluno com esta matrícula.");
        }


        if (alunos.getTelefonePessoal() != null && alunos.getTelefonePessoal().replaceAll("\\D", "").length() < 10) {
            throw new DadosInvalidosException("Telefone pessoal inválido.");
        }
        if (alunos.getTelefoneProfissional() != null && alunos.getTelefoneProfissional().replaceAll("\\D", "").length() < 10) {
            throw new DadosInvalidosException("Telefone profissional inválido.");
        }


        if (alunos.getLinkedin() != null && !alunos.getLinkedin().isBlank()) {
            if (!alunos.getLinkedin().startsWith("https://") || !alunos.getLinkedin().contains("linkedin.com")) {
                throw new DadosInvalidosException("URL do LinkedIn inválida.");
            }
        }


        if (alunos.getStatus() != null && alunos.getStatus().name().equals("INATIVO")) {
            throw new RegraDeNegocioException("Não é permitido cadastrar aluno com status INATIVO.");
        }

        alunos.getUsuarios().setTipo(UsuarioTipo.ALUNO);

        return alunosGateways.save(alunos);
    }
}
