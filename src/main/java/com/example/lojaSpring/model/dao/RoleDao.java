package com.example.lojaSpring.model.dao;

import com.example.lojaSpring.model.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RoleDao {

    @PersistenceContext
    private EntityManager em ;

    public void save(Role role){
        em.persist(role);
    }

    public Role find(String nome){ return em.find(Role.class,nome); }


}
