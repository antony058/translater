package ru.priamosudov.restapi.translater.service;

import ru.priamosudov.restapi.dictionary.view.DictionaryView;

public interface TranslatorService {
    DictionaryView sendRequest(String originText);
}
