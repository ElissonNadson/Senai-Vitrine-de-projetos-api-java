package com.exemplo.meuapp.application.port.in.professor;

import com.exemplo.meuapp.application.port.out.ProfessoresGateways;

import java.util.UUID;

public class DeletarProfessorUseCase {
    private ProfessoresGateways professorGateways;

    public DeletarProfessorUseCase(ProfessoresGateways professorGateways) {
        this.professorGateways = professorGateways;
    }

    public void deletar(UUID uuid) {
        professorGateways.delete(uuid);
    }
}