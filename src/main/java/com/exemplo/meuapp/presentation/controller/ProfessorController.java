package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.professor.AtualizarProfessorUseCase;
import com.exemplo.meuapp.application.port.in.professor.DeletarProfessorUseCase;
import com.exemplo.meuapp.application.port.in.professor.EncontrarProfessorUseCase;
import com.exemplo.meuapp.common.mapper.ProfessoresMapper;
import com.exemplo.meuapp.domain.model.Professores;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProfessoresEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.professor.CriarProfessorUseCase;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/professor")
public class ProfessorController {

    @Autowired
    @Qualifier("professoresMapperImpl")
    private ProfessoresMapper mapper;
    private CriarProfessorUseCase criarProfessorUseCase;
    private EncontrarProfessorUseCase encontrarProfessorUseCase;
    private DeletarProfessorUseCase deletarProfessorUseCase;
    private AtualizarProfessorUseCase atualizarProfessorUseCase;

    @Autowired
    public ProfessorController(
            @Qualifier("professoresMapperImpl")ProfessoresMapper mapper,
            CriarProfessorUseCase criarProfessorUseCase,
            EncontrarProfessorUseCase encontrarProfessorUseCase,
            DeletarProfessorUseCase deletarProfessorUseCase,
            AtualizarProfessorUseCase atualizarProfessorUseCase) {
        this.mapper = mapper;
        this.criarProfessorUseCase = criarProfessorUseCase;
        this.encontrarProfessorUseCase = encontrarProfessorUseCase;
        this.deletarProfessorUseCase = deletarProfessorUseCase;
        this.atualizarProfessorUseCase = atualizarProfessorUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Professores request) {
        try {

            Professores professor = criarProfessorUseCase.criar(request);
            ProfessoresEntity response = mapper.toEntity(professor);

            return new ResponseEntity<ProfessoresEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarProfessorUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Professores professor = encontrarProfessorUseCase.buscarPorUUID(UUID.fromString(uuid));
            ProfessoresEntity response = mapper.toEntity(professor);
            return new ResponseEntity<ProfessoresEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Professores request) {
        try {
            Professores professor = atualizarProfessorUseCase.atualizar(UUID.fromString(uuid), request);
            ProfessoresEntity response = mapper.toEntity(professor);
            return new ResponseEntity<ProfessoresEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarProfessorUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUsuario/{usuario}")
    ResponseEntity<?> findByUsuario(@PathVariable("usuario") String usuario) {
        try {
            return new ResponseEntity<>(encontrarProfessorUseCase.buscarPorUsuario(UUID.fromString(usuario)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByDepartamento/{departamento}")
    ResponseEntity<?> findByDepartamento(@PathVariable("departamento") String departamento) {
        try {
            return new ResponseEntity<>(encontrarProfessorUseCase.buscarPorDepartamento(departamento), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByStatus/{status}")
    ResponseEntity<?> findByStatus(@PathVariable("status") String status) {
        try {
            return new ResponseEntity<>(encontrarProfessorUseCase.buscarPorStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
