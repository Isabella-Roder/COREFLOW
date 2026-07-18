package com.coreflow.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.coreflow.backend.enums.StatusContaFinanceira;
import com.coreflow.backend.enums.TipoContaFinanceira;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ContaFinanceira {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private TipoContaFinanceira tipo;

    @Enumerated(EnumType.STRING)
    private StatusContaFinanceira status;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Fornecedor fornecedor;

    public ContaFinanceira() {

    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public TipoContaFinanceira getTipo() {
        return tipo;
    }

    public StatusContaFinanceira getStatus() {
        return status;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setTipo(TipoContaFinanceira tipo) {
        this.tipo = tipo;
    }

    public void setStatus(StatusContaFinanceira status) {
        this.status = status;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
