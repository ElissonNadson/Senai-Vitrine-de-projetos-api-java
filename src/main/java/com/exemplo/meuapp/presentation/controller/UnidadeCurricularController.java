package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.unidadeCurricular.AtualizarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.in.unidadeCurricular.DeletarUnidadeCurricularUseCase;
import com.exemplo.meuapp.application.port.in.unidadeCurricular.EncontrarUnidadeCurricularUseCase;
import com.exemplo.meuapp.common.mapper.UnidadeCurricularMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.UnidadeCurricularEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.unidadeCurricular.CriarUnidadeCurricularUseCase;
import com.exemplo.meuapp.domain.model.UnidadeCurricular;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/unidadeCurricular")
public class UnidadeCurricularController {
    @Autowired
    @Qualifier("unidadeCurricularMapperImpl")
    private UnidadeCurricularMapper mapper;
    private CriarUnidadeCurricularUseCase criarUnidadeCurricularUseCase;
    private EncontrarUnidadeCurricularUseCase encontrarUnidadeCurricularUseCase;
    private DeletarUnidadeCurricularUseCase deletarUnidadeCurricularUseCase;
    private AtualizarUnidadeCurricularUseCase atualizarUnidadeCurricularUseCase;

    @Autowired
    public UnidadeCurricularController(
            @Qualifier("unidadeCurricularMapperImpl")UnidadeCurricularMapper mapper,
            CriarUnidadeCurricularUseCase criarUnidadeCurricularUseCase,
            EncontrarUnidadeCurricularUseCase encontrarUnidadeCurricularUseCase,
            DeletarUnidadeCurricularUseCase deletarUnidadeCurricularUseCase,
            AtualizarUnidadeCurricularUseCase atualizarUnidadeCurricularUseCase) {
        this.mapper = mapper;
        this.criarUnidadeCurricularUseCase = criarUnidadeCurricularUseCase;
        this.encontrarUnidadeCurricularUseCase = encontrarUnidadeCurricularUseCase;
        this.deletarUnidadeCurricularUseCase = deletarUnidadeCurricularUseCase;
        this.atualizarUnidadeCurricularUseCase = atualizarUnidadeCurricularUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody UnidadeCurricular request) {
        try {

            UnidadeCurricular unidadeCurricular = criarUnidadeCurricularUseCase.criar(request);
            UnidadeCurricularEntity response = mapper.toEntity(unidadeCurricular);

            return new ResponseEntity<UnidadeCurricularEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarUnidadeCurricularUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            UnidadeCurricular unidadeCurricular = encontrarUnidadeCurricularUseCase.buscarPorUUID(UUID.fromString(uuid));
            UnidadeCurricularEntity response = mapper.toEntity(unidadeCurricular);
            return new ResponseEntity<UnidadeCurricularEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody UnidadeCurricular request) {
        try {
            UnidadeCurricular unidadeCurricular = atualizarUnidadeCurricularUseCase.atualizar(UUID.fromString(uuid), request);
            UnidadeCurricularEntity response = mapper.toEntity(unidadeCurricular);
            return new ResponseEntity<UnidadeCurricularEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarUnidadeCurricularUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome/{nome}")
    ResponseEntity<?> findByNome(@PathVariable("nome") String nome) {
        try {
            return new ResponseEntity<>(encontrarUnidadeCurricularUseCase.buscarPorNome(nome), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByCargaHoraria/{cargahoraria}")
    ResponseEntity<?> findByCargaHoraria(@PathVariable("cargahoraria") String cargahoraria) {
        try {
            return new ResponseEntity<>(encontrarUnidadeCurricularUseCase.buscarPorCargaHoraria(cargahoraria), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
