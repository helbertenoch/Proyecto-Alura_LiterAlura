package com.alurachallenge.literalura.service;

import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorService autorService;

    public Libro guardarLibro(Libro libro) {
        Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(libro.getTitulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está registrado");
            return libroExistente.get();
        }

        Autor autor = autorService.guardarAutor(libro.getAutor());
        libro.setAutor(autor);
        return libroRepository.save(libro);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
