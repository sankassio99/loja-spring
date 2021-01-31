package com.example.lojaSpring.model.dao;

import com.example.lojaSpring.model.entity.ItemVenda;
import com.example.lojaSpring.model.entity.Produto;
import com.example.lojaSpring.model.entity.Venda;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProdutoDao {
    @PersistenceContext
    private EntityManager em ;

    public void save(Produto produto){

        if(produto.getId()==null){
            em.persist(produto);
        }else {
            em.merge(produto);
        }

    }

    public Produto find(long id){ return em.find(Produto.class,id); }

    public void remove(Produto p){
        em.remove(p);
    }

    public List<Produto> produtos(){
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }
}
