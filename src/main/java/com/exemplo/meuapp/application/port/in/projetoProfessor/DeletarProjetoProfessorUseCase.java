package com.exemplo.meuapp.application.port.in.projetoProfessor;

import com.exemplo.meuapp.application.port.out.ProjetoProfessorGateways;
import java.util.UUID;

public class DeletarProjetoProfessorUseCase {
    private ProjetoProfessorGateways projetoProfessorGateways;

    public DeletarProjetoProfessorUseCase(ProjetoProfessorGateways projetoProfessorGateways) {
        this.projetoProfessorGateways = projetoProfessorGateways;
    }

    public void deletar(UUID uuid) {
        projetoProfessorGateways.delete(uuid);
    }
}