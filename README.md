# Financial Volatility Monitor - Spring Boot

Um serviço de backend construído em Java e Spring Boot para monitorar a volatilidade de ativos financeiros da bolsa brasileira. A aplicação consome dados da API Brapi.dev, calcula o indicador ATR (Average True Range) e persiste o histórico em um banco de dados MySQL para análise.

## Funcionalidades Principais

- **Coleta de Dados Históricos**: Busca automática de dados de mercado (Abertura, Máxima, Mínima e Fechamento) da API da Brapi.
- **Cálculo de Volatilidade**: Implementa a lógica para calcular o ATR (Average True Range) de 14 períodos, um indicador clássico para medir a volatilidade de um ativo.
- **Agendamento de Tarefas**: Utiliza o Spring Scheduler (`@Scheduled`) para executar a rotina de monitoramento de forma automática e periódica (configurado para rodar de segunda a sexta às 18:00).
- **Persistência de Dados**: Salva o histórico de volatilidade (Ticker, Preço de Fechamento, Valor do ATR e Data) em um banco de dados MySQL.
- **API REST Gerenciável**: Expõe endpoints para listar os dados de volatilidade e para adicionar, atualizar e remover os ativos que devem ser monitorados.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA (com Hibernate) para persistência de dados.
- Spring Web para a criação dos endpoints REST.
- Spring Scheduler para automação das tarefas.
- MySQL Connector J como driver de banco de dados.
- Lombok para reduzir código boilerplate.
- Maven para gerenciamento de dependências.

## Pré-requisitos para Execução

- Java JDK 21 ou superior instalado.
- Maven configurado nas variáveis de ambiente.
- Uma instância do MySQL ativa e acessível.
- Um token de acesso da API Brapi.dev. Você pode obtê-lo gratuitamente no site oficial.

## Configuração e Execução

### Clone o repositório:

```bash
git clone https://github.com/danyelbarboza/Financial-Volatility-Monitor-Spring-Boot.git
cd Financial-Volatility-Monitor-Spring-Boot
```

### Configure as Variáveis de Ambiente:

O projeto utiliza variáveis de ambiente para configurar o acesso ao banco de dados e o token da API. Crie as seguintes variáveis no seu sistema:

- `DB_PASSWORD`: Senha do seu usuário root do MySQL.
- `BRAPI_TOKEN`: Seu token de acesso da API Brapi.dev.

### Configure o Banco de Dados:

Abra o arquivo `src/main/resources/application.properties` e, se necessário, altere a URL de conexão do seu banco de dados MySQL. O nome do banco será `financial_data` e ele será criado automaticamente se não existir.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/financial_data?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
```

### Execute a Aplicação:

Utilize o Maven Wrapper para iniciar o serviço:

```bash
./mvnw spring-boot:run
```

Você também pode executar a classe principal `VolatilityMonitorApplication.java` diretamente da sua IDE.

## Endpoints da API

A aplicação expõe os seguintes endpoints para interação:

### Gerenciamento de Ativos para Monitorar

- **POST `/api/stocks/add`**  
  Adiciona um novo ativo à lista de monitoramento.  
  **Corpo da Requisição:**
  ```json
  {
    "stock": "PETR4"
  }
  ```

- **GET `/api/stocks/all`**  
  Lista todos os ativos que estão sendo monitorados.

- **PUT `/api/stocks/update/{id}`**  
  Atualiza o ticker de um ativo existente pelo seu ID.  
  **Corpo da Requisição:**
  ```json
  {
    "stock": "VALE3"
  }
  ```

- **DELETE `/api/stocks/delete/{id}`**  
  Remove um ativo da lista de monitoramento pelo seu ID.

### Dados de Volatilidade

- **GET `/api/finav/all`**  
  Retorna todo o histórico de dados de volatilidade que foi salvo no banco.

- **POST `/api/finav/monitor`**  
  Dispara manualmente a rotina para buscar e calcular a volatilidade de todos os ativos da lista de monitoramento.

- **POST `/api/finav/monitor/{stock}`**  
  Dispara manualmente a rotina para um único ativo específico (ex: `PETR4`).

### Aviso

  Este é um projeto desenvolvido exclusivamente para fins de estudo e construção de portfólio. Ele não deve ser utilizado em um ambiente de produção nem como base para tomar decisões financeiras reais. Os dados e os cálculos são fornecidos "como estão", sem garantias de precisão ou disponibilidade.