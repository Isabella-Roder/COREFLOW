package com.coreflow.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.enums.TipoMovimentacaoEstoque;
import com.coreflow.backend.model.Estoque;
import com.coreflow.backend.model.MovimentacaoEstoque;
import com.coreflow.backend.repository.EstoqueRepository;
import com.coreflow.backend.repository.MovimentacaoEstoqueRepository;

@Service
public class MovimentacaoEstoqueService {
    
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private EstoqueRepository estoqueRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, EstoqueRepository estoqueRepository) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
    }

    public List<MovimentacaoEstoque> listar() {
        return movimentacaoEstoqueRepository.findAll();
    }

    public MovimentacaoEstoque buscarPorId(Long id) {
        return movimentacaoEstoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimentacao estoque nao encontrado."));
    }

    public MovimentacaoEstoque cadastrar(MovimentacaoEstoque movimentacaoEstoque) {
        Estoque estoque = estoqueRepository.findById(movimentacaoEstoque.getEstoque().getId())
            .orElseThrow(() -> new RuntimeException("Estoque nao encontrado."));

        Integer quantidadeAtual = estoque.getQuantidadeAtual();
        Integer quantidade = movimentacaoEstoque.getQuantidade();

        if (movimentacaoEstoque.getTipoMovimentacaoEstoque() == TipoMovimentacaoEstoque.ENTRADA) {
            estoque.setQuantidadeAtual(quantidadeAtual + quantidade);
        }

        if (movimentacaoEstoque.getTipoMovimentacaoEstoque() == TipoMovimentacaoEstoque.SAIDA) {
            estoque.setQuantidadeAtual(quantidadeAtual - quantidade);
        }

        if (movimentacaoEstoque.getTipoMovimentacaoEstoque() == TipoMovimentacaoEstoque.AJUSTE) {
            estoque.setQuantidadeAtual(quantidade);
        }

        movimentacaoEstoque.setEstoque(estoque);
        movimentacaoEstoque.setProdutoServico(estoque.getProdutoServico());
        movimentacaoEstoque.setEmpresa(estoque.getEmpresa());

        if (movimentacaoEstoque.getDataHora() == null) {
            movimentacaoEstoque.setDataHora(LocalDateTime.now());
        }

        estoqueRepository.save(estoque);
        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

    public List<MovimentacaoEstoque> listarPorEmpresa(Long empresaId) {
        return movimentacaoEstoqueRepository.findByEmpresaId(empresaId);
    }

    public List<MovimentacaoEstoque> listarPorEstoque(Long estoqueId) {
        return movimentacaoEstoqueRepository.findByEstoqueId(estoqueId);
    }

}
