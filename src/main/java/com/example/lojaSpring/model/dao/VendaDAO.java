/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lojaSpring.model.dao;

import com.example.lojaSpring.model.entity.Produto;
import com.example.lojaSpring.model.entity.Usuario;
import com.example.lojaSpring.model.entity.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kassio San
 */
@Repository
public class VendaDAO {
    
    @PersistenceContext
    private EntityManager em ;

    public void save(Venda venda){
        em.persist(venda);
    }

    public List<Venda> vendasUser(String user){
        Query query = em.createQuery("from Venda WHERE usuario_login='"+user+"'");
        return query.getResultList();
    }
    
    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
}
