package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.Map;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public record Formats(

        @ElementCollection
        @JsonAlias("formats")
        Map<String, String> formatos_urls
) {}
