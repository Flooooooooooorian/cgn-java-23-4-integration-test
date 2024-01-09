package de.neuefische.cgnjava234webclient.characters.controllers;

import de.neuefische.cgnjava234webclient.characters.exception.ErrorMessage;
import de.neuefische.cgnjava234webclient.characters.models.Character;
import de.neuefische.cgnjava234webclient.characters.services.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    public Character getCharacterById(@PathVariable String id) {
        return characterService.getCharacterById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Character postCharacter(@RequestBody Character character) {
        return characterService.addCharacter(character);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(RuntimeException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}
