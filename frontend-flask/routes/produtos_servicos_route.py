from flask import Blueprint, render_template
from services.api_service import buscar_lista

produtos_servicos_bp = Blueprint("produtosServicos", __name__)

@produtos_servicos_bp.route("/produtos-servicos")
def produtos_servicos() :
    produtos_servicos = buscar_lista("/produtos-servicos")

    total_itens = len(produtos_servicos)

    total_produtos = sum(
        1 for item in produtos_servicos
        if item.get("tipo") == "PRODUTO"
    )

    total_servicos = sum(
        1 for item in produtos_servicos
        if item.get("tipo") == "SERVICO"
    )

    soma_precos = sum(
        item.get("precoVenda", 0) or 0
        for item in produtos_servicos
    )

    if total_itens > 0 :
        preco_medio = soma_precos / total_itens
    else :
        preco_medio = 0

    soma_percentual_margem = 0

    for item in produtos_servicos :
        preco = item.get("precoVenda", 0) or 0
        custo = item.get("custo", 0) or 0

        margem = preco - custo

        if preco > 0 :
            percentual_margem = (margem / preco) * 100
        else :
            percentual_margem = 0
        
        item["margem"] = margem
        item["percentualMargem"] = percentual_margem

        soma_percentual_margem += percentual_margem
    
    if total_itens > 0 :
        margem_media = soma_percentual_margem / total_itens
    else :
        margem_media = 0
    
    return render_template(
        "produtos-servicos.html",
        produtos_servicos=produtos_servicos,
        total_itens=total_itens,
        total_produtos=total_produtos,
        total_servicos=total_servicos,
        preco_medio=preco_medio,
        margem_media=margem_media
    )