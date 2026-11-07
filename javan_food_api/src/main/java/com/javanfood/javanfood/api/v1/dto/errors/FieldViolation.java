package com.javanfood.javanfood.api.v1.dto.errors;

public record FieldViolation(
        String field,
        String message
) {
}
