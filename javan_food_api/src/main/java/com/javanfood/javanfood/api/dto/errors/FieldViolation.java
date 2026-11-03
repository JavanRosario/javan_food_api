package com.javanfood.javanfood.api.dto.errors;

public record FieldViolation(
        String field,
        String message
) {
}
