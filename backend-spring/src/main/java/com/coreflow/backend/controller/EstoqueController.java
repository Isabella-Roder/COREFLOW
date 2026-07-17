package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Estoque;
import com.coreflow.backend.service.EstoqueService;

@RestController
public class EstoqueController {
    
    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping("/estoques")
    public List<Estoque> listar() {
        return estoqueService.listar();
    }

    @PostMapping("/estoques")
    public Estoque cadastrar(@RequestBody Estoque estoque) {
        return estoqueService.cadastrar(estoque);
    }

    @GetMapping("/estoques/{id}")
    public Estoque buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id);
    }

    @PutMapping("/estoques/{id}")
    public Estoque atualizar(@PathVariable Long id, @RequestBody Estoque estoque) {
        return estoqueService.atualizar(id, estoque);
    }

    @DeleteMapping("/estoques/{id}")
    public void deletar(@PathVariable Long id) {
        estoqueService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/estoques")
    public List<Estoque> listarPorEmpresa(@PathVariable Long empresaId) {
        return estoqueService.listarPorEmpresa(empresaId);
    }

    @GetMapping("/empresas/{empresaId}/estoques/baixo")
    public List<Estoque> listarEstoqueBaixoPorEmpresa(@PathVariable Long empresaId) {
        return estoqueService.listarEstoqueBaixoPorEmpresa(empresaId);
    }

}
