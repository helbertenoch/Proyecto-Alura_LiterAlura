package com.alurachallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GutendexResponseDTO(
        int count,
        String next,
        String previus,
        List<LibroDTO> results) {
}
