package br.com.springcloud.skytickets.order.client;

public record PaymentRequest(Long orderId, String cardNumber, Double amount) {}
