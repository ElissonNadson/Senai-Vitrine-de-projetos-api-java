package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.EnderecoGateways;
import com.exemplo.meuapp.common.mapper.EnderecoMapper;
import com.exemplo.meuapp.domain.model.Endereco;
import com.exemplo.meuapp.infrastructure.persistence.entity.EnderecoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class EnderecoRepository implements EnderecoGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EnderecoMapper enderecoMapper;

    @Override
    public Endereco save(Endereco endereco) {
        EnderecoEntity entity = enderecoMapper.toEntity(endereco);
        em.persist(entity);
        return enderecoMapper.toDomain(entity);
    }

    @Override
    public Endereco getEnderecoById(UUID enderecoId) {
        EnderecoEntity entity = em.find(EnderecoEntity.class, enderecoId);
        return entity != null ? enderecoMapper.toDomain(entity) : null;
    }

    @Override
    public void delete(UUID enderecoId) {
        EnderecoEntity entity = em.find(EnderecoEntity.class, enderecoId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Endereco update(Endereco endereco) {
        EnderecoEntity entity = enderecoMapper.toEntity(endereco);
        EnderecoEntity merged = em.merge(entity);
        return enderecoMapper.toDomain(merged);
    }

    @Override
    public List<Endereco> getAllEnderecos() {
        var list = em.createQuery(
                "SELECT e FROM EnderecoEntity e", EnderecoEntity.class
        ).getResultList();
        return list.stream()
                .map(enderecoMapper::toDomain)
                .toList();
    }
}
