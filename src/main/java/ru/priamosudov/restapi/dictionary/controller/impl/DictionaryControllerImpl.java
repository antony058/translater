package ru.priamosudov.restapi.dictionary.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import ru.priamosudov.restapi.commons.ErrorView;
import ru.priamosudov.restapi.dictionary.controller.DictionaryController;
import ru.priamosudov.restapi.dictionary.service.DictionaryService;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;

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

    @Override
    @GetMapping(value = "/callable")
    public Callable<ResponseEntity<DictionaryView>> callable() {
        return () -> {
            Thread.sleep(5000L);
            return new ResponseEntity<>(new DictionaryView(), HttpStatus.OK);
        };
    }

    @GetMapping(value = "/emmit")
    public ResponseBodyEmitter emitter() throws IOException, InterruptedException {
        DictionaryView view = new DictionaryView();
        view.setTextList(Arrays.asList("hello"));

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitter.send(view);

        DictionaryView view1 = new DictionaryView();
        view1.setTextList(Arrays.asList("goodbye"));
        emitter.send(view1);

        emitter.complete();

        return emitter;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorView> rootExceptionHandler(Exception ex) {
        ErrorView view = new ErrorView();
        view.setError(ex.getMessage());

        return ResponseEntity.badRequest().body(view);
    }
}
