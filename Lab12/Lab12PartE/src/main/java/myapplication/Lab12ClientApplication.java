package myapplication;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Lab12ClientApplication {

    private static final String BASE_URL = "http://localhost:8080";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public Lab12ClientApplication() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) {
        Lab12ClientApplication client = new Lab12ClientApplication();
        System.out.println("=== Lab12 Part C JWT Authentication Client ===");
        System.out.println("Make sure Lab12PartC server is running on http://localhost:8080\n");

        client.testJwtAuthentication();
    }

    public void testJwtAuthentication() {
        System.out.println("=== Testing Public Endpoints ===");
        testGetEndpoint("/api/all", null);

        System.out.println("\n=== User Registration ===");
        registerUser("bob", "user","john@example.com", "password123");
        registerUser("jane", "admin","jane@example.com", "password123");
        registerUser("alice", "user","alice@example.com", "password123");
        registerUser("bobbie", "admin","bobbie@example.com", "password123");

        System.out.println("\n=== User Authentication ===");
        String userToken = authenticateUser("john@example.com", "password123");
        String adminToken = authenticateUser("admin@admin.com", "password");
        String modToken = authenticateUser("alice@example.com", "password123");
        String multiRoleToken = authenticateUser("bobbie@example.com", "password123");

        System.out.println("\n--- Testing /api/user (USER role required) ---");
        testGetEndpoint("/api/users", userToken);       // Should work
        testGetEndpoint("/api/users", adminToken);      // Should work (admin has access)
        testGetEndpoint("/api/users", modToken);        // Should work (mod has access)
        testGetEndpoint("/api/users", multiRoleToken);  // Should work

        System.out.println("\n--- Testing /api/admin (ADMIN role required) ---");
        testGetEndpoint("/api/admins", userToken);      // Should fail
        testGetEndpoint("/api/admins", adminToken);     // Should work
        testGetEndpoint("/api/admins", modToken);       // Should fail
        testGetEndpoint("/api/admins", multiRoleToken); // Should fail


    }

    private void registerUser(String firstName,String lastName, String email, String password) {
        try {
            Map<String, Object> registrationData = new HashMap<>();
            registrationData.put("firstName", firstName);
            registrationData.put("lastName", lastName);
            registrationData.put("email", email);
            registrationData.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(registrationData, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    BASE_URL + "/auth/signup",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            System.out.printf("✅ REGISTER [%s] with roles %s | Status: %s%n",
                    firstName, lastName, response.getStatusCode());

        } catch (HttpClientErrorException e) {
            System.out.printf("⚠️ REGISTER [%s] | Status: %s | Error: %s%n",
                    firstName, e.getStatusCode(),
                    e.getResponseBodyAsString().contains("already taken") ? "User already exists" : "Registration failed");
        } catch (Exception e) {
            System.out.printf("💥 REGISTER [%s] | Error: %s%n", firstName, e.getMessage());
        }
    }

    private String authenticateUser(String email, String password) {
        try {
            Map<String, String> loginData = new HashMap<>();
            loginData.put("email", email);
            loginData.put("password", password);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> request = new HttpEntity<>(loginData, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    BASE_URL + "/auth/signin",
                    HttpMethod.POST,
                    request,
                    String.class
            );

            // Extract JWT token from response
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            String token = jsonNode.has("accessToken") ?
                    jsonNode.get("accessToken").asText() :
                    jsonNode.get("token").asText();

            String roles = jsonNode.has("roles") ? jsonNode.get("roles").toString() : "N/A";

            System.out.printf("✅ LOGIN [%s] | Status: %s | Roles: %s | Token: %s...%n",
                    email, response.getStatusCode(), roles,
                    token.substring(0, Math.min(30, token.length())));

            return token;

        } catch (HttpClientErrorException e) {
            System.out.printf("❌ LOGIN [%s] | Status: %s | Error: %s%n",
                    email, e.getStatusCode(),
                    e.getStatusCode().value() == 401 ? "Invalid credentials" : "Authentication failed");
        } catch (Exception e) {
            System.out.printf("💥 LOGIN [%s] | Error: %s%n", email, e.getMessage());
        }
        return null;
    }

    private void testGetEndpoint(String endpoint, String jwtToken) {
        try {
            HttpHeaders headers = new HttpHeaders();

            // Add JWT Bearer token if provided
            if (jwtToken != null) {
                headers.set("Authorization", "Bearer " + jwtToken);
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
                    (jwtToken != null ? "[With JWT]" : "[No Auth]"),
                    response.getStatusCode(),
                    truncateResponse(response.getBody())
            );

        } catch (HttpClientErrorException e) {
            String errorType = switch (e.getStatusCode().value()) {
                case 401 -> "Unauthorized - Invalid or missing JWT token";
                case 403 -> "Forbidden - Valid token but insufficient privileges";
                case 404 -> "Not Found - Endpoint does not exist";
                default -> "HTTP " + e.getStatusCode().value();
            };

            System.out.printf("❌ GET %s %s | Status: %s | Error: %s%n",
                    endpoint,
                    (jwtToken != null ? "[With JWT]" : "[No Auth]"),
                    e.getStatusCode(),
                    errorType
            );
        } catch (Exception e) {
            System.out.printf("💥 GET %s %s | Connection Error: %s%n",
                    endpoint,
                    (jwtToken != null ? "[With JWT]" : "[No Auth]"),
                    e.getMessage()
            );
        }
    }

    private String truncateResponse(String response) {
        if (response == null) return "null";
        return response.length() > 100 ? response.substring(0, 100) + "..." : response;
    }
}