package ru.priamosudov.restapi.dictionary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;

public interface DictionaryController {
    ResponseEntity<DictionaryView> translate(@PathVariable String word);
}
