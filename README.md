# Financial Volatility Monitor com Spring Boot

 

Um serviço backend automatizado, construído com Java e Spring Boot, que monitora a volatilidade de ativos financeiros. A aplicação busca dados do Yahoo Finance, calcula o indicador **ATR (Average True Range)** e salva o histórico em um banco de dados MySQL.

-----

## Funcionalidades

  * **Coleta de Dados**: Busca automática de dados de mercado (Abertura, Máxima, Mínima e Fechamento) da API pública do Yahoo Finance.
  * **Cálculo de Volatilidade**: Implementa a lógica para calcular o ATR (Average True Range) de 14 períodos, um indicador prático de volatilidade.
  * **Agendamento**: Utiliza o Spring Scheduler (`@Scheduled`) para executar a rotina de monitoramento em intervalos de tempo configuráveis.
  * **Persistência**: Salva o histórico de volatilidade em um banco de dados MySQL para futuras consultas e análises.

-----

## Tecnologias Utilizadas

  * **Java 17+**
  * **Spring Boot**
  * **Spring Data JPA** (Hibernate)
  * **Spring Scheduler**
  * **MySQL**
  * **Lombok**

-----

## Como Executar

1.  **Pré-requisitos**:

      * Ter o Java (JDK 17 ou superior) instalado.
      * Ter o Maven ou Gradle instalado.
      * Ter uma instância do MySQL rodando.

2.  **Clone o repositório**:

    ```bash
    git clone https://github.com/danyelbarboza/Financial-Volatility-Monitor-Spring-Boot.git
    ```

3.  **Configure o Banco de Dados**:
    Abra o arquivo `src/main/resources/application.properties` e atualize as informações de conexão com o seu banco de dados MySQL.

    ```properties
    # Configuração do MySQL
    spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados?createDatabaseIfNotExist=true
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Configuração do Hibernate
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    ```

4.  **Execute a Aplicação**:
    Navegue até a pasta raiz do projeto e execute o seguinte comando:

    ```bash
    ./mvnw spring-boot:run
    ```

    Ou execute a classe principal `VolatilityMonitorApplication.java` diretamente da sua IDE. O monitor começará a executar a rotina no próximo intervalo agendado.