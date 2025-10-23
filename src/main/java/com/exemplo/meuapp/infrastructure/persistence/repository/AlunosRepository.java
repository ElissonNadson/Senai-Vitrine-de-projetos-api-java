package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.AlunosGateways;
import com.exemplo.meuapp.common.mapper.AlunosMapper;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Alunos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import com.exemplo.meuapp.infrastructure.persistence.entity.AlunosEntity;

@Repository
@Transactional
public class AlunosRepository implements AlunosGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AlunosMapper mapper;

    @Override
    public Alunos save(Alunos alunos) {
        AlunosEntity entity = mapper.toEntity(alunos);
        em.persist(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Alunos getAlunosByEmail(String email) {
        var query = em.createQuery(
                "SELECT a FROM AlunosEntity a WHERE a.usuarios.email = :email",
                AlunosEntity.class
        );
        query.setParameter("email", email);
        return query.getResultStream()
                .findFirst()
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public void delete(UUID alunosId) {
        AlunosEntity entity = em.find(AlunosEntity.class, alunosId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Alunos update(Alunos alunos) {
        AlunosEntity entity = mapper.toEntity(alunos);
        AlunosEntity merged = em.merge(entity);
        return mapper.toDomain(merged);
    }

    @Override
    public Alunos getAlunos(UUID alunosId) {
        AlunosEntity entity = em.find(AlunosEntity.class, alunosId);
        return entity != null ? mapper.toDomain(entity) : null;
    }

    @Override
    public Alunos getAlunosByMatricula(String matricula) {
        var query = em.createQuery(
                "SELECT a FROM AlunosEntity a WHERE a.matricula = :mat",
                AlunosEntity.class
        );
        query.setParameter("mat", matricula);
        return query.getResultStream()
                .findFirst()
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public List<Alunos> getAlunosByStatus(UsuariosStatus status) {
        var query = em.createQuery(
                "SELECT a FROM AlunosEntity a WHERE a.status = :st",
                AlunosEntity.class
        );
        query.setParameter("st", status);
        return query.getResultList()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<Alunos> getAllAlunos() {
        var list = em.createQuery(
                "SELECT a FROM AlunosEntity a", AlunosEntity.class
        ).getResultList();
        return list.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Alunos getAlunosByUsuarioId(UUID usuarioId) {
        var query = em.createQuery(
                "SELECT a FROM AlunosEntity a WHERE a.usuarios.uuid = :uid",
                AlunosEntity.class
        );
        query.setParameter("uid", usuarioId);
        return query.getResultStream()
                .findFirst()
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        var query = em.createQuery(
                "SELECT COUNT(a) FROM AlunosEntity a WHERE a.usuarios.email = :email",
                Long.class
        );
        query.setParameter("email", email);
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }

    @Override
    public boolean existsByMatricula(String matricula) {
        var query = em.createQuery(
                "SELECT COUNT(a) FROM AlunosEntity a WHERE a.matricula = :matricula",
                Long.class
        );
        query.setParameter("matricula", matricula);
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }

}