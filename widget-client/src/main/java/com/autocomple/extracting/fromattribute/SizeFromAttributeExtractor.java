package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Size;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import java.util.Arrays;

import static com.autocomple.config.ConfigDefaults.DEFAULT_SIZE;
import static com.autocomple.config.Tokens.SIZE_ATTR;

public class SizeFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {

    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(SIZE_ATTR)) {
            try {
                config.setSize(extractSize(inputElement));

            } catch (Exception ignored) {
                Logging.warning("Attribute '" + Tokens.SIZE_ATTR + "' value '" + attr(inputElement) + "' is not valid. " +
                        "Default size value '" + DEFAULT_SIZE + "' is used. " +
                        "Allowed values: " + Arrays.toString(com.autocomple.config.Size.values()));
            }
        }
        return config;
    }

    private Size extractSize(InputElement inputElement) {
        return Size.valueOf(attr(inputElement).toUpperCase());
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(SIZE_ATTR);
    }
}
