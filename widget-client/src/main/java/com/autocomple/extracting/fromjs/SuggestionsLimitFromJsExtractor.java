package com.autocomple.extracting.fromjs;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.ConfigDefaults;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

import static com.autocomple.config.ConfigDefaults.MAX_SUGGESTIONS_LIMIT;
import static com.autocomple.config.ConfigDefaults.MIN_SUGGESTIONS_LIMIT;

public class SuggestionsLimitFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        String limitAsString = jso.getString(Tokens.SUGGESTIONS_LIMIT);

        if (limitAsString != null) {
            try {
                int suggestionLimit = Integer.parseInt(limitAsString);
                config.setSuggestionsLimit(sanitize(suggestionLimit));
            } catch (Exception ignored) {
                Logging.warning("Parameter '" + Tokens.SUGGESTIONS_LIMIT + "' value '" + limitAsString + "' is not valid. " +
                        "Default value '" + ConfigDefaults.DEFAULT_SUGGESTIONS_LIMIT + "' is used.");
            }

        }

        return config;
    }

    private int sanitize(int suggestionsLimit) {
        if (suggestionsLimit < MIN_SUGGESTIONS_LIMIT) {
            Logging.warning("Parameter '" + Tokens.SUGGESTIONS_LIMIT + "' value '" + suggestionsLimit +
                    "' exceeded min value " + MIN_SUGGESTIONS_LIMIT + ". Min value is used.");

            return MIN_SUGGESTIONS_LIMIT;
        }

        if (suggestionsLimit > MAX_SUGGESTIONS_LIMIT) {
            Logging.warning("Parameter '" + Tokens.SUGGESTIONS_LIMIT + "' value '" + suggestionsLimit +
                    "' exceeded max value " + MAX_SUGGESTIONS_LIMIT + ". Max value is used.");

            return MAX_SUGGESTIONS_LIMIT;
        }

        return suggestionsLimit;
    }

}
