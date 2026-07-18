from flask import Blueprint, render_template
from services.api_service import buscar_lista

estoques_bp = Blueprint("estoques", __name__)

@estoques_bp.route("/estoque")
def estoque() :
    estoques = buscar_lista("/estoques")

    total_itens = len(estoques)

    quantidade_total = sum(
        estoque.get("quantidadeAtual", 0) or 0
        for estoque in estoques
    )

    estoques_baixos = []

    for estoque in estoques :
        quantidade_atual = estoque.get("quantidadeAtual", 0) or 0
        estoque_minimo = estoque.get("estoqueMinimo", 0) or 0

        esta_baixo = quantidade_atual <= estoque_minimo

        estoque["estaBaixo"] = esta_baixo

        if esta_baixo :
            estoques_baixos.append(estoque)
    
    total_estoque_baixo = len(estoques_baixos)

    itens_ativos = sum(
        1 for estoque in estoques
        if estoque.get("ativo")
    )

    return render_template(
        "estoque.html",
        estoques=estoques,
        total_itens=total_itens,
        quantidade_total=quantidade_total,
        total_estoque_baixo=total_estoque_baixo,
        itens_ativos=itens_ativos
    )