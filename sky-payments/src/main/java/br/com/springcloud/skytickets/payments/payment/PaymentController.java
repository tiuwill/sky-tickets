package br.com.springcloud.skytickets.payments.payment;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        return "Payment processed for order ID: " + paymentRequest.orderId();
    }
}
