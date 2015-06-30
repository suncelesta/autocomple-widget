package com.autocomple.embedding;

import com.google.gwt.dom.client.Element;

public class ForEmbedding<Config> {

    private final Element target;
    private final Config config;

    public ForEmbedding(Element target, Config config) {
        this.target = target;
        this.config = config;
    }

    public Element getTarget() {
        return target;
    }

    public Config getConfig() {
        return config;
    }
}
