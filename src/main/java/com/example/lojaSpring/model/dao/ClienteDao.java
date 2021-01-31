package com.example.lojaSpring.model.dao;

import com.example.lojaSpring.model.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ClienteDao {

    @PersistenceContext
    private EntityManager em ;

    public void save(Cliente cliente){
        em.persist(cliente);
    }

    public Cliente findByUserName(String usuario_login){ return em.find(Cliente.class,usuario_login); }

//    public Cliente findCliente(String usuario_login){
//        Query query = em.createQuery("select id from ClientePF where usuario_login="+usuario_login);
//        return query.;
//    }

    public List<ClientePF> clientesPF(){
        Query query = em.createQuery("from ClientePF");
        return query.getResultList();
    }

    public List<Cliente> clientes(){
        Query query = em.createQuery("from Cliente");
        return query.getResultList();
    }

    public List<ClientePJ> clientesPJ(){
        Query query = em.createQuery("from ClientePJ");
        return query.getResultList();
    }

}

