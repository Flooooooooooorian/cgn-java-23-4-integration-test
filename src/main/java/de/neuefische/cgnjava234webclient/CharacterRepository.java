package de.neuefische.cgnjava234webclient;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<Character, String> {
}
