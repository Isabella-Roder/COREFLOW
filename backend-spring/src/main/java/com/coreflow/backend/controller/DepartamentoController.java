package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Departamento;
import com.coreflow.backend.service.DepartamentoService;

@RestController
public class DepartamentoController {
    
    private DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping("/departamentos")
    public List<Departamento> listar() {
        return departamentoService.listar();
    }

    @PostMapping("/departamentos")
    public Departamento cadastrar(@RequestBody Departamento departamento) {
        return departamentoService.cadastrar(departamento);
    }

    @GetMapping("/departamentos/{id}")
    public Departamento buscarPorId(@PathVariable Long id) {
        return departamentoService.buscarPorId(id);
    }

    @PutMapping("/departamentos/{id}")
    public Departamento atualizar(@PathVariable Long id, @RequestBody Departamento departamento) {
        return departamentoService.atualizar(id, departamento);
    }

    @DeleteMapping("/departamentos/{id}")
    public void deletar(@PathVariable Long id) {
        departamentoService.deletar(id);
    }

    @GetMapping("/empresas/{empresaId}/departamentos")
    public List<Departamento> listarPorEmpresa(@PathVariable Long empresaId) {
        return departamentoService.listarPorEmpresa(empresaId);
    }

}
