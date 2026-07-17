package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.ProdutoServico;
import com.coreflow.backend.service.ProdutoServicoService;

@RestController
public class ProdutoServicoController {
    
    private ProdutoServicoService produtoServicoService;

    public ProdutoServicoController(ProdutoServicoService produtoServicoService) {
        this.produtoServicoService = produtoServicoService;
    }

    @GetMapping("/produtos-servicos")
    public List<ProdutoServico> listar() {
        return produtoServicoService.listar();
    }

    @PostMapping("/produtos-servicos")
    public ProdutoServico cadastrar(@RequestBody ProdutoServico produtoServico) {
        return produtoServicoService.cadastrar(produtoServico);
    }

    @GetMapping("/produtos-servicos/{id}")
    public ProdutoServico buscarPorId(@PathVariable Long id) {
        return produtoServicoService.buscarPorId(id);
    }

    @PutMapping("/produtos-servicos/{id}")
    public ProdutoServico atualizar(@PathVariable Long id, @RequestBody ProdutoServico produtoServico) {
        return produtoServicoService.atualizar(id, produtoServico);
    }

    @DeleteMapping("/produtos-servicos/{id}")
    public void deletar(@PathVariable Long id) {
        produtoServicoService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/produtos-servicos")
    public List<ProdutoServico> listarPorEmpresa(@PathVariable Long empresaId) {
        return produtoServicoService.buscarPorEmpresa(empresaId);
    }

}
