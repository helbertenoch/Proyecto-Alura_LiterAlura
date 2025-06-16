    package com.alurachallenge.literalura.service;

    import com.alurachallenge.literalura.model.Autor;
    import com.alurachallenge.literalura.repository.AutorRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.Year;
    import java.util.List;

    @Service
    public class AutorService {
        @Autowired
        private AutorRepository autorRepository;

        Autor guardarAutor(Autor autor) {
            if (autor.getFallecimiento() != null && autor.getNacimiento() != null) {
                if (autor.getFallecimiento() < autor.getNacimiento()) {
                    throw new IllegalArgumentException("La fecha de fallecimiento no puede ser anterior a la fecha de nacimiento.");
                }
            }

            return autorRepository
                    .findByNombreIgnoreCase(autor.getNombre())
                    .orElseGet(() -> autorRepository.save(autor));
        }

        public List<Autor> listarAutores() {
            return autorRepository.findAll();
        }

        public List<Autor> autoresVivosEn(int año) {
            return autorRepository.findByNacimientoBeforeAndFallecimientoAfter(año, año);
        }

        public List<Autor> autoresVivosHoy() {
            int añoActual = Year.now().getValue();
            return autorRepository.findByNacimientoBeforeAndFallecimientoIsNull(añoActual);
        }

    }
