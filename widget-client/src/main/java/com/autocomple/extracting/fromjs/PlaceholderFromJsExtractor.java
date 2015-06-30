package com.autocomple.extracting.fromjs;

import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class PlaceholderFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        String elementId = jso.getString("elementId");

        if (elementId != null) {
            Element target = DOM.getElementById(elementId);

            if (target != null) {
                String placeholder = target.getAttribute(Tokens.PLACEHOLDER);

                if (placeholder != null) {
                    config.setPlaceholder(placeholder);
                }
            }
        }

        return config;
    }

}
