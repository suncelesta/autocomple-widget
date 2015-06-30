package com.autocomple;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.*;
import com.autocomple.extracting.fromattribute.*;
import com.autocomple.embedding.ForEmbedding;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NodeList;

import java.util.ArrayList;
import java.util.List;

import static com.autocomple.common.Utils.getInputElements;

class ForEmbeddingsFromAttributesExtractor extends ForEmbeddingsExtractor {

    private final static ConfigExtractor<InputElement, AutocompleConfig> CONFIG_EXTRACTOR = createExtractor();

    @Override
    public List<ForEmbedding<AutocompleConfig>> extract() {
        List<ForEmbedding<AutocompleConfig>> result = new ArrayList<>();
        NodeList<Element> inputElements = getInputElements();

        for (int i = 0; i < inputElements.getLength(); i++) {
            InputElement inputElement = inputElements.getItem(i).cast();
            if (inputElement.hasAttribute(Tokens.AUTOCOMPLE_ATTR)) {
                ForEmbedding<AutocompleConfig> forEmbedding = extract(inputElement);
                result.add(forEmbedding);
            }
        }

        return result;
    }

    private ForEmbedding<AutocompleConfig> extract(InputElement inputElement) {
        AutocompleConfig config = extractAutocompleConfig(inputElement);

        return new ForEmbedding<>(inputElement, config);
    }

    private AutocompleConfig extractAutocompleConfig(InputElement inputElement) {
        AutocompleConfig config = new AutocompleConfig();

        return CONFIG_EXTRACTOR.extract(inputElement, config);
    }

    public static ConfigExtractor<InputElement, AutocompleConfig> createExtractor() {
        ConfigMultiExtractor<InputElement> extractor = new ConfigMultiExtractor<>();

        extractor.addExtractor(new SizeFromAttributeExtractor());
        extractor.addExtractor(new ThemeFromAttributeExtractor());
        extractor.addExtractor(new SuggestionsLimitFromAttributeExtractor());
        extractor.addExtractor(new PlaceholderFromAttributeExtractor());
        extractor.addExtractor(new HintEnabledFromAttributeExtractor());
        extractor.addExtractor(new RemoteUrlFromAttributeExtractor());
        extractor.addExtractor(new RemoteWildcardFromAttributeExtractor());
        extractor.addExtractor(new RemoteIsJsonpFromAttributeExtractor());
        extractor.addExtractor(new RemoteHeadersFromAttributeExtractor());

        return extractor;
    }

}
