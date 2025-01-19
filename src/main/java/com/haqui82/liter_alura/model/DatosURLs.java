package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true)
public record DatosURLs(
     @JsonAlias("text/html") String web,
     @JsonAlias("application/octet-stream") String stream
) {
}
