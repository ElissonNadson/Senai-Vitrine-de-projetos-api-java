package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.Professores;

import java.util.List;
import java.util.UUID;

public interface ProfessoresGateways {
     Professores save(Professores professor);
     Professores getProfessoresById(UUID professorId);
     void delete(UUID professorId);
     Professores update(Professores professor);
     List<Professores> getAllProfessores();
     Professores getProfessoresByUsuarioId(UUID usuarioId);
     List<Professores> getProfessoresByDepartamento(String departamento);
     List<Professores> getProfessoresByStatus(String status);

    boolean existsByUsuariosId(UUID uuid);
}
