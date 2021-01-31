package com.example.lojaSpring.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "tb_pessoajuridica")
public class ClientePJ extends Cliente{

    private String razaoSocial ;
    private String cnpj ;

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}