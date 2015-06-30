package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.ConfigDefaults;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.Tokens.REMOTE_IS_JSONP_ATTR;

public class RemoteIsJsonpFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {
        if (inputElement.hasAttribute(REMOTE_IS_JSONP_ATTR)) {
            try {
                config.setIsJsonp(extractIsJsonp(inputElement));

            } catch (Exception ignored) {
                Logging.warning("Attribute '" + Tokens.REMOTE_IS_JSONP_ATTR + "' value '" + attr(inputElement) + "' is not valid. " +
                        "Default value '" + ConfigDefaults.DEFAULT_REMOTE_IS_JSONP + "' is used. Allowed values: true, false.");
            }
        }

        return config;
    }

    private boolean extractIsJsonp(InputElement inputElement) {
        return Boolean.parseBoolean(attr(inputElement));
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(REMOTE_IS_JSONP_ATTR);
    }
}
