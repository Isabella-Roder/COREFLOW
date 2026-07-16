package com.coreflow.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Departamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Departamento() {

    }

    public Departamento(String nome, String descricao, Boolean ativo, Empresa empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
