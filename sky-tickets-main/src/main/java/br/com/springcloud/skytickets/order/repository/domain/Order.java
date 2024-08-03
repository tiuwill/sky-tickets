package br.com.springcloud.skytickets.order.repository.domain;

public record Order(Long id, String buyerName, Long movieId, String sessionTime, String cardNumber) {}
