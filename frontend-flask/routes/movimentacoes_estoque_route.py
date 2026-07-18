from flask import Blueprint, render_template
from services.api_service import buscar_lista

movimentacoes_estoque_bp = Blueprint("movimentacoes_estoque", __name__)

@movimentacoes_estoque_bp.route("/movimentacoes-estoque")
def movimentacoes_estoque() :
    movimentacoes = buscar_lista("/movimentacoes-estoque")

    total_movimentacoes = len(movimentacoes)

    total_entradas = sum(
        1 for mov in movimentacoes
        if mov.get("tipoMovimentacaoEstoque") == "ENTRADA"
    )

    total_saidas = sum(
        1 for mov in movimentacoes
        if mov.get("tipoMovimentacaoEstoque") == "SAIDA"
    )

    total_ajustes = sum(
        1 for mov in movimentacoes
        if mov.get("tipoMovimentacaoEstoque") == "AJUSTE"
    )

    quantidade_movimentada = sum(
        mov.get("quantidade", 0) or 0
        for mov in movimentacoes
    )

    return render_template(
        "movimentacoes-estoque.html",
        movimentacoes=movimentacoes,
        total_movimentacoes=total_movimentacoes,
        total_entradas=total_entradas,
        total_saidas=total_saidas,
        total_ajustes=total_ajustes,
        quantidade_movimentada=quantidade_movimentada
    )