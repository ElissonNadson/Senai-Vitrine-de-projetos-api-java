package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.ProjetoAlunoGateways;
import com.exemplo.meuapp.common.mapper.ProjetoAlunoMapper;
import com.exemplo.meuapp.domain.model.ProjetoAluno;
import com.exemplo.meuapp.infrastructure.persistence.entity.ProjetoAlunoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class ProjetoAlunoRepository implements ProjetoAlunoGateways {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ProjetoAlunoMapper projetoAlunoMapper;

    @Override
    public ProjetoAluno save(ProjetoAluno projetoAluno) {
        ProjetoAlunoEntity entity = projetoAlunoMapper.toEntity(projetoAluno);
        em.persist(entity);
        return projetoAlunoMapper.toDomain(entity);
    }

    @Override
    public ProjetoAluno getProjetoAluno(UUID projetoAlunoId) {
        ProjetoAlunoEntity entity = em.find(ProjetoAlunoEntity.class, projetoAlunoId);
        return entity != null ? projetoAlunoMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID projetoAlunoId) {
        ProjetoAlunoEntity entity = em.find(ProjetoAlunoEntity.class, projetoAlunoId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public ProjetoAluno update(ProjetoAluno projetoAluno) {
        ProjetoAlunoEntity entity = projetoAlunoMapper.toEntity(projetoAluno);
        ProjetoAlunoEntity merged = em.merge(entity);
        return projetoAlunoMapper.toDomain(merged);
    }

    @Override
    public List<ProjetoAluno> getAllProjetosAlunos() {
        var list = em.createQuery(
                "SELECT pa FROM ProjetoAlunoEntity pa", ProjetoAlunoEntity.class
        ).getResultList();
        return list.stream()
                .map(projetoAlunoMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProjetoAluno> getProjetosAlunosByProjeto(UUID projetoId) {
        var list = em.createQuery(
                        "SELECT pa FROM ProjetoAlunoEntity pa WHERE pa.projeto.id = :projetoId", ProjetoAlunoEntity.class
                ).setParameter("projetoId", projetoId)
                .getResultList();
        return list.stream()
                .map(projetoAlunoMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProjetoAluno> getProjetosAlunosByAluno(UUID alunoId) {
        var list = em.createQuery(
                        "SELECT pa FROM ProjetoAlunoEntity pa WHERE pa.aluno.id = :alunoId", ProjetoAlunoEntity.class
                ).setParameter("alunoId", alunoId)
                .getResultList();
        return list.stream()
                .map(projetoAlunoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByProjetoAndAluno(UUID uuid, UUID uuid1) {
        Long count = em.createQuery(
                "SELECT COUNT(pa) FROM ProjetoAlunoEntity pa WHERE pa.projeto.id = :projetoId AND pa.aluno.id = :alunoId", Long.class
        )
        .setParameter("projetoId", uuid)
        .setParameter("alunoId", uuid1)
        .getSingleResult();

        return count > 0;
    }


}