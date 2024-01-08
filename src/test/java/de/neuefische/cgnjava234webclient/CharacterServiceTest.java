package de.neuefische.cgnjava234webclient;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.repositorys.CharacterRepository;
import de.neuefische.cgnjava234webclient.characters.services.CharacterService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CharacterServiceTest {

    private CharacterRepository characterRepository = mock(CharacterRepository.class);
    private CharacterService characterService = new CharacterService(characterRepository, null);

    @Test
    void getAllCharacters() {
        //GIVEN
        Character c1 = new Character("123", "Test");
        List<Character> characters = List.of(c1);

        when(characterRepository.findAll()).thenReturn(characters);

        //WHEN
        List<Character> actual = characterService.getAllCharacters();

        //THEN
        List<Character> expected = List.of(c1);
        assertEquals(actual, expected);
    }
}
