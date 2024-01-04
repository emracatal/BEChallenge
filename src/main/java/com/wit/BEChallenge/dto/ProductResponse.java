package com.wit.BEChallenge.dto;

public record ProductResponse(Long id, String name, String description, Double price, Integer stock, Double rating, String image) {
}
