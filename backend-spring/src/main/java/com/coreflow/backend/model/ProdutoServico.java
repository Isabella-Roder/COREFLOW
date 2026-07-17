package com.coreflow.backend.model;

import java.math.BigDecimal;

import com.coreflow.backend.enums.TipoItem;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProdutoServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal precoVenda;
    private BigDecimal custo;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public ProdutoServico() {

    }

    public ProdutoServico(String nome, String descricao, BigDecimal precoVenda, BigDecimal custo, Boolean ativo, TipoItem tipo, Empresa empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.custo = custo;
        this.ativo = ativo;
        this.tipo = tipo;
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

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public TipoItem getTipo() {
        return tipo;
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

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setTipo(TipoItem tipo) {
        this.tipo = tipo;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

}
