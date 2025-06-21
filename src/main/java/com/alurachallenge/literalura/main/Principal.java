package com.alurachallenge.literalura.main;

import com.alurachallenge.literalura.dto.LibroDTO;
import com.alurachallenge.literalura.mapper.LibroMapper;
import com.alurachallenge.literalura.service.AutorService;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal  implements CommandLineRunner {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private LibroService libroService;
    private LibroMapper libroMapper;
    private AutorService autorService;

    private static final String URL_BASE = "https://gutendex.com/books/?search=";

    public Principal(LibroService libroService, AutorService autorService, LibroMapper libroMapper) {
        this.libroService = libroService;
        this.autorService = autorService;
        this.libroMapper = libroMapper;
    }

    public void mostrarMenu() {
        var opcion = -1;

        while (opcion != 0) {
            var menu = """
                    --------------------
                    Elija la opción a través de su número:
                    1- Buscar libros por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idiomas
                    
                    0- Salir
                    """;
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibroYGuardar();
                case 2 -> libroService.listarLibrosRegistrados()
                        .forEach(l -> System.out.printf("%s (%s) - Autor: %s\n",l.getTitulo(),
                                l.getIdioma(),
                                l.getAutor() != null ? l.getAutor().getNombre() : "Desconocido"));
                case 3 -> autorService.listarAutores()
                        .forEach(a -> System.out.println(a.getNombre()));
                case 4 -> listarAutoresVivosEnUnAño();
                case 5 -> listarLibrosPorIdiomas();
                case 0 -> System.out.println("Saliendo del programa.....");
                default -> System.out.println("Opción inválido. Intente otra vez.");
            }
        }
    }

    private void listarLibrosPorIdiomas() {
        System.out.println("Ingrese el idioma:");
        String idioma = teclado.nextLine();
        libroService.listarPorIdioma(idioma)
                .forEach(libro -> System.out.printf("%s (%s) - Autor: %s\n",
                        libro.getTitulo(),
                        libro.getIdioma(),
                        libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
    }

    private void listarAutoresVivosEnUnAño() {
        System.out.println("Ingrese el año:");
        int año = Integer.parseInt(teclado.nextLine());
        var autores = autorService.autoresVivosEn(año);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
        } else {
            System.out.println("Autores vivos en el año" + año + ":");
            autores.forEach(a -> System.out.printf("%s (%d - %d)\n",
                    a.getNombre(),
                    a.getNacimiento() != null ? a.getNacimiento() : 0,
                    a.getFallecimiento() != null ? a.getFallecimiento() : 0));
        }

    }

    public void buscarLibroYGuardar() {
        System.out.println("Ingrese el título del libro: ");
        String titulo = teclado.nextLine().trim();

        String url = URL_BASE + titulo.replace(" ", "+");

        List<LibroDTO> librosEncontrados = consumoAPI.obtenerLibros(url);
        System.out.println(librosEncontrados);
        if (librosEncontrados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título");
        } else {
            LibroDTO primerLibro = librosEncontrados.get(0);
            System.out.printf("Libro encontrado: %s (%s)\n", primerLibro.title(),
                    primerLibro.languages());
            libroService.guardarLibro(primerLibro);

        }

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("<hola");
        mostrarMenu();
    }

}