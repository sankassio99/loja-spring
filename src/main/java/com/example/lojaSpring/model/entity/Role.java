package com.example.lojaSpring.model.entity;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "tb_role")
@Entity
public class Role implements GrantedAuthority {

    @Id
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;


    @Override
    public String getAuthority() {
        return nome ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
