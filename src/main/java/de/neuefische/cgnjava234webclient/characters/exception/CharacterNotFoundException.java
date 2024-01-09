package de.neuefische.cgnjava234webclient.characters.exception;

import java.util.NoSuchElementException;

public class CharacterNotFoundException extends NoSuchElementException {

    public CharacterNotFoundException(String message) {
        super(message);
    }
}
