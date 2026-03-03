package co.edu.escuelaing.arep.example;

import co.edu.escuelaing.arep.microframework.MicroSpring;

/**
 * Example Application demonstrating the Microframework usage.
 */
public class App {
    public static void main(String[] args) {
        // Defines where static files are located
        MicroSpring.staticfiles("target/classes/webroot/public");

        // Registers GET REST services
        MicroSpring.get("/App/hello", (req, resp) -> {
            String name = req.getValues("name");
            if (name == null || name.isEmpty()) {
                name = "World";
            }
            return "{\"message\": \"Hello " + name + "\"}";
        });

        MicroSpring.get("/App/pi", (req, resp) -> {
            return String.valueOf(Math.PI);
        });

        // Start the server
        MicroSpring.startServer(args);
    }
}
