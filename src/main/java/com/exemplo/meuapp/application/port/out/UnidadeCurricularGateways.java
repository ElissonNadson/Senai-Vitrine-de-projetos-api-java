package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import java.util.List;
import java.util.UUID;

public interface UnidadeCurricularGateways {
     UnidadeCurricular save(UnidadeCurricular unidadeCurricular);
     UnidadeCurricular getUnidadeCurricular(UUID unidadeCurricularId);
     void delete(UUID unidadeCurricularId);
     UnidadeCurricular update(UnidadeCurricular unidadeCurricular);
     List<UnidadeCurricular> getAllUnidadesCurriculares();
     List<UnidadeCurricular> getUnidadeCurricularByNome(String nome);
     List<UnidadeCurricular> getUnidadeCurricularByCargaHoraria(String cargaHoraria);

     boolean existsByNome(String nome);
}
