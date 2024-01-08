package de.neuefische.cgnjava234webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character addCharacter(Character character) {

        return characterRepository.save(character);
    }
}
