package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.endereco.AtualizarEnderecoUseCase;
import com.exemplo.meuapp.application.port.in.endereco.DeletarEnderecoUseCase;
import com.exemplo.meuapp.application.port.in.endereco.EncontrarEnderecoUseCase;
import com.exemplo.meuapp.common.mapper.EnderecoMapper;
import com.exemplo.meuapp.infrastructure.persistence.entity.EnderecoEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exemplo.meuapp.application.port.in.endereco.CriarEnderecoUseCase;
import com.exemplo.meuapp.domain.model.Endereco;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/endereco")
public class EnderecoController {
    @Autowired
    @Qualifier("enderecoMapperImpl")
    private EnderecoMapper mapper;
    private CriarEnderecoUseCase criarEnderecoUseCase;
    private EncontrarEnderecoUseCase encontrarEnderecoUseCase;
    private DeletarEnderecoUseCase deletarEnderecoUseCase;
    private AtualizarEnderecoUseCase atualizarEnderecoUseCase;

    @Autowired
    public EnderecoController(
            @Qualifier("enderecoMapperImpl") EnderecoMapper mapper,
            CriarEnderecoUseCase criarEnderecoUseCase,
            EncontrarEnderecoUseCase encontrarEnderecoUseCase,
            DeletarEnderecoUseCase deletarEnderecoUseCase,
            AtualizarEnderecoUseCase atualizarEnderecoUseCase) {
        this.mapper = mapper;
        this.criarEnderecoUseCase = criarEnderecoUseCase;
        this.encontrarEnderecoUseCase = encontrarEnderecoUseCase;
        this.deletarEnderecoUseCase = deletarEnderecoUseCase;
        this.atualizarEnderecoUseCase = atualizarEnderecoUseCase;
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestBody Endereco request) {
        try {

            Endereco endereco = criarEnderecoUseCase.criar(request);
            EnderecoEntity response = mapper.toEntity(endereco);

            return new ResponseEntity<EnderecoEntity>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(encontrarEnderecoUseCase.buscarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Endereco endereco = encontrarEnderecoUseCase.buscarPorUuid(UUID.fromString(uuid));
            EnderecoEntity response = mapper.toEntity(endereco);
            return new ResponseEntity<EnderecoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{uuid}")
    ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Endereco request) {
        try {
            Endereco endereco = atualizarEnderecoUseCase.atualizar(UUID.fromString(uuid), request);
            EnderecoEntity response = mapper.toEntity(endereco);
            return new ResponseEntity<EnderecoEntity>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarEnderecoUseCase.deletar(UUID.fromString(uuid));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
