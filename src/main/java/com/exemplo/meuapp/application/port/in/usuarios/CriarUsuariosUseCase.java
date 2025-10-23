package com.exemplo.meuapp.application.port.in.usuarios;

import java.time.LocalDateTime;

import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import com.exemplo.meuapp.application.port.out.UsuariosGateways;
import com.exemplo.meuapp.domain.enums.UsuarioTipo;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Alunos;
import com.exemplo.meuapp.domain.model.Professores;
import com.exemplo.meuapp.domain.model.Usuarios;

public class CriarUsuariosUseCase {

    private UsuariosGateways usuariosGateways;
    private AlunosGateways alunosGateways;
    private ProfessoresGateways professoresGateways;

    public CriarUsuariosUseCase(UsuariosGateways usuariosGateways, 
                               AlunosGateways alunosGateways, 
                               ProfessoresGateways professoresGateways) {
        this.usuariosGateways = usuariosGateways;
        this.alunosGateways = alunosGateways;
        this.professoresGateways = professoresGateways;
    }

    public Usuarios criar(Usuarios usuarios) {
        usuarios.setCriadoEm(LocalDateTime.now());
        usuarios.setAtualizadoEm(LocalDateTime.now());
        
        // Salvar o usuário primeiro
        Usuarios usuarioSalvo = usuariosGateways.save(usuarios);
        
        // Criar registro dependente baseado no tipo
        if (usuarioSalvo.getTipo() == UsuarioTipo.ALUNO) {
            criarRegistroAluno(usuarioSalvo);
        } else if (usuarioSalvo.getTipo() == UsuarioTipo.PROFESSOR) {
            criarRegistroProfessor(usuarioSalvo);
        }
        
        return usuarioSalvo;
    }
      private void criarRegistroAluno(Usuarios usuario) {
        Alunos aluno = new Alunos();
        aluno.setUsuarios(usuario);
        aluno.setMatricula(gerarMatricula());
        aluno.setCurso("Não informado");
        aluno.setTelefonePessoal("");
        aluno.setTelefoneProfissional("");
        aluno.setLinkedin("");
        aluno.setStatus(UsuariosStatus.ATIVO);
        aluno.setCriadoEm(LocalDateTime.now());
        aluno.setAtualizadoEm(LocalDateTime.now());
        
        alunosGateways.save(aluno);
    }
    
    private void criarRegistroProfessor(Usuarios usuario) {
        Professores professor = new Professores();
        professor.setUsuarios(usuario);
        professor.setDepartamento("Não informado");
        professor.setEspecialidade("Não informado");
        professor.setStatus(UsuariosStatus.ATIVO);
        professor.setCriadoEm(LocalDateTime.now());
        professor.setAtualizadoEm(LocalDateTime.now());
                
        professoresGateways.save(professor);
    }
    
    private String gerarMatricula() {
        return String.valueOf(System.currentTimeMillis());
    }
}
