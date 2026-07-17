from flask import Flask, render_template
from routes.dashboard_route import dashboard_bp

app = Flask(__name__)

app.register_blueprint(dashboard_bp)   

if __name__ == "__main__" :
    app.run(debug=True, port=5000)