### Configuração do OpenFeign no Microserviço `sky-tickets-order-manager`

Este guia explica como adicionar e configurar o OpenFeign para comunicação entre microserviços no projeto `sky-tickets-order-manager`.

#### 1. Adicione a dependência do OpenFeign no `pom.xml`

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

#### 2. Habilite Feign Clients na aplicação

Adicione a anotação `@EnableFeignClients` na classe principal da sua aplicação.

```java
@SpringBootApplication
@EnableFeignClients
public class SkyTicketsOrderManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyTicketsOrderManagerApplication.class, args);
    }
}
```

#### 3. Crie o Client Feign para comunicação com o microserviço de pagamentos

Crie uma interface Feign Client para o microserviço de pagamentos (`payments`).

```java
@FeignClient(name = "payments")
public interface PaymentClient {

    @PostMapping("/payment/process")
    String processPayment(@RequestBody PaymentRequest paymentRequest);
}
```



#### 4. Altere o Order Controller para usar o Client Feign

Modifique o `OrderController` para utilizar o `PaymentClient` ao criar um novo pedido.

```java
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        PaymentRequest paymentRequest = new PaymentRequest(order.getId(), "card-number", order.getTotalAmount());
        paymentClient.processPayment(paymentRequest);
        return orderRepository.save(order);
    }
}
```

Este guia deve ajudar a configurar e utilizar o OpenFeign para comunicação eficiente entre microserviços no projeto `sky-tickets-order-manager`.