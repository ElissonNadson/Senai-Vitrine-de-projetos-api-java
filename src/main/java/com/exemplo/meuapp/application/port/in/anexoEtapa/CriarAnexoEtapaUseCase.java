package com.exemplo.meuapp.application.port.in.anexoEtapa;

    import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;
    import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
    import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
    import com.exemplo.meuapp.domain.model.AnexoEtapa;

    import java.time.LocalDateTime;

    public class CriarAnexoEtapaUseCase {
        private AnexoEtapaGateways anexoEtapaGateways;

        public CriarAnexoEtapaUseCase(AnexoEtapaGateways anexoEtapaGateways) {
            this.anexoEtapaGateways = anexoEtapaGateways;
        }

        public AnexoEtapa criar(AnexoEtapa anexoEtapa) {
            anexoEtapa.setDataUpload(LocalDateTime.now());
            anexoEtapa.correct();


            if (!anexoEtapa.getNomeArquivo().matches("^[\\w\\-. ]+$")) {
                throw new DadosInvalidosException("Nome do arquivo contém caracteres inválidos.");
            }


            if (!anexoEtapa.getUrl().startsWith("https://")) {
                throw new DadosInvalidosException("A URL do anexo deve começar com https://");
            }

            if (anexoEtapaGateways.existsByEtapaAndNomeArquivo(
                    anexoEtapa.getEtapa().getUuid(), anexoEtapa.getNomeArquivo())) {
                throw new RegraDeNegocioException("Já existe um anexo com este nome para esta etapa.");
            }

            if (anexoEtapa.getDataUpload() != null && anexoEtapa.getDataUpload().isAfter(LocalDateTime.now())) {
                throw new RegraDeNegocioException("Data de upload não pode ser futura.");
            }

            return anexoEtapaGateways.save(anexoEtapa);
        }
    }