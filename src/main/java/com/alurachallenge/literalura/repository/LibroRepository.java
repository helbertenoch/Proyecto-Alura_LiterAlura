package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByTituloAndAutor_Nombre(String titulo, String autorNombre);

    Optional<Libro> findByTituloIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
