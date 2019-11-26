package http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Http {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static HttpResponse<String> get(String url) throws IOException, InterruptedException {
        return get(url, Map.of());
    }

    public static HttpResponse<String> get(String url, Map<String, String> headers) throws IOException, InterruptedException {
        return execute(requestFrom(Method.GET, url, headers, null));
    }

    public static HttpResponse<String> post(String url, String body) throws IOException, InterruptedException {
        return post(url, body, Map.of());
    }

    public static HttpResponse<String> post(String url, String body, Map<String, String> headers) throws IOException, InterruptedException {

        return execute(requestFrom(Method.POST, url, headers, body));
    }

    public static HttpResponse put(String url, String body) throws IOException, InterruptedException {
        return put(url, body, Map.of());
    }

    public static HttpResponse put(String url, String body, Map<String, String> headers) throws IOException, InterruptedException {
        return execute(requestFrom(Method.PUT, url, headers, body));
    }

    public static HttpResponse<String> delete(String url) throws IOException, InterruptedException {
        return execute(requestFrom(Method.DELETE, url, Map.of(), null));
    }

    private static HttpResponse<String> execute(HttpRequest request) throws IOException, InterruptedException {
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private static HttpRequest requestFrom(Method method, String url, Map<String, String> headers, String body) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder(URI.create(url));

        headers.forEach(requestBuilder::header);

        switch (method) {
            case GET -> requestBuilder.GET();
            case POST -> requestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
            case PUT -> requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(body));
            case DELETE -> requestBuilder.DELETE();
        }

        return requestBuilder.build();
    }

    private enum Method {GET, POST, PUT, DELETE}
}
