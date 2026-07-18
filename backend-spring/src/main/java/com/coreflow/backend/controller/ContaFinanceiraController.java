package com.coreflow.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coreflow.backend.model.ContaFinanceira;
import com.coreflow.backend.service.ContaFinanceiraService;

@RestController
public class ContaFinanceiraController {
    
    private final ContaFinanceiraService contaFinanceiraService;

    public ContaFinanceiraController(ContaFinanceiraService contaFinanceiraService) {
        this.contaFinanceiraService = contaFinanceiraService;
    }

    @PostMapping("/contas-financeiras")
    public ContaFinanceira criar(@RequestBody ContaFinanceira contaFinanceira) {
        return contaFinanceiraService.criar(contaFinanceira);
    }

    @GetMapping("/contas-financeiras/empresa/{empresaId}")
    public List<ContaFinanceira> listarPorEmpresa(@PathVariable Long empresaId) {
        return contaFinanceiraService.listarPorEmpresa(empresaId);
    }

    @PutMapping("/contas-financeiras/{id}/pagar")
    public ContaFinanceira pagar(@PathVariable Long id) {
        return contaFinanceiraService.pagar(id);
    }

    @PutMapping("/contas-financeiras/{id}/cancelar")
    public ContaFinanceira cancelar(@PathVariable Long id) {
        return contaFinanceiraService.cancelar(id);
    }

}
