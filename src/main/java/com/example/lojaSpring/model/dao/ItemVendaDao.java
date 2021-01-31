package com.example.lojaSpring.model.dao;

import com.example.lojaSpring.model.entity.ItemVenda;
import com.example.lojaSpring.model.entity.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ItemVendaDao {
    @PersistenceContext
    private EntityManager em ;

    public void save(ItemVenda itemVenda){
        em.persist(itemVenda);
    }

    public List<ItemVenda> itensVenda(){
        Query query = em.createQuery("from ItemVenda");
        return query.getResultList();
    }
}
