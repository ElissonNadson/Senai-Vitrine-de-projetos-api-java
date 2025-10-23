package com.exemplo.meuapp.application.port.in.anexoEtapa;

    import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;
    import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
    import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
    import com.exemplo.meuapp.domain.model.AnexoEtapa;

    import java.time.LocalDateTime;
    import java.util.UUID;

    public class AtualizarAnexoEtapaUseCase {
        private final AnexoEtapaGateways anexoEtapaGateways;

        public AtualizarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
            this.anexoEtapaGateways = anexoEtapaGateways;
        }

        public AnexoEtapa atualizar(UUID uuid, AnexoEtapa anexoEtapa) {
            AnexoEtapa anexoEtapaInDb = anexoEtapaGateways.getAnexoEtapa(uuid);
            if (anexoEtapaInDb == null) {
                throw new RegraDeNegocioException("Anexo não encontrado.");
            }

            anexoEtapa.correct();

            if (!anexoEtapaInDb.getNomeArquivo().equals(anexoEtapa.getNomeArquivo()) &&
                    anexoEtapaGateways.existsByEtapaAndNomeArquivo(anexoEtapa.getEtapa().getUuid(),anexoEtapa.getNomeArquivo())) {
                throw new RegraDeNegocioException("Já existe um anexo com este nome para a etapa.");
            }

            if (anexoEtapa.getUrl() == null || !anexoEtapa.getUrl().startsWith("https://")) {
                throw new DadosInvalidosException("URL do anexo deve começar com https://");
            }

            if (anexoEtapa.getDataUpload() != null && anexoEtapa.getDataUpload().isAfter(LocalDateTime.now())) {
                throw new RegraDeNegocioException("Data de upload não pode ser futura.");
            }

            anexoEtapaInDb.setEtapa(anexoEtapa.getEtapa());
            anexoEtapaInDb.setNomeArquivo(anexoEtapa.getNomeArquivo());
            anexoEtapaInDb.setUrl(anexoEtapa.getUrl());
            anexoEtapaInDb.setTipo(anexoEtapa.getTipo());
            anexoEtapaInDb.setDataUpload(LocalDateTime.now());

            return anexoEtapaGateways.update(anexoEtapaInDb);
        }
    }