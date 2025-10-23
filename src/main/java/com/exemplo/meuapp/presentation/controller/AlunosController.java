package com.exemplo.meuapp.presentation.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.meuapp.application.port.in.alunos.AtualizarAlunosUseCase;
import com.exemplo.meuapp.application.port.in.alunos.CriarAlunosUseCase;
import com.exemplo.meuapp.application.port.in.alunos.DeletarAlunosUseCase;
import com.exemplo.meuapp.application.port.in.alunos.EncontrarAlunosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.CriarUsuariosUseCase;
import com.exemplo.meuapp.application.port.in.usuarios.EncontrarUsuariosUseCase;
import com.exemplo.meuapp.common.mapper.AlunosMapper;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Alunos;
import com.exemplo.meuapp.domain.model.Usuarios;
import com.exemplo.meuapp.infrastructure.persistence.entity.AlunosEntity;
import com.exemplo.meuapp.infrastructure.webclient.CollectEmailForTokenService;
import com.exemplo.meuapp.presentation.dto.CadastroAlunoDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/senai/alunos")
public class AlunosController {

    @Autowired
    @Qualifier("alunosMapperImpl")
    private AlunosMapper mapper;
    private CriarAlunosUseCase criarAlunosUseCase;
    private EncontrarAlunosUseCase encontrarAlunosUseCase;
    private DeletarAlunosUseCase deletarAlunosUseCase;    private AtualizarAlunosUseCase atualizarAlunosUseCase;
    private EncontrarUsuariosUseCase encontrarUsuariosUseCase;
    private CollectEmailForTokenService collectEmailForTokenService;
    private CriarUsuariosUseCase criarUsuariosUseCase;    @Autowired
    public AlunosController(
            @Qualifier("alunosMapperImpl") AlunosMapper mapper,
            CriarAlunosUseCase criarAlunosUseCase,
            EncontrarAlunosUseCase encontrarAlunosUseCase,
            DeletarAlunosUseCase deletarAlunosUseCase,
            AtualizarAlunosUseCase atualizarAlunosUseCase,
            EncontrarUsuariosUseCase encontrarUsuariosUseCase,
            CollectEmailForTokenService collectEmailForTokenService,
            CriarUsuariosUseCase criarUsuariosUseCase) {
        this.mapper = mapper;
        this.criarAlunosUseCase = criarAlunosUseCase;
        this.encontrarAlunosUseCase = encontrarAlunosUseCase;
        this.deletarAlunosUseCase = deletarAlunosUseCase;
        this.encontrarUsuariosUseCase = encontrarUsuariosUseCase;
        this.collectEmailForTokenService = collectEmailForTokenService;
        this.atualizarAlunosUseCase = atualizarAlunosUseCase;
        this.criarUsuariosUseCase = criarUsuariosUseCase;
    }

    public @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Alunos request, HttpServletRequest httpServletRequest) {
        try {
            request.setUsuarios(encontrarUsuariosUseCase.buscarPorEmailUser(
                    collectEmailForTokenService.execute(httpServletRequest)));
            Alunos alunos = criarAlunosUseCase.criar(request);
           
            AlunosEntity response = mapper.toEntity(alunos);

            return new ResponseEntity<AlunosEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CadastroAlunoDTO request) {
        try {
            // Criar objeto Usuarios usando setters
            Usuarios novoUsuario = new Usuarios();
            novoUsuario.setUsuario(request.nome());
            novoUsuario.setEmail(request.email());
            novoUsuario.setSenha(request.senha());
            novoUsuario.setTipo(request.tipo());
            novoUsuario.setStatus(UsuariosStatus.ATIVO); // Automaticamente ATIVO
            novoUsuario.setCriadoEm(java.time.LocalDateTime.now());
            novoUsuario.setAtualizadoEm(java.time.LocalDateTime.now());
            
            // Usar o serviço de criação de usuários que já funciona
            Usuarios usuarioCriado = criarUsuariosUseCase.criar(novoUsuario);

            return ResponseEntity.status(201).body(Map.of(
                "message", "✅ Aluno cadastrado com sucesso!",
                "usuario", Map.of(
                    "uuid", usuarioCriado.getUuid(),
                    "nome", usuarioCriado.getUsuario(),
                    "email", usuarioCriado.getEmail(),
                    "tipo", usuarioCriado.getTipo(),
                    "status", usuarioCriado.getStatus(),
                    "criadoEm", usuarioCriado.getCriadoEm()
                ),
                "instruction", "Use /api/user/login para fazer login com as credenciais cadastradas."
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of(
                "error", "❌ Erro ao cadastrar aluno",
                "message", e.getMessage(),
                "details", "Verifique se o email já não está em uso ou se todos os campos obrigatórios foram preenchidos."
            ));
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarAlunosUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    public ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Alunos alunos = encontrarAlunosUseCase.buscarPorUuid(UUID.fromString(uuid));
            AlunosEntity response = mapper.toEntity(alunos);
            return new ResponseEntity<AlunosEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Alunos request) {
        try {
            Alunos alunos = atualizarAlunosUseCase.atualizar(UUID.fromString(uuid), request);
            AlunosEntity response = mapper.toEntity(alunos);
            return new ResponseEntity<AlunosEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarAlunosUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByMatricula/{matricula}")
    public ResponseEntity<?> findByMatricula(@PathVariable("matricula") String matricula) {
        try {
            return new ResponseEntity<>(encontrarAlunosUseCase.buscarPorMatricula(matricula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
