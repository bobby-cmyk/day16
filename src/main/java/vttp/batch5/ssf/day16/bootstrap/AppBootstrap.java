package vttp.batch5.ssf.day16.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import vttp.batch5.ssf.day16.services.HttpbinService;

import java.io.Reader;
import java.io.StringReader;


// Will run first before any thing runs
@Component
public class AppBootstrap implements CommandLineRunner{
    
    @Autowired
    private HttpbinService httpbinSvc;


    @Override
    public void run(String... args) {

        httpbinSvc.getWithQueryParams();

        httpbinSvc.getJokes();

        httpbinSvc.postForm();

        httpbinSvc.postJson();

        /*

        
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();

        // Add creates a new object everytime=
        objBuilder = objBuilder
            .add("name", "fred")
            .add("email", "fred@gmail.com")
            .add("age", 50)
            .add("married", true);


        JsonObjectBuilder addrBuilder = Json.createObjectBuilder();

        addrBuilder.add("street", "1 bedrock ave")
                    .add("postcode", "abcd1234");


        JsonObject addr = addrBuilder.build();

    
         
            JsonObject addr = Json.createObjectBuilder()    
                .add("street", "1 bedrock ave")
                .add("postcode", "abcd1234")
                .build();       
    

        objBuilder = objBuilder.add("address", addr);

        JsonArray hobbies = Json.createArrayBuilder()
            .add("skiing")
            .add("jogging")
            .add("travelling")
            .build();

        objBuilder = objBuilder.add("hobbies", hobbies);

        JsonObject fred = objBuilder.build();

        System.out.printf("Fred as JSON: \n%s\n", fred.toString());

        System.out.printf("Name: %s\n", fred.getString("name"));

        System.out.printf("Age: %d\n", fred.getInt("age"));

        JsonObject tempObject = fred.getJsonObject("address");

        System.out.printf("Street: %s\n", tempObject.getString("street"));

        JsonArray tempArray = fred.getJsonArray("hobbies");

        System.out.printf("Hobbies[1]: %s\n", tempArray.getString(0));

        String data = """
    {
        "name": "barney",
        "email": "barney@gmai.com",
        "age": 50,
        "married": true,
        "address": {
            "street": "1 bedrock ave",
            "postcode": "abc123"
        },
        "hobbies": ["skiing", "reading", "jogging"]
    }
    """;

        Reader reader = new StringReader(data);

        JsonReader jsonReader = Json.createReader(reader);

        JsonObject barney = jsonReader.readObject();

        System.out.printf(">>> barney\n\t:%s\n", barney.toString());

        String arrData = "[123, 245, 789]";
        reader = new StringReader(arrData);
        jsonReader = Json.createReader(reader);
        JsonArray numberArray = jsonReader.readArray();

        System.out.printf(">>> Number Array\n\t:%s\n", numberArray.toString());
        */

    }

}
