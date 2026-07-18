from flask import Flask, render_template
from routes.dashboard_route import dashboard_bp
from routes.compras_route import compra_bp
from routes.cadastros_route import cadastro_bp
from routes.vendas_route import vendas_bp

app = Flask(__name__)

app.register_blueprint(dashboard_bp)
app.register_blueprint(compra_bp)
app.register_blueprint(cadastro_bp)
app.register_blueprint(vendas_bp)


if __name__ == "__main__" :
    app.run(debug=True, port=5000)