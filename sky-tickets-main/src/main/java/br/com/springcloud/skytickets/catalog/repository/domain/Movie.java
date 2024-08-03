package br.com.springcloud.skytickets.catalog.repository.domain;

public record Movie(Long id, String title, String imageUrl, String sessionTime) {}