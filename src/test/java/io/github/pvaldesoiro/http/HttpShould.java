package io.github.pvaldesoiro.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HttpShould {
    @Test void
    retrieve_data_through_a_get_http_request_and_retrieve_the_response()
            throws IOException, InterruptedException {

        HttpResponse response = Http.get("https://jsonplaceholder.typicode.com/posts/1");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("{\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "  \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "}"
        );
    }

    @Test void
    send_data_through_a_post_http_request_and_retrieve_the_response()
            throws IOException, InterruptedException {

        HttpResponse response = Http.post(
                "https://jsonplaceholder.typicode.com/posts",
                "{\"title\":\"foo\",\"body\":\"bar\",\"userId\": 1}",
                Map.of("Content-type", "application/json; charset=UTF-8"));

        assertThat(response.statusCode()).isEqualTo(201);
        assertThat(response.body()).isEqualTo("{\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1,\n" +
                "  \"id\": 101\n" +
                "}");
    }

    @Test void
    send_data_through_a_put_http_request_and_retrieve_the_response() throws IOException, InterruptedException {
        HttpResponse response = Http.put(
                "https://jsonplaceholder.typicode.com/posts/1",
                "{\"id\":1,\"title\":\"foo\",\"body\":\"bar\",\"userId\": 1}",
                Map.of("Content-type", "application/json; charset=UTF-8"));

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body()).isEqualTo("{\n" +
                "  \"id\": 1,\n" +
                "  \"title\": \"foo\",\n" +
                "  \"body\": \"bar\",\n" +
                "  \"userId\": 1\n" +
                "}");
    }

    @Test void
    send_a_delete_http_request_and_retrieve_the_response() throws IOException, InterruptedException {
        HttpResponse response = Http.delete("https://jsonplaceholder.typicode.com/posts/1");

        assertThat(response.statusCode()).isEqualTo(200);
    }
}
