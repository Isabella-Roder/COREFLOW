from flask import render_template, redirect, Blueprint, request
from services.api_service import buscar_lista, enviar_dados

cadastro_bp = Blueprint("cadastros", __name__)

def normalizar_valor(valor):
    if not valor:
        return 0

    return float(valor.replace(".", "").replace(",", "."))

@cadastro_bp.route("/cadastros")
def cadastros() :
    empresas = buscar_lista("/empresas")
    produtos_servicos = buscar_lista("/produtos-servicos")
    return render_template("cadastros.html", empresas = empresas, produtos_servicos = produtos_servicos)

@cadastro_bp.route("/cadastros/fornecedor", methods=["POST"])
def cadastrar_fornecedor() :
    dados = {
        "nome": request.form.get("nome"),
        "documento": request.form.get("documento"),
        "email": request.form.get("email"),
        "telefone": request.form.get("telefone"),
        "tipoServico": request.form.get("tipoServico"),
        "ativo": True,
        "empresa": {
            "id": int(request.form.get("empresaId"))
        }
    }

    enviar_dados("/fornecedores", dados)
    return redirect("/cadastros")

@cadastro_bp.route("/cadastros/produto-servico", methods=["POST"])
def cadastrar_produtoServico() :
    dados = {
        "nome": request.form.get("nome"),
        "descricao": request.form.get("descricao"),
        "precoVenda": normalizar_valor(request.form.get("precoVenda")),
        "custo": normalizar_valor(request.form.get("custo")),
        "tipo": request.form.get("tipo"),
        "ativo": True,
        "empresa": {
            "id": int(request.form.get("empresaId"))
        }
    }

    enviar_dados("/produtos-servicos", dados)
    return redirect("/cadastros")

@cadastro_bp.route("/cadastros/estoque", methods=["POST"])
def cadastrar_estoque() :
    dados = {
        "produtoServico": {
            "id": int(request.form.get("produtoServicoId")),
            "tipo": "PRODUTO"
        },
        "quantidadeAtual": int(request.form.get("quantidadeAtual")),
        "estoqueMinimo": int(request.form.get("estoqueMinimo")),
        "ativo": True,
        "empresa": {
            "id": int(request.form.get("empresaId"))
        }
    }

    enviar_dados("/estoques", dados)
    return redirect("/cadastros")

@cadastro_bp.route("/cadastros/cliente", methods=["POST"])
def cadastrar_cliente() :
    dados = {
        "nome": request.form.get("nome"),
        "documento": request.form.get("documento"),
        "email": request.form.get("email"),
        "telefone": request.form.get("telefone"),
        "empresa": {
            "id": int(request.form.get("empresaId"))
        }
    }

    enviar_dados("/clientes", dados)
    return redirect("/cadastros")