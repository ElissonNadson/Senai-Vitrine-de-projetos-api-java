package com.exemplo.meuapp.presentation.controller;

import com.exemplo.meuapp.application.port.in.curso.CriarCursoUseCase;
import com.exemplo.meuapp.application.port.in.curso.EncontrarCursoUseCase;
import com.exemplo.meuapp.application.port.in.curso.AtualizarCursoUseCase;
import com.exemplo.meuapp.application.port.in.curso.DeletarCursoUseCase;
import com.exemplo.meuapp.domain.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/senai/curso")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CriarCursoUseCase criarCursoUseCase;
    
    @Autowired
    private EncontrarCursoUseCase encontrarCursoUseCase;
    
    @Autowired
    private AtualizarCursoUseCase atualizarCursoUseCase;
    
    @Autowired
    private DeletarCursoUseCase deletarCursoUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Curso request) {
        try {
            Curso curso = criarCursoUseCase.criar(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro ao criar curso: " + e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Curso> cursos = encontrarCursoUseCase.findAll();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar cursos: " + e.getMessage());
        }
    }

    @GetMapping("/findByUUID/{uuid}")
    public ResponseEntity<?> findByUUID(@PathVariable("uuid") String uuid) {
        try {
            Curso curso = encontrarCursoUseCase.findByUUID(UUID.fromString(uuid));
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Curso não encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/findByCodigo/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable("codigo") String codigo) {
        try {
            Curso curso = encontrarCursoUseCase.findByCodigo(codigo);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Curso não encontrado: " + e.getMessage());
        }
    }

    @GetMapping("/findByNivel/{nivel}")
    public ResponseEntity<?> findByNivel(@PathVariable("nivel") String nivel) {
        try {
            List<Curso> cursos = encontrarCursoUseCase.findByNivel(nivel);
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar cursos: " + e.getMessage());
        }
    }

    @GetMapping("/findAtivos")
    public ResponseEntity<?> findAtivos() {
        try {
            List<Curso> cursos = encontrarCursoUseCase.findAtivos();
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro ao buscar cursos ativos: " + e.getMessage());
        }
    }

    @PutMapping("/update/{uuid}")
    public ResponseEntity<?> update(@PathVariable("uuid") String uuid, @RequestBody Curso request) {
        try {
            request.setUuid(UUID.fromString(uuid));
            Curso curso = atualizarCursoUseCase.atualizar(request);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") String uuid) {
        try {
            deletarCursoUseCase.deletar(UUID.fromString(uuid));
            return ResponseEntity.ok("Curso deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro ao deletar curso: " + e.getMessage());
        }
    }
}
