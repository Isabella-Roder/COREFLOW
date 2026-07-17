from flask import Blueprint, render_template
import requests

dashboard_bp = Blueprint("dashboard", __name__)

API_URL = "http://localhost:8080"

def buscar_lista(rota) :
    try :
        resposta = requests.get(f"{API_URL}{rota}", timeout=5)
        resposta.raise_for_status()
        dados = resposta.json()

        if isinstance(dados, list) :
            return dados
        
        return [dados]
    
    except requests.RequestException :
        return []

@dashboard_bp.route("/")
def dashboard() :
    empresas = buscar_lista("/empresas")
    clientes = buscar_lista("/clientes")
    fornecedores = buscar_lista("/fornecedores")
    produtos_servicos = buscar_lista("/produtos-servicos")

    return render_template (
        "dashboard.html",
        empresas = empresas,
        clientes = clientes,
        fornecedores = fornecedores,
        produtos_servicos = produtos_servicos
    )  