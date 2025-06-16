package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);

    Optional<Autor> findByNombreIgnoreCase(String nombre);

    List<Autor> findByNacimientoBeforeAndFallecimientoAfter(int nacimiento, int fallecimiento);
    List<Autor> findByNacimientoBeforeAndFallecimientoIsNull(int año);

}
