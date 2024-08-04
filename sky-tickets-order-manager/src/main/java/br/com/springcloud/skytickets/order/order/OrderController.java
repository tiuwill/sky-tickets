package br.com.springcloud.skytickets.order.order;



import br.com.springcloud.skytickets.order.client.PaymentClient;
import br.com.springcloud.skytickets.order.client.PaymentRequest;
import br.com.springcloud.skytickets.order.order.repository.OrderRepository;
import br.com.springcloud.skytickets.order.order.repository.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentClient paymentClient;


    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        PaymentRequest paymentRequest = new PaymentRequest(1L, "card-number", 20.0);
        paymentClient.processPayment(paymentRequest);
        return orderRepository.save(order);
    }
}