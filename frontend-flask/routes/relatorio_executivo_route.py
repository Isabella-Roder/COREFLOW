from flask import Blueprint, render_template
from services.api_service import buscar_lista

relatorio_executivo_bp = Blueprint("relatorio_executivo", __name__)

@relatorio_executivo_bp.route("/relatorio-executivo")
def relatorio_executivo() :
    empresas = buscar_lista("/empresas")
    clientes = buscar_lista("/clientes")
    fornecedores = buscar_lista("/fornecedores")
    produtos_servicos = buscar_lista("/produtos-servicos")
    vendas = buscar_lista("/vendas")
    compras = buscar_lista("/compras")
    estoques = buscar_lista("/estoques")
    movimentacoes = buscar_lista("/movimentacoes-estoque")

    total_vendido = sum(
        venda.get("valorTotal", 0) or 0
        for venda in vendas
    )

    total_comprado = sum(
        compra.get("valorTotal", 0) or 0
        for compra in compras
    )

    saldo_estimado = total_vendido - total_comprado

    total_produtos = sum(
        1 for item in produtos_servicos
        if item.get("tipo") == "PRODUTO"
    )

    total_servicos = sum(
        1 for item in produtos_servicos
        if item.get("tipo") == "SERVICO"
    )

    estoques_baixos = []

    for estoque in estoques :
        quantidade_atual = estoque.get("quantidadeAtual", 0) or 0
        estoque_minimo = estoque.get("estoqueMinimo", 0) or 0

        if quantidade_atual <= estoque_minimo :
            estoques_baixos.append(estoque)

    total_entradas = sum(
        1 for mov in movimentacoes
        if mov.get("tipoMovimentacaoEstoque") == "ENTRADA"
    )

    total_saidas = sum(
        1 for mov in movimentacoes
        if mov.get("tipoMovimentacoesEstoque") == "SAIDA"
    )

    margem_total_estimada = 0

    for item in produtos_servicos :
        preco = item.get("precoVenda", 0) or 0
        custo = item.get("custo", 0) or 0
        margem_total_estimada += preco - custo

    grafico_vendas_compras = {
        "labels": ["Vendas", "Compras"],
        "valores": [total_vendido, total_comprado]
    }

    grafico_catalogo = {
        "labels": ["Produtos", "Serviços"],
        "valores": [total_produtos, total_servicos]
    }

    grafico_movimentacoes = {
        "labels": ["Entradas", "Saídas"],
        "valores": [total_entradas, total_saidas]
    }

    grafico_estoque = {
        "labels": [],
        "valores": []
    }

    for estoque in estoques :
        produto = estoque.get("produtoServico")

        if produto :
            grafico_estoque["labels"].append(produto.get("nome"))
            grafico_estoque["valores"].append(estoque.get("quantidadeAtual", 0) or 0)

    return render_template(
        "relatorio_executivo.html",
        empresas=empresas,
        clientes=clientes,
        fornecedores=fornecedores,
        produtos_servicos=produtos_servicos,
        vendas=vendas,
        compras=compras,
        estoques=estoques,
        estoques_baixos=estoques_baixos,
        total_vendido=total_vendido,
        total_comprado=total_comprado,
        saldo_estimado=saldo_estimado,
        total_produtos=total_produtos,
        total_servicos=total_servicos,
        total_entradas=total_entradas,
        total_saidas=total_saidas,
        margem_total_estimada=margem_total_estimada,
        grafico_vendas_compras=grafico_vendas_compras,
        grafico_catalogo=grafico_catalogo,
        grafico_movimentacoes=grafico_movimentacoes,
        grafico_estoque=grafico_estoque
    )