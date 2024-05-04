package com.game.wordguess.FileConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class WordAPI {

    private final RestTemplate template = new RestTemplate();

    public Map<String, String> GettingDefinition(String object) throws JsonProcessingException {

        Map<String, String> stringMap = new HashMap<>();

        JsonNode definitions;

        //Getting a JSONResponse from api
        String JSONResponse = template.getForObject("https://api.dictionaryapi.dev/api/v2/entries/en/"+object, String.class);

        //Creating a ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        //Mapping the JSON respone as nodes
        JsonNode node = mapper.readTree(JSONResponse);

        //Extracting the word string
        String word = node.path(0).path("word").asText();

        //Extracting the meanings Array with only the first element of its array
        JsonNode meaningArray = node.path(0).get("meanings").path(0);

        //Extracting the definitions Array with only the first element of its array
         definitions = meaningArray.get("definitions").path(0);

         //Extracting the definition of first element
         String definition = definitions.path("definition").asText();


        //Assigning the definition to the map
        stringMap.put(word,definition);


        return stringMap;
    }


}
