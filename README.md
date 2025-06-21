# 📚 Literalura – Gestión de Libros desde Gutendex API

Este proyecto es una **aplicación de consola Java + Spring Boot** que permite buscar libros en la API pública [Gutendex](https://gutendex.com/) y almacenarlos localmente en una base de datos H2 (o la que configures).

### 🚀 Funcionalidades

- **Buscar y guardar** el primer resultado de una búsqueda por título
- **Listar libros** registrados en la base de datos
- **Listar autores** registrados
- **Filtrar autores vivos** en un año determinado
- **Listar libros** por idioma

### 🛠️ Tecnologías usadas

- Java 17
- Spring Boot 3
    - Spring Data JPA
    - Spring Web
- H2 Database (por defecto)
- Consumo de API REST con `HttpClient` y JSON con `Gson`
- Maven

### 📦 Estructura del Proyecto

```bash
com.alurachallenge.literalura
├── LiteraluraApplication.java         # Clase principal de Spring Boot
├── main
│   ├── java
│   │   ├── com.alurachallenge.literalura
│   │   │   ├── main
│   │   │   │   └── Principal.java     # CommandLineRunner, menú de consola
│   │   │   ├── dto
│   │   │   │   ├── LibroDTO.java      # Record de datos de libro (Gutendex)
│   │   │   │   └── AutorDTO.java      # Record de datos de autor
│   │   │   ├── mapper
│   │   │   │   └── LibroMapper.java   # Convierte DTO → entidad Libro
│   │   │   ├── model
│   │   │   │   ├── Libro.java         # Entidad JPA Libro
│   │   │   │   └── Autor.java         # Entidad JPA Autor
│   │   │   ├── repository
│   │   │   │   ├── LibroRepository.java
│   │   │   │   └── AutorRepository.java
│   │   │   ├── service
│   │   │   │   ├── LibroService.java
│   │   │   │   ├── AutorService.java
|   |   |   |   ├── ConsumoAPI.java
|   |   |   |   ├── ConvierteDatos.java
|   |   |   |   └── IConvierteDatos.java
└── resources      
    └── application.properties       # Configuración: URL H2, JPA, etc.              
```

### 🧪 Ejecución

#### 1. Clona el repositorio
```bash
git clone https://github.com/zaidarosales/AluraChallenge--LiterAlura.git
cd LiterAlura
```

#### 2. Configura tu JDK y Maven
- Java 17+
- Maven 3.6+

#### 3. Ejecuta la aplicación
```bash
mvn spring-boot:run
```
O bien, empaqueta y ejecuta el JAR:
```bash
mvn package
java -jar target/literalura-0.0.1-SNAPSHOT.jar
```

#### 4. Interactúa en consola
- Ingresa la opción deseada (1–5)
- Para buscar libros, proporciona un título; el primer resultado se guardará automáticamente
- Resto de opciones listará datos directamente en consola

---

### 📝 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**. Consulta el archivo [LICENSE](./LICENSE) para más detalles.
