package br.com.springcloud.skytickets.payments.payment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    @PostMapping("/process")
    public String processPayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Processing Payment");
        return "Payment processed for order ID: " + paymentRequest.orderId();
    }
}
