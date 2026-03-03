# Lista de Requerimientos Cumplidos

Aquí tienes una lista detallada de los elementos evaluables descritos en el enunciado del proyecto ("Project Statement") y su estado actual en nuestra implementación en `Microframeworks-WEB`:

### 1. GET Static Method for REST Services (Método GET Estático para Servicios REST)
✅ **Cumplido:** 
- Implementamos el método `MicroSpring.get(String path, Route route)`.
- Se usa una Interfaz Funcional `Route` con el método `handle()` para procesar la expresión Lambda provista por el desarrollador.
- **Ejemplo en el código:** `MicroSpring.get("/App/hello", (req, resp) -> ...)` está funcionando de acuerdo al ejemplo de requerimiento en `App.java`.

### 2. Query Value Extraction Mechanism (Mecanismo de Extracción del Query String)
✅ **Cumplido:** 
- Se construyó la clase `Request`.
- La clase automáticamente parsea los valores que están después del signo `?` en la URL (ej. `?name=Pedro`) dividiendo las llaves y valores.
- En `App.java` y en los servicios Lambda esto se puede consultar fácilmente como se pidió usando: `req.getValues("name")`.

### 3. Static File Location Specification (Especificación de Ubicación de Archivos Estáticos)
✅ **Cumplido:** 
- Implementamos el método estático `MicroSpring.staticfiles(String folder)`.
- En caso de configurar una carpeta (e.g. `staticfiles("target/classes/webroot/public")`), si la ruta de llegada en la petición HTTP _no empata_ con ningún servicio REST de Lambda, el `HttpServer` irá a buscar el archivo físico en el sistema de archivos del servidor.
- Además, lee los *bytes* de los archivos y envía los encabezados HTTP apropiados (`Content-Type` para `.html`, `.css`, `.js`, etc.) y estatus 200 o envía un `404 Not Found` si el archivo no existe.

### 4. Additional Tasks: The Example Application (Aplicación de Muestra del desarrollador)
✅ **Cumplido:** 
- Se implementó la clase `App.java` la cual funciona exactamente como lo exigía el taller:
  1. Configura la ruta de archivos estáticos.
  2. Publica un servicio REST: `http://localhost:8080/App/hello?name=Pedro`.
  3. Publica un servicio REST: `http://localhost:8080/App/pi`.
  4. Cuenta con un HTML interactivo (`index.html`) que consume dichos servicios mediante Javascript (`fetch()`) para confirmar que toda la arquitectura funciona interconectada.

### 5. Deliverables (Entregables Arquitectónicos y Estructurales)
✅ **Cumplido:** 
- **Maven & Git (`The project should be built using Maven and Git`)**: Completamente implementado vía un fichero `pom.xml`. Se compila con `mvn compile` y se probó empaquetamiento y test automatizados.
- **README Descriptivo**: Ya se construyó un `README.md` robusto con instrucciones de corrida, arquitectura, pruebas y explicación del ejemplo.
- **Tests Performed**: Se implementó una suite de Testing con JUnit v4+ en la ruta `src/test/java/.../FrameworkTest.java` para verificar parseado y mapeo de Lambda.

---

**Resumen general:**
Todos los puntos de la Rúbrica de la escuela (Escuela Colombiana de Ingeniería) descritos en el requerimiento **se han cumplido al 100%**. Lo único que faltaba al momento del último _prompt_ era que subieras el proyecto construido hacia GitHub, lo cual al parecer ya realizaste exitosamente basándonos en tu último mensaje: `Submission status: Submitted for grading` a la URL de tu repositorio de Git. 🚀
