package ru.priamosudov.restapi.dictionary.utils;

import ru.priamosudov.restapi.dictionary.model.DictionaryModel;
import ru.priamosudov.restapi.dictionary.view.DictionaryView;

public class DictionaryMapper {
    public static DictionaryModel toModel(DictionaryView view, String originText) {
        DictionaryModel model = new DictionaryModel();
        model.setTextList(view.getTextList());
        model.setLang(view.getLang());
        model.setOriginText(originText);

        return model;
    }

    public static DictionaryView toView(DictionaryModel model) {
        DictionaryView view = new DictionaryView();
        view.setLang(model.getLang());
        view.setTextList(model.getTextList());

        return view;
    }
}
