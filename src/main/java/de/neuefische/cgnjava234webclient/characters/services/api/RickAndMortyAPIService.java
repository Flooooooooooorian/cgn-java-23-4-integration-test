package de.neuefische.cgnjava234webclient.characters.services.api;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.models.api.RickAndMortyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class RickAndMortyAPIService {

    private final RestClient restClient;

    public RickAndMortyAPIService(@Value("${app.rickandmorty.api.url}") String url) {
        restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    public List<Character> loadAllCharacters() {
        RickAndMortyResponse responseBody = restClient.get()
                .uri("/character")
                .retrieve()
                .body(RickAndMortyResponse.class);

        return responseBody.results();
    }

    public Character loadCharacterById(String id) {
        return restClient.get()
                .uri("/character/" + id)
                .retrieve()
                .body(Character.class);
    }
}
