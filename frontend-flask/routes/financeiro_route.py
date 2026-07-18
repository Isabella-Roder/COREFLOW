from flask import Blueprint, render_template, request, redirect, url_for
from services.api_service import buscar_lista, enviar_dados
import requests

financeiro_bp = Blueprint("financeiro", __name__)

API_URL = "http://localhost:8080"

def moeda_para_decimal(valor) :
    if not valor :
        return 0

    return float(valor.replace(".", "").replace(",", "."))

@financeiro_bp.route("/financeiro")
def financeiro() :
    empresas = buscar_lista("/empresas")
    clientes = buscar_lista("/clientes")
    fornecedores = buscar_lista("/fornecedores")

    empresa_id = request.args.get("empresaId")

    contas = []
    if empresa_id :
        contas = buscar_lista(f"/contas-financeiras/empresa/{empresa_id}")

    total_receber = 0
    total_pagar = 0
    total_vencidas = 0

    for conta in contas :
        valor = conta.get("valor", 0) or 0
        tipo = conta.get("tipo")
        status = conta.get("status")

        if status == "PENDENTE" :
            if tipo == "RECEBER" :
                total_receber += valor
            
            if tipo == "PAGAR" :
                total_pagar += valor
        
        if status == "VENCIDO" :
            total_vencidas += valor
    
    saldo_previsto = total_receber - total_pagar

    return render_template(
        "financeiro.html",
        empresas=empresas,
        clientes=clientes,
        fornecedores=fornecedores,
        contas=contas,
        empresa_id=empresa_id,
        total_receber=total_receber,
        total_pagar=total_pagar,
        total_vencidas=total_vencidas,
        saldo_previsto=saldo_previsto
    )

@financeiro_bp.route("/financeiro/criar", methods=["POST"])
def criar_conta_financeira() :
    tipo = request.form.get("tipo")

    dados = {
        "descricao": request.form.get("descricao"),
        "valor": moeda_para_decimal(request.form.get("valor")),
        "dataVencimento": request.form.get("dataVencimento"),
        "tipo": tipo,
        "empresa": {
            "id": int(request.form.get("empresaId"))
        }
    }

    cliente_id = request.form.get("clienteId")
    fornecedor_id = request.form.get("fornecedorId")

    if tipo == "RECEBER" and cliente_id :
        dados["cliente"] = {
            "id": int(cliente_id)
        }
    
    if tipo == "PAGAR" and fornecedor_id :
        dados["fornecedor"] = {
            "id": int(fornecedor_id)
        }
    
    enviar_dados("/contas-financeiras", dados)

    return redirect(url_for("financeiro.financeiro", empresaId=request.form.get("empresaId")))


@financeiro_bp.route("/financeiro/<int:conta_id>/pagar", methods=["POST"])
def pagar_conta(conta_id) :
    empresa_id = request.form.get("empresaId")

    requests.put(f"{API_URL}/contas-financeiras/{conta_id}/pagar")

    return redirect(url_for("financeiro.financeiro", empresa_id=empresa_id))
        
@financeiro_bp.route("/financeiro/<int:conta_id>/cancelar", methods=["POST"])
def cancelar_conta(conta_id) :
    empresa_id = request.form.get("empresaId")

    requests.put(f"{API_URL}/contas-financeiras/{conta_id}/cancelar")

    return redirect(url_for("financeiro.financeiro", empresa_id=empresa_id))