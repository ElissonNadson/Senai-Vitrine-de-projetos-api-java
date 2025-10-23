package com.exemplo.meuapp.infrastructure.webclient;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exemplo.meuapp.infrastructure.persistence.entity.UsuariosEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("🔍 CustomUserDetailsService: Buscando usuário com email: " + email);
        
        TypedQuery<UsuariosEntity> query = em.createQuery(
                "SELECT u FROM UsuariosEntity u WHERE u.email = :email",
                UsuariosEntity.class
        );
        query.setParameter("email", email);
        List<UsuariosEntity> results = query.getResultList();
        
        System.out.println("📊 Resultados encontrados: " + results.size());
        
        if (results.isEmpty()) {
            System.out.println("❌ Usuário não encontrado: " + email);
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
        
        UsuariosEntity user = results.get(0);
        System.out.println("✅ Usuário encontrado:");
        System.out.println("   - UUID: " + user.getUuid());
        System.out.println("   - Email: " + user.getEmail());
        System.out.println("   - Usuario: " + user.getUsuario());
        System.out.println("   - Senha (hash): " + user.getSenha());
        System.out.println("   - Tipo: " + user.getTipo());
        System.out.println("   - Status: " + user.getStatus());
        
        return user; // UsuariosEntity já implementa UserDetails
    }
}
