package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.ProjetoProfessor;

import java.util.List;
import java.util.UUID;

public interface ProjetoProfessorGateways {
     ProjetoProfessor save(ProjetoProfessor projetoProfessor);
     ProjetoProfessor getProjetoProfessor(UUID projetoProfessorId);
     void delete(UUID projetoProfessorId);
     ProjetoProfessor update(ProjetoProfessor projetoProfessor);
     List<ProjetoProfessor> getAllProjetosProfessores();
     List<ProjetoProfessor> getByProjeto(UUID projetoId);
     List<ProjetoProfessor> getByProfessor(UUID professorId);

    boolean existsByProjetoAndProfessor(UUID uuid, UUID uuid1);

     boolean existsOrientadorByProjeto(UUID uuid);
}
