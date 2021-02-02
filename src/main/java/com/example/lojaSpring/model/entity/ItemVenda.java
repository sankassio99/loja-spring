/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lojaSpring.model.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


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

    @Min(value = 1,message = "Você deve preecher a quantidade")
    private int qtd ;

    public double total;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @NotNull
    private Produto produto ;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Venda venda;

    public void setTotal(double total) {
        this.total = total;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }



    public double getTotal() {
        return total;
    }

    public void setTotal() {

        double soma = 0 ;
        soma = qtd * produto.valor ;

        this.total = soma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getQtd() {
        return qtd;
    }


    public Produto getProduto(){
        return produto ;
    }
    public void setProduto(Produto produto){
        this.produto = produto ;
    }

    
    
}
