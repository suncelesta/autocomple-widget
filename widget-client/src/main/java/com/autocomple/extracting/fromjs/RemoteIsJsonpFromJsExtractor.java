package com.autocomple.extracting.fromjs;

import com.autocomple.common.Logging;
import com.autocomple.common.Jso;
import com.autocomple.config.AutocompleConfig;
import com.autocomple.config.ConfigDefaults;
import com.autocomple.config.Tokens;
import com.autocomple.extracting.ConfigExtractor;
import com.google.gwt.core.client.GWT;

public class RemoteIsJsonpFromJsExtractor implements ConfigExtractor<Jso, AutocompleConfig> {
    @Override
    public AutocompleConfig extract(Jso jso, AutocompleConfig config) {
        Jso source = jso.getJso(Tokens.SOURCE);

        if (source != null) {
            Jso remote = source.getJso(Tokens.REMOTE);

            if (remote != null) {
                String isJsonpAsString = remote.getString(Tokens.IS_JSONP);

                if (isJsonpAsString != null) {
                    try {
                        boolean isJsonp = Boolean.parseBoolean(String.valueOf(isJsonpAsString));
                        config.setIsJsonp(isJsonp);

                    } catch (Exception ignored) {
                        Logging.warning("Parameter '" + Tokens.IS_JSONP + "' value '" + isJsonpAsString + "' is not valid. " +
                                "Default value '" + ConfigDefaults.DEFAULT_REMOTE_IS_JSONP + "' is used. Allowed values: true, false.");
                    }
                }
            }
        }

        return config;
    }

}
