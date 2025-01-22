package com.haqui82.liter_alura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public record Languages(

        @ElementCollection
        @JsonAlias("languages")
        List<String> lenguaje
) {}
