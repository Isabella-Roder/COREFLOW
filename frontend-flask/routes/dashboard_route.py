from flask import Blueprint, render_template
from services.api_service import buscar_lista

dashboard_bp = Blueprint("dashboard", __name__)


@dashboard_bp.route("/")
def dashboard() :
    empresas = buscar_lista("/empresas")
    clientes = buscar_lista("/clientes")
    fornecedores = buscar_lista("/fornecedores")
    produtos_servicos = buscar_lista("/produtos-servicos")
    estoques = buscar_lista("/estoques")
    estoques_baixos = buscar_lista("/empresas/33/estoques/baixo")
    movimentacoes = buscar_lista("/movimentacoes-estoque")
    vendas = buscar_lista("/vendas")
    compras = buscar_lista("/compras")

    total_vendido = sum(venda.get("valorTotal", 0) for venda in vendas)
    total_comprado = sum(compra.get("valorTotal", 0) for compra in compras)
    saldo = total_vendido - total_comprado

    return render_template (
        "dashboard.html",
        empresas = empresas,
        clientes = clientes,
        fornecedores = fornecedores,
        produtos_servicos = produtos_servicos,
        estoques = estoques,
        estoques_baixos = estoques_baixos,
        movimentacoes = movimentacoes, 
        vendas = vendas,
        compras = compras,
        total_vendido = total_vendido, 
        total_comprado = total_comprado,
        saldo = saldo
    )  