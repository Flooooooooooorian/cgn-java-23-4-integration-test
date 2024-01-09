package de.neuefische.cgnjava234webclient.characters.services;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.repositorys.CharacterRepository;
import de.neuefische.cgnjava234webclient.characters.services.api.RickAndMortyAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final RickAndMortyAPIService rickAndMortyAPIService;

    public List<Character> getAllCharacters() {
        List<Character> rickMortyCharacters = rickAndMortyAPIService.loadAllCharacters();
        List<Character> localCharacters = characterRepository.findAll();

        List<Character> allCharacters = new ArrayList<>();
        allCharacters.addAll(rickMortyCharacters);
        allCharacters.addAll(localCharacters);
        return allCharacters;
    }

    public Character addCharacter(Character character) {

        return characterRepository.save(character);
    }

    public Character getCharacterById(String id) {
        return rickAndMortyAPIService.loadCharacterById(id);
    }
}
