package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.MovimentacaoEstoque;
import com.coreflow.backend.service.MovimentacaoEstoqueService;

@RestController
public class MovimentacaoEstoqueController {
    
    private MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @GetMapping("/movimentacoes-estoque")
    public List<MovimentacaoEstoque> listar() {
        return movimentacaoEstoqueService.listar();
    }

    @PostMapping("/movimentacoes-estoque")
    public MovimentacaoEstoque cadastrar(@RequestBody MovimentacaoEstoque movimentacaoEstoque) {
        return movimentacaoEstoqueService.cadastrar(movimentacaoEstoque);
    }

    @GetMapping("/movimentacoes-estoque/{id}")
    public MovimentacaoEstoque buscarPorId(@PathVariable Long id) {
        return movimentacaoEstoqueService.buscarPorId(id);
    }

    @GetMapping("/empresas/{empresaId}/movimentacoes-estoque")
    public List<MovimentacaoEstoque> listarPorEmpresa(@PathVariable Long empresaId) {
        return movimentacaoEstoqueService.listarPorEmpresa(empresaId);
    }

    @GetMapping("/estoques/{estoqueId}/movimentacoes")
    public List<MovimentacaoEstoque> listarPorEstoque(@PathVariable Long estoqueId) {
        return movimentacaoEstoqueService.listarPorEstoque(estoqueId);
    }

}
