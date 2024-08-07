# Configurando Repositório de Configurações Remoto

Este guia deve ser aplicado nos projetos: 
    Server: **sky-tickets-config-server**
    Client: **sky-tickets-main**

## Passo 1: Configurar o Repositório Git

Clone ou crie um repositório Git para armazenar as configurações remotas:

[Repositório de Configuração - Sky Tickets](https://github.com/tiuwill/sky-tickets-config)

## Passo 2: Criar Arquivo de Configurações

Crie um arquivo de configurações com o nome do microserviço para disponibilizar as configurações remotas. Por exemplo:

[sky-tickets.properties](https://github.com/tiuwill/sky-tickets-config/blob/main/sky-tickets.properties)

```properties
hello.message=Bem vindo remoto ao Sky Tickets
```

Coloque suas configurações no arquivo para que o servidor as distribua para o cliente correto.

---

# Configurando o Spring Cloud Config Server

Primeiro, vamos criar um servidor para o Spring Cloud Config.

## Passo 1: Adicionar Gerenciamento de Dependências do Spring Cloud

Adicione o gerenciamento de dependências do Spring Cloud no seu `pom.xml`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Passo 2: Definir a Versão do Spring Cloud

Crie a propriedade para importar a versão do Spring Cloud:

```xml
<properties>
    <spring-cloud.version>2023.0.3</spring-cloud.version>
</properties>
```

## Passo 3: Adicionar Dependência do Spring Cloud Config Server

Adicione a dependência do Spring Cloud Config Server no seu `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

## Passo 4: Habilitar o Config Server

Adicione a anotação `@EnableConfigServer` na classe principal da sua aplicação:

```java
@SpringBootApplication
@EnableConfigServer
public class SkyTicketsConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyTicketsConfigServerApplication.class, args);
    }

}
```

## Passo 5: Configurar o `application.properties` do Servidor

Defina a localização das suas configurações no arquivo `application.properties` do servidor:

```properties
spring.application.name=sky-tickets-config-server
server.port=8888
spring.cloud.config.server.git.uri=https://github.com/tiuwill/sky-tickets-config.git
```

---

# Configurando o Spring Cloud Config Client

## Passo 1: Adicionar Gerenciamento de Dependências do Spring Cloud

Adicione o gerenciamento de dependências do Spring Cloud no seu `pom.xml`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Passo 2: Definir a Versão do Spring Cloud

Crie a propriedade para importar a versão do Spring Cloud:

```xml
<properties>
    <spring-cloud.version>2023.0.3</spring-cloud.version>
</properties>
```

## Passo 3: Adicionar Dependência do Spring Cloud Config Starter Client

Adicione a dependência do Spring Cloud Config Starter Client no seu `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

## Passo 4: Configurar o `application.properties` do Cliente

Altere o arquivo `application.properties` do projeto para apontar para o servidor de configurações:

```properties
spring.application.name=sky-tickets
spring.config.import=configserver:http://localhost:8888
```

O nome da aplicação é obrigatório e será utilizado para selecionar a configuração necessária do repositório.

---

Com essas configurações, você terá um servidor de configurações Spring Cloud configurado para distribuir configurações remotas para os seus clientes. Certifique-se de ajustar as URLs e nomes conforme necessário para o seu ambiente.