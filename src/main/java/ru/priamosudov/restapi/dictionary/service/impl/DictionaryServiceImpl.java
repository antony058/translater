package ru.priamosudov.restapi.dictionary.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.priamosudov.restapi.dictionary.model.DictionaryModel;
import ru.priamosudov.restapi.dictionary.repository.DictionaryRepository;
import ru.priamosudov.restapi.dictionary.repository.MongoDictionaryRepository;
import ru.priamosudov.restapi.dictionary.service.DictionaryService;
import ru.priamosudov.restapi.dictionary.utils.DictionaryMapper;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;
import ru.priamosudov.restapi.translater.service.TranslatorService;
import ru.priamosudov.restapi.user.repository.UserRepository;

import java.util.Optional;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    private final TranslatorService translatorService;
    private final DictionaryRepository dictionaryRepository;
    private final MongoDictionaryRepository mongoRepository;
    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DictionaryServiceImpl(TranslatorService translatorService, DictionaryRepository dictionaryRepository,
                                 MongoDictionaryRepository mongoRepository, MongoTemplate mongoTemplate,
                                 UserRepository userRepository) {
        this.translatorService = translatorService;
        this.dictionaryRepository = dictionaryRepository;
        this.mongoRepository = mongoRepository;
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DictionaryView translate(String originWord) {
        DictionaryView view;
        Optional<DictionaryModel> model = dictionaryRepository.findById(originWord);

        if (!model.isPresent()) {
            view = translatorService.sendRequest(originWord);

            dictionaryRepository.save(DictionaryMapper.toModel(view, originWord));
        } else {
            view = DictionaryMapper.toView(model.get());
            mongoRepository.save(model.get());
        }

        return view;
    }
}
