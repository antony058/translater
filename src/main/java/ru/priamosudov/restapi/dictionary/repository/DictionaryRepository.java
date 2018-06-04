package ru.priamosudov.restapi.dictionary.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.priamosudov.restapi.dictionary.model.DictionaryModel;

@Repository
public interface DictionaryRepository extends CrudRepository<DictionaryModel, String> {
}
