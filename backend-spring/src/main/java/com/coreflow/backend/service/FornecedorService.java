package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.model.Fornecedor;
import com.coreflow.backend.repository.FornecedorRepository;

@Service
public class FornecedorService {
    
    private FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));
    }

    public Fornecedor cadastrar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor dadosAtualizados) {
        Fornecedor fornecedor = buscarPorId(id);

        fornecedor.setNome(dadosAtualizados.getNome());
        fornecedor.setDocumento(dadosAtualizados.getDocumento());
        fornecedor.setEmail(dadosAtualizados.getEmail());
        fornecedor.setTelefone(dadosAtualizados.getTelefone());
        fornecedor.setTipoServico(dadosAtualizados.getTipoServico());
        fornecedor.setAtivo(dadosAtualizados.getAtivo());
        fornecedor.setEmpresa(dadosAtualizados.getEmpresa());

        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Long id) {
        Fornecedor fornecedor = buscarPorId(id);

        fornecedorRepository.delete(fornecedor);
    }

    public List<Fornecedor> listarPorEmpresa(Long empresaId) {
        return fornecedorRepository.findByEmpresaId(empresaId);
    }

}
