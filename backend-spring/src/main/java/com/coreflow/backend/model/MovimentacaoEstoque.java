package com.coreflow.backend.model;

import java.time.LocalDateTime;

import com.coreflow.backend.enums.TipoMovimentacaoEstoque;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MovimentacaoEstoque {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    @ManyToOne
    @JoinColumn(name = "produto_servico_id")
    private ProdutoServico produtoServico;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacaoEstoque tipoMovimentacaoEstoque;

    private Integer quantidade;
    private LocalDateTime dataHora;
    private String observacao;

    public MovimentacaoEstoque() {

    }

    public MovimentacaoEstoque(Estoque estoque, ProdutoServico produtoServico, Empresa empresa, TipoMovimentacaoEstoque tipoMovimentacaoEstoque, Integer quantidade, LocalDateTime dataHora, String observacao) {
        this.estoque = estoque;
        this.produtoServico = produtoServico;
        this.empresa = empresa;
        this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public ProdutoServico getProdutoServico() {
        return produtoServico;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public TipoMovimentacaoEstoque getTipoMovimentacaoEstoque() {
        return tipoMovimentacaoEstoque;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public void setProdutoServico(ProdutoServico produtoServico) {
        this.produtoServico = produtoServico;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque tipoMovimentacaoEstoque) {
        this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
