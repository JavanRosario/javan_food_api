package com.javanfood.javanfood.domain.modelXmls;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.javanfood.javanfood.domain.model.Cozinha;
import jakarta.annotation.Nonnull;
import lombok.Data;

import java.util.List;

@JacksonXmlRootElement(localName = "Cozinhas")
@Data
public class CozinhaXml {

    @JsonProperty("cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    @Nonnull
    private List<Cozinha> cozinhaList;
}
