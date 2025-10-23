package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.EtapasProjeto;

import java.util.List;
import java.util.UUID;

public interface EtapasProjetoGateways {
     EtapasProjeto save(EtapasProjeto etapaProjeto);
     EtapasProjeto getEtapaById(UUID etapaId);
     void delete(UUID etapaId);
     EtapasProjeto update(EtapasProjeto etapaProjeto);
     List<EtapasProjeto> getAllEtapas();
     List<EtapasProjeto> getEtapasByProjetoId(UUID projetoId);
     List<EtapasProjeto> getEtapasByStatus(String status);

    boolean existsByProjetoAndNomeEtapa(UUID uuid, String nomeEtapa);

     boolean existsByProjetoAndOrdem(UUID uuid, int ordem);
}
