package de.neuefische.cgnjava234webclient.characters.repositorys;

import de.neuefische.cgnjava234webclient.characters.models.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
}
