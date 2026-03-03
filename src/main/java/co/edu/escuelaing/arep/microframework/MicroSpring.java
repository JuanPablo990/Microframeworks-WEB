package co.edu.escuelaing.arep.microframework;

import java.util.HashMap;
import java.util.Map;

/**
 * Core class for the microframework that holds the routing table and static
 * file configuration.
 */
public class MicroSpring {
    // Stores the registered GET routes
    private static final Map<String, Route> getRoutes = new HashMap<>();

    // Directory for static files
    private static String staticFilesLocation = "target/classes/webroot/public"; // default

    /**
     * Registers a GET route.
     * 
     * @param path  The path of the endpoint (e.g., "/hello").
     * @param route The lambda function to execute when the path is requested.
     */
    public static void get(String path, Route route) {
        getRoutes.put(path, route);
    }

    /**
     * Sets the directory for serving static files.
     * 
     * @param folder The directory path relative to the project root.
     */
    public static void staticfiles(String folder) {
        staticFilesLocation = folder;
    }

    /**
     * Returns the registered route for a given path, or null if not found.
     */
    public static Route getRoute(String path) {
        return getRoutes.get(path);
    }

    /**
     * Returns the configured static files location.
     */
    public static String getStaticFilesLocation() {
        return staticFilesLocation;
    }

    /**
     * Starts the HTTP server on port 8080.
     */
    public static void startServer(String[] args) {
        try {
            HttpServer server = new HttpServer();
            server.start(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
