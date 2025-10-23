package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.AnexoEtapaGateways;
import com.exemplo.meuapp.common.mapper.AnexoEtapaMapper;
import com.exemplo.meuapp.domain.model.AnexoEtapa;
import com.exemplo.meuapp.infrastructure.persistence.entity.AnexoEtapaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class AnexoEtapaRepository implements AnexoEtapaGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private AnexoEtapaMapper anexoEtapaMapper;

    @Override
    public AnexoEtapa save(AnexoEtapa anexoEtapa) {
        AnexoEtapaEntity entity = anexoEtapaMapper.toEntity(anexoEtapa);
        em.persist(entity);
        return anexoEtapaMapper.toDomain(entity);
    }

    @Override
    public AnexoEtapa getAnexoEtapa(UUID anexoEtapaId) {
        AnexoEtapaEntity entity = em.find(AnexoEtapaEntity.class, anexoEtapaId);
        return entity != null ? anexoEtapaMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID anexoEtapaId) {
        AnexoEtapaEntity entity = em.find(AnexoEtapaEntity.class, anexoEtapaId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public AnexoEtapa update(AnexoEtapa anexoEtapa) {
        AnexoEtapaEntity entity = anexoEtapaMapper.toEntity(anexoEtapa);
        AnexoEtapaEntity merged = em.merge(entity);
        return anexoEtapaMapper.toDomain(merged);
    }

    @Override
    public List<AnexoEtapa> getAllAnexosEtapa() {
        var list = em.createQuery(
                "SELECT a FROM AnexoEtapaEntity a", AnexoEtapaEntity.class
        ).getResultList();
        return list.stream()
                .map(anexoEtapaMapper::toDomain)
                .toList();
    }

    @Override
    public List<AnexoEtapa> getAnexosByEtapaId(UUID etapaId) {
        var query = em.createQuery(
                "SELECT a FROM AnexoEtapaEntity a WHERE a.etapa.id = :etapaId", AnexoEtapaEntity.class
        );
        query.setParameter("etapaId", etapaId);
        return query.getResultList().stream()
                .map(anexoEtapaMapper::toDomain)
                .toList();
    }

    @Override
    public List<AnexoEtapa> getAnexosByEtapaIdAndTipo(UUID etapaId, String tipo) {
        var query = em.createQuery(
                "SELECT a FROM AnexoEtapaEntity a WHERE a.etapa.id = :etapaId AND a.tipo = :tipo", AnexoEtapaEntity.class
        );
        query.setParameter("etapaId", etapaId);
        query.setParameter("tipo", tipo);
        return query.getResultList().stream()
                .map(anexoEtapaMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByEtapaAndNomeArquivo(UUID uuid, String nomeArquivo) {
        var query = em.createQuery(
                "SELECT COUNT(a) > 0 FROM AnexoEtapaEntity a WHERE a.etapa.id = :etapaId AND a.nomeArquivo = :nomeArquivo",
                Boolean.class
        );
        query.setParameter("etapaId", uuid);
        query.setParameter("nomeArquivo", nomeArquivo);
        return query.getSingleResult();
    }
}
