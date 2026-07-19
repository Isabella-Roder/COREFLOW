from flask import Blueprint, render_template, request, redirect, url_for
from services.api_service import buscar_lista

portal_cliente_bp = Blueprint("portal_cliente", __name__)

@portal_cliente_bp.route("/portal-cliente")
def portal_cliente() :
    return render_template("portal-cliente-login.html")

@portal_cliente_bp.route("/portal-cliente/home")
def portal_cliente_home() :
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

    
    total_comprado = sum(
        venda.get("total", 0) or 0 
        for venda in vendas_cliente
    )

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

@portal_cliente_bp.route("/portal-cliente/entrar", methods=["POST"])
def entrar_portal_cliente() :
    email = request.form.get("email", "").strip().lower()

    clientes = buscar_lista("/clientes")

    cliente_encontrado = None

    for cliente in clientes :
        email_cliente = (cliente.get("email") or "").strip().lower()

        if email_cliente == email :
            cliente_encontrado = cliente
            break

    if not cliente_encontrado :
        return render_template(
            "portal-cliente-login.html",
            erro="Cliente não encontrado com esse e-mail."
        )

    return redirect(url_for("portal_cliente.portal_cliente_home", clienteId=cliente_encontrado.get("id")))

@portal_cliente_bp.route("/portal-cliente/compras/<int:venda_id>")
def detalhe_compra(venda_id) :
    cliente_id = request.args.get("clienteId", type=int)

    vendas = buscar_lista("/vendas")

    venda_encontrada = None

    for venda in vendas :
        if venda.get("id") == venda_id :
            venda_encontrada = venda
            break

    if not venda_encontrada :
        return "Compra não encontrada", 404

    cliente = venda_encontrada.get("cliente")

    if not cliente or cliente.get("id") != cliente_id :
        return "Compra não pertence a este cliente", 403

    return render_template(
        "portal-compra-detalhe.html",
        venda=venda_encontrada,
        cliente_id=cliente_id
    )

@portal_cliente_bp.route("/portal-cliente/faturas/<int:fatura_id>")
def detalhe_fatura(fatura_id) :
    cliente_id = request.args.get("clienteId", type=int)

    clientes = buscar_lista("/clientes")

    cliente_selecionado = None

    for cliente in clientes :
        if cliente.get("id") == cliente_id :
            cliente_selecionado = cliente
            break 

    if not cliente_selecionado :
        return "Cliente não encontrado", 404
    
    empresa = cliente_selecionado.get("empresa")

    if not empresa :
        return "Cliente não possui empresa vinculada", 404
    
    empresa_id = empresa.get("id")

    contas = buscar_lista(f"/contas-financeiras/empresa/{empresa_id}")

    fatura_encontrada = None

    for conta in contas :
        cliente = conta.get("cliente")

        if(
            conta.get("id") == fatura_id
            and conta.get("tipo") == "RECEBER"
            and cliente
            and cliente.get("id") == cliente_id
        ) :
            fatura_encontrada = conta
            break

    if not fatura_encontrada :
        return "Fatura não encontrada para este cliente", 404
    
    return render_template(
        "portal-fatura-detalhe.html",
        fatura=fatura_encontrada,
        cliente=cliente_selecionado,
        cliente_id=cliente_id
    )
