package com.example.phrasing.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PhraseInsertDTO(
        @NotNull
        @Size(min = 3, max = 255, message = "Phrase must be between 3 and 255 characters long")
        String text,
        @NotNull
        String username
) {
}
