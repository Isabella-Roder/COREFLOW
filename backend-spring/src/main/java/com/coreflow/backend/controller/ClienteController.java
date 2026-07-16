package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Cliente;
import com.coreflow.backend.service.ClienteService;

@RestController
public class ClienteController {
    
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @PostMapping("/clientes")
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteService.cadastrar(cliente);
    }

    @GetMapping("/clientes/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PutMapping("/clientes/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizar(id, cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public void deletar(@PathVariable Long id) {
        clienteService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/clientes")
    public List<Cliente> listarPorEmpresa(@PathVariable Long empresaId) {
        return clienteService.listarPorEmpresa(empresaId);
    }

}
