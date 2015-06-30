package com.autocomple.extracting.fromattribute;

import com.autocomple.config.AutocompleConfig;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.Tokens.PLACEHOLDER_ATTR;

public class PlaceholderFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(PLACEHOLDER_ATTR)) {
            String placeholder = inputElement.getAttribute(PLACEHOLDER_ATTR);
            config.setPlaceholder(placeholder);
        }

        return config;
    }

}
