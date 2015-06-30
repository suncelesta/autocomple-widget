package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.ConfigDefaults.DEFAULT_HINT_ENABLED;
import static com.autocomple.config.Tokens.HINT_ENABLED_ATTR;

public class HintEnabledFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(HINT_ENABLED_ATTR)) {
            try {
                config.setHintEnabled(extractHintEnabled(inputElement));

            } catch (Exception ignored) {
                Logging.warning("Attribute '" + Tokens.HINT_ENABLED_ATTR + "' value '" + attr(inputElement) + "' is not valid. " +
                        "Default value '" + DEFAULT_HINT_ENABLED + "' is used. Allowed values: true, false.");
            }
        }

        return config;
    }

    private boolean extractHintEnabled(InputElement inputElement) {
        return Boolean.parseBoolean(attr(inputElement));
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(HINT_ENABLED_ATTR);
    }

}
