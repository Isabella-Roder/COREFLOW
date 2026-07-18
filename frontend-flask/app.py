from flask import Flask
from routes.dashboard_route import dashboard_bp
from routes.compras_route import compra_bp
from routes.cadastros_route import cadastro_bp
from routes.vendas_route import vendas_bp
from routes.clientes_route import clientes_bp
from routes.fornecedores_route import fornecedores_bp
from routes.produtos_servicos_route import produtos_servicos_bp
from routes.estoque_route import estoques_bp
from routes.movimentacoes_estoque_route import movimentacoes_estoque_bp

app = Flask(__name__)

app.register_blueprint(dashboard_bp)
app.register_blueprint(compra_bp)
app.register_blueprint(cadastro_bp)
app.register_blueprint(vendas_bp)
app.register_blueprint(clientes_bp)
app.register_blueprint(fornecedores_bp)
app.register_blueprint(produtos_servicos_bp)
app.register_blueprint(estoques_bp)
app.register_blueprint(movimentacoes_estoque_bp)


if __name__ == "__main__" :
    app.run(debug=True, port=5000)