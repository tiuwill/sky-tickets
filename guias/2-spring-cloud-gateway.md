## Configurando Spring Application Gateway

Este guia deve ser aplicado no projeto: **sky-tickets-gateway-server**

### 1. Adicione o Dependency Management

Adicione o seguinte bloco ao seu `pom.xml` para gerenciar as dependências do Spring Cloud:

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

### 2. Defina a Versão do Spring Cloud

Adicione a versão do Spring Cloud no seu `pom.xml`:

```xml
<properties>
    <spring-cloud.version>2023.0.3</spring-cloud.version>
</properties>
```

### 3. Adicione a Dependência do Spring Cloud Gateway

Inclua a dependência do Spring Cloud Gateway no seu `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
```

### 4. Configure o Gateway

Adicione as seguintes propriedades ao seu arquivo `application.properties` para configurar o Gateway:

```properties
spring.application.name=sky-tickets-gateway-server
spring.main.web-application-type=reactive
server.port=9090

spring.cloud.gateway.globalcors.add-to-simple-url-handler-mapping=true
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedOrigins=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedHeaders=*
spring.cloud.gateway.globalcors.corsConfigurations.[/**].allowedMethods=*
```

### 5. Crie a Configuração de Rotas na Aplicação

Crie uma classe de configuração para definir as rotas no Gateway:

```java
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("catalog-service", r -> r
                        .path("/catalog/**")
                        .uri("http://localhost:8080"))
                .route("greetings-service", r -> r
                        .path("/greetings/**")
                        .uri("http://localhost:8080"))
                .route("order-service", r -> r
                        .path("/order/**")
                        .uri("http://localhost:8081"))
                .build();
    }

}
```

Este guia fornece as etapas necessárias para configurar o Spring Application Gateway em uma aplicação Spring Boot. Siga os passos para garantir que o gateway esteja configurado corretamente e redirecione as requisições para os serviços apropriados.