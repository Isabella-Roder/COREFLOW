from flask import Blueprint, redirect, render_template, request
from services.api_service import buscar_lista, enviar_dados

clientes_bp = Blueprint("clientes", __name__)

@clientes_bp.route("/clientes")
def clientes() :
    clientes = buscar_lista("/clientes")
    vendas = buscar_lista("/vendas")

    total_clientes = len(clientes)

    clientes_ativos = sum(
        1 for cliente in clientes
        if cliente.get("ativo")
    )

    clientes_inativos = total_clientes - clientes_ativos

    clientes_com_venda_ids = []

    for venda in vendas :
        cliente = venda.get("cliente")

        if cliente :
            cliente_id = cliente.get("id")

            if cliente_id not in clientes_com_venda_ids :
                clientes_com_venda_ids.append(cliente_id)
    
    clientes_com_venda = len(clientes_com_venda_ids)

    for cliente in clientes:
        total_vendas_cliente = 0
        valor_comprado_cliente = 0

        for venda in vendas:
            cliente_venda = venda.get("cliente")

            if cliente_venda and cliente_venda.get("id") == cliente.get("id"):
                total_vendas_cliente += 1
                valor_comprado_cliente += venda.get("valorTotal", 0) or 0

        cliente["totalVendas"] = total_vendas_cliente
        cliente["valorComprado"] = valor_comprado_cliente

    return render_template(
        "clientes.html",
        clientes=clientes,
        total_clientes=total_clientes,
        clientes_ativos=clientes_ativos,
        clientes_inativos=clientes_inativos,
        clientes_com_venda=clientes_com_venda
    )