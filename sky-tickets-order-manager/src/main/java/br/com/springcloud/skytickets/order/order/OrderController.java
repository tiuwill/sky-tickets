package br.com.springcloud.skytickets.order.order;



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
    private RestTemplate restTemplate;


    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        PaymentRequest paymentRequest = new PaymentRequest(1L, "card-number", 20.0);
        restTemplate.postForObject("http://payments/payment/process", paymentRequest,  String.class);
        return orderRepository.save(order);
    }
}