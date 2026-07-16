package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.Empresa;
import com.coreflow.backend.service.EmpresaService;

@RestController
public class EmpresaController {
    
    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/empresas")
    public List<Empresa> listar() {
        return empresaService.listar();
    }

    @PostMapping("/empresas")
    public Empresa cadastrar(@RequestBody Empresa empresa) {
        return empresaService.cadastrar(empresa);
    }

    @GetMapping("/empresas/{id}")
    public Empresa buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id);
    }

    @PutMapping("/empresas/{id}")
    public Empresa atualizar(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.atualizar(id, empresa);
    }
    
    @DeleteMapping("/empresas/{id}")
    public void deletar(@PathVariable Long id) {
        empresaService.deletar(id);
    }
}
