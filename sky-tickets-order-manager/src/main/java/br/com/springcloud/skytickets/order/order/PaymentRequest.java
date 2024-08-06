package br.com.springcloud.skytickets.order.order;

public record PaymentRequest(Long orderId, String cardNumber, Double amount) {}
