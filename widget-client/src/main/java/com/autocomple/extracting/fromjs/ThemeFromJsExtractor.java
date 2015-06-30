package com.autocomple.extracting.fromjs;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Theme;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

import java.util.Arrays;

import static com.autocomple.config.ConfigDefaults.DEFAULT_THEME;

public class ThemeFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {

    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        String themeAsString = jso.getString(Tokens.THEME);

        if (themeAsString != null) {
            try {
                Theme theme = Theme.valueOf(themeAsString.toUpperCase());
                config.setTheme(theme);

            } catch (Exception ignored) {
                Logging.warning("Parameter '" + Tokens.THEME + "' value '" + themeAsString + "' is not valid. " +
                        "Default theme value '" + DEFAULT_THEME + "' is used. " +
                        "Allowed values: " + Arrays.toString(com.autocomple.config.Theme.values()));
            }
        }

        return config;
    }

}
