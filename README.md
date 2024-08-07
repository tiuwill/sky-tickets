# SkyTickets

## Descrição
Este projeto é um exemplo de uma loja mock (não representa uma aplicação funcional real) de ingressos de cinema. Foi construída com Java 22 e Spring Boot 3.

## Objetivo
O objetivo deste projeto é demonstrar como podemos aplicar ferramentas e padrões de sistemas distribuídos utilizando Spring Cloud em uma aplicação Spring Boot existente, aumentando resiliência, observabilidade, escalabilidade e muito mais.

## Tecnologias Pré-Requisito
Para seguir os guias e a live de Spring Cloud, é necessário que o aluno possua as seguintes ferramentas instaladas no seu computador:

- Java JDK 22
- Docker (última versão, se possível)
- IntelliJ ou IDE de sua preferência
- Maven (recomendado na linha de comando, porém pode-se utilizar o embarcado do IntelliJ)

## Estrutura do Projeto

### Aplicação Principal (Main): **sky-tickets-main**
Este projeto é o monolito inicial, contendo todas as funcionalidades do sistema, como catálogo, criação de pedidos e mensagem de boas-vindas.

### Aplicação Order Manager: **sky-tickets-order-manager**
Este projeto é a segregação em micro serviço da funcionalidade de criação de pedidos. Ele serve como base para demonstrar o funcionamento do Spring Cloud API Gateway no gerenciamento de rotas para microserviços.

### Aplicação Payments: **sky-payments**
Esta aplicação é um micro serviço interno da rede que processa o pagamento dos pedidos. É utilizada para demonstrar a comunicação entre micro serviços usando Spring Cloud OpenFeign e os padrões service registry e discovery através da ferramenta Consul.

### Aplicação Config Server (dentro da pasta servers): **sky-tickets-config-server**
Esta aplicação é o servidor de configuração que provê configurações remotas aos outros micro serviços. Está configurada para um repositório externo referenciado nos anexos.

### Aplicação Gateway (dentro da pasta servers): **sky-tickets-gateway-server**
Esta aplicação serve como API Gateway, recebendo requisições e redirecionando-as para o micro serviço responsável.

## Guias
A pasta `guias` contém uma série de guias organizados na ordem em que as tecnologias serão apresentadas e aplicadas no projeto.

## Collection
A pasta `collections` contém duas coleções do Postman:

- **SkyTickets.postman_collection.json**: Para chamadas diretas aos microserviços.
- **SkyTickets Gateway.postman_collection.json**: Para chamadas ao gateway.

Estas coleções estão configuradas para agilizar os testes das diferentes partes da aplicação.

## Anexos
O repositório de configurações remotas pode ser encontrado no seguinte link:

[Repositório de Configurações Remotas](https://github.com/tiuwill/sky-tickets-config)
