package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Fornecedor;
import com.coreflow.backend.service.FornecedorService;

@RestController
public class FornecedorController {
    
    private FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/fornecedores")
    public List<Fornecedor> listar() {
        return fornecedorService.listar();
    }

    @PostMapping("/fornecedores")
    public Fornecedor cadastrar(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.cadastrar(fornecedor);
    }

    @GetMapping("/fornecedores/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @PutMapping("/fornecedores/{id}")
    public Fornecedor atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        return fornecedorService.atualizar(id, fornecedor);
    }

    @DeleteMapping("/fornecedores/{id}")
    public void deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/fornecedores")
    public List<Fornecedor> listarPorEmpresa(@PathVariable Long empresaId) {
        return fornecedorService.listarPorEmpresa(empresaId);
    }

}
