# Requisitos - CoreFlow

## 1. Visao Geral

CoreFlow sera um sistema de gestao empresarial para pequenas e medias empresas, com foco em operacao, financeiro, estoque e relatorios.

## 2. Objetivos

- Cadastrar empresas.
- Gerenciar usuarios por empresa.
- Controlar clientes e fornecedores.
- Controlar produtos e servicos.
- Controlar estoque.
- Registrar vendas e compras.
- Controlar contas a pagar e contas a receber.
- Exibir fluxo de caixa.
- Gerar dashboards gerenciais.
- Gerar relatorios com Python.

## 3. Modulos

### 3.1 Empresas

- [x] Cadastrar empresa.
- [x] Listar empresas.
- [x] Buscar empresa por ID.
- [x] Editar empresa.
- [x] Excluir empresa.
- [x] Vincular colaboradores a uma empresa.
- [x] Cadastrar departamentos por empresa.
- [x] Listar departamentos por empresa.

### 3.2 Usuarios e Permissoes

- [x] Cadastro de colaboradores.
- [x] Perfis basicos de colaborador.
- [ ] Cadastro de usuarios para login.
- [ ] Login.
- [ ] Perfis de acesso.
- [ ] Permissoes por modulo.

### 3.3 Clientes

- [x] Cadastrar cliente.
- [x] Listar clientes.
- [x] Editar cliente.
- [x] Consultar historico de vendas por cliente.

### 3.4 Fornecedores

- [x] Cadastrar fornecedor.
- [x] Listar fornecedores.
- [x] Editar fornecedor.
- [x] Consultar historico de compras por fornecedor.

### 3.5 Produtos e Servicos

- [x] Cadastrar produto.
- [x] Cadastrar servico.
- [x] Definir preco de venda.
- [x] Definir custo.
- [x] Controlar status ativo/inativo.

### 3.6 Estoque

- [x] Cadastro de estoque.
- [x] Listar estoque por empresa.
- [x] Entrada de estoque.
- [x] Saida de estoque.
- [x] Ajuste manual de estoque.
- [x] Historico de movimentacoes por estoque.
- [x] Historico de movimentacoes por empresa.
- [x] Alerta de estoque baixo.
### 3.7 Vendas

- [x] Registrar venda.
- [x] Associar cliente.
- [x] Registrar itens da venda.
- [x] Calcular total da venda.
- [x] Baixar estoque automaticamente.
- [x] Registrar movimentacao de saida por venda.
- [ ] Gerar conta a receber automaticamente pela venda.
### 3.8 Compras

- [x] Registrar compra.
- [x] Associar fornecedor.
- [x] Atualizar estoque automaticamente.
- [x] Registrar movimentacao de entrada por compra.
- [ ] Gerar conta a pagar automaticamente pela compra.

### 3.9 Financeiro

- [x] Contas a pagar.
- [x] Contas a receber.
- [x] Pagamento de conta.
- [x] Recebimento de conta.
- [ ] Fluxo de caixa.

### 3.10 Dashboards e Relatorios

- [x] Dashboard inicial no Flask.
- [x] Resumo de vendas e compras no dashboard.
- [x] Resumo de compras do periodo.
- [x] Saldo gerencial inicial no dashboard.
- [x] Template base Flask para telas internas.
- [x] Tela Flask de cadastros operacionais.
- [x] Tela Flask de vendas.
- [x] Tela Flask de compras.
- [x] Tela Flask de clientes.
- [x] Tela Flask de fornecedores.
- [x] Tela Flask de produtos e servicos.
- [x] Tela Flask de estoque.
- [x] Tela Flask de movimentacoes de estoque.
- [x] Tela Flask de empresas.
- [x] Tela Flask de relatorio executivo.
- [x] Tela Flask de financeiro.
- [ ] Contas vencidas.
- [x] Relatorio executivo inicial com Flask/Python.
- [x] Graficos no relatorio executivo.
- [x] Grafico de vendas x compras.
- [x] Grafico de catalogo.
- [x] Grafico de movimentacoes de estoque.
- [x] Grafico de estoque por produto.
- [ ] Exportacao CSV/PDF.

### 3.11 Portal do Cliente

- [ ] Area separada para clientes acessarem informacoes proprias.
- [ ] Visualizar dados do cliente.
- [ ] Visualizar compras realizadas.
- [ ] Visualizar contas a receber/faturas em aberto.
- [ ] Visualizar historico de pagamentos.
- [ ] Login de cliente.

## 4. Arquitetura

- Spring Boot sera a API principal.
- Flask consumira a API Java para renderizar telas e dashboards.
- Python ficara responsavel por relatorios, graficos e indicadores.
- O banco inicial sugerido e PostgreSQL.



