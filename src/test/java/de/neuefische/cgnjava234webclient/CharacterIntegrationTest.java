package de.neuefische.cgnjava234webclient;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.models.api.RickAndMortyOrigin;
import de.neuefische.cgnjava234webclient.characters.repositorys.CharacterRepository;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class CharacterIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CharacterRepository characterRepository;

    private static MockWebServer mockWebServer;

    @BeforeAll
    public static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @DynamicPropertySource
    public static void configureUrl(DynamicPropertyRegistry registry) {
        registry.add("app.rickandmorty.api.url", () -> mockWebServer.url("/").toString());
    }

    @AfterAll
    public static void cleanup() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void getAllCharacters() throws Exception {
        //GIVEN
        characterRepository.save(new Character("123", "Test", "alive", new RickAndMortyOrigin("Earth", "url"), List.of("episode1")));

        mockWebServer.enqueue(new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .setBody("""
                        {
                        "info": {
                               "count": 3,
                               "pages": 1
                           },
                        "results": [
                                 {
                                    "id": 1,
                                    "name": "TEst",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "Earth (C-137)",
                                        "url": "https://rickandmortyapi.com/api/location/1"
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    },
                                    "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                                    "episode": [
                                        "https://rickandmortyapi.com/api/episode/1",
                                        "https://rickandmortyapi.com/api/episode/2",
                                        "https://rickandmortyapi.com/api/episode/3"          
                                    ],
                                    "url": "https://rickandmortyapi.com/api/character/1",
                                    "created": "2017-11-04T18:48:46.250Z"
                                }
                            ]
                        }
                        """));

        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                        "id": "123",
                        "name": "Test"
                        },
                        {"id":"1","name":"TEst","status":"Alive","origin":{"name":"Earth (C-137)","url":"https://rickandmortyapi.com/api/location/1"},"episode":["https://rickandmortyapi.com/api/episode/1","https://rickandmortyapi.com/api/episode/2","https://rickandmortyapi.com/api/episode/3"]}]
                        """))
                .andReturn();


        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }

    @Test
    @DirtiesContext
    public void postCharacter() throws Exception {
        //GIVEN

        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/characters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "name": "Florian"
                                }
                                """))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "name": "Florian"
                        }
                        """))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();


        assertEquals(mvcResult.getResponse().getStatus(), 200);
    }
}
