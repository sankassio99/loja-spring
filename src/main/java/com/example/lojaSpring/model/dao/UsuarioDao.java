package com.example.lojaSpring.model.dao;


import com.example.lojaSpring.model.entity.Role;
import com.example.lojaSpring.model.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String login){
        return em.find(Usuario.class, login);
    }

    public void save(Usuario usuario){
        em.persist(usuario);
    }

}
