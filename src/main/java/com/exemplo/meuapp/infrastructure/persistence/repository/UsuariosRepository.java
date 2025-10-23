package com.exemplo.meuapp.infrastructure.persistence.repository;

import com.exemplo.meuapp.application.port.out.UsuariosGateways;
import com.exemplo.meuapp.common.mapper.UsuariosMapper;
import com.exemplo.meuapp.domain.enums.UsuariosStatus;
import com.exemplo.meuapp.domain.model.Usuarios;
import com.exemplo.meuapp.infrastructure.persistence.entity.UsuariosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UsuariosRepository implements UsuariosGateways {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsuariosMapper mapper;

    @Override
    public Usuarios save(Usuarios usuarios) {
        usuarios.setSenha(new BCryptPasswordEncoder().encode(usuarios.getSenha()));
        UsuariosEntity entity = mapper.toEntity(usuarios);

        entity.setCriadoEm(LocalDateTime.now());
        entity.setAtualizadoEm(LocalDateTime.now());
        em.persist(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Usuarios getUsuariosByEmail(String email) {
        TypedQuery<UsuariosEntity> query = em.createQuery(
                "SELECT u FROM UsuariosEntity u WHERE u.email = :email",
                UsuariosEntity.class
        );
        query.setParameter("email", email);
        List<UsuariosEntity> results = query.getResultList();
        return results.isEmpty()
                ? null
                : mapper.toDomain(results.get(0));
    }

    @Override
    public void delete(UUID usuariosId) {
        UsuariosEntity entity = em.find(UsuariosEntity.class, usuariosId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public Usuarios update(Usuarios usuarios) {
        usuarios.setSenha(new BCryptPasswordEncoder().encode(usuarios.getSenha()));
        UsuariosEntity entity = mapper.toEntity(usuarios);
        entity.setAtualizadoEm(LocalDateTime.now());
        UsuariosEntity merged = em.merge(entity);
        return mapper.toDomain(merged);
    }

    @Override
    public Usuarios getUsuarios(UUID usuariosId) {
        UsuariosEntity entity = em.find(UsuariosEntity.class, usuariosId);
        return entity != null
                ? mapper.toDomain(entity)
                : null;
    }

    @Override
    public Usuarios getUsuariosByMatricula(String matricula) {
        // Aqui assumimos que "matricula" é armazenada no campo "usuario" da entidade
        TypedQuery<UsuariosEntity> query = em.createQuery(
                "SELECT u FROM UsuariosEntity u WHERE u.usuario = :usuario",
                UsuariosEntity.class
        );
        query.setParameter("usuario", matricula);
        List<UsuariosEntity> results = query.getResultList();
        return results.isEmpty()
                ? null
                : mapper.toDomain(results.get(0));
    }

    @Override
    public List<Usuarios> getUsuariosByStatus(UsuariosStatus status) {
        TypedQuery<UsuariosEntity> query = em.createQuery(
                "SELECT u FROM UsuariosEntity u WHERE u.status = :status",
                UsuariosEntity.class
        );
        query.setParameter("status", status);
        return query.getResultList()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        TypedQuery<UsuariosEntity> query = em.createQuery(
                "SELECT u FROM UsuariosEntity u",
                UsuariosEntity.class
        );
        return query.getResultList()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}
