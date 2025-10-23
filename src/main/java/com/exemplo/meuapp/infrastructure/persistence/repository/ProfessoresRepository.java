package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.ProfessoresGateways;
import com.exemplo.meuapp.common.mapper.ProfessoresMapper;
import com.exemplo.meuapp.domain.model.Professores;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProfessoresEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ProfessoresRepository implements ProfessoresGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProfessoresMapper professoresMapper;

    @Override
    public Professores save(Professores professor) {
        ProfessoresEntity entity = professoresMapper.toEntity(professor);
        em.persist(entity);
        return professoresMapper.toDomain(entity);
    }

    @Override
    public Professores getProfessoresById(UUID professorId) {
        ProfessoresEntity entity = em.find(ProfessoresEntity.class, professorId);
        return entity != null ? professoresMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID professorId) {
        ProfessoresEntity entity = em.find(ProfessoresEntity.class, professorId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Professores update(Professores professor) {
        ProfessoresEntity entity = professoresMapper.toEntity(professor);
        ProfessoresEntity merged = em.merge(entity);
        return professoresMapper.toDomain(merged);
    }

    @Override
    public List<Professores> getAllProfessores() {
        var list = em.createQuery(
                "SELECT p FROM ProfessoresEntity p", ProfessoresEntity.class
        ).getResultList();
        return list.stream()
                .map(professoresMapper::toDomain)
                .toList();
    }

    @Override
    public Professores getProfessoresByUsuarioId(UUID usuarioId) {
        var list = em.createQuery(
                "SELECT p FROM ProfessoresEntity p WHERE p.usuarios.uuid = :usuarioId", ProfessoresEntity.class
        ).setParameter("usuarioId", usuarioId)
         .getResultList();
        return list.isEmpty() ? null : professoresMapper.toDomain(list.get(0));
    }

    @Override
    public boolean existsByUsuariosId(UUID uuid) {
        var query = em.createQuery(
                "SELECT COUNT(p) FROM ProfessoresEntity p WHERE p.usuarios.uuid = :usuarioId",
                Long.class
        );
        query.setParameter("usuarioId", uuid);
        Long count = query.getSingleResult();
        return count != null && count > 0;
    }

    @Override
    public List<Professores> getProfessoresByDepartamento(String departamento) {
        var list = em.createQuery(
                        "SELECT p FROM ProfessoresEntity p WHERE p.departamento = :departamento", ProfessoresEntity.class
                ).setParameter("departamento", departamento)
                .getResultList();
        return list.stream()
                .map(professoresMapper::toDomain)
                .toList();
    }

    @Override
    public List<Professores> getProfessoresByStatus(String status) {
        var list = em.createQuery(
                        "SELECT p FROM ProfessoresEntity p WHERE p.status = :status", ProfessoresEntity.class
                ).setParameter("status", status)
                .getResultList();
        return list.stream()
                .map(professoresMapper::toDomain)
                .toList();
    }
}