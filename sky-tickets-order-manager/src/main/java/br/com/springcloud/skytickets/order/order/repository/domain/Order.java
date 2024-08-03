package br.com.springcloud.skytickets.order.order.repository.domain;

public record Order(String buyerName, Long movieId, String sessionTime, String cardNumber) {}
