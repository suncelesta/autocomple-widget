package com.autocomple.extracting.fromjs;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

import static com.autocomple.config.ConfigDefaults.DEFAULT_HINT_ENABLED;

public class HintEnabledFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        String hindEnabledAsString = jso.getString(Tokens.HINT_ENABLED);

        if (hindEnabledAsString != null) {
            try {
                boolean hintEnabled = Boolean.parseBoolean(hindEnabledAsString);
                config.setHintEnabled(hintEnabled);

            } catch (Exception ignored) {
                Logging.warning("Parameter '" + Tokens.HINT_ENABLED + "' value '" + hindEnabledAsString + "' is not valid. " +
                        "Default value '" + DEFAULT_HINT_ENABLED + "' is used. Allowed values: true, false.");
            }
        }

        return config;
    }

}
