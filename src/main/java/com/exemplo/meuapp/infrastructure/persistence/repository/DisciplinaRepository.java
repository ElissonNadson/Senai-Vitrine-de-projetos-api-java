package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.DisciplinaGateways;
import com.exemplo.meuapp.common.mapper.DisciplinaMapper;
import com.exemplo.meuapp.domain.model.Disciplina;
import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class DisciplinaRepository implements DisciplinaGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DisciplinaMapper disciplinaMapper;

    @Override
    public Disciplina save(Disciplina disciplina) {
        DisciplinaEntity entity = disciplinaMapper.toEntity(disciplina);
        em.persist(entity);
        return disciplinaMapper.toDomain(entity);
    }

    @Override
    public Disciplina getDisciplina(UUID disciplinaId) {
        DisciplinaEntity entity = em.find(DisciplinaEntity.class, disciplinaId);
        return entity != null ? disciplinaMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID disciplinaId) {
        DisciplinaEntity entity = em.find(DisciplinaEntity.class, disciplinaId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Disciplina update(Disciplina disciplina) {
        DisciplinaEntity entity = disciplinaMapper.toEntity(disciplina);
        DisciplinaEntity merged = em.merge(entity);
        return disciplinaMapper.toDomain(merged);
    }

    @Override
    public List<Disciplina> getAllDisciplinas() {
        var list = em.createQuery(
                "SELECT a FROM DisciplinaEntity a", DisciplinaEntity.class
        ).getResultList();
        return list.stream()
                .map(disciplinaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Disciplina> getDisciplinaByNome(String nome) {
        var list = em.createQuery(
                "SELECT d FROM DisciplinaEntity d WHERE LOWER(d.nome) = LOWER(:nome)", DisciplinaEntity.class
        ).setParameter("nome", nome)
         .getResultList();
        return list.stream()
                .map(disciplinaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Disciplina> getDisciplinaByProfessor(UUID professorId) {
        var list = em.createQuery(
                "SELECT d FROM DisciplinaEntity d WHERE d.professor.uuid = :professorId", DisciplinaEntity.class
        ).setParameter("professorId", professorId)
         .getResultList();
        return list.stream()
                .map(disciplinaMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByNome(String nome) {
        var query = em.createQuery(
                "SELECT COUNT(d) FROM DisciplinaEntity d WHERE LOWER(d.nome) = LOWER(:nome)", Long.class
        );
        query.setParameter("nome", nome);
        Long count = query.getSingleResult();
        return count > 0;
    }

}
