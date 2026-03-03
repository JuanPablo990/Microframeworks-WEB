package co.edu.escuelaing.arep.microframework;

/**
 * Functional interface that represents a route handler.
 */
@FunctionalInterface
public interface Route {
    /**
     * Handles an HTTP request and provides a response.
     *
     * @param req  The HTTP request
     * @param resp The HTTP response
     * @return An object representing the body of the response (e.g., a String)
     */
    Object handle(Request req, Response resp);
}
