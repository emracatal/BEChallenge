package com.wit.BEChallenge.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(String email,String password) {
}
