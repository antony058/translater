package ru.priamosudov.restapi.translater.service.impl;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;
import ru.priamosudov.restapi.translater.service.TranslatorService;

import java.net.URI;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    @Value(value = "${translater.yandex.base-url-with-token}")
    private String urlWithToken;

    private RestTemplate restTemplate;

    @Autowired
    public TranslatorServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public DictionaryView sendRequest(String originText) {
        URI uri = UriComponentsBuilder.fromUriString(urlWithToken)
                .build(originText);

        return restTemplate
                .getForObject(uri, DictionaryView.class);
    }
}
