package com.alurachallenge.literalura.mapper;

import com.alurachallenge.literalura.dto.LibroDTO;
import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.Libro;
import org.springframework.stereotype.Component;

@Component
public class LibroMapper {
    public static Libro desdeDTO(LibroDTO dto) {
        Autor autor = dto.authors() != null && !dto.authors().isEmpty()
                ? new Autor(
                dto.authors().get(0).name(),
                dto.authors().get(0).birth_year(),
                dto.authors().get(0).death_year()
        )
                : new Autor("Desconocido", null, null);

        autor.setId(null);

        String idioma = dto.languages() != null && !dto.languages().isEmpty()
                ? dto.languages().get(0)
                : "desconocido";

        return new Libro(
                dto.title(),
                null,
                idioma,
                dto.download_count()
        );
    }
}
