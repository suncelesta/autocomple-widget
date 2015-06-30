package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Theme;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import java.util.Arrays;

import static com.autocomple.config.ConfigDefaults.DEFAULT_THEME;
import static com.autocomple.config.Tokens.THEME_ATTR;

public class ThemeFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(THEME_ATTR)) {
            try {
                config.setTheme(extractTheme(inputElement));

            } catch (Exception ignored) {
                Logging.warning("Attribute '" + Tokens.THEME_ATTR + "' value '" + attr(inputElement) + "' is not valid. " +
                        "Default theme value '" + DEFAULT_THEME + "' is used. " +
                        "Allowed values: " + Arrays.toString(com.autocomple.config.Theme.values()));
            }
        }

        return config;
    }

    private Theme extractTheme(InputElement inputElement) {
        return Theme.valueOf(attr(inputElement).toUpperCase());
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(THEME_ATTR);
    }
}
