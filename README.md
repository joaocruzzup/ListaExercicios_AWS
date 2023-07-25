# Estacionamento DEEP - Aplicação de Gerenciamento
Este repositório contém uma aplicação para gerenciar a entrada, permanência e saída de carros no estacionamento DEEP. A aplicação utiliza um banco de dados compartilhado com a equipe e oferece funcionalidades essenciais para o cliente realizar a gestão de veículos estacionados.

## Funcionalidades
- Visualização dos carros estacionados, incluindo a sua permanência, valor a ser pago e o dono do carro.
- Inserção da entrada de um novo carro com informações como nome do carro, permanência, placa e nome do dono, além do valor a ser pago.
- Atualização dos carros já estacionados, permitindo ajustar o valor a ser pago caso o veículo passe da permanência de 1 hora.
- Exclusão de carros que estão saindo do estacionamento

## Tabelas do banco de dados (postgresql)

O banco de dados possui três tabelas para armazenar os dados:

### Tabela "clientes"
- id (SERIAL): Identificador único do cliente.
- nome (VARCHAR(250)): Nome do cliente.
- cpf (VARCHAR(14)): CPF do cliente.

  
### Tabela "carros"
- id (SERIAL): Identificador único do carro.
- nome (VARCHAR(100)): Nome do carro.
- id_cliente (INTEGER): Chave estrangeira que referencia o cliente associado ao carro.
- placa (VARCHAR(10)): Placa do carro.
- data_entrada (DATE): Data de entrada do carro no estacionamento.
- hora_entrada (TIME): Hora de entrada do carro no estacionamento.

  
### Tabela "permanencias"
- id (SERIAL): Identificador único da permanência.
- idCarro (INTEGER): Chave estrangeira que referencia o carro associado à permanência.
- data_saida (DATE): Data de saída do carro do estacionamento.
- hora_saida (TIME): Hora de saída do carro do estacionamento.
- valor (DECIMAL(10,2)): Valor a ser pago pela permanência do carro.

## Como executar o projeto

1. Certifique-se de ter um banco de dados compatível (por exemplo, PostgreSQL) disponível e acessível.
2. Clone este repositório em sua máquina local.
3. Configure as informações de conexão com o banco de dados no código da aplicação.
4. Execute a aplicação em um ambiente compatível com Java.

