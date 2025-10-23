package com.exemplo.meuapp.domain.model;
import java.util.UUID;

import com.exemplo.meuapp.domain.enums.TipoAnexo;
import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class AnexoEtapa {
    private UUID uuid;
    private EtapasProjeto etapa;
    private String nomeArquivo;
    private String url;
    private TipoAnexo tipo;
    private LocalDateTime dataUpload;

    public AnexoEtapa correct() {
        if (etapa == null) {
            throw new DadosInvalidosException("O campo etapa deve ser preenchido");
        }
        if (nomeArquivo == null || nomeArquivo.isBlank()) {
            throw new DadosInvalidosException("O campo nomeArquivo deve ser preenchido");
        }
        if (url == null || url.isBlank()) {
            throw new DadosInvalidosException("O campo url deve ser preenchido");
        }
        if (tipo == null) {
            throw new DadosInvalidosException("O campo tipo deve ser preenchido");
        }
        if (dataUpload == null) {
            throw new DadosInvalidosException("O campo dataUpload deve ser preenchido");
        }
        return this;
    }
}
