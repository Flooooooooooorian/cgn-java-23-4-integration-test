package de.neuefische.cgnjava234webclient;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.models.api.RickAndMortyOrigin;
import de.neuefische.cgnjava234webclient.characters.repositorys.CharacterRepository;
import de.neuefische.cgnjava234webclient.characters.services.CharacterService;
import de.neuefische.cgnjava234webclient.characters.services.api.RickAndMortyAPIService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CharacterServiceTest {

    private CharacterRepository characterRepository = mock(CharacterRepository.class);
    private RickAndMortyAPIService rickAndMortyAPIService = mock(RickAndMortyAPIService.class);
    private CharacterService characterService = new CharacterService(characterRepository, rickAndMortyAPIService);

    @Test
    void getAllCharacters() {
        //GIVEN
        Character c1 = new Character("123", "Test", "alive", new RickAndMortyOrigin("Earth", "url"), List.of("episode1"));
        Character c2 = new Character("123", "Test", "alive", new RickAndMortyOrigin("Earth", "url"), List.of("episode1"));
        List<Character> characters1 = List.of(c1);
        List<Character> characters2 = List.of(c2);

        when(characterRepository.findAll()).thenReturn(characters1);
        when(rickAndMortyAPIService.loadAllCharacters()).thenReturn(characters2);

        //WHEN
        List<Character> actual = characterService.getAllCharacters();

        //THEN
        List<Character> expected = new ArrayList<>();
        expected.addAll(characters1);
        expected.addAll(characters2);

        assertEquals(actual, expected);
    }
}
