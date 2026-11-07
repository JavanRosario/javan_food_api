package com.javanfood.javanfood.core.jackson;

import org.springframework.boot.jackson.JacksonComponent;
import org.springframework.boot.jackson.ObjectValueSerializer;
import org.springframework.data.domain.PageImpl;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;

@JacksonComponent
public class PageJsonSerializer extends ObjectValueSerializer<PageImpl<?>> {


    @Override
    protected void serializeObject(PageImpl<?> value, JsonGenerator jgen, SerializationContext context) {
        jgen.writePOJOProperty("content", value.getContent());
        jgen.writeNumberProperty("size", value.getSize());
        jgen.writeNumberProperty("totalElements", value.getTotalElements());
        jgen.writeNumberProperty("totalPages", value.getTotalPages());
        jgen.writeNumberProperty("number", value.getNumber());
    }
}
