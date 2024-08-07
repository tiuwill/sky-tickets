## Configurando Testes de Contrato com Spring Cloud

Este guia deve ser aplicado nas seguintes aplicaçõs:
- sky-payments
- sky-tickets-order-manager


### Aplicação Produtora: `sky-payments`

#### 1. Adicionar as Dependências no Projeto que Produz a API:

```xml
<!-- test contract -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-contract-verifier</artifactId>
    <scope>test</scope>
</dependency>
```

#### 2. Adicionar o Plugin para Gerar os Testes de Contrato:

```xml
<plugin>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-contract-maven-plugin</artifactId>
    <extensions>true</extensions>
    <configuration>
        <baseClassForTests>
            br.com.springcloud.skytickets.payments.PaymentContractTests
        </baseClassForTests>
    </configuration>
</plugin>
```

#### 3. Criar a Classe de Teste que foi Passada no Plugin `PaymentContractTests`:

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class PaymentContractTests {

    @Autowired
    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder
                = MockMvcBuilders.standaloneSetup(paymentController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
```

#### 4. Criar o Arquivo do Contrato da API na Parte do Produtor (Onde Está o Controller que é Chamado)

Na pasta `/src/test/resources/contracts`

**nome_do_contrato.groovy**

```groovy
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/payment/process'
        body([
                orderId: 1,
                cardNumber: "card-number",
                amount: 20.0
        ])
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body([
                orderId: 1,
                cardNumber: "card-number",
                amount: 20.0
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
```

#### 5. Rodar o `mvn clean install` e Observar os Testes Gerados na Pasta `target` do Projeto

Localização dos testes gerados:

`target/generated-test-sources/contracts`

Os testes irão falhar inicialmente para demonstrar um exemplo de quebra de contrato.

#### 6. Configurar a API do Payments para Retornar um Objeto JSON e Resolver o Problema do Teste:

```java
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/process")
    public PaymentRequest processPayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Payment processed for order ID: " + paymentRequest.getOrderId());
        return paymentRequest;
    }
}
```

Execute o `mvn clean install` para fazer o build e o deploy local do artefato.

### Lado do Consumidor: `sky-tickets-order-manager`

#### 1. Adicionar a Dependência do Stub Runner:

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-contract-stub-runner</artifactId>
    <scope>test</scope>
</dependency>
```

#### 2. Criar o Seguinte Teste:

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "br.com.springcloud.skytickets.payments:payments:+:stubs:8090")
public class OrderControllerTests {

    @Autowired
    private OrderController orderController;

    @Autowired
    private PaymentClient paymentClient;

    @Test
    public void testCreateOrder() {
        Order order = new Order("buyer", 1L, "14pm", "card-number-123");

        orderController.createOrder(order);
    }
}
```

### Conclusão

Seguindo esses passos, você configurará corretamente os testes de contrato usando Spring Cloud Contract. Isso garantirá que as APIs do produtor e do consumidor estejam em conformidade com o contrato estabelecido, ajudando a evitar quebras de integração entre serviços.