package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Colaborador;
import com.coreflow.backend.service.ColaboradorService;

@RestController
public class ColaboradorController {
    
    private ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/colaboradores")
    public List<Colaborador> listar() {
        return colaboradorService.listar();
    }

    @PostMapping("/colaboradores")
    public Colaborador cadastrar(@RequestBody Colaborador colaborador) {
        return colaboradorService.cadastrar(colaborador);
    }

    @GetMapping("/colaboradores/{id}")
    public Colaborador buscarPorId(@PathVariable Long id) {
        return colaboradorService.buscarPorId(id);
    }

    @PutMapping("/colaboradores/{id}")
    public Colaborador atualizar(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        return colaboradorService.atualizar(id, colaborador);
    }

    @DeleteMapping("/colaboradores/{id}")
    public void deletar(@PathVariable Long id) {
        colaboradorService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/colaboradores")
    public List<Colaborador> listarPorEmpresa(@PathVariable Long empresaId) {
        return colaboradorService.listarPorEmpresa(empresaId);
    }

}
