package com.exemplo.meuapp.application.port.out;

import com.exemplo.meuapp.domain.model.AnexoEtapa;

import java.util.List;
import java.util.UUID;

public interface AnexoEtapaGateways {
    AnexoEtapa save(AnexoEtapa anexoEtapa);

    AnexoEtapa getAnexoEtapa(UUID anexoEtapaId);

    void delete(UUID anexoEtapaId);

    AnexoEtapa update(AnexoEtapa anexoEtapa);

    List<AnexoEtapa> getAllAnexosEtapa();

    List<AnexoEtapa> getAnexosByEtapaId(UUID etapaId);

    List<AnexoEtapa> getAnexosByEtapaIdAndTipo(UUID etapaId, String tipo);

    boolean existsByEtapaAndNomeArquivo(UUID uuid, String nomeArquivo);
}
