package com.exemplo.meuapp.application.port.in.projeto;

    import com.exemplo.meuapp.application.port.out.ProjetoGateways;
    import com.exemplo.meuapp.domain.enums.ProjetoStatus;
    import com.exemplo.meuapp.domain.exception.DadosInvalidosException;
    import com.exemplo.meuapp.domain.exception.RegraDeNegocioException;
    import com.exemplo.meuapp.domain.model.Projeto;

    import java.time.LocalDateTime;
    import java.util.Random;

    public class CriarProjetoUseCase {
        private ProjetoGateways projetosGateways;
        private static final Random random = new Random();

        public CriarProjetoUseCase(ProjetoGateways projetosGateways) {
            this.projetosGateways = projetosGateways;
        }

        public Projeto criar(Projeto projeto) {
            projeto.correct();

            if (projeto.getCodigo() == null || projeto.getCodigo().isBlank()) {
                projeto.setCodigo(gerarCodigo());
            }

            if (projeto.getCodigo() != null && !projeto.getCodigo().isBlank()) {
                if (projetosGateways.existsByCodigo(projeto.getCodigo())) {
                    throw new RegraDeNegocioException("Já existe um projeto com este código.");
                }
            }

            if (projeto.getLiderProjeto() != null && projeto.getLiderProjeto().getStatus() != null
                    && !projeto.getLiderProjeto().getStatus().name().equals("ATIVO")) {
                throw new RegraDeNegocioException("Líder do projeto está inativo.");
            }

            if (projeto.getStatus() == null || projeto.getStatus() != ProjetoStatus.ATIVO) {
                throw new DadosInvalidosException("Status do projeto não pode ser nulo.");
            }

            projeto.setCriadoEm(LocalDateTime.now());
            projeto.setAtualizadoEm(LocalDateTime.now());
            return projetosGateways.save(projeto);
        }

        public static String gerarCodigo() {
            String letras = random.ints(4, 'A', 'Z' + 1)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            String numeros = String.format("%03d", random.nextInt(1000));

            return "PRJ-" + letras + numeros;
        }
    }