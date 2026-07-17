package com.coreflow.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.coreflow.backend.enums.StatusCompra;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    private LocalDateTime dataHora;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    public Compra() {

    }

    public Compra(Empresa empresa, Fornecedor fornecedor, LocalDateTime dataHora, BigDecimal valorTotal, StatusCompra status) {
        this.empresa = empresa;
        this.fornecedor = fornecedor;
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusCompra status) {
        this.status = status;
    }
}
