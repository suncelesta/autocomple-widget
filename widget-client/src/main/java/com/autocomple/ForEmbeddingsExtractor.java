package com.autocomple;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.embedding.ForEmbedding;

import java.util.ArrayList;
import java.util.List;

class ForEmbeddingsExtractor {

    private static final List<ForEmbeddingsExtractor> EXTRACTORS = new ArrayList<>(2);
    static {
        EXTRACTORS.add(new ForEmbeddingsFromJsExtractor());
        EXTRACTORS.add(new ForEmbeddingsFromAttributesExtractor());
    }

    public List<ForEmbedding<AutocompleConfig>> extract() {
        List<ForEmbedding<AutocompleConfig>> result = new ArrayList<>();

        for (ForEmbeddingsExtractor extractor : EXTRACTORS) {
            result.addAll(extractor.extract());
        }

        return result;
    }

}
