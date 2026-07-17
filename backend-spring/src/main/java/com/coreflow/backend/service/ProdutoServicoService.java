package com.coreflow.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.model.ProdutoServico;
import com.coreflow.backend.repository.ProdutoServicoRepository;

@Service
public class ProdutoServicoService {
    
    private ProdutoServicoRepository produtoServicoRepository;

    public ProdutoServicoService(ProdutoServicoRepository produtoServicoRepository) {
        this.produtoServicoRepository = produtoServicoRepository;
    }

    public List<ProdutoServico> listar() {
        return produtoServicoRepository.findAll();
    }

    public ProdutoServico buscarPorId(Long id) {
        return produtoServicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto servico não encontrado."));
    }

    public ProdutoServico cadastrar(ProdutoServico produtoServico) {
        return produtoServicoRepository.save(produtoServico);
    }

    public ProdutoServico atualizar(Long id, ProdutoServico dadosAtualizados) {
        ProdutoServico produtoServico = buscarPorId(id);

        produtoServico.setNome(dadosAtualizados.getNome());
        produtoServico.setDescricao(dadosAtualizados.getDescricao());
        produtoServico.setPrecoVenda(dadosAtualizados.getPrecoVenda());
        produtoServico.setCusto(dadosAtualizados.getCusto());
        produtoServico.setAtivo(dadosAtualizados.getAtivo());
        produtoServico.setTipo(dadosAtualizados.getTipo());
        produtoServico.setEmpresa(dadosAtualizados.getEmpresa());

        return produtoServicoRepository.save(produtoServico);
    }

    public void deletar(Long id) {
        ProdutoServico produtoServico = buscarPorId(id);

        produtoServicoRepository.delete(produtoServico);
    }

    public List<ProdutoServico> buscarPorEmpresa(Long empresaId) {
        return produtoServicoRepository.findByEmpresaId(empresaId);
    }

}
