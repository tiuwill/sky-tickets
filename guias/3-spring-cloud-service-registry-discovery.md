# Setup do Consul e Microserviços (Service Registry e Service Discovery)

Este guia modifica os seguintes projetos:
- sky-payments
- sky-tickets-main
- sky-tickets-order-manager
- sky-tickets-gateway-server

## 1. Configuração do Consul

### 1.1. Faça pull da imagem Docker do Consul

```bash
docker pull consul:1.15.4
```

### 1.2. Inicie o serviço na porta 8500

```bash
docker run -d --name=consul -p 8500:8500 consul:1.15.4
```

Abra [http://localhost:8500](http://localhost:8500/ui/dc1/services) no navegador para validar o funcionamento do Consul.

## 2. Setup dos Microserviços

### 2.1. Adicione Dependency Management nos Módulos

Em módulos que ainda não têm Spring Cloud, adicione o gerenciamento de dependências:

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

Defina a versão do Spring Cloud:

```xml
<spring-cloud.version>2023.0.3</spring-cloud.version>
```

### 2.2. Adicione Dependências para Consul e Actuator

Para que os projetos possam se comunicar com o Consul, adicione as seguintes dependências, incluindo a dependência do Actuator para que o Consul possa consultar informações de saúde do microserviço:

```xml
<!-- Discovery and register -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 2.3. Adicione Propriedades de Configuração nos Projetos

Configure as propriedades nos arquivos `application.properties` dos projetos:

```properties
spring.cloud.consul.host=localhost
spring.cloud.consul.port=8500
spring.cloud.consul.discovery.enabled=true
spring.cloud.consul.discovery.register=true
spring.cloud.consul.discovery.prefer-ip-address=true
spring.cloud.consul.discovery.health-check-path=/actuator/health
spring.cloud.consul.discovery.health-check-interval=10s
management.endpoints.web.exposure.include=health,info
```

### 2.4. Crie a Configuração do `RestTemplate` no Projeto `sky-tickets-order-manager`

Adicione a classe de configuração `AppConfig`:

```java
@Configuration
public class AppConfig {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

### 2.5. Faça a Chamada para o Serviço de Pagamentos no Projeto `order`

No projeto `order`, faça a requisição para o serviço `payments`:

```java
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        PaymentRequest paymentRequest = new PaymentRequest(1L, "card-number", 20.0);
        restTemplate.postForObject("http://payments/payment/process", paymentRequest, String.class);
        return orderRepository.save(order);
    }
}
```

Com essas etapas, seus microserviços estarão configurados para se registrar e descobrir uns aos outros via Consul, além de realizar verificações de saúde automaticamente.