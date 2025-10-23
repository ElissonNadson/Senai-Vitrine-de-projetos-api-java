package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.disciplina.AtualizarDisciplinaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.DeleteDisciplinaUseCase;
import com.exemplo.meuapp.application.port.in.disciplina.EncontrarDisciplinaUseCase;
import com.exemplo.meuapp.common.mapper.DisciplinaMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.disciplina.CriarDisciplinaUseCase;
import com.exemplo.meuapp.domain.model.Disciplina;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/disciplina")
public class DisciplinaController {

    @Autowired
    @Qualifier("disciplinaMapperImpl")
    private DisciplinaMapper mapper;
    private CriarDisciplinaUseCase criarDisciplinaUseCase;
    private EncontrarDisciplinaUseCase encontrarDisciplinaUseCase;
    private DeleteDisciplinaUseCase deleteDisciplinaUseCase;
    private AtualizarDisciplinaUseCase atualizarDisciplinaUseCase;

    @Autowired
    public DisciplinaController(
            @Qualifier("disciplinaMapperImpl") DisciplinaMapper mapper,
            CriarDisciplinaUseCase criarDisciplinaUseCase,
            EncontrarDisciplinaUseCase encontrarDisciplinaUseCase,
            DeleteDisciplinaUseCase deleteDisciplinaUseCase,
            AtualizarDisciplinaUseCase atualizarDisciplinaUseCase) {
        this.mapper = mapper;
        this.criarDisciplinaUseCase = criarDisciplinaUseCase;
        this.encontrarDisciplinaUseCase = encontrarDisciplinaUseCase;
        this.deleteDisciplinaUseCase = deleteDisciplinaUseCase;
        this.atualizarDisciplinaUseCase = atualizarDisciplinaUseCase;
    }

    public @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Disciplina request) {
        try {

            Disciplina disciplina = criarDisciplinaUseCase.criar(request);
            DisciplinaEntity response = mapper.toEntity(disciplina);

            return new ResponseEntity<DisciplinaEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarDisciplinaUseCase.buscarTodas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Disciplina disciplina = encontrarDisciplinaUseCase.buscarPorUuid(UUID.fromString(uuid));
            DisciplinaEntity response = mapper.toEntity(disciplina);
            return new ResponseEntity<DisciplinaEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Disciplina request) {
        try {
            Disciplina disciplina = atualizarDisciplinaUseCase.atualizar(UUID.fromString(uuid), request);
            DisciplinaEntity response = mapper.toEntity(disciplina);
            return new ResponseEntity<DisciplinaEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deleteDisciplinaUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findByNome/{nome}")
    ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
        try {
            return new ResponseEntity<>(encontrarDisciplinaUseCase.buscarPorNome(nome), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
