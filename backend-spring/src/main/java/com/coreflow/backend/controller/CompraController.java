package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.dto.CompraRequest;
import com.coreflow.backend.model.Compra;
import com.coreflow.backend.service.CompraService;

@RestController
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping("/compras")
    public List<Compra> listar() {
        return compraService.listar();
    }

    @GetMapping("/compras/{id}")
    public Compra buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id);
    }

    @GetMapping("/empresas/{empresaId}/compras")
    public List<Compra> listarPorEmpresa(@PathVariable Long empresaId) {
        return compraService.listarPorEmpresa(empresaId);
    }

    @GetMapping("/fornecedores/{fornecedorId}/compras")
    public List<Compra> listarPorFornecedor(@PathVariable Long fornecedorId) {
        return compraService.listarPorFornecedor(fornecedorId);
    }

    @PostMapping("/compras")
    public Compra registrarCompra(@RequestBody CompraRequest request) {
        return compraService.registrarCompra(request);
    }
}