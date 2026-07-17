from flask import Blueprint, render_template

from services.api_service import buscar_lista

compra_bp = Blueprint("compras", __name__)

@compra_bp.route("/compras")
def compras() :
    compras = buscar_lista("/compras")

    total_compras = len(compras)
    valor_total = sum(compra.get("valorTotal", 0) or 0 for compra in compras)
    maior_compra = max((compra.get("valorTotal", 0) or 0 for compra in compras), default=0)

    fornecedores = []
    for compra in compras :
        fornecedor = compra.get("fornecedor")
        if fornecedor :
            nome = fornecedor.get("nome")
            if nome and nome not in fornecedores :
                fornecedores.append(nome)

    return render_template(
        "compras.html",
        compras = compras,
        total_compras = total_compras,
        valor_total = valor_total,
        maior_compra = maior_compra,
        total_fornecedores = len(fornecedores)
    )