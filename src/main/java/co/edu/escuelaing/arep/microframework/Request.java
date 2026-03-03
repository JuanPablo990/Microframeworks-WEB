package co.edu.escuelaing.arep.microframework;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an HTTP Request.
 */
public class Request {
    private String path;
    private String queryString;
    private Map<String, String> queryParams;

    public Request(String path, String queryString) {
        this.path = path;
        this.queryString = queryString;
        this.queryParams = parseQuery(queryString);
    }

    /**
     * Parses a url-encoded query string into a map of key-value pairs.
     */
    private Map<String, String> parseQuery(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isEmpty()) {
            return map;
        }
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length > 1) {
                map.put(kv[0], kv[1]);
            } else {
                map.put(kv[0], "");
            }
        }
        return map;
    }

    /**
     * Extracts query parameters from the incoming request.
     * 
     * @param key The query parameter name.
     * @return The value of the query parameter, or null if not present.
     */
    public String getValues(String key) {
        return queryParams.get(key);
    }

    public String getPath() {
        return path;
    }

    public String getQueryString() {
        return queryString;
    }
}
