package com.exemplo.meuapp.application.port.in.alunos;

import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
import com.exemplo.meuapp.domain.model.Alunos;

import java.time.LocalDateTime;
import java.util.UUID;

public class AtualizarAlunosUseCase {
    private final AlunosGateways alunosGateways;

    public AtualizarAlunosUseCase(AlunosGateways alunosGateways) {
        this.alunosGateways = alunosGateways;
    }

    public Alunos atualizar(UUID uuid, Alunos alunos) {
        Alunos alunosInDb = alunosGateways.getAlunos(uuid);
        if (alunosInDb == null) {
            throw new RegraDeNegocioException("Aluno não encontrado.");
        }

        alunos.correct();

        if (alunos.getStatus() == UsuariosStatus.INATIVO) {
            throw new RegraDeNegocioException("Não é permitido atualizar aluno INATIVO.");
        }


        if (!alunosInDb.getUsuarios().getEmail().equals(alunos.getUsuarios().getEmail()) &&
                alunosGateways.existsByEmail(alunos.getUsuarios().getEmail())) {
            throw new RegraDeNegocioException("Já existe um aluno com este e-mail.");
        }
        if (!alunosInDb.getMatricula().equals(alunos.getMatricula()) &&
                alunosGateways.existsByMatricula(alunos.getMatricula())) {
            throw new RegraDeNegocioException("Já existe um aluno com esta matrícula.");
        }

        if (alunos.getTelefonePessoal() != null &&
                alunos.getTelefonePessoal().replaceAll("\\D", "").length() < 10) {
            throw new DadosInvalidosException("Telefone pessoal inválido.");
        }
        if (alunos.getTelefoneProfissional() != null &&
                alunos.getTelefoneProfissional().replaceAll("\\D", "").length() < 10) {
            throw new DadosInvalidosException("Telefone profissional inválido.");
        }

        alunosInDb.setAtualizadoEm(LocalDateTime.now());
        alunosInDb.setCurso(alunos.getCurso());
        alunosInDb.setStatus(alunos.getStatus());
        alunosInDb.setEndereco(alunos.getEndereco());
        alunosInDb.setTelefonePessoal(alunos.getTelefonePessoal());
        alunosInDb.setTelefoneProfissional(alunos.getTelefoneProfissional());
        alunosInDb.setUsuarios(alunos.getUsuarios());
        alunosInDb.setMatricula(alunos.getMatricula());

        return alunosGateways.update(alunosInDb);
    }
}