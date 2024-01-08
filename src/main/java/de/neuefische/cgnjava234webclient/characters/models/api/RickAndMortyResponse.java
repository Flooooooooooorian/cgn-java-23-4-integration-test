package de.neuefische.cgnjava234webclient.characters.models.api;

import de.neuefische.cgnjava234webclient.characters.models.Character;

import java.util.List;

public record RickAndMortyResponse(
        RickAndMortyInfo info,
        List<Character> results
) {
}
