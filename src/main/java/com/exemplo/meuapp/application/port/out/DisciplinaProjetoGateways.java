package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

import java.util.List;
import java.util.UUID;

public interface DisciplinaProjetoGateways {
     DisciplinaProjeto save(DisciplinaProjeto disciplinaProjeto);
     DisciplinaProjeto getDisciplinaProjeto(UUID disciplinaProjetoId);
     void delete(UUID disciplinaProjetoId);
     DisciplinaProjeto update(DisciplinaProjeto disciplinaProjeto);
     List<DisciplinaProjeto> getAllDisciplinasProjetos();

    List<DisciplinaProjeto> getByDisciplina(UUID disciplinaId);

    List<DisciplinaProjeto> getByProjeto(UUID projetoId);

    List<DisciplinaProjeto> getByDisciplinaAndProjeto(UUID disciplinaId, UUID projetoId);

    boolean existsByDisciplinaAndProjeto(UUID uuid, UUID uuid1);
}
