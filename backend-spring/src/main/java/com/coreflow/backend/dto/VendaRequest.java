package com.coreflow.backend.dto;

import java.util.List;

public class VendaRequest {
    
    private Long empresaId;
    private Long clienteId;
    private List<ItemVendaRequest> itens;

    public Long getEmpresaId() {
        return empresaId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public List<ItemVendaRequest> getItens() {
        return itens;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setItens(List<ItemVendaRequest> itens) {
        this.itens = itens;
    }

}
