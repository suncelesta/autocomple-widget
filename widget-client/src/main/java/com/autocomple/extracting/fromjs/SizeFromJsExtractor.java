package com.autocomple.extracting.fromjs;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.Size;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;

import java.util.Arrays;

import static com.autocomple.config.ConfigDefaults.DEFAULT_SIZE;

public class SizeFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {

    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        String sizeAsString = jso.getString(Tokens.SIZE);

        if (sizeAsString != null) {
            try {
                Size size = Size.valueOf(sizeAsString.toUpperCase());
                config.setSize(size);

            } catch (Exception ignored) {
                Logging.warning("Parameter '" + Tokens.SIZE + "' value '" + sizeAsString + "' is not valid. " +
                        "Default size value '" + DEFAULT_SIZE + "' is used. " +
                        "Allowed values: " + Arrays.toString(com.autocomple.config.Size.values()));
            }
        }

        return config;
    }

}
