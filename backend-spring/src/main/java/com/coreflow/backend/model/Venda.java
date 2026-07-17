package com.coreflow.backend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.coreflow.backend.enums.StatusVenda;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataHora;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusVenda status;

    public Venda() {

    }

    public Venda(Empresa empresa, Cliente cliente, LocalDateTime dataHora, BigDecimal valorTotal, StatusVenda status) {
        this.empresa = empresa;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }
}
