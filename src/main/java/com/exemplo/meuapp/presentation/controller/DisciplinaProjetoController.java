package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.disciplinaProjeto.AtualizarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.in.disciplinaProjeto.DeletarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.application.port.in.disciplinaProjeto.EncontrarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.common.mapper.DisciplinaProjetoMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaProjetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.disciplinaProjeto.CriarDisciplinaProjetoUseCase;
import com.exemplo.meuapp.domain.model.DisciplinaProjeto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/disciplinaProjeto")
public class DisciplinaProjetoController {

    @Autowired
    @Qualifier("disciplinaProjetoMapperImpl")
    private DisciplinaProjetoMapper mapper;
    private CriarDisciplinaProjetoUseCase criarDisciplinaProjetoUseCase;
    private EncontrarDisciplinaProjetoUseCase encontrarDisciplinaProjetoUseCase;
    private DeletarDisciplinaProjetoUseCase deletarDisciplinaProjetoUseCase;
    private AtualizarDisciplinaProjetoUseCase atualizarDisciplinaProjetoUseCase;

    @Autowired
    public DisciplinaProjetoController(
            @Qualifier("disciplinaProjetoMapperImpl") DisciplinaProjetoMapper mapper,
            CriarDisciplinaProjetoUseCase criarDisciplinaProjetoUseCase,
            EncontrarDisciplinaProjetoUseCase encontrarDisciplinaProjetoUseCase,
            DeletarDisciplinaProjetoUseCase deletarDisciplinaProjetoUseCase,
            AtualizarDisciplinaProjetoUseCase atualizarDisciplinaProjetoUseCase) {
        this.mapper = mapper;
        this.criarDisciplinaProjetoUseCase = criarDisciplinaProjetoUseCase;
        this.encontrarDisciplinaProjetoUseCase = encontrarDisciplinaProjetoUseCase;
        this.deletarDisciplinaProjetoUseCase = deletarDisciplinaProjetoUseCase;
        this.atualizarDisciplinaProjetoUseCase = atualizarDisciplinaProjetoUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody DisciplinaProjeto request) {
        try {

            DisciplinaProjeto disciplinaProjeto = criarDisciplinaProjetoUseCase.criar(request);
            DisciplinaProjetoEntity response = mapper.toEntity(disciplinaProjeto);

            return new ResponseEntity<DisciplinaProjetoEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarDisciplinaProjetoUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            DisciplinaProjeto disciplinaProjeto = encontrarDisciplinaProjetoUseCase.buscarPorUuid(UUID.fromString(uuid));
            DisciplinaProjetoEntity response = mapper.toEntity(disciplinaProjeto);
            return new ResponseEntity<DisciplinaProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody DisciplinaProjeto request) {
        try {
            DisciplinaProjeto disciplinaProjeto = atualizarDisciplinaProjetoUseCase.atualizar(UUID.fromString(uuid), request);
            DisciplinaProjetoEntity response = mapper.toEntity(disciplinaProjeto);
            return new ResponseEntity<DisciplinaProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarDisciplinaProjetoUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDisciplina/{disciplina}")
    ResponseEntity<?> findByDisciplina(@PathVariable("disciplina") String disciplina) {
        try {
            return new ResponseEntity<>(encontrarDisciplinaProjetoUseCase.buscarPorDisciplina(UUID.fromString(disciplina)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByProjeto/{projeto}")
    ResponseEntity<?> findByProjeto(@PathVariable("projeto") String projeto) {
        try {
            return new ResponseEntity<>(encontrarDisciplinaProjetoUseCase.buscarPorProjeto(UUID.fromString(projeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDisciplinaEProjeto/{projeto}/{disciplina}")
    ResponseEntity<?> findByDisciplinaEProjeto(@PathVariable("projeto") String projeto,
            @PathVariable("disciplina") String disciplina) {
        try {
            return new ResponseEntity<>(encontrarDisciplinaProjetoUseCase.buscarPorDisciplinaEProjeto(UUID.fromString(disciplina), UUID.fromString(projeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
