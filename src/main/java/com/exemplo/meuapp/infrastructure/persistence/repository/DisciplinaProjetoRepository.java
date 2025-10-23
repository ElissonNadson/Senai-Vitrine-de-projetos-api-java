package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.DisciplinaProjetoGateways;
import com.exemplo.meuapp.common.mapper.DisciplinaProjetoMapper;
import com.exemplo.meuapp.domain.model.DisciplinaProjeto;
import com.exemplo.meuapp.infrastructure.persistence.entity.DisciplinaProjetoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class DisciplinaProjetoRepository implements DisciplinaProjetoGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DisciplinaProjetoMapper disciplinaProjetoMapper;


    @Override
    public DisciplinaProjeto save(DisciplinaProjeto disciplinaProjeto) {
        var entity = disciplinaProjetoMapper.toEntity(disciplinaProjeto);
        em.persist(entity);
        return disciplinaProjetoMapper.toDomain(entity);
    }

    @Override
    public DisciplinaProjeto getDisciplinaProjeto(UUID disciplinaProjetoId) {
        var entity = em.find(DisciplinaProjetoEntity.class, disciplinaProjetoId);
        return entity != null ? disciplinaProjetoMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID disciplinaProjetoId) {
        var entity = em.find(DisciplinaProjetoEntity.class, disciplinaProjetoId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public DisciplinaProjeto update(DisciplinaProjeto disciplinaProjeto) {
        var entity = disciplinaProjetoMapper.toEntity(disciplinaProjeto);
        var merged = em.merge(entity);
        return disciplinaProjetoMapper.toDomain(merged);
    }

    @Override
    public List<DisciplinaProjeto> getAllDisciplinasProjetos() {
        var list = em.createQuery(
            "SELECT d FROM DisciplinaProjetoEntity d", DisciplinaProjetoEntity.class
        ).getResultList();
        return list.stream()
                .map(disciplinaProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<DisciplinaProjeto> getByDisciplina(UUID disciplinaId) {
        var list = em.createQuery(
            "SELECT d FROM DisciplinaProjetoEntity d WHERE d.disciplina.id = :disciplinaId", DisciplinaProjetoEntity.class
        ).setParameter("disciplinaId", disciplinaId)
         .getResultList();
        return list.stream()
                .map(disciplinaProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<DisciplinaProjeto> getByProjeto(UUID projetoId) {
        var list = em.createQuery(
            "SELECT d FROM DisciplinaProjetoEntity d WHERE d.projeto.id = :projetoId", DisciplinaProjetoEntity.class
        ).setParameter("projetoId", projetoId)
         .getResultList();
        return list.stream()
                .map(disciplinaProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<DisciplinaProjeto> getByDisciplinaAndProjeto(UUID disciplinaId, UUID projetoId) {
        var list = em.createQuery(
            "SELECT d FROM DisciplinaProjetoEntity d WHERE d.disciplina.id = :disciplinaId AND d.projeto.id = :projetoId", DisciplinaProjetoEntity.class
        ).setParameter("disciplinaId", disciplinaId)
         .setParameter("projetoId", projetoId)
         .getResultList();
        return list.stream()
                .map(disciplinaProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByDisciplinaAndProjeto(UUID uuid, UUID uuid1) {
        var query = em.createQuery(
            "SELECT COUNT(d) > 0 FROM DisciplinaProjetoEntity d WHERE d.disciplina.id = :disciplinaId AND d.projeto.id = :projetoId", Boolean.class
        );
        query.setParameter("disciplinaId", uuid);
        query.setParameter("projetoId", uuid1);
        return query.getSingleResult();
    }
}
