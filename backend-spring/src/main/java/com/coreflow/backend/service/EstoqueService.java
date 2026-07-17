package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.enums.TipoItem;
import com.coreflow.backend.model.Estoque;
import com.coreflow.backend.repository.EstoqueRepository;

@Service
public class EstoqueService {
    
    private EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> listar() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(Long id) {
        return estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Estoque não encontrado."));
    }

    public Estoque cadastrar(Estoque estoque) {
        
        if (estoque.getProdutoServico().getTipo() != TipoItem.PRODUTO) {
            throw new RuntimeException("Apenas produtos podem ter estoque.");
        }

        if (estoqueRepository.findByProdutoServicoId(estoque.getProdutoServico().getId()).isPresent()) {
            throw new RuntimeException("Este produto já possui estoque cadastrado.");
        }

        return estoqueRepository.save(estoque);

    }

    public Estoque atualizar(Long id, Estoque dadosAtualizados) {

        Estoque estoque = buscarPorId(id);

        if (dadosAtualizados.getProdutoServico().getTipo() != TipoItem.PRODUTO) {
            throw new RuntimeException("Apenas produtos podem ter estoque.");
        }

        estoque.setProdutoServico(dadosAtualizados.getProdutoServico());
        estoque.setEmpresa(dadosAtualizados.getEmpresa());
        estoque.setQuantidadeAtual(dadosAtualizados.getQuantidadeAtual());
        estoque.setEstoqueMinimo(dadosAtualizados.getEstoqueMinimo());
        estoque.setAtivo(dadosAtualizados.getAtivo());

        return estoqueRepository.save(estoque);

    }

    public void deletar(Long id) {
        Estoque estoque = buscarPorId(id);

        estoqueRepository.delete(estoque);
    }

    public List<Estoque> listarPorEmpresa(Long empresaId) {
        return estoqueRepository.findByEmpresaId(empresaId);
    }

    public List<Estoque> listarEstoqueBaixoPorEmpresa(Long empresaId) {
        return estoqueRepository.buscarEstoqueBaixoPorEmpresa(empresaId);
    }

}
