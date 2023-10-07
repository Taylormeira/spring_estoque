package com.example.spring_estoque.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EstoqueRecordDto(@NotBlank @NotNull String nm_estoque, @NotNull String ds_estoque ) {
}
