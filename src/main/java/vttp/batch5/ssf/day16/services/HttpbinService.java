package vttp.batch5.ssf.day16.services;

import java.util.UUID;

import org.apache.catalina.connector.Request;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;



@Service
public class HttpbinService {
    
    public static final String GET_URL = "https://httpbin.org/get";
    public static final String JOKES_URL = "https://official-joke-api.appspot.com/jokes/ten";
    private static final String POST_URL = "https://httpbin.org/post";


    public void postJson() {

        JsonObject json = Json.createObjectBuilder()
            .add("name", "HEHe").build();

        // Create a req
        RequestEntity<JsonObject> req = RequestEntity
            .post(POST_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(json, JsonObject.class);

        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> resp = template.exchange(req, String.class);
            String payload = resp.getBody();
            System.out.printf(">>>%s\n", payload);
        }
        catch (Exception e) {

        }
    }

    public void postForm() {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("name", "apple");
        form.add("quantity", "%d".formatted(3));

        // Create a req
        RequestEntity<MultiValueMap<String, String>> req = RequestEntity
            .post(POST_URL)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .accept(MediaType.APPLICATION_JSON)
            .body(form, MultiValueMap.class);

        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> resp = template.exchange(req, String.class);
            String payload = resp.getBody();
            System.out.printf(">>>%s\n", payload);
        }
        catch (Exception e) {

        }
    }
    public void getJokes() {

        // Configure the request
        // GET jokes/ten
        // Accept: application/json
        
        RequestEntity<Void> req = RequestEntity.get(JOKES_URL).accept(MediaType.APPLICATION_JSON).build();

        // Create REST template
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp;

        try {

            // Make the ca'''
            resp = template.exchange(req, String.class);
            String payload = resp.getBody();
            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonArray result = reader.readArray();

            for (int i =0; i < result.size(); i++) {
                JsonObject joke = result.getJsonObject(i);
                System.out.printf("SETUP: %s\nPUNCHLINE: %s\n\n", joke.getString("setup"), joke.getString("punchline"));
            }

        } catch (Exception e) {

        }
    }

    public void getWithQueryParams() {

        String url = UriComponentsBuilder
            .fromUriString(GET_URL)
            .queryParam("name", "fred")
            .queryParam("email", "fred@gmail.com")
            .toUriString();

        System.out.printf("URL with query params: %s", url);

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
    }


    public JsonObject get() {

        // Request 
        // GET /get
        // Accept: application/json
        // X-Myheader: abc123

        RequestEntity<Void> req = RequestEntity
            .get(GET_URL)
            .accept(MediaType.APPLICATION_JSON)
            .header("X-Myheader", UUID.randomUUID().toString())
            .build();

        // Create an instance of RestTemplate
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        // 200, 300 - OK
        // 400, 500 - exception
        try {   
            resp = template.exchange(req, String.class);
            String payload = resp.getBody();

            JsonReader reader = Json.createReader(new StringReader(payload));
            JsonObject result = reader.readObject();

            JsonObject headers = result.getJsonObject("headers");

            System.out.printf("headers: %s\n", headers.toString());



            System.out.printf(">>> Payload: %s", payload);

        } catch (Exception ex) {
            System.out.printf(">>> error: %s\n", ex.getMessage());
        }
        

        return null;
    }
}
