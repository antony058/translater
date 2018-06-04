package ru.priamosudov.restapi.translater.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;
import ru.priamosudov.restapi.translater.service.TranslatorService;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    @Value(value = "${translater.yandex.base-url-with-token}")
    private String urlWithToken;

    private UriComponents uriComponents;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public DictionaryView sendRequest(String originText) {
        uriComponents = uriComponents.expand("text", originText);

        return restTemplate
                .getForObject(uriComponents.encode().toUri(), DictionaryView.class);
    }

    @PostConstruct
    public void init() {
        uriComponents = UriComponentsBuilder.fromUriString(urlWithToken)
                .build();
    }
}
