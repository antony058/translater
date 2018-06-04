package ru.priamosudov.restapi.dictionary.service;

import ru.priamosudov.restapi.dictionary.view.DictionaryView;

public interface DictionaryService {
    DictionaryView translate(String originWord);
}
