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

- [ ] Cadastrar cliente.
- [ ] Listar clientes.
- [ ] Editar cliente.
- [ ] Consultar historico de vendas por cliente.

### 3.4 Fornecedores

- [ ] Cadastrar fornecedor.
- [ ] Listar fornecedores.
- [ ] Editar fornecedor.
- [ ] Consultar historico de compras por fornecedor.

### 3.5 Produtos e Servicos

- [ ] Cadastrar produto.
- [ ] Cadastrar servico.
- [ ] Definir preco de venda.
- [ ] Definir custo.
- [ ] Controlar status ativo/inativo.

### 3.6 Estoque

- [ ] Entrada de estoque.
- [ ] Saida de estoque.
- [ ] Ajuste manual de estoque.
- [ ] Alerta de estoque baixo.

### 3.7 Vendas

- [ ] Registrar venda.
- [ ] Associar cliente.
- [ ] Baixar estoque automaticamente.
- [ ] Gerar conta a receber.

### 3.8 Compras

- [ ] Registrar compra.
- [ ] Associar fornecedor.
- [ ] Atualizar estoque automaticamente.
- [ ] Gerar conta a pagar.

### 3.9 Financeiro

- [ ] Contas a pagar.
- [ ] Contas a receber.
- [ ] Pagamento de conta.
- [ ] Recebimento de conta.
- [ ] Fluxo de caixa.

### 3.10 Dashboards e Relatorios

- [ ] Dashboard gerencial no Flask.
- [ ] Faturamento do periodo.
- [ ] Despesas do periodo.
- [ ] Lucro estimado.
- [ ] Contas vencidas.
- [ ] Relatorios com Python.
- [ ] Exportacao CSV/PDF.

## 4. Arquitetura

- Spring Boot sera a API principal.
- Flask consumira a API Java para renderizar telas e dashboards.
- Python ficara responsavel por relatorios, graficos e indicadores.
- O banco inicial sugerido e PostgreSQL.

