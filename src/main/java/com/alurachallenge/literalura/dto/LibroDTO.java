package com.alurachallenge.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(
    int id,
    String title,
    List<AutorDTO> authors,
    List<String> languages,
    int download_count) {
}
