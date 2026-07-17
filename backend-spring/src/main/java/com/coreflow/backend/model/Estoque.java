package com.coreflow.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Estoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_servico_id")
    private ProdutoServico produtoServico;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    private Integer quantidadeAtual;
    private Integer estoqueMinimo;
    private Boolean ativo;

    public Estoque() {

    }

    public Estoque(ProdutoServico produtoServico, Empresa empresa, Integer quantidadeAtual, Integer estoqueMinimo, Boolean ativo) {
        this.produtoServico = produtoServico;
        this.empresa = empresa;
        this.quantidadeAtual = quantidadeAtual;
        this.estoqueMinimo = estoqueMinimo;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public ProdutoServico getProdutoServico() {
        return produtoServico;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setProdutoServico(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

}
