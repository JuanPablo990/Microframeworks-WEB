# Microframeworks WEB Project

Este proyecto es un servidor web y microframework construido desde cero utilizando Java Sockets. Permite a los desarrolladores publicar servicios REST mediante funciones lambda y especificar un directorio para servir archivos estáticos (HTML, CSS, JS, IMG), inspirado en herramientas como SparkJava o Express.js.

## Arquitectura

La arquitectura de la solución se basa en un patrón de diseño orientado a objetos y funciones lambda dentro del ecosistema de Java (JDK 11+).

### Componentes Principales:
1. **`HttpServer`**: El demonio central que escucha peticiones en un puerto TCP (por defecto 8080) empleando `ServerSocket` y `Socket`. Se encarga de analizar (parsear) la línea de la petición HTTP, extraer el método, el path y los valores del Query String.
2. **`MicroSpring`**: Clase principal del framework que expone los servicios estáticos a los desarrolladores. Sirve como un enrutador (Router), manteniendo internamente un mapeo de URIs hacia expresiones lambda (`Route`), además de almacenar la configuración de la ruta para archivos estáticos.
3. **`Route`**: Interfaz funcional (`@FunctionalInterface`) que define el contrato `handle(Request req, Response resp)`.
4. **`Request` & `Response`**: Encapsulan la información de la petición HTTP (parámetros del Query, Path, etc.) y la respuesta a emitir.
5. **`App`**: Una aplicación de ejemplo que demuestra el uso del framework, registrando dos servicios GET (`/App/hello` y `/App/pi`) y configurando el acceso a un archivo `index.html`.

## Prerrequisitos

* Java JDK 11 o superior.
* Maven instalado y configurado en el PATH.
* Git.

## Instalación y Ejecución

1. Clona el repositorio.
2. Navega al directorio raíz del proyecto (`Microframeworks-WEB`).
3. Compila y empaqueta el proyecto empleando Maven:
   ```bash
   mvn clean compile package
   ```
4. Para correr el servidor con la aplicación de ejemplo, ejecuta:
   ```bash
   mvn exec:java
   ```
   *El servidor iniciará e imprimirá en consola: `Microframework HTTP Server listening on port: 8080`*

## Pruebas Manuales (Ejemplo de Ejecución)

Abre un navegador web y navega a las siguientes URLs:

1. **Prueba de página estática**: 
   Navega a: `http://localhost:8080/index.html` (o `http://localhost:8080/`)
   Deberás ver una página HTML estilizada con botones interactivos que consumen los servicios REST.

2. **Prueba de Servicio REST simple (PI)**:
   Navega a: `http://localhost:8080/App/pi`
   Deberás recibir la respuesta: `3.141592653589793`

3. **Prueba de Servicio REST con Query Params (Hello)**:
   Navega a: `http://localhost:8080/App/hello?name=Pedro`
   Deberás recibir la respuesta JSON: `{"message": "Hello Pedro"}`
   *Si omites el parámetro `name`, el framework usará "World" por defecto.*

## Pruebas Automatizadas

El proyecto incluye pruebas unitarias construidas con JUnit 4 que verifican:
- El correcto mapeo de las URLs de los servicios REST.
- El almacenamiento del valor del Query String y el correcto parsing dentro del objeto `Request`.
- La asignación del directorio para los archivos estáticos.

Para ejecutar los Unit Tests, corre:
```bash
mvn test
```