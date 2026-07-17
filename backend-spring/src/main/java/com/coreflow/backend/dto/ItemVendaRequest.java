package com.coreflow.backend.dto;

public class ItemVendaRequest {
    
    private Long produtoServicoId;
    private Integer quantidade;

    public Long getProdutoServicoId() {
        return produtoServicoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setProdutoServicoId(Long produtoServicoId) {
        this.produtoServicoId = produtoServicoId;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
