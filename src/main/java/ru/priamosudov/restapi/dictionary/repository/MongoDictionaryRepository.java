package ru.priamosudov.restapi.dictionary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.priamosudov.restapi.dictionary.model.DictionaryModel;

import java.util.List;

public interface MongoDictionaryRepository extends MongoRepository<DictionaryModel, String> {
    List<DictionaryModel> findByLang(String lang);
}
