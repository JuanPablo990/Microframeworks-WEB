package co.edu.escuelaing.arep.microframework;

/**
 * Represents the HTTP Response.
 */
public class Response {
    private String body;

    public Response() {
        this.body = "";
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
