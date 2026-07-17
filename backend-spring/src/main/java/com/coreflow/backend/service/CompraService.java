package com.coreflow.backend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coreflow.backend.dto.CompraRequest;
import com.coreflow.backend.dto.ItemCompraRequest;
import com.coreflow.backend.enums.StatusCompra;
import com.coreflow.backend.enums.TipoItem;
import com.coreflow.backend.enums.TipoMovimentacaoEstoque;
import com.coreflow.backend.model.Compra;
import com.coreflow.backend.model.Empresa;
import com.coreflow.backend.model.Estoque;
import com.coreflow.backend.model.Fornecedor;
import com.coreflow.backend.model.ItemCompra;
import com.coreflow.backend.model.MovimentacaoEstoque;
import com.coreflow.backend.model.ProdutoServico;
import com.coreflow.backend.repository.CompraRepository;
import com.coreflow.backend.repository.EmpresaRepository;
import com.coreflow.backend.repository.EstoqueRepository;
import com.coreflow.backend.repository.FornecedorRepository;
import com.coreflow.backend.repository.ItemCompraRepository;
import com.coreflow.backend.repository.MovimentacaoEstoqueRepository;
import com.coreflow.backend.repository.ProdutoServicoRepository;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final ItemCompraRepository itemCompraRepository;
    private final EmpresaRepository empresaRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoServicoRepository produtoServicoRepository;
    private final EstoqueRepository estoqueRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public CompraService(
            CompraRepository compraRepository,
            ItemCompraRepository itemCompraRepository,
            EmpresaRepository empresaRepository,
            FornecedorRepository fornecedorRepository,
            ProdutoServicoRepository produtoServicoRepository,
            EstoqueRepository estoqueRepository,
            MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {
        this.compraRepository = compraRepository;
        this.itemCompraRepository = itemCompraRepository;
        this.empresaRepository = empresaRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoServicoRepository = produtoServicoRepository;
        this.estoqueRepository = estoqueRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    public List<Compra> listar() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra nao encontrada."));
    }

    public List<Compra> listarPorEmpresa(Long empresaId) {
        return compraRepository.findByEmpresaId(empresaId);
    }

    public List<Compra> listarPorFornecedor(Long fornecedorId) {
        return compraRepository.findByFornecedorId(fornecedorId);
    }

    public Compra registrarCompra(CompraRequest request) {
        if (request.getItens() == null || request.getItens().isEmpty()) {
            throw new RuntimeException("A compra precisa ter pelo menos um item.");
        }

        Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa nao encontrada."));

        Fornecedor fornecedor = fornecedorRepository.findById(request.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor nao encontrado."));

        Compra compra = new Compra();
        compra.setEmpresa(empresa);
        compra.setFornecedor(fornecedor);
        compra.setDataHora(LocalDateTime.now());
        compra.setValorTotal(BigDecimal.ZERO);
        compra.setStatus(StatusCompra.FINALIZADA);

        compra = compraRepository.save(compra);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemCompraRequest itemRequest : request.getItens()) {
            ProdutoServico produtoServico = produtoServicoRepository.findById(itemRequest.getProdutoServicoId())
                    .orElseThrow(() -> new RuntimeException("Produto ou servico nao encontrado."));

            if (itemRequest.getQuantidade() == null || itemRequest.getQuantidade() <= 0) {
                throw new RuntimeException("A quantidade precisa ser maior que zero.");
            }

            if (itemRequest.getPrecoUnitario() == null || itemRequest.getPrecoUnitario().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("O preco unitario precisa ser maior que zero.");
            }

            BigDecimal subtotal = itemRequest.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(itemRequest.getQuantidade()));

            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setCompra(compra);
            itemCompra.setProdutoServico(produtoServico);
            itemCompra.setQuantidade(itemRequest.getQuantidade());
            itemCompra.setPrecoUnitario(itemRequest.getPrecoUnitario());
            itemCompra.setSubtotal(subtotal);

            itemCompraRepository.save(itemCompra);

            valorTotal = valorTotal.add(subtotal);

            if (produtoServico.getTipo() == TipoItem.PRODUTO) {
                Estoque estoque = estoqueRepository.findByProdutoServicoId(produtoServico.getId())
                        .orElseThrow(() -> new RuntimeException("Estoque do produto nao encontrado."));

                estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + itemRequest.getQuantidade());
                estoqueRepository.save(estoque);

                MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
                movimentacao.setEstoque(estoque);
                movimentacao.setProdutoServico(produtoServico);
                movimentacao.setEmpresa(empresa);
                movimentacao.setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque.ENTRADA);
                movimentacao.setQuantidade(itemRequest.getQuantidade());
                movimentacao.setDataHora(LocalDateTime.now());
                movimentacao.setObservacao("Entrada automatica por compra");

                movimentacaoEstoqueRepository.save(movimentacao);
            }
        }

        compra.setValorTotal(valorTotal);
        return compraRepository.save(compra);
    }
}