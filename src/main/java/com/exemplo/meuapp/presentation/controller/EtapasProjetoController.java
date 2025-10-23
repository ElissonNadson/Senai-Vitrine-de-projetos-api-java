package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.etapasProjeto.AtualizarEtapasProjetoUseCase;
import com.exemplo.meuapp.application.port.in.etapasProjeto.DeletarEtapasProjetoUseCase;
import com.exemplo.meuapp.application.port.in.etapasProjeto.EncontrarEtapasProjetoUseCase;
import com.exemplo.meuapp.common.mapper.EtapasProjetoMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.EtapasProjetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.etapasProjeto.CriarEtapasProjetoUseCase;
import com.exemplo.meuapp.domain.model.EtapasProjeto;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/etapasProjeto")
public class EtapasProjetoController {

    @Autowired
    @Qualifier("etapasProjetoMapperImpl")
    private EtapasProjetoMapper mapper;
    private CriarEtapasProjetoUseCase criarEtapasProjetoUseCase;
    private EncontrarEtapasProjetoUseCase encontrarEtapasProjetoUseCase;
    private DeletarEtapasProjetoUseCase deletarEtapasProjetoUseCase;
    private AtualizarEtapasProjetoUseCase atualizarEtapasProjetoUseCase;


    @Autowired
    public EtapasProjetoController(
            @Qualifier("etapasProjetoMapperImpl") EtapasProjetoMapper mapper,
                            CriarEtapasProjetoUseCase criarEtapasProjetoUseCase,
                            EncontrarEtapasProjetoUseCase encontrarEtapasProjetoUseCase,
                            DeletarEtapasProjetoUseCase deletarEtapasProjetoUseCase,
                            AtualizarEtapasProjetoUseCase atualizarEtapasProjetoUseCase) {
        this.mapper = mapper;
        this.criarEtapasProjetoUseCase = criarEtapasProjetoUseCase;
        this.encontrarEtapasProjetoUseCase = encontrarEtapasProjetoUseCase;
        this.deletarEtapasProjetoUseCase = deletarEtapasProjetoUseCase;
        this.atualizarEtapasProjetoUseCase = atualizarEtapasProjetoUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody EtapasProjeto request) {
        try {

            EtapasProjeto etapasProjeto = criarEtapasProjetoUseCase.criar(request);
            EtapasProjetoEntity response = mapper.toEntity(etapasProjeto);

            return new ResponseEntity<EtapasProjetoEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarEtapasProjetoUseCase.buscarTodas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            EtapasProjeto etapasProjeto = encontrarEtapasProjetoUseCase.buscarPorUuid(UUID.fromString(uuid));
            EtapasProjetoEntity response = mapper.toEntity(etapasProjeto);
            return new ResponseEntity<EtapasProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody EtapasProjeto request) {
        try {
            EtapasProjeto etapasProjeto = atualizarEtapasProjetoUseCase.atualizar(UUID.fromString(uuid), request);
            EtapasProjetoEntity response = mapper.toEntity(etapasProjeto);
            return new ResponseEntity<EtapasProjetoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarEtapasProjetoUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByProjeto/{projeto}")
    ResponseEntity<?> findByProjeto(@PathVariable("projeto") String projeto) {
        try {
            return new ResponseEntity<>(encontrarEtapasProjetoUseCase.buscarPorProjeto(UUID.fromString(projeto)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/findByStatus/{status}")
    ResponseEntity<?> findByStatus(@PathVariable("status") String status) {
        try {
            return new ResponseEntity<>(encontrarEtapasProjetoUseCase.buscarPorStatus(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
