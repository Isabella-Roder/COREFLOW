from flask import Blueprint, render_template
from services.api_service import buscar_lista, enviar_dados

fornecedores_bp = Blueprint("fornecedores", __name__)

@fornecedores_bp.route("/fornecedores")
def fornecedores() :
    fornecedores = buscar_lista("/fornecedores")
    compras = buscar_lista("/compras")

    total_fornecedores = len(fornecedores)

    fornecedores_ativos = sum(
        1 for fornecedor in fornecedores
        if fornecedor.get("ativo")
    )

    fornecedores_inativos = total_fornecedores - fornecedores_ativos

    fornecedores_com_compra_ids = []

    for compra in compras :
        fornecedor = compra.get("fornecedor")

        if fornecedor :
            fornecedor_id = fornecedor.get("id")

            if fornecedor_id not in fornecedores_com_compra_ids :
                fornecedores_com_compra_ids.append(fornecedor_id)

    fornecedores_com_compra = len(fornecedores_com_compra_ids)

    for fornecedor in fornecedores :
        total_compra_fornecedor = 0
        valor_comprado_fornecedor = 0

        for compra in compras :
            fornecedor_compra = compra.get("fornecedor")

            if fornecedor_compra and fornecedor_compra.get("id") == fornecedor.get("id") :
                total_compra_fornecedor += 1
                valor_comprado_fornecedor += compra.get("valorTotal", 0) or 0
        
        fornecedor["totalCompras"] = total_compra_fornecedor
        fornecedor["valorFornecido"] = valor_comprado_fornecedor

    return render_template(
        "fornecedores.html",
        fornecedores=fornecedores,
        total_fornecedores=total_fornecedores,
        fornecedores_ativos=fornecedores_ativos,
        fornecedores_inativos=fornecedores_inativos,
        fornecedores_com_compra=fornecedores_com_compra
    )
        