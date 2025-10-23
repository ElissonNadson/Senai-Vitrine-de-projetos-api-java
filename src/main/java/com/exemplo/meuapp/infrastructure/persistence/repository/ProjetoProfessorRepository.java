package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.ProjetoProfessorGateways;
import com.exemplo.meuapp.common.mapper.ProjetoProfessorMapper;
import com.exemplo.meuapp.domain.model.ProjetoProfessor;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoProfessorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ProjetoProfessorRepository implements ProjetoProfessorGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProjetoProfessorMapper projetoProfessorMapper;

    @Override
    public ProjetoProfessor save(ProjetoProfessor projetoProfessor) {
        ProjetoProfessorEntity entity = projetoProfessorMapper.toEntity(projetoProfessor);
        em.persist(entity);
        return projetoProfessorMapper.toDomain(entity);
    }

    @Override
    public ProjetoProfessor getProjetoProfessor(UUID projetoProfessorId) {
        ProjetoProfessorEntity entity = em.find(ProjetoProfessorEntity.class, projetoProfessorId);
        return entity != null ? projetoProfessorMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID projetoProfessorId) {
        ProjetoProfessorEntity entity = em.find(ProjetoProfessorEntity.class, projetoProfessorId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public ProjetoProfessor update(ProjetoProfessor projetoProfessor) {
        ProjetoProfessorEntity entity = projetoProfessorMapper.toEntity(projetoProfessor);
        ProjetoProfessorEntity merged = em.merge(entity);
        return projetoProfessorMapper.toDomain(merged);
    }

    @Override
    public List<ProjetoProfessor> getAllProjetosProfessores() {
        var list = em.createQuery(
                "SELECT pp FROM ProjetoProfessorEntity pp", ProjetoProfessorEntity.class
        ).getResultList();
        return list.stream()
                .map(projetoProfessorMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProjetoProfessor> getByProjeto(UUID projetoId) {
        var list = em.createQuery(
                        "SELECT pp FROM ProjetoProfessorEntity pp WHERE pp.projeto.uuid = :projetoId", ProjetoProfessorEntity.class
                ).setParameter("projetoId", projetoId)
                .getResultList();
        return list.stream()
                .map(projetoProfessorMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProjetoProfessor> getByProfessor(UUID professorId) {
        var list = em.createQuery(
                        "SELECT pp FROM ProjetoProfessorEntity pp WHERE pp.professor.uuid = :professorId", ProjetoProfessorEntity.class
                ).setParameter("professorId", professorId)
                .getResultList();
        return list.stream()
                .map(projetoProfessorMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByProjetoAndProfessor(UUID uuid, UUID uuid1) {
        return em.createQuery(
                "SELECT COUNT(pp) > 0 FROM ProjetoProfessorEntity pp WHERE pp.projeto.uuid = :projetoId AND pp.professor.uuid = :professorId", Boolean.class
        )
        .setParameter("projetoId", uuid)
        .setParameter("professorId", uuid1)
        .getSingleResult();
    }

    @Override
    public boolean existsOrientadorByProjeto(UUID uuid) {
        return em.createQuery(
                "SELECT COUNT(pp) > 0 FROM ProjetoProfessorEntity pp WHERE pp.projeto.uuid = :projetoId AND pp.isOrientador = true", Boolean.class
        )
        .setParameter("projetoId", uuid)
        .getSingleResult();
    }
}