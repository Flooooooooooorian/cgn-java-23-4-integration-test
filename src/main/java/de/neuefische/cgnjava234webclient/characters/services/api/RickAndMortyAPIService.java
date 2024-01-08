package de.neuefische.cgnjava234webclient.characters.services.api;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.models.api.RickAndMortyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RickAndMortyAPIService {

    private RestClient restClient = RestClient.builder()
            .baseUrl("https://rickandmortyapi.com/api")
            .build();


    public List<Character> loadAllCharacters() {
        Character[] responseBody = restClient.get()
                .uri("/character")
                .retrieve()
                .body(Character[].class);

         return Arrays.asList(responseBody);
    }
}
