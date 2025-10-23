package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.projetoProfessor.AtualizarProjetoProfessorUseCase;
import com.exemplo.meuapp.application.port.in.projetoProfessor.CriarProjetoProfessorUseCase;
import com.exemplo.meuapp.application.port.in.projetoProfessor.DeletarProjetoProfessorUseCase;
import com.exemplo.meuapp.application.port.in.projetoProfessor.EncontrarProjetoProfessorUseCase;
import com.exemplo.meuapp.common.mapper.ProjetoProfessorMapper;
import com.exemplo.meuapp.domain.model.ProjetoProfessor;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoProfessorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/projetoProfessor")
public class ProjetoProfessorController {
    @Autowired
    @Qualifier("projetoProfessorMapperImpl")
    private ProjetoProfessorMapper mapper;
    private CriarProjetoProfessorUseCase criarProjetoProfessorUseCase;
    private EncontrarProjetoProfessorUseCase encontrarProjetoProfessorUseCase;
    private DeletarProjetoProfessorUseCase deletarProjetoProfessorUseCase;
    private AtualizarProjetoProfessorUseCase atualizarProjetoProfessorUseCase;

    @Autowired
    public ProjetoProfessorController(
            @Qualifier("projetoProfessorMapperImpl")ProjetoProfessorMapper mapper,
                                      CriarProjetoProfessorUseCase criarProjetoProfessorUseCase,
                                      EncontrarProjetoProfessorUseCase encontrarProjetoProfessorUseCase,
                                      DeletarProjetoProfessorUseCase deletarProjetoProfessorUseCase,
                                      AtualizarProjetoProfessorUseCase atualizarProjetoProfessorUseCase) {
        this.mapper = mapper;
        this.criarProjetoProfessorUseCase = criarProjetoProfessorUseCase;
        this.encontrarProjetoProfessorUseCase = encontrarProjetoProfessorUseCase;
        this.deletarProjetoProfessorUseCase = deletarProjetoProfessorUseCase;
        this.atualizarProjetoProfessorUseCase = atualizarProjetoProfessorUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody ProjetoProfessor request) {
        try {

            ProjetoProfessor projetoProfessor = criarProjetoProfessorUseCase.criar(request);
            ProjetoProfessorEntity response = mapper.toEntity(projetoProfessor);

            return new ResponseEntity<ProjetoProfessorEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarProjetoProfessorUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            ProjetoProfessor projetoProfessor = encontrarProjetoProfessorUseCase.buscarPorUUID(UUID.fromString(uuid));
            ProjetoProfessorEntity response = mapper.toEntity(projetoProfessor);
            return new ResponseEntity<ProjetoProfessorEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody ProjetoProfessor request) {
        try {
            ProjetoProfessor projetoProfessor = atualizarProjetoProfessorUseCase.atualizar(UUID.fromString(uuid), request);
            ProjetoProfessorEntity response = mapper.toEntity(projetoProfessor);
            return new ResponseEntity<ProjetoProfessorEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarProjetoProfessorUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByProfessor/{professor}")
    ResponseEntity<?> findByProfessor(@PathVariable("professor") String professor) {
        try {
            return new ResponseEntity<>(encontrarProjetoProfessorUseCase.buscarPorProfessor(UUID.fromString(professor)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByProjeto/{projeto}")
    ResponseEntity<?> findByProjeto(@PathVariable("projeto") String projeto) {
        try {
            return new ResponseEntity<>(encontrarProjetoProfessorUseCase.buscarPorProjeto(UUID.fromString(projeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
