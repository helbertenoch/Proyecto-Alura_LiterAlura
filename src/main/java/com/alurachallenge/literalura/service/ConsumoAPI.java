package com.alurachallenge.literalura.service;

import com.alurachallenge.literalura.dto.GutendexResponseDTO;
import com.alurachallenge.literalura.dto.LibroDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class ConsumoAPI {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String obtenerDatos(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response =client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException  | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LibroDTO> obtenerLibros(String url) {
        try {
            String json = obtenerDatos(url);
            GutendexResponseDTO datos = objectMapper
                    .readValue(json, GutendexResponseDTO.class);
            return datos.results();
        } catch (Exception e){
            throw new RuntimeException("Error al procesar los datos de la API " + e.getMessage());
        }
    }
}
