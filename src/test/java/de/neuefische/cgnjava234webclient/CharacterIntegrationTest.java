package de.neuefische.cgnjava234webclient;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.repositorys.CharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class CharacterIntegrationTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CharacterRepository characterRepository;

    @Test
    public void getAllCharacters() throws Exception {
        //GIVEN
        characterRepository.save(new Character("123", "Test"));

        //WHEN
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/characters"))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [{
                        "id": "123", 
                        "name": "Test"
                        }]
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
