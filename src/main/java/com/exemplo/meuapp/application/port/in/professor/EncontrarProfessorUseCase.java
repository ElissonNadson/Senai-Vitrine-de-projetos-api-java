package com.exemplo.meuapp.application.port.in.professor;

import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import com.exemplo.meuapp.domain.model.Professores;

import java.util.List;
import java.util.UUID;

public class EncontrarProfessorUseCase {

    private final ProfessoresGateways professoresGateways;

    public EncontrarProfessorUseCase(ProfessoresGateways professoresGateways) {
        this.professoresGateways = professoresGateways;
    }

    public Professores buscarPorUUID(UUID professorId) {
        return professoresGateways.getProfessoresById(professorId);
    }

    public List<Professores> buscarTodos() {
        return professoresGateways.getAllProfessores();
    }

    public Professores buscarPorUsuario(UUID usuario) {
        return professoresGateways.getProfessoresByUsuarioId(usuario);
    }

    public List<Professores> buscarPorDepartamento(String departamento) {
        return professoresGateways.getProfessoresByDepartamento(departamento);
    }

    public List<Professores> buscarPorStatus(String status) {
        return professoresGateways.getProfessoresByStatus(status);
    }
}
