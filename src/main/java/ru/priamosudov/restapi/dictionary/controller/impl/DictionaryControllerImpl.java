package ru.priamosudov.restapi.dictionary.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.priamosudov.restapi.commons.ErrorView;
import ru.priamosudov.restapi.dictionary.controller.DictionaryController;
import ru.priamosudov.restapi.dictionary.service.DictionaryService;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;

@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class DictionaryControllerImpl implements DictionaryController {

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryControllerImpl(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    @PostMapping(value = "/dictionary")
    public ResponseEntity<DictionaryView> translate(@RequestParam String word) {
        return new ResponseEntity<>(dictionaryService.translate(word), HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorView> rootExceptionHandler(Exception ex) {
        ErrorView view = new ErrorView();
        view.setError(ex.getMessage());

        return ResponseEntity.badRequest().body(view);
    }
}
