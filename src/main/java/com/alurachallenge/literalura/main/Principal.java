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
                case 2 -> libroService.listarLibrosRegistrados();
                case 3 -> autorService.listarAutores().forEach(a -> System.out.println(a.getNombre()));
                case 4 -> {
                    System.out.println("Ingrese el año:");
                    int año = Integer.parseInt(teclado.nextLine());
                    autorService.autoresVivosEn(año).forEach(a -> System.out.println(a.getNombre()));
                }
                case 5 -> {
                    System.out.println("Ingrese el idioma (ej. 'en'):");
                    String idioma = teclado.nextLine();
                    libroService.listarPorIdioma(idioma);
                }
                case 0 -> System.out.println("Saliendo del programa.....");
                default -> System.out.println("Opción inválido. Intente otra vez.");
            }
        }
    }

    public void buscarLibroYGuardar() {
        System.out.println("Ingrese el título del libro: ");
        String titulo = teclado.nextLine().trim();

        String url = URL_BASE + titulo.replace(" ", "+");

        List<LibroDTO> librosEncontrados = consumoAPI.obtenerLibros(url);
        System.out.println(librosEncontrados);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("<hola");
        mostrarMenu();
    }

}