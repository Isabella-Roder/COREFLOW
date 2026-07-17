package com.coreflow.backend.dto;

import java.util.List;

public class CompraRequest {

    private Long empresaId;
    private Long fornecedorId;
    private List<ItemCompraRequest> itens;

    public Long getEmpresaId() {
        return empresaId;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public List<ItemCompraRequest> getItens() {
        return itens;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public void setItens(List<ItemCompraRequest> itens) {
        this.itens = itens;
    }
}
