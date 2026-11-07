package com.javanfood.javanfood.core.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.boot.jackson.JacksonMixin;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.json.ProblemDetailJacksonMixin;

@JacksonMixin(ProblemDetail.class)
@JsonPropertyOrder({
        "type",
        "title",
        "status",
        "detail",
        "instance",
        "code",
        "timestamp"
})
public abstract class ProblemDetailMixin implements ProblemDetailJacksonMixin {
}