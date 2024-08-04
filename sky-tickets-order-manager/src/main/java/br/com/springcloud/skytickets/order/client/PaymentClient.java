package br.com.springcloud.skytickets.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payments")
public interface PaymentClient {

    @PostMapping("/payment/process")
    String processPayment(@RequestBody PaymentRequest paymentRequest);
}