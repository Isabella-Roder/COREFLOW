import requests

API_URL = "http://localhost:8080"

def buscar_lista(rota) :
    try :
        resposta = requests.get(f"{API_URL}{rota}", timeout=5)
        resposta.raise_for_status()
        dados = resposta.json()

        if isinstance(dados, list) :
            return dados
        
        return [dados]
    
    except requests.RequestException as erro :
        print(f"Erro ao buscar {rota}: {erro}")
        return []

def enviar_dados(rota, dados) :
    try :
        resposta = requests.post(f"{API_URL}{rota}", json=dados, timeout=5)
        resposta.raise_for_status()
        return resposta.json()
    except requests.RequestException as erro :
        print(f"Erro ao enviar dados para {rota}: {erro}")
        return None