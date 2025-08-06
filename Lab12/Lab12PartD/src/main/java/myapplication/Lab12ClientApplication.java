package myapplication;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Base64;

public class Lab12ClientApplication {

    private static final String BASE_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;

    public Lab12ClientApplication() {
        this.restTemplate = new RestTemplate();
    }

    public static void main(String[] args) {
        Lab12ClientApplication client = new Lab12ClientApplication();
        client.testAllEndpoints();
    }

    public void testAllEndpoints() {
        testEndpoint("/shopping", null, null);
        testEndpoint("/shopping", "bob", "123");
        testEndpoint("/shopping", "mary", "123");

        testEndpoint("/orders", "bob", "123");
        testEndpoint("/orders", "mary", "123");

        testEndpoint("/payments", "bob", "123");
        testEndpoint("/payments", "mary", "123");
    }

    private void testEndpoint(String endpoint, String username, String password) {
        try {
            HttpHeaders headers = new HttpHeaders();

            // Add Basic Auth header if credentials provided
            if (username != null && password != null) {
                String auth = username + ":" + password;
                String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
                headers.set("Authorization", "Basic " + encodedAuth);
            }

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    BASE_URL + endpoint,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            System.out.printf("✅ GET %s %s | Status: %s | Response: %s%n",
                    endpoint,
                    (username != null ? "[" + username + "]" : "[no auth]"),
                    response.getStatusCode(),
                    response.getBody()
            );

        } catch (HttpClientErrorException e) {
            System.out.printf("❌ GET %s %s | Status: %s | Error: %s%n",
                    endpoint,
                    (username != null ? "[" + username + "]" : "[no auth]"),
                    e.getStatusCode(),
                    e.getResponseBodyAsString()
            );
        } catch (Exception e) {
            System.out.printf("💥 GET %s %s | Error: %s%n",
                    endpoint,
                    (username != null ? "[" + username + "]" : "[no auth]"),
                    e.getMessage()
            );
        }
    }
}