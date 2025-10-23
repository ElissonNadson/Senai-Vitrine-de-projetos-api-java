package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.UnidadeCurricularGateways;
import com.exemplo.meuapp.common.mapper.UnidadeCurricularMapper;
import com.exemplo.meuapp.domain.model.UnidadeCurricular;
import com.exemplo.meuapp.infrastructure.persistence.entity.UnidadeCurricularEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class UnidadeCurricularRepository implements UnidadeCurricularGateways {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UnidadeCurricularMapper unidadeCurricularMapper;

    @Override
    public UnidadeCurricular save(UnidadeCurricular unidadeCurricular) {
        UnidadeCurricularEntity entity = unidadeCurricularMapper.toEntity(unidadeCurricular);
        em.persist(entity);
        return unidadeCurricularMapper.toDomain(entity);
    }

    @Override
    public UnidadeCurricular getUnidadeCurricular(UUID unidadeCurricularId) {
        UnidadeCurricularEntity entity = em.find(UnidadeCurricularEntity.class, unidadeCurricularId);
        return entity != null ? unidadeCurricularMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID unidadeCurricularId) {
        UnidadeCurricularEntity entity = em.find(UnidadeCurricularEntity.class, unidadeCurricularId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public UnidadeCurricular update(UnidadeCurricular unidadeCurricular) {
        UnidadeCurricularEntity entity = unidadeCurricularMapper.toEntity(unidadeCurricular);
        UnidadeCurricularEntity merged = em.merge(entity);
        return unidadeCurricularMapper.toDomain(merged);
    }

    @Override
    public List<UnidadeCurricular> getAllUnidadesCurriculares() {
        var list = em.createQuery(
                "SELECT u FROM UnidadeCurricularEntity u", UnidadeCurricularEntity.class
        ).getResultList();
        return list.stream()
                .map(unidadeCurricularMapper::toDomain)
                .toList();
    }

    @Override
    public List<UnidadeCurricular> getUnidadeCurricularByNome(String nome) {
        var list = em.createQuery(
                        "SELECT u FROM UnidadeCurricularEntity u WHERE LOWER(u.nome) LIKE LOWER(:nome)", UnidadeCurricularEntity.class
                ).setParameter("nome", "%" + nome + "%")
                .getResultList();
        return list.stream()
                .map(unidadeCurricularMapper::toDomain)
                .toList();
    }

    @Override
    public List<UnidadeCurricular> getUnidadeCurricularByCargaHoraria(String cargaHoraria) {
        var list = em.createQuery(
                        "SELECT u FROM UnidadeCurricularEntity u WHERE u.cargaHoraria = :cargaHoraria", UnidadeCurricularEntity.class
                ).setParameter("cargaHoraria", cargaHoraria)
                .getResultList();
        return list.stream()
                .map(unidadeCurricularMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByNome(String nome) {
        var count = em.createQuery(
                "SELECT COUNT(u) FROM UnidadeCurricularEntity u WHERE LOWER(u.nome) = LOWER(:nome)", Long.class
        ).setParameter("nome", nome)
         .getSingleResult();
        return count > 0;
    }
}