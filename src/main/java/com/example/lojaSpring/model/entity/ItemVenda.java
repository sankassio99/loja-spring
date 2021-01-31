/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lojaSpring.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 * @author Kassio San
 */
@Entity
@Table(name = "tb_itemvenda")
public class ItemVenda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double qtd ;
    
    @OneToOne
    private Produto produto ;
    
    public double total(){
        double soma = 0 ;
        soma = qtd * produto.valor ;
        return soma;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public Produto getProduto(){
        return produto ;
    }
    public void setProduto(Produto produto){
        this.produto = produto ;
    }

    
    
}
