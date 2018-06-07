package ru.priamosudov.restapi.dictionary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.priamosudov.restapi.dictionary.model.DictionaryModel;

import java.util.List;

@Repository
public interface MongoDictionaryRepository extends MongoRepository<DictionaryModel, String> {
    List<DictionaryModel> findByLang(String lang);
    List<DictionaryModel> findByTextList(String textList);
    List<DictionaryModel> findByOriginTextLikeOrderByOriginText(String pattern);
}
