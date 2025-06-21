package com.alurachallenge.literalura.service;

import com.alurachallenge.literalura.dto.AutorDTO;
import com.alurachallenge.literalura.dto.LibroDTO;
import com.alurachallenge.literalura.mapper.LibroMapper;
import com.alurachallenge.literalura.model.Autor;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.AutorRepository;
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
    private AutorRepository autorRepository;

    @Autowired
    private LibroMapper libroMapper;

    public Libro guardarLibro(LibroDTO dto) {
        String titulo = dto.title();

        Optional<Libro> libroExistente = libroRepository.findByTituloIgnoreCase(titulo);
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya está regristado" + titulo);
            return libroExistente.get();
        }
        Libro libro = libroMapper.desdeDTO(dto);

        Autor autor = null;

        if (dto.authors() != null && !dto.authors().isEmpty()) {
            AutorDTO autorDTO = dto.authors().get(0);
            Optional<Autor> autorExistente = autorRepository.findByNombreIgnoreCase(autorDTO.name());

            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
            } else {
                autor = new Autor(autorDTO.name(), autorDTO.birth_year(), autorDTO.death_year());
                autor = autorRepository.save(autor);
            }
        } else {
            System.out.println("Libro sin autor, Se guardara con autor = null");
        }
        libro.setAutor(autor);
        return libroRepository.save(libro);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }

    private Autor guardarAutorSiNoExiste(Autor autor) {
        Optional<Autor> existente = autorRepository.findByNombreIgnoreCase(autor.getNombre());
        if (existente.isPresent()) {
            return existente.get();
        } else {
            return autorRepository.save(autor);
        }
    }
}
