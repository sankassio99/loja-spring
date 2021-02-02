package com.example.lojaSpring.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_pessoafisica")
public class ClientePF extends Cliente {

    @NotBlank(message = "Digite o nome")
    private String nome ;

    @NotBlank(message = "Digite o CPF")
    private String cpf ;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
