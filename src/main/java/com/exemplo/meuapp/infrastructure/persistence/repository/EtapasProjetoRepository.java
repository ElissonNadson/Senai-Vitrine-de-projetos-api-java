package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.EtapasProjetoGateways;
import com.exemplo.meuapp.common.mapper.EtapasProjetoMapper;
import com.exemplo.meuapp.domain.model.EtapasProjeto;
import com.exemplo.meuapp.infrastructure.persistence.entity.EtapasProjetoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class EtapasProjetoRepository implements EtapasProjetoGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EtapasProjetoMapper etapasProjetoMapper;

    @Override
    public EtapasProjeto save(EtapasProjeto etapaProjeto) {
        EtapasProjetoEntity entity = etapasProjetoMapper.toEntity(etapaProjeto);
        em.persist(entity);
        return etapasProjetoMapper.toDomain(entity);
    }

    @Override
    public EtapasProjeto getEtapaById(UUID etapaId) {
        EtapasProjetoEntity entity = em.find(EtapasProjetoEntity.class, etapaId);
        return entity != null ? etapasProjetoMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID etapaId) {
        EtapasProjetoEntity entity = em.find(EtapasProjetoEntity.class, etapaId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public EtapasProjeto update(EtapasProjeto etapaProjeto) {
        EtapasProjetoEntity entity = etapasProjetoMapper.toEntity(etapaProjeto);
        EtapasProjetoEntity merged = em.merge(entity);
        return etapasProjetoMapper.toDomain(merged);
    }

    @Override
    public List<EtapasProjeto> getAllEtapas() {
        var list = em.createQuery(
                "SELECT e FROM EtapasProjetoEntity e", EtapasProjetoEntity.class
        ).getResultList();
        return list.stream()
                .map(etapasProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<EtapasProjeto> getEtapasByProjetoId(UUID projetoId) {
        var list = em.createQuery(
                "SELECT e FROM EtapasProjetoEntity e WHERE e.projeto.id = :projetoId", EtapasProjetoEntity.class
        ).setParameter("projetoId", projetoId)
         .getResultList();
        return list.stream()
                .map(etapasProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<EtapasProjeto> getEtapasByStatus(String status) {
        var list = em.createQuery(
                "SELECT e FROM EtapasProjetoEntity e WHERE e.status = :status", EtapasProjetoEntity.class
        ).setParameter("status", status)
         .getResultList();
        return list.stream()
                .map(etapasProjetoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByProjetoAndNomeEtapa(UUID uuid, String nomeEtapa) {
        var query = em.createQuery(
                "SELECT COUNT(e) > 0 FROM EtapasProjetoEntity e WHERE e.projeto.id = :projetoId AND e.nomeEtapa = :nomeEtapa",
                Boolean.class
        );
        query.setParameter("projetoId", uuid);
        query.setParameter("nomeEtapa", nomeEtapa);
        return query.getSingleResult();
    }

    @Override
    public boolean existsByProjetoAndOrdem(UUID uuid, int ordem) {
        var query = em.createQuery(
                "SELECT COUNT(e) > 0 FROM EtapasProjetoEntity e WHERE e.projeto.id = :projetoId AND e.ordem = :ordem",
                Boolean.class
        );
        query.setParameter("projetoId", uuid);
        query.setParameter("ordem", ordem);
        return query.getSingleResult();
    }
}