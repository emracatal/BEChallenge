package com.wit.BEChallenge.dto;

import com.wit.BEChallenge.entity.Gender;

public record CategoryResponse(Long id, Gender gender, String title, Double rating, String img) {
}
