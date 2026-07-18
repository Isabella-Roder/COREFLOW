package com.coreflow.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.enums.StatusContaFinanceira;
import com.coreflow.backend.enums.TipoContaFinanceira;
import com.coreflow.backend.model.ContaFinanceira;
import com.coreflow.backend.model.Empresa;
import com.coreflow.backend.repository.ClienteRepository;
import com.coreflow.backend.repository.ContaFinanceiraRepository;
import com.coreflow.backend.repository.EmpresaRepository;
import com.coreflow.backend.repository.FornecedorRepository;

@Service
public class ContaFinanceiraService {
    
    private final ContaFinanceiraRepository contaFinanceiraRepository;
    private final EmpresaRepository empresaRepository;
    private final ClienteRepository clienteRepository;
    private final FornecedorRepository fornecedorRepository;

    public ContaFinanceiraService(
        ContaFinanceiraRepository contaFinanceiraRepository,
        EmpresaRepository empresaRepository,
        ClienteRepository clienteRepository,
        FornecedorRepository fornecedorRepository
    ) {
        this.contaFinanceiraRepository = contaFinanceiraRepository;
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<ContaFinanceira> listarPorEmpresa(Long empresaId) {
        return contaFinanceiraRepository.findByEmpresaId(empresaId);
    }

    public ContaFinanceira criar(ContaFinanceira contaFinanceira) {
        Empresa empresa = empresaRepository.findById(contaFinanceira.getEmpresa().getId())
            .orElseThrow(() -> new RuntimeException("Empresa não encontrada."));

        if (contaFinanceira.getTipo() == TipoContaFinanceira.RECEBER && contaFinanceira.getCliente() == null) {
            throw new RuntimeException("Conta a receber precisa de cliente");
        }

        if (contaFinanceira.getTipo() == TipoContaFinanceira.PAGAR && contaFinanceira.getFornecedor() == null) {
            throw new RuntimeException("Conta a pagar precisa de fornecedor");
        }

        contaFinanceira.setEmpresa(empresa);   
        contaFinanceira.setStatus(StatusContaFinanceira.PENDENTE);
        return contaFinanceiraRepository.save(contaFinanceira);
    }

    public ContaFinanceira pagar(Long id) {
        ContaFinanceira conta = contaFinanceiraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conta financeira não encontrada."));

        conta.setStatus(StatusContaFinanceira.PAGO);
        conta.setDataPagamento(LocalDate.now());

        return contaFinanceiraRepository.save(conta);
    }

    public ContaFinanceira cancelar(Long id) {
        ContaFinanceira contaFinanceira = contaFinanceiraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conta financeira não encontrada."));
        
        contaFinanceira.setStatus(StatusContaFinanceira.CANCELADO);

        return contaFinanceiraRepository.save(contaFinanceira);
    }

}
