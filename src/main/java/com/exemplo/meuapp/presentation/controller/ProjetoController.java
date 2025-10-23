package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.projeto.AtualizarProjetoUseCase;
import com.exemplo.meuapp.application.port.in.projeto.DeletarProjetoUseCase;
import com.exemplo.meuapp.application.port.in.projeto.EncontrarProjetoUseCase;
import com.exemplo.meuapp.common.mapper.ProjetoMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.projeto.CriarProjetoUseCase;
import com.exemplo.meuapp.domain.model.Projeto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/projeto")
public class ProjetoController {
    @Autowired
    @Qualifier("projetoMapperImpl")
    private ProjetoMapper mapper;
    private CriarProjetoUseCase criarProjetoUseCase;
    private EncontrarProjetoUseCase encontrarProjetoUseCase;
    private DeletarProjetoUseCase deletarProjetoUseCase;
    private AtualizarProjetoUseCase atualizarProjetoUseCase;

    @Autowired
    public ProjetoController(
            @Qualifier("projetoMapperImpl")ProjetoMapper mapper,
            CriarProjetoUseCase criarProjetoUseCase,
            EncontrarProjetoUseCase encontrarProjetoUseCase,
            DeletarProjetoUseCase deletarProjetoUseCase,
            AtualizarProjetoUseCase atualizarProjetoUseCase) {
        this.mapper = mapper;
        this.criarProjetoUseCase = criarProjetoUseCase;
        this.encontrarProjetoUseCase = encontrarProjetoUseCase;
        this.deletarProjetoUseCase = deletarProjetoUseCase;
        this.atualizarProjetoUseCase = atualizarProjetoUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Projeto request) {
        try {

            Projeto projeto = criarProjetoUseCase.criar(request);
            ProjetoEntity response = mapper.toEntity(projeto);

            return new ResponseEntity<ProjetoEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Projeto projeto = encontrarProjetoUseCase.buscarPorUUID(UUID.fromString(uuid));
            ProjetoEntity response = mapper.toEntity(projeto);
            return new ResponseEntity<ProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Projeto request) {
        try {
            Projeto projeto = atualizarProjetoUseCase.atualizar(UUID.fromString(uuid), request);
            ProjetoEntity response = mapper.toEntity(projeto);
            return new ResponseEntity<ProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarProjetoUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTitulo/{titulo}")
    ResponseEntity<?> findByTitulo(@PathVariable("titulo") String titulo) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorTitulo(titulo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByStatus/{status}")
    ResponseEntity<?> findByStatus(@PathVariable("status") String matricula) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorStatus(matricula), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByCurso/{curso}")
    ResponseEntity<?> findByCurso(@PathVariable("curso") String curso) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorCurso(curso), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByTurma/{turma}")
    ResponseEntity<?> findByTurma(@PathVariable("turma") String turma) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorTurma(turma), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByLiderProjeto/{liderProjeto}")
    ResponseEntity<?> findByfindByLiderProjeto(@PathVariable("liderProjeto") String liderProjeto) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorLider(UUID.fromString(liderProjeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByrUnidadeCurricular/{unidadeCurricular}")
    ResponseEntity<?> findByUnidadeCurricular(@PathVariable("unidadeCurricular") String unidadeCurricular) {
        try {
            return new ResponseEntity<>(encontrarProjetoUseCase.buscarPorUnidadeCurricular(UUID.fromString(unidadeCurricular)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
