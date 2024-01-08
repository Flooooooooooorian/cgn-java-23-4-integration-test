package de.neuefische.cgnjava234webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;


    @GetMapping
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @PostMapping
    public Character postCharacter(@RequestBody Character character) {
        return characterService.addCharacter(character);
    }
}
