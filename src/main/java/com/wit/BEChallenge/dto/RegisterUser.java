package com.wit.BEChallenge.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

public record RegisterUser(String fullName, String email, @Valid @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password should be Minimum eight characters, at least one letter, one number and one special character.") String password) {
}