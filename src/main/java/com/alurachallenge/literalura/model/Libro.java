package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

@Entity
public class Libro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;
        private String idioma;
        private Integer numeroDeDescargas;

        @ManyToOne(cascade = CascadeType.ALL)
        private Autor autor;

    public Libro() {}

    public Libro(String title, Autor autor, String idioma, int i) {
        this.titulo = title;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDeDescargas = i;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}

