package com.coreflow.backend.model;

import com.coreflow.backend.enums.TipoPerfil;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Colaborador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String cargo;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoPerfil perfil;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    public Colaborador() {

    }

    public Colaborador(String nome, String email, String senha, String cargo, Boolean ativo, TipoPerfil perfil, Empresa empresa, Departamento departamento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = cargo;
        this.ativo = ativo;
        this.perfil = perfil;
        this.empresa = empresa;
        this.departamento = departamento;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCargo() {
        return cargo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public TipoPerfil getPerfil() {
        return perfil;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setPerfil(TipoPerfil perfil) {
        this.perfil = perfil;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
