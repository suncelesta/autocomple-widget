package com.autocomple;

import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.extracting.ConfigExtractor;
import com.autocomple.extracting.ConfigMultiExtractor;
import com.autocomple.extracting.fromjs.*;
import com.autocomple.embedding.ForEmbedding;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;

import java.util.ArrayList;
import java.util.List;

import static com.autocomple.config.ConfigUtils.getAutocompleConfigJsos;

class ForEmbeddingsFromJsExtractor extends ForEmbeddingsExtractor {

    private final static ConfigExtractor<Jso, AutocompleConfig> CONFIG_EXTRACTOR = createExtractor();

    @Override
    public List<ForEmbedding<AutocompleConfig>> extract() {
        List<ForEmbedding<AutocompleConfig>> result = new ArrayList<>();
        JsArray<Jso> jsos = getAutocompleConfigJsos();

        for (int i = 0; i < jsos.length(); i++) {
            ForEmbedding<AutocompleConfig> forEmbedding = extract(jsos.get(i));
            result.add(forEmbedding);
        }

        return result;
    }

    private ForEmbedding<AutocompleConfig> extract(Jso jso) {
        Element target = extractTargetElement(jso);
        AutocompleConfig config = extractAutocompleConfig(jso);

        return new ForEmbedding<>(target, config);
    }

    private Element extractTargetElement(Jso jso) {
        String elementId = jso.getString("elementId");
        return DOM.getElementById(elementId);
    }

    private AutocompleConfig extractAutocompleConfig(Jso jso) {
        AutocompleConfig config = new AutocompleConfig();

        return CONFIG_EXTRACTOR.extract(jso, config);
    }

    public static ConfigExtractor<Jso, AutocompleConfig> createExtractor() {
        ConfigMultiExtractor<Jso> extractor = new ConfigMultiExtractor<>();

        extractor.addExtractor(new SizeFromJsExtractor());
        extractor.addExtractor(new ThemeFromJsExtractor());
        extractor.addExtractor(new SuggestionsLimitFromJsExtractor());
        extractor.addExtractor(new PlaceholderFromJsExtractor());
        extractor.addExtractor(new HintEnabledFromJsExtractor());
        extractor.addExtractor(new RemoteUrlFromJsExtractor());
        extractor.addExtractor(new RemoteWildcardFromJsExtractor());
        extractor.addExtractor(new RemoteIsJsonpFromJsExtractor());
        extractor.addExtractor(new RemoteHeadersFromJsExtractor());

        return extractor;
    }
}
