from flask import Blueprint, render_template, request
from services.api_service import buscar_lista

porta_cliente_bp = Blueprint("portal_cliente", __name__)

@porta_cliente_bp.route("/portal-cliente")
def portal_cliente() :
    clientes = buscar_lista("/clientes")
    vendas = buscar_lista("/vendas")

    cliente_id = request.args.get("clienteId", type=int)

    cliente_selecionado = None
    vendas_cliente = []
    faturas_cliente = []

    if cliente_id :
        for cliente in clientes :
            if cliente.get("id") == cliente_id :
                cliente_selecionado = cliente
        
        for venda in vendas :
            cliente = venda.get("cliente")
            if cliente and cliente.get("id") == cliente_id :
                vendas_cliente.append(venda)
        
        empresa_id = None
        if cliente_selecionado and cliente_selecionado.get("empresa") :
            empresa_id = cliente_selecionado["empresa"].get("id")

        if empresa_id:
            contas = buscar_lista(f"/contas-financeiras/empresa/{empresa_id}")

            for conta in contas :
                cliente = conta.get("cliente")
                if (
                    conta.get("tipo") == "RECEBER"
                    and cliente
                    and cliente.get("id") == cliente_id
                ) :
                    faturas_cliente.append(conta)

    
    total_comprado = sum(venda.get("total", 0) or 0 for venda in vendas_cliente)

    total_em_aberto = sum(
        conta.get("valor", 0) or 0
        for conta in faturas_cliente
        if conta.get("status") == "PENDENTE"
    )

    return render_template(
        "portal-cliente.html",
        clientes=clientes,
        cliente_id=cliente_id,
        cliente=cliente_selecionado,
        vendas=vendas_cliente,
        faturas=faturas_cliente,
        total_comprado=total_comprado,
        total_em_aberto=total_em_aberto
    )