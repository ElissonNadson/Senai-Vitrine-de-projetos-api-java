package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.ProjetoGateways;
import com.exemplo.meuapp.common.mapper.ProjetoMapper;
import com.exemplo.meuapp.domain.model.Projeto;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ProjetoRepository implements ProjetoGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProjetoMapper projetoMapper;

    @Override
    public Projeto save(Projeto projeto) {
        ProjetoEntity entity = projetoMapper.toEntity(projeto);
        em.persist(entity);
        return projetoMapper.toDomain(entity);
    }

    @Override
    public Projeto getProjetoById(UUID projetoId) {
        ProjetoEntity entity = em.find(ProjetoEntity.class, projetoId);
        return entity != null ? projetoMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID projetoId) {
        ProjetoEntity entity = em.find(ProjetoEntity.class, projetoId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Projeto update(Projeto projeto) {
        ProjetoEntity entity = projetoMapper.toEntity(projeto);
        ProjetoEntity merged = em.merge(entity);
        return projetoMapper.toDomain(merged);
    }

    @Override
    public List<Projeto> getAllProjetos() {
        var list = em.createQuery(
                "SELECT p FROM ProjetoEntity p", ProjetoEntity.class
        ).getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByTitulo(String titulo) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE LOWER(p.titulo) LIKE LOWER(:titulo)", ProjetoEntity.class
                ).setParameter("titulo", "%" + titulo + "%")
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByStatus(String status) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE p.status = :status", ProjetoEntity.class
                ).setParameter("status", status)
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByCurso(String curso) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE p.curso = :curso", ProjetoEntity.class
                ).setParameter("curso", curso)
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByTurma(String turma) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE p.turma = :turma", ProjetoEntity.class
                ).setParameter("turma", turma)
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByLider(UUID liderProjetoId) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE p.liderProjeto.uuid = :liderProjetoId", ProjetoEntity.class
                ).setParameter("liderProjetoId", liderProjetoId)
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Projeto> getProjetosByUnidadeCurricular(UUID unidadeCurricularId) {
        var list = em.createQuery(
                        "SELECT p FROM ProjetoEntity p WHERE p.unidadeCurricular.uuid = :unidadeCurricularId", ProjetoEntity.class
                ).setParameter("unidadeCurricularId", unidadeCurricularId)
                .getResultList();
        return list.stream()
                .map(projetoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCodigo(String codigo) {
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM ProjetoEntity p WHERE p.codigo = :codigo", Long.class
        ).setParameter("codigo", codigo).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByTitulo(String titulo) {
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM ProjetoEntity p WHERE LOWER(p.titulo) = LOWER(:titulo)", Long.class
        ).setParameter("titulo", titulo).getSingleResult();
        return count > 0;
    }
}