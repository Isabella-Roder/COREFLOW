package com.coreflow.backend.dto;

import java.math.BigDecimal;

public class ItemCompraRequest {

    private Long produtoServicoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    public Long getProdutoServicoId() {
        return produtoServicoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setProdutoServicoId(Long produtoServicoId) {
        this.produtoServicoId = produtoServicoId;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
