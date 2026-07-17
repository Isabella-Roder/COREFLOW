package com.coreflow.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.dto.ItemVendaRequest;
import com.coreflow.backend.dto.VendaRequest;
import com.coreflow.backend.enums.StatusVenda;
import com.coreflow.backend.enums.TipoItem;
import com.coreflow.backend.enums.TipoMovimentacaoEstoque;
import com.coreflow.backend.model.Cliente;
import com.coreflow.backend.model.Empresa;
import com.coreflow.backend.model.Estoque;
import com.coreflow.backend.model.ItemVenda;
import com.coreflow.backend.model.MovimentacaoEstoque;
import com.coreflow.backend.model.ProdutoServico;
import com.coreflow.backend.model.Venda;
import com.coreflow.backend.repository.ClienteRepository;
import com.coreflow.backend.repository.EmpresaRepository;
import com.coreflow.backend.repository.EstoqueRepository;
import com.coreflow.backend.repository.ItemVendaRepository;
import com.coreflow.backend.repository.MovimentacaoEstoqueRepository;
import com.coreflow.backend.repository.ProdutoServicoRepository;
import com.coreflow.backend.repository.VendaRepository;

@Service
public class VendaService {
    
    private VendaRepository vendaRepository;
    private EmpresaRepository empresaRepository;
    private ClienteRepository clienteRepository;
    private ProdutoServicoRepository produtoServicoRepository;
    private ItemVendaRepository itemVendaRepository;
    private EstoqueRepository estoqueRepository;
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public VendaService(VendaRepository vendaRepository, EmpresaRepository empresaRepository, ClienteRepository clienteRepository, ProdutoServicoRepository produtoServicoRepository, ItemVendaRepository itemVendaRepository, EstoqueRepository estoqueRepository, MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {
        this.vendaRepository = vendaRepository;
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoServicoRepository = produtoServicoRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.estoqueRepository = estoqueRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    public List<Venda> listar() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda nao encontrada."));
    }

    public List<Venda> listarPorEmpresa(Long empresaId) {
        return vendaRepository.findByEmpresaId(empresaId);
    }

    public List<Venda> listarPorCliente(Long clienteId) {
        return vendaRepository.findByClienteId(clienteId);
    }

    public Venda registrarVenda(VendaRequest request) {
        Empresa empresa = empresaRepository.findById(request.getEmpresaId()).orElseThrow(() -> new RuntimeException("Empresa nao encontrada."));

        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente nao encontrado."));

        Venda venda = new Venda();
        venda.setEmpresa(empresa);
        venda.setCliente(cliente);
        venda.setDataHora(LocalDateTime.now());
        venda.setStatus(StatusVenda.FINALIZADA);
        venda.setValorTotal(BigDecimal.ZERO);

        venda = vendaRepository.save(venda);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemVendaRequest itemRequest : request.getItens()) {
            ProdutoServico produtoServico = produtoServicoRepository.findById(itemRequest.getProdutoServicoId())
                .orElseThrow(() -> new RuntimeException("Produto ou servico nao encontrado."));
        
            BigDecimal precoUnitario = produtoServico.getPrecoVenda();
            BigDecimal quantidade = BigDecimal.valueOf(itemRequest.getQuantidade());
            BigDecimal subTotal = precoUnitario.multiply(quantidade);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(venda);
            itemVenda.setProdutoServico(produtoServico);
            itemVenda.setQuantidade(itemRequest.getQuantidade());
            itemVenda.setPrecoUnitario(precoUnitario);
            itemVenda.setSubtotal(subTotal);

            itemVendaRepository.save(itemVenda);

            valorTotal = valorTotal.add(subTotal);

            if (produtoServico.getTipo() == TipoItem.PRODUTO) {
                Estoque estoque = estoqueRepository.findByProdutoServicoId(produtoServico.getId())
                    .orElseThrow(() -> new RuntimeException("Estoque do produto nao encontrado."));

                if (estoque.getQuantidadeAtual() < itemRequest.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produtoServico.getNome());
                }

                estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - itemRequest.getQuantidade());
                estoqueRepository.save(estoque);

                MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
                movimentacao.setEstoque(estoque);
                movimentacao.setProdutoServico(produtoServico);
                movimentacao.setEmpresa(empresa);
                movimentacao.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.SAIDA);
                movimentacao.setQuantidade(itemRequest.getQuantidade());
                movimentacao.setDataHora(LocalDateTime.now());
                movimentacao.setObservacao("Saida automatica por venda");

                movimentacaoEstoqueRepository.save(movimentacao);
            }
        }

        venda.setValorTotal(valorTotal);
        return vendaRepository.save(venda);
    }
    
    
}
