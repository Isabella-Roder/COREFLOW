package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.dto.VendaRequest;
import com.coreflow.backend.model.Venda;
import com.coreflow.backend.service.VendaService;

@RestController
public class VendaController {
    
    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping("/vendas")
    public List<Venda> listar() {
        return vendaService.listar();
    }

    @GetMapping("/vendas/{id}")
    public Venda buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id);
    }

    @GetMapping("/empresas/{empresaId}/vendas")
    public List<Venda> listarPorEmpresa(@PathVariable Long empresaId) {
        return vendaService.listarPorEmpresa(empresaId);
    }

    @GetMapping("/clientes/{clienteId}/vendas")
    public List<Venda> listarPorCliente(@PathVariable Long clienteId) {
        return vendaService.listarPorCliente(clienteId);
    }

    @PostMapping("/vendas")
    public Venda registrarVenda(@RequestBody VendaRequest request) {
        return vendaService.registrarVenda(request);
    }

}
