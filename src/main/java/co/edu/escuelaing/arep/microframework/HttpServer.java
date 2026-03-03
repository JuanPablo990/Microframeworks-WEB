package co.edu.escuelaing.arep.microframework;

import java.io.*;
import java.net.*;
import java.nio.file.*;

/**
 * The HTTP Server engine that accepts requests and delegates them
 * to the registered routes or serves static files.
 */
public class HttpServer {

    private boolean running = false;

    /**
     * Starts the server on the specified port.
     */
    public void start(int port) {
        running = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Microframework HTTP Server listening on port: " + port);

            while (running) {
                Socket clientSocket = null;
                try {
                    clientSocket = serverSocket.accept();
                    handleClientRequest(clientSocket);
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                } finally {
                    if (clientSocket != null) {
                        try {
                            clientSocket.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
        }
    }

    /**
     * Processes individual client requests.
     */
    private void handleClientRequest(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty())
                return;

            // Extract HTTP Method, URI and Protocol
            String[] reqParts = requestLine.split(" ");
            if (reqParts.length < 3)
                return;

            String method = reqParts[0];
            String uri = reqParts[1];

            // Print the rest of headers (optional, for debugging)
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.isEmpty())
                    break;
            }

            // Parse URI to get Path and Query String
            String path = uri;
            String queryString = "";
            int qIndex = uri.indexOf("?");
            if (qIndex != -1) {
                path = uri.substring(0, qIndex);
                queryString = uri.substring(qIndex + 1);
            }

            if ("GET".equalsIgnoreCase(method)) {
                // Check if there is a registered REST route
                Route route = MicroSpring.getRoute(path);

                if (route != null) {
                    processRestRequest(path, queryString, route, out);
                } else {
                    processStaticFileRequest(path, out);
                }
            } else {
                sendError(out, 405, "Method Not Allowed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRestRequest(String path, String queryString, Route route, OutputStream out) throws IOException {
        Request req = new Request(path, queryString);
        Response resp = new Response();

        // Execute the developer's lambda
        Object result = route.handle(req, resp);

        String responseBody = result != null ? result.toString() : "";

        PrintWriter writer = new PrintWriter(out, true);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: application/json; charset=UTF-8");
        writer.println("Content-Length: " + responseBody.length());
        writer.println();
        writer.print(responseBody);
        writer.flush();
    }

    private void processStaticFileRequest(String path, OutputStream out) throws IOException {
        if ("/".equals(path)) {
            path = "/index.html";
        }

        String basePath = MicroSpring.getStaticFilesLocation();
        // Resolving the absolute path within the project
        File file = new File(basePath + path);
        if (file.exists() && !file.isDirectory()) {
            byte[] fileData = Files.readAllBytes(file.toPath());
            String contentType = getContentType(path);

            PrintWriter writer = new PrintWriter(out, true);
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: " + contentType);
            writer.println("Content-Length: " + fileData.length);
            writer.println();
            writer.flush();
            out.write(fileData);
            out.flush();
        } else {
            sendError(out, 404, "Not Found");
        }
    }

    private String getContentType(String path) {
        if (path.endsWith(".html"))
            return "text/html";
        if (path.endsWith(".css"))
            return "text/css";
        if (path.endsWith(".js"))
            return "application/javascript";
        if (path.endsWith(".png"))
            return "image/png";
        if (path.endsWith(".jpg") || path.endsWith(".jpeg"))
            return "image/jpeg";
        return "text/plain";
    }

    private void sendError(OutputStream out, int statusCode, String statusMessage) throws IOException {
        String response = "<html><body><h1>" + statusCode + " " + statusMessage + "</h1></body></html>";
        PrintWriter writer = new PrintWriter(out, true);
        writer.println("HTTP/1.1 " + statusCode + " " + statusMessage);
        writer.println("Content-Type: text/html");
        writer.println("Content-Length: " + response.length());
        writer.println();
        writer.print(response);
        writer.flush();
    }
}
