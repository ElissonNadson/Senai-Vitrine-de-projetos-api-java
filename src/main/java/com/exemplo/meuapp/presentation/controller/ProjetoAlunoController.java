package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.projetoAluno.AtualizarProjetoAlunoUseCase;
import com.exemplo.meuapp.application.port.in.projetoAluno.DeletarProjetoAlunoUseCase;
import com.exemplo.meuapp.application.port.in.projetoAluno.EncontrarProjetoAlunoUseCase;
import com.exemplo.meuapp.common.mapper.ProjetoAlunoMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoAlunoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.projetoAluno.CriarProjetoAlunoUseCase;
import com.exemplo.meuapp.domain.model.ProjetoAluno;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/projetoAluno")
public class ProjetoAlunoController {
    @Autowired
    @Qualifier("projetoAlunoMapperImpl")
    private ProjetoAlunoMapper mapper;
    private CriarProjetoAlunoUseCase criarProjetoAlunoUseCase;
    private EncontrarProjetoAlunoUseCase encontrarProjetoAlunoUseCase;
    private DeletarProjetoAlunoUseCase deletarProjetoAlunoUseCase;
    private AtualizarProjetoAlunoUseCase atualizarProjetoAlunoUseCase;

    @Autowired
    public ProjetoAlunoController(
            @Qualifier("projetoAlunoMapperImpl")ProjetoAlunoMapper mapper,
            CriarProjetoAlunoUseCase criarProjetoAlunoUseCase,
            EncontrarProjetoAlunoUseCase encontrarProjetoAlunoUseCase,
            DeletarProjetoAlunoUseCase deletarProjetoAlunoUseCase,
            AtualizarProjetoAlunoUseCase atualizarProjetoAlunoUseCase) {
        this.mapper = mapper;
        this.criarProjetoAlunoUseCase = criarProjetoAlunoUseCase;
        this.encontrarProjetoAlunoUseCase = encontrarProjetoAlunoUseCase;
        this.deletarProjetoAlunoUseCase = deletarProjetoAlunoUseCase;
        this.atualizarProjetoAlunoUseCase = atualizarProjetoAlunoUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody ProjetoAluno request) {
        try {

            ProjetoAluno projetoAluno = criarProjetoAlunoUseCase.criar(request);
            ProjetoAlunoEntity response = mapper.toEntity(projetoAluno);

            return new ResponseEntity<ProjetoAlunoEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarProjetoAlunoUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            ProjetoAluno projetoAluno = encontrarProjetoAlunoUseCase.buscarPorUUID(UUID.fromString(uuid));
            ProjetoAlunoEntity response = mapper.toEntity(projetoAluno);
            return new ResponseEntity<ProjetoAlunoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody ProjetoAluno request) {
        try {
            ProjetoAluno projetoAluno = atualizarProjetoAlunoUseCase.atualizar(UUID.fromString(uuid), request);
            ProjetoAlunoEntity response = mapper.toEntity(projetoAluno);
            return new ResponseEntity<ProjetoAlunoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarProjetoAlunoUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByAluno/{aluno}")
    ResponseEntity<?> findByAluno(@PathVariable("aluno") String aluno) {
        try {
            return new ResponseEntity<>(encontrarProjetoAlunoUseCase.buscarPorAluno(UUID.fromString(aluno)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByProjeto/{projeto}")
    ResponseEntity<?> findByProjeto(@PathVariable("projeto") String projeto) {
        try {
            return new ResponseEntity<>(encontrarProjetoAlunoUseCase.buscarPorProjeto(UUID.fromString(projeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
