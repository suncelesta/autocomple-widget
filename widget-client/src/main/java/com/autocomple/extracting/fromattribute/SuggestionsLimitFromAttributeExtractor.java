package com.autocomple.extracting.fromattribute;

import com.autocomple.common.Logging;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.ConfigDefaults;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.dom.client.InputElement;

import static com.autocomple.config.ConfigDefaults.MAX_SUGGESTIONS_LIMIT;
import static com.autocomple.config.ConfigDefaults.MIN_SUGGESTIONS_LIMIT;
import static com.autocomple.config.Tokens.SUGGESTIONS_LIMIT_ATTR;

public class SuggestionsLimitFromAttributeExtractor implements ConfigExtractor<InputElement, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(InputElement inputElement, AutocompleConfig config) {

        if (inputElement.hasAttribute(SUGGESTIONS_LIMIT_ATTR)) {
            try {
                int suggestionsLimit = extractSuggestionsLimit(inputElement);

                config.setSuggestionsLimit(sanitize(suggestionsLimit));

            } catch (Exception ignored) {
                Logging.warning("Attribute '" + Tokens.SUGGESTIONS_LIMIT_ATTR + "' value '" + attr(inputElement) + "' is not valid. " +
                        "Default value '" + ConfigDefaults.DEFAULT_SUGGESTIONS_LIMIT + "' is used.");
            }
        }

        return config;
    }

    private int sanitize(int suggestionsLimit) {
        if (suggestionsLimit < MIN_SUGGESTIONS_LIMIT) {
            Logging.warning("Attribute '" + Tokens.SUGGESTIONS_LIMIT_ATTR + "' value '" + suggestionsLimit +
                    "' exceeded min value " + MIN_SUGGESTIONS_LIMIT + ". Min value is used.");

            return MIN_SUGGESTIONS_LIMIT;
        }

        if (suggestionsLimit > MAX_SUGGESTIONS_LIMIT) {
            Logging.warning("Attribute '" + Tokens.SUGGESTIONS_LIMIT_ATTR + "' value '" + suggestionsLimit +
                    "' exceeded max value " + MAX_SUGGESTIONS_LIMIT + ". Max value is used.");

            return MAX_SUGGESTIONS_LIMIT;
        }

        return suggestionsLimit;
    }

    private int extractSuggestionsLimit(InputElement inputElement) {
        return Integer.parseInt(attr(inputElement));
    }

    private String attr(InputElement inputElement) {
        return inputElement.getAttribute(SUGGESTIONS_LIMIT_ATTR);
    }
}
