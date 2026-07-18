from flask import render_template, Blueprint, request, redirect
from services.api_service import buscar_lista, enviar_dados

vendas_bp = Blueprint("vendas", __name__)

@vendas_bp.route("/vendas")
def vendas() :
    vendas = buscar_lista("/vendas")
    clientes = buscar_lista("/clientes")
    empresas = buscar_lista("/empresas")
    produtos_servicos = buscar_lista("/produtos-servicos")

    total_vendas = len(vendas)

    valor_total = sum(
        venda.get("valorTotal", 0) or 0
        for venda in vendas
    )

    maior_venda = max(
        (venda.get("valorTotal", 0) or 0 for venda in vendas),
        default=0
    )

    clientes_unicos = []

    for venda in vendas :
        cliente = venda.get("cliente")

        if cliente :
            cliente_id = cliente.get("id")

            if cliente_id not in clientes_unicos :
                clientes_unicos.append(cliente_id)

    total_clientes = len(clientes_unicos)

    return render_template(
        "vendas.html",
        vendas = vendas,
        empresas = empresas,
        clientes = clientes,
        produtos_servicos = produtos_servicos,
        total_vendas = total_vendas,
        valor_total = valor_total,
        maior_venda = maior_venda,
        total_clientes = total_clientes
    )

@vendas_bp.route("/vendas/registrar", methods=["POST"])
def registrar_venda() :
    dados = {
        "empresaId": int(request.form.get("empresaId")),
        "clienteId": int(request.form.get("clienteId")),
        "itens": [
            {
                "produtoServicoId": int(request.form.get("produtoServicoId")),
                "quantidade": int(request.form.get("quantidade"))
            }
        ]
    }

    enviar_dados("/vendas", dados)
    return redirect("/vendas")