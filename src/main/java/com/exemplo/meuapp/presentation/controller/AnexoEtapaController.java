package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.anexoEtapa.AtualizarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.CriarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.DeletarAnexoEtapaUseCase;
import com.exemplo.meuapp.application.port.in.anexoEtapa.EncontrarAnexoEtapaUseCase;
import com.exemplo.meuapp.common.mapper.AnexoEtapaMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.AnexoEtapaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.domain.model.AnexoEtapa;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/AnexoEtapa")
public class AnexoEtapaController {

    @Autowired
    @Qualifier("anexoEtapaMapperImpl")
    private AnexoEtapaMapper mapper;
    private CriarAnexoEtapaUseCase criarAnexoEtapaUseCase;
    private EncontrarAnexoEtapaUseCase encontrarAnexoEtapaUseCase;
    private DeletarAnexoEtapaUseCase deletarAnexoEtapaUseCase;
    private AtualizarAnexoEtapaUseCase atualizarAnexoEtapaUseCase;

    @Autowired
    public AnexoEtapaController(
            @Qualifier("anexoEtapaMapperImpl") AnexoEtapaMapper mapper,
            CriarAnexoEtapaUseCase criarAnexoEtapaUseCase,
            EncontrarAnexoEtapaUseCase encontrarAnexoEtapaUseCase,
            DeletarAnexoEtapaUseCase deletarAnexoEtapaUseCase,
            AtualizarAnexoEtapaUseCase atualizarAnexoEtapaUseCase) {
        this.mapper = mapper;
        this.criarAnexoEtapaUseCase = criarAnexoEtapaUseCase;
        this.encontrarAnexoEtapaUseCase = encontrarAnexoEtapaUseCase;
        this.deletarAnexoEtapaUseCase = deletarAnexoEtapaUseCase;
        this.atualizarAnexoEtapaUseCase = atualizarAnexoEtapaUseCase;
    }

    public @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody AnexoEtapa request) {
        try {

            AnexoEtapa AnexoEtapa = criarAnexoEtapaUseCase.criar(request);
            AnexoEtapaEntity response = mapper.toEntity(AnexoEtapa);

            return new ResponseEntity<AnexoEtapaEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarAnexoEtapaUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            AnexoEtapa AnexoEtapa = encontrarAnexoEtapaUseCase.buscarPorUuid(UUID.fromString(uuid));
            AnexoEtapaEntity response = mapper.toEntity(AnexoEtapa);
            return new ResponseEntity<AnexoEtapaEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody AnexoEtapa request) {
        try {
            AnexoEtapa AnexoEtapa = atualizarAnexoEtapaUseCase.atualizar(UUID.fromString(uuid), request);
            AnexoEtapaEntity response = mapper.toEntity(AnexoEtapa);
            return new ResponseEntity<AnexoEtapaEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarAnexoEtapaUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findByEtapa/{etapa_uuid}")
    ResponseEntity<?> findByEtapa(@PathVariable("etapa_uuid") String etapa_uuid) {
        try {
            return new ResponseEntity<>(encontrarAnexoEtapaUseCase.buscarPorEtapaId(UUID.fromString(etapa_uuid)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public @GetMapping("/findByEtapaETipo/{etapa_uuid}/{tipo}")
    ResponseEntity<?> findByEtapaETipo(@PathVariable("etapa_uuid") String etapa_uuid, @PathVariable("tipo") String tipo) {
        try {
            return new ResponseEntity<>(encontrarAnexoEtapaUseCase.buscarPorEtapaIdETipo(UUID.fromString(etapa_uuid), tipo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
