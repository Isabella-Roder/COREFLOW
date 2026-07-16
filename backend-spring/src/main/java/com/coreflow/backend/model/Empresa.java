package com.coreflow.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Empresa {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String razaoSocial;
private String nomeFantasia;
private String cnpj;
private String email;
private String telefone;

public Empresa() {

}

public Empresa(String razaoSocial, String nomeFantasia, String cnpj, String email, String telefone) {
    this.razaoSocial = razaoSocial;
    this.nomeFantasia = nomeFantasia;
    this.cnpj = cnpj;
    this.email = email;
    this.telefone = telefone;
}

public Long getId() {
    return id;
}

public String getRazaoSocial() {
    return razaoSocial;
}

public String getNomeFantasia() {
    return nomeFantasia;
}

public String getCnpj() {
    return cnpj;
}

public String getEmail() {
    return email;
}

public String getTelefone() {
    return telefone;
}

public void setRazaoSocial(String razaoSocial) {
    this.razaoSocial = razaoSocial;
}

public void setNomeFantasia(String nomeFantasia) {
    this.nomeFantasia = nomeFantasia;
}

public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
}

public void setEmail(String email) {
    this.email = email;
}
 
public void setTelefone(String telefone) {
    this.telefone = telefone;
}

}
