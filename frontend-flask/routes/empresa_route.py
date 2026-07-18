from flask import Blueprint, render_template
from services.api_service import buscar_lista

empresas_bp = Blueprint("empresas", __name__)

@empresas_bp.route("/empresas")
def empresa() :
    empresas = buscar_lista("/empresas")
    clientes = buscar_lista("/clientes")
    fornecedores = buscar_lista("/fornecedores")
    produtos_servicos = buscar_lista("/produtos-servicos")
    vendas = buscar_lista("/vendas")
    compras = buscar_lista("/compras")

    total_empresas = len(empresas)

    for empresa in empresas :
        empresa_id = empresa.get("id")

        cliente_empresa = [
            cliente for cliente in clientes
            if cliente.get("empresa") and cliente.get("empresa").get("id") == empresa_id
        ]

        fornecedores_empresa = [
            fornecedor for fornecedor in fornecedores
            if fornecedor.get("empresa") and fornecedor.get("empresa").get("id") == empresa_id
        ]

        itens_empresa = [
            item for item in produtos_servicos
            if item.get("empresa") and item.get("empresa").get("id") == empresa_id
        ]

        vendas_empresa = [
            venda for venda in vendas
            if venda.get("empresa") and venda.get("empresa").get("id") == empresa_id
        ]

        compras_empresa = [
            compra for compra in compras
            if compra.get("empresa") and compra.get("empresa").get("id") == empresa_id
        ]

        total_vendido = sum(
            venda.get("valorTotal", 0) or 0
            for venda in vendas_empresa
        )

        total_comprado = sum(
            compra.get("valorTotal", 0) or 0
            for compra in compras_empresa
        )

        empresa["totalClientes"] = len(cliente_empresa)
        empresa["totalFornecedores"] = len(fornecedores_empresa)
        empresa["totalItens"] = len(itens_empresa)
        empresa["totalVendas"] = len(vendas_empresa)
        empresa["totalCompras"] = len(compras_empresa)
        empresa["totalVendido"] = total_vendido
        empresa["totalComprado"] = total_comprado
        empresa["saldo"] = total_vendido - total_comprado
    
    empresas_com_movimento = sum(
        1 for empresa in empresas
        if empresa.get("totalVendas", 0) > 0 or empresa.get("totalCompras", 0) > 0
    )

    return render_template(
        "empresa.html",
        empresas=empresas,
        total_empresas=total_empresas,
        empresas_com_movimento=empresas_com_movimento
    )