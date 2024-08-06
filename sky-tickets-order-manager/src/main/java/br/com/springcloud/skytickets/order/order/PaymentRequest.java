package br.com.springcloud.skytickets.payments.payment;

public record PaymentRequest(Long orderId, String cardNumber, Double amount) {}
