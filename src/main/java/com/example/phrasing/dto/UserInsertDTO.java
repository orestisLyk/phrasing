package com.example.phrasing.dto;

import jakarta.validation.constraints.*;

public record UserInsertDTO(
        @NotBlank
        @Size(min = 3, max = 50, message = "username must be at least 3 characters long")
        String username,
        @Email
        String email,
        @NotNull
        @Size(min = 8, max = 50, message = "password must be at least 8 characters long")
        String password) {
}
