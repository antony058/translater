package ru.priamosudov.restapi.dictionary.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;

import java.util.concurrent.Callable;

public interface DictionaryController {
    ResponseEntity<DictionaryView> translate(@PathVariable String word);
    Callable<ResponseEntity<DictionaryView>> callable();
}
