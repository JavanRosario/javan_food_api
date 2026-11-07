package com.javanfood.javanfood.core.data;

import com.javanfood.javanfood.domain.exception.OrdenacaoInvalidaException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public final class PageableTranslator {

    private PageableTranslator() {
    }

    public static Pageable translate(
            Pageable pageable,
            Map<String, String> fields) {

        var invalidFields = pageable.getSort().stream()
                .map(Sort.Order::getProperty)
                .filter(property -> !fields.containsKey(property))
                .toList();

        if (!invalidFields.isEmpty()) {
            throw new OrdenacaoInvalidaException(
                    "Campos de ordenação inválidos: " + invalidFields
            );
        }

        var orders = pageable.getSort().stream()
                .map(order -> order.withProperty(
                        fields.get(order.getProperty())
                ))
                .toList();

        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(orders)
        );
    }
}

