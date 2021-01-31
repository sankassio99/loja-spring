/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.lojaSpring.model.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Kassio San
 */

@Scope("session")
@Component
@Entity
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id ;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;

    @OneToMany(mappedBy = "venda",cascade = CascadeType.PERSIST)
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Usuario usuario ;

    public double total = 0;

    public double getTotal() {
        return total;
    }

    public void setTotal(Double total){
        this.total = total ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda ;
    }

    public void setItensVenda(ItemVenda itemVenda) {
        this.itensVenda.add(itemVenda);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
