package br.com.springcloud.skytickets.order;

import br.com.springcloud.skytickets.order.order.OrderController;
import br.com.springcloud.skytickets.order.order.repository.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "br.com.springcloud.skytickets.payments:payments:+:stubs:8090")
public class OrderControllerTests {

    @Autowired
    private OrderController orderController;


    @Test
    public void testCreateOrder() {
        Order order = new Order("buyer", 1L, "14pm", "card-number-123");

        orderController.createOrder(order);
    }
}